package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.Answer;
import lombok.Data;

@Data
public class AnwserUpdateOutputDto {

    private Long id;
    private Long idTopico;
    private boolean solucaoValida;
    private String usuarioDono;

    public static AnwserUpdateOutputDto of (Answer answer){
        return new AnwserUpdateOutputDto(answer);
    }

    private AnwserUpdateOutputDto(Answer answer) {
        this.id = answer.getId();
        this.idTopico = answer.getTopic().getId();
        this.solucaoValida = answer.isSolution();
        this.usuarioDono = answer.getOwner().getName();
    }

}
