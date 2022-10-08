package com.lof.lofserver.config;


import com.lof.lofserver.filter.JsonWebToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/v1/*")
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JsonWebToken jsonWebToken;

    public JwtFilter(JsonWebToken jsonWebToken){
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getServletPath();

        //user 생성시에는 filter 를 적용하지 않는다.
        if(url.equals("/v1/user/create")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
        //token 이 없을 경우
        if(token == null){
            response.sendError(401, "Unauthorized");
            return;
        }
        //token 이 test 일 경우
        if(token.equals("test")){
            request.setAttribute("id", 1L);
            filterChain.doFilter(request, response);
            return;
        }
        //token 이 정상일 경우
        if(jsonWebToken.checkJwtToken(token).equals("valid")) {
            request.setAttribute("id", jsonWebToken.getClaimsByToken(token).get("id"));
            filterChain.doFilter(request, response);
        }
        //token 이 만료일 경우
        else if(jsonWebToken.checkJwtToken(token).equals("expired")){
            response.sendError(401, "Token Expired");
        }
        else {
            response.sendError(401, "Unauthorized");
        }
    }
}
