package org.bonky.keycloak;


import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.authentication.DisplayTypeAuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

public class EmailAuthenticatorFactory implements AuthenticatorFactory, DisplayTypeAuthenticatorFactory {
    public static final String PROVIDER_ID = "auth-email-form";

    public String getDisplayType() {
        return "Email Form";
    }

    public String getReferenceCategory() {
        return UserCredentialModel.PASSWORD;
    }

    public boolean isConfigurable() {
        return false;
    }

    public static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED
    };

    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    public boolean isUserSetupAllowed() {
        return false;
    }

    public String getHelpText() {
        return "Validate an email login entry from login form";
    }

    public List<ProviderConfigProperty> getConfigProperties() {
        return null;
    }

    public Authenticator create(KeycloakSession keycloakSession) {
        return new EmailAuthenticator();
    }

    public void init(Config.Scope scope) {

    }

    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    public void close() {

    }

    public String getId() {
        return PROVIDER_ID;
    }

    public int order() {
        return 0;
    }

    public Authenticator createDisplay(KeycloakSession keycloakSession, String s) {
        return new EmailAuthenticator();
    }
}
