/* global Materialize */

$(function () {
    consultarPedidoAJAX();
});


function consultarPedidoAJAX() {
    $.get("http://localhost:8084/PCMAKER/consultarPedidoAJAX", exibirPedido);
}

function exibirPedido(data) {

    var objJSON = JSON.parse(data);
    var tabela = $(".tabelaPedido").find("tbody");

    $.each(objJSON, function (i) {

        linhaPedido = criaLinhaPedido(objJSON[i]);
        tabela.append(linhaPedido);
        $(".btnAlterarStatus" + objJSON[i].idPedido).click(function () {
            var valorStatusPedido = $("#selectPedido" + objJSON[i].idPedido).val();
            var selectStatusPedido = $("#selectPedido" + objJSON[i].idPedido);
            var idPedido = objJSON[i].idPedido;
            alterarStatusAJAX(idPedido, valorStatusPedido);
            Materialize.toast("Status do Pedido alterado para "+valorStatusPedido,5000);
            if(valorStatusPedido === "PAGO" || valorStatusPedido === "RECUSADO"){
                var divSelect = selectStatusPedido.closest("div");
                divSelect.attr("disabled",true);
            }
        });

    });
    $("select").material_select();
}

function criaLinhaPedido(objJSON) {

    var tr = $("<tr>");
    var colunaIdPedido = $("<td>")
            .text(objJSON.idPedido);

    var colunaNomeUsuario = $("<td>");
    var colunaAlterarStatus = $("<td>");

    var colunaTelefone = $("<td>");

    var colunaDtPedido = $("<td>")
            .text(objJSON.dataPedido);

    var colunaValorTotal = $("<td>")
            .text("R$ " + objJSON.valorTotal);

    var colunaStatusPedido = $("<td>");

    var linkAlterarStatus = $("<a>")
            .addClass("waves-effect")
            .addClass("waves-light")
            .addClass("grey")
            .addClass("darken-4")
            .addClass("btn")
            .addClass("btnAlterarStatus" + objJSON.idPedido)
            .text("Alterar Status");

    var selectPedido = $("<select>")
            .attr("id", "selectPedido" + objJSON.idPedido);

    var option1 = $("<option>")
            .attr("value", objJSON.statusPedido)
            .attr("selected", true)
            .text(objJSON.statusPedido);

    var option2 = $("<option>")
            .attr("value", "PAGO")
            .text("PAGO");
    
    var option4 = $("<option>")
            .attr("value", "CANCELADO")
            .text("CANCELADO");

    selectPedido.append(option1);
    selectPedido.append(option2);
    selectPedido.append(option4);
    colunaStatusPedido.append(selectPedido);

    tr.append(colunaIdPedido);

    var usuarioDoPedido = objJSON.usuarioDoPedido;

    colunaNomeUsuario.text(usuarioDoPedido.nomeCompleto);
    tr.append(colunaNomeUsuario);

    colunaTelefone.text(usuarioDoPedido.telefone);
    tr.append(colunaTelefone);

    tr.append(colunaDtPedido);
    tr.append(colunaValorTotal);
    tr.append(colunaStatusPedido);

    var enderecos = objJSON.usuarioDoPedido.enderecos;

    $.each(enderecos, function (i) {
        var colunaRua = $("<td>");
        var colunaCidade = $("<td>");
        var colunaUf = $("<td>");

        colunaRua.text(enderecos[i].rua);
        tr.append(colunaRua);

        colunaCidade.text(enderecos[i].cidade);
        tr.append(colunaCidade);

        colunaUf.text(enderecos[i].uf);
        tr.append(colunaUf);
    });
    
    colunaAlterarStatus.append(linkAlterarStatus);
    tr.append(colunaAlterarStatus);
    
    if(objJSON.statusPedido === "PAGO" || objJSON.statusPedido === "CANCELADO"){
        selectPedido.attr("disabled",true);
    }

    return tr;   
}

function alterarStatusAJAX(idPedido, valorStatusPedido) {

    $.ajax({
        url: "http://localhost:8084/PCMAKER/alterarStatusPedido",
        type: "GET",
        data: {
            id: idPedido,
            statusPedido: valorStatusPedido
        }
    });

}































