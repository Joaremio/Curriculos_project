# Currículo Online - Projeto de Envio de Currículos

Este projeto permite que candidatos a vagas de emprego enviem seus currículos através de um formulário web. Os dados são validados e os arquivos são armazenados em uma pasta local chamada uploads. Além disso, o sistema envia um e-mail de confirmação para o candidato, contendo os detalhes do currículo enviado.

## Funcionalidades

**Envio de Currículo:** O candidato pode enviar seu currículo com os seguintes dados: nome, e-mail, telefone, cargo desejado, escolaridade e um arquivo de currículo.

**Validação de Arquivos:** O sistema verifica se o formato do arquivo é válido e se o tamanho não excede o limite de 1MB.

**Armazenamento de Arquivos:** Os arquivos enviados são armazenados localmente na pasta uploads do projeto.

**Envio de E-mail de Confirmação:** O sistema envia um e-mail para o candidato confirmando o envio do currículo.

**Log de Atividades:** O sistema mantém um log detalhado das atividades para monitoramento e rastreamento de erros e eventos importantes.


## Arquitetura

- Frontend: A interface do usuário é construída usando HTML, CSS, JS, Thymeleaf e Bootstrap (para a renderização dinâmica das páginas no servidor). O formulário é responsivo e permite que o usuário faça o upload de seu currículo diretamente na plataforma.

- Backend: O backend é desenvolvido utilizando o Spring Boot, com a utilização de Spring MVC para a gestão das requisições HTTP, Spring Data JPA para a persistência de dados no banco de dados, e Spring Mail para o envio de e-mails. O arquivo enviado é validado em termos de formato e tamanho, antes de ser salvo no servidor.

- Banco de Dados: O banco de dados utilizado é MySQL, onde são armazenados os dados dos currículos recebidos, incluindo informações pessoais do candidato e os metadados do arquivo enviado.


## Backend: Tecnologias Utilizadas

- **Spring Boot** - Framework para construção da aplicação backend.
- **Spring MVC** - Para gerenciamento das requisições HTTP e visualizações.
- **Spring Data JPA** - Para acesso ao banco de dados.
- **Spring Mail** - Para o envio de e-mails.
- **MySQL** - Banco de dados para armazenamento dos currículos.
- **JavaMailSender** - Para enviar e-mails com o currículo anexado.

## Frontend: Interface do Usuário

A interface do usuário é construída utilizando as seguintes tecnologias:

- **HTML**: Estruturação da página e conteúdo.
- **CSS**: Estilização da página para torná-la visualmente agradável.
- **JavaScript**: Funcionalidade dinâmica e interatividade.
- **Thymeleaf**: Utilizado para a renderização dinâmica das páginas no servidor, permitindo integração de dados no frontend.
- **Bootstrap**: Framework CSS utilizado para tornar o design da interface responsivo e agradável, garantindo que a aplicação seja bem adaptada a diferentes tamanhos de tela.

O formulário de envio de currículo foi projetado para ser **responsivo**, permitindo que o usuário faça o upload de seu currículo diretamente na plataforma, com uma interface que se adapta automaticamente.

### Funcionalidades:
- O formulário possui campos para o usuário inserir seu **nome**, **e-mail** e **currículo** (arquivo).
- Utiliza o **Bootstrap** para garantir que o design seja limpo, responsivo e fácil de usar.

## Estrutura do Projeto

### 1. Armazenamento de Arquivos

Os arquivos enviados pelos candidatos são armazenados em uma pasta local chamada uploads. Isso permite que os arquivos sejam facilmente acessados e organizados no servidor.

- Os arquivos são salvos na pasta uploads dentro do diretório raiz do projeto.
- Apenas arquivos com formato permitido (ex.: .pdf, .docx) e tamanho inferior a 1MB são aceitos.

### 2. Envio de E-mail

Após o envio do currículo, um e-mail de confirmação é enviado para o candidato, informando que o currículo foi recebido com sucesso. O e-mail contém um resumo dos dados enviados e um anexo com o arquivo do currículo.

### 3. Logs no Projeto

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
git clone https://github.com/Joaremio/curriculo_project.git
cd curriculo_project
```

**Configuração do Banco de Dados MySQL**

Para rodar o projeto localmente, é necessário configurar o banco de dados MySQL e conectá-lo ao projeto.

- Instale o MySQL e o MySQL Workbench (caso ainda não tenha instalado):

- Configure o Usuário e Senha do MySQL:

- Certifique-se de que você tem um usuário com permissões adequadas no MySQL. O usuário padrão geralmente é root com a senha configurada durante a instalação do MySQL.
  
- Dentro do projeto localize o arquivo src/main/resources/application.properties.
  
- Substitua os valores padrão pelas configurações do seu ambiente MySQL. Exemplo:
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/curriculos
  spring.datasource.username=seu_usuario
  spring.datasource.password=sua_senha

- Execute o aplicativo:

```bash
./mvnw spring-boot:run
```

Acesse o formulário de envio de currículos em http://localhost:8080


## ⚠️ Alerta: Bloqueio de envio de e-mails por Antivírus ou Firewall

Ao clonar este projeto e testar o envio de e-mails, é possível que o antivírus ou firewall instalado no seu computador bloqueie a conexão com o servidor SMTP do Gmail (`smtp.gmail.com`) na porta 587. Isso pode impedir que o sistema envie e-mails corretamente.

### **Como Resolver:**
1. **Verifique se o antivírus está ativo:**
   - Alguns antivírus possuem configurações que bloqueiam conexões SMTP por padrão.
   - Consulte as configurações do seu antivírus e permita a aplicação ou a porta 587.

2. **Desative temporariamente o antivírus para testes:**
   - Apenas para testes locais, você pode desativar temporariamente o antivírus e verificar se o envio de e-mails funciona.

### **Importante:**
Este problema é específico de algumas configurações de segurança locais. Certifique-se de ajustar essas configurações apenas quando necessário e, se possível, reverta as alterações após os testes.


