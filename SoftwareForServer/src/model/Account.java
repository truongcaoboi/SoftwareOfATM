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

public class Account {

    //Khai báo thuộc tính
    private String account_no;//Số tài khoản
    private String address;//Địa chỉ
    private double balance;//Số dư tài khoản
    private String birth;//Năm sinh
    private Date dateCreate;//Ngày tạo tài khoản
    private String email;//Địa chỉ hộp thư thoại
    private String fullName;//Tên đầy đủ
    private int gender;//Giới tính
    private String iden_no;//Số chứng minh thư
    private String phone;//Số điện thoại
    private int sta;//Trạng thái hoạt động của tài khoản

    //Khai báo các phương thức set và get
    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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

    public String getEmail() {
        if(email!=null){
            return email;
        }else{
            return "Chưa cập nhật";
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIden_no() {
        return iden_no;
    }

    public void setIden_no(String iden_no) {
        this.iden_no = iden_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSta() {
        return this.sta+"";
    }

    public void setSta(int sta) {
        this.sta = sta;
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
