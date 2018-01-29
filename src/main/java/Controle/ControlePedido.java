/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import DAO.PedidoDAO;
import DAO.ComponenteDAO;
import Enum.StatusPedido;
import Modelo.Carrinho;
import Modelo.Componente;
import Modelo.Pedido;
import Modelo.Usuario;
import Util.NotificacaoEmail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 *
 * @author leo_l
 */
@WebServlet(name = "ControlePedido", urlPatterns = {"/cadastrarPedido", "/consultarPedidoPorUsuario", "/cancelarPedidoUsuario", "/consultarPedidoAdm", "/alterarStatusPedido", "/cancelarPedidoGeral", "/consultarPedidoAJAX"})
public class ControlePedido extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.equals(req.getContextPath() + "/cancelarPedidoUsuario")) {
            try {
                cancelarPedidoUsuario(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            }
        } else if (uri.equals(req.getContextPath() + "/cadastrarPedido")) {
            try {
                cadastrarPedido(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            } catch (ParseException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/consultarPedidoPorUsuario")) {
            try {
                consultarPedidoPorUsuario(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            }
        } else if (uri.equals(req.getContextPath() + "/consultarPedidoAdm")) {
            try {
                consultarPedidoAdm(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            } catch (ParseException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/alterarStatusPedido")) {
            try {
                alterarStatusPedido(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            }
        } else if (uri.equals(req.getContextPath() + "/cancelarPedidoGeral")) {
            try {
                cancelarPedidoGeral(req, resp);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
                req.getRequestDispatcher("erro.jsp").forward(req, resp);
            } catch (ParseException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals(req.getContextPath() + "/consultarPedidoAJAX")) {
            try {
                consultarPedidoAJAX(req, resp);
            } catch (ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(ControlePedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //Responsável pelo cadastro de Pedido no banco de dados.
    public void cadastrarPedido(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {

        List<Componente> componentesCarrinho = new ArrayList<>();
        Carrinho carrinho = null;
        Usuario usuarioSessao = new Usuario();
        carrinho = (Carrinho) req.getSession().getAttribute("carrinho");
        usuarioSessao = (Usuario) req.getSession().getAttribute("usuarioAutenticado");
        if (carrinho != null) {

            Date dataPedidoAtual = new Date();
            String dataPedido = new SimpleDateFormat("dd/MM/yyyy").format(dataPedidoAtual);
            Pedido pedido = new Pedido();
            double valorTotal = 0;

            pedido.setDataPedido(dataPedido);
            pedido.setUsuarioDoPedido(usuarioSessao);
            pedido.setStatusPedido(StatusPedido.PENDENTE);

            for (Componente comp : carrinho.getComponentes()) {

                //Adicionando o componente da sessão no arrayList
                componentesCarrinho.add(comp);
                ComponenteDAO daoComponente = new ComponenteDAO();

                //Retira o componente do estoque
                daoComponente.atualizarEstoque(comp);
            }

            //Adicionando os componentes no pedido
            pedido.setItensComponente(componentesCarrinho);
            List<Componente> listaComp = pedido.getItensComponente();

            //Retornando a soma do Preço da lista de Componentes.
            valorTotal = pedido.calcularPreco(listaComp);
            //Adicionando o valor total
            pedido.setValorTotal(valorTotal);

            PedidoDAO daoPedido = new PedidoDAO();
            //Cadastrar o Pedido no banco de dados
            daoPedido.cadastrarPedido(pedido);

            req.getSession().removeAttribute("carrinho");
            NotificacaoEmail email = new NotificacaoEmail();
            email.enviaEmailFinalizarPedido(usuarioSessao, pedido);

            this.gerarBoleto(req, resp, pedido);
        } else {
            System.err.println("Não existe nenhum componente no carrinho");
        }

    }

    //Responsável pela consulta de pedidos no banco de dados.
    public void consultarPedidos(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {

        List<Pedido> listaPedidos = new ArrayList<>();
        PedidoDAO daoPedido = new PedidoDAO();
        listaPedidos = daoPedido.consultarTodosPedidos();
        req.setAttribute("listaGeralPedidos", listaPedidos);
        req.getRequestDispatcher("consultaPedidos.jsp").forward(req, resp);
    }

    //Responsável pela consulta de todos os pedidos solicitados pelo Usuário. 
    public void consultarPedidoPorUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        Usuario usuario = new Usuario();
        usuario = (Usuario) req.getSession().getAttribute("usuarioAutenticado");
        List<Pedido> listaDePedidos = new ArrayList<>();
        PedidoDAO daoPedido = new PedidoDAO();

        listaDePedidos = daoPedido.consultarTodosPedidoPorUsuario(usuario);
        req.setAttribute("listaDePedido", listaDePedidos);
        req.getRequestDispatcher("historicoDePedidos.jsp").forward(req, resp);

    }

    //Responsável pelo cancelamento do pedido (ALTERAÇÃO DE STATUS PARA CANCELADO) e retorno dos componentes no estoque.
    public void cancelarPedidoUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        Pedido pedido = new Pedido();
        PedidoDAO daoPedido = new PedidoDAO();

        pedido.setIdPedido(Integer.parseInt(req.getParameter("id")));
        pedido = daoPedido.consultarPedidoPorId(pedido);

        pedido.setStatusPedido(StatusPedido.CANCELADO);

        for (Componente comp : pedido.getItensComponente()) {
            ComponenteDAO daoComponente = new ComponenteDAO();
            daoComponente.retornaEstoque(comp);
        }
        daoPedido.alterarStatusPedido(pedido);
        this.consultarPedidoPorUsuario(req, resp);
    }

    //Responsável pela criação do boleto do pedido.
    public void gerarBoleto(HttpServletRequest req, HttpServletResponse resp, Pedido pedido) throws IOException, ClassNotFoundException, SQLException, ServletException {

        Usuario usuarioSessao = new Usuario();
        usuarioSessao = (Usuario) req.getSession().getAttribute("usuarioAutenticado");

        // Informando Dados do CEDENTE
        Cedente cedente = new Cendente("PC MAKER", "95.543.063/0001-09");

        //Informando Dados do SACADO
        Sacado sacado = new Sacado(usuarioSessao.getNomeCompleto(), usuarioSessao.getCpf());

        //Informando o endereço do SACADO
        for (Modelo.Endereco ende : usuarioSessao.getEnderecos()) {
            //Informando o endereço do sacado.
            Endereco enderecoSac = new Endereco();
            enderecoSac.setUF(UnidadeFederativa.SP);
            enderecoSac.setLocalidade(ende.getCidade());
            enderecoSac.setCep(new CEP(ende.getCep()));
            enderecoSac.setBairro(ende.getBairro());
            enderecoSac.setLogradouro(ende.getRua() + " " + ende.getComplemento());
            enderecoSac.setNumero(Integer.toString(ende.getNumero()));
            sacado.addEndereco(enderecoSac);
        }

        //Informando nome fantasia e CNPJ da empresa
        SacadorAvalista sacadorAvalista = new SacadorAvalista("PC MAKER", "95.543.063/0001-09");

        //Informando o endereço do CNPJ
        Endereco enderecoSacAval = new Endereco();
        enderecoSacAval.setUF(UnidadeFederativa.SP);
        enderecoSacAval.setLocalidade("Mogi das Cruzes");
        enderecoSacAval.setCep(new CEP("08710640"));
        enderecoSacAval.setBairro("Centro");
        enderecoSacAval.setLogradouro("Rua Manoel Pimenta de Abreu");
        enderecoSacAval.setNumero("116");
        sacadorAvalista.addEndereco(enderecoSacAval);

        //Informações bancárias do CNPJ
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(2478, "3"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(148, "3"));

        //Informando os dados de pagamento do boleto
        Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
        titulo.setNumeroDoDocumento("834710");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(pedido.getValorTotal()));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.NF_NOTA_FISCAL);
        titulo.setDesconto(new BigDecimal(0.15));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ONE);

        Boleto boleto = new Boleto(titulo);

        boleto.setLocalPagamento("Pagável preferencialmente em agências BRADESCO ou em "
                + "qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Pague em dia. Evite juros!!!");
        boleto.setInstrucao1("Não receber após o vencimento do boleto!");
        boleto.setInstrucao2("O não pagamento não implicará em cobranças!");

        OutputStream outPut = resp.getOutputStream();

        BoletoViewer viewer = new BoletoViewer(boleto);
        byte[] pdfAsByte = viewer.getPdfAsByteArray();
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=boletoPCMAKER.pdf");
        outPut.write(pdfAsByte);
        resp.flushBuffer();

    }

    //Responsável pela consulta de todos os pedidos na sessão administrativa.
    public void consultarPedidoAdm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {

        List<Pedido> listaGeralPedidos = new ArrayList<>();
        PedidoDAO daoPedido = new PedidoDAO();
        listaGeralPedidos = daoPedido.consultarTodosPedidos();
        this.consultarPedidos(req, resp);

    }

    //Responsável pela alteração do status do Pedido.
    public void alterarStatusPedido(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException {

        int id = Integer.parseInt(req.getParameter("id"));
        String statusPedido = req.getParameter("statusPedido");

        Pedido pedido = new Pedido();
        pedido.setIdPedido(id);

        Usuario usuario = new Usuario();
        PedidoDAO daoPedido = new PedidoDAO();
        pedido = daoPedido.consultarPedidoPorId(pedido);
        usuario = pedido.getUsuarioDoPedido();
        int quantidade = pedido.getItensComponente().size();
        System.out.println("quantidade "+quantidade);

        
        //Retorna o status do Pedido.
        pedido = pedido.alterarStatusPedido(pedido, statusPedido);
        daoPedido.alterarStatusPedido(pedido);

        if ("CANCELADO".equals(statusPedido)) {
            for(Componente comp : pedido.getItensComponente()){
                ComponenteDAO daoComponente = new ComponenteDAO();
                daoComponente.retornaEstoque(comp);
            }
        }

        NotificacaoEmail email = new NotificacaoEmail();
        email.enviarEmailAlterarStatusPedido(usuario, pedido, statusPedido);
    }

    //Método responsável pelo cancelamento do pedido e retorno dos componentes no estoque caso o status seja "CANCELADO"
    public void cancelarPedidoGeral(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {

        Pedido pedido = new Pedido();
        PedidoDAO daoPedido = new PedidoDAO();

        pedido.setIdPedido(Integer.parseInt(req.getParameter("id")));
        pedido = daoPedido.consultarPedidoPorId(pedido);

        if (pedido.getStatusPedido().equals(StatusPedido.CANCELADO)) {

            for (Componente comp : pedido.getItensComponente()) {
                ComponenteDAO daoComponente = new ComponenteDAO();
                daoComponente.retornaEstoque(comp);
            }
        }
        this.consultarPedidos(req, resp);
    }

    //Responsável pela consulta de pedidos por AJAX.
    public void consultarPedidoAJAX(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, SQLException, ServletException, ParseException {

        List<Pedido> listaPedidos = new ArrayList<>();
        PedidoDAO daoPedido = new PedidoDAO();
        listaPedidos = daoPedido.consultarTodosPedidos();

        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new Pedido.ExclusaoItensComponenteDoPedido())
                .addSerializationExclusionStrategy(new Usuario.ExclusaoUsuarioDoPedido())
                .addSerializationExclusionStrategy(new Modelo.Endereco.ExclusaoUsuarioDoEndereco()).create();

        String listaPedidosJSON = gson.toJson(listaPedidos);
        System.out.println(listaPedidosJSON);
        resp.getWriter().println(listaPedidosJSON);

    }

}
