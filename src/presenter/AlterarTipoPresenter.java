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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Atributo;
import model.DataBase;
import model.Entidade;
import model.GerenciarDataBase;
import model.RelacionamentoEntidade;
import view.ViewAlterarTipo;

/**
 *
 * @author JoãoVitor
 */
public class AlterarTipoPresenter {

    ViewAlterarTipo view;
    EditarAtributoPresenter editarAtributoPresenter;
    Atributo at;
    GerenciarDataBase gerenciarDataBase;
    Entidade entidade;
    DataBase database;

    public AlterarTipoPresenter(final EditarAtributoPresenter editarAtributoPresenter, Atributo atributo, final GerenciarDataBase gerenciarDataBase, final Entidade entidade, DataBase database) {
        view = new ViewAlterarTipo();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Alterar Tipo");
        this.editarAtributoPresenter = editarAtributoPresenter;
        this.at = atributo;
        this.gerenciarDataBase = gerenciarDataBase;
        this.entidade = entidade;
        this.database = database;

        view.getComboBoxTipoAtributo().removeAllItems();

        view.getComboBoxTipoAtributo().addItem("INT");
        view.getComboBoxTipoAtributo().addItem("FLOAT");
        view.getComboBoxTipoAtributo().addItem("DOUBLE");
        view.getComboBoxTipoAtributo().addItem("DATE");
        view.getComboBoxTipoAtributo().addItem("DATETIME");
        view.getComboBoxTipoAtributo().addItem("TIMESTAMP");
        view.getComboBoxTipoAtributo().addItem("YEAR");
        view.getComboBoxTipoAtributo().addItem("CHAR");
        view.getComboBoxTipoAtributo().addItem("VARCHAR");
        view.getComboBoxTipoAtributo().addItem("BLOB");
        view.getComboBoxTipoAtributo().addItem("ENUM");

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editarAtributoPresenter.view.setEnabled(true);
            }
        });

        view.getComboBoxTipoAtributo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) view.getComboBoxTipoAtributo().getSelectedItem();
                if (tipo.equals("CHAR") || tipo.equals("VARCHAR")) {
                    view.getTextM().setEnabled(true);
                } else {
                    view.getTextM().setEnabled(false);
                }

            }
        });

        view.getBtnsalvar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (at.getChavePrimaria().equals("SIM")) {
                    Object opcoes[] = {"Sim", "Não"};
                    int result = JOptionPane.showOptionDialog(null, "Atributo Chave Primaria, relacionamentos ligados a chave seram perdidos. Deseja alterar o tipo do Atributo? ", "ALERTA",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);

                    if (result == 0) {
                        at.setTipoAtributo((String) view.getComboBoxTipoAtributo().getSelectedItem());
                        entidade.getRelacionamentoEntidades().clear();
                        gerenciarDataBase.serializar();
                        preencheTabela();
                    }
                } else {
                    if (at.getChaveEstrangeira().equals("SIM")) {
                        Object opcoes[] = {"Sim", "Não"};
                        int result = JOptionPane.showOptionDialog(null, "Atributo Chave Estrangeira, relacionamentos ligados a chave seram perdidos. Deseja alterar o tipo do Atributo? ", "ALERTA",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);

                        if (result == 0) {
                            RelacionamentoEntidade re = pegaRelacionamento();
                            Entidade ent = pegaEntidadePai();
                            ent.getRelacionamentoEntidades().remove(re);
                            at.setTipoAtributo((String) view.getComboBoxTipoAtributo().getSelectedItem());
                            gerenciarDataBase.serializar();
                            preencheTabela();
                            editarAtributoPresenter.view.setEnabled(true);
                            view.dispose();
                        }

                    } else {
                        at.setTipoAtributo((String) view.getComboBoxTipoAtributo().getSelectedItem());
                        gerenciarDataBase.serializar();
                        preencheTabela();
                    }
                }

            }

        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editarAtributoPresenter.view.setEnabled(true);
                view.dispose();

            }
        });

    }

    public RelacionamentoEntidade pegaRelacionamento() {
        for (Entidade ent : database.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveStrangeira().equals(at.getNomeAtributo()))) {
                    return re;
                }
            }
        }
        return null;
    }

    public Entidade pegaEntidadePai() {
        for (Entidade ent : database.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveStrangeira().equals(at.getNomeAtributo()))) {
                    return ent;
                }
            }
        }
        return null;
    }

    public void preencheTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        editarAtributoPresenter.view.getTabela().setModel(modelo);

        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Não Nulo");
        modelo.addColumn("Chave Primaria");
        modelo.addColumn("Chave Estrangeira");

        for (Atributo atr : entidade.getAtributos()) {
            String nome = atr.getNomeAtributo();
            String nn = atr.getNOTNULL();
            String tipo = atr.getTipoAtributo();
            String pk = atr.getChavePrimaria();
            String fk = atr.getChaveEstrangeira();

            modelo.addRow(new Object[]{nome, tipo, nn, pk, fk});
        }

    }

}
