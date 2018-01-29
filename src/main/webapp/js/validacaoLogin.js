/* global Materialize */

var mensagem = $(".msg-erro").text();

Materialize.toast(mensagem, 6000);

console.log(mensagem);