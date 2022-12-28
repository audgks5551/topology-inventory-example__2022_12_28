package io.mhan.domain.specification;

import io.mhan.domain.entity.CoreRouter;
import io.mhan.domain.exception.GenericSpecificationException;
import io.mhan.domain.specification.shared.AbstractSpecification;

public class EmptyRouterSpec extends AbstractSpecification<CoreRouter> {
    @Override
    public boolean isSatisfiedBy(CoreRouter coreRouter) {
        return coreRouter.getRouters() == null || coreRouter.getRouters().isEmpty();
    }

    @Override
    public void check(CoreRouter coreRouter) throws GenericSpecificationException {
        if (!isSatisfiedBy(coreRouter)) {
            throw new GenericSpecificationException(
                    "It isn't allowed to remove a core router with" +
                            "other routers attached to it");
        }
    }
}
