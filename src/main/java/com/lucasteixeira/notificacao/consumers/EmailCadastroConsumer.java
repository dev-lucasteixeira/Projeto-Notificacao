package com.lucasteixeira.notificacao.consumers;

import com.lucasteixeira.notificacao.business.dto.EmailRecordDTO;
import com.lucasteixeira.notificacao.business.services.EmailCadastroService;
import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailCadastroConsumer {

    private final EmailCadastroService emailCadastroService;

    @RabbitListener(queues = "${mq.queues.email-queue}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO){
        var emailCadastroEntity = new EmailCadastroEntity();
        BeanUtils.copyProperties(emailRecordDTO, emailCadastroEntity);

        emailCadastroService.sendEmail(emailCadastroEntity);
    }
}
