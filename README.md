# Coffee N Pasete Project
## Project Introduction
### YOUTUBE
[![Coffee N Paste](https://img.youtube.com/vi/hV9tPMfjEeU/0.jpg)](https://youtu.be/hV9tPMfjEeU)

### WHO
#### ๐ป Front-end developer ::: 
- ๐ **๊ฐํํ** [FE๋ฆฌ๋]
  * ๋ฉ์ธํ์ด์ง ๋ค๋น๊ฒ์ด์, ๋ฉ์ธ ์ด๋ฏธ์ง ๊ธฐ๋ฅ ๊ตฌํ, ์์ธํ์ด์ง ๊ฒ์๊ธ Read, ๋๊ธ CRUD, ๋ฌดํ์คํฌ๋กค
- ๐ **๋์ ์ง**
  * ๊ฒ์๊ธ Add, ์ด๋ฏธ์ง ๋ฏธ๋ฆฌ๋ณด๊ธฐ, ์ด๋ฏธ์ง ๋ฆฌ์ฌ์ด์ง, ๋ฉ์๋ฆฌ๋ ๋ ์ด์์, createPotal
- ๐ **๊ณ ํธ์ฑ**
  * ๋ก๊ทธ์ธ*ํ์๊ฐ์ ๋ชจ๋ฌ

#### ๐ป Back-end developer ::: 
- ๐ **๋ฐ์์ฑ** [BE๋ฆฌ๋]
- ๐ **๊นํ๋ฆผ** [์ด๊ด]
- ๐ **๊นํ์ค**
- ๐ **์ต์ค์ฐ**

### WHEN
- 2022.08.12 ~ 2022.08.18

### WHAT
- Web Service 'Coffee N Paste'
- ::: ์ปคํผ ํ๋์ฐจ์ด์ฆ ๋ฉ๋ด๋ณ ๋ฆฌ๋ทฐ ์ปค๋ฎค๋์ผ์ด์ ์๋น์ค

### ์ฃผ์๊ธฐ๋ฅ
- ๐ป Infinite Scroll 
- ๐ป CreatePotal 
- ๐ป Image Upload 
- ๐ป Image Resizing(Compression) 
- ๐ป LogIn & SignIn 
- ๐ป Post CRUD
- ๐ป Comment CRUD

### Trouble Shooting
### FE

**1. ์ด๋ฏธ์ง ํ๋ฆฌ๋ทฐ ์๋ก๋ ๋ฐ ๋ฆฌ์ฌ์ด์ง, ์๋ฒ ์ ์ก**
- Formdata ํ์, Multi Part ๋ฐ์ดํฐ ํ์์ผ๋ก ์ ์ก ํ์ โ FormData Web API ์ฌ์ฉํด์ ํด๊ฒฐ
- ์ด๋ฏธ์ง ํ๋ฆฌ๋ทฐ ๊ธฐ๋ฅ ๊ตฌํ : ๊ธฐ๋ฅ ๊ตฌํ ํ ํ์ธํด๋ณด๋ ์ด๋ฏธ์ง ์ฉ๋์ด ์ปค์ ์ฌ์ด์ฆ ์ค์ผ ํ์์ฑ์ ๋๋ โ browser-image-compression ํจํค์ง ํ์ฉํ์ฌ ์ด๋ฏธ์ง ์ฌ์ด์ฆ ์กฐ์ 

**2. ๊ฒ์๊ธ ์ด๋ฏธ์ง(AWS S3) ์ ์ก ํ ๊ฒ์๊ธ ์ปจํ์ธ  ์ ์ก**
- ๊ฒ์๊ธ ๋ฑ๋ก ๋ฒํผ์ ๋๋ ์ ๋ ์ด๋ฏธ์ง ๋ฐ ๊ฒ์๊ธ ์ปจํ์ธ ๊ฐ ํ ๋ฒ์ ์ ์ก๋์์ผ๋ฉด ํจ. Async Await ํ์ฉํ์ฌ ์์ฐจ์ ์ผ๋ก ์ ์ก ์งํ

**3. ๋ชจ๋ฌ์ฐฝ ๊ตฌํ์ผ๋ก ์์ธ ๊ฒ์๊ธ ๋ถ๋ฌ์ฌ ๋ ๋๋๋ง ์๋๋ ํ์**
- CreatePortalํ์ฉํ์ฌ ๋ชจ๋ฌ ์ฐฝ ๊ตฌํ :  ๊ธฐ์กด ์์๊ณผ ์ถฉ๋ํ์์ด ์ผ์ด๋ ๋ชจ๋ฌ์ฐฝ ์ปจํ์ธ ์ ์ด๊ธฐ ๋ ๋๋ง์ด ์๋จ โ ์์ ์ปจํฌ๋ํธ๋ก ๋ชจ๋ฌ์ฐฝ์ ์ด๋์์ผ ํด๊ฒฐ

**4. ๋ฌดํ ์คํฌ๋กค ๊ฒ์๊ธ ์ค๋ณต ํ์**
- ๋ฉ๋ด๋ณ ์นดํ๊ณ ๋ฆฌ ๋ถ๋ฅ์ ์์ด ์ด๋ ค์์ด ์์์ : 


__์ด ์ธ์๋ ์๋ฌ๋ ๋งค์ผ ์์์โฆ..!__

### BE

**1. member ๊ฐ์ฒด๋ฅผ ์ด์ฉํ์ฌ Authentication ๊ฐ์ฒด ๋ง๋ค๊ธฐ**
    
```java
    // ์ 
    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(requestDto.getNickname(), requestDto.getPassword());
    
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    
    TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
    
    // ํ
    UserDetailsImpl userDetails = new UserDetailsImpl(member);
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "");
    
    TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
```
    

**2. ๋ฐ๊ธํ ํ ํฐ์ ํค๋์ ๋ด์์ ํด๋ผ์ด์ธํธ์๊ฒ ๋ณด๋ด๋ฉด ๋ธ๋ผ์ฐ์ ๊น์ง๋ ๋๋ฌํ๋๋ฐ Axios response๊น์ง๋ ๋ค์ด๊ฐ์ง ์์์ responseBody์ ํ ํฐ์ ๋ด์์ ๋ณด๋ด๊ธฐ**
    
```java
    TokenDto token = memberService.login(loginRequestDto);
    
    // ์ 
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token.getAuthorization());
    headers.add("Refresh-Token", token.getRefreshToken());
    
    return ResponseEntity.ok()
    					.headers(headers)
    					.body(Map.entry("msg", "๋ก๊ทธ์ธ ์๋ฃ"));
    
    // ํ
    return ResponseEntity.ok()
    					.body(token);
```
    

**3. CORS ๊ฐ๋ฅํ๊ฒ ํ๊ธฐ**
    
```java
    // ์ 
    configuration.setAllowedOrigins(Arrays.asList("*"));
    
    // ํ
    configuration.setAllowedOrigin**Patterns**(Arrays.asList("*"));
```
    

**4. JWT ํ ํฐ Provider์์ bearer๋ก ์์ฑํ๊ณ  Filter์์ Bearer๋ก ๊ฒ์ฆํ๋ ๊ฒ์ผ๋ก ๋์ด์์์ด์ ๋ ๋ค Bearer๋ก ํต์ผ**
    
```java
    // ์ 
    private static final String BEARER_TYPE = "bearer";
    
    // ํ
    private static final String BEARER_TYPE = "Bearer";
    
    return TokenDto.builder()
                    .authorization(BEARER_TYPE + " " + accessToken)
                    .refreshToken(refreshToken)
                    .build();
```
    

**5. ๋ก๊ทธ์ธ ์๋๋ง๋ค Refresh Token์ด ๋ฐ์ดํฐ๋ฒ ์ด์ค์ ์ ์ฅ๋์ด์ ์ ์ฅ๋ ๊ฐ์ ํ์ธํ๋๋ก ํด์ ์ ์  ํ ๋ช์ Refresh Token ํ๋์ฉ ์ ์ฅ๋๋๋ก ๋ณ๊ฒฝํ๊ธฐ**
    
```java
    String refreshToken = Jwts.builder()
                    .setExpiration(new Date(now + REFRESH_TOKEN_EXPRIRE_TIME))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
    
    //--- ์ 
    RefreshToken refreshTokenObject = RefreshToken.builder()
                    .member(member)
                    .tokenValue(refreshToken)
                    .build();
    //---
    
    //--- ํ
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
    

**6. ๊ฒ์๋ฌผ์ด 9์๊ฐ ์ด์ ์ผ๋ก ๋ฑ๋ก๋๋ ๋ฌธ์  ํด๊ฒฐํ๊ธฐ**

```shell
   java -Duser.timezone=GMT+9 -jar ํ๋ก๊ทธ๋จ
```

### ํจํค์ง
- ์คํ์ผ ์ ์ฉ : `styled-components`
- s3 ์ฐ๊ฒฐ : `aws-sdk`
- json-server (์๋ฒ) ์ค์น(ํ์คํธ์ฉ) : `yarn add json-server`
- ๋ฆฌ๋์ค ์ค์น : `yarn add react-redux`
- ํดํท (๋ฆฌ๋์ค) ์ค์น : `yarn add @reduxjs/toolkit`
- thunk (๋ฏธ๋ค์จ์ด) ์ค์น : `yarn add redux-thunk`
- axios(ํต์ ) ์ค์น : `yarn add axios` 
- logger (๊ฐ๋ฐ ํธํ๊ฒ ๋์์ค) ์ค์น : `yarn add redux-loger`
- image resizing : `yarn add browser-image-compression`
- masorny ๋ ์ด์์ ๊ธฐ๋ฅ ๊ตฌํ : `yarn add imagesloaded` (DOM์ ๋ถ์ฐฉ๋ ์ด๋ฏธ์ง ๋ก๋ ์์  ์๊ธฐ ์ํด ์ฌ์ฉ)

### git commit message ๊ท์น
- ํ์ผ ์์ฑ โจnew : ~~~ ์์ฑ์ ๋ํ ์ปค๋ฐ
- ํ์ผ ์๋ฐ์ดํธ ๐update : ~~~ ์ถ๊ฐ์ ๋ํ ์ปค๋ฐ
- ํ์ผ ์์  โfix : ~~~ ์์ ์ ๋ํ ์ปค๋ฐ
- ํ์ผ ์ญ์  ๐delete : ~~~ ์ญ์ ์ ๋ํ ์ปค๋ฐ

### ํด๋ ๋ฐ ์ปดํฌ๋ํธ ๊ตฌ์ฑ
<img src='https://github.com/YooJinRa/coffee-n-paste-FE/blob/master/documentImage/directoryFile1.png' width="100px" />
<img src='https://github.com/YooJinRa/coffee-n-paste-FE/blob/master/documentImage/directoryFile2.png' width="100px" />
