/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Compra;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.LineaCompra;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author silmont
 */
public class VistaRegistrarRecepcionCompra extends javax.swing.JFrame {

    private final CtrlVistaRegistrarRecepcionCompra controlador;
    /**
     * Creates new form VistaRegistrarRecepcionCompra
     */
    public VistaRegistrarRecepcionCompra() {
        initComponents();
        errorLabel.setVisible(false);
        controlador = new CtrlVistaRegistrarRecepcionCompra(this);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BotonRegistrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        botonOK = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        nombreBodega = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("REGISTRAR RECEPCIÓN COMPRA");

        jLabel2.setText("Id de Compra");

        BotonRegistrar.setText("Registrar");
        BotonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarActionPerformed(evt);
            }
        });

        jLabel3.setText("Datos de la Compra");

        botonOK.setText("OK");
        botonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOKActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jLabel4.setText("id Linea ----- Referencia  ----- Unidades");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(BotonRegistrar))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonOK))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nombreBodega)))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(BotonRegistrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorLabel)
                        .addGap(18, 18, 18)
                        .addComponent(nombreBodega)
                        .addGap(120, 120, 120)
                        .addComponent(botonOK)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarActionPerformed
        // TODO add your handling code here:

        controlador.procesaEventoIntroduceId();
        
    }//GEN-LAST:event_BotonRegistrarActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
               JList target = (JList)evt.getSource();
               int index = target.locationToIndex(evt.getPoint());
               if (index >= 0) {
                  Object item = target.getModel().getElementAt(index);
                  //JOptionPane.showMessageDialog(null, item.toString());
                  String parse = (String)item;
                  String[] datos = parse.split(" ----- ");
                   try {
                       controlador.procesaEventoSeleccionaLinea(datos[0]);
                   } catch (CompraNotFoundException ex) {
                       Logger.getLogger(VistaRegistrarRecepcionCompra.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            }
    }//GEN-LAST:event_jList1MouseClicked

    private void botonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOKActionPerformed
        // TODO add your handling code here:
        controlador.procesaEventoFinalizarRegistroLineas();
    }//GEN-LAST:event_botonOKActionPerformed

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
            java.util.logging.Logger.getLogger(VistaRegistrarRecepcionCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrarRecepcionCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrarRecepcionCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaRegistrarRecepcionCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaRegistrarRecepcionCompra().setVisible(true);
            }
        });
    }
    
    public String getId() {
        return jTextField1.getText();
    }
    
    public void mostrarMensajeError(String mensaje) {
        errorLabel.setText(mensaje);
        errorLabel.setVisible(true);
        errorLabel.setText(mensaje);
    }
    
    public void esconderMensajeError() {
        errorLabel.setVisible(false);
    }
    

    
    public void mostrarDatosCompra(Compra compra){
        nombreBodega.setVisible(true);
        nombreBodega.setText(compra.getNombreBodega());
        Iterator it = compra.getLineasCompra();
        //DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Vector data = new Vector();
        // TODO meter un if con linea no recibida
        while(it.hasNext()){
            LineaCompra c = (LineaCompra)it.next();
            String referencia = Integer.toString(c.getCodigoReferencia());
            String unidades = Integer.toString(c.getUnidades());
            String id = Integer.toString(c.getId());
            //model.addRow(new Object[]{referencia, unidades, false});
            String tabla = id + " ----- " + referencia + " ----- " + unidades;
            data.add(tabla);
            
        }
        
        jList1.setListData(data);
        
        
        

    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonRegistrar;
    private javax.swing.JButton botonOK;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel nombreBodega;
    // End of variables declaration//GEN-END:variables



    void mostrarLineasNoCompletadas(ArrayList<LineaCompra> l) {
        String stringMostrar = "";
        for (LineaCompra linea : l){
            stringMostrar = stringMostrar + Integer.toString(linea.getCodigoReferencia())+" --- " + Integer.toString(linea.getUnidades()) + "\n";
        }
        JOptionPane.showMessageDialog(null, stringMostrar);
    }
}
