package com.lucasteixeira.notificacao.infrastructure.respository;

import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroUsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCadastroUsuariosRespository extends JpaRepository<EmailCadastroUsuariosEntity, Long> {
}
