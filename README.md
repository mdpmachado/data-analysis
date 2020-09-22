# Sistema de análise de dados
> Teste de conhecimento DBC Company.

Realiza a leitura de um arquivo .dat extraindo os dados e os tratando de acordo com o seu id.
Realiza a transformação dos dados executando determinados calculos para coletar quantidade de clientes e vendedores, venda mais cara e pior vendedor.

![](../header.png)

## Instalação

> data-analysis-config

> data-analysis-eureka

 Os microsserviços são parecidos com a plataforma SOA, onde existem vários serviços. Cada serviço, quando está online, se registra no Service Registry. Quando algum outro serviço deseja se comunicar com um serviço já registrado, eles perguntam ao servidor Eureka o url de base desse serviço. Várias instâncias do mesmo serviço podem ser registradas no Eureka; nesse caso, o Eureka pode ajudar no balanceamento de carga.

> data-analysis-zuul

Um ambiente de microsserviço precisa de um gateway. Um Gateway é a única entidade exposta ao mundo externo, que permite o acesso a microsserviços e faz mais. Um Gateway poderia fazer

*Medição de API
*Autenticação / autorização centralizada
*Balanceamento de carga
*etc


> data-analysis-extraction

> data-analysis-transformation

> data-analysis-zuul

## Exemplo de uso

Alguns exemplos interessantes e úteis sobre como seu projeto pode ser utilizado. Adicione blocos de códigos e, se necessário, screenshots.

_Para mais exemplos, consulte a [Wiki][wiki]._ 

## Configuração para Desenvolvimento

mvn clean package install
