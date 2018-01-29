/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;


import DAO.RelatorioDAO;
import Modelo.Pedido;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
@WebServlet(name = "ControleRelatorio", urlPatterns = {"/gerarRelatorioSomaDeVendas"})
public class ControleRelatorio extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.equals(req.getContextPath() + "/gerarRelatorioSomaDeVendas")) {
            try {
                gerarRelatorioSomaDeVendas(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            } catch (ParseException ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void gerarRelatorioSomaDeVendas(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {
        
        String dataInicial = req.getParameter("dtInicial");
        String dataFinal = req.getParameter("dtFinal");
        List<Pedido> listaRelatorio = new ArrayList<>();
        
        RelatorioDAO daoPedido = new RelatorioDAO();
        listaRelatorio = daoPedido.consultarRelatorioVendas(dataInicial, dataFinal);
        
        Gson gson = new Gson();
        
        String lista = gson.toJson(listaRelatorio);
        resp.getWriter().println(lista);
        System.out.println(lista);
    }

}
