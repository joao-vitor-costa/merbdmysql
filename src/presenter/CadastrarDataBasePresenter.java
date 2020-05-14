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
import javax.swing.JOptionPane;
import model.DataBase;
import model.GerenciarDataBase;
import view.ViewCadastrarDataBase;

/**
 *
 * @author JoãoVitor
 */
public final class CadastrarDataBasePresenter {

    ViewCadastrarDataBase view;
    GerenciarDataBase gerenciaDataBase;
    PrincipalPresenter principalPresenter;

    public CadastrarDataBasePresenter(final GerenciarDataBase gerenciaDataBase, final PrincipalPresenter principalPresenter) {
        view = new ViewCadastrarDataBase();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.gerenciaDataBase = gerenciaDataBase;
        this.principalPresenter = principalPresenter;
        view.setTitle("Cadastrar DataBase");

        view.getBtnCadastrar().addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings({"ResultOfObjectAllocationIgnored", "UnusedAssignment", "IncompatibleEquals"})
            public void actionPerformed(ActionEvent e) {
                DataBase novo = new DataBase(view.getTextNomeDataBase().getText());
                int verifica = acharIgual();
                if (view.getTextNomeDataBase().getText().equals("")) {
                    JOptionPane.showMessageDialog(view, "Campo vazio.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    String palavra = view.getTextNomeDataBase().getText();
                    if (palavra.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(view, "Campo só contém Espaço.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (verifica == 0) {
                            gerenciaDataBase.getDatabase().add(novo);
                            view.getTextNomeDataBase().setText(null);
                            gerenciaDataBase.serializar();
                        } else {
                            view.getTextNomeDataBase().setText(null);
                            JOptionPane.showMessageDialog(view, "Nome já existe.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

                        }

                    }
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

    public int acharIgual() {
        DataBase db;
        Iterator<DataBase> it = this.gerenciaDataBase.getDatabase().iterator();
        while (it.hasNext()) {
            db = it.next();
            if (db.getNomeDataBase().toUpperCase().trim().equals(view.getTextNomeDataBase().getText().toUpperCase().trim())) {
                return 1;
            }
        }
        return 0;

    }
}
