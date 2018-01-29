/* global Materialize */

$(function () {
    consultarComponenteAJAX();
});

function consultarComponenteAJAX() {

    $.get("http://localhost:8084/PCMAKER/consultarComponenteAJAX", exibirInfoComponentes)

            .fail(function () {
                var erro = $("#erro");
                erro.toggle();
                setTimeout(function () {
                    erro.fadeOut();
                }, 1800);
            });
}

function exibirInfoComponentes(data) {

    var objJSON = JSON.parse(data);

    var corpoTabela = $(".tabela-componente").find("tbody");
    
    $.each(objJSON,function(index){     
       var componentes = objJSON[index].componentes;
       $.each(componentes,function(i){
                    
          var tr = $("<tr>").addClass("componente"+objJSON[index].componentes[i].id);
          var colunaTipoComponente = $("<td>").text(objJSON[index].nomeComponente);
          var colunaMarca = $("<td>").text(objJSON[index].componentes[i].marca);
          var colunaModelo = $("<td>").text(objJSON[index].componentes[i].modelo);
          var colunaPreco = $("<td>").text("R$ "+objJSON[index].componentes[i].preco);
          var colunaQuantidade = $("<td>");
          var inputQuantidade = $("<input>")
                  .addClass("inputComponente")
                  .attr("name","quantidade")
                  .attr("id","componente"+objJSON[index].componentes[i].id)
                  .attr("type","number")
                  .attr("min",objJSON[index].componentes[i].quantidade)
                  .css("width","10%")
                  .attr("value",objJSON[index].componentes[i].quantidade);
          colunaQuantidade.append(inputQuantidade);        
          
          tr.append(colunaTipoComponente);
          tr.append(colunaMarca);
          tr.append(colunaModelo);
          tr.append(colunaPreco);
          tr.append(colunaQuantidade);
          corpoTabela.append(tr);
          
          $("#componente"+objJSON[index].componentes[i].id).change(function(){             
              var idComponente = objJSON[index].componentes[i].id;
              var quantidadeAlterada = $("#componente"+objJSON[index].componentes[i].id).val(); 
              if(quantidadeAlterada < objJSON[index].componentes[i].quantidade){
                Materialize.toast("Nao e possivel atribuir uma quantidade menor que a anterior",3000);
                $("#componente"+objJSON[index].componentes[i].id).val(objJSON[index].componentes[i].quantidade);
              }else {
              Materialize.toast("A quantidade do componente "+objJSON[index].componentes[i].modelo +" foi alterada para "+quantidadeAlterada+"!",3000);
              alterarQuantidadeAJAX(idComponente,quantidadeAlterada);
          }
              
          });
       });
       
    });

}

function alterarQuantidadeAJAX(idComponente,quantidadeAlterada){
    
    $.ajax({        
        url: "http://localhost:8084/PCMAKER/alterarQuantidadeComponente",
        type: "POST",
        data: {
            idComp: idComponente,
            qtdeComponente: quantidadeAlterada
        }
    });
}