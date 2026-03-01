# 📮 CEP API

> API REST em Java 21 + Spring Boot 4 para consulta de CEP com integração à BrasilAPI

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen?style=flat&logo=springboot)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## 📋 Sobre o Projeto

A **CEP API** é um projeto educacional desenvolvido para praticar dois conceitos fundamentais do desenvolvimento backend em Java:

1. **Integração com APIs Externas** - Consumo da [BrasilAPI](https://brasilapi.com.br) usando RestClient
2. **Tratamento Profissional de Exceções** - Implementação de exception handlers globais com respostas padronizadas

Este projeto **não utiliza** Docker nem persistência em banco de dados, mantendo o foco nos conceitos de Spring MVC e integração HTTP.

---

## 🎯 Objetivos de Aprendizado

- ✅ Consumir APIs REST externas com `RestClient` (Spring 6.1+)
- ✅ Implementar validações de entrada com Bean Validation
- ✅ Criar hierarquia de exceções customizadas
- ✅ Tratar erros globalmente com `@RestControllerAdvice`
- ✅ Mapear respostas externas para DTOs internos
- ✅ Documentar API com OpenAPI/Swagger
- ✅ Seguir padrões profissionais de arquitetura em camadas

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| **Java** | 21 | Linguagem de programação |
| **Spring Boot** | 4.0.3 | Framework base |
| **Spring Web MVC** | 6.2.x | Criação de endpoints REST |
| **Spring Validation** | - | Validação de dados |
| **RestClient** | - | Consumo de APIs externas |
| **SpringDoc OpenAPI** | 3.0.0 | Documentação Swagger |
| **Spring Actuator** | - | Monitoramento e métricas |
| **Maven** | 3.9+ | Gerenciamento de dependências |

---

## 🏗️ Arquitetura do Projeto

```
cep-api/
├── api/                      # Camada de apresentação (Controllers e DTOs)
│   ├── controller/
│   │   └── CepController.java
│   └── dto/
│       └── CepResponseDTO.java
├── domain/                   # Camada de negócio (Services)
│   ├── CepService.java
│   └── CepServiceImpl.java
├── integration/              # Camada de integração externa
│   └── brasilapi/
│       ├── client/
│       │   ├── BrasilApiClient.java
│       │   └── BrasilApiClientImpl.java
│       ├── config/
│       │   └── RestClientConfig.java
│       └── dto/
│           ├── BrasilApiCepResponseDTO.java
│           └── ApiErrorResponseDTO.java
├── mapper/                   # Mapeamento entre DTOs
│   └── Brasilapimapper.java
└── exception/                # Tratamento de exceções
    ├── CepInvalidoException.java
    ├── CepNaoEncontradoException.java
    ├── ServicoExternoException.java
    └── GlobalExceptionHandler.java
```

### 📐 Padrão de Camadas

- **Controller**: Recebe requisições HTTP e delega para o Service
- **Service**: Contém a lógica de negócio e validações
- **Client**: Responsável pela comunicação com a BrasilAPI
- **Mapper**: Converte DTOs externos para internos
- **Exception Handler**: Centraliza o tratamento de erros

---

## 📡 Endpoints da API

### Buscar CEP

Consulta informações de um CEP na BrasilAPI com fallback automático entre múltiplos provedores.

```http
GET /ceps/{cep}
```

#### Parâmetros

| Nome | Tipo | Obrigatório | Descrição |
|------|------|-------------|-----------|
| `cep` | string | Sim | CEP com 8 dígitos numéricos (sem hífen) |

#### Exemplo de Requisição

```bash
curl -X GET "http://localhost:8080/ceps/01310100"
```

#### Resposta de Sucesso (200 OK)

```json
{
  "cep": "01310-100",
  "state": "SP",
  "city": "São Paulo",
  "neighborhood": "Bela Vista",
  "street": "Avenida Paulista",
  "service": "brasilapi"
}
```

#### Resposta de Erro (400 Bad Request)

```json
{
  "timestamp": "2026-02-28T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "CEP inválido. O CEP deve conter exatamente 8 dígitos numéricos.",
  "path": "/ceps/123"
}
```

#### Resposta de Erro (404 Not Found)

```json
{
  "timestamp": "2026-02-28T14:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "CEP não encontrado na base de dados.",
  "path": "/ceps/99999999"
}
```

#### Resposta de Erro (502 Bad Gateway)

```json
{
  "timestamp": "2026-02-28T14:30:00",
  "status": 502,
  "error": "Bad Gateway",
  "message": "Serviço externo indisponível. Tente novamente mais tarde.",
  "path": "/ceps/01310100"
}
```

---

## 📸 Documentação Interativa (Swagger)

A API possui documentação interativa gerada automaticamente com SpringDoc OpenAPI.

**Acesse:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

![Swagger UI](https://raw.githubusercontent.com/devgiuliano/Giulianom95/refs/heads/main/assets/Swagger-cep.png)


---

## ⚙️ Como Executar

### Pré-requisitos

- **Java 21** ou superior instalado
- **Maven 3.9+** instalado
- Conexão com a internet (para acessar a BrasilAPI)

### Passos

1. **Clone o repositório**

```bash
git clone https://github.com/seu-usuario/cep-api.git
cd cep-api
```

2. **Compile o projeto**

```bash
mvn clean install
```

3. **Execute a aplicação**

```bash
mvn spring-boot:run
```

Ou execute o JAR gerado:

```bash
java -jar target/cep-api-0.0.1-SNAPSHOT.jar
```

4. **Acesse a API**

- **Endpoint base:** `http://localhost:8080/ceps/{cep}`
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **Health check:** `http://localhost:8080/actuator/health`

---

## 🧪 Testando a API

### Com cURL

```bash
# Buscar CEP válido
curl http://localhost:8080/ceps/01310100

# CEP inválido (menos de 8 dígitos)
curl http://localhost:8080/ceps/123

# CEP inválido (com letras)
curl http://localhost:8080/ceps/abcd1234

# CEP não encontrado
curl http://localhost:8080/ceps/99999999
```

### Com HTTPie

```bash
http GET localhost:8080/ceps/01310100
```

### Com Postman ou Insomnia

Importe a coleção OpenAPI em: `http://localhost:8080/v3/api-docs`

---

## 🔍 Regras de Validação

| Validação | Exceção Lançada | HTTP Status |
|-----------|-----------------|-------------|
| CEP com menos/mais de 8 dígitos | `CepInvalidoException` | 400 |
| CEP contém caracteres não numéricos | `CepInvalidoException` | 400 |
| CEP não encontrado na BrasilAPI | `CepNaoEncontradoException` | 404 |
| Timeout ou erro da BrasilAPI | `ServicoExternoException` | 502 |
| BrasilAPI retorna 5xx | `ServicoExternoException` | 502 |

---

## 🎓 Conceitos Implementados

### 1. Consumo de API Externa com RestClient

```java
@Bean
public RestClient brasilApiRestClient(RestClient.Builder builder) {
    return builder
        .baseUrl("https://brasilapi.com.br/api")
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .build();
}
```

O `RestClient` é a forma moderna do Spring (6.1+) para consumir APIs REST, substituindo o `RestTemplate`.

### 2. Hierarquia de Exceções Customizadas

```java
CepInvalidoException extends RuntimeException      // 400
CepNaoEncontradoException extends RuntimeException // 404
ServicoExternoException extends RuntimeException   // 502
```

### 3. Tratamento Global com @RestControllerAdvice

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleCepInvalido(...) {
        // Retorna JSON padronizado
    }
}
```

### 4. Records como DTOs (Java 14+)

```java
public record CepResponseDTO(
    String cep,
    String state,
    String city,
    String neighborhood,
    String street,
    String service
) {}
```

Records reduzem boilerplate e garantem imutabilidade.

---

## 🐛 Troubleshooting

### Erro: "Connection refused"

**Causa:** A BrasilAPI está fora do ar ou há problemas de rede.

**Solução:** Aguarde alguns minutos e tente novamente. A aplicação lançará `ServicoExternoException` (502).

### Erro: "CEP inválido"

**Causa:** O CEP informado não possui 8 dígitos numéricos.

**Solução:** Remova hífens e verifique se o CEP contém exatamente 8 números.

### Porta 8080 já está em uso

**Solução:** Altere a porta no `application.properties`:

```properties
server.port=8081
```

---

## 📚 Recursos de Aprendizado

- [BrasilAPI - Documentação](https://brasilapi.com.br/docs)
- [Spring RestClient - Guia Oficial](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient)
- [Exception Handling no Spring](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)
- [Records em Java](https://openjdk.org/jeps/395)

---

## 🤝 Contribuições

Este é um projeto educacional, mas sugestões são bem-vindas! Sinta-se à vontade para:

1. Fazer um fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abrir um Pull Request

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Giuliano**

- GitHub: [@devgiuliano](https://github.com/devgiuliano)
- LinkedIn: [@giulianodev](https://www.linkedin.com/in/giulianodev/)
- Email: giuliano.m995@gmail.com
---

## ⭐ Agradecimentos

- [BrasilAPI](https://brasilapi.com.br) - API pública gratuita de dados brasileiros
- Spring Team - Pelo excelente framework
- Comunidade Java - Pela documentação e tutoriais

---

<div align="center">

**Desenvolvido com ☕ e 💚 para aprender Spring Boot**

[⬆ Voltar ao topo](#-cep-api)

</div>

