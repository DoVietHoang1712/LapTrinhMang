/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhmang.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import laptrinhmang.controller.dao.EmployeeDAO;
import laptrinhmang.controller.utils.MySQLConnUtils;
import laptrinhmang.model.Employee;
import laptrinhmang.view.FormEmployee;

/**
 *
 * @author hoang
 */
public class EmployeeControl {

    FormEmployee view;
    EmployeeDAO dao;

    public EmployeeControl(FormEmployee view) {
        try {
            dao = new EmployeeDAO(MySQLConnUtils.getMySQLConnection());
            this.view = view;
            view.addEmployeeListener(new EmployeeListener());

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            this.dao.closeConnection();
            System.exit(0);
        }
    }

    class EmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                String command = e.getActionCommand();
                if (command.equalsIgnoreCase("Them")) {
                    Employee emp = view.input();
                    dao.insert(emp);
                } else if (command.equalsIgnoreCase("Sua")) {
                    Employee emp = view.input();
                    dao.update(emp);
                } else if (command.equalsIgnoreCase("Lay")) {
                    Employee[] emps = dao.selectAll();
                    view.fillTable(emps);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private Employee[] displayAll() {
        return this.dao.selectAll();
    }

    private Employee[] displayByName(String name) {
        return this.dao.selectByName(name);
    }

    public void exit() {
        this.dao.closeConnection();
    }
}
