/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author JoãoVitor
 */
public class RelacionamentoEntidade implements Serializable {
    private String NomeEntidadePai;
    private String NomeEntidadeR;
    private String ChaveStrangeira;
    private String chaveprimaria;
    
    
    public RelacionamentoEntidade(String nomeentidadeR,String chaveStrangeira,String chaveprimaria,String nomeEntidadePai){
        this.NomeEntidadeR = nomeentidadeR;
        this.chaveprimaria = chaveprimaria;
        this.ChaveStrangeira = chaveStrangeira;
        this.NomeEntidadePai = nomeEntidadePai;
    }

    public String getChaveprimaria() {
        return chaveprimaria;
    }

    public void setChaveprimaria(String chaveprimaria) {
        this.chaveprimaria = chaveprimaria;
    }

    public String getChaveStrangeira() {
        return ChaveStrangeira;
    }

    public void setChaveStrangeira(String ChaveStrangeira) {
        this.ChaveStrangeira = ChaveStrangeira;
    }

    public String getNomeEntidadeR() {
        return NomeEntidadeR;
    }

    public void setNomeEntidadeR(String NomeEntidadeR) {
        this.NomeEntidadeR = NomeEntidadeR;
    }  

    public String getNomeEntidadePai() {
        return NomeEntidadePai;
    }

    public void setNomeEntidadePai(String NomeEntidadePai) {
        this.NomeEntidadePai = NomeEntidadePai;
    }
    
}
