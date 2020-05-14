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
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.DataBase;
import model.GerenciarDataBase;
import view.ViewAlterarNomeDatabase;

/**
 *
 * @author JoãoVitor
 */
public class AlterarNomeDataDasePresenter {

    ViewAlterarNomeDatabase view;
    EditarDataBasePresenter editarDataBasePresenter;
    DataBase db;
    GerenciarDataBase gerenciarDataBase;

    public AlterarNomeDataDasePresenter(final EditarDataBasePresenter editarDataBasePresenter, final DataBase db, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewAlterarNomeDatabase();
        this.editarDataBasePresenter = editarDataBasePresenter;
        this.db = db;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Alterar Nome");

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editarDataBasePresenter.view.setEnabled(true);
            }
        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editarDataBasePresenter.view.setEnabled(true);
                view.dispose();

            }
        });

        view.getBtnalterar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextnovonome().getText().equals("")) {
                    JOptionPane.showMessageDialog(view, "Campo vazio.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    String palavra = view.getTextnovonome().getText();
                    if (palavra.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Campo só contém Espaço.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

                    } else {
                        int verifica = acharIgual();
                        if (verifica == 0) {
                            db.setNome(view.getTextnovonome().getText());
                            view.getTextnovonome().setText(null);
                            preencheJlist();
                            gerenciarDataBase.serializar();
                            editarDataBasePresenter.view.setEnabled(true);
                            view.dispose();
                        } else {
                            JOptionPane.showMessageDialog(view, "Nome já existe.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        }

                    }
                }
            }
        });
    }

    public int acharIgual() {
        Iterator<DataBase> it = this.gerenciarDataBase.getDatabase().iterator();
        DataBase database;
        while (it.hasNext()) {
            database = it.next();
            if (database.getNomeDataBase().toUpperCase().trim().equals(view.getTextNovoNomeDataBase().getText().toUpperCase().trim())) {
                return 1;
            }
        }
        return 0;

    }

    public void preencheJlist() {
        DefaultListModel modelo = new DefaultListModel();
        DataBase database;
        Iterator<DataBase> it = this.gerenciarDataBase.getDatabase().iterator();
        while (it.hasNext()) {
            database = it.next();
            modelo.addElement(database.getNomeDataBase());
        }

        editarDataBasePresenter.view.getjListdatabases().setModel(modelo);
    }

}
