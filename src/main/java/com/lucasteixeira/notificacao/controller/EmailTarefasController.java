package com.lucasteixeira.notificacao.controller;


import com.lucasteixeira.notificacao.business.services.EmailTaskExpirationService;
import com.lucasteixeira.notificacao.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailTarefasController {

    private final EmailTaskExpirationService emailTaskExpirationService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasDTO dto){
        emailTaskExpirationService.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }
}
