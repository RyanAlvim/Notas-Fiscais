Entendi. Você quer **um único bloco Markdown que seja fechado somente no final**, sem eu colocar ` ``` ` no meio do README.

O problema da imagem aconteceu porque eu coloquei blocos de código internos (` ```text `) dentro do bloco principal (` ```markdown `). Isso quebra a visualização do conteúdo quando você copia.

Daqui para frente, vou usar **apenas um bloco externo ` ```markdown `** e, dentro dele, para diagramas, vou usar indentação de 4 espaços em vez de cercas de código.

```markdown
# 🧾 Notas Fiscais

> Automação desenvolvida inteiramente em Java para processamento e integração de notas fiscais entre FTP, Google e Sankhya.

---

## 📌 Sobre o Projeto

O **Notas Fiscais** é uma aplicação desenvolvida **100% em Java** para automatizar o processo de recebimento, processamento e integração de notas fiscais.

A aplicação realizava a conexão com um servidor **FTP**, identificava e baixava automaticamente as notas fiscais disponíveis, enviava os documentos para o ambiente do **Google**, extraía o **número do pedido** e utilizava essa informação para realizar o processamento necessário no **Sankhya**.

Após a conclusão do processamento, a nota fiscal utilizada era automaticamente excluída do FTP, evitando que o mesmo documento fosse processado novamente.

O projeto foi desenvolvido com foco em **automação de processos empresariais e integração entre sistemas**.

---

## 🎯 Objetivo

O principal objetivo do projeto era reduzir a quantidade de tarefas manuais envolvidas no processamento de notas fiscais.

A aplicação automatizava todo o fluxo:

- 📡 Conexão com o FTP
- 📥 Busca e download das notas fiscais
- ☁️ Envio dos documentos para o Google
- 🔎 Extração de informações da nota
- 🔢 Identificação do número do pedido
- 🏢 Processamento no Sankhya
- 🗑️ Exclusão da nota já processada do FTP

---

## 🔄 Fluxo da Automação

    ┌─────────────────┐
    │       FTP       │
    │                 │
    │ Notas Fiscais   │
    └────────┬────────┘
             │
             │ Buscar e baixar
             ▼
    ┌─────────────────┐
    │    Download     │
    │                 │
    │ Nota Fiscal     │
    └────────┬────────┘
             │
             │ Enviar
             ▼
    ┌─────────────────┐
    │     Google      │
    │                 │
    │ Processamento   │
    └────────┬────────┘
             │
             │ Extrair informações
             ▼
    ┌─────────────────┐
    │     Extração    │
    │                 │
    │ Nº do Pedido    │
    └────────┬────────┘
             │
             │ Integrar
             ▼
    ┌─────────────────┐
    │     Sankhya     │
    │                 │
    │ Processamento   │
    └────────┬────────┘
             │
             │ Processamento concluído
             ▼
    ┌─────────────────┐
    │  Exclusão FTP   │
    │                 │
    │ Nota processada │
    └─────────────────┘

---

## ⚙️ Funcionamento

### 1. 📡 Conexão com o FTP

A aplicação estabelecia uma conexão com o servidor FTP responsável por disponibilizar as notas fiscais.

O sistema verificava os arquivos disponíveis e identificava as notas que deveriam ser processadas.

### 2. 📥 Download das Notas

As notas fiscais encontradas no FTP eram baixadas automaticamente pela aplicação Java.

### 3. ☁️ Envio para o Google

Após o download, os documentos eram enviados para o ambiente do **Google** utilizado pelo processo.

### 4. 🔎 Extração das Informações

A aplicação processava a nota fiscal e extraía as informações necessárias para continuar o fluxo.

Entre essas informações estava o **número do pedido**, utilizado posteriormente no Sankhya.

### 5. 🏢 Processamento no Sankhya

Com o número do pedido identificado, a aplicação realizava o processamento necessário no **Sankhya**, integrando as informações da nota ao sistema utilizado pela empresa.

### 6. 🗑️ Exclusão da Nota no FTP

Após o processamento ser concluído, a nota fiscal utilizada era excluída do FTP.

Isso evitava que a mesma nota fosse processada novamente em uma execução futura.

---

## ☕ Tecnologia

### Java

O projeto foi desenvolvido **inteiramente em Java**.

A linguagem era utilizada para implementar todo o fluxo de automação e integração:

    Java
     │
     ├── Conexão com FTP
     ├── Download de arquivos
     ├── Manipulação das notas
     ├── Processamento dos documentos
     ├── Extração de informações
     ├── Integração com Google
     ├── Integração com Sankhya
     └── Gerenciamento dos arquivos processados

---

## 🔗 Integrações

| Sistema | Função |
|---|---|
| 📡 **FTP** | Origem e armazenamento das notas fiscais |
| ☁️ **Google** | Recebimento e processamento dos documentos |
| 🏢 **Sankhya** | Processamento e integração das informações |

---

## 🧩 Principais Funcionalidades

- ✅ Conexão automática com servidor FTP
- ✅ Busca de notas fiscais disponíveis
- ✅ Download automático dos documentos
- ✅ Envio dos arquivos para o Google
- ✅ Processamento das notas fiscais
- ✅ Extração do número do pedido
- ✅ Integração com o Sankhya
- ✅ Controle do fluxo de processamento
- ✅ Exclusão das notas já utilizadas
- ✅ Prevenção de processamento duplicado
- ✅ Automação de tarefas repetitivas

---

## 🧠 Conhecimentos Aplicados

O desenvolvimento do projeto envolveu conhecimentos em:

### Programação

- Java
- Orientação a Objetos
- Manipulação de arquivos
- Processamento de documentos
- Automação de processos

### Integração de Sistemas

- Comunicação com servidores FTP
- Integração entre sistemas distintos
- Transferência de arquivos
- Processamento de documentos
- Integração com serviços externos
- Integração com sistema ERP

### Automação

- Execução automática de tarefas
- Processamento sequencial de documentos
- Extração de informações
- Controle de documentos processados
- Gerenciamento de arquivos

---

## 📊 Problema Solucionado

O projeto foi criado para automatizar um fluxo que envolvia diferentes sistemas e diversas operações manuais.

O processo podia ser representado da seguinte forma:

    Localizar nota
          ↓
    Baixar nota
          ↓
    Enviar para o Google
          ↓
    Processar documento
          ↓
    Extrair número do pedido
          ↓
    Processar no Sankhya
          ↓
    Remover nota utilizada do FTP

A aplicação Java transformava essas etapas em um fluxo automatizado.

---

## 🚀 Benefícios da Automação

A automatização do processo proporcionava:

- ⚡ Maior agilidade no processamento das notas
- 🔄 Redução de tarefas repetitivas
- 🤖 Menor necessidade de intervenção manual
- 📂 Organização do fluxo de documentos
- 🔗 Integração entre diferentes sistemas
- 🛡️ Redução do risco de processamento duplicado
- 🧹 Limpeza automática dos documentos já processados

---

## 🏗️ Arquitetura Conceitual

A aplicação funcionava como uma camada de automação responsável por conectar as diferentes etapas do processo:

    ┌──────────────────────┐
    │     Aplicação Java   │
    └──────────┬───────────┘
               │
       ┌───────┼────────┐
       │       │        │
       ▼       ▼        ▼
      FTP    Google   Sankhya
       │       │        │
       │       │        │
       ▼       ▼        ▼
    Entrada  Process.  Integração
    de notas dos dados do pedido
       │       │        │
       └───────┼────────┘
               │
               ▼
        Processo concluído

---

## 📚 Objetivo Técnico

Além da automação do processo empresarial, o projeto representou uma aplicação prática de **integração de sistemas utilizando Java**.

O desenvolvimento envolveu a comunicação entre sistemas com responsabilidades diferentes, permitindo que o fluxo de processamento de notas fiscais fosse executado de forma automatizada.

O projeto demonstra conhecimentos em:

- ☕ Java
- 🔗 Integração de sistemas
- 📡 FTP
- 📂 Manipulação de arquivos
- 📄 Processamento de documentos
- 🤖 Automação
- 🏢 Integração com ERP

---

## 📌 Status

> 🚧 Projeto desenvolvido anteriormente.

Este repositório representa um projeto de automação empresarial desenvolvido em Java para processamento e integração de notas fiscais.

---

## 👨‍💻 Autor

**Ryan Alvim**

Desenvolvedor interessado em **Java, backend, automação e integração de sistemas**.

### 🔗 Contato

- GitHub: [@RyanAlvim](https://github.com/RyanAlvim)
- E-mail: [ryanalvim65@gmail.com](mailto:ryanalvim65@gmail.com)

---

<p align="center">
  Desenvolvido com ☕ Java
</p>
```
