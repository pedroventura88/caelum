package br.com.alura.forum.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageErrorOutputDto {
    private String message;
    private int code;
}
