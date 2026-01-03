package com.lucasteixeira.notificacao.infrastructure.entity;


import com.lucasteixeira.notificacao.business.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_emails_cadastro_tarefas")
@Builder
public class EmailCadastroTarefasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "emailFrom")
    private String emailFrom;
    @Column(name = "emailTo")
    private String emailTo;
    @Column(name = "subject")
    private String subject;
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    @Column(name = "sendDateEmail", columnDefinition = "TIMESTAMP")
    private LocalDateTime sendDateEmail;
    @Enumerated(EnumType.STRING)
    private StatusEmail statusEmail;
}
