# language: pt

@regressivo
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido do semaforo

  Cenario: Validar contrato do cadastro bem-sucedido do semaforo
    Dado que eu tenha os seguintes dados do semáforo:
      | campo               | valor               |
      | localizacao         | Av. Paulista        |
      | estadoAtual         | verde               |
      | tempoVerde          | 30                  |
      | tempoVermelho       | 45                  |
      | condicoesClimaticas | Ensolarado          |
      | ultimaAtualizacao   | 2024-10-27T23:22:27 |
    Quando eu enviar a requisicao para o endpoint "/semaforos"
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado e o "Cadastro bem-sucedido do semaforo"
    Então a resposta da requisicao deve estar em conformidade com o contrato selecionado