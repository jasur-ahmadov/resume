package com.company.resume.panel;

import com.company.entity.User;
import com.company.resume.config.Config;

public class ProfilePanel extends javax.swing.JPanel {

    public ProfilePanel() {
        initComponents();
    }

    public void fillUserComponents() {
        txtAreaProfile.setText(Config.loggedInUser.getProfileDesc());
    }

    public void fillUser(User user) {
        user.setProfileDesc(txtAreaProfile.getText());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProfile = new javax.swing.JScrollPane();
        txtAreaProfile = new javax.swing.JTextArea();

        txtAreaProfile.setColumns(20);
        txtAreaProfile.setRows(5);
        panelProfile.setViewportView(txtAreaProfile);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane panelProfile;
    private javax.swing.JTextArea txtAreaProfile;
    // End of variables declaration//GEN-END:variables
}