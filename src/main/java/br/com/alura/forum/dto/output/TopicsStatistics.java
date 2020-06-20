package br.com.alura.forum.dto.output;


public class TopicsStatistics {
    private Long id;
    private String name;
    private Long quantidade;

    public TopicsStatistics(Long id, String name, Long quantidade) {
        this.id = id;
        this.name = name;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
