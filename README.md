# 도메인 주도 개발 시작하기 예제 코드

DDD START!의 재출간판인 [도메인 주도 개발 시작하기](https://www.hanbit.co.kr/store/books/look.php?p_code=B4309942517) 책의 예제 코드입니다.

---

## 변경 사항
* `Maven` -> `Gradle`로 변경
    * 'org.springframework.boot' version '3.4.0'
    * 'io.spring.dependency-management' version '1.1.6'
* Java 21로 포팅
    * The WebSecurityConfigurerAdapter class was removed in Spring Security 5.0. To resolve this issue, you need to update your security configuration to use the new approach with SecurityFilterChain.


## `Java 21` 특이 사항
* [`Java EE`에서 `Jakarta EE`로의 이관](./docs/Java-EE-to-Jakarta-EE.md)
