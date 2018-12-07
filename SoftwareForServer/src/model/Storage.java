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
import java.util.Vector;
public class Storage {
    private static Vector<String> emails=new Vector<String>();
    private static Vector<String> phones=new Vector<String>();
    private static Vector<String> account_nos=new Vector<String>();
    private static Vector<String> card_nos=new Vector<String>();
    private static Vector<String> bill_nos=new Vector<String>();
    private static Vector<String> iden_nos=new Vector<String>();

    public static Vector<String> getIdens(){
        return iden_nos;
    }
    
    public static Vector<String> getEmails() {
        return emails;
    }

    public static Vector<String> getPhones() {
        return phones;
    }

    public static Vector<String> getAccount_nos() {
        return account_nos;
    }

    public static Vector<String> getCard_nos() {
        return card_nos;
    }

    public static Vector<String> getBill_nos() {
        return bill_nos;
    }
    
    public static void addIden(String i){
        iden_nos.add(i);
    }
    
    public static void addEmail(String email){
        emails.add(email);
    }
    
    public static void addPhone(String phone){
        phones.add(phone);
    }
    
    public static void addAccountNo(String accountNo){
        account_nos.add(accountNo);
    }
    
    public static void addCardNo(String cardNo){
        card_nos.add(cardNo);
    }
    
    public static void addBillNo(String billNo){
        bill_nos.add(billNo);
    }

    public static boolean isChild(Vector<String> strings,String item){
        for(String x:strings){
            if(item.equals(x)){
                return true;
            }
        }
        return false;
    }
    
    
    public static void ResetAccount(){
        iden_nos.removeAllElements();
        emails.removeAllElements();
        phones.removeAllElements();
        account_nos.removeAllElements();
    }
    
    public static void ResetBill(){
        bill_nos.removeAllElements();
    }
    
    public static void ResetCard(){
        card_nos.removeAllElements();
    }
}
