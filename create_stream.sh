#!/bin/sh
#chmod +x *.sh

filename=`basename "$0"`

echo "INFO: running $filename "$@""

docker exec -it dataflow-server java -jar shell.jar

app register   --name processor-service --type processor --uri file://root/scdf/processor-service-0.0.6-SNAPSHOT.jar

app register  --name sink-service --type     sink --uri file://root/scdf/sink-service-0.0.3-SNAPSHOT.jar

app register  --name source-service --type source --uri file://root/scdf/source-service-0.0.12-SNAPSHOT.jar

stream create --definition "source-service | processor-service | sink-service" --name demo-stream

stream create --definition "source-service --test-file-name=test01.txt | processor-service --bad-word=bug | sink-service --test-file-name=test01.txt" --name demo-stream

stream deploy --name demo-stream