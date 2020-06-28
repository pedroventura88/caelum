package br.com.alura.forum.dto.output;


import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class FieldOutputErrorDto {

    private String field;
    private String message;

}
