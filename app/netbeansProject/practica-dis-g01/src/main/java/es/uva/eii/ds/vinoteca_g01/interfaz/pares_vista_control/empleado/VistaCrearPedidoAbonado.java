/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;
import javax.swing.JOptionPane;

/**
 *
 * @author richard
 */
public class VistaCrearPedidoAbonado extends javax.swing.JFrame {

    private final CtrlVistaCrearPedidoAbonado controlador;

    /**
     * Creates new form VistaCrearPedidoAbonado
     */
    public VistaCrearPedidoAbonado() {
        initComponents();
        errorLabel.setVisible(false);
        controlador = new CtrlVistaCrearPedidoAbonado(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notificarPopup = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        pedidoPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        numReferenciaTextField = new javax.swing.JTextField();
        cantidadTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        finalizarPedidoButton = new javax.swing.JButton();
        noFinalizarButton = new javax.swing.JButton();
        introducirRefYCantButton = new javax.swing.JButton();
        datosAbonadoPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombreLabel = new javax.swing.JLabel();
        apellidosLabel = new javax.swing.JLabel();
        telefonoLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        abonadoIncorrectoButton = new javax.swing.JButton();
        abonadoCorrectoButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cancelarButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numAbonadoTextField = new javax.swing.JTextField();
        buscarButton = new javax.swing.JButton();
        buscarPorParametrosButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel8.setText("Pedido:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel6.setText("Datos Abonado:");

        errorLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        errorLabel.setForeground(java.awt.Color.red);
        errorLabel.setText("jLabel12");

        jLabel9.setText("Nº Referencia:");

        jLabel10.setText("Cantidad:");

        numReferenciaTextField.setEnabled(false);

        cantidadTextField.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setText("Finalizar pedido:");

        finalizarPedidoButton.setText("Si");
        finalizarPedidoButton.setEnabled(false);
        finalizarPedidoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarPedidoButtonActionPerformed(evt);
            }
        });

