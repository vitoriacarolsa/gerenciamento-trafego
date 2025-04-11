# Gerenciamento de Tráfego

Este projeto é uma aplicação para controle de semáforos inteligentes, otimizando o fluxo de tráfego com base em padrões de tráfego e condições climáticas.

## Funcionalidades

- **Controle de Semáforos Inteligentes**: Monitoramento dos semáforos com base no fluxo de tráfego.
- **Monitoramento de Tráfego**: Análise em tempo real dos padrões de tráfego para otimização de rotas e redução de congestionamentos.

## Endpoints da API

### Semáforos

- **Listar Semáforos**:
    ```http
    GET /semaforos
    ```

- **Obter Semáforo por ID**:
    ```http
    GET /semaforos/{id}
    ```

- **Criar Novo Semáforo**:
    ```http
    POST /semaforos
    ```

    | Parâmetro    | Tipo    | Descrição                        |
    | :----------- | :------ | :------------------------------- |
    | `localizacao`| `string`| Localização do semáforo.        |
    | `estadoAtual`| `string`| Estado atual do semáforo (vermelho, verde, amarelo). |
    | `tempoVerde` | `integer`| Duração do tempo verde em segundos. |
    | `tempoVermelho` | `integer`| Duração do tempo vermelho em segundos. |
    | `condicoesClimaticas` | `string`| Condições climáticas no local. |
    
    ```json
    {
        "localizacao": "Av. Paulista, 1000",
        "estadoAtual": "verde",
        "tempoVerde": 30,
        "tempoVermelho": 30,
        "condicoesClimaticas": "ensolarado"
    }
    ```

- **Atualizar Semáforo**:
    ```http
    PUT /semaforos/{id}
    ```

    | Parâmetro    | Tipo    | Descrição                        |
    | :----------- | :------ | :------------------------------- |
    | `estadoAtual`| `string`| Novo estado do semáforo.        |

    ```json
    {
        "estadoAtual": "vermelho"
    }
    ```

- **Deletar Semáforo**:
    ```http
    DELETE /semaforos/{id}
    ```

### Tráfego

- **Listar Registros de Tráfego**:
    ```http
    GET /trafegos
    ```

- **Obter Registro de Tráfego por ID**:
    ```http
    GET /trafegos/{id}
    ```

- **Criar Novo Registro de Tráfego**:
    ```http
    POST /trafegos
    ```

    | Parâmetro        | Tipo       | Descrição                                      |
    | :--------------- | :--------- | :--------------------------------------------- |
    | `dataHora`      | `string`   | Data e hora do registro (formato ISO 8601).  |
    | `localizacao`   | `string`   | Localização do registro de tráfego.           |
    | `quantidadeVeiculo` | `integer`| Número de veículos em um determinado ponto.   |
    | `velocidadeVeiculo` | `double` | Velocidade média dos veículos registrados.     |
    | `congestionamento`  | `boolean` | Indica se há congestionamento (true/false).   |

    ```json
    {
        "dataHora": "2024-11-04T10:00:00Z",
        "localizacao": "Av. Paulista, 1000",
        "quantidadeVeiculo": 150,
        "velocidadeVeiculo": 40.5,
        "congestionamento": false
    }
    ```

### Usuários

- **Deletar Usuário**:
    ```http
    DELETE /usuarios/{id}
    ```

- **Atualizar Usuário**:
    ```http
    PUT /usuarios/{id}
    ```

    | Parâmetro    | Tipo    | Descrição                        |
    | :----------- | :------ | :------------------------------- |
    | `nome`       | `string`| Nome do usuário.                |
    | `email`      | `string`| Email do usuário.               |
    | `senha`      | `string`| Senha do usuário.               |
    | `role`       | `string`| Papel do usuário (ex: ADMIN, USER). |

    ```json
    {
        "nome": "João Silva",
        "email": "joao.silva@example.com",
        "senha": "senha123",
        "role": "USER"
    }
    ```


# trigger
