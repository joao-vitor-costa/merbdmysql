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
import javax.swing.table.DefaultTableModel;
import model.Atributo;
import model.DataBase;
import model.Entidade;
import model.GerenciarDataBase;
import model.RelacionamentoEntidade;
import view.ViewAlterarAtributoNome;

/**
 *
 * @author JoãoVitor
 */
public class AlterarAtributoNomePresenter {

    ViewAlterarAtributoNome view;
    Entidade entidade;
    Atributo atributo;
    DataBase dataBase;
    EditarAtributoPresenter editarAtributoPresenter;
    GerenciarDataBase gerenciarDataBase;

    public AlterarAtributoNomePresenter(final Entidade entidade, final Atributo atributo, final EditarAtributoPresenter editarAtributoPresenter, final DataBase dataBase, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewAlterarAtributoNome();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.dataBase = dataBase;
        this.atributo = atributo;
        this.entidade = entidade;
        this.editarAtributoPresenter = editarAtributoPresenter;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setTitle("Alterar Nome");

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editarAtributoPresenter.view.setEnabled(true);
            }
        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editarAtributoPresenter.view.setEnabled(true);
                view.dispose();

            }
        });

        view.getBtnAlterar().addActionListener(new ActionListener() {

            @Override
            @SuppressWarnings("IncompatibleEquals")
            public void actionPerformed(ActionEvent e) {
                if (view.getTextnovonome().equals("")) {
                    JOptionPane.showMessageDialog(view, "Campo vazio.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    String palavra = view.getTextnovonome().getText();
                    if (palavra.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Campo só contém Espaço.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int verifica = acharIgual();
                        if (verifica == 0) {
                            int relacionamentoFK = verificaRelacionamentoFK();
                            while (relacionamentoFK == 1) {
                                RelacionamentoEntidade re = pegaRelacionamentoFK();
                                re.setChaveStrangeira(view.getTextnovonome().getText());
                                relacionamentoFK = verificaRelacionamentoFK();
                            }
                            
                            int relacionamentoPK = verificaRelacionamentoPK();
                            while (relacionamentoPK == 1) {
                                RelacionamentoEntidade re = pegaRelacionamentoPK();
                                re.setChaveprimaria(view.getTextnovonome().getText());
                                relacionamentoPK = verificaRelacionamentoPK();
                            }

                            if (entidade.getAssociativa().equals("SIM")) {
                                for (Atributo at : entidade.getAtributos()) {
                                    if (at.getChavePrimaria().equals("SIM")) {
                                        if (at.getNomeAtributo().equals(atributo.getNomeAtributo())) {
                                            at.setNomeAtributo(view.getTextnovonome().getText());
                                        }
                                    }

                                }
                            }
                            atributo.setNomeAtributo(view.getTextnovonome().getText());
                            view.getTextnovonome().setText(null);
                            gerenciarDataBase.serializar();
                            preencheTabela();
                            editarAtributoPresenter.view.setEnabled(true);
                            view.dispose();
                        } else {
                            JOptionPane.showMessageDialog(view, "Nome já existe.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        }

                    }
                }

            }
        });

    }

    public RelacionamentoEntidade pegaRelacionamentoFK() {
        for (Entidade ent : dataBase.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveStrangeira().equals(atributo.getNomeAtributo()))) {
                    return re;
                }
            }
        }
        return null;
    }

    public int verificaRelacionamentoFK() {
        for (Entidade ent : dataBase.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveStrangeira().equals(atributo.getNomeAtributo()))) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public RelacionamentoEntidade pegaRelacionamentoPK() {
        for (Entidade ent : dataBase.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveprimaria().equals(atributo.getNomeAtributo()))) {
                    return re;
                }
            }
        }
        return null;
    }

    public int verificaRelacionamentoPK() {
        for (Entidade ent : dataBase.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveprimaria().equals(atributo.getNomeAtributo()))) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public void preencheTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        editarAtributoPresenter.view.getTabela().setModel(modelo);

        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Não Nulo");
        modelo.addColumn("Chave Primaria");
        modelo.addColumn("Chave Estrangeira");

        for (Atributo at : entidade.getAtributos()) {
            String nome = at.getNomeAtributo();
            String nn = at.getNOTNULL();
            String tipo = at.getTipoAtributo();
            String pk = at.getChavePrimaria();
            String fk = at.getChaveEstrangeira();

            modelo.addRow(new Object[]{nome, tipo, nn, pk, fk});
        }

    }

    public int acharIgual() {
        Atributo at;
        Iterator<Atributo> it = this.entidade.getAtributos().iterator();
        while (it.hasNext()) {
            at = it.next();
            if (at.getNomeAtributo().toUpperCase().trim().equals(view.getTextnovonome().getText().toUpperCase().trim())) {
                return 1;
            }
        }
        return 0;

    }

}
