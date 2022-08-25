# Cloud Parking

Cloud Parking é uma API utilizando Spring Boot para controlar um estacionamento de veículos, que gerencia a entrada, a saída e o valor a ser cobrado do cliente. 

Foi desenvolvida em um Desafio de Projeto, ministrado pelo especialista [Sandro Giacomozzi](https://github.com/sandrogiacom), durante o Bootcamp Spring Framework Experience, da [Digital Inovation One](https://www.dio.me/). O repositório original pode ser acessado [aqui](https://github.com/sandrogiacom/cloud-parking).

São usadas todas as boas práticas de desenvolvimento de API's incluindo segurança com Spring Security e acesso a banco de dados PostgreSQL. 

O deploy foi feito na cloud do Heroku.

# Como rodar o projeto

O docker pode ser usado para criar o banco de dados com seguinte comando:

```
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine
```

Após ter o banco de dados criado e configurado, o projeto pode ser executado com o seguinte comando:

```
./mvnw spring-boot:run
```
