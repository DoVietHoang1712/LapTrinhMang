/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhmang.controller.dao;


import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import laptrinhmang.model.Employee;
import laptrinhmang.model.Timekeeper;

/**
 *
 * @author Quang
 */
public class TimekeeperDAO extends IDAO<Timekeeper>{
    
      public TimekeeperDAO(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    @Override
    public Timekeeper[] selectAll() {
   Vector<Timekeeper> ee = new Vector<Timekeeper>();
        Timekeeper[] result;
        try {
            String sql = "Select * from Timekeeper";

            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                Timekeeper e = new Timekeeper(
                        rs.getString(1),
                        rs.getDate(2),
                        BigInteger.valueOf(rs.getInt(3)),
                        rs.getString(4));
                ee.add(e);

                i++;
            }
            result = new Timekeeper[i];
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return ee.toArray(result);  }

    @Override
    public Timekeeper[] selectByName(String name) {
    Vector<Timekeeper> ee = new Vector<Timekeeper>();
        Timekeeper[] result;
        try {
            String sql = "Select * from Timekeeper where Timekeeper_Id='" + name + "'";

            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
               Timekeeper e = new Timekeeper(
                        rs.getString(1),
                        rs.getDate(2),
                        BigInteger.valueOf(rs.getInt(3)),
                        rs.getString(4));
                ee.add(e);

                i++;
            }
            result = new Timekeeper[i];
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return ee.toArray(result); }

    @Override
    public int insert(Timekeeper e) {
   String sql = "INSERT INTO TIMEKEEPER (TIMEKEEPER_ID,"
                + "DATE_TIME,"
                + "EMPID,"
                + "IN_OUT,"         
                + "VALUES (?,?,?,?)";
        try {
            this.preStatement = this.conn.prepareStatement(sql);
    
            this.preStatement.setString(1, e.getTimekeeper_Id());
 
            this.preStatement.setDate(2, new java.sql.Date(e.getDate_Time().getTime()));
            
            this.preStatement.setLong(3, e.getEmpId().longValue());
             this.preStatement.setString(4, e.getIn_Out());
        
            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;

        }
   }

    @Override
    public int update(Timekeeper e) {
   String sql = "UPDATE TIMEKEEPER set " 
                + "TIMEKEEPER_ID = ?,"
                + "DATE_TIME = ?,"
                + "EMP_ID = ?,"
                + "IN_OUT = ?";
        try {
            this.preStatement = this.conn.prepareStatement(sql);
    
            this.preStatement.setString(1, e.getTimekeeper_Id());
 
            this.preStatement.setDate(2, new java.sql.Date(e.getDate_Time().getTime()));
            
            this.preStatement.setLong(3, e.getEmpId().longValue());
             this.preStatement.setString(4, e.getIn_Out());
        
            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;

        } }

    @Override
    public void closeConnection() {
     try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } }

   
    
}
