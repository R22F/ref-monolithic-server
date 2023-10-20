package com.example.refmonolithicserver.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.refmonolithicserver.common.component.AppProperties.Jwt;
import com.example.refmonolithicserver.common.exception.ErrorResponse;
import com.example.refmonolithicserver.configuration.principalDetaills.PrincipalDetails;
import com.example.refmonolithicserver.user.dao.UserRepository;
import com.example.refmonolithicserver.user.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private final UserRepository userRepository;
    private final Jwt jwt;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, Jwt jwt) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwt = jwt;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(jwt.getACCESS_TOKEN_PREFIX());
        if(header == null || !header.startsWith(jwt.getHEADER_STRING())) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(jwt.getACCESS_TOKEN_PREFIX())
                .replace(jwt.getHEADER_STRING()+" ", "");
        try {
            String username = JWT.require(Algorithm
                            .HMAC512(jwt.getACCESS_SECRET()))
                    .build()
                    .verify(token)
                    .getClaim("username").asString();

            if (username != null) {
                Optional<User> user = userRepository.findByUsername(username);
                if (user.isEmpty())
                    throw new TokenExpiredException("");
                PrincipalDetails principalDetails = new PrincipalDetails(user.get());
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (TokenExpiredException e){
            log.error("Token expired");
            setResponse(response, e, HttpServletResponse.SC_EXPECTATION_FAILED, "JWT : Token expired");
            return;

        }catch (SignatureVerificationException e){
            log.error("Signature not valid");
            setResponse(response, e, HttpServletResponse.SC_UNAUTHORIZED, "JWT : Signature not valid");
            return;

        }catch (UsernameNotFoundException e){
            log.error("User not found");
            setResponse(response, e, HttpServletResponse.SC_FORBIDDEN, "JWT : User not found");
            return;

        }catch (Exception e){
            log.error("exception");
            setResponse(response, e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "JWT : Undefined exception");
            return;
        }
        chain.doFilter(request, response);
    }

    private void setResponse(HttpServletResponse response, Exception exception, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(
                new ErrorResponse(status, message, exception.getMessage())));
    }
}
