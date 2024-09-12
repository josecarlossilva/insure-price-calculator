# Insure Price Calculator

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring MVC**
- **Lombok**
- **H2 Database**
- **JUnit 5**
- **Mockito**
- **SLF4J**
- **Prometheus**

## Regras do Sistema

1. **Calcular Preço Tarifado:** O preço base do produto não pode ser negativo.
2. **Validação de Categoria:** A categoria do produto precisa ser informada.
3. **Persistência de Dados:** Após o cálculo do preço tarifado, o produto deve ser salvo no banco de dados.
4. **Cálculo do Preço Tarifado:** O preço tarifado é calculado com base no preço base do produto e nos valores de IOF, PIS e COFINS associadas à sua categoria. A fórmula do cálculo é: `precoTarifado = precoBase + (precoBase * categoria.getIof()) + (precoBase * categoria.getPis()) + (precoBase * categoria.getCofins())`.

## Instruções Básicas de Como Executar o Projeto

### Pré-requisitos
- **Java 17** ou superior
- **Maven**

### Passos para Buildar e Executar o .jar

1. Clone o repositório:
    ```sh
    git clone https://github.com/josecarlossilva/insure-price-calculator.git
    ```
2. Navegue até o diretório do projeto:
    ```sh
    cd insure-price-calculator
    ```
3. Execute o seguinte comando para buildar o `.jar` usando Maven:
    ```sh
    ./mvnw clean install
    ```
4. Execute o `.jar` gerado:
    ```sh
    java -jar target/insure-price-calculator-0.0.1-SNAPSHOT.jar
    ```

### Utilização do H2 para Validar os Dados

1. Após iniciar a aplicação, acesse o console do H2 navegando até:

´http://localhost:8080/h2-console´

2. Use as seguintes credenciais para acessar:
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **Username:** `sa`
   - **Password:** password
3. Execute suas consultas SQL para verificar os dados persistidos no banco de dados.

## Tabela `Categoria`

A tabela `Categoria` define as diferentes categorias de produtos e seus respectivos valores de IOF, PIS e COFINS.

| Categoria     | IOF  | PIS  | COFINS |
|---------------|------|------|--------|
| VIDA          | 0.01 | 0.022| 0.0    |
| AUTO          | 0.055| 0.04 | 0.01   |
| VIAGEM        | 0.02 | 0.04 | 0.01   |
| RESIDENCIAL   | 0.04 | 0.0  | 0.03   |
| PATRIMONIAL   | 0.05 | 0.03 | 0.0    |

## Testando a API via Postman

### Endpoint: Calcular Preço Tarifado

#### Request
- **URL:** `http://localhost:8080/produtos/calcular`
- **Método:** `POST`
- **Headers:**
   - `Content-Type: application/json`
- **Body:**
    ```json
    {
        "nome": "Seguro de Vida Individual",
        "categoria": "VIDA",
        "precoBase": 100.0
    }
    ```

#### Response
- **Status Code:** `200 OK`
- **Body:**
    ```json
    {
        "id": 1,
        "nome": "Seguro de Vida Individual",
        "categoria": "VIDA",
        "precoBase": 100.0,
        "precoTarifado": 103.2
    }
    ```

### Exemplos de Cenários de Erro

#### Preço Base Negativo

- **Request:**
    ```json
    {
        "nome": "Seguro de Vida Individual",
        "categoria": "VIDA",
        "precoBase": -100.0
    }
    ```
- **Response:**
    ```json
    {
        "msg": "Preço base não pode ser negativo"
    }
    ```

#### Categoria não informada

- **Request:**
    ```json
    {
        "nome": "Seguro de Vida Individual",
        "precoBase": 100.0
    }
    ```
- **Response:**
    ```json
    {
        "message": "Informe uma Categoria para o Produto"
    }
    ```

## Tipos de Testes Criados para Validar a Aplicação

- **Testes Unitários:** Validação de regras de negócio e manipulação de exceções.
- **Testes de Integração:** Verificação da integração entre diferentes camadas da aplicação.

## Arquitetura Utilizada

- **Arquitetura MVC:** Separação da aplicação em camadas distintas (Controller, Service, Repository).

## Design Patterns Utilizados

- **Repository Pattern:** Para encapsular a lógica de acesso aos dados.
- **Service Pattern:** Para a lógica de negócios.

## Observabilidade

### Prometheus

- **Configuração:** A aplicação expõe métricas para Prometheus na URL `/actuator/prometheus`.
- **Integração:** Adicione a configuração no Prometheus para coletar estas métricas. Pesquise a palavra **``calcular``** (nome do recurso) no navegador para monitoração das requisições do recurso.

### Logs

- **SLF4J:** Framework de logging utilizado para registrar logs da aplicação.
- **Logs de Exceções:** Todos os erros capturados são registrados com detalhes no log.

---

Esperamos que este guia ajude na execução e validação da aplicação. Sinta-se à vontade para contribuir e reportar quaisquer problemas encontrados!