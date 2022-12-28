package io.mhan.domain.specification;

import io.mhan.domain.entity.Equipment;
import io.mhan.domain.entity.Switch;
import io.mhan.domain.exception.GenericSpecificationException;
import io.mhan.domain.specification.shared.AbstractSpecification;
import io.mhan.domain.vo.IP;
import io.mhan.domain.vo.Network;

public class NetworkAvailabilitySpec extends AbstractSpecification<Equipment> {
    private IP address;
    private String name;
    private int cidr;

    public NetworkAvailabilitySpec(Network network){
        this.address = network.getNetworkAddress();
        this.name = network.getNetworkName();
        this.cidr = network.getNetworkCidr();
    }

    @Override
    public boolean isSatisfiedBy(Equipment switchNetworks){
        return switchNetworks != null && isNetworkAvailable(switchNetworks);
    }

    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("This network already exists");
    }

    private boolean isNetworkAvailable(Equipment switchNetworks){
        var availability = true;
        for (Network network : ((Switch)switchNetworks).getSwitchNetworks()) {
            if(network.getNetworkAddress().equals(address) &&
                    network.getNetworkName().equals(name) &&
                    network.getNetworkCidr() == cidr)
                availability = false;
            break;
        }
        return availability;
    }
}
