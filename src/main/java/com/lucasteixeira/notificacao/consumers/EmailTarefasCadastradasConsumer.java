package com.lucasteixeira.notificacao.consumers;

import com.lucasteixeira.notificacao.business.dto.EmailRecordDTO;
import com.lucasteixeira.notificacao.business.services.EmailCadastroTarefasService;
import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroTarefasEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailTarefasCadastradasConsumer {

    private final EmailCadastroTarefasService emailCadastroTarefasService;

    @RabbitListener(queues = "${mq.queues.emailcadastrotarefas-queue}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO){
        var emailCadastroTarefasEntity = new EmailCadastroTarefasEntity();
        BeanUtils.copyProperties(emailRecordDTO, emailCadastroTarefasEntity);

        emailCadastroTarefasService.sendEmailCadastroTarefas(emailCadastroTarefasEntity);
    }
}
