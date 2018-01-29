/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Enum.StatusPedido;
import Modelo.Pedido;
import Util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author leo_l
 */
public class RelatorioDAO {

    public List<Pedido> consultarRelatorioVendas(String dataInicial, String dataFinal) {
        EntityManager manager = new JPAUtil().getEntityManager();
        List<Pedido> listaRelatorio = new ArrayList<>();
        StatusPedido status = StatusPedido.PAGO;
        try {
            manager.getTransaction().begin();
            TypedQuery<Pedido> query = manager.createQuery("SELECT NEW Pedido(SUM(ped.valorTotal),ped.dataPedido) FROM Pedido ped WHERE ped.dataPedido BETWEEN "
                    + ":dtInicial AND :dtFinal AND ped.statusPedido =:pStatus GROUP BY ped.dataPedido ORDER BY ped.dataPedido", Pedido.class);
            query.setParameter("dtInicial", dataInicial);
            query.setParameter("dtFinal", dataFinal);
            query.setParameter("pStatus",status);
            return query.getResultList();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível realizar a consulta " + ex);
        } finally {
            manager.close();
        }
        return null;
    }

}
