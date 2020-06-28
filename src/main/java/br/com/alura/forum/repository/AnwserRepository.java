package br.com.alura.forum.repository;


import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnwserRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

    Answer getAnswerByIdAndOwner (Long id, User usuarioLogado );
}
