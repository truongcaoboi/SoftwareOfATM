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
import java.util.Random;
import java.util.Vector;
import model.Storage;

public class CreateCode {

    private Random rd = new Random();

    public String createAccountNo() {
        String code = "";
        while (true) {
            for (int i = 0; i < 12; i++) {
                code += "" + Math.abs(rd.nextInt()) % 10;
            }
            if (!Storage.isChild(Storage.getAccount_nos(), code)) {
                Storage.addAccountNo(code);
                break;
            }
        }
        return code;
    }

    public String createCardNo() {
        String code = "";
        while (true) {
            for (int i = 0; i < 16; i++) {
                code += "" + Math.abs(rd.nextInt()) % 10;
            }
            if (!Storage.isChild(Storage.getCard_nos(), code)) {
                Storage.addCardNo(code);
                break;
            }
        }
        return code;
    }

    public String createBillNo() {
        String code = "";
        while (true) {
            for (int i = 0; i < 32; i++) {
                code += "" + Math.abs(rd.nextInt()) % 10;
            }
            if (!Storage.isChild(Storage.getAccount_nos(), code)) {
                Storage.addBillNo(code);
                break;
            }
        }
        return code;
    }
}
