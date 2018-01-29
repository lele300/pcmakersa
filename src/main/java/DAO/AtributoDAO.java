/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Atributo;
import Util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author leo_l
 */
public class AtributoDAO {

    public void cadastrarAtributo(Atributo atributo) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.persist(atributo);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.err.println("Não foi possível inserir atributo: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    //Método para deleter um Atributo e suas dependências (TipoAtributo)
    public void deletarAtributo(Atributo atributo) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            atributo = manager.find(Atributo.class, atributo.getId());
            manager.remove(atributo);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.err.println("Falha ao deletar atributo: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public List<Atributo> consultarAtributos() {

        EntityManager manager = new JPAUtil().getEntityManager();
        List<Atributo> listaAtributos = new ArrayList<>();
        try{
        TypedQuery<Atributo> query = manager.createQuery("select at from Atributo at", Atributo.class);
        listaAtributos = query.getResultList();
        } catch(Exception ex){
            ex.getMessage();
            System.out.println("Não foi possível recuperar os atributos: "+ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
        return listaAtributos;

    }

    public void alterarAtributo(Atributo atributo) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.merge(atributo);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível atualizar o Atributo: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public Atributo consultarPorIdAtributo(Atributo atributo) {
        EntityManager manager = new JPAUtil().getEntityManager();
        try {
            atributo = manager.find(Atributo.class, atributo.getId());
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível localizar o Atributo de ID " + atributo.getId() + " : " + ex);
        } finally {
            manager.close();
        }
        return atributo;
    }

}
