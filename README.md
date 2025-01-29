# mentorr
**Projeto de Pós-graduação na UniFAP** 

Uma aplicação que possibilita o compartilhamento de conhecimento entre alunos e mentores.

## Tecnologias utilizadas

### Back-end

* [Java 11](http://www.oracle.com)

### Banco de dados

* [PostgreSQL 12](https://sbp.enterprisedb.com/getfile.jsp?fileid=1259097)

## Instalação

### Back-end

  1. Instale e abra o [`Spring Tools Suite`](https://spring.io/tools);
  2. Selecione o workspace como a pasta do **_mentorr_**;
  3. Vá em `File > Import > Maven > Existing Maven Projects` e no 'browse', selecione a pasta `api` e clique em `Finish`;
  4. Vá em `Help > Install New Software` e adicione o `lombok` via o link do [website](https://projectlombok.org/p2) dele;
  5. Clique com o botão direito no projeto e clique `Run As` > `Run Configurations`;
  6. Na aba `Main type`, selecione com.projeto.mentorr.MentorrApplication;
  7. Na aba `Profile`, coloque 'dev';
  8. Na aba `Enviroment`, cole os _secrets_;
  - ![print_segredos](image.png)
  9. Clique `Apply` > `Run`;

O Banco será gerado automaticamente...

## Rotas
Acesse o link: http://localhost:8780/swagger-ui/index.html