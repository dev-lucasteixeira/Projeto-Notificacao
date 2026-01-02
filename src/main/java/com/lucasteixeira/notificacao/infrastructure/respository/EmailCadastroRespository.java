package com.lucasteixeira.notificacao.infrastructure.respository;

import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailCadastroRespository extends JpaRepository<EmailCadastroEntity, Long> {
}
