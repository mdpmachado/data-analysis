# Sistema de análise de dados
> <b>Teste de conhecimento DBC Company</b>.

Realiza a leitura de um arquivo .dat extraindo os dados e os tratando de acordo com o seu id, fazendo a transformação dos dados e executando determinados calculos para coletar quantidade de clientes e vendedores, venda mais cara e pior vendedor para o relatório de saída. 

## Microsserviços

> <b>data-analysis-config</b>

Responsável pelo geranciamento das propriedades externas de cada aplicação.

> <b>data-analysis-eureka</b>

Responsável pelos registros criados. <br>
Cada serviço, quando está online, se registra no Service Registry. Quando algum outro serviço deseja se comunicar com um serviço já registrado, eles perguntam ao servidor Eureka o url de base desse serviço.

> <b>data-analysis-zuul</b>

Um ambiente de microsserviço precisa de um gateway. Um Gateway é a única entidade exposta ao mundo externo, que permite o acesso a microsserviços e faz mais. Um Gateway poderia fazer

*Medição de API<br>
*Autenticação / autorização centralizada<br>
*Balanceamento de carga<br>
*etc<br>

> <b>data-analysis-extraction</b>

Responsável pela leitura do arquivo arquivo de entrada .dat e extração dos dados de acordo com o id.

Exemplo arquivo de entrada.<br>
001;1234567891234;Pedro;50000.<br>
002;2345675433444345;Eduardo Pereira;Rural.<br>
003;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo.<br>

> <b>data-analysis-transformation</b>

Responsável por realizar os calculos de cada indicador.

> <b>data-analysis-report</b>

Responsável por gerar o relatório .done.dat.


## Configuração para Desenvolvimento

<b>Criar diretorio e arquivo de entrada.</b><br>
${user.home}\data\in<br>

<b>Criar diretorio para arquivo de saída.</b><br>
${user.home}\data\out

<b>Alterar no application.yml do serviço \data-analysis-config\ o diretorio onde o \data-analysis-config-repo\ se encontra.</b><br>
config:server:native:search-locations: file:///C:/projetos/test-dbc/data-analysis-config-repo/localhost/<br>

<b>Excecutar o seguinte comando do maven em cada aplicação </b>

* mvn clean package install

> <b>Procedimento para instalação do Kafka</b>

<b>1 - Realizar o download do Kafka</b><br>
http://ftp.unicamp.br/pub/apache/kafka/2.6.0/kafka_2.12-2.6.0.tgz

<b>2 - Extrair o diretório</b>

<b>3 - Entrar no diretorio principal e abrir duas abas do terminal, e executar os seguintes comandos em cada aba.</b><br>
```sh
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties 
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

> <b>Procedimento para subir a aplicação utilizando o Docker</b>

<b>Excutar pelo terminal do docker os serguinte comandos.</b>

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
