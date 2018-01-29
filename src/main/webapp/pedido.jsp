<%-- 
    Document   : pedido
    Created on : 09/10/2017, 20:35:16
    Author     : leo_l
--%>


<%@page import="Modelo.Carrinho"%>
<%-- 
    Document   : telaCadastro
    Created on : 27/02/2017, 15:01:26
    Author     : Leonardo
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Componente"%>
<%@page import="Modelo.Usuario"%>
<!DOCTYPE html>
<html>
    <head>  
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/custom.css"  media="screen,projection"/>        
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/css?family=Abel" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Bungee+Inline|Gruppo" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Palanquin+Dark" rel="stylesheet">
        <meta charset="utf-8"/>
    </head>

    <body>

        <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

            if (usuario != null) {


        %>

        <!-- Dropdown de funcionalidades de usuários -->

        <ul id="dropdown2" class="dropdown-content">
            <li><a class="grey darken-4 grey-text text-lighten-1 hoverable" href="consultarPedidoPorUsuario"><i class="material-icons left red-text text-darken-4">shopping_cart</i>Meus pedidos</a></li>
            <li class="divider"></li>
            <li><a class="grey darken-4 grey-text text-lighten-1" href="consultarPorId?id=<%=usuario.getIdUsuario()%>"><i class="material-icons left red-text text-darken-4">perm_identity</i>Meu perfil</a></li>
            <li class="divider"></li>
        </ul>

        <!-- Fim da lista de funcionalidades -->

        <!-- Inicío Barra de Navegação -->
        <div class="navbar-fixed">

            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li><a href="" class="dropdown-button hoverable grey-text text-lighten-1 modal-trigger" data-activates="dropdown2"><i class="material-icons left red-text text-darken-4">group</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i><%=usuario.getLogin()%></a></li>
                        <li><a href="principal.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>    <br>
        <!-- Fim da Barra de Navegação -->


        <!-- Finalização do Pedido -->
        <main>
            <div class="container">
                <div class="row">

                    <% Carrinho carrinho = null;

                        if ((carrinho = (Carrinho) request.getSession().getAttribute("carrinho")) != null) {
                        double somaPreco = 0;
                            for (Componente comp : carrinho.getComponentes()) {
                            somaPreco += comp.getPreco();
                    %>
                    <div class="col s12">
                        <form action="cadastrarPedido" method="POST">
                            <ul class="collapsible" data-collapsible="accordion">
                                <li>
                                    <div class="collapsible-header"><i class="material-icons">layers</i><%=comp.getModelo()%>
                                        <span class="precoComponente alinhado-direita">R$ <%=comp.getPreco()%></span>
                                    </div>
                                    <div class="collapsible-body">

                                        <p class="descricaoComponente">Descrição: <%=comp.getDescricao()%></p>                        
                                    </div>
                                </li>
                            </ul>
                        </form>
                    </div>
                    <%}%>
                    <div class="col s12 right-align">Total R$ <%=somaPreco%> </div>
                    <%}%>
                    
                </div> 
                <div class="row">
                    <div class="col s4">
                        <a class="waves-effect waves-light grey darken-4 btn left btnPedido" href="cadastrarPedido"><i class="material-icons left">done</i>Finalizar Pedido</a>
                    </div>
                     <div class="col s4">
                        
                    </div>
                     <div class="col s4">
                        <a class="waves-effect waves-light grey darken-4 btn right" href="itensCarrinhoComponente"><i class="material-icons left">arrow_back</i>Voltar á loja</a>
                    </div>
                    
                </div>
                
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
                        <ul class="social-nav model-9 center-align">
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
        <script type="text/javascript" src="js/carrinho.js"></script>
        <script type="text/javascript" src="js/main.js"></script>

    </body>
</html>


