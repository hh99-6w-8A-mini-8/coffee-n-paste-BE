# Coffee N Pasete Project
## Project Introduction
### YOUTUBE
[![Coffee N Paste](https://img.youtube.com/vi/hV9tPMfjEeU/0.jpg)](https://youtu.be/hV9tPMfjEeU)

### WHO
#### 💻 Front-end developer ::: 
- 🏆 **강태훈** [FE리더]
  * 메인페이지 네비게이션, 메인 이미지 기능 구현, 상세페이지 게시글 Read, 댓글 CRUD, 무한스크롤
- 🏆 **나유진**
  * 게시글 Add, 이미지 미리보기, 이미지 리사이징, 메소리니 레이아웃, createPotal
- 🏆 **고호성**
  * 로그인*회원가입 모달

#### 💻 Back-end developer ::: 
- 🏆 **박상욱** [BE리더]
- 🏆 **김휘림** [총괄]
- 🏆 **김현중**
- 🏆 **최준우**

### WHEN
- 2022.08.12 ~ 2022.08.18

### WHAT
- Web Service 'Coffee N Paste'
- ::: 커피 프랜차이즈 메뉴별 리뷰 커뮤니케이션 서비스

### 주요기능
- 💻 Infinite Scroll 
- 💻 CreatePotal 
- 💻 Image Upload 
- 💻 Image Resizing(Compression) 
- 💻 LogIn & SignIn 
- 💻 Post CRUD
- 💻 Comment CRUD

### Trouble Shooting
### FE

**1. 이미지 프리뷰 업로드 및 리사이징, 서버 전송**
- Formdata 형식, Multi Part 데이터 형식으로 전송 필요 → FormData Web API 사용해서 해결
- 이미지 프리뷰 기능 구현 : 기능 구현 후 확인해보니 이미지 용량이 커서 사이즈 줄일 필요성을 느낌 → browser-image-compression 패키지 활용하여 이미지 사이즈 조절

**2. 게시글 이미지(AWS S3) 전송 후 게시글 컨텐츠 전송**
- 게시글 등록 버튼을 눌렀을 때 이미지 및 게시글 컨텐츠가 한 번에 전송되었으면 함. Async Await 활용하여 순차적으로 전송 진행

**3. 모달창 구현으로 상세 게시글 불러올 때 랜더링 안되는 현상**
- CreatePortal활용하여 모달 창 구현 :  기존 작업과 충돌현상이 일어나 모달창 컨텐츠의 초기 렌더링이 안됨 → 자식 컨포넌트로 모달창을 이동시켜 해결

**4. 무한 스크롤 게시글 중복 현상**
- 메뉴별 카테고리 분류에 있어 어려움이 있었음 : 


__이 외에도 에러는 매일 있었음…..!__

### BE

**1. member 객체를 이용하여 Authentication 객체 만들기**
    
```java
    // 전
    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(requestDto.getNickname(), requestDto.getPassword());
    
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    
    TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
    
    // 후
    UserDetailsImpl userDetails = new UserDetailsImpl(member);
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "");
    
    TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
```
    

**2. 발급한 토큰을 헤더에 담아서 클라이언트에게 보내면 브라우저까지는 도달하는데 Axios response까지는 들어가지 않아서 responseBody에 토큰을 담아서 보내기**
    
```java
    TokenDto token = memberService.login(loginRequestDto);
    
    // 전
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token.getAuthorization());
    headers.add("Refresh-Token", token.getRefreshToken());
    
    return ResponseEntity.ok()
    					.headers(headers)
    					.body(Map.entry("msg", "로그인 완료"));
    
    // 후
    return ResponseEntity.ok()
    					.body(token);
```
    

**3. CORS 가능하게 하기**
    
```java
    // 전
    configuration.setAllowedOrigins(Arrays.asList("*"));
    
    // 후
    configuration.setAllowedOrigin**Patterns**(Arrays.asList("*"));
```
    

**4. JWT 토큰 Provider에서 bearer로 생성하고 Filter에서 Bearer로 검증하는 것으로 되어있었어서 둘 다 Bearer로 통일**
    
```java
    // 전
    private static final String BEARER_TYPE = "bearer";
    
    // 후
    private static final String BEARER_TYPE = "Bearer";
    
    return TokenDto.builder()
                    .authorization(BEARER_TYPE + " " + accessToken)
                    .refreshToken(refreshToken)
                    .build();
```
    

**5. 로그인 시도마다 Refresh Token이 데이터베이스에 저장되어서 저장된 값을 확인하도록 해서 유저 한 명에 Refresh Token 하나씩 저장되도록 변경하기**
    
```java
    String refreshToken = Jwts.builder()
                    .setExpiration(new Date(now + REFRESH_TOKEN_EXPRIRE_TIME))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
    
    //--- 전
    RefreshToken refreshTokenObject = RefreshToken.builder()
                    .member(member)
                    .tokenValue(refreshToken)
                    .build();
    //---
    
    //--- 후
    RefreshToken refreshTokenObject = refreshTokenRepository.findByMember(member)
    			.orElse(
    					RefreshToken.builder()
    							.member(member)
    							.build()
    			);
    refreshTokenObject.updateTokenValue(refreshToken);
    //---
    
    refreshTokenRepository.save(refreshTokenObject);
```
    

**6. 게시물이 9시간 이전으로 등록되는 문제 해결하기**

```shell
   java -Duser.timezone=GMT+9 -jar 프로그램
```

### 패키지
- 스타일 적용 : `styled-components`
- s3 연결 : `aws-sdk`
- json-server (서버) 설치(테스트용) : `yarn add json-server`
- 리덕스 설치 : `yarn add react-redux`
- 툴킷 (리덕스) 설치 : `yarn add @reduxjs/toolkit`
- thunk (미들웨어) 설치 : `yarn add redux-thunk`
- axios(통신) 설치 : `yarn add axios` 
- logger (개발 편하게 도와줌) 설치 : `yarn add redux-loger`
- image resizing : `yarn add browser-image-compression`
- masorny 레이아웃 기능 구현 : `yarn add imagesloaded` (DOM에 부착된 이미지 로드 시점 알기 위해 사용)

### git commit message 규칙
- 파일 생성 ✨new : ~~~ 생성에 대한 커밋
- 파일 업데이트 🚀update : ~~~ 추가에 대한 커밋
- 파일 수정 ✂fix : ~~~ 수정에 대한 커밋
- 파일 삭제 🗑delete : ~~~ 삭제에 대한 커밋

### 폴더 및 컴포넌트 구성
<img src='https://github.com/YooJinRa/coffee-n-paste-FE/blob/master/documentImage/directoryFile1.png' width="100px" />
<img src='https://github.com/YooJinRa/coffee-n-paste-FE/blob/master/documentImage/directoryFile2.png' width="100px" />
