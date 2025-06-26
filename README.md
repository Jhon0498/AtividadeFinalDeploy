Microsserviço de Usuario - Projeto Final Integrado - ADS 5º Semestre-
-
Tema: Sistema Aplicativo com Backend em Microsserviços e Entrega Contínua
-


Este projeto foi desenvolvido como parte das disciplinas:

PTBADOP: Ambiente de Desenvolvimento e Operações (Prof. Adriano Ferruzzi)

PTBDAMS: Desenvolvimento de APIs e Microsserviços (Prof. Luiz Albano)

PTBDDMA: Desenvolvimento para Dispositivos Móveis (Prof. Renato Montanher)

---

Grupo:

Gabriele de Barros Oliveira – Microsserviço Usuários

Letícia – Microsserviço Categorias

Ana – Microsserviço Transações

Jhonathan – Deploy e Integração

Matheus – Front-end Flutter

---

Descrição Geral do Projeto

O objetivo do projeto foi criar uma solução tecnológica para Gestão Financeira Pessoal, permitindo que os usuários controlem seus gastos, cadastrem categorias e registrem transações financeiras.

---

A solução contém:

Um aplicativo móvel funcional (em Flutter)
Um backend dividido em 3 microsserviços independentes
Mensageria assíncrona com RabbitMQ
Deploy automatizado usando técnicas de DevOps
--- 
Estrutura de Microsserviços (Backend)

| Microsserviço | Responsável | Funcionalidades | Comunicação |
|---|---|---|---|
| **Usuários** | Gabriele | CRUD completo de usuários | Produz mensagens no RabbitMQ (Producer) |
| **Categorias** | Letícia | CRUD de categorias | Produz mensagens para o RabbitMQ |
| **Transações** | Ana | CRUD de transações | Consome mensagens do RabbitMQ |

---

Microsserviço de Usuário

 Principais Funcionalidades:

- CRUD de Usuários (Endpoints REST via Spring Boot)
- Integração com PostgreSQL
- Documentação de API via Swagger
- Configuração de CORS (para permitir chamadas do app Flutter)
- Integração com RabbitMQ como **Producer**  
  (Enviando mensagens para a fila `fila.usuario` em eventos de criação, atualização e exclusão de usuários)

---

## Tecnologias utilizadas no Microsserviço de Usuários:

- Java 17 + Spring Boot
- Maven
- PostgreSQL
- RabbitMQ (via Spring AMQP)
- Swagger / OpenAPI
- Docker (RabbitMQ Container)
- VS Code

---

###  Exemplo de Configuração RabbitMQ no `application.properties`:


spring.rabbitmq.host=localhost
 spring.rabbitmq.port=5672
 spring.rabbitmq.username=guest
 spring.rabbitmq.password=guest

---

###  Estrutura de Pacotes do Microsserviço Usuário:


src/main/java/com/gabi/usuario
 ├── controller
 │ └── UsuarioController.java
 ├── model
 │ └── Usuario.java
 ├── repository
 │ └── UsuarioRepository.java
 ├── messaging
 │ ├── RabbitMQConfig.java
 │ └── UsuarioProducer.java
 └── config
 └── CorsConfig.java

---

###  Como Rodar Localmente:

1. **Subir o RabbitMQ com Docker:**

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

Garantir que o PostgreSQL esteja rodando, com o banco usuarios_db criado.


Executar o microsserviço:


mvn clean install
mvn spring-boot:run

Acessar o Swagger:


http://localhost:8080/swagger-ui.html

 Status do Microsserviço de Usuário:

CRUD de Usuários
Integração com PostgreSQL
Documentação Swagger
Produção de mensagens no RabbitMQ
CORS habilitado para Flutter


 Observações Finais:
-A integração assíncrona obrigatória do projeto está sendo realizada entre Categorias (Producer) e Transações (Consumer).
-O Microsserviço de Usuários funciona apenas como Producer no RabbitMQ, enviando mensagens sempre que um usuário é criado, atualizado ou deletado.




