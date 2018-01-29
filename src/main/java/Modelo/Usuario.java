/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Enum.TipoAdm;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Leonardo
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(length = 100, nullable = false)
    private String nomeCompleto;

    @Column(length = 20, unique = true, nullable = false, updatable = false) //Único login no banco
    private String login;

    @Column(length = 50, nullable = false)
    private String senha;

    @Column(unique = true, nullable = false, updatable = false) //Único e-mail no banco
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAdm tipoAdm;

    @Column(length = 30, unique = true, nullable = false) //Único CPF no banco
    private String cpf;

    @Column(length = 30, unique = true, nullable = false) //Único RG no banco
    private String rg;

    @Column(length = 30, nullable = false)
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioDoPedido", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Pedido> pedidos;

    //Construtor 
    public Usuario() {

    }

    // Getters e Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoAdm getTipoAdm() {
        return tipoAdm;
    }

    public void setTipoAdm(TipoAdm tipoAdm) {
        this.tipoAdm = tipoAdm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idUsuario);
        return hash;
    }

    public static String encryptBlowfish(String to_encrypt, String strkey) {
        try {
            SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return new String(cipher.doFinal(to_encrypt.getBytes()));
        } catch (Exception e) {
            return null;
        }
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.idUsuario, other.idUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nomeCompleto=" + nomeCompleto + ", login=" + login + ", email=" + email + ", tipoAdm=" + tipoAdm + ", cpf=" + cpf + ", rg=" + rg + ", enderecos=" + enderecos + '}';
    }

    public static class ExclusaoUsuarioDoPedido implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Usuario")
                    && fa.getName().equals("pedidos");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }

    }

}
