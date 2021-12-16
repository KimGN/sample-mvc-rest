#Study

## 스켈레톤 연결
메이븐 스프링 jdk 설정 후 run

## DataBase 연결
dependency 추가 mysql, mybatis


## Login , Join 

* 로그인을 하기위해 security config 등록
    * @Override configure
    * Security Session <= Authentication <= UserDetails 순서로 등록을 해야함
    * UserDetails(PrincipalDetails) 객채 생성 
    * Authentication 등록 PrincipalDetailsService / @Override UserDetails loadUserByUsername
* 회원가입
    * password Encode @Bean 등록
    * 회원가입 password encode 변환해서 등록
