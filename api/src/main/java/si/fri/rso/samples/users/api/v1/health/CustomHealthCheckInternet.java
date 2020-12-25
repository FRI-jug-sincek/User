package si.fri.rso.samples.users.api.v1.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import si.fri.rso.samples.users.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;

@Liveness
@ApplicationScoped
public class CustomHealthCheckInternet implements HealthCheck {

    @Inject
    private RestProperties restProperties;

    @Override
    public HealthCheckResponse call() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
            Object objData = urlConnect.getContent();

        } catch (Exception e) {
            return HealthCheckResponse.down(CustomHealthCheckInternet.class.getSimpleName());
        }

        return HealthCheckResponse.up(CustomHealthCheckInternet.class.getSimpleName());
    }
}