$(function () {
    consultarUsuarioAJAX();
    exibeUsuariosEEnderecos();
});

function exibeUsuariosEEnderecos(data) {

    var objJSON = JSON.parse(data);
    console.log(objJSON);
    var corpoTabela = $(".tabela-usuario").find("tbody");

    $.each(objJSON, function (i) {
        linhaUsuario = criaLinha(objJSON[i]);
        corpoTabela.append(linhaUsuario);
    });

}

function criaLinha(objJSON) {

    var tr = $("<tr>");
    var colunaIdUsuario = $("<td>").text(objJSON.idUsuario);
    var colunaNomeUsuario = $("<td>").text(objJSON.nomeCompleto);
    var colunaLogin = $("<td>").text(objJSON.login);
    var colunaEmail = $("<td>").text(objJSON.email);
    var colunaCpf = $("<td>").text(objJSON.cpf);
    var colunaRg = $("<td>").text(objJSON.rg);
    var colunaTipoAdm = $("<td>").text(objJSON.tipoAdm);
    var colunaDeletar = $("<td>");
    var colunaAlterar = $("<td>");
    var linkExcluir = $("<a>").attr("href", "deletarUsuario?id=" + objJSON.idUsuario).text("Excluir");
    var linkAlterar = $("<a>").attr("href", "alterarUsuario?id=" + objJSON.idUsuario).text("Alterar");

    //Colocando links de Excluir e Alterar dentro do TD
    colunaDeletar.append(linkExcluir);
    colunaAlterar.append(linkAlterar);

    //Colocando os TD's na TR's
    tr.append(colunaIdUsuario);
    tr.append(colunaNomeUsuario);
    tr.append(colunaLogin);
    tr.append(colunaEmail);
    tr.append(colunaCpf);
    tr.append(colunaRg);

    var enderecos = objJSON.enderecos;

    $.each(enderecos, function (i) {
        var colunaRua = $("<td>").text(enderecos[i].rua);
        var colunaBairro = $("<td>").text(enderecos[i].bairro);
        var colunaCep = $("<td>").text(enderecos[i].cep);
        var colunaUf = $("<td>").text(enderecos[i].uf);
        var colunaNum = $("<td>").text(enderecos[i].numero);
        var colunaCidade = $("<td>").text(enderecos[i].cidade);
        var colunaComplemento = $("<td>").text(enderecos[i].complemento);

        tr.append(colunaRua);
        tr.append(colunaBairro);
        tr.append(colunaCep);
        tr.append(colunaUf);
        tr.append(colunaNum);
        tr.append(colunaTipoAdm);
        tr.append(colunaCidade);
        tr.append(colunaComplemento);

    });

    tr.append(colunaDeletar);
    tr.append(colunaAlterar);

    return tr;
}

function consultarUsuarioAJAX(){
    
    $.get("http://localhost:8084/PCMAKER/consultarUsuarioAJAX",exibeUsuariosEEnderecos);
}



