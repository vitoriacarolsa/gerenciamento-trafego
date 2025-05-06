# language: pt

@regressivo
Funcionalidade: Cadastro de novo semáforo inteligente

  Cenario: Cadastro bem-sucedido de semáforo
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

  Cenario: Cadastro de entrega sem sucesso ao passar o campo ultimaAtualizacao invalido
    Dado que eu tenha os seguintes dados do semáforo:
      | campo               | valor        |
      | localizacao         | Av. Paulista |
      | estadoAtual         | verde        |
      | tempoVerde          | 30           |
      | tempoVermelho       | 45           |
      | condicoesClimaticas | Ensolarado   |
      | ultimaAtualizacao   | 22:27        |
    Quando eu enviar a requisicao para o endpoint "/semaforos"
    Então o status code da resposta deve ser 400
    E o corpo da resposta de erro da api deve retornar a mensagem "formato invalidooo"






