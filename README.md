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

## Identificadores de Produto com UUID

### Vantagens:

1. **Unicidade Global:** Garantido para ser único em escala global, ideal para sistemas distribuídos.
2. **Segurança e Privacidade:** Difícil de adivinhar, aumentando a segurança.
3. **Manutenção e Migração de Dados:** Facilita a fusão de bancos de dados sem renumeração.
4. **Independência do Banco de Dados:** IDs podem ser gerados pela aplicação, não dependendo de características específicas do banco de dados.
5. **Escalabilidade:** Nodes diferentes podem gerar IDs sem centralização, melhorando a resiliência.

### Considerações:

1. **Tamanho do UUID:** UUIDs são maiores e podem impactar na performance de índices e tamanho do banco de dados.
2. **Legibilidade e Debugging:** Menos legíveis e mais difíceis de debugar em comparação a números sequenciais.
3. **Ordenação de Registros:** Menos eficientes para ordenação, mas adequados para inserção distribuída.

### Exemplo:
```json
{
    "id": "8cfb5eb2-fd93-4322-bb74-c82f27c95a47",
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "precoBase": 100.0,
    "precoTarifado": 103.2
}
```

UUIDs são uma excelente escolha para aplicações escaláveis e distribuídas, providenciando unicidade global e alta segurança.

### Precisão de Casas Decimais

Durante a implementação, foi identificado um problema relacionado à precisão dos cálculos financeiros usando tipos primitivos de ponto flutuante (`double`). Isso é uma preocupação frequente ao lidar com operações financeiras, onde erros de arredondamento podem se acumular e levar a resultados incorretos.

Para resolver esses problemas, alterei a implementação para usar `BigDecimal` para os cálculos de preço tarifado. O `BigDecimal` é uma classe do Java que permite uma precisão arbitrária e um controle completo sobre o arredondamento, garantindo a precisão necessária para cálculos financeiros sensíveis.

#### Benefícios de Usar BigDecimal:

- **Precisão:** `BigDecimal` evita os problemas de imprecisão associados ao uso de `double` em cálculos financeiros.
- **Controle de Arredondamento:** Permite especificar a precisão e o modo de arredondamento desejado, o que é crucial para garantir a consistência dos resultados.
- **Confiabilidade:** Reduz quaisquer discrepâncias aritméticas que possam surgir com operações de ponto flutuante.

#### Implementação

No método `calcularPrecoTarifado` na classe `CalculoTarifaService` foi adicionado uma checagem prévia no banco de dados. Caso um produto com a mesma `categoria` e `precoBase` já exista, o método retornará esse produto existente em vez de calcular e salvar um novo.

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
    mvn clean install
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
        "id": "8cfb5eb2-fd93-4322-bb74-c82f27c95a47",
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
        "msg": "Informe uma Categoria para o Produto"
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