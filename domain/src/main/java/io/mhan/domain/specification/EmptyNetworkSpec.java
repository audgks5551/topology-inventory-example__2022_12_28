package io.mhan.domain.specification;

import io.mhan.domain.entity.Switch;
import io.mhan.domain.exception.GenericSpecificationException;
import io.mhan.domain.specification.shared.AbstractSpecification;

public class EmptyNetworkSpec extends AbstractSpecification<Switch> {
    @Override
    public boolean isSatisfiedBy(Switch switchNetwork) {
        return switchNetwork.getSwitchNetworks() == null ||
                switchNetwork.getSwitchNetworks().isEmpty();
    }

    @Override
    public void check(Switch anySwitch) throws GenericSpecificationException {
        if (!isSatisfiedBy(anySwitch)) {
            throw new GenericSpecificationException("error");
        }
    }
}
