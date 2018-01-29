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
 * @author Leonardo
 */

@Entity
public class Endereco implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;
    
    @Column(length = 8, nullable = false) //Único CEP no banco
    private String cep;
    
    @Column(length = 100, nullable = false)
    private String rua;

    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 2, nullable = false)
    private String uf;

    @Column(length = 6, nullable = false)
    private int numero;

    @Column(length = 60, nullable = false)
    private String cidade;

    @Column(length = 50, nullable = false)
    private String complemento;
   
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario; 

    //Getters e Setters

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        final Endereco other = (Endereco) obj;
        return Objects.equals(this.idEndereco, other.idEndereco);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idEndereco);
        return hash;
    }

    @Override
    public String toString() {
        return "Endereco{" + "idEndereco=" + idEndereco + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", uf=" + uf + ", numero=" + numero + ", cidade=" + cidade + ", complemento=" + complemento + '}';
    }
    
    
    public static class ExclusaoUsuarioDoEndereco implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Endereco") &&
                    fa.getName().equals("usuario");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }
        
    }
}
