package com.infoshareacademy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class UserService {

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
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHeader(@HeaderParam("user-agent") String agent) {

        return Response.status(Response.Status.OK).entity("Header user-agent " + agent).build();
    }

}
