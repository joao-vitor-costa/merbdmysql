/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GerenciarDataBase;
import view.ViewPrincipal;

/**
 *
 * @author JoãoVitor
 */
public class PrincipalPresenter {

    ViewPrincipal view;
    GerenciarDataBase gerenciaDataBase;

    public PrincipalPresenter(final GerenciarDataBase gerenciaDataBase) {
        view = new ViewPrincipal();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.gerenciaDataBase = gerenciaDataBase;

        view.getBtnCadastrarNovo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                chamarDataBasePresenter();
            }
        });

        view.getBtnEditarDataBase().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                chamarEditarDataBasePresenter();
            }
        });

        view.getBtnExportarScriptDDL().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                chamarExportarScriptDDL();
            }
        }
        );

        view.getBtnExportarModeloLogico().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                chamarExportarModeloLogico();
            }
        });
        
       view.getBtncadastrarentidades().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                chamarCadastrarEntidadePresenter();
            }
        });
        
       view.getBtnCriarAtributo().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                chamarSelecionaDataBasePresenter();
            }
        });
       
       view.getBtnsair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
       
       view.getBtncriarelacionamento().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.setEnabled(false);
                chamarCriarRelacionamentoPresenter();
            }
        });

    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")

    public void chamarDataBasePresenter() {
        new CadastrarDataBasePresenter(gerenciaDataBase, this);
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarEditarDataBasePresenter() {
        new EditarDataBasePresenter(this, gerenciaDataBase);
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarExportarScriptDDL() {
        new ExportarScriptDDLPresenter(this, gerenciaDataBase);
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarExportarModeloLogico() {
        new ExportarModeloLogicoPresenter(this, gerenciaDataBase);
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarCadastrarEntidadePresenter(){
        new CadastrarEntidadePresenter(this, gerenciaDataBase);
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarSelecionaDataBasePresenter(){
        new SelecionarDataBasePresenter(this,gerenciaDataBase);
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void chamarCriarRelacionamentoPresenter(){
        new CriarRelacionamentoEntidadePresenter(this, gerenciaDataBase);
    }

}
