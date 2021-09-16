/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhmang.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import laptrinhmang.controller.dao.TimekeeperDAO;
import laptrinhmang.controller.utils.MySQLConnUtils;
import laptrinhmang.model.Timekeeper;
import laptrinhmang.view.FormTimekeeper;

/**
 *
 * @author hoang
 */
public class TimekeeperControl {
    FormTimekeeper view;
    TimekeeperDAO dao;
    
    public TimekeeperControl(FormTimekeeper view) {
        try {
            dao = new TimekeeperDAO(MySQLConnUtils.getMySQLConnection());
            this.view = view;
            view.addTimekeeperListener(new TimekeeperListener());
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            this.dao.closeConnection();
            System.exit(0);
        }
    }
    
    class TimekeeperListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                String command = e.getActionCommand();
                if (command.equalsIgnoreCase("Them")) {
                    Timekeeper emp = view.input();
                    dao.insert(emp);
                } else if (command.equalsIgnoreCase("Sua")) {
                    Timekeeper emp = view.input();
                    dao.update(emp);
                } else if (command.equalsIgnoreCase("Lay")) {
                    Timekeeper[] emps = dao.selectAll();
                    view.fillTable(emps);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private Timekeeper[] displayAll() {
        return this.dao.selectAll();
    }

    private Timekeeper[] displayByName(String name) {
        return this.dao.selectByName(name);
    }

    public void exit() {
        this.dao.closeConnection();
    }
}
