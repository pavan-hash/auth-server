package com.pk.auth_server.Controller;

import com.pk.auth_server.Dto.LoginRequest;
import com.pk.auth_server.Model.AppUser;
import com.pk.auth_server.Repo.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private AppUserRepository appUserrepo;

   AuthenticationManager authManager;

   public LoginController(AuthenticationManager authManager) {
       this.authManager = authManager;
   }

    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@Validated @RequestBody LoginRequest request)
    {
        try {
            Authentication user = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            return new ResponseEntity<>(HttpStatus.OK);
        }

        catch(AuthenticationException ex)
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
