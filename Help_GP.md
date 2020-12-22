# SpringDataFlowDemo with Prometheus/Grafana monitoring configuration


Spring Cloud Data Flow Demo

###### 1. Скачать Spring Cloud Data Flow Docker Compose (так же он есть в корне проекта)

> https://dataflow.spring.io/docs/installation/local/docker/
 
###### 2. Запустить Spring Cloud Data Flow Server

> export HOST_MOUNT_PATH=/SpringDataFlowDemo/jars  - укажите переменную HOST_MOUNT_PATH если вам нужны кастомные приложения. Должна указывать на папку с jar файлами ваших кастомных source processor и sink приложений

> export DATAFLOW_VERSION=2.7.0  - укажите версию Spring Cloud Data Flow

> export SKIPPER_VERSION=2.6.0  - укажите версию skipper

> docker-compose -f ./docker-compose.yml -f ./docker-compose-prometheus.yml up   - выполните docker-compose

###### 3. Запустить Spring Cloud Data Flow shell






https://dataflow.spring.io/docs/installation/local/docker-customize/#prometheus--grafana