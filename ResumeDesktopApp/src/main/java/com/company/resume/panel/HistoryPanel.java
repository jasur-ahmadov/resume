package com.company.resume.panel;

import com.company.dao.inter.*;
import com.company.main.Context;
import com.company.entity.*;
import com.company.resume.config.Config;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class HistoryPanel extends javax.swing.JPanel {

    private EmploymentHistoryDaoInter empHistoryDao = Context.instanceEmploymentHistoryDao();
    private UserEmpHistoryDaoInter userEmpHistoryDao = Context.instanceUserEmploymentHistoryDao();
    private List<EmploymentHistory> userEmpHistList = new ArrayList<>();

    public HistoryPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmpHistory = new javax.swing.JTable();
        txtHeader = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        txtBeginDate = new javax.swing.JTextField();
        txtEndDate = new javax.swing.JTextField();

        btnAdd.setText("add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setText("save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblEmpHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblEmpHistory);

        txtHeader.setToolTipText("txtHeader");

        txtDescription.setToolTipText("txtDescription");

        txtBeginDate.setToolTipText("txtBeginDate");

        txtEndDate.setToolTipText("txtEndDate");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBeginDate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBeginDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnAdd))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtHeader.getText().trim().isEmpty()) {
            EmploymentHistory eh = new EmploymentHistory(0, Config.loggedInUser, txtHeader.getText(), null, null, txtDescription.getText());
            try {
                long l = Config.sdf.parse(txtBeginDate.getText()).getTime();
                Date bd = new Date(l);
                eh.setBeginDate(bd);
            } catch (ParseException ex) {
                System.out.print("Houston, we have a problem");
            }
            try {
                long l = Config.sdf.parse(txtEndDate.getText()).getTime();
                Date bd = new Date(l);
                eh.setEndDate(bd);
            } catch (ParseException ex) {
                System.out.print("Houston, we have a problem");
            }
            empHistoryDao.insertEmpHistory(eh);
        }
        fillTable();
    }//GEN-LAST:event_btnAddActionPerformed

    public void fillUserComponent() {
        fillTable();
    }

    public void fillTable() {
        DefaultTableModel tableModel = new DefaultTableModel();
        Vector vectorHeaders = new Vector();
        vectorHeaders.add("id");
        vectorHeaders.add("Header");
        vectorHeaders.add("Job Description");
        vectorHeaders.add("Begin Date");
        vectorHeaders.add("End Date");
        Vector vectorRows = new Vector();
        userEmpHistList = userEmpHistoryDao.getAllEmpHistoryByUserId(Config.loggedInUser.getId());
        for (EmploymentHistory eh : userEmpHistList) {
            Vector row = new Vector();
            row.add(eh.getId());
            row.add(eh.getHeader());
            row.add(eh.getJobDescription());
            try {
                Date dt = eh.getBeginDate();
                String sdt = Config.sdf.format(dt);
                row.add(sdt);
            } catch (Exception ex) {
                row.add(null);
            }
            try {
                Date dt = eh.getEndDate();
                String sdt = Config.sdf.format(dt);
                row.add(sdt);

            } catch (Exception ex) {
                row.add(null);

            }
            vectorRows.add(row);
        }
        tableModel.setDataVector(vectorRows, vectorHeaders);
        tblEmpHistory.setModel(tableModel);
    }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int column = 0;
        int index = tblEmpHistory.getSelectedRow();
        if (index > -1) {
            EmploymentHistory eh = userEmpHistList.get(index);
            if (txtHeader.getText().trim().isEmpty()) {
                eh = new EmploymentHistory(eh.getId(), Config.loggedInUser, txtHeader.getText(), null, null, txtDescription.getText());
                try {
                    long l = Config.sdf.parse(txtBeginDate.getText()).getTime();
                    Date bd = new Date(l);
                    eh.setBeginDate(bd);
                } catch (ParseException ex) {
                    System.out.print("Ops, we have a problem");
                }
                try {
                    long l = Config.sdf.parse(txtEndDate.getText()).getTime();
                    Date bd = new Date(l);
                    eh.setEndDate(bd);
                } catch (ParseException ex) {
                    System.out.print("Ops, we have a problem");
                }
                empHistoryDao.updateEmpHistory(eh);
            }
            fillTable();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int index = tblEmpHistory.getSelectedRow();
        System.out.println("Count : " + index);
        if (index > -1) {
            EmploymentHistory eh = userEmpHistList.get(index);
            empHistoryDao.removeEmpHistory(eh.getId());
        }
        fillTable();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblEmpHistory;
    private javax.swing.JTextField txtBeginDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JTextField txtHeader;
    // End of variables declaration//GEN-END:variables

    public void fillUser(User usr) {
    }
}