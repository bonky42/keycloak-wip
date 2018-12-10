package org.bonky.keycloak;

import org.apache.commons.validator.routines.EmailValidator;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.ConsoleDisplayMode;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.KeycloakModelUtils;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;


public class EmailAuthenticator implements Authenticator {
    public void authenticate(AuthenticationFlowContext authenticationFlowContext) {
        Response response = ConsoleDisplayMode.challenge(authenticationFlowContext)
        .header()
        .param("email")
        .label("email")
        .challenge().form().createLogin();

        authenticationFlowContext.challenge(response);
    }

    public void action(AuthenticationFlowContext authenticationFlowContext) {
        MultivaluedMap<String, String> formData = authenticationFlowContext.getHttpRequest().getDecodedFormParameters();
        String email = formData.getFirst("email").trim();
        UserModel user = null;
        try {
            EmailValidator eValidator = EmailValidator.getInstance();
            if (!eValidator.isValid(email)) {
                return;
            }
            user = KeycloakModelUtils.findUserByNameOrEmail(authenticationFlowContext.getSession(), authenticationFlowContext.getRealm(), email);
            if (user != null) {
                authenticationFlowContext.success();
            }
        } catch (ModelDuplicateException mde) {
            return;
        }
    }

    public boolean requiresUser() {
        return false;
    }

    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return false;
    }

    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    public void close() {

    }
}
