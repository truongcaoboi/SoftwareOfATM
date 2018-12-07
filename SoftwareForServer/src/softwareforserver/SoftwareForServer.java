/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareforserver;

/**
 *
 * @author Xuan Truong PC
 */
import communicate.Communication;
import control.DataControl;
import java.io.*;
import java.util.Vector;
import model.Account;
import java.net.*;
public class SoftwareForServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ReadFIle().Read();
        ServerSocket listener=null;
        try{
            listener=new ServerSocket(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        while(true){
            try{
                Communication com=new Communication();
                if(listener!=null){
                    com.setServer(listener.accept());
                    Thread t=new Thread(){
                        @Override
                        public void run(){
                            com.Run();
                        }
                    };
                    t.setDaemon(true);
                    t.start();
                }else{
                    break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
