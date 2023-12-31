package com.distribuida.api;

import com.distribuida.db.Singer;
import com.distribuida.dto.SingerDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.distribuida.service.SingerService;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("/singers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class SingerResource {

    @Inject
    private SingerService singerService;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response getAllSingers() {
        List<SingerDto> singers = singerService.getAllSingersDto();
        return Response.ok(singers).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getSingerById(@PathParam("id") Integer id) {
        SingerDto singer = singerService.getSingerDtoById(id);
        if (singer != null) {
            return Response.ok(singer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response createSinger(Singer singer) {
        singerService.createSinger(singer);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response updateSinger(@PathParam("id") Integer id, Singer singer) {
        singer.setId(id);
        singerService.updateSinger(singer);
        return Response.ok().build();
    }

    @DELETE
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response deleteSinger(@PathParam("id") Integer id) {
        singerService.deleteSinger(id);
        return Response.noContent().build();
    }
}
