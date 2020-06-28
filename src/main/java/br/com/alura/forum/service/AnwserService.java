package br.com.alura.forum.service;

import br.com.alura.forum.dto.input.AnwserInputDto;
import br.com.alura.forum.dto.input.AnwserUpdateInput;
import br.com.alura.forum.dto.output.AnwserOutputDto;
import br.com.alura.forum.dto.output.AnwserUpdateOutputDto;
import br.com.alura.forum.exception.InvalidOperationException;
import br.com.alura.forum.exception.ResourceNotFoundException;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.AnwserRepository;
import br.com.alura.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;

@Service
@AllArgsConstructor
public class AnwserService {

    private AnwserRepository anwserRepository;
    private TopicRepository topicRepository;

    public AnwserOutputDto criarResposta (AnwserInputDto respostaEntrada, Long topicId, User usuarioLogado) {

        Answer answer = new Answer(respostaEntrada.getContent(),
                topicRepository.findById(topicId).orElseThrow(NullPointerException::new), usuarioLogado);

        return new AnwserOutputDto(anwserRepository.save(answer));

    }


    //Utilizar posteriormente o ResponseEntity
    public AnwserUpdateOutputDto atualizarSolucao(AnwserUpdateInput solucao, Long respostaId, User usuarioLogado) {

        Answer answer = anwserRepository.findById(respostaId).orElseThrow(ResourceNotFoundException::new);

        if (answer.getTopic().getOwner().getEmail().equals(usuarioLogado.getEmail())) {
            answer.setSolution(solucao.isSolution());
            return AnwserUpdateOutputDto.of(anwserRepository.save(answer));
        } else {
            throw new InvalidOperationException("Usuário não é o dono do tópico");
        }

    }

}
