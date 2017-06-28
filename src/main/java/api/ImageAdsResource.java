package api;

import com.google.gson.Gson;
import dto.MessageDto;
import entity.ImageAds;
import helper.UploadHelper;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import service.ImageAdsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Path("image-ads")
public class ImageAdsResource extends ApplicationResource {

    @Inject
    private ImageAdsService imageAdsService;

    @POST
    @Path("/admin/{ads_id}")
    @Consumes("multipart/form-data")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create(@PathParam("ads_id") Integer ads_id, MultipartFormDataInput dataInput) {
        try {
            ArrayList file = UploadHelper.updateArchive(dataInput,UPLOADED_FILE_PATH );
            if(file==null){
                return Response.status(400).entity(file).build();
            }
            List<ImageAds> imageAds = new ArrayList<>();
            for (Object f : file){
                ImageAds imageInsert = new ImageAds();
                imageInsert.setAdsId(ads_id);
                imageInsert.setName(f.toString());
                imageAdsService.create(imageInsert);
                imageAds.add(imageInsert);
            }
            return Response.status(200).entity(imageAds).build();
        } catch (Exception e){
            MessageDto message = new MessageDto();
            message.setMessage(e.getMessage() + e.getClass());
            return Response.status(400).type(MediaType.APPLICATION_JSON).entity(message).build();
        }
    }

    @DELETE
    @Path("/admin/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response remove(@PathParam("id") Integer id) {
        try {
            Gson json = new Gson();
            ImageAds imageAds = imageAdsService.get(id);
            imageAdsService.remove(id);
            (new File(this.UPLOADED_FILE_PATH+imageAds.getName())).delete();
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
}