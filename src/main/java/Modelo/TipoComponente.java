/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author leo_l
 */

@Entity
public class TipoComponente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 255, insertable = true, updatable = true, nullable = false)
    private String nomeComponente;
    
    //Um tipoComponente está associado á vários componentes
    @OneToMany(mappedBy = "tipoComponente", fetch = FetchType.EAGER)
    private List<Componente> componentes;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<TipoAtributo> tipoAtributos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeComponente() {
        return nomeComponente;
    }

    public void setNomeComponente(String nomeComponente) {
        this.nomeComponente = nomeComponente;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public List<TipoAtributo> getTipoAtributos() {
        return tipoAtributos;
    }

    public void setTipoAtributos(List<TipoAtributo> tipoAtributos) {
        this.tipoAtributos = tipoAtributos;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
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
        final TipoComponente other = (TipoComponente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomeComponente, other.nomeComponente)) {
            return false;
        }
        return true;
    } 
    
    public static class ExclusaoComponenteDoTipoComponente implements ExclusionStrategy{

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.TipoComponente") 
                    && fa.getName().equals("componentes");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }
        
    }
    
    public static class ExclusaoAtributosDoTipoComponente implements ExclusionStrategy{

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.TipoComponente") 
                    && fa.getName().equals("tipoAtributos");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }
        
    }
    
}
