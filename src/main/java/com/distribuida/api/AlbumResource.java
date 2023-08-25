package com.distribuida.api;

import com.distribuida.db.Album;
import com.distribuida.db.Singer;
import com.distribuida.service.AlbumService;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.distribuida.service.SingerService;

@Path("/albums")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AlbumResource {

    @Inject
    private AlbumService albumService;

    @GET
    public Response getAllAlbums() {
        JsonArray albums = albumService.getAllAlbumAsJsonArray();
        return Response.ok(albums).build();
    }

    @GET
    @Path("/{id}")
    public Response getAlbumById(@PathParam("id") Integer id) {
        JsonObject album = albumService.getAlbumByIdAsJsonObject(id);
        if (album != null) {
            return Response.ok(album).build();
        } else {
            return  Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlbum(Album album) {
        albumService.createAlbum(album);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAlbum(@PathParam("id") Integer id, Album album) {
        album.setId(id);
        albumService.updateAlbum(album);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAlbum(@PathParam("id") Integer id) {
        albumService.deleteAlbum(id);
        return Response.noContent().build();
    }
}
