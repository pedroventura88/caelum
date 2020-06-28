package br.com.alura.forum.service;

import br.com.alura.forum.dto.input.CreateTopicDto;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.dto.output.TopicOutputDto;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TopicService {
    private TopicRepository topicRepository;
    private CourseRepository courseRepository;

    public Page<TopicBriefOutputDto> getTopics(TopicSearchDto topicSearchDto, Pageable pageable) {
        return TopicBriefOutputDto.listFromTopics(topicRepository.findAll(topicSearchDto.toSpecification(), pageable));
    }

    public TopicOutputDto createTopic(CreateTopicDto createTopicDto, User loggedUser) {

        Course course = courseRepository
                .findByName(createTopicDto.getCourseName())
                .orElseThrow(() -> new NullPointerException("Curso "+createTopicDto.getCourseName()+" não foi encontrado."));

        Topic topic = new Topic(createTopicDto.getShortDescription(), createTopicDto.getContent(), loggedUser, course);
        return TopicOutputDto.of(topicRepository.save(topic));
    }

    public Page<TopicBriefOutputDto> findAll(TopicSearchDto topicSearchDto, Pageable paginacao) {
        Page<Topic> pagina = topicRepository.findAll(topicSearchDto.toSpecification(), paginacao);
        return TopicBriefOutputDto.listFromTopics(pagina);
    }

    public List<TopicBriefOutputDto> findAll() {
        List<Topic> listTopic = topicRepository.findAll();
        return TopicBriefOutputDto.listFromTopics(listTopic);
    }

    public List<Topic> findbyOwnerAndCreationInstantAfterOrderbyCAndCreationInstantAsc(User loggedUser, Instant oneHourAgo) {
        return topicRepository.findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(loggedUser, oneHourAgo);
    }

    public TopicOutputDto findTopicById(Long id) {
        return TopicOutputDto.of(
                topicRepository.findById(id).orElseThrow(NullPointerException::new));
    }
}