        noFinalizarButton.setText("No");
        noFinalizarButton.setEnabled(false);
        noFinalizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noFinalizarButtonActionPerformed(evt);
            }
        });

        introducirRefYCantButton.setText("Introducir");
        introducirRefYCantButton.setEnabled(false);
        introducirRefYCantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introducirRefYCantButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pedidoPanelLayout = new javax.swing.GroupLayout(pedidoPanel);
        pedidoPanel.setLayout(pedidoPanelLayout);
        pedidoPanelLayout.setHorizontalGroup(
            pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pedidoPanelLayout.createSequentialGroup()
                .addGroup(pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pedidoPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalizarPedidoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(noFinalizarButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pedidoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(numReferenciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pedidoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cantidadTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(174, 174, 174))
            .addGroup(pedidoPanelLayout.createSequentialGroup()
                .addGroup(pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(pedidoPanelLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(introducirRefYCantButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pedidoPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cantidadTextField, numReferenciaTextField});

        pedidoPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {finalizarPedidoButton, noFinalizarButton});

        pedidoPanelLayout.setVerticalGroup(
            pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidoPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(numReferenciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(cantidadTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(introducirRefYCantButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pedidoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pedidoPanelLayout.createSequentialGroup()
                        .addGroup(pedidoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finalizarPedidoButton)
                            .addComponent(noFinalizarButton))
                        .addGap(22, 22, 22))))
        );

        pedidoPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cantidadTextField, numReferenciaTextField});

        pedidoPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {finalizarPedidoButton, noFinalizarButton});

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellidos:");

        jLabel4.setText("Teléfono:");

        jLabel5.setText("Email:");

        abonadoIncorrectoButton.setText("No");
        abonadoIncorrectoButton.setEnabled(false);
        abonadoIncorrectoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonadoIncorrectoButtonActionPerformed(evt);
            }
        });

        abonadoCorrectoButton.setText("Si");
        abonadoCorrectoButton.setEnabled(false);
        abonadoCorrectoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonadoCorrectoButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("¿Es el abonado que buscaba?");

        javax.swing.GroupLayout datosAbonadoPanelLayout = new javax.swing.GroupLayout(datosAbonadoPanel);
        datosAbonadoPanel.setLayout(datosAbonadoPanelLayout);
        datosAbonadoPanelLayout.setHorizontalGroup(
            datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(abonadoCorrectoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(abonadoIncorrectoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(apellidosLabel))
                            .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(nombreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel)
                                    .addComponent(telefonoLabel))))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        datosAbonadoPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {apellidosLabel, emailLabel, nombreLabel, telefonoLabel});

        datosAbonadoPanelLayout.setVerticalGroup(
            datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosAbonadoPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreLabel))
                .addGap(18, 18, 18)
                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(apellidosLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(telefonoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(emailLabel))
                .addGap(41, 41, 41)
                .addComponent(jLabel7)
                .addGap(16, 16, 16)
                .addGroup(datosAbonadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abonadoIncorrectoButton)
                    .addComponent(abonadoCorrectoButton))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        datosAbonadoPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {apellidosLabel, emailLabel, nombreLabel, telefonoLabel});

        cancelarButton.setText("Cancelar");
        cancelarButton.setEnabled(false);
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datosAbonadoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(pedidoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(errorLabel)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelarButton))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(datosAbonadoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(pedidoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Nº Abonado:");

        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        buscarPorParametrosButton.setText("Buscar por parámetros");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(numAbonadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buscarButton)
                .addGap(98, 98, 98)
                .addComponent(buscarPorParametrosButton)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numAbonadoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(buscarButton)
                    .addComponent(buscarPorParametrosButton))
                .addGap(42, 42, 42))
        );

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel12.setText("CREAR PEDIDO ABONADO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(283, 283, 283))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel12)
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        controlador.procesaEventoIntroduceNumeroAbonado();
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void abonadoCorrectoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonadoCorrectoButtonActionPerformed
        controlador.procesaEventoConfirmarAbonado();
    }//GEN-LAST:event_abonadoCorrectoButtonActionPerformed

    private void introducirRefYCantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introducirRefYCantButtonActionPerformed
        controlador.procesaEventoIntroducirDatosPedido();
    }//GEN-LAST:event_introducirRefYCantButtonActionPerformed

    private void finalizarPedidoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarPedidoButtonActionPerformed
        controlador.procesaEventoFinalizarPedido();
    }//GEN-LAST:event_finalizarPedidoButtonActionPerformed

    private void abonadoIncorrectoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonadoIncorrectoButtonActionPerformed
        controlador.procesaEventoAbonadoIncorrecto();
    }//GEN-LAST:event_abonadoIncorrectoButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        controlador.procesaEventoCancelar();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void noFinalizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noFinalizarButtonActionPerformed
        controlador.procesaEventoNoFinalizar();
    }//GEN-LAST:event_noFinalizarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(VistaCrearPedidoAbonado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCrearPedidoAbonado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCrearPedidoAbonado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCrearPedidoAbonado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCrearPedidoAbonado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abonadoCorrectoButton;
    private javax.swing.JButton abonadoIncorrectoButton;
    private javax.swing.JLabel apellidosLabel;
    private javax.swing.JButton buscarButton;
    private javax.swing.JButton buscarPorParametrosButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField cantidadTextField;
    private javax.swing.JPanel datosAbonadoPanel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton finalizarPedidoButton;
    private javax.swing.JButton introducirRefYCantButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton noFinalizarButton;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JPopupMenu notificarPopup;
    private javax.swing.JTextField numAbonadoTextField;
    private javax.swing.JTextField numReferenciaTextField;
    private javax.swing.JPanel pedidoPanel;
    private javax.swing.JLabel telefonoLabel;
    // End of variables declaration//GEN-END:variables

    public String getNumeroAbonado() {
        return numAbonadoTextField.getText();
    }

    public void mostrarAbonado(Abonado abonado) {
        abonadoCorrectoButton.setEnabled(true);
        abonadoIncorrectoButton.setEnabled(true);
        nombreLabel.setText(abonado.getNombre());
        apellidosLabel.setText(abonado.getApellidos());
        telefonoLabel.setText(abonado.getTelefono());
        emailLabel.setText(abonado.getEmail());
    }
    
    public void deshabilitarCamposAbonado() {
        abonadoCorrectoButton.setEnabled(false);
        abonadoIncorrectoButton.setEnabled(false);
    }
    
    public void limpiarCamposAbonado() {
        nombreLabel.setText("");
        apellidosLabel.setText("");
        telefonoLabel.setText("");
        emailLabel.setText("");
    }

    public void habilitarCamposPedido() {
        numReferenciaTextField.setEnabled(true);
        cantidadTextField.setEnabled(true);
        introducirRefYCantButton.setEnabled(true);
    }

    public String getNumReferencia() {
        return numReferenciaTextField.getText();
    }

    public String getCantidad() {
        return cantidadTextField.getText();
    }

    public void mostrarError(String mensaje) {
        errorLabel.setVisible(true);
        errorLabel.setText(mensaje);
    }

    public void esconderError() {
        errorLabel.setVisible(false);
    }

    public void habilitarFinalizarPedido() {
        finalizarPedidoButton.setEnabled(true);
        noFinalizarButton.setEnabled(true);
    }
    
    public void deshabilitarFinalizarPedido() {
        finalizarPedidoButton.setEnabled(false);
        noFinalizarButton.setEnabled(false);
    }

    public void limpiarNumeroAbonadoTextField() {
        numAbonadoTextField.setText("");
    }

    public void habilitarCancelar() {
        cancelarButton.setEnabled(true);
    }

    public void deshabilitarCancelar() {
        cancelarButton.setEnabled(false);
    }
    
    public void mostrarVentanaNotificacion() {
        JOptionPane.showMessageDialog(this, "Se enviará una notificación al correo electrónico del abonado");
        limpiarNumeroAbonadoTextField();
        limpiarCamposAbonado();
        deshabilitarCamposAbonado();
        deshabilitarFinalizarPedido();
        esconderError();
        habilitarCamposDeBusqueda();
    }

    public void habilitarCamposDeBusqueda() {
        numAbonadoTextField.setEnabled(true);
        buscarButton.setEnabled(true);
        buscarPorParametrosButton.setEnabled(true);
    }
    
    public void deshabilitarCamposDeBusqueda() {
        numAbonadoTextField.setEnabled(false);
        buscarButton.setEnabled(false);
        buscarPorParametrosButton.setEnabled(false);
    }

    public void deshabilitarCamposPedido() {
        numReferenciaTextField.setEnabled(false);
        cantidadTextField.setEnabled(false);
        introducirRefYCantButton.setEnabled(false);
    }

    public void limpiarCamposPedido() {
        numReferenciaTextField.setText("");
        cantidadTextField.setText("");
    }
}
