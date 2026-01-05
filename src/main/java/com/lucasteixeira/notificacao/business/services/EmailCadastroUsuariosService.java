package com.lucasteixeira.notificacao.business.services;

import com.lucasteixeira.notificacao.business.enums.StatusEmail;
import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroUsuariosEntity;
import com.lucasteixeira.notificacao.infrastructure.respository.EmailCadastroUsuariosRespository;
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
public class EmailCadastroUsuariosService {

    private final EmailCadastroUsuariosRespository emailCadastroUsuariosRespository;
    private final JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public EmailCadastroUsuariosEntity sendEmailCadastroUsuarios(EmailCadastroUsuariosEntity emailCadastroUsuariosEntity){
        try{
            emailCadastroUsuariosEntity.setSendDateEmail(LocalDateTime.now());
            emailCadastroUsuariosEntity.setEmailFrom(emailFrom);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailCadastroUsuariosEntity.getEmailTo());
            message.setSubject(emailCadastroUsuariosEntity.getSubject());
            message.setText(emailCadastroUsuariosEntity.getText());
            emailSender.send(message);

            emailCadastroUsuariosEntity.setStatusEmail(StatusEmail.ENVIADO);
        }catch (MailException e){
            System.out.println("Erro ao enviar email");
            e.printStackTrace();
            emailCadastroUsuariosEntity.setStatusEmail(StatusEmail.COM_ERRO);
        } finally {
            return emailCadastroUsuariosRespository.save(emailCadastroUsuariosEntity);
        }
    }
}
