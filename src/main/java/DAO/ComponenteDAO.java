package DAO;

import Modelo.Componente;
import Util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ComponenteDAO {

    public void cadastrarComponente(Componente componente) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.persist(componente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível cadastrar um componente "+ ex);
        }
    }

    public List<Componente> consultarComponentes() {

        //Instância do EntityManager para fazer as transações do banco
        EntityManager manager = new JPAUtil().getEntityManager();
        //Inicializar o ArrayList do tipo Componente
        List<Componente> listaDeComponentes = new ArrayList<>();
        try {
            TypedQuery<Componente> query = manager.createQuery("select comp from Componente comp", Componente.class);
            listaDeComponentes = query.getResultList();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível listar os componentes: " + ex);
        } finally {
            manager.close();
        }

        return listaDeComponentes;
    }

    //Método para deleter um Componente e suas dependências (TipoComponente)
    public void deletarComponente(Componente componente) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            componente = manager.find(Componente.class, componente.getId());
            manager.remove(componente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível deletar um Componente: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public void alterarComponente(Componente componente) {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
        try {
            manager.merge(componente);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível atualizar o Componente: " + ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    public Componente consultarPorIdComponente(Componente componente) {
        EntityManager manager = new JPAUtil().getEntityManager();
        try {
            componente = manager.find(Componente.class, componente.getId());
        } catch (Exception ex) {
            ex.getMessage();
            System.out.println("Não foi possível localizar o Componente com o  ID " + componente.getId() + " : " + ex);
        } finally {
            manager.close();
        }
        return componente;
    }
    
    public void atualizarEstoque(Componente comp){
        
        EntityManager manager = new JPAUtil().getEntityManager();
        
        try{
            Query query = manager.createQuery("UPDATE Componente comp SET comp.quantidade = comp.quantidade-1 WHERE comp.id=:pId"); 
            query.setParameter("pId",comp.getId());
            manager.getTransaction().begin();        
            //Executa o update no banco 
            query.executeUpdate();       
            //Comita a update
            manager.getTransaction().commit();
        } catch(Exception ex){
            ex.getMessage();
            System.out.println("Não foi possível atualizar estoque: "+ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }
     
    public void retornaEstoque(Componente comp){
        
        EntityManager manager = new JPAUtil().getEntityManager();
        System.out.println(comp.getModelo());
        try{
            Query query = manager.createQuery("UPDATE Componente comp SET comp.quantidade = comp.quantidade+1 WHERE comp.id=:pId");
            query.setParameter("pId",comp.getId());
            manager.getTransaction().begin();
            query.executeUpdate();
            manager.getTransaction().commit();          
        }catch(Exception ex){
            ex.getMessage();
            System.out.println("Não foi possível retornar o componente ao estoque: "+ex);
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }
}
