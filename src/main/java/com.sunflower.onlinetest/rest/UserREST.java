package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.entity.User;
import com.sunflower.onlinetest.service.serviceImpl.UserServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@RequestScoped
public class UserREST {

    @Inject
    private UserServiceImpl userService;

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("userId") Integer userId) {
        System.out.println("user id: " + userId);
        User user = userService.findById(userId);
        System.out.println("user: " + user);
        return Response.ok(user).build();
    }
}
