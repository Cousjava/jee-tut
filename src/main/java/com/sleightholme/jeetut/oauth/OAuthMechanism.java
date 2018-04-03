package com.sleightholme.jeetut.oauth;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@ApplicationScoped
@AutoApplySession
public class OAuthMechanism implements HttpAuthenticationMechanism {

    private static final String CLIENT_ID = "0196c84ee1abd053acb5";
    private static final String CLIENT_SECRET = "27a29b8a6ad63ad37feb5fb959b69462cc9448e9";

    private static final Logger LOGGER = Logger.getLogger("jeetut-oauth");

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    RequestState state;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        if ("/SecuredPage".equals(request.getServletPath()) && !request.isRequestedSessionIdValid()) {
            state.setState("/SecuredPage");
            LOGGER.log(Level.FINE, "github oauth started");
            return httpMessageContext.redirect("https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&state=" + state.getState());
        } else if ("/Callback".equals(request.getServletPath())) {
            if (request.getParameter("code") != null) {
                Credential cred = new RememberMeCredential(request.getParameter("code"));
                CredentialValidationResult result = identityStoreHandler.validate(cred);
                if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                    LOGGER.log(Level.FINE, "Successfully logged in");
                    return httpMessageContext.notifyContainerAboutLogin(result);
                } else {
                    LOGGER.log(Level.FINE, "Login failed");
                    return httpMessageContext.responseUnauthorized();
                }
            }

        }
        LOGGER.log(Level.FINER, "not secured - {0}", request.getServletPath());
        return AuthenticationStatus.NOT_DONE;
    }

}

