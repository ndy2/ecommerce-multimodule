## Ecommerce App

- 인프런 이도원님의 강의 ([링크](https://www.inflearn.com/course/스프링-클라우드-마이크로서비스)) 를 바탕으로 코틀린, 멀티모듈 기반으로 바꾸어 구성해보았습니다.
- Section 12. 까지의 내용을 실습해보았습니다.
- [원본 깃헙 링크](https://github.com/joneconsulting/msa_with_spring_cloud)

### How to Run
1. docker build all services
```
docker build -t ndy2/user-service:1.0 user-service/.
docker build -t ndy2/order-service:1.0 order-service/.
docker build -t ndy2/product-service:1.0 product-service/.
docker build -t ndy2/service-discovery:1.0 service-discovery/.
docker build -t ndy2/apigateway:1.0 api-gateway/.
docker build -t ndy2/config-server:1.0 config-server/.
```

2. run apps
```
docker-compose up docker-compose-infra.yml
user-service/gradlew bootRun
order-service/gradlew bootRun
product-service/gradlew bootRun
```

3. Goto Eureka-Service
```
http://localhost:8761
```

### 더 공부할것
- 테스트 컨테이너
  - https://www.testcontainers.org/
- multi-module 에서 document 를 효율적으로 제공하는 방법 찾기
  - 참고 https://github.com/spring-cloud/spring-cloud-config)
- docker 에서제공하는 설정을 spring-cloud-config 에의해 덮어지지 않도록 효율적으로 제어하는 방법찾기 