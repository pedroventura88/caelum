package br.com.alura.forum.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnwserUpdateInput {

    @NotNull
    private boolean solution;
}
