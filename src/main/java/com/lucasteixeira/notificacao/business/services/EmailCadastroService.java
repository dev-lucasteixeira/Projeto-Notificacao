package com.lucasteixeira.notificacao.business.services;

import com.lucasteixeira.notificacao.business.enums.StatusEmail;
import com.lucasteixeira.notificacao.infrastructure.entity.EmailCadastroEntity;
import com.lucasteixeira.notificacao.infrastructure.respository.EmailCadastroRespository;
import jakarta.annotation.PostConstruct;
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
public class EmailCadastroService {

    private final EmailCadastroRespository emailCadastroRespository;
    private final JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public EmailCadastroEntity sendEmail(EmailCadastroEntity emailCadastroEntity){
        try{
            emailCadastroEntity.setSendDateEmail(LocalDateTime.now());
            emailCadastroEntity.setEmailFrom(emailFrom);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailCadastroEntity.getEmailTo());
            message.setSubject(emailCadastroEntity.getSubject());
            message.setText(emailCadastroEntity.getText());
            System.out.println("Enviado com sucesso");
            emailSender.send(message);

            emailCadastroEntity.setStatusEmail(StatusEmail.ENVIADO);
        }catch (MailException e){
            System.out.println("Erro ao enviar email");
            e.printStackTrace();
            emailCadastroEntity.setStatusEmail(StatusEmail.COM_ERRO);
        } finally {
            return emailCadastroRespository.save(emailCadastroEntity);
        }
    }


    @PostConstruct
    public void logConfig() {
        System.out.println("----------------------------------------------");
        System.out.println("CONFIGURAÇÃO DE E-MAIL CARREGADA: " + emailFrom);
        System.out.println("----------------------------------------------");
    }
}
