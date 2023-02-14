package com.allen.digital.resources;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {
    @GET
    @Timed
    public String show() {
        return "yay";
    }

    @GET
    @Metered(name = "fancyName") // If name isn't specified, the meter will given the name of the method it decorates.
    @Path("/metered")
    public String metered() {
        return "woo";
    }

    @GET
    @ExceptionMetered(cause = IOException.class) // Default cause is Exception.class
    @Path("/exception-metered")
    public String exceptionMetered(@QueryParam("splode") @DefaultValue("false") boolean splode) throws IOException {
        if (splode) {
            throw new IOException("AUGH");
        }
        return "fuh";
    }

    @GET
    @ResponseMetered
    @Path("/response-metered")
    public Response responseMetered(@QueryParam("invalid") @DefaultValue("false") boolean invalid) {
        if (invalid) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }
}
