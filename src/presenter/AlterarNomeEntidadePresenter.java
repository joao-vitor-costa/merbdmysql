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
import model.RelacionamentoEntidade;
import view.ViewAlterarNomeEntidade;

/**
 *
 * @author JoãoVitor
 */
public class AlterarNomeEntidadePresenter {

    ViewAlterarNomeEntidade view;
    EditarEntidadePresenter editarEntidadePresenter;
    Entidade entidade;
    DataBase dataBase;
    GerenciarDataBase gerenciarDataBase;

    public AlterarNomeEntidadePresenter(final EditarEntidadePresenter editarEntidadePresenter, final Entidade entidade, DataBase dataBase, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewAlterarNomeEntidade();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.entidade = entidade;
        this.editarEntidadePresenter = editarEntidadePresenter;
        this.dataBase = dataBase;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setTitle("Alterar Nome");

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editarEntidadePresenter.view.setEnabled(true);
            }
        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editarEntidadePresenter.view.setEnabled(true);
                view.dispose();

            }
        });

        view.getBtnalterar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextnome().getText().equals("")) {
                    JOptionPane.showMessageDialog(view, "Campo vazio.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    String palavra = view.getTextnome().getText();
                    if (palavra.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Campo só contém Espaço.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

                    } else {
                        int verifica = acharIgual();
                        if (verifica == 0) {
                            int relacionamento = verificaRelacionamento(entidade);
                            while (relacionamento == 1) {
                                RelacionamentoEntidade re = pegaRelacionamento(entidade);
                                re.setNomeEntidadeR(view.getTextnome().getText());
                                relacionamento = verificaRelacionamento(entidade);
                            }
                            entidade.setNomeEntidade(view.getTextnome().getText());
                            view.getTextnome().setText(null);
                            gerenciarDataBase.serializar();
                            preencheJlist();
                            editarEntidadePresenter.view.setEnabled(true);
                            view.dispose();
                        } else {
                            JOptionPane.showMessageDialog(view, "Nome ja existe.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        }

                    }

                }

            }
        });

    }

    public int acharIgual() {
        Entidade ent;
        Iterator<Entidade> it = this.dataBase.getEntidade().iterator();
        while (it.hasNext()) {
            ent = it.next();
            if (ent.getNomeEntidade().toUpperCase().trim().equals(view.getTextnome().getText().toUpperCase().trim())) {
                return 1;
            }
        }
        return 0;

    }

    public void preencheJlist() {
        DefaultListModel modelo = new DefaultListModel();
        Entidade ent;
        Iterator<Entidade> it = this.dataBase.getEntidade().iterator();
        while (it.hasNext()) {
            ent = it.next();
            modelo.addElement(ent.getNomeEntidade());
        }
        editarEntidadePresenter.view.getjListentidades().setModel(modelo);
    }

    public RelacionamentoEntidade pegaRelacionamento(Entidade enti) {
        for (Entidade ent : dataBase.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(enti.getNomeEntidade()))) {
                    return re;
                }
            }
        }

        return null;
    }

    public int verificaRelacionamento(Entidade enti) {
        for (Entidade ent : dataBase.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(enti.getNomeEntidade()))) {
                    return 1;
                }
            }
        }
        return 0;
    }

}
