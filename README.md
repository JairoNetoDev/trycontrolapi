# TryControl: Seu Controle do Seu Jeito

**TryControl** é um aplicativo para controle de despesas pessoais, com o objetivo de ajudar usuários a gerenciar seus gastos de forma fácil e acessível. A principal característica do TryControl é sua integração com o Google Sheets, permitindo que o usuário faça registros offline de suas despesas e, quando estiver online novamente, a aplicação sincroniza automaticamente com o banco de dados e atualiza a planilha.

## Funcionalidades Principais:
- **Cadastro de Despesas**: Permite ao usuário cadastrar suas despesas de forma simples.
- **Controle Offline**: O usuário pode registrar suas despesas mesmo sem conexão com a internet.
- **Integração com Google Sheets**: A aplicação mantém uma planilha sincronizada com o banco de dados para visualização e controle das despesas.
- **Integração com Bancos Digitais**: Planejado para integrar com bancos como Inter e Nubank.

## Tecnologias Usadas:
- **Backend**: Java 21, Spring Boot.
- **Banco de Dados**: PostgreSQL (com possibilidade de usar H2 para testes locais).

## Features:
- **Frontend**: Planejado para ser implementado com React (para Web) ou React Native (para Mobile).
- **Integração**: Google Sheets API via Google App Script.
