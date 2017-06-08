package api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface IResource<T> {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getAll();

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    void remove (Integer id);

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    T update (String json);

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    T create (String json);



}
