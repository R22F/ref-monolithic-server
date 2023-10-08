package com.example.refmonolithicserver.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.refmonolithicserver.common.component.AppProperties;
import com.example.refmonolithicserver.configuration.principalDetaills.PrincipalDetails;
import com.example.refmonolithicserver.user.dto.UserDto.UserSignInRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;
    private final AppProperties.Jwt jwt;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AppProperties.Jwt jwt) {
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
        setFilterProcessesUrl("/signin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();
        UserSignInRequestDto loginRequestDto = null;
        try {
            loginRequestDto = om.readValue(request.getInputStream(), UserSignInRequestDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert loginRequestDto != null;
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword());
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult){

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String accessToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwt.getACCESS_EXPIRATION_TIME()))
                .withClaim("id", principalDetails.getUser().getId().toString())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(jwt.getACCESS_SECRET()));

        response.addHeader(jwt.getACCESS_TOKEN_PREFIX(), jwt.getHEADER_STRING() + " " + accessToken);
    }
}
