<%-- 
    Document   : cadastroComponente
    Created on : 10/04/2017, 08:30:36
    Author     : Leonardo
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.TipoAtributo"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Atributo"%>
<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Componente</title>
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
        <!-- Dropdown das pe�as -->
        <ul id="dropdown1" class="dropdown-content">
            
        </ul>
        <!-- Inic�o Barra de Navega��o -->
        <div class="navbar-fixed">
            
                <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

                    if (usuario != null) {


                %>
            
            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li><a href="#modal1" class="hoverable grey-text text-lighten-1 modal-trigger"><i class="material-icons left red-text text-darken-4">group</i><%=usuario.getLogin()%></a></li>
                        <li><a class="dropdown-button hoverable grey-text text-lighten-1" href="#!" data-activates="dropdown1">Componentes<i class="material-icons left red-text text-darken-4">layers</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                        <li><a href="indexAdm.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>    <br>
                        
        <!-- Fim da Barra de Navega��o -->

        <!-- In�cio do Modal(Login) -->
        <div id="modal1" class="modal">

            <div class="modal-content grey darken-3 center-align">
                <h4 class="grey-text text-lighten-3">Fa�a o Login em PC MAKER</h4>
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

        <!-- Formul�rio de cadastro de Componente -->
        
        <main>

        <div class="container">
            <div class="row">
                <form class="col s12" method="post" action="cadastrarTipoComponente">
                    
                    <div class="row">
                        
                        <div class="input-field col s12">
                            <input name="nomeComponente" id="nomeComponente" type="text" class="validate hoverable" maxlength="50" required>
                            <label for="nomeComponente" class="grey-text text-darken-4"><i class="material-icons left brown-text text-lighten-2">layers</i>Componente</label>
                        </div> 
                    </div>
                    
                    <!-- Op��es dos atributos -->
                    <div class="row">
                        <h5><label for="instrucao" class="grey-text text-darken-4"><i class="material-icons left red-text text-darken-4">info_outline</i> Selecione os atributos que deseja para este componente </label></h5>
                    </div>
                    
                    
                    
                    <div class="row">
                        
                        <% List<TipoAtributo> listaTipoAtributo = (List<TipoAtributo>) request.getAttribute("listaTipoAtributos");
                             for ( TipoAtributo at : listaTipoAtributo ){ %>     
                        
                        <div class="col s3">
                            <input type="checkbox" name="opcaoAtributo" class="filled-in" id="<%=at.getNomeAtributo()%>" value="<%=at.getId()%>">
                            <label for="<%=at.getNomeAtributo()%>"><%=at.getNomeAtributo()%></label>
                        </div>
                        
                        <%}%>
                        
                    </div>    
                    
                    <div class="row">
                     
                    </div>
                    <input type="submit" class="btn waves-effect waves-light grey darken-4" value="Cadastrar Componente">
                </form>
            </div>
        </div>
        </main>
 

    <!-- Fim do formul�rio de cadastro de componente -->

</div>
   

<!-- In�cio do Rodap� -->
<footer class="page-footer grey darken-4">
    <div class="container">
        <div class="row">
            <div class="col l6 s12 left-align">
                <h5 class="grey-text text-lighten-1"><i class="material-icons left green-text text-darken-2">local_grocery_store</i> Escolha as pe�as </h5>
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
            � 2017 Todos os direitos reservados de PC MAKER | Vers�o 1.0
            <a class="grey-text text-lighten-1 right" href="#!">Fale Conosco</a>
        </div>
    </div>
</footer>

<%}%>
<!-- Fim do Rodap� -->

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/customJS.js"></script>
<script src="https://use.fontawesome.com/93d491e836.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
