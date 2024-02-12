# <p align="center"> PicPay Simplificado </p>
O objetivo do repositório é treinar o framework Spring, resolvendo um desafio de vaga backend da empresa PicPay e aprimorando minhas habilidades como programador. Link do desafio: https://github.com/simplify-tec/desafio-junior-backend-simplify

## Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Spring Validation
- Lombok

## Como rodar
Basta baixar o projeto e rodar o seguinte comando: `java -Dspring.profiles.active=prod -jar target/picpay-0.0.1-SNAPSHOT.jar`

## Rotas
POST - `/users` - criar uma nova task no banco.

`{
"name": "Testname",
"email": "Test Email",
"document": "Teste document",
"password": "1234"
}`

GET - `/users/{id}` - resgatar algum usuário do banco.

DELETE - `/users/{id}` - deleta algum usuário do banco.

POST - `/transactions` - criar uma nova transação no banco.

`{
"payer": 1,
"payee": 2,
"value": 24
}`

GET - `/transactions/{id}` - resgatar alguma transação do banco.

DELETE - `/transactions/{id}` - deleta alguma transação do banco.
