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
import view.ViewCriarAtributo;

/**
 *
 * @author JoãoVitor
 */
public final class CriarAtributoPresenter {

    ViewCriarAtributo view;
    Entidade entidade;
    DataBase database;
    SelecionarDataBasePresenter selecionarDataBasePresenter;
    GerenciarDataBase gerenciarDataBase;

    public CriarAtributoPresenter(final DataBase database, final SelecionarDataBasePresenter selecionarDataBasePresenter, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewCriarAtributo();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Cadastrar Atributo");
        this.database = database;
        this.selecionarDataBasePresenter = selecionarDataBasePresenter;
        this.gerenciarDataBase = gerenciarDataBase;
        preencheJlist();

        //Limpo as Caixas de Combinações
        view.getComboBoxChavePrimaria().removeAllItems();
        view.getComboBoxNotNull().removeAllItems();
        view.getComboBoxTipoAtributo().removeAllItems();
        view.getComboboxChaveEstrangeira().removeAllItems();

        //Preencho as Caixas de Combinações
        view.getComboBoxChavePrimaria().addItem("SIM");
        view.getComboBoxChavePrimaria().addItem("NÃO");
        view.getComboBoxNotNull().addItem("SIM");
        view.getComboBoxNotNull().addItem("NÃO");
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
        view.getComboboxChaveEstrangeira().addItem("NÃO");
        view.getComboboxChaveEstrangeira().addItem("SIM");
        view.getTextM().setEnabled(false);
        preencheJlist();

        view.getBtnCriarAtributo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String chaveprimaria = (String) view.getComboBoxChavePrimaria().getSelectedItem();
                    String chaveestrangeira = (String) view.getComboboxChaveEstrangeira().getSelectedItem();
                    String nn = (String) view.getComboBoxNotNull().getSelectedItem();
                    String tipoatributo = (String) view.getComboBoxTipoAtributo().getSelectedItem();
                    String tamanho = view.getTextM().getText();

                    Atributo novo = new Atributo(view.getTextNomeAtributo().getText(), tipoatributo, nn, chaveprimaria, tamanho, chaveestrangeira);
                    entidade = pegarObejtoSelecionado();
                    int igual = achaIgual(pegarObejtoSelecionado());

                    if (view.getTextNomeAtributo().getText().equals("")) {
                        JOptionPane.showMessageDialog(view, "Campo Vazio.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String palavra = view.getTextNomeAtributo().getText();
                        if (palavra.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(view, "Campo só contém Espaço.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (igual == 1) {
                                JOptionPane.showMessageDialog(view, "Esse nome já Existe.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (chaveprimaria.equals("SIM") && chaveestrangeira.equals("NÃO")) {
                                    if (nn.equals("SIM")) {
                                        entidade.getAtributos().add(novo);
                                        view.getTextNomeAtributo().setText(null);
                                        view.getTextM().setText(null);
                                        gerenciarDataBase.serializar();
                                    } else {
                                        JOptionPane.showMessageDialog(view, "Uma Chave Primaria tem que ser Não Nula.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                                if (chaveprimaria.equals("SIM") && chaveestrangeira.equals("SIM")) {
                                    JOptionPane.showMessageDialog(view, "Um atributo não pode ser Chave Primaria e Chave estrangeira.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                                }

                                if (chaveestrangeira.equals("SIM") && chaveprimaria.equals("NÃO")) {
                                    if (nn.equals("SIM")) {
                                        entidade.getAtributos().add(novo);
                                        view.getTextNomeAtributo().setText(null);
                                        view.getTextM().setText(null);
                                        gerenciarDataBase.serializar();
                                    } else {
                                        JOptionPane.showMessageDialog(view, "Uma chave Estrangeira tem que ser Não Nula.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                                    }
                                }

                                if (chaveestrangeira.equals("NÃO") && chaveprimaria.equals("NÃO")) {
                                    entidade.getAtributos().add(novo);
                                    view.getTextNomeAtributo().setText(null);
                                    view.getTextM().setText(null);
                                    gerenciarDataBase.serializar();
                                }

                            }

                        }

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Nenhuma Entidade selecionada.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
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

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                selecionarDataBasePresenter.view.setEnabled(true);
            }
        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selecionarDataBasePresenter.view.setEnabled(true);
                view.dispose();
            }
        });

    }

    public int achaIgual(Entidade ent) {
        for (Atributo a : ent.getAtributos()) {
            if (a.getNomeAtributo().toUpperCase().trim().equals(view.getTextNomeAtributo().getText().toUpperCase().trim())) {
                return 1;

            }
        }
        return 0;

    }

    public int verificaChavePrimaria(Entidade ent) {
        int retorno = 0;
        for (Atributo a : ent.getAtributos()) {
            if (a.getChavePrimaria().equals("SIM") || ent.getAssociativa().equals("SIM")) {
                retorno = 1;
                return retorno;

            }
        }

        return retorno;
    }

    public void preencheJlist() {
        DefaultListModel modelo = new DefaultListModel();
        Entidade ent;
        Iterator<Entidade> it = this.database.getEntidade().iterator();
        while (it.hasNext()) {
            ent = it.next();
            modelo.addElement(ent.getNomeEntidade());
        }
        view.getjListentidades().setModel(modelo);
    }

    public Entidade pegarObejtoSelecionado() {
        Entidade ent;
        Iterator<Entidade> it = this.database.getEntidade().iterator();
        while (it.hasNext()) {
            ent = it.next();
            if (view.getjListentidades().getSelectedValue().equals(ent.getNomeEntidade())) {
                return ent;
            }
        }
        return null;
    }

}
