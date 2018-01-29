/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leo_l
 */
public class Carrinho {

    private List<Componente> componentesNoCarrinho = new ArrayList<>();

    public List<Componente> getComponentes() {
        return componentesNoCarrinho;
    }

    public void adicionarNoCarrinho(Componente componente) {
        componentesNoCarrinho.add(componente);
    }

    public void removerDoCarrinho(Componente componente) {
        componentesNoCarrinho.remove(componente);
    }
    
    public double retornaSomaWatts(List<Componente> listaCarrinho){
        
        double somaWatts = 0;
        
        for(Componente comp : listaCarrinho){
            for(Atributo at : comp.getAtributos()){
                if("Consumo Watts".equals(at.getTipoAtributo().getNomeAtributo())){
                    double wattsComponente = Double.parseDouble(at.getValor());
                    somaWatts += wattsComponente;
                }
            }           
        }       
        return somaWatts;
    }
}
