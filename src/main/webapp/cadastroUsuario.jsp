<%-- 
    Document   : telaCadastro
    Created on : 27/02/2017, 15:01:26
    Author     : Leonardo
--%>

<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <meta charset="utf-8"/>
    </head>

    <body>
        <!-- Dropdown das peças -->
        <ul id="dropdown1" class="dropdown-content">
           
        </ul>
        <!-- Inicío Barra de Navegação -->
        <div class="navbar-fixed">


            <nav> 
                <div class="nav-wrapper grey darken-4">
                    <a href="#" class="brand-logo">PC MAKER</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down grey darken-4">
                        <li><a href="#modal1" class="hoverable grey-text text-lighten-1 modal-trigger"><i class="material-icons left red-text text-darken-4">group</i>Entrar/Cadastrar</a></li>
                        <li><a class="dropdown-button hoverable grey-text text-lighten-1" href="#!" data-activates="dropdown1">Componentes<i class="material-icons left red-text text-darken-4">layers</i><i class="material-icons right red-text text-darken-4">arrow_drop_down</i></a></li>
                        <li><a href="home.jsp" class="hoverable grey-text text-lighten-1"><i class="material-icons left red-text text-darken-4">home</i>Home</a></li>
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


        <!-- Formulário Cadastro Cliente -->                

        <div class="container">
            <div class="row" >
                <form id="formUsuario" class="col s12" action="cadastrarUsuario" method="POST" >

                    <div class="row">
                        <div class="input-field col s12">
                            <input name="nomeCompleto" id="nomeCompleto" type="text" class="hoverable erro-nome" maxlength="100" required>
                            <label for="nomeCompleto" class="grey-text text-darken-4"><i class="material-icons left">directions_walk</i>Nome Completo</label>
                        </div>
                    </div>


                    <div class="row">
                        <div class="input-field col s6">

                            <input name="login" id="login" type="text" class="validate hoverable erro-logiin" required>
                            <label for="login" class="grey-text text-darken-4"><i class="material-icons left">account_circle</i>Login</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="senha" id="senha" type="password" class="validate hoverable erro-senha" minlength="6" required>
                            <label for="senha" class="grey-text text-darken-4"><i class="material-icons left">lock_open</i>Senha</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s6">
                            <input name="email" id="email" type="email" class="validate hoverable erro-email" required>
                            <label for="email" class="grey-text text-darken-4" data-wrong="errado"><i class="material-icons left">contact_mail</i>E-mail</label>
                        </div>

                        <div class="input-field col s6">
                            <input name="telefone" id="telefone" type="text" class="validate hoverable erro-telefone" required>
                            <label for="telefone" class="grey-text text-darken-4"><i class="material-icons left">contact_phone</i>Telefone</label>
                        </div>
                    </div>

                    <div class="row">


                        <div class="input-field col s6">
                            <input name="rg" id="rg" type="text" class="validate hoverable erro-rg" maxlength="16" required>
                            <label for="rg" class="grey-text text-darken-4"><i class="material-icons left">style</i>RG</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="cpf" id="cpf" type="text" class="validate hoverable erro-cpf" maxlength="11" required>
                            <label for="cpf" class="grey-text text-darken-4"><i class="material-icons left">style</i>CPF</label>
                        </div>
                    </div>

                    <div class="row">

                        <div class="input-field col s6">
                            <input name="cep" id="cep" type="text" class="validate hoverable erro-cep" maxlength="8" required>
                            <label for="cep" class="grey-text text-darken-4"><i class="material-icons left">local_shipping</i>CEP</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="cidade" id="cidade" type="text" class="validate hoverable erro-cidade" required>
                            <label for="cidade" class="grey-text text-darken-4"><i class="material-icons left">satellite</i>Cidade</label>
                        </div>
                    </div>


                    <div class="row">

                        <div class="input-field col s12">
                            <input name="rua" id="rua" type="text" class="validate hoverable erro-rua" required>
                            <label for="rua" class="grey-text text-darken-4"><i class="material-icons left">place</i>Rua</label>
                        </div>
                    </div>

                    <div class="row">


                        <div class="input-field col s4">
                            <input name="bairro" id="bairro" type="text" class="validate hoverable erro-bairro" required>
                            <label for="bairro" class="grey-text text-darken-4"><i class="material-icons left">place</i>Bairro</label>
                        </div>

                         <div class="input-field col s4">
                            <select name="uf" id="uf" class="hoverable validate erro-estado" required>
                                <option value="" disabled selected>Selecione um estado...</option>
                                <option value="AC">Acre</option> 
                                <option value="AL">Alagoas</option> 
                                <option value="AM">Amazonas</option> 
                                <option value="AP">Amapá</option> 
                                <option value="BA">Bahia</option> 
                                <option value="CE">Ceará</option> 
                                <option value="DF">Distrito Federal</option> 
                                <option value="ES">Espírito Santo</option> 
                                <option value="GO">Goiás</option> 
                                <option value="MA">Maranhão</option> 
                                <option value="MT">Mato Grosso</option> 
                                <option value="MS">Mato Grosso do Sul</option> 
                                <option value="MG">Minas Gerais</option> 
                                <option value="PA">Pará</option> 
                                <option value="PB">Paraíba</option> 
                                <option value="PR">Paraná</option> 
                                <option value="PE">Pernambuco</option> 
                                <option value="PI">Piauí</option> 
                                <option value="RJ">Rio de Janeiro</option> 
                                <option value="RN">Rio Grande do Norte</option> 
                                <option value="RO">Rondônia</option> 
                                <option value="RS">Rio Grande do Sul</option> 
                                <option value="RR">Roraima</option> 
                                <option value="SC">Santa Catarina</option> 
                                <option value="SE">Sergipe</option> 
                                <option value="SP">São Paulo</option> 
                                <option value="TO">Tocantins</option>
                            </select>
                        </div>

                        <div class="input-field col s4">
                            <input name="numero" id="numero" type="text" class="validate hoverable erro-numero" maxlength="6" required>
                            <label for="numero" class="grey-text text-darken-4"><i class="material-icons left">looks_6</i>Nº</label>
                        </div>
                    </div>


                    <div class="row">
                        <div class="input-field col s12">
                            <textarea name="complemento" id="complemento" class="materialize-textarea hoverable erro-complemento"></textarea>
                            <label for="complemento" class="grey-text text-darken-4"><i class="material-icons left">flag</i>Complemento</label>
                        </div>
                    </div>                    

                    <input type="submit" class="btn waves-effect waves-light grey darken-4 btn-cadastrar" value="Cadastrar"> 
                    <a class="waves-effect waves-light grey darken-4 btn right" id="btn-limpar"><i class="material-icons left">clear</i>Limpar Campos</a>

                </form>

                <!-- Fim do formulário de Cadastro -->
            </div>
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
        <!-- Fim do Rodapé -->



        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="js/jQuery.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/customJS.js"></script>
        <script src="https://use.fontawesome.com/93d491e836.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script src="js/jquery.validate.js" type="text/javascript"></script>
        <script src="js/jquery-validator.js" type="text/javascript"></script>
        <script src="js/validar-cpf.js" type="text/javascript"></script>
        
    </body>
</html>

