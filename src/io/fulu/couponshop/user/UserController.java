package io.fulu.couponshop.user;

import io.fulu.couponshop.security.AdminRoleNeeded;
import io.fulu.couponshop.security.JWTTokenNeeded;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("/users")
public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @GET
    @JWTTokenNeeded
    @AdminRoleNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        user = userService.login(user);
        if (user != null) {
            // Issue a token for the user
            String token = issueToken(user);
            // Return the token on the response
            return Response.ok().entity(user).header(AUTHORIZATION, "Bearer " + token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(false).build();
        }
    }

    @POST
    @JWTTokenNeeded
    @AdminRoleNeeded
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User register(User user) {
        return userService.register(user);
    }

    private String issueToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().toString());
        claims.put("user", user.getUsername());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());

        String jwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuer("localhost")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, "SECRET")
                .compact();
        return jwtToken;
    }
}
