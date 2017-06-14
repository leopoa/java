
### Criado uma API fake, para ser consumida pelo nosso endpoint:
1. Rodar main do arquivo: FakeRestController
2. Testar a API no ar: http://localhost:8090/fake

### Criado um endpoint que consome a API acima e mostra o resultado:
1. Rodar main do arquivo: RestController
2. Testar a API no ar: http://localhost:8080/

  * O retorno de sucesso exibe na tela: "Bombou!"
  * Em caso de erro ao chamar nossa API fake (derrubar a API do FakeRestController) aciona o fallback (hystrix) e exibe na tela: "Fora do ar!"

### Hystrix Dashboard
* http://localhost:8080/hystrix/ 
* Adicione a seguinte informação: http://localhost:8080/hystrix.stream
