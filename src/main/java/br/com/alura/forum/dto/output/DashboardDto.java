package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.Category;

import java.util.List;

public class DashboardDto {
    private String categoryName;
    private List<String> subcategories;
    private Long allTopics;
    private Long lastWeekTopics;
    private Long unansweredTopics;

    public DashboardDto(Category category, Long allTopics, Long lastWeekTopics, Long unansweredTopics) {
        this.categoryName = category.getName();
        this.subcategories = category.getSubcategoryNames();
        this.allTopics = allTopics;
        this.lastWeekTopics = lastWeekTopics;
        this.unansweredTopics = unansweredTopics;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<String> subcategories) {
        this.subcategories = subcategories;
    }

    public Long getAllTopics() {
        return allTopics;
    }

    public void setAllTopics(Long allTopics) {
        this.allTopics = allTopics;
    }

    public Long getLastWeekTopics() {
        return lastWeekTopics;
    }

    public void setLastWeekTopics(Long lastWeekTopics) {
        this.lastWeekTopics = lastWeekTopics;
    }

    public Long getUnansweredTopics() {
        return unansweredTopics;
    }

    public void setUnansweredTopics(Long unansweredTopics) {
        this.unansweredTopics = unansweredTopics;
    }
}
