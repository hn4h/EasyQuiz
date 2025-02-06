/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author 11
 */
public class PasswordUtil {

    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }

    public static void main(String[] args) {
        String password = "admin";
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);
        String password1 = "123456";
        String hashedPassword1 = hashPassword(password1);
        System.out.println("Hashed Password: " + hashedPassword1);
        boolean isMatch = verifyPassword(password, hashedPassword);
        System.out.println("Password is correct: " + isMatch);
    }
}
