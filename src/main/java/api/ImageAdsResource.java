package api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("image-ads")
public class ImageAdsResource {

    /* Aqui o ideal seria colocar no demoiselle.properties */
    private final String UPLOADED_FILE_PATH = "c:/uploads_carx/";

    @POST
    @Path("/{id}")
    @Consumes("multipart/form-data")
    public Response uploadFile(@PathParam("id") Integer id, MultipartFormDataInput dataInput) {

        String fileName = "";
        Response response = null;

        Map<String, List<InputPart>> uploadForm = dataInput.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");

        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);

                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                byte[] bytes = IOUtils.toByteArray(inputStream);

                writeFile(bytes, UPLOADED_FILE_PATH + fileName);

                response = Response.status(201).entity(fileName + id).build();

            } catch (IOException e) {
                e.printStackTrace();
                response = Response.status(400).build();
            }
        }
        return response;
    }

    /**
     * Exemplo de header (http://en.wikipedia.org/wiki/List_of_HTTP_header_fields)
     * {
     *  Content-Type: multipart/form-data,
     *  Content-Disposition: form-data; name="nameOfInputTypeFileOnHtmlForm"; filename="test.pdf"
     * }
     **/
    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    private void writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();
    }
}