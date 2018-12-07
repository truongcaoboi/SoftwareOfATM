/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicate;

/**
 *
 * @author Xuan Truong PC
 */
import java.net.*;
import java.io.*;
import java.util.Base64;
import model.*;
import control.*;
import java.util.Vector;
import org.json.JSONObject;

public class Communication {

    private Socket server = null;
    private int k = 0;

    public void SendResponse(Socket server, String response) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
            response = Base64.getEncoder().encodeToString(response.getBytes("UTF-8"));
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String ReceiveRequest(Socket server) {
        String request = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
            request = reader.readLine();
            request = new String(Base64.getDecoder().decode(request), "UTF-8").toString();
            String[] list = request.split(";");
            k = Integer.parseInt(list[0]);
            request = list[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public String executeRequest(String request) {
        String response = "";
        try {
            switch (k) {
                case 1:
                    JSONObject object = new JSONObject(request);
                    String s = object.getString("s");
                    int k1 = object.getInt("k");
                    Vector<Account> accounts = DataControl.Search(s, k1);
                    if (accounts.size() != 0) {
                        for (Account a : accounts) {
                            response += TranformJSONFromAccount(a) + ";";
                        }
                    } else {
                        response +="empty";
                    }
                    break;
                case 2:
                    System.out.println("case2");
                    Account a = DataControl.getAccount(request);
                    if (a != null) {
                        response += TranformJSONFromAccount(a);
                    } else {
                        response +="empty";
                    }
                    break;
                case 3:
                    Vector<Account> accounts1 = DataControl.getAccounts();
                    if (accounts1.size() != 0) {
                        for (Account a1 : accounts1) {
                            response += TranformJSONFromAccount(a1) + ";";
                        }
                    } else {
                        response +="empty";
                    }
                    break;
                case 4:
                    JSONObject object1 = new JSONObject(request);
                    String address = object1.getString("address");
                    String email = object1.getString("email");
                    if(Storage.isChild(Storage.getEmails(), email)){
                        return "trung email";
                    }
                    String phone = object1.getString("phone");
                    if(Storage.isChild(Storage.getPhones(), phone)){
                        return "trung phone";
                    }
                    String stk = object1.getString("stk");
                    boolean flag = DataControl.updateInfoAccount(address, email, phone, stk);
                    if (flag) {
                        response +="success";
                    } else {
                        response +="failure";
                    }
                    break;
                case 5:
                    if (DataControl.CloseAccount(request)) {
                        response +="success";
                    } else {
                        response +="failure";
                    }
                    break;
                case 6:
                    if (DataControl.OpenAccount(request)) {
                        response +="success";
                    } else {
                        response +="failure";
                    }
                    break;
                case 7:
                    JSONObject object2 = new JSONObject(request);
                    String add=object2.getString("add");
                    double bal=object2.getDouble("bal");
                    String birth=object2.getString("birth");
                    String email1=object2.getString("email");
                    if(Storage.isChild(Storage.getEmails(), email1)){
                        return "trung email";
                    }
                    String name=object2.getString("name");
                    int gen=object2.getInt("gen");
                    String ide=object2.getString("ide");
                    if(Storage.isChild(Storage.getIdens(), ide)){
                        return "trung iden";
                    }
                    String phone2=object2.getString("phone");
                    if(Storage.isChild(Storage.getPhones(), phone2)){
                        return "trung phone";
                    }
                    if(DataControl.insertAccount(add, bal, birth, email1, name, gen, ide, phone2)){
                        response+="success";
                    }else{
                        response+="failure";
                    } 
                    break;
                case 8:
                    JSONObject item = new JSONObject(request);
                    stk=item.getString("stk");
                    int type=item.getInt("type");
                    response+=DataControl.insertCard(stk, type);
                    break;
                case 9:
                    if(DataControl.CloseCard(request)){
                        response+="success";
                    }else{
                        response+="failure";
                    }    
                    break;
                case 10:
                    if(DataControl.OpenCard(request)){
                        response+="success";
                    }else{
                        response+="failure";
                    }
                    break;
                case 11:
                    Vector<Transaction> bills=DataControl.getAllBill();
                    if(bills.size()==0){
                        return "empty";
                    }else{
                        for(Transaction b:bills){
                            response+=TranformJSONFromBill(b);
                        }
                    }
                    break;
                case 12:
                    item=new JSONObject(request);
                    double amount=item.getDouble("amount");
                    String card_no=item.getString("card_no");
                    String at=item.getString("at");
                    if(DataControl.RutTien(card_no, amount, at)){
                        response+="success";
                    }else{
                        response+="failure";
                    }
                    break;
                case 13:
                    item=new JSONObject(request);
                    amount=item.getDouble("amount");
                    card_no=item.getString("card_no");
                    at=item.getString("at");
                    if(DataControl.NopTien(card_no, amount, at)){
                        response+="success";
                    }else{
                        response+="failure";
                    }
                    break;
                case 14:
                    item=new JSONObject(request);
                    amount=item.getDouble("amount");
                    card_no=item.getString("card_no");
                    at=item.getString("at");
                    String receiver=item.getString("receiver");
                    if(DataControl.ChuyenTien(card_no,receiver, amount, at)){
                        response+="success";
                    }else{
                        response+="failure";
                    }
                    break;
                case 15:
                    //Lấy ra danh sách thẻ của người dung
                    Vector<Card> cards=DataControl.getCardOfAccount(request);
                    for(Card c:cards){
                        response+=TranformJSONFromCard(c);
                    }
                    break;
                case 16:
                    //Kiểm tra đăng nhập người dùng
                    item=new JSONObject(request);
                    card_no=item.getString("card_no");
                    String pass=item.getString("pass");
                    a=DataControl.checkLogin(card_no, pass);
                    if(a!=null){
                        return TranformJSONFromAccount(a);
                    }else{
                        return "failure";
                    }
                    //break;
                case 17:
                    item=new JSONObject(request);
                    card_no=item.getString("card_no");
                    pass=item.getString("pass");
                    if(DataControl.changePass(card_no, pass)){
                        response+="success";
                    }else{
                        response+="failure";
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
        return response;
    }

    public void Run() {
        SendResponse(server, executeRequest(ReceiveRequest(server)));
    }

    public void setServer(Socket s) {
        server = s;
    }

    private String TranformJSONFromAccount(Account a) {
        String str = "{\"accoun_no\":\"" + a.getAccount_no() + "\",\"address\":\"" + a.getAddress() + "\",\"birth\":\"" + a.getBirth() + "\",\"date_create\":\"" + a.getDateCreate() + "\",\"email\":\"" + a.getEmail() + "\",\"fullname\":\"" + a.getFullName() + "\",\"gender\":" + a.getGender() + ",\"iden_no\":\"" + a.getIden_no() + "\",\"phone\":\"" + a.getPhone() + "\",\"sta\":" + a.getSta() + ",\"balance\":" + a.getBalance() + "}";
        System.out.println(str);
        return str;
    }
    
    private String TranformJSONFromBill(Transaction a) {
        String str = "{\"bill_no\":\"" + a.getBill_no() + "\",\"card_no\":\"" + a.getCard_no() + "\",\"account_no\":\"" + a.getAccount_no() + "\",\"date_create\":\"" + a.getDateCreate() + "\",\"at\":\"" + a.getExchange_at() + "\",\"receiver\":\"" + a.getReceiver() + "\",\"nameTo\":\"" + a.getNameTo() + "\",\"nameFrom\":\"" + a.getNameFrom() + "\",\"amount\":\"" + a.getAmount() + "\",\"sta\":" + a.getSta() + ",\"type\":" + a.getType() + "}";
        System.out.println(str);
        return str;
    }
    
    private String TranformJSONFromCard(Card a) {
        String str = "{\"accoun_no\":\"" + a.getAccount_no() + "\",\"card_no\":\"" + a.getCard_no() + "\",\"date_create\":\"" + a.getDateCreate() + "\",\"sta\":" + a.getSta() + ",\"type\":" + a.getType() + ",\"pass\":\"" + a.getPass() + "\"}";
        System.out.println(str);
        return str;
    }
}
