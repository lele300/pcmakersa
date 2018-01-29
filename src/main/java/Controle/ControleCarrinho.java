/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.ComponenteDAO;
import Modelo.Atributo;
import Modelo.Carrinho;
import Modelo.Componente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leo_l
 */
@WebServlet(name = "ControleCarrinho", urlPatterns = {"/inserirComponenteNoCarrinho", "/removerComponenteDoCarrinho", "/somarWattsComponentes"})
public class ControleCarrinho extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURI();

        if (url.equals(req.getContextPath() + "/inserirComponenteNoCarrinho")) {
            try {
                inserirComponenteNoCarrinho(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleCarrinho.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (url.equals(req.getContextPath() + "/removerComponenteDoCarrinho")) {
            try {
                removerComponenteDoCarrinho(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleCarrinho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void inserirComponenteNoCarrinho(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        int id = Integer.parseInt(req.getParameter("idComponente"));
        Componente componente = new Componente();
        componente.setId(id);

        ComponenteDAO daoComponente = new ComponenteDAO();
        componente = daoComponente.consultarPorIdComponente(componente);

        Carrinho carrinho = (Carrinho) req.getSession(true).getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new Carrinho();
            req.getSession().setAttribute("carrinho", carrinho);
        }

        carrinho.adicionarNoCarrinho(componente);
        Componente fonteEscolhida = componente.verificarFonteEscolhida(carrinho.getComponentes());
        if (fonteEscolhida != null) {
            double potencia = 0;
            double somaWatts = 0;
            if ("Fonte".equals(fonteEscolhida.getTipoComponente().getNomeComponente())) {
                for (Atributo at : fonteEscolhida.getAtributos()) {
                    if ("Potencia".equals(at.getTipoAtributo().getNomeAtributo())) {
                        potencia = Double.parseDouble(at.getValor());
                    }
                }
            } else {
                somaWatts = carrinho.retornaSomaWatts(carrinho.getComponentes());
            }

            somaWatts = carrinho.retornaSomaWatts(carrinho.getComponentes());

            if (potencia < somaWatts && potencia != 0) {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println("Fonte não suportada, por favor seleciona outra !!!");
            }
        }
    }

    private void removerComponenteDoCarrinho(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        try {
            int id = Integer.parseInt(req.getParameter("idComponente"));
            Componente removerComponente = new Componente();
            removerComponente.setId(id);

            ComponenteDAO daoComponente = new ComponenteDAO();
            removerComponente = daoComponente.consultarPorIdComponente(removerComponente);

            Carrinho carrinho = (Carrinho) req.getSession().getAttribute("carrinho");
            carrinho.removerDoCarrinho(removerComponente);

        } catch (NumberFormatException ex) {
            ex.getMessage();
            System.out.println("Não foi possível retirar o componente do carrinho " + ex);
        }
    }
}
