package si.fri.rso.samples.users.config;

import javax.enterprise.context.ApplicationScoped;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

//@ConfigBundle("rest-properties")
//@ApplicationScoped
//public class RestProperties {
//
//    @ConfigValue(watch = true)
//    private Boolean broken;
//
//    public Boolean getBroken() { return this.broken; }
//
//    public void setBroken(final Boolean broken) {
//        this.broken = broken;
//    }
//}
@ConfigBundle("rest-properties")
@ApplicationScoped
public class RestProperties {

    @ConfigValue(watch = true)
    private Boolean maintenanceMode;

    @ConfigValue(watch = true)
    private Boolean broken;

    public Boolean getMaintenanceMode() {
        return this.maintenanceMode;
    }

    public void setMaintenanceMode(final Boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public Boolean getBroken() {
        return this.broken;
    }

    public void setBroken(final Boolean broken) {
        this.broken = broken;
    }
}