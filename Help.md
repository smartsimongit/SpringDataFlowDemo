# SpringDataFlowDemo

Spring Cloud Data Flow демо с кафкой

###### Выполните следующие шаги

###### 1. Apache-Kafka Binary Дистрибутив [Download](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.6.0/kafka_2.13-2.6.0.tgz).

> $ tar -xzf kafka_2.13-2.6.0.tgz 
> 
> $ cd kafka_2.13-2.6.0

###### 2. Запустить Zookeeper сервер 

> ./bin/zookeeper-server-start.sh $HOME/kafka_2.13-2.6.0/config/zookeeper.properties

###### 3. Запустить Kafka сервер 

> ./bin/kafka-server-start.sh $HOME/kafka_2.13-2.6.0/config/server.properties

###### 4. Скачать Spring Cloud Data Flow Docker Compose: стандартная конфигурация и Server Jars

> https://dataflow.spring.io/docs/installation/local/docker/

 или

> https://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-server/2.7.0/spring-cloud-dataflow-server-2.7.0.jar


###### 5. Запустить Spring Cloud Data Flow Server 

> export DATAFLOW_VERSION=2.7.0
> export SKIPPER_VERSION=2.6.0
> docker-compose up

или

> java -jar spring-cloud-dataflow-server-2.7.0.jar

###### 6. Скачать Spring Cloud Data Flow Shell jar  

> wget -O spring-cloud-dataflow-shell-2.7.0.jar https://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-shell/2.7.0/spring-cloud-dataflow-shell-2.7.0.jar

или

> Если вы запустили Spring Cloud Data Flow используя Docker Compose, то shell уже включен в springcloud/spring-cloud-dataflow-server Docker образ.

###### 7. Запустить Spring Cloud Data Flow shell  

> java -jar  spring-cloud-dataflow-shell-2.7.0

или

> docker exec -it dataflow-server java -jar shell.jar 

###### 8. Зарегистрировать все 3 микросервиса в Spring Cloud Data Flow Server   

Общий пример:

> app register --type source --name my-app --uri file://root/scdf/my-app-1.0.0.RELEASE.jar

Пример для SpringDataFlowDemo

> app register --name processor-service --type source --uri maven:// 
> app register --name processor-service --type source --uri file://home/smartsimon/SpringDataFlowDemo/processor-service/target/processor-service.jar 

> app register --name sink-service --type processor --uri maven:// 
> app register --name sink-service --type processor --uri file://home/smartsimon/SpringDataFlowDemo/sink-service/target/sink-service.jar

> app register --name source-service --type sink --uri maven:// 
> app register --name source-service --type sink --uri file://home/smartsimon/SpringDataFlowDemo/source-service/target/source-service.jar

###### . Start и Deploy Stream(а) 

> stream deploy --name log-data

______________________________________________

