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
import view.ViewEditarEntidade;

/**
 *
 * @author JoãoVitor
 */
public final class EditarEntidadePresenter {

    ViewEditarEntidade view;
    EditarDataBasePresenter editarDataBasePresenter;
    GerenciarDataBase gerenciarDataBase;
    DataBase db;

    public EditarEntidadePresenter(final EditarDataBasePresenter editarDataBasePresenter, final DataBase db, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewEditarEntidade();
        this.editarDataBasePresenter = editarDataBasePresenter;
        this.db = db;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Editar Entidade");
        preencheJlist();

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

        view.getBtneditaratributos().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.setEnabled(false);
                    chamarEditarAtributoPresenter(pegarObejtoSelecionado());
                } catch (Exception ex) {
                    view.setEnabled(true);
                    JOptionPane.showMessageDialog(view, "Nenhuma Entidade selecionada.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        view.getBtnexcluirentidade().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Entidade en = pegarObejtoSelecionado();
                    en.getRelacionamentoEntidades().clear();
                    
                    int relacionamento = verificaRelacionamento(en);
                    
                    while(relacionamento == 1){
                        Entidade pai = pegaEntidadePai(en);
                        RelacionamentoEntidade re = pegaRelacionamento(en);
                        pai.getRelacionamentoEntidades().remove(re);
                        relacionamento = verificaRelacionamento(en);
                    }
                    
                    db.getEntidade().remove(en);
                    gerenciarDataBase.serializarExcluir();
                    preencheJlist();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Nenhuma Entidade selecionada.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        view.getBtnalterarnomeentidade().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    view.setEnabled(false);
                    chamarAlteraNomeEntidadePresenter(pegarObejtoSelecionado());
                } catch (Exception ex) {
                    view.setEnabled(true);
                    JOptionPane.showMessageDialog(view, "Nenhuma Entidade selecionada.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarEditarAtributoPresenter(Entidade ent) {
        new EditarAtributoPresenter(this, ent, db, gerenciarDataBase);
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarAlteraNomeEntidadePresenter(Entidade ent) {
        new AlterarNomeEntidadePresenter(this, ent, db,gerenciarDataBase);
    }

    public void preencheJlist() {
        DefaultListModel modelo = new DefaultListModel();
        Entidade ent;
        Iterator<Entidade> it = this.db.getEntidade().iterator();
        while (it.hasNext()) {
            ent = it.next();
            modelo.addElement(ent.getNomeEntidade());
        }
        view.getjListentidades().setModel(modelo);
    }

    public Entidade pegarObejtoSelecionado() {
        Entidade ent;
        Iterator<Entidade> it = this.db.getEntidade().iterator();
        while (it.hasNext()) {
            ent = it.next();
            if (view.getjListentidades().getSelectedValue().equals(ent.getNomeEntidade())) {
                return ent;
            }
        }
        return null;
    }

    public RelacionamentoEntidade pegaRelacionamento(Entidade enti) {
        for (Entidade ent : db.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(enti.getNomeEntidade()))) {
                    return re;
                }
            }
        }
        
        return null;
    }
    
    public Entidade pegaEntidadePai(Entidade enti) {
        for (Entidade ent : db.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(enti.getNomeEntidade()))) {
                    return ent;
                }
            }
        }
        return null;
    }
    
    public int verificaRelacionamento(Entidade enti) {
        for (Entidade ent : db.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(enti.getNomeEntidade()))) {
                    return 1;
                }
            }
        }
        return 0;
    }
    

}
