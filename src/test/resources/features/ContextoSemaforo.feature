# language: pt

@regressivo
Funcionalidade: Deletar um semaforo

  Contexto: Cadastro bem-sucedido do semaforo
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

  Cenario: Deve ser  possivel deletar um semaforo
    Dado que eu recupere o ID do semaforo criado no contexto
    Quando eu enviar a requisicao com o ID para o endpoint "/semaforos" de delecao de semaforos
    Então o status code da resposta deve ser 204
