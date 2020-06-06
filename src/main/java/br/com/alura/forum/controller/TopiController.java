package br.com.alura.forum.controller;

import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TopiController {

    @GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> listTopics() {
        Category subCategory = new Category("Java", new Category("Programação"));
        Course course = new Course("Curso Spring", subCategory);
        Topic topic = new Topic("Descrição curso spring",
                "Conteudo do curso spring",
                new User("Pedro", "pedro@gmail.com", "senha123"), course);
        List<Topic> listTopics = new ArrayList<>();
        listTopics.add(topic);

        return TopicBriefOutputDto.listFromTopics(listTopics);
    }
}
