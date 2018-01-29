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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Componente implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 100, insertable = true, updatable = true, nullable = false)
    private String modelo;

    @Column(length = 100, insertable = true, updatable = true, nullable = false)
    private String marca;

    @Column(length = 3, insertable = true, updatable = true, nullable = false)
    private int quantidade;

    @Column(insertable = true, updatable = true, nullable = false)
    private double preco;

    @Column(length = 255, insertable = true, updatable = true, nullable = false)
    private String descricao;

    //Um componente só pode estar associado á um tipoComponente
    @ManyToOne(cascade = CascadeType.REMOVE)
    private TipoComponente tipoComponente;

    //Um componente pode estar associado á vários atributos
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentes", fetch = FetchType.EAGER)
    private List<Atributo> atributos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoComponente getTipoComponente() {
        return tipoComponente;
    }

    public void setTipoComponente(TipoComponente tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Componente verificarFonteEscolhida(List<Componente> listaCarrinho) {

        Componente fonteEscolhida = null;

        for (Componente comp : listaCarrinho) {
            if ("Fonte".equals(comp.getTipoComponente().getNomeComponente())) {
                fonteEscolhida = comp;
                continue;
            }
        }
        return fonteEscolhida;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.modelo);
        hash = 41 * hash + Objects.hashCode(this.marca);
        hash = 41 * hash + this.quantidade;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.preco) ^ (Double.doubleToLongBits(this.preco) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.descricao);
        hash = 41 * hash + Objects.hashCode(this.tipoComponente);
        hash = 41 * hash + Objects.hashCode(this.atributos);
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
        final Componente other = (Componente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public static class ExclusaoTipoComponenteDoComponente implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Componente")
                    && fa.getName().equals("tipoComponente");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }
    }

    public static class ExclusaoAtributosDoComponente implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Componente")
                    && fa.getName().equals("atributos");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }

    }
}
