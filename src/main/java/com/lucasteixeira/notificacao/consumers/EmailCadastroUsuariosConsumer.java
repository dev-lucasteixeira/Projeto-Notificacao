package com.lucasteixeira.notificacao.consumers;

import com.lucasteixeira.notificacao.business.dto.EmailRecordDTO;
import com.lucasteixeira.notificacao.business.services.EmailCadastroUsuariosService;
import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroUsuariosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailCadastroUsuariosConsumer {

    private final EmailCadastroUsuariosService emailCadastroUsuariosService;

    @RabbitListener(queues = "${mq.queues.emailcadastro-queue}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO){
        var emailCadastroEntity = new EmailCadastroUsuariosEntity();
        BeanUtils.copyProperties(emailRecordDTO, emailCadastroEntity);

        emailCadastroUsuariosService.sendEmailCadastroUsuarios(emailCadastroEntity);
    }
}
