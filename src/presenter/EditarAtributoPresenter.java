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
import view.ViewEditarAtributo;

/**
 *
 * @author JoãoVitor
 */
public final class EditarAtributoPresenter {

    ViewEditarAtributo view;
    EditarEntidadePresenter editarEntidadePresenter;
    Entidade entidade;
    DataBase database;
    GerenciarDataBase gerenciarDataBase;

    public EditarAtributoPresenter(final EditarEntidadePresenter editarEntidadePresenter, final Entidade entidade, final DataBase database, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewEditarAtributo();
        this.editarEntidadePresenter = editarEntidadePresenter;
        this.entidade = entidade;
        this.database = database;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Editar Atributo");
        preencheTabela();

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editarEntidadePresenter.view.setEnabled(true);
            }
        });

        view.getBtnalteraranome().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = pegaLinhaSelecionada();
                if (linha != -1) {
                    view.setEnabled(false);
                    chamarAlterarAtributoNomePresenter(pegaObjetoSelecionado(linha), database);

                } else {
                    JOptionPane.showMessageDialog(view, "Nenhum Atributo selecionado.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        view.getBtnexcluiratributo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = pegaLinhaSelecionada();
                if (linha != -1) {
                    Atributo at = pegaObjetoSelecionado(linha);
                    if (at.getChavePrimaria().equals("SIM")) {
                        Object opcoes[] = {"Sim", "Não"};
                        int result = JOptionPane.showOptionDialog(null, "Atributo Chave Primaria, relacionamentos ligados a chave seram perdidos. Deseja Excluir? ", "ALERTA",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);

                        if (result == 0) {
                            entidade.getAtributos().remove(at);
                            entidade.getRelacionamentoEntidades().clear();
                            gerenciarDataBase.serializarExcluir();
                            preencheTabela();
                        }
                    } else {
                        if (at.getChaveEstrangeira().equals("SIM")) {
                            Object opcoes[] = {"Sim", "Não"};
                            int result = JOptionPane.showOptionDialog(null, "Atributo Chave Estrangeira, relacionamentos ligados a chave seram perdidos. Deseja Excluir? ", "ALERTA",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);

                            if (result == 0) {
                                RelacionamentoEntidade re = pegaRelacionamento(at);
                                Entidade ent = pegaEntidadePai(at);
                                ent.getRelacionamentoEntidades().remove(re);
                                entidade.getAtributos().remove(at);
                                gerenciarDataBase.serializarExcluir();
                                preencheTabela();
                            }

                        } else {
                            entidade.getAtributos().remove(at);
                            gerenciarDataBase.serializarExcluir();
                            preencheTabela();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(view, "Nenhum Atributo selecionado.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);

                }
            }
        });

        view.getBtnaltertiipo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                int linha = pegaLinhaSelecionada();
                if (linha == -1) {
                    JOptionPane.showMessageDialog(view, "Nenhum Atributo selecionado.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    chamarAlterarTipoPresenter(pegaObjetoSelecionado(linha));
                }
            }
        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editarEntidadePresenter.view.setEnabled(true);
                view.dispose();

            }
        });
    }

    public void preencheTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        view.getTabela().setModel(modelo);

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

    public int pegaLinhaSelecionada() {
        int selecionada = view.getTabela().getSelectedRow();
        return selecionada;
    }

    public Atributo pegaObjetoSelecionado(int linha) {
        Atributo at;
        Iterator<Atributo> it = this.entidade.getAtributos().iterator();
        while (it.hasNext()) {
            at = it.next();
            if (view.getTabela().getValueAt(linha, 0).toString().equals(at.getNomeAtributo())) {
                return at;
            }

        }
        return null;
    }

    public RelacionamentoEntidade pegaRelacionamento(Atributo at) {
        for (Entidade ent : database.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveStrangeira().equals(at.getNomeAtributo()))) {
                    return re;
                }
            }
        }
        return null;
    }

    public Entidade pegaEntidadePai(Atributo at) {
        for (Entidade ent : database.getEntidade()) {
            for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                if ((re.getNomeEntidadeR().equals(entidade.getNomeEntidade())) && (re.getChaveStrangeira().equals(at.getNomeAtributo()))) {
                    return ent;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarAlterarAtributoNomePresenter(Atributo at, DataBase db) {
        new AlterarAtributoNomePresenter(entidade, at, this, db, gerenciarDataBase);

    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarAlterarTipoPresenter(Atributo at) {
        new AlterarTipoPresenter(this, at, gerenciarDataBase, entidade, database);
    }

}
