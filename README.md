# ***도메인 주도 개발 시작하기 예제 코드***

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

## 사전 준비 사항

1. `Java 17` 설치
2. `Git` 설치
3. `IntelliJ IDEA` 설치
4. `Docker Desktop` 설치
   * 윈도우의 경우 `WSL2` 설치
5. `Kind` 설치
6. `kubectl` 설치
7. `k9s` 설치

---

## 1. **단독 실행 테스트**

1. 데이터베이스 실행

* Windows

```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v c:\work\mysqldata:/var/lib/mysql mysql:8.0.27
docker start mysql
```

* MacOS, Linux
```bash
rm -rf ~/data/mysql/*
docker run --rm --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v ~/data/mysql:/var/lib/mysql mysql:8.0.27
#docker start mysql
```

2. `IntelliJ IDEA`에서 `DDD Foundation` 애플리케이션 실행

---

## 2. **쿠버네테스 클러스터 (`Kind`)를 이용한 배포**

### 2.1. **툴셋 설정 및 쿠버네테스 클러스터 생성**

```bash
make install-tools helm-add-repos
make create-cluster
```

### 2.2. **데이터베이스 배포 (`MySQL`)**

```bash
make install-database-k8s
```

### 2.3. ** RabbitMQ 배포 및 설치**

1. `RabbitMQ` 배포

```bash
make install-rabbitmq-k8s
```

2. `RabbitMQ` 관리 UI 포트 포워딩

```bash
make port-forward-rabbitmq-management-ui
```

3. **RabbitMQ Exchange 및 Queue 생성**

> 📌(참고)📌<br>
> 위에서 Port Forwarding을 수행한 터미널이 아닌 새로운 터미널을 열어서 아래 명령어를 수행합니다.

```bash
make download-rabbitmqadmin
make configure-rabbitmq-exchange-queue
```

4. **RabbitMQ 관리 UI 포트 포워딩 종료**

앞서 실행한 `make port-forward-rabbitmq-management-ui` 명령어를 종료합니다 (Ctrl + C).


### 2.4. **인그레스 설정**

1. `Nginx Ingress Controller` 설치

```bash
make install-nginx-ingress-controller
````

### 2.5. **애플리케이션 빌드 및 배포**

1. 소스 빌드

```bash
make build
```

2. 컨테이너 이미지 빌드

```bash
make build-container-image
```

3. 빌드된 컨테이너 이미지 노드에 적재

```bash
make load-container-image
````

4. `DDD Foundation` 애플리케이션 배포

```bash
make deploy-application
```

5. 테스트

```bash
curl -fsSL http://localhost
```

6. (참고) `DDD Foundation` 애플리케이션 배포 삭제

```bash
make undeploy-application
```

---

## 3. **자원 정리**
1. `Kind` 클러스터 삭제

```bash
kind delete cluster --name metsys-cluster
```

---

## 4. **추가 수행 사항**
1. (DONE) `Flyway`를 이용한 데이터베이스 관리
2. (DONE) `RabbitMQ`를 이용한 이벤트 드리븐 아키텍처 구현
   * `Avro` 스키마 파일 Git Submodule로 추가
3. (DONE) `인벤토리 서비스 애플리케이션` 분리
   * Gradle 멀티 프로젝트
   * Polyglot 데이터베이스 (PostgreSQL)
   * 멀티 스테이지 컨테이너 빌드
4. `GitOps`를 이용한 애플리케이션 배포
   * `ArgoCD` 혹은 `Flux`
   * `Argo Rollouts`를 이용한 배포 전략 적용 - Canary, Blue-Green, A/B Testing 등
5. (TODO) `TDD (Test-Driven Development)` 및 테스트 자동화
6. (TODO) 코드 커버리지 목표 설정 및 방법론 적용
7. (TODO) 성능/부하 테스트
8. (TODO) 점진적 전환 전략 및 기술 체계
