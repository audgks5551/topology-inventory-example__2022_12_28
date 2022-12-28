package io.mhan.domain.specification;

import io.mhan.domain.entity.Equipment;
import io.mhan.domain.exception.GenericSpecificationException;
import io.mhan.domain.specification.shared.AbstractSpecification;

public class SameIpSpec extends AbstractSpecification<Equipment> {
    private Equipment equipment;

    public SameIpSpec(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean isSatisfiedBy(Equipment anyEquipment) {
        return !equipment.getIp().equals(anyEquipment.getIp());
    }

    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if (!isSatisfiedBy(equipment)) {
            throw new GenericSpecificationException(
                    "It's not possible to attach routers with the same IP");
        }
    }
}
