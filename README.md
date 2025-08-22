# 📧 API de Notificação por E-mail – Agendador de Tarefas

Este módulo é responsável por **enviar notificações por e-mail** para os usuários sobre suas tarefas agendadas.  
O objetivo é manter os usuários informados e garantir que não percam prazos importantes.

---

## 🚀 Tecnologias Utilizadas

- ☕ **Java 17+**
- 🌱 **Spring Boot**
  - Spring Web
  - Spring Mail
  - Spring Security + JWT
- 🐘 **MongoDB**
- 📦 **Gradle (Groovy DSL)**
- 🧪 **JUnit** (testes unitários)
- 📩 **JavaMail / Jakarta Mail**

---

## ⚙️ Funcionalidades

- Envio de notificações por e-mail para tarefas agendadas  
- Configuração de templates de e-mail  
- Integração com o módulo de Tarefas via **Feign Client**  
- Suporte a múltiplos destinatários e agendamento de envio  

---

## 🛠️ Como Executar

1. Configure o **MongoDB** e o **servidor SMTP** no `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/db_agendador
   spring.mail.host=smtp.seuprovedor.com
   spring.mail.port=587
   spring.mail.username=seu_email
   spring.mail.password=sua_senha
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
