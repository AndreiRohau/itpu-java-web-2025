# spring-rest

## ws-25-spring-rest
- [Learning REST APIs](https://www.linkedin.com/learning/learning-rest-apis/welcome?u=2113185)
- [official documentation](https://spring.io/guides/tutorials/rest)
- [REST with Spring Tutorial](https://www.baeldung.com/rest-with-spring-series)
- []()
- []()


___

POST

http://localhost:8080/employees

{
"name": "Mr Xi",
"role": "Chairman"
}

---

PUT

http://localhost:8080/employees/3

{
"id": 3,
"name": "tOster",
"role": "Nester"
}

---

NOT an IDEMPOTENT implementation - bad practice

PUT

http://localhost:8080/employees/33

{
"id": 333,
"name": "tOster",
"role": "Nester"
}

---

An IDEMPOTENT implementation - good practice

DELETE

http://localhost:8080/employees/5

---



