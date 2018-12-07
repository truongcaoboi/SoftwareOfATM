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

import java.sql.*;
public class DataAccess {
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String user="truong";
    private static String pass="123";
    private static String host1="";
    private static String host2="";
    
    public static void setHost1(String host){
        host1=host;
    }

    public static void setHost2(String host){
        host2=host;
    }
    
    public static Connection getConnect1(){
        String url1="jdbc:mysql://"+host1+":3306/minibank?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
        Connection con=null;
        try{
            Class.forName(driver);
            con=DriverManager.getConnection(url1,user,pass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
    
    public static Connection getConnect2(){
        String url2="jdbc:mysql://"+host2+":3306/minibank2?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
        Connection con=null;
        try{
            Class.forName(driver);
            con=DriverManager.getConnection(url2,user,pass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
    
    public static void CloseConnect(Connection con){
        try{
            if(con!=null){
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void CloseStatement(Statement state){
        try{
            if(state!=null){
                state.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void CloseResultSet(ResultSet result){
        try{
            if(result!=null){
                result.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
