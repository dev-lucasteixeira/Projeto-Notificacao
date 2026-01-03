# 📧 MS-Notificacao — Event-Driven Email Worker

Este microsserviço é o braço de comunicação do ecossistema. Projetado como um **Worker assíncrono**, sua responsabilidade é consumir eventos de agendamento e garantir a entrega de notificações personalizadas via e-mail, utilizando protocolos SMTP e integração com mensageria.

---

## 🏗️ Arquitetura e Fluxo de Entrega

O **MS-Notificacao** opera na ponta final do fluxo de dados, garantindo que o processamento de e-mails não onere a performance do Core Business:



1.  **Consumo**: Escuta a fila dedicada no **RabbitMQ** aguardando eventos de tarefas.
2.  **Processamento**: Recebe o payload, valida os dados do destinatário e seleciona o template.
3.  **Execução**: Interage com o servidor **SMTP** para o disparo do e-mail.
4.  **Feedback**: (Opcional) Notifica o MS-Agendador sobre o sucesso ou falha do envio.

---

## 🛠️ Stack Tecnológica

- **Java 17/24** & **Spring Boot 3.5.x**
- **Spring Boot Starter Mail**: Integração robusta com Jakarta Mail para envio via SMTP/TLS.
- **RabbitMQ (Spring AMQP)**: Consumo de mensagens em background para processamento resiliente.
- **Spring Security + JWT**: Proteção de endpoints administrativos e validação de contexto.
- **MongoDB**: Armazenamento de logs de envio e histórico de notificações.
- **Gradle**: Automação de build e gerenciamento de dependências.

---

## ⚙️ Funcionalidades Técnicas

| Recurso | Descrição |
| :--- | :--- |
| **Async Consumption** | Consome mensagens da fila sem bloquear o produtor (MS-Agendador). |
| **Retry Logic** | Mecanismo de reprocessamento em caso de falha temporária no servidor SMTP. |
| **Email Templating** | Suporte a e-mails formatados para melhor experiência do usuário. |
| **SMTP Integration** | Configuração pronta para provedores como Gmail, Outlook ou Mailtrap. |

---

## 🚀 Como Executar

### 1. Infraestrutura Necessária
- **MongoDB**: (Porta `27017`) Para logs de auditoria de e-mail.
- **RabbitMQ**: (Porta `5672`) Para recebimento dos eventos.
- **Servidor SMTP**: Necessário para o envio real (recomenda-se **Mailtrap** para desenvolvimento).

### 2. Configuração de Variáveis (`application.yml`)
```yaml
spring:
  mail:
    host: smtp.mailtrap.io
    port: 2525
    username: ${SMTP_USER}
    password: ${SMTP_PASS}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
  rabbitmq:
    template:
      exchange: "tarefas.exchange"
    queues:
      tarefas-notificacao: "tarefas.queue"
3. Build e Execução
Bash

./gradlew clean build
java -jar build/libs/notificacao-0.0.1-SNAPSHOT.jar
📩 Estrutura do Evento Consumido
O worker espera um payload no seguinte formato vindo do Broker:

JSON

{
  "emailUsuario": "destinatario@exemplo.com",
  "nomeTarefa": "Reunião de Alinhamento",
  "descricao": "Discutir os novos microsserviços",
  "dataEvento": "2026-01-03T18:00:00"
}

```
📈 Resiliência
Graças ao uso do RabbitMQ, se o servidor de e-mail estiver temporariamente fora do ar, as mensagens permanecem seguras na fila e são processadas automaticamente assim que a conexão é reestabelecida, garantindo que o usuário nunca perca uma notificação.

Desenvolvido por Lucas Teixeira 👨‍💻
