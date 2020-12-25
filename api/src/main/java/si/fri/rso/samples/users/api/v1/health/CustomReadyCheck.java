package si.fri.rso.samples.users.api.v1.health;

import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.fri.rso.samples.users.config.RestProperties;
import si.fri.rso.samples.users.lib.User;
import si.fri.rso.samples.users.services.beans.UsersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Readiness
@ApplicationScoped
public class CustomReadyCheck implements HealthCheck {

    @Inject
    private UsersBean usersBean;

    @Inject
    private RestProperties restProperties;

    public HealthCheckResponse call() {

        List<User> users = usersBean.getUsers();

        if (!restProperties.getMaintenanceMode()) {
            return HealthCheckResponse.up(CustomReadyCheck.class.getSimpleName());
        } else {
            return HealthCheckResponse.down(CustomReadyCheck.class.getSimpleName());
        }
    }

}