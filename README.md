# API Dígito Único

### Build, testes e execução :coffee:

Antes de quaisquer das ações seguintes é necessário acessar a pasta raiz do projeto após a clonagem/ download do
repositório (`cd digito-unico/`).

- Para build e download das dependências utilize o comando:

  - `./mvnw install`

- Para executar os testes unitários utilize o comando:

  - `./mvnw test`

- Para executar a aplicação (após o build) utilize o comando:

    - `./mvnw spring-boot:run`

- Para empacotar a aplicação (geração do arquivo `.jar`) utilize:

    - `./mvnw package`

### Sobre o cálculo do 'Digito Único' :bulb:

API que disponibiliza cálculo do dígito único a partir de uma representação de inteiro em formato String.

input: `String number` `int repeatTimes`

output: `int singleDigit`

#### Exemplo de cálculo do dígito único de um inteiro

`getSingleDigit("9875", 4)`

`getSingleDigit("9875987598759875")`

`getSingleDigit("9 + 8 + 7 + 5 + 9 + 8 + 7 + 5 + 9 + 8 + 7 + 5 + 9 + 8 + 7 + 5")`

`getSingleDigit("116")`

`getSingleDigit("1 + 1 + 6")`

`getSingleDigit("8")`

`8`




