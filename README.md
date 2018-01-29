# PCMAKER S.A
Trabalho de conclusão de curso na Universidade de Mogi das Cruzes.

Este sistema foi construído com orientação dos professores da Universidade de Mogi das Cruzes com as tecnologias JAVA, JPA, servidor TOM CAT, HTML , CSS e muito Javascript :D. O sistema é capaz de calcular o consumo médio de watts por componente escolhido e avisar o usuário caso a fonte não suporte.

Para testar o projeto, é necessário criar um banco de dados chamado "pcmaker" no banco de dados POSTGRESQL (driver de conexão do JPA está configurado neste SGBD).

Ao realizar a execução do projeto, todas as tabelas serão criadas automaticamente, juntamente com seus relacionamentos.

Para que tudo funcione corretamente, é necessário criar os "Tipos de Atributos" do sistema na área administrativa, que serão as características como Clock, Potencia, Velocidade do HD, entre outros. O cadastro da "Potência" deve ser escrita da seguinte forma: Potencia, caso contrário não será possível realizar o cálculo dos componentes.

Ao cadastrar um "Tipo de Componente" (semelhante á uma categoria), a categoria "Fontes" deve ser cadastrada da seguinte forma: "Fonte", caso contrário, não será possível realizar o cálculo dos componentes.



