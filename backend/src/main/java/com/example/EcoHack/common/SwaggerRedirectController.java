package com.example.EcoHack.common;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class SwaggerRedirectController {
    @RequestMapping(path = "api")
    public String redirectToDocumentation(HttpServletResponse httpResponse) throws Exception {
        httpResponse.sendRedirect("/api-docs/swagger-ui/index.html#/"); // TODO get paths from application.yml
        return null;
    }
}
