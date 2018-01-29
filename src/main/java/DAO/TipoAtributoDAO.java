/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.TipoAtributo;
import Util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author leo_l
 */
public class TipoAtributoDAO {

    public void cadastrarTipoAtributo(TipoAtributo tipoAtributo) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.persist(tipoAtributo);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível criar o TipoAtributo: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public List<TipoAtributo> consultarTipoAtributos() {

        EntityManager manager = new JPAUtil().getEntityManager();
        List<TipoAtributo> listaTipoAtributos = new ArrayList<>();

        try {
            TypedQuery<TipoAtributo> query = manager.createQuery("select tp from TipoAtributo tp", TipoAtributo.class);
            listaTipoAtributos = query.getResultList();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível recuperar os TiposAtributos");
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
        return listaTipoAtributos;
    }

    public void deletarTipoAtributo(TipoAtributo tipoAtributo) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            tipoAtributo = manager.find(TipoAtributo.class, tipoAtributo.getId());
            manager.remove(tipoAtributo);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível localizar Tipo Atributo " + tipoAtributo.getId() + " : " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public void alterarTipoAtributo(TipoAtributo tipoAtributo) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.merge(tipoAtributo);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível alterar um Tipo Atributo : " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public TipoAtributo consultarPorIdTipoAtributo(TipoAtributo tipoAtributo) {

        EntityManager manager = new JPAUtil().getEntityManager();

        //Retorna um objeto TipoAtributo pela Primary Key (PK)
        tipoAtributo = manager.find(TipoAtributo.class, tipoAtributo.getId());
        return tipoAtributo;

    }

}
