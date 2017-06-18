package api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import helper.UploadHelper;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("image-ads")
public class ImageAdsResource {

    private final String UPLOADED_FILE_PATH = "c:/uploads_carx/";

    @POST
    @Path("/{id}")
    @Consumes("multipart/form-data")
    public Response uploadFile(@PathParam("id") Integer id, MultipartFormDataInput dataInput) {

        String file = UploadHelper.updateArchive(dataInput,UPLOADED_FILE_PATH );
        if(file==null){
            return Response.status(400).entity(file).build();
        }
        return Response.status(200).entity(file).build();
    }

}