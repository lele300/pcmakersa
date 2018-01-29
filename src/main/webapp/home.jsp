 <!DOCTYPE html>
 <%@page contentType="utf-8"%>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html;"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>PC Maker - Monte aqui seu PC !</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/custom.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="https://fonts.googleapis.com/css?family=Bungee+Inline|Gruppo" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Palanquin+Dark" rel="stylesheet">
    </head>
    <body>
        
        <% String mensagem = (String) request.getAttribute("msg");
        
           if(mensagem == null){
               mensagem = "";
           } 
        %>
        <!-- Dropdown das peças -->
        <ul id="dropdown1" class="dropdown-content">
            
        </ul>
        <!-- Inicío Barra de Navegação -->
        <div class="nav-content">
            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo grey-text text-lighten-1 left">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li><a href="#modal1" class="hoverable grey-text text-lighten-1 modal-trigger"><i class="material-icons left red-text text-darken-4">group</i>Entrar/Cadastrar</a></li>
                        <li><a class="dropdown-button hoverable grey-text text-lighten-1" href="#!" data-activates="dropdown1">Componentes<i class="material-icons left red-text text-darken-4">layers</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                        <li><a href="home.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>
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
                            <input id="icon_prefix" type="text" class="validate login" name="login" required>
                            <label for="login" class="grey-text text-darken-4">Login</label>
                        </div>

                    </div>

                    <div class="row">         
                        <div class="input-field col s12">
                            <i class="material-icons prefix grey-text text-darken-4">lock_open</i>
                            <input id="icon_telephone" type="password" class="validate senha" name="senha" required>
                            <label for="senha" class="grey-text text-darken-4">Senha</label>   
                        </div>                  
                    </div>
                    
                    <div>
                        <center><span class="erro-login msg-erro"><%=mensagem%></span></center>
                    </div>
                    <br>
                    
                    <div class="row">
                        <div class="col s6 left-align">
                            <input id="entrar" type="submit" class="btn waves-effect light-green lighten-1 btn-logar" name="acao" value="Entrar">                     
                        </div> 
                        
                        <div class="col s6 right-align">
                            <a href="cadastroUsuario.jsp" class="btn waves-effect light-blue lighten-1">Cadastre-se</a>                
                        </div> 
                    </div>     
                </form>
            </div>
        </div>
        <!-- Fim do Modal -->


        <!-- Início da index -->

        <div id="index-banner" class="parallax-container">
            <div class="section no-pad-bot">
                <div class="container">
                    <br><br>
                    <h1 class="header center grey-text text-lighten-4 slogan">MONTE SEU PC SEM PREOCUPAÇÕES</h1>
                    <div class="row">
                        <h5 class="header col s12 light"></h5>
                    </div>
                    <div class="row center">
                        <a href="cadastroUsuario.jsp" class="btn-large waves-effect waves-light green lighten-1 hoverable teste"><i class="material-icons left">subject</i>Cadastre-se agora</a>
                    </div>
                    <br><br>

                </div>
            </div>
            <div class="parallax"><img src="img/fundo7.jpg" alt="Imagem de Fundo bits"></div>
        </div>


        <div class="container">
            <div class="section">

                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center black-text"><i class="material-icons">flash_on</i></h2>
                            <h5 class="center">Compatibilidade de Fonte</h5>

                            <p class="light center">É garantida a compatibilidade de fonte entre as peças que você escolher. Não queremos que você tenha qualquer problema na sua máquina.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center grey-text text-darken-1"><i class="material-icons">settings</i></h2>
                            <h5 class="center bordaEscrita">Escolha sem preocupações</h5>

                            <p class="light center">Você é o nosso principal componente. Com um sistema inteligente de cálculo de Watts, você não precisa se preocupar. Escolha sem medo.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center red-text text-darken-2"><i class="material-icons">layers</i></h2>
                            <h5 class="center">Diferencial do mercado</h5>

                            <p class="light center">Nós garantimos á você que sua máquina não sofrerá problemas relacionados a falta de potência para sua configuração.</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>


        <div class="parallax-container valign-wrapper">
            <div class="section no-pad-bot">
                <div class="container">
                    <div class="row center">
                        <h4 class="header col s12">Comece a configurar sua máquina por aqui.</h4>
                    </div>

                    <div class="row center">
                        <h6 class="header col s12"><a href="carrinho.jsp" class="btn-large waves-effect waves-light green lighten-1 hoverable"><i class="material-icons left">store</i>Ir para as compras</a></h6>
                    </div>
                </div>
            </div>
            <div class="parallax"><img src="img/fundo.jpg" alt="Unsplashed background img 2"></div>
        </div>

        <div class="container">
            <div class="section">

                <div class="row">
                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center green-text text-darkin-2"><i class="material-icons">https</i></h2>
                            <h5 class="center">Sua compra é segura</h5>

                            <p class="light center">Seus dados estão totalmente protegidos e encriptados pelos algoritmos mais seguros do mundo.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center blue-text text-darkin-2"><i class="material-icons">query_builder</i></h2>
                            <h5 class="center">Entrega rápida</h5>

                            <p class="light center">Trabalhamos com transportadora própria, o que evita atraso nas entregas dos clientes.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center yellow-text text-darkin-2"><i class="material-icons">star</i></h2>
                            <h5 class="center">Boas recomendações</h5>

                            <p class="light center">Nossa plataforma garante ao consumidor total suporte na pré-venda e pós-venda</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- Fim da Index -->

        <!-- Início do Rodapé -->
        <footer class="page-footer grey darken-4">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12 left-align">
                        <h5 class="grey-text text-lighten-1"><i class="material-icons left green-text text-darken-2">local_grocery_store</i> Peças mais procuradas </h5>
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
        <!-- Fim do Rodapé -->


        <!--  Scripts-->
        <script type="text/javascript" src="js/jQuery.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script src="js/customJS.js"></script>
        <script src="js/init.js"></script>
        <script src="https://use.fontawesome.com/93d491e836.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script src="js/validacaoLogin.js" type="text/javascript"></script>
    </body>
</html>
