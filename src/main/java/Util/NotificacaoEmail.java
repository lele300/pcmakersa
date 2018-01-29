/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Modelo.Componente;
import Modelo.Pedido;
import Modelo.Usuario;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.TransportStrategy;

/**
 *
 * @author leo_l
 */
public class NotificacaoEmail {
    
    public void enviaEmailUsuarioCadastrado(Usuario usuario) {
        Email email = new Email();

        email.setFromAddress("PC MAKER", "pfcmaker@gmail.com");
        email.addToRecipients(usuario.getEmail());
        email.setSubject("Bem-vindo ao PC MAKER");
        email.setTextHTML("<b> Olá "+usuario.getNomeCompleto()+ "</b>, "
                + "<br> agora que você possui o cadastro PC MAKER, poderá montar seu PC, gerenciar seus pedidos, entre outras funcionalidades."
                + "<br> Email cadastrado: " + usuario.getEmail()+""
                + "<br> Usuário: "+ usuario.getLogin());
              
        email.addBccRecipients("leo_lopes09@hotmail.com");

        new Mailer("smtp.gmail.com", 587, "pfcmaker@gmail.com", "c5148c1f", TransportStrategy.SMTP_TLS).sendMail(email, true);
    }
    
        public void enviaEmailFinalizarPedido(Usuario usuario,Pedido pedido) {
        Email email = new Email();

        email.setFromAddress("PC MAKER", "pfcmaker@gmail.com");
        email.addToRecipients(usuario.getEmail());
        email.setSubject("Finalização do Pedido");
        email.setTextHTML("<b> Olá "+usuario.getNomeCompleto()+ "</b>, "
                + "<br> Seu pedido foi finalizado com sucesso. Na seção 'Meus Pedido', você poderá visualizar as informações referente a compra."
                + "<br> Nº do pedido: "+pedido.getIdPedido()
                + "<br> CPF Utilizado na compra: "+usuario.getCpf()
                + "<br> Valor Total (R$): "+pedido.getValorTotal()
                + "<br> Para mais informações, verifique a seção 'Meus Pedidos'."
        );
                             
        new Mailer("smtp.gmail.com", 587, "pfcmaker@gmail.com", "c5148c1f", TransportStrategy.SMTP_TLS).sendMail(email, true);
    }
        
    public void enviarEmailAlterarStatusPedido(Usuario usuario,Pedido pedido, String statusPedido) {
        Email email = new Email();

        email.setFromAddress("PC MAKER", "pfcmaker@gmail.com");
        email.addToRecipients(usuario.getEmail());
        email.setSubject("Status da Compra");
        email.setTextHTML("<b> Olá "+usuario.getNomeCompleto()+ "</b>, "
                + "<br> O status da sua compra foi alterado de PENDENTE para "+pedido.getStatusPedido()+ "."
                + "<br> Caso o status do seu pedido tenha sido alterado para RECUSADO, o boleto não foi pago até a data de vencimento."
                + "<br> Caso deseje mais informações, por favor entre em contato no email pfcmaker@gmail.com"
        );                            
        new Mailer("smtp.gmail.com", 587, "pfcmaker@gmail.com", "c5148c1f", TransportStrategy.SMTP_TLS).sendMail(email, true);
    }
    
}
