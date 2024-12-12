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

install-database:
	@kubectl apply -f k8s/database/namespace.yaml
	@kubectl apply -f k8s/database/mysql-configmap.yaml
	@kubectl apply -f k8s/database/mysql-service.yaml
	@kubectl apply -f k8s/database/mysql-persistentvolume.yaml
	@kubectl apply -f k8s/database/mysql-statefulset.yaml

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

undeploy-application:
	@kubectl delete -f k8s/application/deployment.yaml
	@kubectl delete -f k8s/application/namespace.yaml