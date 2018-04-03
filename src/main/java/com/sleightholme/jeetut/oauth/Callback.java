package com.sleightholme.jeetut.oauth;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author jonathan
 */
@WebServlet(name="Callback", urlPatterns={"/Callback"})
public class Callback extends ExtendedServlet {

    private static final String CLIENT_ID="0196c84ee1abd053acb5";
    private static final String CLIENT_SECRET="27a29b8a6ad63ad37feb5fb959b69462cc9448e9";
    
    @Inject
    RequestState state;
    
    @Override
    public void init(){
        title = "Github Callback";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
        header();
        Client jaxrsClient = ClientBuilder.newClient();
        WebTarget target = jaxrsClient.target("https://github.com/login/oauth/access_token?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + 
                request.getParameter("code") + "&state=" + state.getState());
        Response githubResponse = target.request().build("POST").invoke();
        String result = githubResponse.readEntity(String.class);
        if("/SecuredPage".equals(state.getState())){
            state.setState(result);
            response.sendRedirect(root + "/SecuredPage");
            return;
        }
        
        out.println(result);
//        String[] parts = result.split("&");
//        for (String part: parts){
//            out.println(part + "</br>");
//            if (part.contains("state")){
//                String resultState = part.split("=")[1];
//                if (resultState.equals("%2FSecuredPage")){
//                    response.sendRedirect(root + "/SecuredPage");
//                }
//            }
//            
//        }
        
        footer();
    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
