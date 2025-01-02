APP_NAME:=ddd-foundation
VERSION:=$(shell cat build.gradle | grep "version =" | awk -F"'" '{print $$2}')

.PHONY: build

install-kind-linux:
	@curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.25.0/kind-linux-amd64
	@chmod +x ./kind
	@sudo mv ./kind /usr/local/bin/kind

create-cluster:
	@kind create cluster --name ddd-foundation --config k8s/cluster/kind-cluster.yaml

delete-cluster:
	@kind delete cluster --name ddd-foundation

run-local-database:
	@docker run --rm -d --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:8.0.27

delete-local-database:
	@docker stop mysql

install-database:
	@kubectl apply -f k8s/database/namespace.yaml
	@kubectl apply -f k8s/database/mysql-configmap.yaml
	@kubectl apply -f k8s/database/mysql-service.yaml
	@kubectl apply -f k8s/database/mysql-persistentvolume.yaml
	@kubectl apply -f k8s/database/mysql-statefulset.yaml

init-database:
	@mysql -h 127.0.0.1 -u root < src/sql/ddl.sql
	@mysql -h 127.0.0.1 -u root < src/sql/init.sql

uninstall-database:
	@kubectl delete -f k8s/database/mysql-statefulset.yaml || true
	@kubectl delete pvc data-mysql-0 -n database || true
	@kubectl delete pvc data-mysql-1 -n database || true
	@kubectl delete -f k8s/database/mysql-persistentvolume.yaml || true
	@kubectl delete -f k8s/database/mysql-service.yaml || true
	@kubectl delete -f k8s/database/mysql-configmap.yaml || true
	@kubectl delete -f k8s/database/namespace.yaml || true

build:
	@./gradlew clean build --no-daemon -x test

build-container-image:
	@docker build -t ddd-foundation:latest .

load-container-image:
	@kind load docker-image ddd-foundation:latest --name ddd-foundation

prepare-data:
	@echo "Prepare data before moving on..."

deploy-application:
	@kubectl apply -f k8s/application/namespace.yaml
	@kubectl apply -f k8s/application/deployment.yaml
	@kubectl apply -f k8s/application/service.yaml

undeploy-application:
	@kubectl delete -f k8s/application/service.yaml
	@kubectl delete -f k8s/application/deployment.yaml
	@kubectl delete -f k8s/application/namespace.yaml

install-helm:
	@echo "Install Helm as per your OS"

add-rabbitmq-helm-repo:
	@helm repo add bitnami https://charts.bitnami.com/bitnami
	@helm repo update

install-rabbitmq:
	@helm install rabbitmq bitnami/rabbitmq --namespace rabbitmq --create-namespace --wait
	@kubectl get pods -n rabbitmq

show-rabbitmq-credentials:
	@echo "Username: user"
	@echo "Password: $(shell kubectl get secret --namespace rabbitmq rabbitmq -o jsonpath="{.data.rabbitmq-password}" | base64 --decode)"
	@echo "Configure port-forward, and then access to RabbitMQ Management UI URL at: http://localhost:15672"

port-forward-rabbitmq-management-ui:
	@echo "RabbitMQ Management UI is available at: http://localhost:15672"
	@kubectl port-forward --namespace rabbitmq svc/rabbitmq 15672:15672

port-forward-rabbitmq-amqp:
	@echo "RabbitMQ AMQP port is available at: amqp://localhost:5672"
	@kubectl port-forward --namespace rabbitmq svc/rabbitmq 5672:5672
