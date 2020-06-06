package br.com.alura.forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        System.out.println("Bateu no endpoint da Index");
        return "Opa... acessou o endPoint da Index!";
    }
}
