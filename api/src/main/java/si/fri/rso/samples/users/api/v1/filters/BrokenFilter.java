package si.fri.rso.samples.users.api.v1.filters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import si.fri.rso.samples.users.config.RestProperties;

@Provider
@ApplicationScoped
public class BrokenFilter implements ContainerRequestFilter {

    @Inject
    private RestProperties restProperties;

    @Override
    public void filter(ContainerRequestContext ctx)  {
        if (restProperties.getMaintenanceMode()) {

            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"message\" : \"Maintenance mode enabled\"}")
                    .build());
        } else if (restProperties.getBroken()) {
            ctx.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\" : \"Broken mode enabled\"}")
                    .build());
        }
    }
}