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
import view.ViewEditarDataBase;

/**
 *
 * @author JoãoVitor
 */
public final class EditarDataBasePresenter {

    ViewEditarDataBase view;
    PrincipalPresenter principalPresenter;
    GerenciarDataBase gerenciarDataBase;
    DataBase database;
    CadastrarDataBasePresenter cadastrarDataBasePresenter;

    public EditarDataBasePresenter(final PrincipalPresenter principalPresenter, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewEditarDataBase();
        this.principalPresenter = principalPresenter;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Editar DataBase");
        preencheJlist();

        view.getBtneditarentidade().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.setEnabled(false);
                    chamarEditarEntidadePresenter(pegarObejtoSelecionado());
                } catch (Exception ex) {
                    view.setEnabled(true);
                    JOptionPane.showMessageDialog(view, "Nenhum DataBase selecionado.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                principalPresenter.view.setEnabled(true);
            }
        });

        view.getBtnalterarnomedatabase().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.setEnabled(false);
                    chamarAlterarNomeDataBasePresenter(pegarObejtoSelecionado());
                } catch (Exception ex) {
                    view.setEnabled(true);
                    JOptionPane.showMessageDialog(view, "Nenhum DataBase selecionado.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        view.getBtnexcluir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataBase db = pegarObejtoSelecionado();
                    gerenciarDataBase.getDatabase().remove(db);
                    gerenciarDataBase.serializarExcluir();
                    preencheJlist();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Nenhum DataBase selecionado.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
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

    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarEditarEntidadePresenter(DataBase db) {
        new EditarEntidadePresenter(this, db,gerenciarDataBase);
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarAlterarNomeDataBasePresenter(DataBase db) {
        new AlterarNomeDataDasePresenter(this, db, gerenciarDataBase);
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

}
