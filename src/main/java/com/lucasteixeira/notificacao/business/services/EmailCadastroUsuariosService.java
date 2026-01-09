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

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailCadastroUsuariosEntity sendEmailCadastroUsuarios(EmailCadastroUsuariosEntity emailEntity) {
        try {
            // 1. Prepara os dados da entidade
            emailEntity.setSendDateEmail(LocalDateTime.now());
            emailEntity.setEmailFrom(emailFrom);

            // 2. Validação simples antes de enviar
            if (emailEntity.getEmailTo() == null || emailEntity.getText() == null) {
                throw new IllegalArgumentException("Destinatário ou corpo do e-mail estão vazios.");
            }

            // 3. Configura e envia a mensagem
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailEntity.getEmailTo());
            message.setSubject(emailEntity.getSubject());
            message.setText(emailEntity.getText());

            emailSender.send(message);

            emailEntity.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException | IllegalArgumentException e) {
            System.err.println("Erro ao enviar email para: " + emailEntity.getEmailTo());
            e.printStackTrace();
            emailEntity.setStatusEmail(StatusEmail.COM_ERRO);
        }

        return emailCadastroUsuariosRespository.save(emailEntity);
    }
}
