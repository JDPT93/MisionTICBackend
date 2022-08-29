package com.jdpt93.cycle3.backend;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    public static final String KEY = "fc3e80241a94ada1560acddc6bde9d90cc1d0ad1423f10bf17f5051ab2cd86d2c4307fc358b6e3608a3ec265041680e772df2bc3473c574354a11d6ca273e850";
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String authenticationHeader = request.getHeader(HEADER);
            if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
                SecurityContextHolder.clearContext();
            } else {
                Claims claims = Jwts.parser()
                        .setSigningKey(KEY.getBytes())
                        .parseClaimsJws(authenticationHeader.substring(PREFIX.length())).getBody();
                if (claims.get("authorities") == null) {
                    SecurityContextHolder.clearContext();
                } else {
                    SecurityContextHolder.getContext()
                            .setAuthentication(new UsernamePasswordAuthenticationToken(
                                    claims.getSubject(),
                                    null,
                                    Stream.of(claims.get("authorities"))
                                            .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                                            .toList()));
                }
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException exception) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
        }
    }

}
