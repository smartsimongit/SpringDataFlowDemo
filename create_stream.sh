#!/bin/sh
#chmod +x *.sh

filename=`basename "$0"`

echo "INFO: running $filename "$@""

docker exec -it dataflow-server java -jar shell.jar

app register   --name processor-service --type processor --uri file://root/scdf/processor-service-0.0.1-SNAPSHOT.jar

app register  --name sink-service --type     sink --uri file://root/scdf/sink-service-0.0.1-SNAPSHOT.jar

app register  --name source-service --type source --uri file://root/scdf/source-service-0.0.1-SNAPSHOT.jar

stream create --definition "source-service | processor-service | sink-service" --name demo-stream

stream deploy --name demo-stream