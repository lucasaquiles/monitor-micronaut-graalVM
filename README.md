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