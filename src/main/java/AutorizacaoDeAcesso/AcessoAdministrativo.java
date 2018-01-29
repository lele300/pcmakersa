/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutorizacaoDeAcesso;

import Enum.TipoAdm;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leonardo
 */

@WebFilter(servletNames = {"ControleAtributo"}, urlPatterns = {"/cadastrarAcessoUsuario","/alterarAcessoUsuario", "/cadastrarTipoComponente", "/cadastrarComponente", "/consultarComponente", "/deletarComponente", "/alterarComponente", "/consultarPorIdTipoComponente", "/iniciarCadastroComponente","/cadastrarAtributo","/consultarAtributo","/deletarAtributo","/alterarAtributo","/consultarPorIdTipoAtributo","/alterarStatusPedido","/excluirPedidoGeral","/gerarRelatorioSomaDeVendas","/consultarPedidoAdm"})
public class AcessoAdministrativo implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
             
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpSession sessaoUsuario = ((HttpServletRequest)request).getSession();
        Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
        
        if(usuario != null && usuario.getTipoAdm().equals(TipoAdm.ADMINISTRADOR)){
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendRedirect("acessoNegado.jsp");
        }
        
    }

    @Override
    public void destroy() {
        
    }
    
    
    
}

