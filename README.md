<h1 align="center">Mentorr - Conectando experiências</h1>

<p align="center">
  <img src="https://raw.githubusercontent.com/alessfm/mentorr/main/web/src/assets/images/logo-m.svg" width="160px" alt="Logo do Mentorr">
  <br>
  <em>Projeto de Pós-graduação na UniFAP</em>
</p>

<p align="center">
  <a href="https://alessfm.github.io/mentorr/"><strong>alessfm.github.io/mentorr/</strong></a>
</p>

<p align="center">
  <a href="https://www.npmjs.com/@angular/core">
    <img alt="GitHub package.json version" src="https://img.shields.io/github/package-json/v/alessfm/mentorr?filename=web%2Fpackage.json&style=flat-square&label=release&labelColor=2f363d&color=396ef1">
  </a>
  <a href="https://github.com/alessfm/mentorr/actions/workflows/deploy.yml">
    <img alt="GitHub deploy status" src="https://github.com/alessfm/mentorr/actions/workflows/deploy.yml/badge.svg">
  </a>
  <a href="https://www.npmjs.com/@angular/core">
    <img alt="GitHub Created At" src="https://img.shields.io/github/created-at/alessfm/mentorr?style=flat-square&labelColor=2f363d&color=396ef1">
  </a>
</p>

<hr>

## Descrição
Website no qual pode-se obter orientação personalizada, com mentores experientes, acerca de dúvidas relacionadas ao ambiente de trabalho.
A missão é auxiliar as pessoas a alcançarem seus objetivos profissionais, seja na busca por uma promoção, numa mudança de emprego, na criação de uma startup, ou em outras dificuldades enfrentadas na carreira.

## Tecnologias utilizadas

| Área            | Linguagem        | Framework                   |
|-----------------|------------------|-----------------------------|
| Front-end       | TypeScript       | Angular                     |
| Back-end        | Java             | Spring Boot                 |
| Banco de dados  | SQL              | [PostgreSQL][postgresql]    |

## Execução do Front-end

### Pré-requisitos
Instale o [**Node.js 14.17.0**][node].

### Etapas
1. Acesse a pasta `web`:
    ```bash
    cd web
    ```

2. Instale as dependências:
    ```bash
    npm install
    ```

3. Execute a aplicação:
    ```bash
    npm start
    ```

4. Acesse [localhost:4200](http://localhost:4200/) para ver a interface.

## Execução do Back-end

### Pré-requisitos
Instale o [**Java 11**][java] e o [**Spring Tools Suite**][spring].

### Etapas
1. Abra o Spring Tools Suite e selecione a pasta do repositório `mentorr` como _workspace_. Em seguida, clique em `Launch`;
2. Vá em **File > Import**, selecione **Maven > Existing Maven Projects** e clique em `Next`. Selecione a pasta `api` como _Root Directory_ e clique em `Finish`;
3. Vá em **Help > Install New Software** e cole o link (https://projectlombok.org/p2) no _Work with_, clique em `Select All` e `Next`. Novamente, clique em `Next`. Aceite os termos de uso e clique em `Finish`;
4. Clique com o botão direito na pasta `api`, localizada na barra lateral, e vá em **Run As > Run Configurations**;
5. Na campo _Main type_, selecione `com.projeto.mentorr.MentorrApplication`;
6. Na campo _Profile_, selecione `dev`;
7. Na aba _Enviroment_, adicione os segredos abaixo:

    ![print_segredos](image.png)

8. Clique em `Apply` e `Run`;
9. Acesse [localhost:8780](http://localhost:8780/swagger-ui/index.html) para ver as rotas.

### Banco de Dados

1. Crie um banco de dados chamado `mentorr`, com esquema `public`;
2. Após executar o `back-end`, as tabelas serão criadas automaticamente;
2. Popule as tabelas:
    ```sql
    INSERT INTO public.roles (name) VALUES ('ROLE_GESTAO', 'ROLE_ALUNO', 'ROLE_MENTOR');
    INSERT INTO public.tags (nome) VALUES ('ux/ui design', 'back-end', 'front-end', 'devops', 'agile');
    ```

[postgresql]: https://sbp.enterprisedb.com/getfile.jsp?fileid=1259097
[node]: https://nodejs.org/pt/download
[java]: https://www.oracle.com/java/technologies/downloads/#java11-windows
[spring]: https://spring.io/tools
