package si.fri.rso.samples.users.api.v1.resources;

import org.json.JSONArray;
import org.json.JSONObject;
import si.fri.rso.samples.users.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {
    private Logger log = Logger.getLogger(UsersResource.class.getName());

    @Context
    protected UriInfo uriInfo;

    @Inject
    private RestProperties restProperties;

    @GET
    @Path("/info")
    public Response getInfo() {
        JSONArray imena = new JSONArray();
        imena.put("jj6706");
        imena.put("ms9922");
        String opis = "Aplikacija za iskanje cimrov v študentskih stanovanjih.";
        JSONArray mikrostoritve = new JSONArray();
        mikrostoritve.put("http://52.188.216.238:8080/v1/users");
        mikrostoritve.put("http://40.88.248.233:8080/v1/apartments");
        JSONArray githubi = new JSONArray();
        githubi.put("https://github.com/FRI-jug-sincek/Users");
        githubi.put("https://github.com/FRI-jug-sincek/Apartments");
        JSONArray dockers = new JSONArray();
        dockers.put("https://hub.docker.com/r/frijugsincek/users");
        dockers.put("https://hub.docker.com/r/frijugsincek/apartments");
        String jsonString = new JSONObject()
                .put("clani", imena)
                .put("opis_projekta", opis)
                .put("mikrostoritve", mikrostoritve)
                .put("github", githubi)
                .put("travis", new JSONArray())
                .put("dockerhub", dockers)
                .toString();
        return Response.status(Response.Status.OK).entity(jsonString).build();
    }

    @POST
    @Path("break")
    public Response makeBroken() {

        restProperties.setBroken(true);

        return Response.status(Response.Status.OK).build();
    }
}
