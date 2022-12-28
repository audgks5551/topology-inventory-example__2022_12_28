package io.mhan.domain.specification;

import io.mhan.domain.entity.EdgeRouter;
import io.mhan.domain.exception.GenericSpecificationException;
import io.mhan.domain.specification.shared.AbstractSpecification;

public class EmptySwitchSpec extends AbstractSpecification<EdgeRouter> {
    @Override
    public boolean isSatisfiedBy(EdgeRouter edgeRouter) {
        return edgeRouter.getSwitches() == null || edgeRouter.getSwitches().isEmpty();
    }

    @Override
    public void check(EdgeRouter edgeRouter) throws GenericSpecificationException {
        if (!isSatisfiedBy(edgeRouter)) {
            throw new GenericSpecificationException(
                    "It isn't allowed to remove an edge router with" +
                            "a switch attached to it");
        }
    }
}
