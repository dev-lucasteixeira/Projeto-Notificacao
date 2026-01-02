package com.lucasteixeira.notificacao.business.dto;


public record EmailRecordDTO(Long userId,
                             String emailTo,
                             String subject,
                             String text) {
}
