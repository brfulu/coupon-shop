package io.fulu.couponshop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@AdminRoleNeeded
@Priority(Priorities.AUTHENTICATION)
public class AdminRoleNeededFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        try {
            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();
            System.out.println("Token: " + token);
            // Validate the role
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey("SECRET")
                    .parseClaimsJws(token);
            String role = (String)claims.getBody().get("role");
            System.out.println("role: " + role);
            if (!role.equals("ADMIN")) throw new Exception();
        } catch (Exception e) {
            System.out.println("#### invalid role");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
