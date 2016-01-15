API Star Wars: https://swapi.co/documentation

# Missão 1
"O Império começou a atacar os planetas afim de assumir o controle da galáxia. 
Estamos nos organizando para formar a resistência e sua missão está no áudio abaixo. 
Boa sorte recruta!" - Luke Skywalker

<b>O planeta Tatooine está sendo atacado e sua missão é evacuar todos dessa lista, o mais rápido possível.</b>

## Objetivos:
Você vai receber uma lista de identificação de pessoas e precisa agrupar elas.
- Separe os grupos por espécies para preparar a fuga deles;
- Podem haver números repetidos, nesses casos serão considerados clones, irmãos ou da famíia;
- Exemplo de input entrada: 1,42,5,5,1,45,33,50,55,66,67,68,80

Para ajudar você a levar as pessoas até a nave, você precisará programar quantas viagens o veículo Corporate Alliance Tank Droid deverá fazer para levar todos
- Cada viagem do veículo só poderá levar pessas da mesma espécie;
- O veículo tem uma capacidade máxima, então calcule quantas viagens serão necessárias para cada espécie;
- http://swapi.co/api/vehicles/72/

Os sábios de cada espécie devem ter prioridade
- Descubra o mais velho de cada espécie e esses devem ter prioridade de fuga;
- Essa é a única exceção de enviar um veículo com diferentes espécies juntas, se forem todos os mais velhos de cada espécie;
- Quantas viagens são necessárias para levar todos os sábios primeiro, antes de todos os outros;

Fique atento, dependendo do peso da pessoa, ela ocupa mais espaço impedindo de usar capacidade máxima do veículo
- Independente da espécie, se tiver mais de 100kg (mass) essa pessoa vai ocupar 2 lugares no veículo;
- Se o peso for desconhecido, assuma que é mais de 100kg;

Se houvesse um veículo somente para as mulheres humanas
- Calcule quantos viagens seriam necessárias para levar somente as mulheres;
