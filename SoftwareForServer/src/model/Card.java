/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Xuan Truong PC
 */
import java.util.Date;
import java.text.SimpleDateFormat;
public class Card {
    private String card_no;
    private Date dateCreate;
    private String pass;
    private String account_no;
    private int sta;
    private int type;

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getDateCreate() {
        String str = "";
        try {
            SimpleDateFormat output = new SimpleDateFormat("dd/MM/Y");
            str = output.format(dateCreate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getSta() {
        
        return sta+"";
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public String getType() {
        
        return type+"";
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String showCardNo(){
        String result="";
        for(int i=0;i<card_no.length();i++){
            char item=card_no.charAt(i);
            result+=item;
            if((i+1)%4==0){
                result+=" ";
            }
        }
        return result;
    }
    
    public String showAccountNo(){
        String result="";
        for(int i=0;i<account_no.length();i++){
            char item=account_no.charAt(i);
            result+=item;
            if((i+1)%3==0){
                result+=" ";
            }
        }
        return result;
    }
}
