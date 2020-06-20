package br.com.alura.forum.dto.input;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginDto {

    private String email;
    private String password;

    /** Metodo que responsável que transforma o metodo interpretável pelo framework JWT */
    public UsernamePasswordAuthenticationToken token() {
        return new UsernamePasswordAuthenticationToken(email,password);
    }
}
