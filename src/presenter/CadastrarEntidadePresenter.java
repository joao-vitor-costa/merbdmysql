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
import model.Entidade;
import model.GerenciarDataBase;
import view.ViewCadastrarEntidade;

/**
 *
 * @author JoãoVitor
 */
public final class CadastrarEntidadePresenter {

    ViewCadastrarEntidade view;
    GerenciarDataBase gerenciarDataBase;
    PrincipalPresenter principalPresenter;
    DataBase database;

    public CadastrarEntidadePresenter(final PrincipalPresenter principalPresenter, final GerenciarDataBase gerenciarDataBase) {
        this.view = new ViewCadastrarEntidade();
        this.gerenciarDataBase = gerenciarDataBase;
        this.principalPresenter = principalPresenter;
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Cadastrar Entidade");
        preencheJlist();

        view.getBtnCadastrarEntidade().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataBase db = pegarObejtoSelecionado();
                    int verifica = verificarIgual(db, view.getTextNomeEntidade().getText());
                    if (view.getTextNomeEntidade().getText().equals("")) {
                        JOptionPane.showMessageDialog(view, "Campo Vazio.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

                    } else {
                        Entidade novo = new Entidade(view.getTextNomeEntidade().getText(), "NÃO");
                        String palavra = view.getTextNomeEntidade().getText();
                        if (palavra.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(view, "Campo só contém Espaço.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (verifica == 0) {
                                db.getEntidade().add(novo);
                                gerenciarDataBase.serializar();
                                view.getTextNomeEntidade().setText(null);
                            } else {
                                JOptionPane.showMessageDialog(view, "Nome já Existe.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                                view.getTextNomeEntidade().setText(null);

                            }
                        }
                    }
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

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                principalPresenter.view.setEnabled(true);
            }
        }
        );

    }

    public int verificarIgual(DataBase db, String nome) {
        for (Entidade ent : db.getEntidade()) {
            if (ent.getNomeEntidade().toUpperCase().trim().equals(nome.toUpperCase().trim())) {
                return 1;
            }
        }

        return 0;

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
