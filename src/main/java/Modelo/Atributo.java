/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author leo_l
 */

@Entity
public class Atributo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 255, insertable = true, updatable = true)
    private String valor;
    
    //Um Atributo só está associado á um tipoAtributo
    @ManyToOne()
    private TipoAtributo tipoAtributo;
    
    //Um atributo pode estar associado á um componente
    @ManyToOne(cascade = CascadeType.ALL)
    private Componente componentes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(TipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public Componente getComponentes() {
        return componentes;
    }

    public void setComponentes(Componente componentes) {
        this.componentes = componentes;
    } 
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.valor);
        hash = 41 * hash + Objects.hashCode(this.tipoAtributo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atributo other = (Atributo) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Atributo{" + "id=" + id + ", valor=" + valor + ", tipoAtributo=" + tipoAtributo + '}';
    }
    
    
    public static class ExclusaoTipoAtributoDoAtributo implements ExclusionStrategy{

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Atributo") &&
                    fa.getName().equals("tipoAtributo");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }
        
    }
    
    public static class ExclusaoComponenteDoAtributo implements ExclusionStrategy{

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Atributo") &&
                    fa.getName().equals("componentes");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }
        
    }
    
}
