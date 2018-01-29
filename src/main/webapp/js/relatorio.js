

/* global Highcharts, Materialize, Highchart */



var selectRelatorio = $(".selectRelatorio");

$('#container').hide();

$(".gerarRelatorio").click(function () {


    var valorSelect = selectRelatorio.val();
    if (valorSelect === "1") {
        $("#container").show();
        gerarRelatorioSomaVendas();
        
    }
});


function gerarRelatorioSomaVendas() {

    var dataInicial = $(".dataInicial").val();
    var dataFinal = $(".dataFinal").val();
    
    $.ajax({
        url: "http://localhost:8084/PCMAKER/gerarRelatorioSomaDeVendas",
        type: "GET",
        data: {
            dtInicial: dataInicial,
            dtFinal: dataFinal
        },
        success: function (data) {
            montarGrafico(data);
        }
    });

}


function montarGrafico(data){
    
    var dataInicial = $(".dataInicial").val();
    var dataFinal = $(".dataFinal").val();
    var objJSON = JSON.parse(data);
    console.log(objJSON);
    
    var arrayValores = [];
    $.each(objJSON, function(index){
                            arrayValores.push(objJSON[index].valorTotal);
                            console.log(arrayValores);
                        });
    var arrayDatas = [];
    $.each(objJSON, function(index){
                            arrayDatas.push(objJSON[index].dataPedido);
                            console.log(arrayDatas);
                        });
            
            Highcharts.chart('container', {
                                
                chart: {
                    type: 'bar'
                },
                title: {
                    text: 'Somatória das Vendas entre ' + dataInicial + ' e ' + dataFinal
                },
                xAxis: {
                    categories: arrayDatas
                },
                yAxis: {
                    title: {
                        text: 'Valor Total (R$)'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series: [{
                        name: 'Soma Total',
                        data: arrayValores
                    }]
            });
} 
        
        
$('#dataInicial').mask('00/00/0000');
$('#dataFinal').mask('00/00/0000');







