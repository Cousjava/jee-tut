package com.sleightholme.jeetut.security;

import java.util.HashMap;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author jonathan coustick
 */
//@DatabaseIdentityStoreDefinition(callerQuery="SELECT password FROM users WHERE username = ?")
@ApplicationScoped
public class SecurityStore implements IdentityStore {

    private HashMap<String, String> users;
    private HashSet<String> roles;

    public SecurityStore() {
        users = new HashMap<String, String>();
        roles = new HashSet<String>();
        users.put("admin", "password");
        roles.add("users.normal");
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential cred = (UsernamePasswordCredential) credential;
            String pass = users.get(cred.getCaller());
            if (pass != null && pass.equals(cred.getPasswordAsString())) {
                return new CredentialValidationResult(cred.getCaller(), roles);
            }

        } else if (credential instanceof RememberMeCredential) {
            RememberMeCredential cred = (RememberMeCredential) credential;
            return new CredentialValidationResult(cred.getToken(), roles);
        }
        return CredentialValidationResult.INVALID_RESULT;

    }

}
