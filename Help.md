# SpringDataFlowDemo

Spring Cloud Data Flow Demo

###### 1. Скачать Spring Cloud Data Flow Docker Compose (так же он есть в корне проекта)

> https://dataflow.spring.io/docs/installation/local/docker/

###### 2. Запустить Spring Cloud Data Flow Server

> export HOST_MOUNT_PATH=/SpringDataFlowDemo/jars  - укажите переменную HOST_MOUNT_PATH если вам нужны кастомные приложения. Должна указывать на папку с jar файлами ваших кастомных source processor и sink приложений

> export DATAFLOW_VERSION=2.7.0  - укажите версию Spring Cloud Data Flow

> export SKIPPER_VERSION=2.6.0  - укажите версию skipper

> docker-compose up   - выполните docker-compose

###### 3. Запустить Spring Cloud Data Flow shell

> Если вы запустили Spring Cloud Data Flow используя Docker Compose, то shell уже включен в springcloud/spring-cloud-dataflow-server Docker образ.

> docker exec -it dataflow-server java -jar shell.jar

> Так же вы можете добавить внешние приложение в админке http://localhost:9393/dashboard

###### 4. Зарегистрировать все 3 микросервиса в Spring Cloud Data Flow Server

> docker exec dataflow-server ls /root/scdf    -  проверить, появились ли в докере необходимые приложения 

> app register   --name processor-service --type processor --uri file://root/scdf/processor-service-0.0.1-SNAPSHOT.jar

> app register  --name sink-service --type     sink --uri file://root/scdf/sink-service-0.0.1-SNAPSHOT.jar

> app register  --name source-service --type source --uri file://root/scdf/source-service-0.0.1-SNAPSHOT.jar

###### 5. Создать стрим 

> stream create --definition "source-service | processor-service | sink-service" --name test-stream

###### 6. Start и Deploy Stream(а)

> stream deploy --name test-stream   - или это можно сделать иак же в админке dataflow


______________________________________________

