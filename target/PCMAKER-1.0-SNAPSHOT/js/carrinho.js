/* global Materialize */

$(function () {
    consultarComponenteAJAX();
    verificaItensDaLista();
});

var linhaPrincipal = $(".divConteiner").find(".linhaPrincipal");
var divConteiner = $(".divConteiner");

function enviarDadosCarrinho(valorModeloComponente, objJSON) {

    var componente = objJSON.componentes[valorModeloComponente];
    var id = componente.id;

    $.ajax({
        url: "http://localhost:8084/PCMAKER/inserirComponenteNoCarrinho",
        type: "POST",
        data: {
            idComponente: id
        },
        success: function (data) {
            Materialize.toast(data, 5000);
            if (data.length > 0) {
                $(".btnFimCarrinho").attr("disabled", true);
            } else {
                $(".btnFimCarrinho").attr("disabled", false);
            }

        }
    });

}

function excluirDadosCarrinho(valorModeloComponente, objJSON) {

    var componente = objJSON.componentes[valorModeloComponente];
    var id = componente.id;

    $.ajax({
        url: "http://localhost:8084/PCMAKER/removerComponenteDoCarrinho",
        type: "POST",
        data: {
            idComponente: id
        }
    });
}

function consultarComponenteAJAX() {

    $.get("http://localhost:8084/PCMAKER/consultarComponenteAJAX", exibeComponentes)

            .fail(function () {
                var erro = $("#erro");
                erro.toggle();
                setTimeout(function () {
                    erro.fadeOut();
                }, 1800);
            });
}

function exibeComponentes(data) {

    var objJSON = JSON.parse(data);


    $.each(objJSON, function (i) {
        var index = i;
        console.log(objJSON[i].nomeComponente);
        var bloco = montarBlocosDeComponentes(objJSON[index]);
        linhaPrincipal.append(bloco);
        bloco.find("#add-carrinho" + objJSON[i].id).click(function () {
            //Retorna o value do Combobox gerado dinamicamente
            var valorModeloComponente = $("#selectModeloComponente" + objJSON[index].id).val();
            //Seleciona a lista de componentes que será criada (carrinho)
            var listaComponentesCarrinho = $(".listaComponente");

            //Retorna o componente e atribui na variável "li"
            var li = montarItemCarrinho(valorModeloComponente, objJSON[index]);

            //Insere o componente na lista de componentes
            listaComponentesCarrinho.append(li);

            //Soma o consumo de Watts
            somaConsumoWatts(valorModeloComponente, objJSON[index]);

            //Soma o preço dos componentes
            somaValoresComponente(valorModeloComponente, objJSON[index]);

            //Envia os dados para o back-end inserir o componente no carrinho (sessão)
            enviarDadosCarrinho(valorModeloComponente, objJSON[index]);
            var componente = objJSON[index].componentes[valorModeloComponente];
            var atributos = objJSON[index].componentes[valorModeloComponente].atributos;
            divConteiner.append(listaComponentesCarrinho);
            verificaItensDaLista();
            reservarComponenteCarrinho(valorModeloComponente, objJSON[index]);
//          verificarCompatibilidade();


            //Ao clicar no botão "Remover do Carrinho", ele executa a função de remover do carrinho.
            $(".btn-excluirCarrinho" + componente.id).click(function () {
                excluirDadosCarrinho(valorModeloComponente, objJSON[index]);
                $(".itemCarrinho" + componente.id).first().remove();
                var somaValores = $(".valorTotal").text();
                var somaWatts = $(".wattsConsumidos").text();
                atualizaValorTotal(somaValores, componente);
                atualizaConsumoTotal(somaWatts, atributos);
                verificaItensDaLista();
                atualizaWatts(valorModeloComponente, objJSON[index]);
                var quantidadeAtual = verificaQuantidadeComponente(objJSON[index].componentes[valorModeloComponente]);
                retornarQuantidadeComponente(valorModeloComponente, objJSON[index], quantidadeAtual);
            });
        });

        // Ao mudar o valor do campo Select da combobox, executa as funções abaixo.
        $("#selectModeloComponente" + objJSON[index].id).change(function () {
            var valorModeloComponente = $("#selectModeloComponente" + objJSON[index].id).val();
            var quantidade = verificaQuantidadeComponente(objJSON[index].componentes[valorModeloComponente]);
            console.log(quantidade);
            if (quantidade < 1) {
                Materialize.toast("Você reservou a quantidade maxima deste componente", 4000);
                $("#add-carrinho" + objJSON[index].id).attr("disabled", true);
            } else {
                $("#add-carrinho" + objJSON[index].id).attr("disabled", false);
            }
        });
    });

    $("select").material_select();
}

function verificarCompatibilidade() {

    $.ajax({

        url: "http://localhost:8084/PCMAKER/somarWattsComponentes",
        type: "POST",
        success: function (data) {
            console.log(data);
        }
    });
}

