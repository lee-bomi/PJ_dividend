# PJ_dividend
### 웹 스크래핑을 통한 주식 배당금 관리 프로젝트
Use : Spring, Jpa, MariaDB, Java, Spring Security, Redis, Jwt  
Goal : 주식 배당금 정보를 가져와서 저장 후 활용한다

### 필수구현사항
- yahoo finance 데이터 스크래핑
- DB table 모델링 및 연관관계 매핑
- 스크래핑한 데이터를 적절한 형태로 DB 에 저장
- 저장된 데이터를 활용하기위한 REST API 메서드를 사용
- 레디스 서버 구성
- 레디스에 데이터 캐싱/삭제
- 로그레벨로 필요한 로그 남기기
- ControllerAdvice 에서 에러 처리



