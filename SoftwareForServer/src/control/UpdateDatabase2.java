/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.Account;
import model.Storage;
import model.Transaction;

/**
 *
 * @author Xuan Truong PC
 */
public class UpdateDatabase2 {

    public static boolean updateInfoAccount(String address, String email, String phone, String stk) {
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(address);
            String sql = "update account set address=\'" + address + "\',email=\'" + email + "\',phone=\'" + phone + "\' where Account_No=\'" + stk + "\'";
            //System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static boolean CloseAccount(String stk) {
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            String sql = "update account set sta=0 where Account_No=\'" + stk + "\'";
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static boolean OpenAccount(String stk) {
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            String sql = "update account set sta=1 where Account_No=\'" + stk + "\'";
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static boolean insertAccount(String stk, String add, double bal, String birth, String email, String name, int gen, String ide, String phone, int sta) {
        String sql = "insert into account(Account_No,address,balance,birth,date_create,email,fullname,gender,iden_no,phone,sta,MarkLock) values (\'" + stk
                + "\',\'" + add + "\'," + bal + ",\'" + birth + "\',now(),\'" + email + "\',\'" + name + "\'," + gen + ",\'" + ide + "\',\'" + phone + "\'," + sta + ",0)";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static boolean insertCard(String card_no, String stk, int type) {
        String sql = "insert into card(card_no,account_no,pass,type,sta,date_create) values (\'" + card_no + "\',\'" + stk + "\',\'123456\'," + type + ",1,now())";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            //System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static boolean CloseCard(String card_no) {
        String sql = "update card set sta=0 where card_no=\'" + card_no + "\'";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static boolean OpenCard(String card_no) {
        String sql = "update card set sta=1 where card_no=\'" + card_no + "\' and sta=0";;
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static boolean insertBill(String bill_no, double amount, String card_no, String ex_at, String re, int sta, int type) {
        String sql = "insert into transaction(amount,bill_no,card_no,date_create,exchage_at,receiver,sta,type) values (" + amount + ",\'" + bill_no + "\',\'" + card_no + ""
                + "\',now(),\'" + ex_at + "\',\'" + re + "\'," + sta + "," + type + ")";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }

    public static void updateBalance(String card_no, double balance) {
        String sql = "update account set balance=" + balance + " where account_no=(select account_no from card where card_no=\'" + card_no + "\')";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            int x = state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
    }

    public static void updateBalance1(String stk, double balance) {
        String sql = "update account set balance=" + balance + " where Account_No=\'" + stk + "\'";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect2();
            state = con.createStatement();
            int x = state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
    }
}
