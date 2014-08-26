/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.command.factory;

/**
 *
 * @author Sergiusz
 */
public enum CommandType {

    BEGIN, LOGIN, REGISTER, NEEDLOGIN, NEEDREGISTER, PREPARETEST, CHOOSESUBJECT,
    CHOOSETHEME, MAKETEST, PRODUCINGTESTCOMMAND, CHOOSETEST, PASSTEST, 
    CHANGETEST, DELETETEST, DELETECHOSENTEST, CHANGECHOSENTEST, LOOKSTAT,
    LOOKMAKESTAT, CHANGELOCALE, USERDEAL, UPGRADEUSER, DOWNGRADEUSER, DELETEUSER

}
