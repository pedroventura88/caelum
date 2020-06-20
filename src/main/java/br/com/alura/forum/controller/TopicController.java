package br.com.alura.forum.controller;

import br.com.alura.forum.annotation.ApiPageable;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TopicController {

    private TopicRepository topicRepository;

    //Boas Práticas, de acordo com o "Java Efetivo", é injetar dependencias via construtor
    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping(value = "/api/search")
    @ApiPageable
    public Page<TopicBriefOutputDto> listaPorStatusCategoria(TopicSearchDto topicSearchDto,@ApiIgnore Pageable paginacao) {

        Page<Topic> pagina = topicRepository.findAll(topicSearchDto.toSpecification(), paginacao);
        return TopicBriefOutputDto.listFromTopics(pagina);
    }

    @GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> listaTopicos() {
        List<Topic> listTopic = topicRepository.findAll();
        return TopicBriefOutputDto.listFromTopics(listTopic);
    }


}
