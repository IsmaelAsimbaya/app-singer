package com.distribuida.api;

import com.distribuida.db.Instrument;
import com.distribuida.db.Singer;
import com.distribuida.dto.InstrumentDto;
import com.distribuida.service.InstrumentService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.util.List;

@Path("/instruments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class InstrumentResource {

    @Inject
    private InstrumentService instrumentService;

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response getAllInstrumentsDto() {
        List<InstrumentDto> instruments = instrumentService.getAllInstrumentsDto();
        return Response.ok(instruments).build();
    }

    @GET
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response getInstrumentDtoById(@PathParam("id") Integer id) {
        InstrumentDto instrument = instrumentService.getInstrumentDtoById(id);
        if (instrument != null) {
            return Response.ok(instrument).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    public Response createInstrument(Instrument instrument) {
        instrumentService.createInstrument(instrument);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response updateInstrument(@PathParam("id") Integer id, Instrument instrument) {
        instrument.setId(id);
        instrumentService.updateInstrument(instrument);
        return Response.ok().build();
    }

    @DELETE
    @Timeout(4000)
    @Retry(retryOn = Exception.class , maxRetries = 2)
    @Path("/{id}")
    public Response deleteInstrument(@PathParam("id") Integer id) {
        instrumentService.deleteInstrument(id);
        return Response.noContent().build();
    }
}
