package io.mhan.domain.entity;

import io.mhan.domain.specification.EmptyRouterSpec;
import io.mhan.domain.specification.EmptySwitchSpec;
import io.mhan.domain.specification.SameCountrySpec;
import io.mhan.domain.specification.SameIpSpec;
import io.mhan.domain.vo.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class CoreRouter extends Router {

    @Getter
    private Map<Id, Router> routers;

    @Builder
    public CoreRouter(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType, Map<Id, Router> routers) {
        super(id, vendor, model, ip, location, routerType);
        this.routers = routers;
    }

    public Router addRouter(Router anyRouter) {
        var sameCountrySpec = new SameCountrySpec(this);
        var sameIpSpec = new SameIpSpec(this);

        sameCountrySpec.check(anyRouter);
        sameIpSpec.check(anyRouter);

        return this.routers.put(anyRouter.id, anyRouter);
    }

    public Router removeRouter(Router anyRouter) {
        var emptyRoutersSpec = new EmptyRouterSpec();
        var emptySwitchSpec = new EmptySwitchSpec();

        switch (anyRouter.routerType) {
            case CORE:
                var coreRouter = (CoreRouter) anyRouter;
                emptyRoutersSpec.check(coreRouter);
            case EDGE:
                var edgeRouter = (EdgeRouter) anyRouter;
                emptySwitchSpec.check(edgeRouter);
        }

        return this.routers.remove(anyRouter.id);
    }
}
