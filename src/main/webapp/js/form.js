    
$(function(){
    //Atrelando evento de click ao botão e limpar todos os campos.
    $("#btn-limpar").click(limparCamposFormUsuario);
});


//Aplicação de máscara de CPF, CEP e Telefone
$('#cpf').mask('000.000.000-00',{placeholder: "xxx.xxx.xxx-xx"});
$('#cep').mask('00000-000',{placeholder: "xxxxx-xxx"});
$('#telefone').mask('(00) 0000-0000',{placeholder: "(DDD) 1234-5678"});

function limparCamposFormUsuario(){
    
    $("#nomeCompleto").val("");
    $("#login").val("");
    $("#senha").val("");
    $("#email").val("");
    $("#telefone").val("");
    $("#rg").val("");
    $("#cpf").val("");
    $("#cep").val("");
    $("#cidade").val("");
    $("#rua").val("");
    $("#bairro").val("");
    $("#uf").val("");
    $("#numero").val("");
    $("#complemento").val("");
   
}


function validaCPF(cpf)
  {
    var numeros, digitos, soma, i, resultado, digitos_iguais;
    digitos_iguais = 1;
    if (cpf.length < 11)
          return false;
    for (i = 0; i < cpf.length - 1; i++)
          if (cpf.charAt(i) != cpf.charAt(i + 1))
                {
                digitos_iguais = 0;
                break;
                }
    if (!digitos_iguais)
          {
          numeros = cpf.substring(0,9);
          digitos = cpf.substring(9);
          soma = 0;
          for (i = 10; i > 1; i--)
                soma += numeros.charAt(10 - i) * i;
          resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
          if (resultado != digitos.charAt(0))
                return false;
          numeros = cpf.substring(0,10);
          soma = 0;
          for (i = 11; i > 1; i--)
                soma += numeros.charAt(11 - i) * i;
          resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
          if (resultado != digitos.charAt(1))
                return false;
          return true;
          }
    else
        return false;
  }








