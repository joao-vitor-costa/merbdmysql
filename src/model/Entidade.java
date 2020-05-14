/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author JoãoVitor
 */
public class Entidade implements Serializable {
    private String NomeEntidade;
    private String Associativa;
    private final ArrayList<Atributo> atributos = new ArrayList<>();
    private ArrayList<RelacionamentoEntidade> RelacionamentoEntidades = new ArrayList<>();

    public String getAssociativa() {
        return Associativa;
    }

    public void setAssociativa(String Associativa) {
        this.Associativa = Associativa;
    }
    
    public Entidade(String NomeE, String associativa){
        this.Associativa = associativa;
        this.NomeEntidade = NomeE;
    }
    
    public ArrayList<RelacionamentoEntidade> getRelacionamentoEntidades() {
        return RelacionamentoEntidades;
    }

    public ArrayList<Atributo> getAtributos() {
        return atributos;
    }
    
    public void setRelacionamentoEntidades(ArrayList<RelacionamentoEntidade> RelacionamentoEntidades) {
        this.RelacionamentoEntidades = RelacionamentoEntidades;
    }
    
    public String getNomeEntidade() {
        return NomeEntidade;
    }
   
    public void setNomeEntidade(String NomeEntidade) {
        this.NomeEntidade = NomeEntidade;
    }
    
    
    
}
