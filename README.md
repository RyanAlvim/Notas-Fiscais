# 📄 Notas Fiscais

> Automação desenvolvida em Java para processamento de documentos digitalizados, reconhecimento de texto, integração com FTP e registro automático de informações no ERP Sankhya.

---

## 📌 Sobre o Projeto

O **Notas Fiscais** é uma aplicação desenvolvida inteiramente em **Java** para automatizar o processamento de documentos digitalizados utilizados em diferentes processos internos.

O sistema funciona como uma camada de integração entre diferentes serviços:

- 📡 Servidor FTP
- 🖼️ Imagens digitalizadas
- ☁️ Google Cloud Vision
- 🔎 OCR e extração de informações
- 🏢 ERP Sankhya
- 🌐 Serviços HTTP

A aplicação monitora diferentes diretórios no FTP e executa fluxos específicos de acordo com o tipo de documento recebido.

Entre os processos automatizados estão:

- 🧾 Notas fiscais automáticas
- 📝 Notas fiscais manuais
- 🔧 Documentos relacionados a bombas
- 🔍 Documentos de vistorias

---

## 🎯 Objetivo

O objetivo principal do projeto é automatizar o processamento de documentos digitalizados que anteriormente dependeriam de diversas operações manuais.

O sistema realiza automaticamente etapas como:

- Busca de documentos no FTP
- Download das imagens
- Conversão das imagens para Base64
- Envio para OCR
- Extração de informações do documento
- Identificação de números e chaves
- Consulta ao Sankhya
- Registro das informações processadas
- Organização dos documentos processados
- Separação de documentos com problemas

---

## 🏗️ Arquitetura

O fluxo geral da aplicação pode ser representado da seguinte forma:

    ┌──────────────────────┐
    │         FTP          │
    │                      │
    │  Documentos / JPGs   │
    └──────────┬───────────┘
               │
               ▼
    ┌──────────────────────┐
    │      Aplicação       │
    │         Java         │
    └──────────┬───────────┘
               │
               ▼
    ┌──────────────────────┐
    │      Download        │
    │      da imagem       │
    └──────────┬───────────┘
               │
               ▼
    ┌──────────────────────┐
    │       Base64         │
    │                      │
    │ Conversão da imagem  │
    └──────────┬───────────┘
               │
               ▼
    ┌──────────────────────┐
    │   Google Vision API  │
    │                      │
    │   TEXT_DETECTION     │
    └──────────┬───────────┘
               │
               ▼
    ┌──────────────────────┐
    │  Extração de texto   │
    │      / JSON          │
    └──────────┬───────────┘
               │
               ▼
    ┌──────────────────────┐
    │       Sankhya        │
    │                      │
    │ Consulta / Registro  │
    └──────────┬───────────┘
               │
          ┌────┴─────┐
          ▼          ▼
    ┌───────────┐ ┌───────────┐
    │Processadas│ │  Lixeira  │
    └───────────┘ └───────────┘

---

# 🔄 Fluxo Principal

A aplicação possui um ponto de entrada centralizado na classe:

    Main.Main

O programa executa continuamente os módulos de processamento:

    Main
     │
     ├── Notas Manuais
     │
     ├── Notas Automáticas
     │
     ├── Bombas
     │
     └── Vistorias

Cada módulo acessa seu respectivo diretório no FTP e executa o fluxo de processamento correspondente.

---

# 🧾 Notas Automáticas

O módulo:

    Notas_Automaticas.Notas_Automaticas

é responsável pelo processamento automatizado de notas fiscais digitalizadas.

O diretório utilizado é:

    Scanner/Notas_Automaticas

---

## 🔄 Fluxo das Notas Automáticas

    FTP
     │
     │ Scanner/Notas_Automaticas
     ▼
    Imagem JPG
     │
     ▼
    Download
     │
     ▼
    Conversão para Base64
     │
     ▼
    Google Vision
     │
     ▼
    OCR
     │
     ▼
    Extração da chave de acesso
    e número de entrega
     │
     ▼
    Consulta no Sankhya
     │
     ▼
    Identificação da nota
     │
     ▼
    Registro no Sankhya
     │
     ▼
    Arquivo processado

---

