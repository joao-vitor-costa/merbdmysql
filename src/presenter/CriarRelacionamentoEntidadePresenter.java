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
import model.Atributo;
import model.DataBase;
import model.Entidade;
import model.GerenciarDataBase;
import model.RelacionamentoEntidade;
import view.ViewCriarRelacionamentoEntidade;

/**
 *
 * @author JoãoVitor
 */
public final class CriarRelacionamentoEntidadePresenter {

    ViewCriarRelacionamentoEntidade view;
    GerenciarDataBase gerenciarDataBase;
    Entidade entidade;
    DataBase database;
    EntidadeAssociativaPresenter entidadeAssociativaPresenter;
    PrincipalPresenter principalPresenter;

    public CriarRelacionamentoEntidadePresenter(final PrincipalPresenter principalPresenter, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewCriarRelacionamentoEntidade();
        this.principalPresenter = principalPresenter;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Cadastrar Relacionamento");

        //Limpo as caixas de combinações
        view.getComboBoxentidade01().removeAllItems();
        view.getComboBoxEntidade02().removeAllItems();
        view.getComboBoxCarnalidade().removeAllItems();
        view.getComboBoxChavePrimaria().removeAllItems();
        view.getComboboxChaveEstrangeira().removeAllItems();

        //Preencho as caixas de combinações
        view.getComboBoxCarnalidade().addItem("1..1");
        view.getComboBoxCarnalidade().addItem("1..N");
        view.getComboBoxCarnalidade().addItem("N..N");

        preencheJlist();

        view.getBtncarregar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                database = pegarObejtoSelecionado();
                preencheComBoBoxEntidades(database);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(view,"Nenhum DataBase Selecionado.","ATENÇÃO",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        view.getComboBoxentidade01().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getComboBoxChavePrimaria().removeAllItems();
                String entidade01;
                entidade01 = (String) view.getComboBoxentidade01().getSelectedItem();
                Entidade ent = pegaEntidade(entidade01);
                for (Atributo at : ent.getAtributos()) {
                    if (at.getChavePrimaria().equals("SIM")) {
                        view.getComboBoxChavePrimaria().addItem(at.getNomeAtributo());
                    }
                }

            }
        });

        view.getComboBoxEntidade02().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getComboboxChaveEstrangeira().removeAllItems();
                String entidade02;
                entidade02 = (String) view.getComboBoxEntidade02().getSelectedItem();
                Entidade ent = pegaEntidade(entidade02);
                if (view.getComboBoxCarnalidade().getSelectedItem().equals("N..N")) {
                    for (Atributo at : ent.getAtributos()) {
                        if (at.getChavePrimaria().equals("SIM")) {
                            view.getComboboxChaveEstrangeira().addItem(at.getNomeAtributo());
                        }
                    }
                } else {
                    for (Atributo a : ent.getAtributos()) {
                        if (a.getChaveEstrangeira().equals("SIM")) {
                            view.getComboboxChaveEstrangeira().addItem(a.getNomeAtributo());
                        }
                    }
                }
            }
        });

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

        view.getBtncriarrelacao().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String entidade01 = (String) view.getComboBoxentidade01().getSelectedItem();
                String entidade02 = (String) view.getComboBoxEntidade02().getSelectedItem();
                String relacao = (String) view.getComboBoxCarnalidade().getSelectedItem();
                String fk = (String) view.getComboboxChaveEstrangeira().getSelectedItem();
                String pk = (String) view.getComboBoxChavePrimaria().getSelectedItem();
                Entidade pai = pegaEntidade(entidade01);
                Entidade filha = pegaEntidade(entidade02);
                database = pegarObejtoSelecionado();
                int verificaTipo = comparaTipo(pai, filha, pk, fk);
                if (verificaTipo == 1) {
                    if (view.getComboBoxCarnalidade().getSelectedItem().equals("N..N")) {
                        view.setEnabled(false);
                        String tipo1 = pegaTipoAtributo(pai, pk);
                        String tipo2 = pegaTipoAtributo(filha, fk);
                        Object opcoes[] = {"Sim", "Não"};
                        int result = JOptionPane.showOptionDialog(null, "Entidade Associativa Criada. Dejeja dar um nome para nova Entidade?", "Entidade Associativa",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
                        if (result == 0) {
                            chamarEntidadeAssociativaPresenter(database, pai, filha, pk, fk);
                        } else {
                            Entidade novo = new Entidade(pai.getNomeEntidade() + "_" + filha.getNomeEntidade(), "SIM");
                            database.getEntidade().add(novo);

                            RelacionamentoEntidade filhoassociativa = new RelacionamentoEntidade(pai.getNomeEntidade() + "_" + filha.getNomeEntidade(), pk, pk, pai.getNomeEntidade());
                            RelacionamentoEntidade filhoassociativa2 = new RelacionamentoEntidade(pai.getNomeEntidade() + "_" + filha.getNomeEntidade(), fk, fk, filha.getNomeEntidade());
                            pai.getRelacionamentoEntidades().add(filhoassociativa);
                            filha.getRelacionamentoEntidades().add(filhoassociativa2);

                            Atributo at1 = pegaAtributo(pai, pk);
                            Atributo at2 = pegaAtributo(filha, fk);

                            novo.getAtributos().add(at1);
                            novo.getAtributos().add(at2);
                            gerenciarDataBase.serializar();
                            view.setEnabled(true);
                        }

                    } else {
                        String tipo1 = pegaTipoAtributo(filha, fk);
                        RelacionamentoEntidade filho = new RelacionamentoEntidade(entidade02, fk, pk, pai.getNomeEntidade());
                        pai.getRelacionamentoEntidades().add(filho);
                        gerenciarDataBase.serializar();
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Tipo de Atributo incopantivel.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    public void preencheComBoBoxEntidades(DataBase database) {
        view.getComboBoxentidade01().removeAllItems();
        view.getComboBoxEntidade02().removeAllItems();
        if (database.getEntidade().size() > 0) {
            Entidade ent;
            Iterator<Entidade> it = this.database.getEntidade().iterator();
            while (it.hasNext()) {
                ent = it.next();
                view.getComboBoxentidade01().addItem(ent.getNomeEntidade());
                view.getComboBoxEntidade02().addItem(ent.getNomeEntidade());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Este Database não possui entidade.");
        }
    }

    public Entidade pegaEntidade(String entidade) {
        Entidade ent;
        Iterator<Entidade> it = this.database.getEntidade().iterator();
        while (it.hasNext()) {
            ent = it.next();
            if (ent.getNomeEntidade().equals(entidade)) {
                return ent;
            }

        }

        return null;

    }

    public String pegaTipoAtributo(Entidade ent, String nome) {
        String tipo;
        for (Atributo a : ent.getAtributos()) {
            if (a.getNomeAtributo().equals(nome)) {
                tipo = a.getTipoAtributo();
                return tipo;
            }
        }
        return null;
    }

    public int comparaTipo(Entidade ent1, Entidade ent2, String pk, String fk) {
        int retorno = 0;
        String tipo = null;
        for (Atributo a : ent1.getAtributos()) {
            if (a.getNomeAtributo().equals(pk)) {
                tipo = a.getTipoAtributo();
            }
        }

        for (Atributo b : ent2.getAtributos()) {
            if (b.getNomeAtributo().equals(fk)) {
                if (b.getTipoAtributo().equals(tipo)) {
                    retorno = 1;
                    return retorno;
                }
            }
        }

        return retorno;
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarEntidadeAssociativaPresenter(DataBase db, Entidade ent, Entidade ent2, String pk, String fk) {
        new EntidadeAssociativaPresenter(this, db, ent, ent2, pk, fk, gerenciarDataBase);
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

    public Atributo pegaAtributo(Entidade ent, String nome) {
        for (Atributo at : ent.getAtributos()) {
            if (at.getNomeAtributo().equals(nome)) {
                return at;
            }
        }
        return null;
    }

}
