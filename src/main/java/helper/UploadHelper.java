package helper;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by felipemoura on 17/06/2017.
 */
public class UploadHelper {


    public static String getFileName(MultivaluedMap<String, String> header) {

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

    public static void writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();
    }

    public static String updateArchive(MultipartFormDataInput dataInput, String UPLOADED_FILE_PATH){

        String fileName = "";
        Map<String, List<InputPart>> uploadForm = dataInput.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");

        for (InputPart inputPart : inputParts)
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = UploadHelper.getFileName(header);

                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                byte[] bytes = IOUtils.toByteArray(inputStream);
                File file = new File(UPLOADED_FILE_PATH);
                if (!file.exists()) {
                    file.mkdir();
                }
                UploadHelper.writeFile(bytes, UPLOADED_FILE_PATH + fileName);

                return fileName;

            } catch (IOException e) {
                return null;
            }

        return fileName;
    }
}