## 🔎 Extração das Informações

A classe:

    Json.BuscarStr

é responsável por interpretar o texto retornado pelo OCR.

O processamento procura informações relacionadas a:

- CHAVE DE ACESSO
- N. ENTREGA

A aplicação combina essas informações para localizar o documento correspondente no Sankhya.

---

## 🏢 Consulta no Sankhya

Depois da extração das informações, o projeto executa uma consulta através do serviço:

    DbExplorerSP.executeQuery

A consulta procura correspondências relacionadas à chave da NF-e ou ao número de entrega.

O resultado determina como o documento será processado.

---

## 📂 Resultado do Processamento

Quando o documento é processado corretamente, a imagem é movida para:

    Processadas/

Quando ocorre uma inconsistência ou falha no processamento, o arquivo pode ser direcionado para:

    Lixeira/

---

# 📝 Notas Manuais

O módulo:

    Notas_Manuais.Notas_Manuais

é responsável pelo processamento de documentos inseridos manualmente.

O diretório utilizado é:

    Scanner/Notas_Manuais

---

## 🔄 Fluxo

    FTP
     │
     ▼
    Scanner/Notas_Manuais
     │
     ▼
    Imagem JPG
     │
     ▼
    Identificação pelo nome do arquivo
     │
     ▼
    Registro no Sankhya
     │
     ├───────────────┐
     ▼               ▼
    Processadas    Lixeira

---

## 📌 Identificação

Nesse fluxo, o nome do arquivo é utilizado como identificador do documento.

O sistema utiliza essa informação para realizar o registro através da integração com o Sankhya.

---

# 🔧 Bombas

O módulo:

    Bombas.Bombas

é responsável pelo processamento de documentos relacionados às bombas.

O diretório utilizado é:

    Scanner/Bombas

---

## 🔄 Fluxo das Bombas

    FTP
     │
     ▼
    Imagem JPG
     │
     ▼
    Download
     │
     ▼
    Base64
     │
     ▼
    Google Vision
     │
     ▼
    OCR
     │
     ▼
    Procura por "Pedido:"
     │
     ▼
    Número do pedido
     │
     ▼
    Registro no Sankhya
     │
     ├───────────────┐
     ▼               ▼
    Processadas    Lixeira

---

## 🔎 Extração do Pedido

A classe:

    Bombas.BuscaPedido

é utilizada para localizar o número do pedido dentro do texto reconhecido.

O processamento procura a expressão:

    Pedido:

O valor encontrado é utilizado como identificador do documento.

---

# 🔍 Vistorias

O módulo:

    Vistorias.Vistorias

processa documentos relacionados às vistorias.

O diretório utilizado é:

    Scanner/Vistorias

---

## 🔄 Fluxo

    FTP
     │
     ▼
    Imagem JPG
     │
     ▼
    Download
     │
     ▼
    Base64
     │
     ▼
    Google Vision
     │
     ▼
    OCR
     │
     ▼
    Extração do pedido
     │
     ▼
    Consulta / registro no Sankhya
     │
     ├───────────────┐
     ▼               ▼
    Processadas    Lixeira

---

# ☁️ Google Cloud Vision

O projeto utiliza a API do **Google Cloud Vision** para reconhecimento de texto em imagens.

A classe responsável pela integração é:

    Google.Google

A aplicação utiliza a funcionalidade:

    TEXT_DETECTION

---

## 🖼️ Processo de OCR

Antes do envio para o Google Vision, a imagem é convertida para Base64.

O fluxo é:

    Arquivo JPG
        │
        ▼
    Files.readAllBytes()
        │
        ▼
    Base64 Encoder
        │
        ▼
    String Base64
        │
        ▼
    Google Vision API
        │
        ▼
    JSON
        │
        ▼
    Texto reconhecido

---

# 🔤 Conversão para Base64

A classe:

    Google.FileBase64

é responsável pela conversão das imagens para Base64.

O processo utiliza:

    java.util.Base64

e:

    java.nio.file.Files

O arquivo é lido como bytes e posteriormente convertido para uma representação Base64.

---

# 🧩 Processamento de JSON

A classe:

    Json.Json

interpreta a resposta JSON enviada pelo Google Vision.

