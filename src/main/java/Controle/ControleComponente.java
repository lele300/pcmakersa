/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.ComponenteDAO;
import DAO.TipoComponenteDAO;
import Modelo.Atributo;
import Modelo.Componente;
import Modelo.TipoAtributo;
import Modelo.TipoComponente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControleComponente", urlPatterns = {"/cadastrarComponente","/itensCarrinhoComponente", "/consultarComponenteAJAX" ,"/consultarComponentes", "/alterarQuantidadeComponente"})

public class ControleComponente extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.equals(req.getContextPath() + "/cadastrarComponente")) {
            try {
                cadastrarComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/alterarQuantidadeComponente")) {
            try {
                alterarQuantidadeComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        if ((uri.equals(req.getContextPath() + "/itensCarrinhoComponente"))) {
            try {
                itensCarrinhoComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ((uri.equals(req.getContextPath() + "/consultarComponenteAJAX"))) {
            try {
                consultarComponenteAJAX(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ((uri.equals(req.getContextPath() + "/consultarComponentes"))) {
            try {
                consultarComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cadastrarComponente(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));
        String descricao = req.getParameter("descricao");
        double preco = Double.parseDouble(req.getParameter("preco"));
        int idTipoComponente = Integer.parseInt(req.getParameter("tipoComponente"));

        //Instância do objeto Componente
        Componente componente = new Componente();

        //Setando os valores para a tabela Componente
        componente.setMarca(marca);
        componente.setModelo(modelo);
        componente.setQuantidade(quantidade);
        componente.setDescricao(descricao);
        componente.setPreco(preco);

        TipoComponente tipoComponente = new TipoComponente();
        tipoComponente.setId(idTipoComponente);

        TipoComponenteDAO tipoComponenteDAO = new TipoComponenteDAO();
        tipoComponente = tipoComponenteDAO.consultarTipoComponentePorId(tipoComponente);

        componente.setTipoComponente(new TipoComponente());
        componente.getTipoComponente().setId(idTipoComponente);

        List<Atributo> listaAtributo = new ArrayList<>();
        for (TipoAtributo at : tipoComponente.getTipoAtributos()) {

            String valorAtributo = req.getParameter("atributo" + at.getId());
            Atributo atributo = new Atributo();
            atributo.setValor(valorAtributo);
            TipoAtributo tp = new TipoAtributo();
            tp.setId(at.getId());
            atributo.setTipoAtributo(tp);
            listaAtributo.add(atributo);
            at.setAtributos(listaAtributo);
            atributo.setComponentes(componente);
        }

        componente.setAtributos(listaAtributo);

        ComponenteDAO daoComponente = new ComponenteDAO();
        daoComponente.cadastrarComponente(componente);

        req.getRequestDispatcher("cadastroTipoComponenteOK.jsp").forward(req, resp);

    }
    
    public void consultarComponente(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException{
        
        req.getRequestDispatcher("consultarInfoComponente.jsp").forward(req, resp);
    }

    public void itensCarrinhoComponente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
        req.getRequestDispatcher("carrinho.jsp").forward(req, resp);
    }

    public void consultarComponenteAJAX(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {

        TipoComponenteDAO daoTipoComponente = new TipoComponenteDAO();
        List<TipoComponente> listaComponente = daoTipoComponente.consultarTipoComponentes();

        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new TipoComponente.ExclusaoAtributosDoTipoComponente())
                .addSerializationExclusionStrategy(new Componente.ExclusaoTipoComponenteDoComponente())
                .addSerializationExclusionStrategy(new Atributo.ExclusaoComponenteDoAtributo())
                .addSerializationExclusionStrategy(new TipoAtributo.ExclusaoAtributoDoTipoAtributo())
                .addSerializationExclusionStrategy(new TipoAtributo.ExclusaoTipoComponenteDoTipoAtributo()).create();

        String listaComponenteJSON = gson.toJson(listaComponente);

        resp.getWriter().println(listaComponenteJSON);

        System.out.println(listaComponenteJSON);
    }
    
    public void alterarQuantidadeComponente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        int quantidade = Integer.parseInt(req.getParameter("qtdeComponente"));
        int idComponente = Integer.parseInt(req.getParameter("idComp"));
        
        Componente componente = new Componente();
        componente.setId(idComponente);
        ComponenteDAO daoComponente = new ComponenteDAO();
        
        componente = daoComponente.consultarPorIdComponente(componente);
        
        componente.setQuantidade(quantidade);
        
        daoComponente.alterarComponente(componente);
               
    }
}
