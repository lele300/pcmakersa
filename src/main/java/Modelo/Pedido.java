/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Enum.StatusPedido;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    private String dataPedido;

    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Componente> itensComponente;

    @ManyToOne()
    private Usuario usuarioDoPedido;

    public Pedido() {

    }

    public Pedido(double valorTotal, String dataPedido) {
        this.valorTotal = valorTotal;
        this.dataPedido = dataPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<Componente> getItensComponente() {
        return itensComponente;
    }

    public void setItensComponente(List<Componente> itensComponente) {
        this.itensComponente = itensComponente;
    }

    public Usuario getUsuarioDoPedido() {
        return usuarioDoPedido;
    }

    public void setUsuarioDoPedido(Usuario usuarioDoPedido) {
        this.usuarioDoPedido = usuarioDoPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido alterarStatusPedido(Pedido pedido, String statusPedido) {

        if (statusPedido.equalsIgnoreCase("PENDENTE")) {
            pedido.setStatusPedido(StatusPedido.PENDENTE);
        } else if (statusPedido.equalsIgnoreCase("PAGO")) {
            pedido.setStatusPedido(StatusPedido.PAGO);
        } else {
            pedido.setStatusPedido(StatusPedido.CANCELADO);
        } 
           
        return pedido;
    }

    public double calcularPreco(List<Componente> listaComponente) {

        double somaTotal = 0;
        for (Componente comp : listaComponente) {
            somaTotal += comp.getPreco();
        }

        return somaTotal;
    }

    public static class ExclusaoUsuarioDoPedidoDoPedido implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Pedido")
                    && fa.getName().equals("usuarioDoPedido");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }

    }

    public static class ExclusaoItensComponenteDoPedido implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getDeclaringClass().getName().equals("Modelo.Pedido")
                    && fa.getName().equals("itensComponente");
        }

        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return false;
        }

    }

}
