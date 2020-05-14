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
public class Atributo implements Serializable, Comparable<Atributo> {

    private String NomeAtributo;
    private String TipoAtributo;
    private String NOTNULL;
    private String ChavePrimaria;
    private String ChaveEstrangeira;
    private String tamanhochar_string;

    public Atributo(String NomeA, String TipoA, String notnull, String PK, String tamanho, String FK) {
        this.NomeAtributo = NomeA;
        this.TipoAtributo = TipoA;
        this.NOTNULL = notnull;
        this.ChavePrimaria = PK;
        this.tamanhochar_string = tamanho;
        this.ChaveEstrangeira = FK;
    }

    public String getTamanhochar_string() {
        return tamanhochar_string;
    }

    public void setTamanhochar_string(String tamanhochar_string) {
        this.tamanhochar_string = tamanhochar_string;
    }


    public String getChavePrimaria() {
        return ChavePrimaria;
    }

    public void setChavePrimaria(String ChavePrimaria) {
        this.ChavePrimaria = ChavePrimaria;
    }

    public String getNomeAtributo() {
        return NomeAtributo;
    }

    public void setNomeAtributo(String NomeAtributo) {
        this.NomeAtributo = NomeAtributo;
    }

    public String getTipoAtributo() {
        return TipoAtributo;
    }

    public void setTipoAtributo(String TipoAtributo) {
        this.TipoAtributo = TipoAtributo;
    }

    public String getNOTNULL() {
        return NOTNULL;
    }

    public void setNOTNULL(String NOTNULL) {
        this.NOTNULL = NOTNULL;
    }

    public String getChaveEstrangeira() {
        return ChaveEstrangeira;
    }

    public void setChaveEstrangeira(String ChaveEstrangeira) {
        this.ChaveEstrangeira = ChaveEstrangeira;
    }
    
    
    
    @Override
    public int compareTo(Atributo TipoAtributoPrim) {
        if   (TipoAtributoPrim.getChavePrimaria().equals("NÃO") )
            return -1;
        if (TipoAtributoPrim.getChavePrimaria().equals("SIM")) 
            return 1;
        return 0;
    }
}
