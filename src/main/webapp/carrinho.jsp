<%-- 
    Document   : telaCadastro
    Created on : 27/02/2017, 15:01:26
    Author     : Leonardo
--%>

<%@page import="Modelo.Atributo"%>
<%@page import="Modelo.TipoAtributo"%>
<%@page import="Modelo.Carrinho"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Componente"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <link href="https://fonts.googleapis.com/css?family=Bungee+Inline|Gruppo" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Palanquin+Dark" rel="stylesheet">
    </head>

    <body>

        <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
            Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
            if(carrinho == null){
                Carrinho carrinhoNovo = new Carrinho();
                request.getSession().setAttribute("carrinho", carrinhoNovo);
            }
            double somaWatts = 0;
            double somaPreco = 0;
            if (usuario != null) {
                for(Componente comp : carrinho.getComponentes()){
                    somaPreco += comp.getPreco();
                        if (!"Fonte".equals(comp.getTipoComponente().getNomeComponente())) {
                            for (Atributo at : comp.getAtributos()) {
                                if ("Consumo Watts".equals(at.getTipoAtributo().getNomeAtributo())) {
                                    double wattsComponente = Double.parseDouble(at.getValor());
                                    somaWatts += wattsComponente;
                                }
                            }
                        }
                }
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
        <% String mensagem = (String) request.getAttribute("mensagem");%>
        <div class="navbar-fixed">
            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li class="grey-text text-lighten-1">R$ <i class="material-icons left green-text text-darken-4">attach_money</i><span class="valorTotal grey-text text-lighten-1 alinhado-esquerda"><%=somaPreco%></span></li>
                        <li class="grey-text text-lighten-1"><i class="material-icons left yellow-text text-darken-2">flash_on</i><span class="wattsConsumidos grey-text text-lighten-1 alinhado-esquerda"><%=somaWatts%></span>W</li>
                        <li><a href="" class="dropdown-button hoverable grey-text text-lighten-1 modal-trigger" data-activates="dropdown2"><i class="material-icons left red-text text-darken-4">group</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i><%=usuario.getLogin()%></a></li>
                        <li><a href="principal.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>    <br>
        <!-- Fim da Barra de Navegação -->

        <!-- Início do Modal(Login) -->
        <div id="modal1" class="modal">

            <div class="modal-content grey darken-3 center-align">
                <h4 class="grey-text text-lighten-3">Faça o Login em PC MAKER</h4>
            </div>
            <div class="modal-content grey lighten-4">

                <form class="col s12" action="ControleAcesso" method="POST">
                    <div class="row">

                        <div class="input-field col s12">
                            <i class="material-icons prefix grey-text text-darken-4">account_circle</i>
                            <input id="icon_prefix" type="text" class="validate" name="login" required>
                            <label for="login" class="grey-text text-darken-4">Login</label>
                        </div>

                    </div>

                    <div class="row">         
                        <div class="input-field col s12">
                            <i class="material-icons prefix grey-text text-darken-4">lock_open</i>
                            <input id="icon_telephone" type="password" class="validate" name="senha" required>
                            <label for="senha" class="grey-text text-darken-4">Senha</label>   
                        </div>                  
                    </div>

                    <div class="row left-align">
                        <div class="col s6 left-align">
                            <input type="submit" class="btn waves-effect light-green lighten-1" name="acao" value="Entrar">                           
                        </div> 

                        <div class="col s6 right-align">
                            <input type="submit" class="btn waves-effect indigo lighten-1" name="acao" value="Com Facebook">
                        </div>
                    </div>     
                </form>

                <li class="divider grey darken-2"></li>

                <div class="modal-footer grey lighten-4">
                    <div class="row">

                    </div>
                    <div class="row">
                        <div class="col s7 left-align">
                            <h6> <a href="#" class="indigo-text text-darken-3"> Esqueceu sua senha ? </a> </h6>
                        </div>

                        <div class="col s5 right-align">
                            <h6><a href="cadastroUsuario.jsp" class="indigo-text text-darken-3"> Cadastre-se aqui !</a></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fim do Modal -->


        <!-- Carrinho de Compras -->

        <div class="container divConteiner">

            <div class="row linhaPrincipal">   

            </div>   


            <ul class="collapsible listaComponente" data-collapsible="accordion">
                <%
                    for (Componente comp : carrinho.getComponentes()) {
                        
                %>
                <li class="item <%=comp.getTipoComponente()%> itemCarrinho<%=comp.getId()%>">
                    <div class="collapsible-header">
                        <i class="material-icons">layers</i>
                        <span class="modeloComponente"><%=comp.getModelo()%></span>
                        <span class="precoComponente alinhado-direita">R$ <%=comp.getPreco()%></span>
                    </div>

                    <div class="collapsible-body">
                        <a class="waves-effect waves-light grey darken-4 btn right btn-excluirCarrinho<%=comp.getId()%>">Remover do Carrinho</a>
                        <% for (Atributo at : comp.getAtributos()) {
                        %>
                        <p><%=at.getTipoAtributo().getNomeAtributo()%> : <%=at.getValor()%></p>
                        <%}%>
                    </div>
                </li>
                <%}%>
            </ul>

        </div>

        <div class="container">
            <a class="waves-effect waves-light grey darken-4 btn right btnFimCarrinho" href="pedido.jsp">Finaliza Carrinho</a>
        </div> <br>

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
    </body>
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="js/jQuery.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <script type="text/javascript" src="js/customJS.js"></script>
    <script src="https://use.fontawesome.com/93d491e836.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/carrinho.js"></script>
</html>


