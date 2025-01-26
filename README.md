# Currículo Online - Projeto de Envio de Currículos

Este projeto permite que candidatos a vagas de emprego enviem seus currículos através de um formulário web. Os dados são validados e os arquivos são armazenados em uma pasta local chamada uploads. Além disso, o sistema envia um e-mail de confirmação para o candidato, contendo os detalhes do currículo enviado.

## Funcionalidades

**Envio de Currículo:** O candidato pode enviar seu currículo com os seguintes dados: nome, e-mail, telefone, cargo desejado, escolaridade e um arquivo de currículo.

**Validação de Arquivos:** O sistema verifica se o formato do arquivo é válido e se o tamanho não excede o limite de 1MB.

**Armazenamento de Arquivos:** Os arquivos enviados são armazenados localmente na pasta uploads do projeto.

**Envio de E-mail de Confirmação:** O sistema envia um e-mail para o candidato confirmando o envio do currículo.

**Log de Atividades:** O sistema mantém um log detalhado das atividades para monitoramento e rastreamento de erros e eventos importantes.

## Estrutura do Projeto

### 1. Validação de Dados

O sistema utiliza Bean Validation para garantir que os dados fornecidos no formulário de envio de currículos estejam corretos, como a verificação de e-mail, telefone e outros campos obrigatórios.

### 2. Armazenamento de Arquivos

Os arquivos enviados pelos candidatos são armazenados em uma pasta local chamada uploads. Isso permite que os arquivos sejam facilmente acessados e organizados no servidor.

- Os arquivos são salvos na pasta uploads dentro do diretório raiz do projeto.
- Apenas arquivos com formato permitido (ex.: .pdf, .docx) e tamanho inferior a 1MB são aceitos.

### 3. Envio de E-mail

Após o envio do currículo, um e-mail de confirmação é enviado para o candidato, informando que o currículo foi recebido com sucesso. O e-mail contém um resumo dos dados enviados e um anexo com o arquivo do currículo.

### 4. Logs no Projeto

O sistema utiliza SLF4J com Logger para manter logs de todas as atividades importantes e erros que possam ocorrer durante o processo de envio do currículo. Isso inclui:

- Registro de atividades como o recebimento e envio de currículos.
- Armazenamento de erros ao tentar salvar arquivos ou enviar e-mails.
- Monitoramento de eventos como falhas de validação ou problemas de configuração.

A configuração de logs ajuda a rastrear o comportamento do sistema e identificar problemas rapidamente.

## Requisitos

- **Java 17 ou superior**
- **Spring Boot 2.x**
- Dependências de Spring Boot para Mail, JPA, Validation, entre outras.
- Servidor de e-mail configurado para envio de mensagens.

## Como Rodar o Projeto

Clone este repositório:

```bash
git clone https://github.com/seu-usuario/curriculo-online.git
cd curriculo-online
```

Execute o aplicativo:

```bash
./mvnw spring-boot:run
```

Acesse o formulário de envio de currículos em http://localhost:8080/formulario.

