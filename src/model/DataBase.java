/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author JoãoVitor
 */
public class DataBase implements Serializable {
    private String NomeDataBase;
    private ArrayList<Entidade> entidades = new ArrayList<>();
    
    public DataBase(String NomeD){
        this.NomeDataBase = NomeD;
    }

    public String getNomeDataBase() {
        return NomeDataBase;
    }

    public List<Entidade> getEntidade() {
        return entidades;
    }

    public void setEntidades(ArrayList<Entidade> entidades) {
        this.entidades = entidades;
    }

    public void setNome(String NomeDataBase) {
        this.NomeDataBase = NomeDataBase;
    } 

  
   
}
