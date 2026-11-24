# Sistema de Gerenciamento da Pousada Serra Azul

**Etapa E2 — Núcleo do domínio**

**Disciplina:** Programação Orientada a Objetos

**Integrantes:** Adryan Gabriel Borges Rocha, Kauan Oliveira Dalat, Guilherme Rodrigues Dias

**Entrega:** 23/11/2026

## 1. Visão geral

Esta etapa implementa em Java o núcleo do domínio proposto na Etapa E1 e representado no diagrama de classes: hóspedes, acomodações (Quarto Standard, Suíte e Chalé), reservas, consumos e contas, além das classes de serviço, da exceção personalizada e de um teste executável pelo console.

## 2. Estrutura do repositório

O projeto é um projeto Gradle. O código-fonte fica em `src/main/java/me/trabalho/poo`, organizado em quatro pacotes:

```text
.
├── gradle/wrapper/
├── src/main/java/me/trabalho/poo/
│   ├── modelo/     Faturavel, Acomodacao, QuartoStandard,
│   │               Suite, Chale, Hospede, Reserva,
│   │               StatusReserva, Consumo, Conta, Formatador
│   ├── servico/    HospedeService, AcomodacaoService,
│   │               ReservaService
│   ├── excecao/    PousadaException, DadosInvalidosException,
│   │               RegistroNaoEncontradoException,
│   │               ReservaConflitanteException,
│   │               ContaFechadaException
│   └── app/        Main (teste pelo console)
├── .gitignore
├── Image.jpeg
├── LICENSE
├── README_2576.md
├── build.gradle.kts
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

| Pacote | Responsabilidade |
|---|---|
| `me.trabalho.poo.modelo` | Classes de domínio: herança, classe abstrata, interface e polimorfismo. |
| `me.trabalho.poo.servico` | Regras de negócio e coleções (cadastros, disponibilidade, consumos, check-out). |
| `me.trabalho.poo.excecao` | Exceção base do sistema e exceções específicas por tipo de falha. |
| `me.trabalho.poo.app` | Classe `Main`, que exercita todo o núcleo e imprime os resultados no console. |

## 3. Como compilar e executar

### 3.1 Pelo Gradle

Na raiz do projeto, com o wrapper já incluído no repositório:

```bash
./gradlew run          # Linux / macOS
gradlew.bat run        # Windows
```

O comando `run` exige o plugin `application` e a classe principal configurada no `build.gradle.kts`. Caso ainda não esteja, acrescente:

```kotlin
plugins {
    java
    application
}

application {
    mainClass.set("me.trabalho.poo.app.Main")
}
```

### 3.2 Pelo javac (sem Gradle)

Linux / macOS:

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
java -cp out me.trabalho.poo.app.Main
```

Windows (PowerShell):

```powershell
mkdir out
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
java -cp out me.trabalho.poo.app.Main
```

Requer JDK 11 ou superior. Se o terminal do Windows não exibir os acentos corretamente, execute antes `chcp 65001` ou use a opção `-Dfile.encoding=UTF-8`.

## 4. Requisitos da E2 e onde são atendidos

| Requisito da etapa | Onde está |
|---|---|
| Herança | `QuartoStandard`, `Suite` e `Chale` estendem `Acomodacao`. |
| Classe abstrata | `Acomodacao`, com os métodos abstratos `calcularValor()`, `getTipo()` e `getCapacidade()`. |
| Interface | `Faturavel`, implementada por `Acomodacao` e por `Consumo`. |
| Polimorfismo | `calcularValor()` devolve valores diferentes por tipo de acomodação; no `Main`, um vetor de `Faturavel` mistura acomodações e consumos. |
| Serviços com coleções | `HospedeService` e `AcomodacaoService` (`LinkedHashMap`), `ReservaService` (`ArrayList`); `Conta` guarda uma `List<Consumo>`. |
| Exceção personalizada | `PousadaException` (checked) e quatro subclasses específicas. |
| Teste pelo console | `me.trabalho.poo.app.Main` executa cenários de sucesso e de falha para cada requisito e regra. |

## 5. Requisitos funcionais e regras de negócio

