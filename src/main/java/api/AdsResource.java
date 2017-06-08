package api;


import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.TODO;
import dto.MessageDto;
import service.AdsService;
import entity.Ads;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ads")
public class AdsResource{
    //public class AdsResource implements IResource <ads>
    @Inject
    private AdsService adsService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getAllAds() { return new Gson().toJson(adsService.getAllAds());
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response updateAds(String jsonString) {
        try {
            Gson json = new Gson();
            Ads ads = json.fromJson(jsonString, Ads.class);
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(adsService.update(ads)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createAds(String jsonString) {
        try {
            Gson json = new Gson();
            Ads ads = json.fromJson(jsonString, Ads.class);
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(adsService.create(ads))).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response removeAds(@PathParam("id") Integer id) {
        try {
            Gson json = new Gson();
            adsService.remove(id);
            MessageDto message = new MessageDto();
            message.setMessage("Removido com sucesso");
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(message)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAds(@PathParam("id") Integer id) {
        try {
            Gson json = new Gson();
            Ads ads = adsService.getAds(id);
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(ads)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }
}

