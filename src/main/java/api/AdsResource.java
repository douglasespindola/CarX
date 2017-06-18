package api;


import com.google.gson.Gson;
import dto.AdsDto;
import dto.ImageAdsDto;
import dto.MessageDto;
import entity.ImageAds;
import service.AdsService;
import entity.Ads;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/ads")
public class AdsResource extends ApplicationResource{

    @Inject
    private AdsService adsService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getAll() {
        try {
            Gson json = new Gson();
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(adsService.getAll())).build();
        } catch (Exception e) {
            //Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response update(String jsonString) {
        try {
            Gson json = new Gson();
            Ads ads = json.fromJson(jsonString, Ads.class);
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(adsService.update(ads))).build();
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
    public Response create(String jsonString) {
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
    public Response remove(@PathParam("id") Integer id) {
        try {
            AdsDto ads = adsService.get(id);

            if(ads.getImageAds()!=null){
                for(ImageAdsDto imageAds : ads.getImageAds()){
                    (new File(this.UPLOADED_FILE_PATH+imageAds.getName())).delete();
                }
            }

            adsService.remove(id);
            MessageDto message = new MessageDto();
            message.setMessage("Removido com sucesso");
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(message).build();
        } catch (Exception e) {
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();

        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response get(@PathParam("id") Integer id) {
        try {
            Gson json = new Gson();
            AdsDto ads = adsService.get(id);
            if (ads == null) {
                return Response.status(400).type(MediaType.APPLICATION_JSON).entity(json.toJson(ads)).build();
            }
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json.toJson(ads)).build();
        } catch (Exception e) {
            Gson json = new Gson();
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }
}

