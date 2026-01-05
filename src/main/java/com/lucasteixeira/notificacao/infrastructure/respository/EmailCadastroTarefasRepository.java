package com.lucasteixeira.notificacao.infrastructure.respository;

import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroTarefasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCadastroTarefasRepository extends JpaRepository<EmailCadastroTarefasEntity, Long> {
}
