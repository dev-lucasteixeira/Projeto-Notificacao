package com.lucasteixeira.notificacao.business.services;

import com.lucasteixeira.notificacao.business.enums.StatusEmail;
import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroTarefasEntity;
import com.lucasteixeira.notificacao.infrastructure.respository.EmailCadastroTarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailCadastroTarefasService {

    private final EmailCadastroTarefasRepository emailCadastroTarefasRespository;
    private final JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public EmailCadastroTarefasEntity sendEmailCadastroTarefas(EmailCadastroTarefasEntity emailCadastroTarefasEntity){
        try{
            emailCadastroTarefasEntity.setSendDateEmail(LocalDateTime.now());
            emailCadastroTarefasEntity.setEmailFrom(emailFrom);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailCadastroTarefasEntity.getEmailTo());
            message.setSubject(emailCadastroTarefasEntity.getSubject());
            message.setText(emailCadastroTarefasEntity.getText());
            emailSender.send(message);

            emailCadastroTarefasEntity.setStatusEmail(StatusEmail.ENVIADO);
        }catch (MailException e){
            System.out.println("Erro ao enviar email");
            e.printStackTrace();
            emailCadastroTarefasEntity.setStatusEmail(StatusEmail.COM_ERRO);
        } finally {
            return emailCadastroTarefasRespository.save(emailCadastroTarefasEntity);
        }
    }
}
