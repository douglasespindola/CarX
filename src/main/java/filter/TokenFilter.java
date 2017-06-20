package filter;

import org.joda.time.DateTime;
import service.UserService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;


public class TokenFilter implements Filter {

    @Inject
    private UserService userService;




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try{
            String token = request.getHeader("Authorization");
            System.out.println(token);
            if (token.equals("") || token.equals(null)){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }else{

                String tokenDecode = new String(DatatypeConverter.parseBase64Binary(token));
                String[] list = tokenDecode.split(":");
                Long time = Long.parseLong(list[1]);
                if (new DateTime().getMillis() > time ){
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
            chain.doFilter(request, servletResponse);
        }catch (Exception e){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    @Override
    public void destroy() {}
}