<%-- 
    Document   : principal
    Created on : 07/04/2017, 19:34:39
    Author     : Leonardo
--%>

<%@page import="Modelo.Usuario"%>
<!DOCTYPE html>
<html>
    <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

        if (usuario != null) {

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem-vindo, <%=usuario.getNomeCompleto()%></title>

        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/custom.css"  media="screen,projection"/>        
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/css?family=Bungee+Inline|Gruppo" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Palanquin+Dark" rel="stylesheet">
    </head>
    <body>

        <!-- Dropdown das peças -->
        <ul id="dropdown1" class="dropdown-content">

        </ul>

        <ul id="dropdown2" class="dropdown-content">
            <li><a class="grey darken-4 grey-text text-lighten-1 hoverable" href="consultarPedidoPorUsuario"><i class="material-icons left red-text text-darken-4">shopping_cart</i>Meus pedidos</a></li>
            <li class="divider"></li>
            <li><a class="grey darken-4 grey-text text-lighten-1" href="consultarPorId?id=<%=usuario.getIdUsuario()%>"><i class="material-icons left red-text text-darken-4">perm_identity</i>Meu perfil</a></li>     
        </ul>

        <header>
            <!-- Inicío Barra de Navegação -->
            <div class="navbar-fixed">
                <nav> 
                    <div class="nav-wrapper grey darken-4">
                        <a href="#" class="brand-logo">PC MAKER</a>
                        <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                            <li><a class="dropdown-button hoverable grey-text text-lighten-1 modal-trigger" data-activates="dropdown2"><i class="material-icons left red-text text-darken-4">group</i><%=usuario.getLogin()%><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                            <li><a class="dropdown-button hoverable grey-text text-lighten-1" href="#!" data-activates="dropdown1">Componentes<i class="material-icons left red-text text-darken-4">layers</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                            <li><a href="principal.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
                        </ul>
                    </div>
                </nav>
            </div>    <br>

        </header>
        <!-- Fim da Barra de Navegação --> 

        <!-- Início área usuário comum -->

        <main>
            <div class="container">
                <div class="row">
                    <div class="col s6">
                        <div class="card-panel grey darken-4 hoverable">
                            <center><h5 class="grey-text text-lighten-1">IR PARA LOJA</h5></center>
                            <center><a href="itensCarrinhoComponente"><i class="large material-icons green-text text-darken-3">add_shopping_cart</i></a></center>
                            <span class="grey-text text-lighten-1">Você será redirecionado para a loja, e poderá escolher as configurações para seu computador. 
                            </span>
                        </div>
                    </div>

                    <div class="col s6">
                        <div class="card-panel grey darken-4 hoverable">
                            <center><h5 class="grey-text text-lighten-1">LOGOUT DO SISTEMA</h5></center>
                            <center><a href="ControleAcesso?acao=Sair"><i class="large material-icons red-text text-darken-4">power_settings_new</i></a></center>
                            <span class="grey-text text-lighten-1"> Aqui você fará logout do sistema e não poderá finalizar pedidos até realizar uma
                                nova autenticação.
                            </span>    
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col s6">
                        <div class="card-panel grey darken-4 hoverable">
                            <center><h5 class="grey-text text-lighten-1">ALTERAR INFORMAÇÕES DO PERFIL</h5></center>
                            <center><a href="consultarPorId?id=<%=usuario.getIdUsuario()%>"><i class="large material-icons grey-text text-lighten-1">settings</i></a></center>
                            <span class="grey-text text-lighten-1">Você poderá alterar suas informações pessoais de cadastro, endereços e mante-los atualizados. 
                            </span>
                        </div>
                    </div>

                    <div class="col s6">
                        <div class="card-panel grey darken-4 hoverable">
                            <center><h5 class="grey-text text-lighten-1">MEUS PEDIDOS</h5></center>
                            <center><a href="consultarPedidoPorUsuario"><i class="large material-icons brown-text text-lighten-3">description</i></a></center>
                            <span class="grey-text text-lighten-1"> Você poderá consultar todas as informações referente aos pedidos que foram realizados.
                            </span>    
                        </div>
                    </div>

                </div>

            </div>
        </main>

        <!-- Fim área usuário comum -->

    </div>
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
    <script src="js/main.js" type="text/javascript"></script>
</body>
</html>
