/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareforserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Xuan Truong PC
 */
public class ReadFIle {
    public void Read(){
        try{
            File file=new File("config.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String host1=br.readLine().split(":")[1];
            String host2=br.readLine().split(":")[1];
            control.DataAccess.setHost1(host1);
            control.DataAccess.setHost2(host2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
