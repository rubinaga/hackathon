package com.example.EcoHack.config.security.filters;

import com.example.EcoHack.exceptions.BaseException;
import com.example.EcoHack.exceptions.UnknownException;
import com.example.EcoHack.exceptions.api.badRequest.BadRequestBodyException;
import com.example.EcoHack.exceptions.api.httpCore.HttpRequestMethodNotSupportedException;
import com.example.EcoHack.exceptions.api.unauthorized.InvalidCredentialsException;
import com.example.EcoHack.security.DTO.DetailedTokenDetailsDTO;
import com.example.EcoHack.security.DTO.MultiAuthIdentityProviderDTO;
import com.example.EcoHack.security.JwtUtils;
import com.example.EcoHack.security.thirdPartyLogin.MultiAuthIdentityProvider;
import com.example.EcoHack.app.AppUser.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final MultiAuthIdentityProvider multiAuthIdentityProvider;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils, AppUserService appUserService, MultiAuthIdentityProvider multiAuthIdentityProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.appUserService = appUserService;
        this.multiAuthIdentityProvider = multiAuthIdentityProvider;
        this.setFilterProcessesUrl(jwtUtils.getAccessTokenEndpoint()); // set authentication endpoint
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            HttpMethod method = HttpMethod.valueOf(request.getMethod());
            Set<HttpMethod> supportedMethods = Set.of(HttpMethod.POST);
            if (!supportedMethods.contains(method))
                throw new HttpRequestMethodNotSupportedException(method, request.getServletPath(), supportedMethods);

            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper objectMapper = new ObjectMapper();
            MultiAuthIdentityProviderDTO authDetails;
            try {
                authDetails = objectMapper.readValue(requestBody, MultiAuthIdentityProviderDTO.class);
            }catch (Exception exception){
                throw new BadRequestBodyException();
            }

            return authenticationManager.authenticate(multiAuthIdentityProvider.getAuthenticationTokenFromAuthDetails(authDetails, appUserService));
        } catch (Exception exception) {
            if (exception instanceof BadCredentialsException)
                exception = new InvalidCredentialsException();
            if (!(exception instanceof BaseException))
                exception = new UnknownException(exception);

            BaseException baseException = ((BaseException) exception);
            response.setStatus(baseException.getHttpStatusCode());
            response.setContentType(APPLICATION_JSON_VALUE);
            logger.error(baseException);
            try {
                new ObjectMapper().writeValue(response.getOutputStream(), baseException.toErrorResponse());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        response.setContentType(APPLICATION_JSON_VALUE);
        DetailedTokenDetailsDTO detailedTokenPair = jwtUtils.generateDetailedTokenPair(user);
        new ObjectMapper().writeValue(response.getOutputStream(), detailedTokenPair);
    }
}