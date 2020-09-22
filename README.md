# Sistema de análise de dados
> Teste de conhecimento DBC Company.

Realiza a leitura de um arquivo .dat extraindo os dados e os tratando de acordo com o seu id.
Realiza a transformação dos dados executando determinados calculos para coletar quantidade de clientes e vendedores, venda mais cara e pior vendedor.

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


## Exemplo de uso

*data-analysis-config

*data-analysis-eureka

*data-analysis-zuul

*data-analysis-extraction

*data-analysis-transformation

*data-analysis-zuul

## Configuração para Desenvolvimento

mvn clean package install
