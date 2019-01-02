package com.alazhar.fileOption;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        bt_open = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        bt_copy = new javax.swing.JButton();
        jProgressBar2 = new javax.swing.JProgressBar();
        bt_cut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_open.setText("Choose File");
        bt_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_openActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        bt_copy.setText("Copy");
        bt_copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_copyActionPerformed(evt);
            }
        });

        jProgressBar2.setOpaque(true);
        jProgressBar2.setStringPainted(true);

        bt_cut.setText("Cut");
        bt_cut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_cut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_copy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(bt_open, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bt_copy, bt_cut, bt_open});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_open, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_copy, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_copy, bt_cut, bt_open});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_openActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        absolutePath = f.getAbsolutePath();
        jTextField1.setText(absolutePath);
        fileName = f.getName();
    }//GEN-LAST:event_bt_openActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void bt_copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_copyActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(1);//DIRECTTORIES_ONLY
        chooser.showSaveDialog(null);

        File selectedFolder = chooser.getSelectedFile();
        Thread t1 = new Thread(() -> {
            try {
                FileManager manager = new FileManager(new UiCallback() {

                    @Override
                    public void onProcessStarted() {
                        bt_copy.setEnabled(false);
                        bt_cut.setEnabled(false);
                        jProgressBar2.setMaximum(100);
                    }

                    @Override
                    public void updateProgress(int value) {
                        jProgressBar2.setValue(value);
                    }

                    @Override
                    public void onProcessEnd() {
                        JOptionPane.showMessageDialog(null, "Sccuess");
                        bt_copy.setEnabled(true);
                        bt_cut.setEnabled(true);
                    }

                });

                manager.copyFile(absolutePath, selectedFolder.getAbsolutePath() + "\\" + fileName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println(ex.getMessage());
            }
        });
        t1.start();
    }//GEN-LAST:event_bt_copyActionPerformed

    private void bt_cutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cutActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(1);//DIRECTTORIES_ONLY
        chooser.showSaveDialog(null);
        File selectedFolder = chooser.getSelectedFile();

        Thread t1 = new Thread(() -> {
            try {
                FileManager manager = new FileManager(new UiCallback() {

                    @Override
                    public void onProcessStarted() {
                        bt_cut.setEnabled(false);
                        bt_copy.setEnabled(false);
                        jProgressBar2.setMaximum(100);
                    }

                    @Override
                    public void updateProgress(int value) {
                        jProgressBar2.setValue(value);
                    }

                    @Override
                    public void onProcessEnd() {
                        JOptionPane.showMessageDialog(null, "Sccuess");
                        bt_cut.setEnabled(true);
                        bt_copy.setEnabled(true);
                    }

                });

                manager.cutFile(absolutePath, selectedFolder.getAbsolutePath() + "\\" + fileName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println(ex.getMessage());
            }
        });
        t1.start();


    }//GEN-LAST:event_bt_cutActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_copy;
    private javax.swing.JButton bt_cut;
    private javax.swing.JButton bt_open;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    String fileName;
    String absolutePath;
}
