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
import view.ViewSelecionarDataBase;

/**
 *
 * @author JoãoVitor
 */
public final class SelecionarDataBasePresenter {

    ViewSelecionarDataBase view;
    PrincipalPresenter principalPresenter;
    GerenciarDataBase gerenciarDataBase;

    public SelecionarDataBasePresenter(final PrincipalPresenter principalPresenter, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewSelecionarDataBase();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.principalPresenter = principalPresenter;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setTitle("Cadastrar Atributo");
        preencheJlist();

        view.getBtnadicionar().addActionListener(new ActionListener() {

            @Override
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            public void actionPerformed(ActionEvent e) {
                try {
                    view.setEnabled(false);
                    chamarCriarAtributoPresente(pegarObejtoSelecionado());
                } catch (Exception ex) {
                    view.setEnabled(true);
                    JOptionPane.showMessageDialog(view, "Nenhum DataBase selecionado", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                principalPresenter.view.setEnabled(true);
                view.dispose();
            }
        });

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                principalPresenter.view.setEnabled(true);
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
        view.getjListdatabases().setModel(modelo);
    }

    public DataBase pegarObejtoSelecionado() {
        DataBase db;
        Iterator<DataBase> it = this.gerenciarDataBase.getDatabase().iterator();
        while (it.hasNext()) {
            db = it.next();
            if (view.getjListdatabases().getSelectedValue().equals(db.getNomeDataBase())) {
                return db;

            }
        }
        return null;

    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarCriarAtributoPresente(DataBase db) {
        new CriarAtributoPresenter(db, this,gerenciarDataBase);

    }

}
