# springMVC-api-vila
Projeto do curso DevInHouse - Spring/Java

- nome da aplicação: VilaDevInHouse
- arquivo de rotas do Postman: Vila DevInHouse.postman_collection.json (importar via Postman)
- rota de login: /login (gera o Bearer Token, copiar e colar nas outras rotas)
- rota para listar os habitantes da vila (apenas nome e ID): / residents/list
- rota para listar os habitantes por nome:/residents/filter?name=X
- rota para listar os habitanes por mês de nascimento: /residents/filter-month?month=X
- rota para listar habitantes com idade igual ou superior a X anos: /residents/filter-age?age=X
- rota para cadastro de habitantes: /residents/create
- rota para detalhamento de um habitante via ID (todos os atributos menos o ID): /residents/X
- rota para deletar um habitante: /residents/delete?id=X
- rota para relatório financeiro da vila: /residents/report
- rota para recuperar senha via email: /auth/forgot
