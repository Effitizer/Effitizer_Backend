# Effitizer_Backend

### Login_flow - 채영
- 프론트
    - 로그인 API 서버에서 인가 코드 받아오기
    - 받아온 인가 코드를 통해 AccessToken 받아오기
    - 받아온 AccessToken을 Header에 포함하여 서버에 전송하기
 
- 백
    - 클라이언트가 정해진 주소로 AccessToken을 헤더에 담아서 요청을 보내면, 해당 AccessToken을 가지고 회원 정보 조회하기
    - 조회한 정보를 바탕으로, 회원가입이나, 로그인 처리 등의 로직 구현하기

### 현재 함수 동작
1. CustomOauth2UserService의 loadUser
2. loadUser 안에서 OAuthAttributes of 호출
3. OAuthAttributes 생성자
4. CustomOauth2UserService의  loadUser 안에서 saveOrUpdate 호출

### URL 
- `/oauth2/authorization/google`: 구글 로그인 계정 선택하는 창으로 이동
- `/oauth2/authorization/naver` : 네이버 계정을 연결하는 창으로 이동