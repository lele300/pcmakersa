
$(document).ready(function () {
    $("#formUsuario").validate({
        rules: {
            
            cpf: {
                cpf: true,
                required: true
            },
            email: {
                email:true,
                required: true
            }
        
        },
        messages: {
            cpf: {
                cpf: "CPF inválido!",
                required: "Por favor, informe um CPF"
            },
            email: {
                email: "Insira um e-mail válido",
                required: "Este campo é obrigatório"
            }
                     
        },
        errorElement: "em",
        errorPlacement: function (error, element) {
            // Add the `help-block` class to the error element
            error.addClass("help-block");

            if (element.prop("name") === "email") {
                error.insertAfter(".erro-email");
            } else if (element.prop("name") === "nome") {
                error.insertAfter(".erro-nome");
            } else if (element.prop("name") === "senha") {
                error.insertAfter(".erro-senha");
            } else if (element.prop("name") === "confirme") {
                error.insertAfter(".erro-confirme");
            } else if (element.prop("name") === "cpf") {
                error.insertAfter(".erro-cpf");
            } else if (element.prop("name") === "cep") {
                error.insertAfter(".erro-cep");
            } else if (element.prop("name") === "telefone") {
                error.insertAfter(".erro-telefone");
            } else if (element.prop("name") === "dataNascimento") {
                error.insertAfter(".erro-dataNascimento");
            } else if (element.prop("name") === "estado") {
                error.insertAfter(".erro-estado");
            } else if (element.prop("name") === "cidade") {
                error.insertAfter(".erro-cidade");
            } else if (element.prop("name") === "bairro") {
                error.insertAfter(".erro-bairro");
            } else if (element.prop("name") === "endereco") {
                error.insertAfter(".erro-endereco");
            } else if (element.prop("name") === "numero") {
                error.insertAfter(".erro-numero");
            } else if (element.prop("name") === "descricao") {
                error.insertAfter(".erro-descricao");
            } else if (element.prop("name") === "valor") {
                error.insertAfter(".erro-valor");
            } else if (element.prop("name") === "produtos") {
                error.insertAfter(".erro-produtos");
            } else {
                error.insertAfter(".erro-data");
            }
        }
    });
});
