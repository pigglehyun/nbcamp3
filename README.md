# 📗Spring

</br>

[✏️과제 TIL 주소 ](https://www.notion.so/3-23a34f9f370b8051abfbddd52e94e164)    

</br>

**1. 코드 개선**

- throw Exception 위치 변경으로 불필요한 로직 실행 방지
- 불필요한 if-else문 제거
- Lombok의 Validation을 통해 비밀번호 검증을 DTO에서 처리

</br>

**2. N+1문제 해결**

- Todo 데이터를 가져오는 과정에서 연관관계인 User 데이터가 함께 불러와지는 문제를 (@EntityGraph / Fetch Join) 으로 해결 

</br>

**3. 테스트코드 리팩토링**

- 가독성을 고려한 메서드 이름
- 테스트 코드 오류 수정

</br>

**4. AOP를 활용한 API 로깅**

- JWT Filter에서 admin 유저 검증
- Admin 권한을 가진 User만 접근할 수 있는 API를 로깅으로 검증
- @Around 어노테이션을 통해 로직 수행 전/후의 데이터를 로깅
  

