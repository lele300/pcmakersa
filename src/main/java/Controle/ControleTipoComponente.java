/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.ComponenteDAO;
import DAO.TipoAtributoDAO;
import DAO.TipoComponenteDAO;
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

/**
 *
 * @author leo_l
 */
@WebServlet(name = "ControleTipoComponente", urlPatterns = {"/cadastrarTipoComponente", "/iniciarCadastroTipoComponente", "/consultarTipoComponente", "/deletarTipoComponente","/alterarTipoComponente","/consultarTipoComponenteAJAX", "/consultarTipoComponentePorId"})
public class ControleTipoComponente extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.equals(req.getContextPath() + "/cadastrarTipoComponente")) {
            try {
                cadastrarTipoComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleTipoComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/alterarTipoComponente")) {
            try {
                alterarTipoComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleTipoComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.equals(req.getContextPath() + "/iniciarCadastroTipoComponente")) {
            try {
                iniciarCadastroTipoComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleTipoComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/consultarTipoComponente")) {
            try {
                consultarTodosTiposComponentes(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleTipoComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/deletarTipoComponente")) {
            try {
                deletarTipoComponente(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleTipoComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/consultarTipoComponenteAJAX")) {
            try {
                consultarTipoComponenteAJAX(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleTipoComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/consultarTipoComponentePorId")) {
            try {
                consultarTipoComponentePorId(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleTipoComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cadastrarTipoComponente(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        //Recuperando o valor do nomeComponente
        String nomeComponente = req.getParameter("nomeComponente");

        //Criando objeto tipoComponente para setar o nome 
        TipoComponente tipoComponente = new TipoComponente();
        tipoComponente.setNomeComponente(nomeComponente);

        //Recuperando os valores do checkbox 
        String[] opcoesSelecionadas = req.getParameterValues("opcaoAtributo");
        ArrayList<TipoAtributo> listaTipoAtributo = new ArrayList<>();

        //Para cada opção selecionada no checkbox, gravar o id do TipoAtributo e setar em listaTipoAtributo
        for (String opcaoSelecionada : opcoesSelecionadas) {
            int idTipoAtributo = Integer.parseInt(opcaoSelecionada);
            TipoAtributo atributos = new TipoAtributo();
            atributos.setId(idTipoAtributo);
            listaTipoAtributo.add(atributos);
        }

        //Setando a lista de Atributos dentro de tipoComponente
        tipoComponente.setTipoAtributos(listaTipoAtributo);

        TipoComponenteDAO daoTipoComponente = new TipoComponenteDAO();
        daoTipoComponente.cadastrarTipoComponente(tipoComponente);
        req.getRequestDispatcher("cadastroTipoComponenteOK.jsp").forward(req, resp);

    }

    public void iniciarCadastroTipoComponente(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {
        //Instância da AtributoDAO
        TipoAtributoDAO daoTipoAtributo = new TipoAtributoDAO();

        // Setando o resultado da consulta na listaAtributos
        List<TipoAtributo> listaTipoAtributos = daoTipoAtributo.consultarTipoAtributos();

        //Atribuindo uma String para enviar á JSP consultaAtributos.jsp o objeto listaAtributos
        req.setAttribute("listaTipoAtributos", listaTipoAtributos);
        req.getRequestDispatcher("cadastroTipoComponente.jsp").forward(req, resp);
    }

    public void consultarTodosTiposComponentes(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        //Instância de TipoComponenteDAO para recuperar a lista de TiposComponente
        //Com seus tiposAtributos definidos
        TipoComponenteDAO daoTipoComponente = new TipoComponenteDAO();

        //Lista para armazenar os tiposComponentes do banco de dados
        List<TipoComponente> listaTipoComponente = daoTipoComponente.consultarTipoComponentes();

        // Atribuir a lista em um objeto para recuperar na JSP listaCadastroTipoComponente.jsp
        req.setAttribute("listaTipoComponente", listaTipoComponente);
        req.getRequestDispatcher("listaCadastroTipoComponente.jsp").forward(req, resp);
        System.out.println(listaTipoComponente);

    }

    public void deletarTipoComponente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {

        //Recuperando o ID do TipoComponente da JSP
        int id = Integer.parseInt(req.getParameter("id"));

        //Instância de TipoComponente
        TipoComponente tipoComponente = new TipoComponente();
        Componente componente = new Componente();

        tipoComponente.setId(id);
        componente.setId(id);

        //Instância da classe de persistência
        TipoComponenteDAO tipoComponenteDAO = new TipoComponenteDAO();
        ComponenteDAO daoComponente = new ComponenteDAO();

        //Deletando o objeto TipoComponente do BD
        daoComponente.deletarComponente(componente);
        tipoComponenteDAO.deletarTipoComponente(tipoComponente);

        this.consultarTodosTiposComponentes(req, resp);

    }
    
    public void alterarTipoComponente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {

        int idTipoComponente = Integer.parseInt(req.getParameter("idTipoComponente"));
        String nomeTipoComponente = req.getParameter("nomeTipoComponente");
        
        TipoComponente tipoComponente = new TipoComponente();
        tipoComponente.setId(idTipoComponente);
        tipoComponente.setNomeComponente(nomeTipoComponente);
        
        TipoComponenteDAO daoTipoComponente = new TipoComponenteDAO();
        daoTipoComponente.alterarTipoComponente(tipoComponente);
        this.consultarTodosTiposComponentes(req, resp);
    }
    
    public void consultarTipoComponentePorId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        int id = Integer.parseInt(req.getParameter("id"));
        TipoComponente tipoComponente = new TipoComponente();
        tipoComponente.setId(id);
        
        TipoComponenteDAO daoTipoComponente = new TipoComponenteDAO();
        tipoComponente = daoTipoComponente.consultarTipoComponentePorId(tipoComponente);
        req.setAttribute("tipoComponente", tipoComponente);
        req.getRequestDispatcher("alterarTipoComponente.jsp").forward(req, resp);
        
    }
    public void consultarTipoComponenteAJAX(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {

        TipoComponenteDAO daoTipoComponente = new TipoComponenteDAO();

        List<TipoComponente> listaTipoComponentes = daoTipoComponente.consultarTipoComponentes();

        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new TipoComponente.ExclusaoAtributosDoTipoComponente())
                .addSerializationExclusionStrategy(new TipoComponente.ExclusaoComponenteDoTipoComponente()).create();

        String listaTipoComponenteJSON = gson.toJson(listaTipoComponentes);
        resp.getWriter().println(listaTipoComponenteJSON);
        System.out.println(listaTipoComponenteJSON);

    }

}
