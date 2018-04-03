package com.sleightholme.jeetut.security;

import java.util.logging.Logger;
import java.util.HashSet;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.RememberMe;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
// with defaults lasts for 1 day
//@ApplicationScoped
//@RememberMe
//@AutoApplySession
public class CheckAuth implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;
    
    private HashSet<String> roles;
    private static final String mainRole = "users.normal";
    
    private Logger logger = Logger.getLogger(CheckAuth.class.getName());

    public CheckAuth() {
        roles = new HashSet<String>();
        roles.add(mainRole);
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        logger.log(Level.FINE, "Validating request...");
        if (request.getParameter("login") != null) {
            logger.log(Level.FINER, "Perfoming login validation");
            UsernamePasswordCredential cred = new UsernamePasswordCredential(request.getParameter("username"), request.getParameter("password"));
            logger.log(Level.FINER, "Created credential");
            CredentialValidationResult result = identityStoreHandler.validate(cred);
            logger.log(Level.FINER, "Validated credential");
            if (result.getStatus() == Status.VALID){
                logger.log(Level.FINER, "Registering session");
                httpMessageContext.setRegisterSession(cred.getCaller(), result.getCallerGroups());
                logger.log(Level.INFO, "Successfully logged in");
                return httpMessageContext.notifyContainerAboutLogin(result);
            } else {
                logger.log(Level.INFO, "Login failed");
                return httpMessageContext.responseUnauthorized();
            }
        } else {
            if (request.isRequestedSessionIdValid() && request.isRequestedSessionIdFromCookie()) {
                if (request.isUserInRole(mainRole)) {
                    logger.log(Level.INFO, "User already logged in");                
                    return httpMessageContext.doNothing();
                } else {
                    logger.log(Level.INFO, "User already logged in but does not have permission.");
                    return httpMessageContext.responseUnauthorized();
                }
            } else {
                try {
                    request.logout();
                    logger.log(Level.FINE, "Logging out");
                } catch (ServletException ex) {
                    Logger.getLogger("sleightholme.security").log(Level.FINE, "Error logging out", ex);
                }
                logger.log(Level.FINER, "Doing nothing with security");
                return httpMessageContext.doNothing();
            }
        }
    }

    /*@Override
    public AuthenticationStatus secureResponse(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        return HttpAuthenticationMechanism.super.secureResponse(request, response, httpMessageContext); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {
        HttpAuthenticationMechanism.super.cleanSubject(request, response, httpMessageContext); //To change body of generated methods, choose Tools | Templates.
    }*/

}