| Item | Implementação |
|---|---|
| RF01 | `HospedeService`: `cadastrar`, `buscar`, `buscarPorNome`, `listar`, `alterar` e `remover`. |
| RF02 | `AcomodacaoService`: `cadastrar`, `buscar`, `listar` e `listarPorTipo`. |
| RF03 | `ReservaService.registrar(hospede, acomodacao, checkIn, checkOut)`. |
| RF04 | `ReservaService.estaDisponivel` e `listarDisponiveis`. |
| RF05 / RN01 | `registrar` lança `ReservaConflitanteException` quando existe reserva confirmada sobreposta para a mesma acomodação. |
| RF06 | `ReservaService.registrarConsumo`, que chama `Conta.adicionarConsumo`. |
| RF07 / RN04 | `Conta.calcularTotal()` = quantidade de diárias × valor da acomodação + todos os consumos. |
| RF08 | `ReservaService.realizarCheckOut` fecha a conta e finaliza a reserva; `Conta.detalhar()` apresenta o detalhamento. |
| RN02 | `Reserva.conflitaCom`: `novoCheckIn < outroCheckOut` e `novoCheckOut > outroCheckIn`. Check-out no mesmo dia de um check-in não gera conflito. |
| RN03 | O construtor de `Reserva` rejeita check-out igual ou anterior ao check-in (`DadosInvalidosException`). |
| RN05 | `Conta` lança `ContaFechadaException` ao receber consumo ou ao ser fechada novamente. |

## 6. Decisões de modelagem

- **Valor da diária por tipo:** `valorDiaria` é o valor base; cada subclasse aplica um acréscimo em `calcularValor()`: Standard com ar-condicionado +10%, Suíte com hidromassagem +25% e Chalé com cozinha +15%. Os percentuais são constantes de cada classe e podem ser ajustados.
- **Sobrecarga:** `Acomodacao.calcularValor(int diarias)` e dois construtores de `Consumo` (com e sem data/hora).
- **Status da reserva:** na proposta da E1 o status era um texto (`String`); no diagrama e no código ele é o enum `StatusReserva` (`CONFIRMADA`, `FINALIZADA`, `CANCELADA`), mais seguro que texto livre.
- **Composição Reserva–Conta:** a `Conta` só é criada por `Reserva.abrirConta()` (construtor visível apenas no pacote) e é aberta junto com a reserva pelo `ReservaService`.
- **Exceções checked:** as falhas de negócio estendem `Exception`, o que obriga quem chama a tratá-las.
- **Após o check-out:** a reserva passa a `FINALIZADA` e deixa de bloquear a acomodação, pois a RN01 trata apenas de reservas confirmadas.
- **Cardinalidade Reserva–Conta:** o diagrama mantém 1 para 0..1, como na E1, pois a classe `Reserva` admite existir sem conta até `abrirConta()` ser chamado. Na prática, o `ReservaService` sempre abre a conta ao registrar a reserva.

## 7. Diagrama de classes

O arquivo `Diagrama.drawio` está alinhado ao código desta etapa. Ele representa as 5 exceções, a interface `Faturavel`, a classe abstrata `Acomodacao` e suas três subclasses, o enum `StatusReserva`, as classes `Hospede`, `Reserva`, `Conta` e `Consumo` e os três serviços, com as mesmas assinaturas de métodos e os mesmos atributos do código. Pontos que merecem atenção:

- Os métodos de `ReservaService` e `HospedeService` recebem o código (`int`) da reserva ou do hóspede, e não o objeto; por exemplo, `realizarCheckOut(int codigoReserva)` e `alterar(int, String, String, String)`.
- `Reserva.conflitaCom` recebe duas datas (`LocalDate`), e `Reserva.abrirConta` recebe o código da conta (`int`).
- `ReservaService.listarDisponiveis` recebe a coleção de acomodações a consultar, além do período.
- `Formatador` é uma classe utilitária de visibilidade de pacote (`modelo`), usada por `Acomodacao`, `Consumo` e `Conta` para exibir valores em reais.
- Uma nota no diagrama indica quais classes lançam cada exceção.
