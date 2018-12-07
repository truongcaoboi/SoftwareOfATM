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

public class Transaction {

    private int id;
    private String bill_no;
    private String card_no;
    private double amount;
    private int type;
    private int sta;
    private String receiver;
    private String exchange_at;
    private Date dateCreate;
    private String account_no;
    private String nameFrom;
    private String nameTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
//        if (type == 1) {
//            return "Rút tiền";
//        } else if (type == 2) {
//            return "Nộp tiền";
//        } else {
//            return "Chuyển tiền";
//        }
        return type+"";
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSta() {
//        if (sta == 1) {
//            return "Đã xác nhận";
//        } else {
//            return "Chưa xác nhận";
//        }
        return sta+"";
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getExchange_at() {
        return exchange_at;
    }

    public void setExchange_at(String exchange_at) {
        this.exchange_at = exchange_at;
    }

    public String getDateCreate() {
        String str = "";
        try {
            SimpleDateFormat output = new SimpleDateFormat("dd/MM/Y HH:mm");
            str = output.format(dateCreate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    
    
    public String showBillNo(){
        String result="";
        for(int i=0;i<bill_no.length();i++){
            char item=bill_no.charAt(i);
            result+=item;
            if((i+1)%4==0){
                result+=" ";
            }
        }
        return result;
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
    
    public String showReceiver(){
        String result="";
        for(int i=0;i<receiver.length();i++){
            char item=receiver.charAt(i);
            result+=item;
            if((i+1)%3==0){
                result+=" ";
            }
        }
        return result;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }
    
    
}
