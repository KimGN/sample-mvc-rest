#Study

## 스켈레톤 연결
메이븐 스프링 jdk 설정 후 run
 * 작동 잘됨 8080 포트


## DataBase 연결
dependency 추가 mysql, mybatis, JDBC(TOMCAT)

* 처음 yml datasource 기본 설으로 데이터베이스에 접근하였으나 접속이 잘 되지 않는거 같음 
    * 터미널 명령어로 데이터베이스 사용 여부 확인 
        * netstat -an | grep 3306 // 디비 포트 검색 사용여부 확인
    * was 에서 디비접근을 못하는 것을 확인 디비 콘피그 작성 필요
* 디비 콘피그 
    * config 작성 완료하였지만 was에 DB가 연결인 되지 않음 데이터소스를 읽지 못해서 그러는거 같음 다른 방법 으로 시도 해봐야 하나 고민됨 
    
    

