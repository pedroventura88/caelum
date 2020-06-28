package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.Answer;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AnwserOutputDto {

    private Long id;
    private String content;
    private Instant creationTime;
    private Boolean solution;
    private String ownerName;

    public AnwserOutputDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.creationTime = answer.getCreationTime();
        this.solution = answer.isSolution();
        this.ownerName = answer.getOwnerName();
    }

    public static List<AnwserOutputDto> ofAnswer (List<Answer> answers) {
        return answers
                .stream()
                .map(AnwserOutputDto::new)
                .collect(Collectors.toList());
    }
}
