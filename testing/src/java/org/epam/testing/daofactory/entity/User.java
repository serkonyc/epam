/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.daofactory.entity;

/**
 *
 * @author Sergiusz
 */
public class User extends AbstractEntity {

    private String nick;
    private String email;
    private String role;
    private String pass;

    public User(int id, String... args) {
        super(id);
        this.nick = args[0];
        this.email = args[1];
        this.role = args[2];
        this.pass = args[3];
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
