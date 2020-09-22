# Sistema de análise de dados
> Teste de conhecimento DBC Company.

Realiza a leitura de um arquivo .dat extraindo os dados e os tratando de acordo com o seu id, fazendo a transformação dos dados e executando determinados calculos para coletar quantidade de clientes e vendedores, venda mais cara e pior vendedor para o relatório de saída. 

## Microsserviços

> data-analysis-config

Responsável pelo geranciamento das propriedades externas de cada aplicação.

> data-analysis-eureka

Responsável pelos registros criados. <br>
Cada serviço, quando está online, se registra no Service Registry. Quando algum outro serviço deseja se comunicar com um serviço já registrado, eles perguntam ao servidor Eureka o url de base desse serviço.

> data-analysis-zuul

Um ambiente de microsserviço precisa de um gateway. Um Gateway é a única entidade exposta ao mundo externo, que permite o acesso a microsserviços e faz mais. Um Gateway poderia fazer

*Medição de API<br>
*Autenticação / autorização centralizada<br>
*Balanceamento de carga<br>
*etc<br>

> data-analysis-extraction

Responsável pela leitura do arquivo arquivo de entrada .dat e extração dos dados de acordo com o id.

Exemplo arquivo de entrada.<br>
001;1234567891234;Pedro;50000.<br>
002;2345675433444345;Eduardo Pereira;Rural.<br>
003;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo.<br>

> data-analysis-transformation

Responsável por realizar os calculos de cada indicador.

> data-analysis-report

Responsável por gerar o relatório .done.dat.


## Configuração para Desenvolvimento

Criar diretorio e arquivo de entrada.<br>
${user.home}\data\in<br>

Criar diretorio para arquivo de saída.<br>
${user.home}\data\out

Alterar no application.yml do serviço \data-analysis-config\ o diretorio onde o \data-analysis-config-repo\ se encontra.<br>
config:server:native:search-locations: file:///C:/projetos/test-dbc/data-analysis-config-repo/localhost/<br>

Excecutar o seguinte comando do maven em cada aplicação 

* mvn clean package install

> Procedimento para instalação do Kafka

1 - Realizar o download do Kafka<br>
http://ftp.unicamp.br/pub/apache/kafka/2.6.0/kafka_2.12-2.6.0.tgz

2 - Extrair o diretório

3 - Entrar no diretorio principal e abrir duas abas do terminal, e executar os seguintes comandos em cada aba.<br>
```sh
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties 
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

> Procedimento para subir a aplicação utilizando o Docker

Excutar pelo terminal do docker os serguinte comandos.

```sh
docker network create dbc

-- Entrar no diretório do serviço e executar 
docker build -t data-analysis-config-server . 
docker run --name data-analysis-config-server --network=dbc -p 8888:8888 -d data-analysis-config-server
docker logs data-analysis-config-server

-- Entrar no diretório do serviço e executar
docker build -t data-analysis-eureka-server . 
docker run --name data-analysis-eureka-server --network=dbc -p 8761:8761 -d data-analysis-eureka-server
docker logs data-analysis-eureka-server

-- Entrar no diretório do serviço e executar
docker build -t data-analysis-zuul-server . 
docker run --name data-analysis-zuul-server --network=dbc -p 8000:8000 -d data-analysis-zuul-server
docker logs data-analysis-zuul-server

-- Entrar no diretório do serviço e executar
docker build -t data-analysis-extaction . 
docker run --name data-analysis-extaction --network=dbc -p 8181:8181 -d data-analysis-extaction
docker logs data-analysis-extaction

-- Entrar no diretório do serviço e executar
docker build -t data-analysis-tranformation . 
docker run --name data-analysis-tranformation --network=dbc -p 8183:8183 -d data-analysis-tranformation
docker logs data-analysis-tranformation

-- Entrar no diretório do serviço e executar
docker build -t data-analysis-report . 
docker run --name data-analysis-report --network=dbc -p 8182:8182 -d data-analysis-report
docker logs data-analysis-report

```