O processamento acessa:

    responses

e:

    textAnnotations

A primeira anotação de texto é utilizada para obter a descrição textual reconhecida na imagem.

---

# 🏢 Integração com Sankhya

A integração com o ERP Sankhya é realizada através de requisições HTTP.

O projeto possui diferentes classes responsáveis por essa comunicação:

    Sankhya.Sankhya
    Sankhya.QueryCount
    Sankhya.Insert

---

## 🔐 Autenticação

A classe:

    Sankhya.Sankhya

realiza a autenticação no serviço do Sankhya através do serviço:

    MobileLoginSP.login

Após a autenticação, a aplicação obtém um:

    JSESSIONID

Esse identificador é utilizado nas requisições seguintes.

---

## 🔎 Consultas

A classe:

    Sankhya.QueryCount

executa consultas utilizando:

    DbExplorerSP.executeQuery

As consultas são enviadas através de requisições HTTP e os resultados são interpretados como JSON.

---

## 💾 Inserção de Registros

A classe:

    Sankhya.Insert

é responsável pelo registro dos documentos processados.

A aplicação utiliza o serviço:

    DatasetSP.save

e grava informações na entidade:

    AD_SCANNERS

Entre os dados utilizados estão:

- Número do scanner
- Chaves relacionadas ao documento
- Caminho do arquivo
- Evento
- Tabela relacionada

---

# 📡 FTP

A classe:

    Ftp.Ftp

encapsula a comunicação com o servidor FTP.

A implementação utiliza:

    Apache Commons Net

e:

    FTPClient

---

## ⚙️ Operações FTP

A classe possui funcionalidades para:

- Conectar ao servidor
- Autenticar
- Alterar diretórios
- Listar arquivos
- Baixar arquivos
- Mover arquivos
- Desconectar

---

## 📂 Organização dos Arquivos

Os módulos utilizam diretórios separados para cada tipo de documento.

    Scanner/
    │
    ├── Notas_Automaticas/
    │   ├── Processadas/
    │   └── Lixeira/
    │
    ├── Notas_Manuais/
    │   ├── Processadas/
    │   └── Lixeira/
    │
    ├── Bombas/
    │   ├── Processadas/
    │   └── Lixeira/
    │
    └── Vistorias/
        ├── Processadas/
        └── Lixeira/

Essa organização permite separar documentos ainda não processados dos documentos já tratados.

---

# 🧠 Arquitetura dos Módulos

A aplicação foi dividida em pacotes de acordo com a responsabilidade de cada componente.

    src/main/java/
    │
    ├── Bombas/
    │   ├── Bombas.java
    │   └── BuscaPedido.java
    │
    ├── Ftp/
    │   └── Ftp.java
    │
    ├── Google/
    │   ├── FileBase64.java
    │   └── Google.java
    │
    ├── Json/
    │   ├── Json.java
    │   └── BuscarStr.java
    │
    ├── Main/
    │   └── Main.java
    │
    ├── Notas_Automaticas/
    │   ├── Notas_Automaticas.java
    │   └── Select.java
    │
    ├── Notas_Manuais/
    │   └── Notas_Manuais.java
    │
    ├── Sankhya/
    │   ├── Insert.java
    │   ├── QueryCount.java
    │   └── Sankhya.java
    │
    └── Vistorias/
        └── Vistorias.java

---

# 🛠️ Tecnologias

## Linguagem

- Java 8

## Bibliotecas

- Apache Commons Net
- Unirest
- JSON

## Serviços

- Google Cloud Vision API
- Sankhya
- FTP

## Build

- Maven

---

# 📦 Dependências

O projeto utiliza Maven para gerenciamento das dependências.

Entre as principais dependências estão:

- `commons-net`
- `unirest-java`
- `org.json`

A configuração está disponível no:

    pom.xml

---

# 🔄 Fluxo Completo

De maneira simplificada, a aplicação funciona como uma esteira de processamento:

    Documento digitalizado
            │
            ▼
          FTP
            │
            ▼
      Aplicação Java
            │
            ▼
        Download
            │
            ▼
         Base64
            │
            ▼
     Google Cloud Vision
            │
            ▼
          OCR
            │
            ▼
     Extração de dados
            │
            ▼
         Sankhya
            │
            ▼
    Registro do documento
            │
       ┌────┴────┐
       ▼         ▼
    Processado  Lixeira

