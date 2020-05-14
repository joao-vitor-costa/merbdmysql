/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 *
 * @author JoãoVitor
 */
public class ViewPrincipal extends JFrame {

    /**
     * Creates new form ViewPrincipal
     */
    public ViewPrincipal() {
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

        jLabel1 = new javax.swing.JLabel();
        btnsair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnCadastrarNovo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btncadastrarentidades = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnCriarAtributo = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btncriarelacionamento = new javax.swing.JMenuItem();
        btnEditarDataBase = new javax.swing.JMenuItem();
        btnExportarModeloLogico = new javax.swing.JMenuItem();
        btnExportarScriptDDL = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        btnsair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/exit.png"))); // NOI18N
        btnsair.setText("Sair");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/DataBase.png"))); // NOI18N
        jMenu1.setText("Cadastar DataBase");

        btnCadastrarNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        btnCadastrarNovo.setText("Cadastrar Novo");
        jMenu1.add(btnCadastrarNovo);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/Entidade.png"))); // NOI18N
        jMenu2.setText("Cadastrar Entidade");

        btncadastrarentidades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        btncadastrarentidades.setText("Cadastrar Novo");
        jMenu2.add(btncadastrarentidades);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/Atributo.png"))); // NOI18N
        jMenu3.setText("Cadastrar Atributo");

        btnCriarAtributo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        btnCriarAtributo.setText("Cadastrar Novo");
        jMenu3.add(btnCriarAtributo);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone/Gerenciar.png"))); // NOI18N
        jMenu4.setText("Gerenciar DataBase");

        btncriarelacionamento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        btncriarelacionamento.setText("Criar Relacionamento ");
        btncriarelacionamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncriarelacionamentoActionPerformed(evt);
            }
        });
        jMenu4.add(btncriarelacionamento);

        btnEditarDataBase.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        btnEditarDataBase.setText("Editar DataBase");
        jMenu4.add(btnEditarDataBase);

        btnExportarModeloLogico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        btnExportarModeloLogico.setText("Exportar Modelo Logico");
        jMenu4.add(btnExportarModeloLogico);

        btnExportarScriptDDL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        btnExportarScriptDDL.setText("Exportar Script DDL");
        jMenu4.add(btnExportarScriptDDL);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(746, Short.MAX_VALUE)
                .addComponent(btnsair, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(452, Short.MAX_VALUE)
                .addComponent(btnsair)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncriarelacionamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncriarelacionamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncriarelacionamentoActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    public JMenuItem getBtnCadastrarNovo() {
        return btnCadastrarNovo;
    }

    public void setBtnCadastrarNovo(JMenuItem btnCadastrarNovo) {
        this.btnCadastrarNovo = btnCadastrarNovo;
    }

    public JMenuItem getBtnEditarDataBase() {
        return btnEditarDataBase;
    }

    public void setBtnEditarDataBase(JMenuItem btnEditarDataBase) {
        this.btnEditarDataBase = btnEditarDataBase;
    }

    public JMenuItem getBtnExportarModeloLogico() {
        return btnExportarModeloLogico;
    }

    public void setBtnExportarModeloLogico(JMenuItem btnExportarModeloLogico) {
        this.btnExportarModeloLogico = btnExportarModeloLogico;
    }

    public JMenuItem getBtnExportarScriptDDL() {
        return btnExportarScriptDDL;
    }

    public void setBtnExportarScriptDDL(JMenuItem btnExportarScriptDDL) {
        this.btnExportarScriptDDL = btnExportarScriptDDL;
    }

    public JMenuItem getBtncadastrarentidades() {
        return btncadastrarentidades;
    }

    public void setBtncadastrarentidades(JMenuItem btncadastrarentidades) {
        this.btncadastrarentidades = btncadastrarentidades;
    }

    public JMenuItem getBtncriarelacionamento() {
        return btncriarelacionamento;
    }

    public void setBtncriarelacionamento(JMenuItem btncriarelacionamento) {
        this.btncriarelacionamento = btncriarelacionamento;
    }

    public JMenuItem getBtnCriarAtributo() {
        return btnCriarAtributo;
    }

    public void setBtnCriarAtributo(JMenuItem btnCriarAtributo) {
        this.btnCriarAtributo = btnCriarAtributo;
    }

    public JButton getBtnsair() {
        return btnsair;
    }

    public void setBtnsair(JButton btnsair) {
        this.btnsair = btnsair;
    }

   
  

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnCadastrarNovo;
    private javax.swing.JMenuItem btnCriarAtributo;
    private javax.swing.JMenuItem btnEditarDataBase;
    private javax.swing.JMenuItem btnExportarModeloLogico;
    private javax.swing.JMenuItem btnExportarScriptDDL;
    private javax.swing.JMenuItem btncadastrarentidades;
    private javax.swing.JMenuItem btncriarelacionamento;
    private javax.swing.JButton btnsair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    
}