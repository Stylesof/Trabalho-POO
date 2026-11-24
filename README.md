# Sistema de Gerenciamento da Pousada Serra Azul

**Projeto Prático de Software Orientado a Objetos — N2**
**Etapa E1 — Proposta do Projeto**

**Disciplina:** Software Orientado a Objetos

**Integrantes:** Adryan Gabriel Borges Rocha, Kauan Oliveira Dalat, Guilherme Rodrigues Dias

## 1. Domínio escolhido

O sistema é pensado para a **Pousada Serra Azul** e vai cuidar do dia a dia de hospedagens, reservas, acomodações e cobranças.

Para representar as acomodações, adotamos uma hierarquia em que a classe abstrata `Acomodacao` dá origem às subclasses `QuartoStandard`, `Suite` e `Chale`. Já a interface `Faturavel` define o que é comum a tudo o que gera cobrança: tanto as diárias das acomodações quanto os consumos adicionais sabem calcular o próprio valor.

## 2. Descrição do problema

Administrar uma pousada significa acompanhar hóspedes, acomodações, reservas, períodos de estadia e tudo o que é consumido durante a hospedagem. Quando essas informações ficam espalhadas, sem um controle central, os problemas aparecem: duas reservas para a mesma acomodação, diárias calculadas errado e cobranças extras esquecidas, como produtos do frigobar ou serviços prestados pela pousada.

Este projeto propõe um sistema que permita cadastrar e consultar hóspedes e acomodações, registrar reservas, verificar a disponibilidade em determinado período e impedir que reservas se sobreponham. O sistema também deverá registrar os consumos adicionais e calcular o valor final da hospedagem, permitindo fechar a conta no momento do check-out.

## 3. Requisitos funcionais

**RF01** — O sistema deve permitir cadastrar, consultar, alterar e remover hóspedes.

**RF02** — O sistema deve permitir cadastrar e consultar acomodações, identificando o tipo de cada uma: Quarto Standard, Suíte ou Chalé.

**RF03** — O sistema deve permitir registrar reservas, vinculando um hóspede a uma acomodação e informando as datas de check-in e check-out.

**RF04** — O sistema deve verificar se a acomodação está disponível no período solicitado antes de confirmar a reserva.

**RF05** — O sistema deve impedir a confirmação de reservas com períodos conflitantes para a mesma acomodação.

**RF06** — O sistema deve permitir registrar os consumos adicionais feitos durante a hospedagem, como itens do frigobar e serviços.

**RF07** — O sistema deve calcular o valor total da hospedagem, considerando a quantidade de diárias e os consumos registrados.

**RF08** — O sistema deve permitir realizar o check-out, fechar a conta e apresentar o detalhamento dos valores cobrados.

## 4. Regras de negócio iniciais

**RN01** — Uma acomodação não pode ter duas reservas confirmadas com períodos de hospedagem sobrepostos.

**RN02** — A data de check-out não conta como diária ocupada; por isso, outra reserva pode começar nesse mesmo dia.

**RN03** — A data de check-out deve ser posterior à data de check-in.

**RN04** — O valor da conta deve considerar as diárias da acomodação e todos os consumos registrados durante a hospedagem.

**RN05** — Uma conta fechada não pode receber novos consumos nem ser fechada novamente.

## 5. Resumo das classes e relacionamentos

### Classes

**Acomodacao:** classe abstrata que reúne os dados e os comportamentos comuns a todas as acomodações da pousada.

**QuartoStandard, Suite e Chale:** subclasses de Acomodacao. Cada uma representa um tipo de acomodação e calcula o seu valor de acordo com as próprias características.

**Faturavel:** interface que define a capacidade de calcular um valor cobrável. É realizada por Acomodacao e por Consumo.

**Hospede:** guarda os dados de identificação e de contato do cliente.

**Reserva:** registra o hóspede, a acomodação e o período reservado.

**Consumo:** representa um item ou serviço adicional lançado durante a hospedagem.

**Conta:** reúne os valores da hospedagem e dos consumos e permite fechar a cobrança no check-out.

### Relacionamentos

**Hospede — Reserva:** associação, 1 para 0..*.

**Acomodacao — Reserva:** associação, 1 para 0..*.

**Reserva — Conta:** composição, 1 para 0..1.

**Conta — Consumo:** composição, 1 para 0..*.

**Acomodacao e seus três tipos:** herança (QuartoStandard, Suite e Chale herdam de Acomodacao).

**Faturavel:** realizada por Acomodacao e por Consumo.

## 6. Próxima etapa

Na **Etapa E2**, a equipe vai detalhar atributos, construtores, validações, métodos, coleções e exceções. A modelagem também será refinada para atender aos requisitos técnicos do projeto, incluindo sobrecarga, polimorfismo e a implementação dos relacionamentos em Java.
