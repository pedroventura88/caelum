package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TopicOutputDto {

    private Long id;
    private String shortDescription;
    private long secondsSinceLastUpdate;
    private String ownerName;
    private String courseName;
    private String subcategoryName;
    private String categoryName;
    private int numberOfResponses;
    private boolean solved;
    private TopicStatus topicStatus;
    private List<AnwserOutputDto> anwser;

    public static TopicOutputDto of (Topic topic) {
        return new TopicOutputDto(topic);
    }

    private TopicOutputDto(Topic topic) {
        this.id = topic.getId() == null ? '1' : topic.getId();
        this.shortDescription = topic.getShortDescription();
        this.secondsSinceLastUpdate = getSecondsSince(topic.getLastUpdate());
        this.ownerName = topic.getOwner().getName();
        this.courseName = topic.getCourse().getName();
        this.subcategoryName = topic.getCourse().getSubcategory().getName();
        this.categoryName = topic.getCourse().getCategoryName();
        this.numberOfResponses = topic.getNumberOfAnswers();
        this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
        this.topicStatus = topic.getStatus();
        this.anwser = AnwserOutputDto.ofAnswer(topic.getAnswers());
    }

    private long getSecondsSince(Instant lastUpdate) {
        return Duration.between(lastUpdate, Instant.now())
                .get(ChronoUnit.SECONDS);
    }

    public static List<TopicOutputDto> listFromTopics(List<Topic> topics) {
        return topics.stream()
                .map(TopicOutputDto::new)
                .collect(Collectors.toList());
    }

    public static Page<TopicOutputDto> listFromTopics(Page<Topic> topicPage) {
        return topicPage.map(TopicOutputDto::new);
    }

}
