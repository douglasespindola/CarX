package filter;

import com.google.gson.Gson;
import dto.MessageDto;
import dto.TokenDto;
import entity.User;
import org.joda.time.DateTime;
import service.UserService;

import javax.inject.Inject;
import javax.persistence.Query;
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

        Gson json = new Gson();
        MessageDto message = new MessageDto();

        try{
            String token = request.getHeader("Authorization");
            if (token.equals("") || token.equals(null)){
                resp.setHeader("Content-Type", "application/json;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getOutputStream().write(json.toJson(message).getBytes());
                return;
            }else{
                String tokenDecode = new String(DatatypeConverter.parseBase64Binary(token));
                String[] list = tokenDecode.split(":");
                Long time = Long.parseLong(list[1]);

                if (new DateTime().getMillis() > time ){
                    resp.setHeader("Content-Type", "application/json;charset=utf-8");
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    resp.getOutputStream().write(json.toJson(message).getBytes());
                    return;
                }
                TokenDto getToken = userService.checkToken(token);
                System.out.println(getToken);
                if (userService.checkToken(token) == null) {
                    message.setMessage("Não autorizado!");
                    resp.setHeader("Content-Type", "application/json;charset=utf-8");
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    resp.getOutputStream().write(json.toJson(message).getBytes());
                    return;
                }
            }
            chain.doFilter(request, servletResponse);
        }catch (Exception e){
            message.setMessage("Não autorizado!");
            resp.setHeader("Content-Type", "application/json;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getOutputStream().write(json.toJson(message).getBytes());
            return;
        }
    }
    @Override
    public void destroy() {}
}