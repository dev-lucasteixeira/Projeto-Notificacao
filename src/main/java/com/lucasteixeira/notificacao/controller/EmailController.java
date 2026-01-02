package com.lucasteixeira.notificacao.controller;


import com.lucasteixeira.notificacao.business.services.EmailTarefasService;
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
public class EmailController {

    private final EmailTarefasService emailTarefasService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasDTO dto){
        emailTarefasService.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }
}
