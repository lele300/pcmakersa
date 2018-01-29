<%-- 
    Document   : relatorioAdm
    Created on : 27/10/2017, 14:36:24
    Author     : leo_l
--%>

<%@page import="Modelo.Usuario"%>
 <%@page contentType="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;">
        <title>Relatórios</title>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/custom.css"  media="screen,projection"/> 
        <link href="https://fonts.googleapis.com/css?family=Aldrich" rel="stylesheet">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/css?family=Bungee+Inline|Gruppo" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Palanquin+Dark" rel="stylesheet">
        
    </head>
    <body>
        
        <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

                if (usuario != null) {

                String noCall = ""; %>
        <!-- Dropdown das peças -->
        <ul id="dropdown1" class="dropdown-content">

        </ul>
        <!-- Inicío Barra de Navegação -->
        <div class="nav-content">
            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo grey-text text-lighten-1 left">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li><a href="#modal1" class="hoverable grey-text text-lighten-1 modal-trigger"><i class="material-icons left red-text text-darken-4">group</i><%=usuario.getLogin()%></a></li>
                        <li><a class="dropdown-button hoverable grey-text text-lighten-1" href="#!" data-activates="dropdown1">Componentes<i class="material-icons left red-text text-darken-4">layers</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                        <li><a href="indexAdm.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <!-- Fim da Barra de Navegação -->
        <main>
            <h3 class="tipografia center-align">Selecione abaixo os tipos de relatórios disponíveis</h3>

            <div class="container">

                <div class="input-field col s12">
                    <select class="selectRelatorio">
                        <option value="" disabled selected>Selecione um relatório abaixo...</option>
                        <option value="1">VENDAS FINALIZADAS</option>
                    </select>
                </div>

                <div class="row">
                    <div class="col s6">
                        <label for="dataInicial" class="grey-text text-darken-4"><i class="material-icons left">event</i>Data Inicial</label>
                        <input name="dataInicial" class="dataInicial" id="dataInicial" type="text" class="validate hoverable">                   
                    </div>

                    <div class="col s6">
                        <label for="dataFinal" class="grey-text text-darken-4"><i class="material-icons left">event</i>Data Final</label>
                        <input name="dataFinal" class="dataFinal" id="dataFinal" type="text" class="validate hoverable">
                    </div>
                </div>

                <a class="waves-effect waves-light grey darken-4 btn center gerarRelatorio" href="#"><i class="material-icons medium left">assessment</i>Gerar relatório</a> <br> <br>
            </div>

            <div id="container" style="min-width: 810px; height: 600px; max-width: 600px; margin: 0 auto"></div>

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
    </body>
    <!--  Scripts-->
    <script type="text/javascript" src="js/jQuery.js"></script>
    <script src="js/jquery.mask.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <script src="js/customJS.js"></script>
    <script src="js/init.js"></script>
    <script src="https://use.fontawesome.com/93d491e836.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="js/relatorio.js" charset="utf-8" type="text/javascript"></script>
</html>
