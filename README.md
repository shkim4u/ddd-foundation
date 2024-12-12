# 도메인 주도 개발 시작하기 예제 코드

DDD START!의 재출간판인 [도메인 주도 개발 시작하기](https://www.hanbit.co.kr/store/books/look.php?p_code=B4309942517) 책의 예제 코드입니다.

---

## 변경 사항
1. 프로젝트 이름: `ddd-foundation`
2. `Maven` -> `Gradle`로 변경
3. `application.properties` -> `application.yaml`로 변경
4. JPA Module이 설정되었는지 확인
   * IntelliJ IDEA에서 File → Project Structure → Modules로 이동합니다.
   * 사용 중인 모듈이 "JPA"로 설정되어 있는지 확인합니다. 
     * 모듈 우클릭 → Add Framework Support → "JPA"를 추가합니다.
5. `JPA Buddy` 간단 사용
   * DDL 생성
   * ERD 생성
6. `Kind`를 이용한 로컬 `Kubernetes` 클러스터 구성
7. 쿠버네테스 (`k8s`) 클러스터에 `MySQL` 배포
8. `DDD Shop` 배포

---

### 사전 준비 사항

1. `Java 17` 설치
2. `Git` 설치
3. `IntelliJ IDEA` 설치
4. `Docker Desktop` 설치
   * 윈도우의 경우 `WSL2` 설치
5. `Kind` 설치
6. `kubectl` 설치
7. `k9s` 설치

---

## 단독 실행 테스트

1. 데이터베이스 실행

* Windows

```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v c:\work\mysqldata:/var/lib/mysql mysql:8.0.27
docker start mysql
```

* MacOS, Linux
```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v ~/data/mysql:/var/lib/mysql mysql:8.0.27
docker start mysql
```

2. `IntelliJ IDEA`에서 `DDD Foundation` 애플리케이션 실행

---

## 로컬 쿠버네테스 클러스터 (`Kind`)를 이용한 배포

1. 쿠버네테스 클러스터 생성

```bash
make cluster
```

2. 데이터베이스 (`MySQL`) 배포

```bash
make install-database
```

3. 소스 빌드

```bash
make build-source
```

4. 컨테이너 이미지 빌드

```bash
make build-container-image
```
5. 컨테이너 이미지 노드에 적재

```bash
make load-container-image
````

6. 데이터베이스 스키마 및 초기 데이터 생성

> 📌(참고)📌<br>
> `IntelliJ` 혹은 데이터베이스 관리 도구를 사용하여 데이터베이스 스키마를 생성하고 초기 데이터를 입력합니다.

7. `DDD Foundation` 애플리케이션 배포

```bash
make deploy-application
```

---

## 자원 정리
1. `Kind` 클러스터 삭제

```bash
kind delete cluster --name metsys-cluster
```


## 추가 수행 사항
1. `Flyway`를 이용한 데이터베이스 관리
