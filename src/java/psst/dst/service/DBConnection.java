/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psst.dst.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import psst.dst.entity.PsstCashier;
import psst.dst.entity.PsstCustomer;

/**
 *
 * @author root
 */
public class DBConnection {

    private static final String DBCLASS = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/270518coffe_dst?zeroDateTimeBehavior=convertToNull";
    private static final String DBUSER = "root";
    private static final String DBPWD = "rootadmin";

    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(DBCLASS);
            con = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            return con;
        }
    }

    public static int logCashier(Integer cash_id, String pwd) throws Exception {
        int isUserAvailable = 2;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT `cash_id`, `name`, `phone`, `email`, `doc`, `img`, `pwd`, `created_at` FROM `psst_cashier` "
                    + "WHERE `cash_id` = '" + cash_id + "' AND `pwd` = '" + pwd + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstCashier cashier = new PsstCashier();
                cashier.setCashId(rs.getInt(1));
                cashier.setName(rs.getString(2));
                cashier.setPhone(rs.getString(3));
                cashier.setEmail(rs.getString(4));
                cashier.setDoc(rs.getString(5));
                cashier.setImg(rs.getString(6));
                cashier.setPwd(rs.getString(7));
                cashier.setCreatedAt(rs.getString(8));

                isUserAvailable = 1;
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }

    public static boolean regCashier(int cash_id, String name, String phone, String email, String doc,
            String img, String pwd)
            throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT INTO `psst_cashier`(`cash_id`, `name`, `phone`, `email`, `doc`, `img`, `pwd`) "
                    + "VALUES ('" + cash_id + "','" + name + "','" + phone + "','" + email + "','" + doc + "','" + img + "','" + pwd + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }

    public static int logCust(String email, String pwd) throws Exception {
        int isUserAvailable = 2;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT `cust_id`, `name`, `phone`, `email`, `pwd`, `gcm_token`, `img`, "
                    + "`created_at` FROM `psst_customer` WHERE `email` = '" + email + "' AND `pwd` = '" + pwd + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PsstCustomer cust = new PsstCustomer();
                cust.setCustId(rs.getInt(1));
                cust.setName(rs.getString(2));
                cust.setPhone(rs.getString(3));
                cust.setEmail(rs.getString(4));
                cust.setPwd(rs.getString(5));
                cust.setGcmToken(rs.getString(6));
                cust.setImg(rs.getString(7));
                cust.setCreatedAt(rs.getString(8));

                isUserAvailable = 1;
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }

    public static boolean regCust(String name, String phone, String email, String pwd,
            String gcmToken, String img)
            throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT INTO `psst_customer`(`name`, `phone`, `email`, `pwd`, `gcm_token`, `img`) "
                    + "VALUES ('" + name + "','" + phone + "','" + email + "','" + pwd + "','" + gcmToken + "','" + img + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }

}
