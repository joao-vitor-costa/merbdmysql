/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JoãoVitor
 */
public class GerenciarDataBase implements Serializable{

    private ArrayList<DataBase> database = new ArrayList<>();

    public GerenciarDataBase() {
        database = new ArrayList<>();
    }

    public ArrayList<DataBase> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<DataBase> database) {
        this.database = database;
    }

    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    public void serializar() {
        try {
            FileOutputStream fos = new FileOutputStream("meuarquivo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(database);
            oos.close();
            fos.close();
             JOptionPane.showMessageDialog(null,"Salvo com Sucesso");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Erro ao Salvar");
            ioe.printStackTrace();
            
        } 

    }

    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    public void deserializar() {
        try {
            FileInputStream fis = new FileInputStream("meuarquivo.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            database = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("sucesso");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("deu ruim");
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Classe nao encontrada");
            c.printStackTrace();
            return;
        }
        for (DataBase db : database) {
            System.out.println(db);
        }

    }
    
    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    public void serializarExcluir(){
         try {
            FileOutputStream fos = new FileOutputStream("meuarquivo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(database);
            oos.close();
            fos.close();
             JOptionPane.showMessageDialog(null,"Excluida com sucesso");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Erro");
            ioe.printStackTrace();
            
        } 
    }
}
