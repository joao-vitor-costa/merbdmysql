/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

/**
 *
 * @author JoãoVitor
 */
public class ViewCriarRelacionamentoEntidade extends javax.swing.JFrame {

    /**
     * Creates new form ViewCriarRelacionamentoEntidade
     */
    public ViewCriarRelacionamentoEntidade() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        ComboBoxentidade01 = new javax.swing.JComboBox();
        labelentidade01 = new javax.swing.JLabel();
        ComboBoxEntidade02 = new javax.swing.JComboBox();
        labelentidade02 = new javax.swing.JLabel();
        Labelcardinalidade = new javax.swing.JLabel();
        btncriarrelacao = new javax.swing.JButton();
        ComboBoxCarnalidade = new javax.swing.JComboBox();
        ComboBoxChavePrimaria = new javax.swing.JComboBox();
        ComboboxChaveEstrangeira = new javax.swing.JComboBox();
        labelNomeChavePrimaria = new javax.swing.JLabel();
        labelChaveEstrageira = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListdatabases = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btncancelar = new javax.swing.JButton();
        btncarregar = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ComboBoxentidade01.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelentidade01.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelentidade01.setText("Entidade:");

        ComboBoxEntidade02.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxEntidade02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxEntidade02ActionPerformed(evt);
            }
        });

        labelentidade02.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelentidade02.setText("Entidade:");

        Labelcardinalidade.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Labelcardinalidade.setText("Cardinalidade");

        btncriarrelacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/Salvar.png"))); // NOI18N
        btncriarrelacao.setText("Salvar");

        ComboBoxCarnalidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ComboBoxChavePrimaria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxChavePrimaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxChavePrimariaActionPerformed(evt);
            }
        });

        ComboboxChaveEstrangeira.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboboxChaveEstrangeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboboxChaveEstrangeiraActionPerformed(evt);
            }
        });

        labelNomeChavePrimaria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelNomeChavePrimaria.setText("Nome da Chave Primaria:");

        labelChaveEstrageira.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelChaveEstrageira.setText("Nome da Chave Estrageira:");

        jListdatabases.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListdatabases);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Selecione um DataBase Cadastrado:");

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/Cancelar.gif"))); // NOI18N
        btncancelar.setText("Cancelar");

        btncarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/carregar.png"))); // NOI18N
        btncarregar.setText("Carregar DataBase");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelNomeChavePrimaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelentidade01, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboBoxentidade01, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboBoxChavePrimaria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ComboBoxCarnalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Labelcardinalidade))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelentidade02)
                                            .addComponent(labelChaveEstrageira, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ComboboxChaveEstrangeira, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(ComboBoxEntidade02, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(btncriarrelacao)
                        .addGap(18, 18, 18)
                        .addComponent(btncancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btncarregar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelentidade01)
                                    .addComponent(labelentidade02))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboBoxentidade01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(ComboBoxEntidade02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(Labelcardinalidade, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomeChavePrimaria)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelChaveEstrageira)
                                .addComponent(ComboBoxCarnalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ComboBoxChavePrimaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btncriarrelacao)
                                    .addComponent(btncancelar)))
                            .addComponent(ComboboxChaveEstrangeira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btncarregar)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxChavePrimariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxChavePrimariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxChavePrimariaActionPerformed

    private void ComboboxChaveEstrangeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboboxChaveEstrangeiraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboboxChaveEstrangeiraActionPerformed

    private void ComboBoxEntidade02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxEntidade02ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxEntidade02ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewCriarRelacionamentoEntidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCriarRelacionamentoEntidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCriarRelacionamentoEntidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCriarRelacionamentoEntidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCriarRelacionamentoEntidade().setVisible(true);
            }
        });
    }

    public JComboBox getComboBoxEntidade02() {
        return ComboBoxEntidade02;
    }

    public void setComboBoxEntidade02(JComboBox ComboBoxEntidade02) {
        this.ComboBoxEntidade02 = ComboBoxEntidade02;
    }

    public JComboBox getComboBoxentidade01() {
        return ComboBoxentidade01;
    }

    public void setComboBoxentidade01(JComboBox ComboBoxentidade01) {
        this.ComboBoxentidade01 = ComboBoxentidade01;
    }

    public JLabel getLabelcardinalidade() {
        return Labelcardinalidade;
    }

    public void setLabelcardinalidade(JLabel Labelcardinalidade) {
        this.Labelcardinalidade = Labelcardinalidade;
    }

    
    public JButton getBtncriarrelacao() {
        return btncriarrelacao;
    }

    public void setBtncriarrelacao(JButton btncriarrelacao) {
        this.btncriarrelacao = btncriarrelacao;
    }


    public JLabel getLabelentidade01() {
        return labelentidade01;
    }

    public void setLabelentidade01(JLabel labelentidade01) {
        this.labelentidade01 = labelentidade01;
    }

    public JLabel getLabelentidade02() {
        return labelentidade02;
    }

    public void setLabelentidade02(JLabel labelentidade02) {
        this.labelentidade02 = labelentidade02;
    }

    public JComboBox getComboBoxCarnalidade() {
        return ComboBoxCarnalidade;
    }

    public void setComboBoxCarnalidade(JComboBox ComboBoxCarnalidade) {
        this.ComboBoxCarnalidade = ComboBoxCarnalidade;
    }

    public JComboBox getComboBoxChavePrimaria() {
        return ComboBoxChavePrimaria;
    }

    public void setComboBoxChavePrimaria(JComboBox ComboBoxChavePrimaria) {
        this.ComboBoxChavePrimaria = ComboBoxChavePrimaria;
    }

    public JComboBox getComboboxChaveEstrangeira() {
        return ComboboxChaveEstrangeira;
    }

    public void setComboboxChaveEstrangeira(JComboBox ComboboxChaveEstrangeira) {
        this.ComboboxChaveEstrangeira = ComboboxChaveEstrangeira;
    }

    public JLabel getLabelChaveEstrageira() {
        return labelChaveEstrageira;
    }

    public void setLabelChaveEstrageira(JLabel labelChaveEstrageira) {
        this.labelChaveEstrageira = labelChaveEstrageira;
    }

    public JLabel getLabelNomeChavePrimaria() {
        return labelNomeChavePrimaria;
    }

    public void setLabelNomeChavePrimaria(JLabel labelNomeChavePrimaria) {
        this.labelNomeChavePrimaria = labelNomeChavePrimaria;
    }

    public JButton getBtncancelar() {
        return btncancelar;
    }

    public void setBtncancelar(JButton btncancelar) {
        this.btncancelar = btncancelar;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JList getjListdatabases() {
        return jListdatabases;
    }

    public void setjListdatabases(JList jListdatabases) {
        this.jListdatabases = jListdatabases;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JToggleButton getjToggleButton1() {
        return jToggleButton1;
    }

    public void setjToggleButton1(JToggleButton jToggleButton1) {
        this.jToggleButton1 = jToggleButton1;
    }

    public JButton getBtncarregar() {
        return btncarregar;
    }

    public void setBtncarregar(JButton btncarregar) {
        this.btncarregar = btncarregar;
    }
    
    

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxCarnalidade;
    private javax.swing.JComboBox ComboBoxChavePrimaria;
    private javax.swing.JComboBox ComboBoxEntidade02;
    private javax.swing.JComboBox ComboBoxentidade01;
    private javax.swing.JComboBox ComboboxChaveEstrangeira;
    private javax.swing.JLabel Labelcardinalidade;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btncarregar;
    private javax.swing.JButton btncriarrelacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jListdatabases;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel labelChaveEstrageira;
    private javax.swing.JLabel labelNomeChavePrimaria;
    private javax.swing.JLabel labelentidade01;
    private javax.swing.JLabel labelentidade02;
    // End of variables declaration//GEN-END:variables
}