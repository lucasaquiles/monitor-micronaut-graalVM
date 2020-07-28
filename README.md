## Monitor

Build com GraalVM usando micronaut

O projeto foi criado usando o profile `--features=graalvm`, isso gera os scripts `docker-build.sh` e as configs `native-image.properties` para personalizar a geração da imagem e incrementa o Dockerfile com os stages de build pra GraalVM

O `Dockerfile` do projeto também foi incrementado com o esquema de multi-stage do Docker

o comando `native-image --no-server -cp *.jar` vai processar as classes e dependências associadas no projeto, verificando quais classes, métodos e recursos são entendidos e usados durante a execução do App e depois passa todas as classes e métodos que foram reconhecidos como input ao compilador da GraalVM que compila para código nativo 

```
./gradlew assemble
sh docker-build.sh
```

o comando vai gerar uma imagem docker chamada `monitor` expondo a porta `8080`

```
docker run -p 8080:8080 monitor
```

fazendo requisições

```
time curl localhost:8080/message
```

```
siege -c5000 -t60S --content-type 'application/json' 'http://localhost:8080/message POST {"dateCreated":"2020-07-28T16:45:00", "content":"teste teste teste"}' -p
```

```
time curl localhost:8080/metrics
```

```
time curl localhost:8080/prometheus
```

a partir da dependência `implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")` é possível coletar métricas da aplicação e indexar no prometheus via registry do micrometer que pode ser consultada pelo path `/prometheus`. 

Por default o prometheus usa o path `/metrics` para fazer o scrap das metricas, isso gera conflito com o core de gerenciamento de metricas do micronautic. No arquivo `prometheus.yml` é criado uma nova config informando o endpoint que deve ser consultado pelo prometheus para fazer scrap das métricas registradas

`docker-compose -f prometheus-compose.yml up -d`

```
http://localhost:9090
``` 