---

# ⚙️ Execução

## Requisitos

Para executar o projeto são necessários, conforme o ambiente utilizado:

- Java 8
- Maven
- Acesso ao servidor FTP
- Acesso à API do Google Cloud Vision
- Acesso ao Sankhya
- Credenciais válidas para os serviços utilizados

---

## ▶️ Inicialização

O ponto de entrada da aplicação é:

    Main.Main

A aplicação inicia o processamento e executa continuamente os módulos:

    Notas_Manuais
    Notas_Automaticas
    Bombas
    Vistorias

---

# 🔐 Configuração

As credenciais e informações sensíveis não devem ficar diretamente no código-fonte.

Uma configuração mais segura deve utilizar variáveis de ambiente ou arquivos de configuração externos.

Exemplo conceitual:

    FTP_HOST
    FTP_USERNAME
    FTP_PASSWORD

    GOOGLE_API_KEY

    SANKHYA_USERNAME
    SANKHYA_PASSWORD

Essas informações não devem ser versionadas no Git.

---

# ⚠️ Segurança

> **Importante:** o projeto original contém credenciais e chaves diretamente no código-fonte.

Antes de publicar este projeto em um repositório público:

- Remova credenciais do código.
- Revogue e gere novamente chaves expostas.
- Altere senhas que tenham sido utilizadas no projeto.
- Utilize variáveis de ambiente.
- Não publique tokens de API.
- Não publique credenciais de FTP.
- Não publique credenciais do Sankhya.
- Revise o histórico do Git caso os segredos já tenham sido commitados.

---

# 🧹 Gerenciamento dos Arquivos

Uma característica importante do projeto é a organização automática dos documentos após o processamento.

O sistema trabalha basicamente com dois destinos:

### Processadas

Documentos que tiveram o processamento concluído.

    Processadas/

### Lixeira

Documentos que apresentaram problemas ou não puderam ser processados corretamente.

    Lixeira/

Isso permite manter o diretório de entrada organizado e separar os documentos de acordo com seu estado.

---

# 📚 Conhecimentos Aplicados

O projeto reúne diversos conceitos de desenvolvimento de software.

### Java

- Programação Orientada a Objetos
- Manipulação de arquivos
- Exceções
- Streams de dados
- Datas e horários
- Base64
- Comunicação de rede

### Integração

- APIs HTTP
- JSON
- FTP
- Integração com ERP
- Integração com serviço de OCR

### Automação

- Processamento automático de documentos
- OCR
- Extração de informações
- Organização automática de arquivos
- Processamento contínuo

### Sistemas Empresariais

- Integração com ERP
- Registro de documentos
- Controle de processos
- Identificação de documentos
- Fluxos de processamento

---

# 💡 O que este projeto demonstra

O **Notas Fiscais** demonstra experiência prática na construção de uma aplicação de integração capaz de conectar diferentes tecnologias em um único fluxo.

O projeto combina:

    Java
      +
    FTP
      +
    Google Vision
      +
    OCR
      +
    Processamento de JSON
      +
    HTTP
      +
    Sankhya
      =
    Automação de processamento de documentos

Além do desenvolvimento em Java, o projeto envolve a criação de fluxos automatizados para reduzir operações manuais e conectar sistemas independentes.

---

# 📌 Status

> 🚧 Projeto desenvolvido anteriormente.

O projeto representa uma aplicação de automação e integração desenvolvida para processamento de documentos e comunicação entre serviços externos.

---

# 👨‍💻 Autor

**Ryan Alvim**

Desenvolvedor interessado em:

- ☕ Java
- 🔧 Backend
- 🤖 Automação
- 🔗 Integração de sistemas
- 🗄️ Banco de dados
- 🌐 Desenvolvimento de software

### Contato

- GitHub: [@RyanAlvim](https://github.com/RyanAlvim)
- E-mail: [ryanalvim65@gmail.com](mailto:ryanalvim65@gmail.com)

---

<p align="center">
  Desenvolvido com ☕ Java
</p>
