<%-- 
    Document   : listaClientes
    Created on : 02/03/2017, 20:48:30
    Author     : Leonardo
--%>


<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.TipoAtributo"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Atributo"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Atributos cadastrados</title>
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
        <!-- Inicío Barra de Navegação -->
        <div class="navbar-fixed">

            <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

                if (usuario != null) {

                String noCall = "";
            %>

            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li><a href="<%=noCall%>" class="dropdown-button hoverable grey-text text-lighten-1 modal-trigger"><i class="material-icons left red-text text-darken-4">group</i><%=usuario.getLogin()%></a></li>
                        <li><a class="dropdown-button hoverable grey-text text-lighten-1" href="#!" data-activates="dropdown1">Componentes<i class="material-icons left red-text text-darken-4">layers</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                        <li><a href="indexAdm.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
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

        <div class="col s10 off-set s2">
            <table class="highlight centered bordered responsive-table">
                <thead>
                    <tr>
                        <th data-field="idAtributo">ID</th>
                        <th data-field="nomeAtributo">Atributo</th>
                        <th data-field="excluir">Excluir</th>
                    </tr>  
                </thead>     

                <% List<TipoAtributo> listaTipoAtributo = (List<TipoAtributo>) request.getAttribute("listaJSP ");
                    for (TipoAtributo at : listaTipoAtributo) {%>     
                
                <tbody>
                    <tr>
                        <td><%=at.getId()%></td>
                        <td><%=at.getNomeAtributo()%></td>                           
                        <td><b><a href="deletarTipoAtributo?id=<%=at.getId()%>"/>Excluir</b> </td>
                    </tr>
                </tbody>
                <%}%>
            </table>
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
        <script type="text/javascript" src="js/main.js"></script>

    </body>
</html>

