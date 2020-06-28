package br.com.alura.forum.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTopicDto {

    @NotBlank
    private String shortDescription;

    @NotBlank
    @Size(min = 10)
    private String content;

    @NotBlank
    private String courseName;

}
