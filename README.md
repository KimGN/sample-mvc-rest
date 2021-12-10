#Study

## 스켈레톤 연결
메이븐 스프링 jdk 설정 후 run

* jdk 1.8

## DataBase 연결
dependency 추가 mysql, mybatis

## Spring security 로그인 회원가입 구현 

예제 https://spring.io/guides/gs/securing-web/

의존성 주입 <br>
spring-boot-starter-security

## Remove DS_Store
파일 삭제 <br>
find . -name .DS_Store -print0 | xargs -0 git rm -f --ignore-unmatch
앞으로도 추가 안하고 싶으면 <br>
echo .DS_Store >> .gitignore
<br>
<br>


