package com.example.EcoHack.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.EcoHack.config.configProperties.JWTConfigProperties;
import com.example.EcoHack.exceptions.BaseException;
import com.example.EcoHack.exceptions.UnknownException;
import com.example.EcoHack.exceptions.api.unauthorized.*;
import com.example.EcoHack.exceptions.generic.TokenDecodeException;
import com.example.EcoHack.app.AppUser.AppUser;
import com.example.EcoHack.security.DTO.DetailedTokenDetailsDTO;
import com.example.EcoHack.security.DTO.MultiAuthIdentityProviderDTO;
import com.example.EcoHack.app.AppUser.AppUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
public class AuthTokenController {
    private final AppUserService appUserService;
    @Autowired
    private JwtUtils jwtUtils;
    protected final Log logger = LogFactory.getLog(this.getClass());

    @PostMapping(value = JWTConfigProperties.DEFAULT_REFRESH_TOKEN_ENDPOINT_VALUE)
    public DetailedTokenDetailsDTO refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new InvalidTokenException("Refresh token missing or not in the format 'Bearer <refresh_token>'");
        try {
            // get token from header
            String refresh_token = authorizationHeader.substring("Bearer ".length());

            // try decode token
            DecodedJWT decodedJWT;
            try {
                decodedJWT = jwtUtils.decodeToken(refresh_token);
            } catch (com.auth0.jwt.exceptions.TokenExpiredException exception) {
                throw new TokenExpiredException(exception);
            } catch (TokenDecodeException exception) {
                throw new InvalidTokenException(exception);
            }

            // check TokenType
            try {
                String tokenType = jwtUtils.getTokenType(decodedJWT);
                if (!tokenType.equals(TokenType.refresh_token.toString()))
                    throw new InvalidTokenTypeException(TokenType.refresh_token.toString(), tokenType);
            } catch (Exception e) {
                if (e instanceof InvalidTokenTypeException)
                    throw e;
                throw new InvalidTokenException("Invalid token! Could not read TokenType from token.", e);
            }

            // try get username
            String username;
            try {
                username = jwtUtils.getUsernameFromToken(decodedJWT);
            } catch (Exception e) {
                throw new InvalidTokenException("Invalid token! Could not read username from token.", e);
            }

            AppUser appUser = appUserService.getUser(username).orElseThrow(() -> {
                throw new InvalidTokenUserException(username);
            });
            // check if user is enabled/active and somehow has a token (i.e. the user was deactivated later on)
            if (!appUser.isEnabled())
                throw new UserAccountNotActivatedException();
            User user = appUser.toDomainUser();

            return jwtUtils.generateDetailedTokenPair(user);
        } catch (Exception exception) {
            if (!(exception instanceof BaseException)) // TODO: a cool idea would be to generate a stack trace/log and save it in a resources folder accessible only from admins we can send the user a link that points to this file so they can share it with us
                exception = new UnknownException(exception);
            BaseException baseException = ((BaseException) exception);
            logger.error(baseException);
            throw baseException;
        }
    }

    // this is redundant, added for swagger to build the docs
    @PostMapping(value = JWTConfigProperties.DEFAULT_ACCESS_TOKEN_ENDPOINT_VALUE)
    public DetailedTokenDetailsDTO authenticate(@RequestBody MultiAuthIdentityProviderDTO multiAuthIdentityProviderDTO) {
        return new DetailedTokenDetailsDTO();
    }
}
