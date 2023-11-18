# Spring Boot JWT Boilerplate


<br/>

## Specification

---

- java 17
- Spring Boot 3.2.0
- Spring Security 6.2.0
- JWT 0.12.3
- PostgreSQL 15.5

## Jwt Info
```javascript
claims = {email=sghg@sghg.kr, username=sghj, sub=1, iat=1700318366, exp=1700320166}

sub: userId
claims: {
    email: user.email
    username: user.username
}
iat: now() (KST)
exp: now() + @ (access = 20m, refresh = 2h)
```
```java
======== sample ========
2023-11-18T23:44:21.625+09:00  INFO 31248 --- [nio-8080-exec-6] c.s.b.user.controller.UserController     : userId = 1
2023-11-18T23:44:21.625+09:00  INFO 31248 --- [nio-8080-exec-6] c.s.b.user.controller.UserController     : expireDt = Sun Nov 19 00:14:16 KST 2023
2023-11-18T23:44:21.625+09:00  INFO 31248 --- [nio-8080-exec-6] c.s.b.user.controller.UserController     : username = sghj
2023-11-18T23:44:21.625+09:00  INFO 31248 --- [nio-8080-exec-6] c.s.b.user.controller.UserController     : email = sghg@sghg.kr
```