package br.com.alura.forum.service;

import br.com.alura.forum.dto.output.DashboardDto;
import br.com.alura.forum.dto.output.TopicsStatistics;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.repository.CategoryRepository;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    private CategoryRepository categoryRepository;
    private TopicRepository topicRepository;

    public DashboardService(CategoryRepository categoryRepository, TopicRepository topicRepository) {
        this.categoryRepository = categoryRepository;
        this.topicRepository = topicRepository;
    }

    public List<DashboardDto> dashboardLista() {
        List<DashboardDto> categoriasDto = new ArrayList<>();
        List<Category> categorias = categoryRepository.findByCategoryIsNull();
        List<TopicsStatistics> countTopicsByCategory = topicRepository.findCountTopicsGroupByCategory();
        List<TopicsStatistics> contTopicsFromLastWeek = topicRepository.findTopicsFromLastWeek(Instant.now().minus(Duration.ofDays(7)));
        List<TopicsStatistics> countTopicsNotAnsweredByCategory = topicRepository.findCountTopicsGroupByCategoryAndNotAnswered();

        categorias.forEach(categoria -> {
            Long allTopics = getStatisticByCategory(countTopicsByCategory, categoria);
            Long notAnsweredTopics = getStatisticByCategory(countTopicsNotAnsweredByCategory, categoria);
            Long lastWeekTopics = getStatisticByCategory(contTopicsFromLastWeek, categoria);
            DashboardDto dashboardDto = new DashboardDto(categoria, allTopics, lastWeekTopics, notAnsweredTopics);
            categoriasDto.add(dashboardDto);
        });

        return categoriasDto;
    }

    private Long getStatisticByCategory(List<TopicsStatistics> countTopicsByCategory, Category categoria) {
        return countTopicsByCategory
                .stream()
                .filter(topicsStatistics -> topicsStatistics.getId().equals(categoria.getId()))
                .map(TopicsStatistics::getQuantidade)
                .findAny()
                .orElse(0L);
    }

}
