package com.lof.lofserver.config;


import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.filter.JsonWebToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JsonWebToken jsonWebToken;
    private final UserRepository userRepository;


    private final List<String> excludeUrlPatterns = new ArrayList<>(Arrays.asList("/swagger-ui.html",
            "/swagger-uui.html", "/webjars/springfox-swagger-ui/springfox.css",
            "/webjars/springfox-swagger-ui/swagger-ui-bundle.js", "/webjars/springfox-swagger-ui/swagger-ui.css",
            "/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
            "/webjars/springfox-swagger-ui/springfox.js", "/swagger-resources/configuration/ui",
            "/webjars/springfox-swagger-ui/favicon-32x32.png", "/swagger-resources/configuration/security",
            "/swagger-resources", "/v2/api-docs",
            "/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff2",
            "/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff2",
            "/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff2",
            "/webjars/springfox-swagger-ui/favicon-16x16.png",
            "/v1/users", //user create
            "/v1/users/temp", //user create,
            "/userList",
            "/sendFcm",
            "/",
            "/v1/users/new"
             ));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        //token ??? ?????? ??????
        if(token == null){
            response.sendError(401, "Unauthorized");
            return;
        }
        //token ??? test ??? ??????
        if(token.equals("test")){
            request.setAttribute("id", 1L);
            filterChain.doFilter(request, response);
            return;
        }
        //token ??? ????????? ??????
        if(jsonWebToken.checkJwtToken(token).equals("valid")) {
            Long id = Long.parseLong(jsonWebToken.getClaimsByToken(token).get("id").toString());
            //token ?????? ????????? id ??? db ??? ??????????????? ??????
            if(userRepository.findById(id).isPresent()){
                request.setAttribute("id", id);
                filterChain.doFilter(request, response);
            }
            else response.sendError(401, "Unauthorized - user not found");
        }
        //token ??? ????????? ??????
        else if(jsonWebToken.checkJwtToken(token).equals("expired")){
            response.sendError(401, "Token Expired");
        }
        else {
            response.sendError(401, "Unauthorized");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return excludeUrlPatterns.contains(path);
    }
}
