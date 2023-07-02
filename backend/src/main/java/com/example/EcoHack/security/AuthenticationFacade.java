package com.example.EcoHack.security;

import com.example.EcoHack.app.AppUser.AppUserService;
import com.example.EcoHack.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public static final SimpleGrantedAuthority ADMIN_AUTHORITY = new SimpleGrantedAuthority("ROLE_ADMIN");
    public static final SimpleGrantedAuthority USER_AUTHORITY = new SimpleGrantedAuthority("ROLE_USER");
    public static final SimpleGrantedAuthority FOOD_PROVIDER_AUTHORITY = new SimpleGrantedAuthority("ROLE_FOOD_PROVIDER");

    public static Authentication getCurrentSecurityContextAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean isAdminOnCurrentSecurityContext() {
        return Utilities.notNullAndContains(getCurrentSecurityContextAuthentication().getAuthorities(), ADMIN_AUTHORITY);
    }

    public static boolean isAdminOrCurrentUserOnCurrentSecurityContext(String username) {
        Authentication authentication = getCurrentSecurityContextAuthentication();
        return Utilities.notNullAndContains(authentication.getAuthorities(), ADMIN_AUTHORITY) || ((Utilities.notNullAndContains(authentication.getAuthorities(), USER_AUTHORITY) || Utilities.notNullAndContains(authentication.getAuthorities(), FOOD_PROVIDER_AUTHORITY)) && authentication.getPrincipal().equals(username));
    }

    public static boolean hasAuthority(SimpleGrantedAuthority authority) {
        return getCurrentSecurityContextAuthentication().getAuthorities().contains(authority);
    }

    @Autowired
    public static boolean isAdminAuthorization(String authorizationHeader, JwtUtils jwtUtils, AppUserService appUserService) {
        try {
            return Utilities.notNullAndContains(jwtUtils.getTokenDetailsFromAuthorizationHeader(authorizationHeader, appUserService).getAuthorities(), ADMIN_AUTHORITY);
        }catch (Exception ex){
            return false;
        }
    }
}
