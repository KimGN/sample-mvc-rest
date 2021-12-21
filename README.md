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
* OAuth2 로그인 구현 
  * 구글 , 네이버 
  * 데이터베이스에 자동으로 등록 구현


## SESSION 
1. 클라이언트에서 최조 요청을 보냄
2. 서버에서 세션 ID를 만들어서 쿠키에 저장
3. 서버에서 Header 쿠키에 세션 ID 담아서 클라이언트에 전달
4. 클라이언트 쿠키에 세션 정보 저장
5. 클라이언트에서 세션 아이디를 가지고 계속 인증
* 세션 초기화
  * 일정 시간이 지나면 세션 끈김 
  * 브라우저를 다 닫으면 세션 끈김 
  
## SESSION 단점 - 로드 밸런싱
1. 동시 처리 하기위해서 여러대의 서버로 로드 밸런싱 진행 
2. 세션 정보가 서버 마다 달라 질수 있음
3. 다수의 서버가 한공간을 참조(DB)
4. DB를 참조하게 되면 I/O가 발생해서 속도가 느려짐
5. 메모리 DB를 사용하여 속도 개선 (Redis, AWS ElastiCache)


## TCP 
###통신 OSI 7계층
* 물-데-네-트-세-프-응
  * 물리 - 데이터링크 - 네트워크 - 트랜스포트 - 세션 - 프리젠테이션 - 응용

- 응용 계층 / Application Layer
  - a.비밀번호, b.사진 전송 
  
- 표현 계층 / Presentation Layer
  - a.암호화, b.압축 
- 세션 계층/ Session Layer
  - 인증 확인
- 전송 계층 / Transport Layer
  - TCP/UDP 선택
    * TCP / Transmission Control Protocol 
      * 신뢰성이 있음
      * 연결 지향적
    * UDP / User Datagram Protocol
      * 신뢰성이 없음 
      * 비연결 적 
- 네트워크 계층 / Network Layer
    - IP 결정 (공유기) / WAN 
- 데이터 링크 계층 / DataLink Layer
    - IP 를찾아가서 (공유기의 물려있는 장비) / LAN
- 물리 / Physical Layer
    - 케이블로 데이터 전송
<br>
### CIA Triad 
#### 기밀성(Confidentiality), 무결성(Integrity), 가용성(Availability)

- 데이터 전송 과정에서 기밀성, 무결성, 가용성이 유지 되어야 한다.
  -중간에 누군가 데이터, ack 가로체서 위조 할수 없게 유지 되어야 한다. 

### RSA (암호화)
  - Public Key : 공개키
  - Private Key : 개인키 
  <br>
  A : A(공개키) , A(개인키)
  <br>
  B : B(공개키) , B(개인키)
  <br>
  * 암호화 - 개인키로 데이터를 확인 
    * A ---> { B(공개키) - DATA } ---> B(개인키) 데이터를 확인
  * 전자서명 - 출처 확인 가능 - 공개키로 데이터 확인
    * A ---> { A(개인키) - DATA } ---> B는 A(공개키) 테이터 확인
  <br>
  * 두가지를 조합해서 인증과 암호화 모두 할수 있다
    * A ---> [ A(개인키){ B(공개키) - DATA } ] <br>
        ---> B는 A(공개키)로 인증을 하고 B(개인키)로 암호화를 풀 수 있다.
      
### RFC (Request for Comments) / Protocol
https://net-study.club/entry/RFC-Request-for-Comments%EB%9E%80-RFC%EC%9D%98-%EC%97%AD%EC%82%AC-RFC-%EC%A2%85%EB%A5%98-RFC-%ED%91%9C%EC%A4%80%ED%99%94-%EC%A0%88%EC%B0%A8


## JWT - Json Web Token
https://jwt.io/
<br>
세선의 고질적인 문제를 해결 할수 있다.
* 세션을 먼저 만들어보고 진행하자

 