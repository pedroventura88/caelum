package br.com.alura.forum.controller;

import br.com.alura.forum.dto.input.LoginDto;
import br.com.alura.forum.dto.output.AuthenticationTokenOutputDto;
import br.com.alura.forum.security.jwt.TokenManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationTokenOutputDto> autenticate(@RequestBody LoginDto loginDto) {
        /** Isso daqui viraria o JSON */
        try {
            Authentication auth = authenticationManager.authenticate(loginDto.token());
            String jwt = tokenManager.generateToken(auth);
            /** Padrão de autenticação tipo de token. Concatena o Bearer com o jwt  */
            return ResponseEntity.ok(new AuthenticationTokenOutputDto("Bearer", jwt));
        } catch (AuthenticationException authenticationException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
