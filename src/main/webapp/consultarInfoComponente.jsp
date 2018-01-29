<%-- 
    Document   : cadastroComponente
    Created on : 10/04/2017, 08:30:36
    Author     : Leonardo
--%>

<%@page import="Modelo.TipoComponente"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.TipoAtributo"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Atributo"%>
<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alteração de Tipo Componente</title>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>     
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/css?family=Bungee+Inline|Gruppo" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Palanquin+Dark" rel="stylesheet">
    </head>
    <body>
        <!-- Dropdown das peças -->
        <ul id="dropdown1" class="dropdown-content">

        </ul>
        <!-- Inicío Barra de Navegação -->
        <div class="navbar-fixed">

            <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

                if (usuario != null) {


            %>

            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li><a href="#" class="hoverable grey-text text-lighten-1 modal-trigger"><i class="material-icons left red-text text-darken-4">group</i><%=usuario.getLogin()%></a></li>
                        <li><a class="dropdown-button hoverable grey-text text-lighten-1" href="#!" data-activates="dropdown1">Componentes<i class="material-icons left red-text text-darken-4">layers</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                        <li><a href="indexAdm.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>    <br>

        <!-- Fim da Barra de Navegação -->
        <main>
            <div class="col s10 off-set s2">
                <table class="highlight centered bordered responsive-table tabela-componente">
                    <thead>
                        <tr>
                            <th data-field="nomeTipoComponente">Tipo de Componente</th>
                            <th data-field="marca">Marca</th>
                            <th data-field="modelo">Modelo</th>                    
                            <th data-field="preco">Preço (R$)</th>
                            <th data-field="quantidade">Quantidade Disponível</th>
                        </tr>  
                    </thead>          

                    <tbody>    

                    </tbody>
                </table>
            </div>           
        </main>
        <!-- Início do Rodapé -->
        <footer class="page-footer grey darken-4">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12 left-align">
                        <h5 class="grey-text text-lighten-1"><i class="material-icons left green-text text-darken-2">local_grocery_store</i> Escolha as peças </h5>
                        <ul class="linkComponente">

                        </ul>
                    </div>

                    <div class="col l4 offset-l2 s12">
                        <h5 class="white-text grey-text text-lighten-1"><i class="material-icons left red-text text-red darken-4">share</i>Siga-nos nas redes sociais</h5>
                        <ul class="social-nav model-9">
                            <li><a href="#" class="twitter"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#" class="facebook"> <i class="fa fa-facebook"></i></a></li>
                            <li><a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a></li>
                            <li><a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a></li>
                        </ul>
                        <br/>
                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="container">
                    © 2017 Todos os direitos reservados de PC MAKER | Versão 1.0
                    <a class="grey-text text-lighten-1 right" href="#!">Fale Conosco</a>
                </div>
            </div>
        </footer>

        <%}%>
        <!-- Fim do Rodapé -->

        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="js/jQuery.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/customJS.js"></script>
        <script src="https://use.fontawesome.com/93d491e836.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script src="js/componente.js" type="text/javascript"></script>
        <link type="text/css" rel="stylesheet" href="css/custom.css"  media="screen,projection"/>   
    </body>
</html>


