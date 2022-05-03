#Effitizer_Backend

### Login_flow - 채영
1. 사용자(사람)은 웹브라우저(리액트)에서 원하는 로그인 서비스를 선택한다.
2. 리액트에서 다음과 같은 url로 요청을 하게 된다.
```
http://localhost:xxxx/oauth2/authorization/kakao?redirect_uri=@@@
```
3. 백엔드 서버는 해당 요청이 오면 "oauth2/"부분에서 uri 캐치를 하여 이용 서비스에게로 리다이렉트를 하게 된다. 리다이렉트 uri는 다음과 같다.
```
    - A : 사용자가 인증에 성공하면, 해당 redirect_uri로 Authorization Code를 전달한다.
https://kauth.kakao.com/oauth/authorize
?client_id=  
    ${선택한 로그인 서비스의 clientID} 
	  &redirect_uri=${kakao에 등록한 redirectUri} // A
	  &response_type=code // B
```
4. url로 요청을 하면, 사용자의 브라우저에는 로그인 창이 나온다.
5. 사용자가 로그인을 성공한다.
6. "redirect uri"로 code를 담아서 보내준다.
7. 백엔드 서버는 해당 uri로 authorization code를 받는다.
8. 백엔드 서버는 authorization code 요청에 담아서 token 요청 uri에 access token을 요청한다. 요청 uri는 `provider의 token-uri`이다.(kakao와 naver의 경우)
9. 백엔드 서버는 accessToken을 이용하여 카카오 resource Server에 회원 정보를 요청한다.
10. accessToken이 올바르다면 카카오 resource Server는 백엔드에게 사용자 정보를 넘겨준다.
11. 백엔드는 사용자 정보를 받고, JWT (access token, refresh token)을 생성한다. (해당 정보로 db의 추가 정보 값을 조회해보고 조회가 안된다면 최초로그인이다.)
12. 백엔드 서버는 해당 토큰을 요청에 포함하여 프론트엔드로 리다이렉트 시킨다. 리다이렉트 시킬때 uri는 `redirect uri`이다.