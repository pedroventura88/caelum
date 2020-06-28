package br.com.alura.forum.controller;

import br.com.alura.forum.dto.input.AnwserInputDto;
import br.com.alura.forum.dto.input.AnwserUpdateInput;
import br.com.alura.forum.dto.output.AnwserOutputDto;
import br.com.alura.forum.dto.output.AnwserUpdateOutputDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.service.AnwserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class AnwserController {

    private AnwserService anwserService;

    @PostMapping(value = "/api/topics/{topicId}/anwsers")
    public AnwserOutputDto createAnswer(@PathVariable Long topicId,
                                          @Valid @RequestBody AnwserInputDto anwserInputDto,
                                          @AuthenticationPrincipal User usuarioLogado) {

        return anwserService.criarResposta(anwserInputDto, topicId, usuarioLogado);

    }

    @PutMapping(value = "/api/topics/anwsers/update/{respostaId}")
    @ResponseStatus(HttpStatus.OK)
    public AnwserUpdateOutputDto validateAnswer(@PathVariable Long respostaId,
                                                  @Valid @RequestBody AnwserUpdateInput anwserUpdateInput,
                                                  @AuthenticationPrincipal User usuarioLogado) {
        return anwserService.atualizarSolucao(anwserUpdateInput, respostaId, usuarioLogado);
    }

}
