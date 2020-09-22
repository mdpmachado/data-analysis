# Sistema de análise de dados
> Teste de conhecimento DBC Company.

Realiza a leitura de um arquivo .dat extraindo os dados e os tratando de acordo com o seu id.
Realiza a transformação dos dados executando determinados calculos para coletar quantidade de clientes e vendedores, venda mais cara e pior vendedor.

![](../header.png)

## Microsserviços

> data-analysis-config

A configuração do Spring Cloud fornece suporte para configuração de externalização em sistemas distribuídos. Com o Config Server, você tem um local central para gerenciar propriedades externas para aplicativos em todos os ambientes.

Você pode ler em detalhes sobre a configuração do Spring Cloud aqui - http://cloud.spring.io/spring-cloud-config/

> data-analysis-eureka

 Os microsserviços são parecidos com a plataforma SOA, onde existem vários serviços. Cada serviço, quando está online, se registra no Service Registry. Quando algum outro serviço deseja se comunicar com um serviço já registrado, eles perguntam ao servidor Eureka o url de base desse serviço. Várias instâncias do mesmo serviço podem ser registradas no Eureka; nesse caso, o Eureka pode ajudar no balanceamento de carga.

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


## Exemplo de uso

*data-analysis-config

*data-analysis-eureka

*data-analysis-zuul

*data-analysis-extraction

*data-analysis-transformation

*data-analysis-zuul

## Configuração para Desenvolvimento

mvn clean package install
