package com.example.practiceoauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public ResponseEntity login(HttpServletResponse response) {
        String githubAuthTokenUri = authService.createGithubAuthTokenUri();
        response.setHeader("Location", githubAuthTokenUri);

        return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/auth/githublogin")
    public ResponseEntity<String> login(@PathParam("code") String code, HttpServletResponse response) {
        response.setHeader("Authorization", authService.getAccessToken(code));

        return new ResponseEntity("Authorized", HttpStatus.OK);
    }
}
