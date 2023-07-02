package com.example.EcoHack.security.thirdPartyLogin;

import com.example.EcoHack.exceptions.api.ResourceNotFoundException;
import com.example.EcoHack.exceptions.api.unauthorized.InvalidCredentialsException;
import com.example.EcoHack.exceptions.api.unauthorized.UserAccountNotActivatedException;
import com.example.EcoHack.exceptions.to_refactor.InvalidValueException;
import com.example.EcoHack.app.AppUser.AppUser;
import com.example.EcoHack.security.DTO.MultiAuthIdentityProviderDTO;
import com.example.EcoHack.app.AppUser.AppUserService;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class MultiAuthIdentityProvider {
    @Getter
    private static final String ThirdPartyAccountPassword = "TODO";

    @Autowired
    GoogleIdentity googleIdentity;
    @Autowired
    FacebookIdentity facebookIdentity;

    public UsernamePasswordAuthenticationToken getAuthenticationTokenFromAuthDetails(MultiAuthIdentityProviderDTO authDetails, AppUserService appUserService) {
        AppUser appUser = null;
        String password = getThirdPartyAccountPassword();
        if (authDetails.getAuthType() == AuthType.BASIC) {
            if (authDetails.getUsername() == null || authDetails.getUsername().trim().length() == 0) {
                throw new InvalidValueException("Username cannot be null or empty when AuthType=BASIC!");
            }
            if (authDetails.getPassword() == null || authDetails.getPassword().trim().length() == 0) {
                throw new InvalidValueException("Username cannot be null or empty when AuthType=BASIC!");
            }
            appUser = appUserService.getUser(authDetails.getUsername()).orElseThrow(() -> {
                throw new InvalidCredentialsException(new ResourceNotFoundException("User", "username", authDetails.getUsername()));
            });
            if (!appUser.getUserAuthType().equals(AuthType.BASIC)) { // this is achieved only if someone tries to access a non BASIC account, the condition prevents accessing the third party provider accounts that have a common password
                throw new InvalidCredentialsException(new ResourceNotFoundException("User", "username", authDetails.getUsername()));
            }
            password = authDetails.getPassword();
        } else if (authDetails.getAuthType() == AuthType.GOOGLE) {
            appUser = appUserService.getOrRegisterUserInternally(googleIdentity.getAppUserFromToken(authDetails.getToken()));
        } else if (authDetails.getAuthType() == AuthType.INSTAGRAM) {
            throw new NotImplementedException("Instagram login is not implemented yet!");
//            appUser = appUserService.getOrRegisterUserInternally(facebookIdentity.getAppUserFromToken(authDetails.getToken()));
        } else if (authDetails.getAuthType() == AuthType.FACEBOOK) {
            appUser = appUserService.getOrRegisterUserInternally(facebookIdentity.getAppUserFromToken(authDetails.getToken()));
        }

        if (appUser == null) {
            throw new InvalidCredentialsException("Could not resolve user from provided authentication details.");
        }
        if (!appUser.isEnabled())
            throw new UserAccountNotActivatedException();
        return new UsernamePasswordAuthenticationToken(appUser.getUsername(), password);
    }
}
