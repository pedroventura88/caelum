package br.com.alura.forum.controller;

import br.com.alura.forum.annotation.ApiPageable;
import br.com.alura.forum.dto.input.CreateTopicDto;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.dto.output.TopicOutputDto;
import br.com.alura.forum.dto.output.ValidationErrorOutputDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.service.TopicService;
import br.com.alura.forum.validation.TopicSpamValidation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@RestController
public class TopicController {

    private TopicService topicService;

    //Boas Práticas, de acordo com o "Java Efetivo", é injetar dependencias via construtor
    @GetMapping(value = "/api/search")
    @ApiPageable
    public Page<TopicBriefOutputDto> listaPorStatusCategoria(TopicSearchDto topicSearchDto,@ApiIgnore Pageable paginacao) {
        return topicService.findAll(topicSearchDto, paginacao);
    }

    @GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> listaTopicos() {
        return topicService.findAll();
    }

    @GetMapping(value = "/api/topics/{id}")
    public TopicOutputDto buscarPorId (@PathVariable("id") Long id) {
        return topicService.findTopicById(id);
    }

    @PostMapping(value = "/api/topics")
    @ResponseStatus(HttpStatus.CREATED)
    public TopicOutputDto createTopic(@Valid @RequestBody CreateTopicDto createTopicDto, @AuthenticationPrincipal User loggedUser) {
        return topicService.createTopic(createTopicDto, loggedUser);
    }

    @InitBinder("createTopicDto")
    public void initBinder(WebDataBinder binder, @AuthenticationPrincipal User loggedUser) {
        binder.addValidators(new TopicSpamValidation(topicService, loggedUser));
    }

}
