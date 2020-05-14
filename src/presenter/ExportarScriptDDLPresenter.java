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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Atributo;
import model.DataBase;
import model.Entidade;
import model.GerenciarDataBase;
import model.RelacionamentoEntidade;
import view.ViewExportarScriptDDL;

/**
 *
 * @author JoãoVitor
 */
public final class ExportarScriptDDLPresenter {

    ViewExportarScriptDDL view;
    PrincipalPresenter principalPresenter;
    DataBase dataBase;
    GerenciarDataBase gerenciarDataBase;

    public ExportarScriptDDLPresenter(final PrincipalPresenter principalPresenter, final GerenciarDataBase gerenciarDataBase) {
        view = new ViewExportarScriptDDL();
        this.principalPresenter = principalPresenter;
        this.gerenciarDataBase = gerenciarDataBase;
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setTitle("Script DDL");
        preencheJlist();

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

        view.getBtnexportar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exportaDDL(pegarObejtoSelecionado());
            }
        });
    }

    public void preencheJlist() {
        DefaultListModel modelo = new DefaultListModel();
        DataBase db;
        Iterator<DataBase> it = this.gerenciarDataBase.getDatabase().iterator();
        while (it.hasNext()) {
            db = it.next();
            modelo.addElement(db.getNomeDataBase());
        }

        view.getjListdatabase().setModel(modelo);
    }

    public DataBase pegarObejtoSelecionado() {
        DataBase db;
        Iterator<DataBase> it = this.gerenciarDataBase.getDatabase().iterator();
        while (it.hasNext()) {
            db = it.next();
            if (view.getjListdatabase().getSelectedValue().equals(db.getNomeDataBase())) {
                return db;
            }
        }
        return null;
    }

    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    public void exportaDDL(DataBase db) {
        String Naonull = "NOT NULL";
        File arquivo = new File("F:\\Documentos\\UFES\\7º periodo\\Banco de Dados\\Trabalho de Banco de Dados\\DBDTrabalho1_JoaoVitor_MarcoAurelio\\" + db.getNomeDataBase() + ".sql");

        try {

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            } else {
                arquivo.delete();
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("CREATE SCHEMA IF NOT EXISTS `" + db.getNomeDataBase() + "` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;");
            bw.newLine();
            bw.write("USE " + db.getNomeDataBase() + ";\n");
            bw.newLine();

            for (Entidade ent : db.getEntidade()) {

                Collections.sort(ent.getAtributos());

                bw.write("-- -----------------------------------------------------\n"
                        + "-- Table `" + db.getNomeDataBase() + "`.`" + ent.getNomeEntidade() + "`\n"
                        + "-- ---------------------- -----------------------------   --\n\n");

                bw.write("CREATE  TABLE IF NOT EXISTS `" + db.getNomeDataBase() + "`.`"
                        + ent.getNomeEntidade() + "` (\n");

                for (Atributo at : ent.getAtributos()) {
                    if (at.getNOTNULL().equals("NÃO")) {
                        Naonull = "NULL";
                    }

                    if (at.getNOTNULL().equals("SIM")) {
                        Naonull = "NOT NULL";
                    }
                    bw.write(" `" + at.getNomeAtributo() + "`");

                    if (at.getTipoAtributo().equals("VARCHAR")
                            || at.getTipoAtributo().equals("CHAR")) {

                        bw.write(" " + at.getTipoAtributo() + "" + "("
                                + at.getTamanhochar_string() + ") ");

                    } else {
                        bw.write(" " + at.getTipoAtributo() + " ");

                    }
                    bw.write(Naonull + " ,\n");

                }
                if (ent.getAssociativa().equals("NÃO")) {
                    int count = 0;
                    for (Atributo at : ent.getAtributos()) {
                        if (at.getChavePrimaria().equals("NÃO")) {
                            count++;
                        }
                    }

                    int j = 0;
                    for (Atributo at : ent.getAtributos()) {
                        int i = ent.getAtributos().size();
                        if (at.getChavePrimaria().equals("SIM")) {
                            bw.write("PRIMARY KEY (`" + at.getNomeAtributo() + "`)");
                            if (j < (i - count) - 1) {
                                bw.write(",\n");
                            }

                        }

                        j++;
                    }
                }

                if (ent.getAssociativa().equals("SIM")) {
                    int fk1 = 0;
                    int counti = 0;
                    for (Atributo at : ent.getAtributos()) {
                        if (at.getChavePrimaria().equals("NÃO")) {
                            counti++;
                        }
                    }

                    int j = 0;
                    for (Atributo at : ent.getAtributos()) {
                        fk1++;
                        int t = ent.getAtributos().size();
                        if (fk1 == 1) {
                            bw.write("PRIMARY KEY (`" + at.getNomeAtributo() + "`,");

                        }
                        
                        if(fk1 == 2){
                            bw.write("`"+at.getNomeAtributo()+"`)");
                            if (j < (t - counti) - 1) {
                                    bw.write(",\n");
                                }
                        }

                        if (fk1 > 2) {
                            if (at.getChavePrimaria().equals("SIM")) {
                                bw.write("PRIMARY KEY (`" + at.getNomeAtributo() + "`)");
                                if (j < (t - counti) - 1) {
                                    bw.write(",\n");
                                }
                            }

                            
                        }
                        j++;
                    }
                }

                System.out.println("\n \n");

                Naonull = "NOT NULL";
                bw.write(")");

                bw.newLine();
                bw.write("ENGINE = InnoDB;");

                bw.newLine();

            }
            bw.write("\n");
            for (Entidade ent : db.getEntidade()) {
                for (RelacionamentoEntidade re : ent.getRelacionamentoEntidades()) {
                    bw.write("\nALTER TABLE `" + re.getNomeEntidadeR() + "` ADD CONSTRAINT `fk_" + ent.getNomeEntidade()
                            + "` FOREIGN KEY ( `" + re.getChaveStrangeira() + "` ) REFERENCES `" + db.getNomeDataBase() + "`.`" + ent.getNomeEntidade() + "` (`"
                            + re.getChaveprimaria() + "`);\n");
                }
            }

            bw.newLine();
            bw.newLine();

            bw.close();
            fw.close();
            JOptionPane.showMessageDialog(view, "Exportado com Sucesso ", "Notificação", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Não foi exportado com Sucesso ", "Notificação", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
