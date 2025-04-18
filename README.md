# Gerenciamento de Tráfego

Aplicação Java com Spring Boot para controle de semáforos inteligentes e análise de tráfego com base em condições climáticas e fluxo de veículos.

## ✅ Funcionalidades

- Controle de Semáforos Inteligentes
- Monitoramento de Tráfego em tempo real
- API REST com endpoints para semáforos, tráfego e usuários

---

## 🔗 Endpoints da API

### 🚦 Semáforos

- `GET /semaforos` — Listar semáforos  
- `GET /semaforos/{id}` — Buscar por ID  
- `POST /semaforos` — Criar novo semáforo  
- `PUT /semaforos/{id}` — Atualizar estado  
- `DELETE /semaforos/{id}` — Deletar semáforo

### 🛣️ Tráfego

- `GET /trafegos` — Listar registros  
- `GET /trafegos/{id}` — Buscar por ID  
- `POST /trafegos` — Criar novo registro

### 👤 Usuários

- `PUT /usuarios/{id}` — Atualizar usuário  
- `DELETE /usuarios/{id}` — Deletar usuário

---

## ▶️ Como rodar o projeto

### 📦 Usando Docker

```bash
mvn clean package
docker build -t gerenciamento-trafego .
docker run -p 8080:8080 gerenciamento-trafego
```

### 🐳 Usando Docker Compose

```bash
docker-compose up --build
```

### 💻 Rodar localmente (sem Docker)

```bash
mvn clean package
java -jar target/trafego-0.0.1-SNAPSHOT.jar
```

Acesse a aplicação em: [http://localhost:8080](http://localhost:8080)

---

## ⚙️ CI/CD com GitHub Actions

O projeto possui pipeline de CI/CD configurado via GitHub Actions (`.github/workflows/ci-cd.yml`), com:

- Build automático ao fazer push na branch `main`
- Testes automatizados 
- Deploy automatizado para staging e produção

Acompanhe a execução em **GitHub > Actions**.

---

## 🐳 Containerização

- Dockerfile incluso para build da aplicação
- Docker Compose opcional para orquestração
- Executável em qualquer ambiente com Docker instalado

---
