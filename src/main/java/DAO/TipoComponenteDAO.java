/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.TipoComponente;
import Util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author leo_l
 */
public class TipoComponenteDAO {

    public void cadastrarTipoComponente(TipoComponente tipoComponente) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.persist(tipoComponente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Erro ao inserir um Tipo Componente: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public List<TipoComponente> consultarTipoComponentes() {

        //Instância do Entity Manager 
        EntityManager manager = new JPAUtil().getEntityManager();
        //Criação de uma lista que possui como retorno os tiposComponentes cadastrados no BD
        List<TipoComponente> listaTipoComponente = new ArrayList<>();
        try {
            //Define o tipo de retorno definido para TipoComponente e criação da consulta.
            TypedQuery<TipoComponente> query = manager.createQuery("select tcomp from TipoComponente tcomp ORDER BY tcomp.id", TipoComponente.class);
            // Inserindo o retorno do banco na lista de TipoComponente
            listaTipoComponente = query.getResultList();

        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível recuperar os dados do TipoComponente: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
        return listaTipoComponente;
    }

    public void deletarTipoComponente(TipoComponente tipoComponente) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            //Retorna o tipoComponente pela PK
            tipoComponente = manager.find(TipoComponente.class, tipoComponente.getId());

            //Removendo o objeto tipoComponente recuperado acima do BD
            manager.remove(tipoComponente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível deletar o TipoComponente: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public TipoComponente consultarTipoComponentePorId(TipoComponente tipoComponente) {

        EntityManager manager = new JPAUtil().getEntityManager();
        tipoComponente = manager.find(TipoComponente.class, tipoComponente.getId());
        return tipoComponente;
    }

    public void alterarTipoComponente(TipoComponente tipoComponente) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.merge(tipoComponente);
            manager.getTransaction().commit();
            System.out.println("Tipo Componente alterado com sucesso!");
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível alterar o Tipo Componente: " + ex);
            manager.getTransaction().rollback();
        }
    }

}
