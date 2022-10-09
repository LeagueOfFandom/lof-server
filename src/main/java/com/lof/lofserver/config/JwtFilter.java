package com.lof.lofserver.config;


import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.filter.JsonWebToken;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JsonWebToken jsonWebToken;
    private final UserRepository userRepository;

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
            Long id = Long.parseLong(jsonWebToken.getClaimsByToken(token).get("id").toString());
            //token 에서 가져온 id 가 db 에 존재하는지 확인
            if(userRepository.findById(id).isPresent()){
                request.setAttribute("id", id);
                filterChain.doFilter(request, response);
            }
            else response.sendError(401, "Unauthorized - user not found");
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
