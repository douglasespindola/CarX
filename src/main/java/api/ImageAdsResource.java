package api;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entity.ImageAds;
import helper.UploadHelper;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.ArrayList;
import java.util.List;

@Path("image-ads")
public class ImageAdsResource {

    @Context
    ServletContext context;

    private String UPLOADED_FILE_PATH = "";

    @PostConstruct
    public void PostConstruct(){
        this.UPLOADED_FILE_PATH = this.context.getInitParameter("upload_path");
    }

    @POST
    @Path("/{id}")
    @Consumes("multipart/form-data")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response uploadFile(@PathParam("id") Integer id, MultipartFormDataInput dataInput) {
        try {
            ArrayList file = UploadHelper.updateArchive(dataInput,UPLOADED_FILE_PATH );
            if(file==null){
                return Response.status(400).entity(file).build();
            }
            List<ImageAds> imageAds = new ArrayList<>();
            for (Object f : file){
                System.out.print(f);
                ImageAds imageInsert = new ImageAds();
                imageInsert.setAdsId(id);
                imageInsert.setName(f.toString());
                imageAds.add(imageInsert);
            }
            return Response.status(200).entity(imageAds).build();
        } catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

}