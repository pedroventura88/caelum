package br.com.alura.forum.controller;

import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TopicController {

    //    @Autowired
    private TopicRepository topicRepository;

    //Boas Práticas, de acordo com o "Java Efetivo", é injetar dependencias via construtor
    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping(value = "/api/search")
    public List<TopicBriefOutputDto> listaPorStatusCategoria(TopicSearchDto topicSearchDto) {
        return TopicBriefOutputDto.listFromTopics(
                topicRepository.findAll(topicSearchDto.toSpecification()));
    }

    @GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> listaTopicos() {
        List<Topic> listTopic = topicRepository.findAll();
        return TopicBriefOutputDto.listFromTopics(listTopic);
    }

//    @GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<TopicBriefOutputDto> listTopics() {
//        Category subCategory = new Category("Java", new Category("Programação"));
//        Course course = new Course("Curso Spring", subCategory);
//        Topic topic = new Topic("Descrição curso spring",
//                "Conteudo do curso spring",
//                new User("Pedro", "pedro@gmail.com", "senha123"), course);
//        List<Topic> listTopics = new ArrayList<>();
//        listTopics.add(topic);
//
//        return TopicBriefOutputDto.listFromTopics(listTopics);
//    }
}
