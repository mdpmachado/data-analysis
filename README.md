# Sistema de análise de dados
> Teste de conhecimento DBC Company.

Realiza a leitura de um arquivo .dat extraindo os dados e os tratando de acordo com o seu id, fazendo a transformação dos dados e executando determinados calculos para coletar quantidade de clientes e vendedores, venda mais cara e pior vendedor para o relatório de saída. 

![](../header.png)

## Microsserviços

> data-analysis-config

Responsável pelo geranciamento das propriedades externas de cada aplicação.

> data-analysis-eureka

Responsável pelos registros criados. 
Cada serviço, quando está online, se registra no Service Registry. Quando algum outro serviço deseja se comunicar com um serviço já registrado, eles perguntam ao servidor Eureka o url de base desse serviço.

> data-analysis-zuul

Um ambiente de microsserviço precisa de um gateway. Um Gateway é a única entidade exposta ao mundo externo, que permite o acesso a microsserviços e faz mais. Um Gateway poderia fazer

*Medição de API
*Autenticação / autorização centralizada
*Balanceamento de carga
*etc

> data-analysis-extraction

Responsável pela leitura e extração dos dados de acordo com o id.

> data-analysis-transformation

Responsável por realizar os calculaos para cada indicador.

> data-analysis-report

Responsável por gerar o relatório.


## Configuração para Desenvolvimento

Excecutar o seguinte comando do maven para cada aplicação 

*mvn clean package install

> DOCKER

Excutar pelo terminal do docker os serguinte comandos.

docker network create dbc

docker build -t data-analysis-config-server .
docker run --name data-analysis-config-server --network=dbc -p 8888:8888 -d data-analysis-config-server

docker build -t data-analysis-eureka-server .
docker run --name data-analysis-eureka-server --network=dbc -p 8761:8761 -d data-analysis-eureka-server

docker build -t data-analysis-zuul-server .
docker run --name data-analysis-zuul-server --network=dbc -p 8000:8000 -d data-analysis-zuul-server

docker build -t data-analysis-extaction .
docker run --name data-analysis-extaction --network=dbc -p 8181:8181 -d data-analysis-extaction

docker build -t data-analysis-tranformation .
docker run --name data-analysis-tranformation --network=dbc -p 8183:8183 -d data-analysis-tranformation

docker build -t data-analysis-report .
docker run --name data-analysis-report --network=dbc -p 8182:8182 -d data-analysis-report



