Tema: Sistema Aplicativo com Backend em Microsserviços e Entrega Contínua

Disciplinas:

PTBADOP: Ambiente de Desenvolvimento e Operações (Prof. Adriano Ferruzzi)

PTBDAMS: Desenvolvimento de APIs e Microsserviços (Prof. Luiz Albano)

PTBDDMA: Desenvolvimento para Dispositivos Móveis (Prof. Renato Montanher)

Grupo:

Gabriele de Barros Oliveira – Microsserviço Usuários

Letícia – Microsserviço Categorias

Ana – Microsserviço Transações

Jhonathan – Deploy e Integração

Matheus – Front-end Flutter

Descrição Geral

O sistema de Gestão Financeira Pessoal permite controle de gastos, cadastro de categorias e registro de transações financeiras. A arquitetura adota microsserviços independentes e mensagens assíncronas via RabbitMQ. O deploy é automatizado com práticas DevOps.

Componentes:

Aplicativo móvel em Flutter

Backend: 3 microsserviços (Usuários, Categorias, Transações)

Mensageria RabbitMQ

Deploy contínuo via Render (ou serviço equivalente)

Status do Microsserviço de Usuário (completado por Gabriele)

Funcionalidades Implementadas

CRUD de Usuários (Spring Boot REST)

Integração com PostgreSQL local

Documentação Swagger/OpenAPI

CORS configurado para Flutter

Producer RabbitMQ (fila fila.usuario)

Estrutura de Pacotes

src/main/java/com/gabi/usuario
 ├── controller/UsuarioController.java
 ├── model/Usuario.java
 ├── repository/UsuarioRepository.java
 ├── messaging/
 │    ├── RabbitMQConfig.java
 │    └── UsuarioProducer.java
 └── config/CorsConfig.java

Execução Local

# RabbitMQ management via Docker
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

# Banco PostgreSQL rodando local
# Build e execução
./mvnw clean install
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

Acesso: http://localhost:8080/swagger-ui.html

Status Microsserviço Transações (implementado por Ana)

Funcionalidades Implementadas

CRUD de Transações

Consumer RabbitMQ (fila categoria.queue)

Configuração de fila e listener (RabbitMQConfig + CategoriaConsumer)

Status Microsserviço Categorias (implementado por Letícia)

Funcionalidades Implementadas

CRUD de Categorias

Producer RabbitMQ (fila categoria.queue)

Deploy e Integração (executado por Jhonathan)

Concluído

Provisionamento de instância RabbitMQ na nuvem (CloudAMQP)

Configuração de profiles Spring Boot (dev para local, padrão para produção)

Uso de variáveis de ambiente no Render para RabbitMQ

Script Bash para organizar pacotes e corrigir declarações de package

Deploy inicial do microsserviço de Usuário no Render (versão de staging)

Em Andamento / Pendências

Provisionar banco PostgreSQL em nuvem (ElephantSQL ou semelhante)

Configurar variáveis de ambiente de banco no ambiente de produção

Ajustar pipeline CI/CD para builds automatizados e testes (GitHub Actions ou Render Autoscale)

Documentar endpoint de mensageria no Swagger UI em produção

Integrar front-end Flutter com endpoints em produção

Testes de carga e resiliência (retry/backoff RabbitMQ)

***Próximos Passos Gerais  faltantes**

Criar instância PostgreSQL em nuvem e configurar no Render

Completar CI/CD: GitHub Actions para build, testes e deploy automático

Implementar monitoramento e alertas (logs centralizados, health checks)

Validar fluxo end-to-end: Flutter ↔ Usuários/Categorias/Transações

Realizar testes de usabilidade no app Flutter

Preparar documentação final e apresentação do projeto

