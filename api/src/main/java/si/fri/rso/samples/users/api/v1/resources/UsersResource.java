package si.fri.rso.samples.users.api.v1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.logging.Logger;

import si.fri.rso.samples.users.config.RestProperties;
import si.fri.rso.samples.users.lib.User;
import si.fri.rso.samples.users.services.beans.UsersBean;

@ApplicationScoped
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private Logger log = Logger.getLogger(UsersResource.class.getName());

    @Inject
    private UsersBean usersBean;

    @Context
    protected UriInfo uriInfo;

    @Inject
    private RestProperties restProperties;

    @GET
    public Response getUsers() {

        List<User> users = usersBean.getUsers();

        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/filtered")
    public Response getUsersFiltered() {

        List<User> users;

        users = usersBean.getUsersFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") Integer userId) {

        User user = usersBean.getUser(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(user).build();
    }

    @POST
    public Response createUser(User user) {

        if ((user.getName() == null || user.getSurname() == null || user.getAge() == null || user.getLocation() == null )) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            user = usersBean.createUser(user);
        }

        return Response.status(Response.Status.OK).entity(user).build();

    }

    @PUT
    @Path("{userId}")
    public Response putUser(@PathParam("userId") Integer userId, User user) {

        user = usersBean.putUser(userId, user);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") Integer userId) {

        boolean deleted = usersBean.deleteUser(userId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
