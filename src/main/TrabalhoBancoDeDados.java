/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import model.GerenciarDataBase;
import presenter.PrincipalPresenter;

/**
 *
 * @author JoãoVitor
 */
public class TrabalhoBancoDeDados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GerenciarDataBase gerenciaDataBase = new GerenciarDataBase();
        PrincipalPresenter vpp = new PrincipalPresenter(gerenciaDataBase);
        gerenciaDataBase.deserializar();
        
        
        
        
    }
    
}
