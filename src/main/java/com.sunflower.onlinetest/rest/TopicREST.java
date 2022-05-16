package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.rest.request.TopicRequest;
import com.sunflower.onlinetest.service.TopicService;
import com.sunflower.onlinetest.service.response.ResponseObjectWithJWT;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("topics")
@RequestScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicREST {

    @Inject
    private TopicService topicService;

    @GET
    @Path("")
    public Response getAll() {
        try {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message("successfully")
                    .data(topicService.getAll())
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }

    @GET
    @Path("{code}")
    public Response getByCode(@PathParam("code") String code) {
        try {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message("successfully")
                    .data(topicService.getByCode(code))
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }

    @PUT
    @Path("create")
    public Response create(TopicRequest topicRequest) {
        try {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message("successfully")
                    .data(topicService.create(topicRequest))
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }
}
