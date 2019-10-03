package com.sleightholme.jeetut.jaxws;

import com.sleightholme.jeetut.util.ExtendedServlet;
import com.strikeiron.GlobalAddressVerification;
import java.io.IOException;;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "ClientTest", urlPatterns = {"/ClientTest"})
public class ClientTest extends ExtendedServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GlobalAddressVerification addressVerifier = new GlobalAddressVerification();
        out.println(addressVerifier.getGlobalAddressVerificationSoap().toString() + "<p>");
        out.println(addressVerifier.getWSDLDocumentLocation());
    }

}
