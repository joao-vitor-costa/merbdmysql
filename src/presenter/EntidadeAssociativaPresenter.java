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
import model.Atributo;
import model.DataBase;
import model.Entidade;
import model.GerenciarDataBase;
import model.RelacionamentoEntidade;
import view.ViewEntidadeAssociativa;

/**
 *
 * @author JoãoVitor
 */
public class EntidadeAssociativaPresenter {

    ViewEntidadeAssociativa view;
    CriarRelacionamentoEntidadePresenter criarRelacionamentoEntidadePresenter;
    DataBase database;
    Entidade pai1;
    Entidade pai2;
    String pk;
    String fk;
    GerenciarDataBase gerenciarDataBase;

    public EntidadeAssociativaPresenter(final CriarRelacionamentoEntidadePresenter criarRelacionamentoEntidadePresenter, final DataBase database, final Entidade pai1, final Entidade pai2, final String pk, final String fk, final GerenciarDataBase gerenciarDataBase) {
        this.criarRelacionamentoEntidadePresenter = criarRelacionamentoEntidadePresenter;
        this.database = database;
        this.pai1 = pai1;
        this.pai2 = pai2;
        this.fk = fk;
        this.pk = pk;
        this.gerenciarDataBase = gerenciarDataBase;
        view = new ViewEntidadeAssociativa();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.getBtnCriarEntidade().setEnabled(true);
        view.setTitle("Entidade Assosiativa");

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                criarRelacionamentoEntidadePresenter.view.setEnabled(true);
            }
        });

        view.getBtncancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                criarRelacionamentoEntidadePresenter.view.setEnabled(true);
                view.dispose();
            }
        });

        view.getBtnCriarEntidade().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Entidade novo = new Entidade(view.getTextnomeentidade().getText(), "SIM");
                database.getEntidade().add(novo);

                RelacionamentoEntidade filhoassociativa = new RelacionamentoEntidade(view.getTextnomeentidade().getText(), pk, pk, pai1.getNomeEntidade());
                RelacionamentoEntidade filhoassociativa2 = new RelacionamentoEntidade(view.getTextnomeentidade().getText(), fk, fk, pai2.getNomeEntidade());
                pai1.getRelacionamentoEntidades().add(filhoassociativa);
                pai2.getRelacionamentoEntidades().add(filhoassociativa2);

                Atributo at1 = pegaAtributo(pai1, pk);
                Atributo at2 = pegaAtributo(pai2, fk);

                novo.getAtributos().add(at1);
                novo.getAtributos().add(at2);
                view.getTextnomeentidade().setText(null);
                gerenciarDataBase.serializar();
                criarRelacionamentoEntidadePresenter.view.setEnabled(true);
                view.dispose();
            }
        });

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
