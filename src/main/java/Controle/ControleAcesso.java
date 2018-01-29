/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.UsuarioDAO;
import Enum.TipoAdm;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leonardo
 */
@WebServlet(displayName = "ControleAcesso", urlPatterns = "/ControleAcesso")
public class ControleAcesso extends HttpServlet {
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        
        try {
            String acao = req.getParameter("acao");
            if (acao.equals("Entrar")) {
                Usuario usuario = new Usuario();
                usuario.setLogin(req.getParameter("login"));
                usuario.setSenha(usuario.encryptBlowfish(req.getParameter("senha"),req.getParameter("senha")));
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuarioAutenticado = usuarioDAO.autenticaUsuarioComum(usuario);
                
                if (usuarioAutenticado != null) {
                    HttpSession sessaoUsuario = req.getSession();
                    sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);
                    if (usuarioAutenticado.getTipoAdm() == TipoAdm.ADMINISTRADOR) {
                        resp.sendRedirect("indexAdm.jsp");
                    } else {
                        resp.sendRedirect("principal.jsp");
                    }
                    
                } else {
                    req.setAttribute("msg", "Login ou senha incorretos");
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                }
                
            } else if (acao.equals("Sair")) {
                HttpSession sessaoUsuario = req.getSession();
                sessaoUsuario.removeAttribute("usuarioAutenticado");
                HttpSession sessaoCarrinho = req.getSession();
                sessaoCarrinho.removeAttribute("carrinho");
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
        } catch (IOException | ServletException ex) {
            RequestDispatcher rd = req.getRequestDispatcher("/erro.jsp");
            req.setAttribute("erro", "Login ou senha incorretos !");
            rd.forward(req, resp);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
