# ClevertecReceiptTask

Стек:
 Spring Boot
 Docker (Docker Desktop 4.15.0)
 JPA
 Lombok
 Java 17
 Gradle
 Junit
 Mokito
  
Инструкция:
 -Клонировать репозиторий https://github.com/PavelLukyanovich/ClevertecReceiptTask.git
 -Запустить docker-compose.yml (docker-compose up) в корне проекта
 
Postman (для проверки)
Endpoint /receipt
 - POST localhost:8080/receipt
 - Body :
        {
    "cardNumber": "0010",
    "items": [
        {
            "id": 1,
            "amount": 1
        },
        {
            "id": 2,
            "amount": 1
        },
        {
            "id": 3,
            "amount": 1
        }
    ]
}
