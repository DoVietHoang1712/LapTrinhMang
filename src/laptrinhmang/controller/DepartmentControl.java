/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhmang.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import laptrinhmang.controller.dao.DepartmentDAO;
import laptrinhmang.controller.utils.MySQLConnUtils;
import laptrinhmang.model.Department;
import laptrinhmang.view.FormDepartment;

/**
 *
 * @author hoang
 */
public class DepartmentControl {
    FormDepartment view;
    DepartmentDAO dao;
    
    public DepartmentControl(FormDepartment view) {
        try {
            dao = new DepartmentDAO(MySQLConnUtils.getMySQLConnection());
            this.view = view;
            view.addDepartmentListener(new DepartmentListener());

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            this.dao.closeConnection();
            System.exit(0);
        }
    }
    
    class DepartmentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                String command = e.getActionCommand();
                if (command.equalsIgnoreCase("Them")) {
                    Department emp = view.input();
                    dao.insert(emp);
                } else if (command.equalsIgnoreCase("Sua")) {
                    Department emp = view.input();
                    dao.update(emp);
                } else if (command.equalsIgnoreCase("Lay")) {
                    Department[] emps = dao.selectAll();
                    view.fillTable(emps);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private Department[] displayAll() {
        return this.dao.selectAll();
    }

    private Department[] displayByName(String name) {
        return this.dao.selectByName(name);
    }

    public void exit() {
        this.dao.closeConnection();
    }
}
