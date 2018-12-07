/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Xuan Truong PC
 */
import model.*;
import java.util.*;
import java.sql.*;

public class DataControl {

    public static Vector<Account> Search(String s, int k) {
        String sql = "";
        if (k == 0) {
            sql = "select * from Account where account_no like\'%" + s + "%\'";
        } else if (k == 1) {
            sql = "select * from Account where fullname like\'%" + s + "%\'";
        } else if (k == 2) {
            sql = "select * from Account where phone like\'%" + s + "%\'";
        } else if (k == 3) {
            sql = "select * from Account where iden_no like\'%" + s + "%\'";
        } else {
            sql = "select account.* from account,card where account.Account_No=card.account_no and card.card_no like \'%" + s + "%\'";
        }
        System.out.println(sql);
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        Vector<Account> accounts = new Vector<Account>();
        try {
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Account a = new Account();
                a.setAccount_no(rs.getString("Account_No"));
                a.setAddress(rs.getString("address"));
                a.setBalance(rs.getDouble("balance"));
                a.setBirth(rs.getString("birth"));
                a.setDateCreate(rs.getDate("date_create"));
                a.setEmail(rs.getString("email"));
                a.setFullName(rs.getString("fullname"));
                a.setGender(rs.getInt("gender"));
                a.setIden_no(rs.getString("iden_no"));
                a.setPhone(rs.getString("phone"));
                a.setSta(rs.getInt("sta"));
                accounts.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataAccess.CloseResultSet(rs);
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return accounts;
    }

    public static Account getAccount(String stk) {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        Account a =null;
        String sql = "Select * from Account where Account_No=\'" + stk + "\'";
        try {
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                a = new Account();
                a.setAccount_no(rs.getString("Account_No"));
                a.setAddress(rs.getString("address"));
                a.setBalance(rs.getDouble("balance"));
                a.setBirth(rs.getString("birth"));
                a.setDateCreate(rs.getDate("date_create"));
                a.setEmail(rs.getString("email"));
                a.setFullName(rs.getString("fullname"));
                a.setGender(rs.getInt("gender"));
                a.setIden_no(rs.getString("iden_no"));
                a.setPhone(rs.getString("phone"));
                a.setSta(rs.getInt("sta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataAccess.CloseResultSet(rs);
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return a;
    }

    
    //Lấy tất cả tài khoản và tạo danh sách phones, emails, account_nos,iden_nos
    public static Vector<Account> getAccounts() {
        String sql = "select * from Account";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        Storage.ResetAccount();
        Vector<Account> accounts = new Vector<Account>();
        try {
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Account a = new Account();
                a.setAccount_no(rs.getString("Account_No"));
                a.setAddress(rs.getString("address"));
                a.setBalance(rs.getDouble("balance"));
                a.setBirth(rs.getString("birth"));
                a.setDateCreate(rs.getDate("date_create"));
                a.setEmail(rs.getString("email"));
                a.setFullName(rs.getString("fullname"));
                a.setGender(rs.getInt("gender"));
                a.setIden_no(rs.getString("iden_no"));
                a.setPhone(rs.getString("phone"));
                a.setSta(rs.getInt("sta"));
                accounts.add(a);
                Storage.addAccountNo(rs.getString("Account_No"));
                if (rs.getString("email") != null) {
                    Storage.addEmail(rs.getString("email"));
                }
                Storage.addIden(rs.getString("iden_no"));
                Storage.addPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataAccess.CloseResultSet(rs);
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return accounts;
    }

    public static boolean updateInfoAccount(String address, String email, String phone, String stk) {
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(address);
            String sql = "update account set address=\'" + address + "\',email=\'" + email + "\',phone=\'" + phone + "\' where Account_No=\'" + stk + "\'";
            //System.out.println(sql);
            con = DataAccess.getConnect1();
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
            con = DataAccess.getConnect1();
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
            con = DataAccess.getConnect1();
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

    public static boolean insertAccount(String add, double bal, String birth, String email, String name, int gen, String ide, String phone) {
        String stk = new CreateCode().createAccountNo();
        String sql = "insert into account(Account_No,address,balance,birth,date_create,email,fullname,gender,iden_no,phone,sta,MarkLock) values (\'" + stk
                + "\',\'" + add + "\'," + bal + ",\'" + birth + "\',now(),\'" + email + "\',\'" + name + "\'," + gen + ",\'" + ide + "\',\'" + phone + "\',1,0)";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        Storage.addAccountNo(stk);
        Storage.addPhone(phone);
        Storage.addIden(ide);
        if (email != null | email.trim() != "") {
            Storage.addEmail(email);
        }
        return flag;
    }

    public static String insertCard(String stk, int type) {
        String card_no = new CreateCode().createCardNo();
        String sql = "insert into card(card_no,account_no,pass,type,sta,date_create) values (\'" + card_no + "\',\'" + stk + "\',\'123456\'," + type + ",1,now())";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            //System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            card_no="0";
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        Storage.addCardNo(card_no);
        return card_no;
    }

    public static boolean CloseCard(String card_no) {
        String sql = "update card set sta=0 where card_no=\'" + card_no + "\'";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
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
            con = DataAccess.getConnect1();
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

    public static void getCardNo() {
        Storage.ResetCard();
        String sql = "select card_no from card";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Storage.addCardNo(rs.getString("card_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
    }

    public static Vector<Transaction> getAllBill() {
        Vector<Transaction> bills = new Vector<Transaction>();
        Storage.ResetBill();
        String sql = "select t.*, a1.Account_No idFrom,a1.fullname nameFrom,a2.fullname nameTo from transaction t,account a1,account a2,card c where a1.Account_No=c.account_no and c.card_no=t.card_no and t.receiver=a2.Account_No";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setAccount_no(rs.getString("idFrom"));
                t.setAmount(rs.getDouble("amount"));
                t.setBill_no(rs.getString("bill_no"));
                t.setCard_no(rs.getString("card_no"));
                t.setDateCreate(rs.getDate("date_create"));
                t.setExchange_at(rs.getString("exchage_at"));
                t.setId(rs.getInt("id"));
                t.setNameFrom(rs.getString("nameFrom"));
                t.setNameTo(rs.getString("nameTo"));
                t.setReceiver(rs.getString("receiver"));
                t.setSta(rs.getInt("sta"));
                t.setType(rs.getInt("type"));
                bills.add(t);
                Storage.addBillNo(rs.getString("t.bill_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return bills;
    }

    public static void getBillNo() {
        Storage.ResetBill();
        String sql = "select bill_no from transaction";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Storage.addBillNo(rs.getString("bill_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
    }

    public static boolean insertBill(double amount, String card_no, String ex_at, String re, int sta, int type) {
        getBillNo();
        String bill_no = new CreateCode().createBillNo();
        String sql = "insert into transaction(amount,bill_no,card_no,date_create,exchage_at,receiver,sta,type) values (" + amount + ",\'" + bill_no + "\',\'" + card_no + ""
                + "\',now(),\'" + ex_at + "\',\'" + re + "\'," + sta + "," + type + ")";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
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

    public static void lockToUpdate(String card_no) {
        String sql = "update account set MarkLock=1 where account_no=(select account_no from card where card_no=\'" + card_no + "\') and MarkLock=0";;
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            int x = state.executeUpdate(sql);
//            if(x==1){
//                System.out.println("Success");
//            }else{
//                System.out.println("Failure");
//            }
            while (x == 0) {
                x = state.executeUpdate(sql);
                Thread.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
    }

    public static void unlock(String card_no) {
        String sql = "update account set MarkLock=0 where account_no=(select account_no from card where card_no=\'" + card_no + "\') and MarkLock=1";;
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            int x = state.executeUpdate(sql);
//            if(x==1){
//                System.out.println("Success");
//            }else{
//                System.out.println("Failure");
//            }
            while (x == 0) {
                x = state.executeUpdate(sql);
                Thread.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
    }

    //type=1
    public static boolean RutTien(String card_no, double amount, String at) {
        lockToUpdate(card_no);
        String sql = "select balance from account where account_no=(select account_no from card where card_no=\'" + card_no + "\')";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        double balance = 0;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                balance = rs.getDouble("balance");
            }
            if (balance >= amount) {
                balance -= amount;
                sql = "update account set balance=" + balance + " where account_no=(select account_no from card where card_no=\'" + card_no + "\')";
                if (state.executeUpdate(sql) == 1) {
                    sql = "select Account_No from card where card_no=\'" + card_no + "\'";
                    rs = state.executeQuery(sql);
                    String stk = "";
                    while (rs.next()) {
                        stk = rs.getString("Account_No");
                    }
                    insertBill(amount, card_no, at, stk, 0, 1);
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
            unlock(card_no);
        }
        return flag;
    }

    //type=2
    public static boolean NopTien(String card_no, double amount, String at) {
        lockToUpdate(card_no);
        String sql = "select balance from account where account_no=(select account_no from card where card_no=\'" + card_no + "\')";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        double balance = 0;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                balance = rs.getDouble("balance");
            }

            balance += amount;
            sql = "update account set balance=" + balance + " where account_no=(select account_no from card where card_no=\'" + card_no + "\')";
            if (state.executeUpdate(sql) == 1) {
                sql = "select Account_No from card where card_no=\'" + card_no + "\'";
                rs = state.executeQuery(sql);
                String stk = "";
                while (rs.next()) {
                    stk = rs.getString("Account_No");
                }
                insertBill(amount, card_no, at, stk, 0, 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
            unlock(card_no);
        }
        return flag;
    }

    //type=3
    public static boolean ChuyenTien(String card_no, String receiver, double amount,String at) {
        lockToUpdate(card_no);
        String sql = "select balance from account where account_no=(select account_no from card where card_no=\'" + card_no + "\')";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        double balance = 0;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                balance = rs.getDouble("balance");
            }
            if (balance >= amount) {
                balance -= amount;
                sql = "update account set balance=" + balance + " where account_no=(select account_no from card where card_no=\'" + card_no + "\')";
                if (state.executeUpdate(sql) == 1) {
                    sql = "select balance from account where account_no=\'" + receiver + "\'";
                    rs = state.executeQuery(sql);
                    while (rs.next()) {
                        balance = rs.getDouble("balance");
                    }
                    balance+=amount;
                    sql="update account set balance="+balance+" where Account_No=\'"+receiver+"\'";
                    if(state.executeUpdate(sql)==1){
                        insertBill(amount, card_no, at, receiver, 0, 3);
                    }
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
            unlock(card_no);
        }
        return flag;
    }
    
    //Lấy danh sách số tài khoản email phone iden_no
    public static void getAcNoPMI(){
        Storage.ResetAccount();
        String sql = "select Account_No,email,phone,iden_no from account";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Storage.addAccountNo(rs.getString("Account_No"));
                Storage.addEmail(rs.getString("email"));
                Storage.addPhone(rs.getString("phone"));
                Storage.addIden(rs.getString("iden_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
    }
    
    //Check login
    public static Account checkLogin(String card_no,String pass){
        String sql = "select account.* from card,account where card_no=\'"+card_no+"\' and pass=\'"+pass+"\' and card.account_no=account.Account_No and card.sta=1";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        boolean flag = true;
        Account a=null;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                a = new Account();
                a.setAccount_no(rs.getString("Account_No"));
                a.setAddress(rs.getString("address"));
                a.setBalance(rs.getDouble("balance"));
                a.setBirth(rs.getString("birth"));
                a.setDateCreate(rs.getDate("date_create"));
                a.setEmail(rs.getString("email"));
                a.setFullName(rs.getString("fullname"));
                a.setGender(rs.getInt("gender"));
                a.setIden_no(rs.getString("iden_no"));
                a.setPhone(rs.getString("phone"));
                a.setSta(rs.getInt("sta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return a;
    }
    
    
    public static Vector<Card> getCardOfAccount(String stk){
        Vector<Card> cards=new Vector<Card>();
        String sql = "select * from card where account_no=\'"+stk+"\'";
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        boolean flag = true;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Card c=new Card();
                c.setAccount_no(rs.getString("account_no"));
                c.setCard_no(rs.getString("card_no"));
                c.setDateCreate(rs.getDate("date_create"));
                c.setPass(rs.getString("pass"));
                c.setSta(rs.getInt("sta"));
                c.setType(rs.getInt("type"));
                cards.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return cards;
    }
    
    public static boolean changePass(String card,String pass){
        String sql = "update card set pass=\'"+pass+"\' where card_no=\'"+card+"\'";
        Connection con = null;
        Statement state = null;
        boolean flag = true;
        Account a=null;
        try {
            System.out.println(sql);
            con = DataAccess.getConnect1();
            state = con.createStatement();
            state.executeUpdate(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DataAccess.CloseStatement(state);
            DataAccess.CloseConnect(con);
        }
        return flag;
    }
}
