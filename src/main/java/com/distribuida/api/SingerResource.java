package com.distribuida.api;

import com.distribuida.db.Singer;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.distribuida.service.SingerService;

@Path("/singers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class SingerResource {

    @Inject
    private SingerService singerService;

    @GET
    public Response getAllSingers() {
        JsonArray singers = singerService.getAllSingersAsJsonArray();
        return Response.ok(singers).build();
    }

    @GET
    @Path("/{id}")
    public Response getSingerById(@PathParam("id") Integer id) {
        JsonObject singer = singerService.getSingerByIdAsJsonObject(id);
        if (singer != null) {
            return Response.ok(singer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createSinger(Singer singer) {
        singerService.createSinger(singer);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSinger(@PathParam("id") Integer id, Singer singer) {
        singer.setId(id);
        singerService.updateSinger(singer);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSinger(@PathParam("id") Integer id) {
        singerService.deleteSinger(id);
        return Response.noContent().build();
    }
}
