package br.com.alura.forum.controller;

import br.com.alura.forum.dto.output.DashboardDto;
import br.com.alura.forum.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DashboardController {

    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping(value = "/api/dashboard")
    public List<DashboardDto> getDashboard() {
        return dashboardService.dashboardLista();
    }
}
