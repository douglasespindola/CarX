package api;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/**
 * Created by felipemoura on 18/06/2017.
 */
public class ApplicationResource {
    @Context
    ServletContext context;
    protected String UPLOADED_FILE_PATH = "";

    @PostConstruct
    protected void PostConstruct(){
        this.UPLOADED_FILE_PATH = this.context.getInitParameter("upload_path");
    }
}
