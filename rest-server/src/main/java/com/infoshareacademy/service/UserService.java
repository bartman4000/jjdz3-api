package com.infoshareacademy.service;

import com.infoshareacademy.model.User;
import com.infoshareacademy.model.UserStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;

@Path("/")
public class UserService {

    @Inject
    UserStore userStore;

    @Context
    private UriInfo uriInfo;

    private Logger LOG = LoggerFactory.getLogger(UserService.class);

    public UserService() {
    }

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(@PathParam("name") String name) {
        LOG.info("Saying hello "+name);

        LOG.info("path:"+uriInfo.getPath());
        LOG.info("baseUri:"+uriInfo.getBaseUri());
        LOG.info("pathParams:"+uriInfo.getPathParameters());

        return Response.ok().entity("Hello "+name).build();
    }

    @GET
    @Path("/header")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeader(@HeaderParam("user-agent") String agent) {

        return Response.status(Response.Status.OK).entity("Header user-agent " + agent).build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = userStore.getUsers();
        if(users.isEmpty())
        {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("id") Integer id) {

        Optional<User> user = userStore.getUser(id);
        if(user.isPresent())
        {
            return Response.status(Response.Status.OK).entity(user.get()).build();
        }
        else
        {
            return Response.noContent().build();
        }
    }

}