function montarBlocosDeComponentes(objJSON) {

    var divPrincipal = $("<div>")
            .addClass("col")
            .addClass("s4");

    var divSecundaria = $("<div>")
            .addClass("card-panel")
            .addClass("grey")
            .addClass("darken-4")
            .addClass("hoverable")
            .addClass("blocoComponente");

    var h5 = montarTipoComponente(objJSON);

    var center2 = $("<center>");

    var iconeComponente = $("<i>")
            .attr("id", "iconeLayer")
            .addClass("large")
            .addClass("material-icons")
            .addClass("cyan-text")
            .addClass("text-lighten-1")
            .text("layers");

    center2.append(iconeComponente);

    var selectComponentes = montarSelectDeComponente(objJSON);
    var optionComponente = montarModeloDeComponente(objJSON, selectComponentes);
    selectComponentes.append(optionComponente);

    var linkAddCarrinho = $("<a>")
            .attr("id", "add-carrinho" + objJSON.id)
            .addClass("btn")
            .addClass("waves-effect")
            .addClass("waves-green")
            .addClass("grey")
            .addClass("lighten-3")
            .addClass("grey-text")
            .addClass("text-darken-3")
            .text("Colocar no carrinho");

    var span = $("<span>")
            .addClass("grey-text")
            .addClass("text-lighten-1");

    var centerInfomarcoes = $("<center>");

    var iconeAdd = $("<i>")
            .addClass("medium")
            .addClass("material-icons")
            .addClass("left")
            .addClass("red-text")
            .addClass("text-darken-5")
            .text("add");

    span.append(centerInfomarcoes);

    var span2 = $("<span>")
            .addClass("grey-text")
            .addClass("text-darken-4");

    var centerLinha = $("<center>")

            .addClass("grey-darken-4")
            .text("____________________________________");

    span2.append(centerLinha);

    var spanCarrinho = $("<span>")
            .addClass("grey-text")
            .addClass("text-lighten-1");

    var centerCarrinho = $("<center>");

    var iconeCarrinho = $("<i>")
            .addClass("medium")
            .addClass("material-icons")
            .addClass("left")
            .addClass("green-text")
            .addClass("text-darken-1")
            .text("add_shopping_cart");

    linkAddCarrinho.append(iconeCarrinho);
    centerCarrinho.append(linkAddCarrinho);
    spanCarrinho.append(centerCarrinho);

    divSecundaria
            .append(h5)
            .append(center2)
            .append(selectComponentes)
            .append(span)
            .append(span2)
            .append(spanCarrinho);

    divPrincipal.append(divSecundaria);

    return divPrincipal;
}

function montarSelectDeComponente(objJSON) {

    var select = $("<select>")
            .attr("id", "selectModeloComponente" + objJSON.id)
            .addClass("grey")
            .addClass("lighten-3")
            .attr("required", true);

    var option = $("<option>")
            .attr("disabled", true)
            .attr("selected", true)
            .text("Escolha o Componente");

    select.append(option);

    return select;
}

function montarTipoComponente(objJSON) {

    var h5 = $("<h5>")
            .addClass("nomeTipoComponente")
            .addClass("grey-text")
            .addClass("grey-lighten-4")
            .addClass("center-align")
            .addClass("destaque")
            .addClass("fonte-tipo-componente")
            .text(objJSON.nomeComponente);
    return h5;
}

function montarModeloDeComponente(objJSON, selectComponentes) {

    var componentes = objJSON.componentes;
    var selectComponente = selectComponentes;
    $.each(componentes, function (i) {
        var optionComponente = $("<option>");
        optionComponente.addClass("valorModeloComponente")
                .attr("value", i)
                .text(componentes[i].modelo);

        selectComponentes.append(optionComponente);
    });

    return selectComponente;
}

function montarItemCarrinho(valorModeloComponente, objJSON) {

    var componente = objJSON.componentes[valorModeloComponente];

    var atributos = objJSON.componentes[valorModeloComponente].atributos;

    var li = $("<li>")
            .addClass("item")
            .addClass(objJSON.nomeComponente)
            .addClass("itemCarrinho" + componente.id);

    var div1 = $("<div>")
            .addClass("collapsible-header");

    var span = $("<span>")
            .addClass("modeloComponente")
            .text(objJSON.componentes[valorModeloComponente].modelo);

    var span2 = $("<span>")
            .addClass("precoComponente")
            .addClass("alinhado-direita")
            .text("R$ " + componente.preco);

    var iconeLayer = $("<i>")
            .addClass("material-icons")
            .text("layers");

    div1.append(iconeLayer);
    div1.append(span);
    div1.append(span2);
    li.append(div1);

    var div2 = $("<div>")
            .addClass("collapsible-body");


    var botaoRemoverCarrinho = $("<a>")
            .addClass("waves-effect")
            .addClass("waves-light")
            .addClass("grey")
            .addClass("darken-4")
            .addClass("btn")
            .addClass("right")
            .addClass("btn-excluirCarrinho" + componente.id)
            .text("Remover do Carrinho");

    div2.append(botaoRemoverCarrinho);

    $.each(atributos, function (index) {

        var pInfoAtr = $("<p>")
                .text(atributos[index].tipoAtributo.nomeAtributo + " : " + atributos[index].valor);

        div2.append(pInfoAtr);
        li.append(div2);
    });

    return li;
}

