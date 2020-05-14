/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.DataBase;
import model.GerenciarDataBase;
import view.ViewExportarModeloLogico;

/**
 *
 * @author JoãoVitor
 */
public final class ExportarModeloLogicoPresenter {

    ViewExportarModeloLogico view;
    PrincipalPresenter principalPresenter;
    GerenciarDataBase gerenciarDataBase;

    public ExportarModeloLogicoPresenter(final PrincipalPresenter principalPresenter, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewExportarModeloLogico();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.gerenciarDataBase = gerenciarDataBase;
        this.principalPresenter = principalPresenter;
        view.setTitle("Modelo Logico");
        preencheJlist();

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                principalPresenter.view.setEnabled(true);
            }
        });
        
          view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                principalPresenter.view.setEnabled(true);
                view.dispose();
                
            }
        });

        view.getBtnexportar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exportaModeloLogico(pegarObejtoSelecionado());
            }
        });
    }

    public void preencheJlist() {
        DefaultListModel modelo = new DefaultListModel();
        DataBase db;
        Iterator<DataBase> it = this.gerenciarDataBase.getDatabase().iterator();
        while (it.hasNext()) {
            db = it.next();
            modelo.addElement(db.getNomeDataBase());
        }

        view.getjListdatabase().setModel(modelo);
    }

    public DataBase pegarObejtoSelecionado() {
        DataBase db;
        Iterator<DataBase> it = this.gerenciarDataBase.getDatabase().iterator();
        while (it.hasNext()) {
            db = it.next();
            if (view.getjListdatabase().getSelectedValue().equals(db.getNomeDataBase())) {
                return db;
            }
        }
        return null;
    }

    /// Marco implementa aqui o salvar em aquivo
    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    public void exportaModeloLogico(DataBase db) {
        int i = 0, j = 0;
        int flag = 0;
        File arquivo = new File("C:\\Users\\JoãoVitor\\Documents\\NetBeansProjects\\DBDTrabalho1_JoaoVitor_MarcoAurelio\\" + db.getNomeDataBase() + ".txt");
        try {

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            } else {
                arquivo.delete();
                arquivo.createNewFile();
            }

            //escreve no arquivo
            FileWriter fw = new FileWriter(arquivo, true);

            BufferedWriter bw = new BufferedWriter(fw);

            while (i < db.getEntidade().size()) {
                Collections.sort(db.getEntidade().get(i).getAtributos());
                // for(i = 0;i < db.getEntidade().size();i++){
                bw.write(db.getEntidade().get(i).getNomeEntidade() + "(");
                while (j < db.getEntidade().get(i).getAtributos().size()) {
                    bw.write(db.getEntidade().get(i).getAtributos().get(j).getNomeAtributo());
                    j++;
                    if (j < db.getEntidade().get(i).getAtributos().size()) {
                        bw.write(",");
                    }

                }
                j = 0;
                bw.write(")");
                bw.newLine();
                //}         
                i++;
            }
            bw.newLine();
            i++;

            bw.close();
            fw.close();
            JOptionPane.showMessageDialog(view,"Exportado com Sucesso ","Notificação", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view,"Não foi exportado com Sucesso ","Notificação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