function somaConsumoWatts(valorModeloComponente, objJSON) {

    var atributos = objJSON.componentes[valorModeloComponente].atributos;
    var somaConsumo = $(".wattsConsumidos").text();
    somaConsumo = parseFloat(somaConsumo);

    $.each(atributos, function (index) {
        if (atributos[index].tipoAtributo.nomeAtributo === "Consumo Watts") {
            var consumoComponente = atributos[index].valor;
            consumoComponente = parseFloat(consumoComponente);
            somaConsumo = somaConsumo + consumoComponente;
        }
    });
    $(".wattsConsumidos").text(somaConsumo);
}

function somaValoresComponente(valorModeloComponente, objJSON) {

    var componente = objJSON.componentes[valorModeloComponente];
    var somaValores = $(".valorTotal").text();

    somaValores = parseFloat(somaValores);

    somaValores += componente.preco;

    $(".valorTotal").text(somaValores);

}

function atualizaValorTotal(somaValores, componente) {
    somaValores -= componente.preco;
    $(".valorTotal").text(somaValores);
}

function atualizaConsumoTotal(somaWatts, atributos) {

    $.each(atributos, function (index) {
        if (atributos[index].tipoAtributo.nomeAtributo === "Consumo Watts") {
            somaWatts -= atributos[index].valor;
        }
    });
    $(".wattsConsumidos").text(somaWatts);
}

function verificaItensDaLista() {

    var itensCarrinho = $(".listaComponente").find(".item").length;
    var btnFimCarrinho = $(".btnFimCarrinho");

    if (itensCarrinho > 0) {
        btnFimCarrinho.removeClass("invisivel");
    } else {
        btnFimCarrinho.addClass("invisivel");
    }
}

function alertaWatts(valorModeloComponente, objJSON) {

    var atributos = objJSON.componentes[valorModeloComponente].atributos;

    $.each($(".Fonte"), function () {
        $.each(atributos, function (index) {
            if (atributos[index].tipoAtributo.nomeAtributo === "Potencia") {
                var potencia = atributos[index].valor;
                potencia = parseFloat(potencia);
                var consumoEstimado = $(".wattsConsumidos").text();
                consumoEstimado = parseFloat(consumoEstimado);
                var totalConsumo = +consumoEstimado;
                console.log(totalConsumo);

                if (totalConsumo > potencia) {
                    var botaoFimCarrinho = $(".btnFimCarrinho");
                    botaoFimCarrinho.attr("disabled", true);
                }
            }
        });
    });

}

function exibeFontesCompativeis(componente) {

    var consumoEstimado = $(".wattsConsumidos").text();
    consumoEstimado = parseFloat(consumoEstimado);
    var totalConsumo = +consumoEstimado;

    var atributos = componente.atributos;
    $.each(atributos, function (index) {
        if (atributos[index].tipoAtributo.nomeAtributo === "Potencia") {
            var potencia = atributos[index].valor;
            potencia = parseFloat(potencia);
            if (totalConsumo > potencia) {
                $(".btnFimCarrinho").attr("disabled", true);
            } else {
                $(".btnFimCarrinho").attr("disabled", false);
            }
        }
    });

}

function atualizaWatts(valorModeloComponente, objJSON) {
    var watts = $(".wattsConsumidos").text();
    watts = parseFloat(watts);

    var componente = objJSON.componentes[valorModeloComponente];
    var atributos = objJSON.componentes[valorModeloComponente].atributos;

    if (atributos.valor > watts) {
        $(".btnFimCarrinho").attr("disabled", false);
    }

}

function reservarComponenteCarrinho(valorModeloComponente, objJSON) {

    var componente = objJSON.componentes[valorModeloComponente];
    componente.quantidade = componente.quantidade - 1;
    if (componente.quantidade === 0) {
        $("#add-carrinho" + objJSON.id).attr("disabled", true);
        Materialize.toast("Você já selecionou a quantidade máxima deste componente", 4000);
    } else {
        $("#add-carrinho" + objJSON.id).attr("disabled", false);
    }
}

function verificaQuantidadeComponente(componente) {
    return componente.quantidade;
}

function retornarQuantidadeComponente(valorModeloComponente, objJSON, quantidadeAtual) {

    var componente = objJSON.componentes[valorModeloComponente];
    componente.quantidade += 1;
    if (componente.quantidade !== 0) {
        $("#add-carrinho" + objJSON.id).attr("disabled", false);
    } else {
        $("#add-carrinho" + objJSON.id).attr("disabled", true);
    }
}


