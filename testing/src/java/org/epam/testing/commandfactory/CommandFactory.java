/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory;

import org.epam.testing.commandfactory.order.*;
import org.epam.testing.commandfactory.order.statistics.*;
import org.epam.testing.commandfactory.order.testmaking.*;
import org.epam.testing.commandfactory.order.testmanaging.*;
import org.epam.testing.commandfactory.order.testpassing.*;
import org.epam.testing.exception.LogicException;

/**
 *
 * @author Sergiusz
 */
public class CommandFactory {

    public static AbstractCommand getCommandByName(String commandName) throws LogicException {
        try {
            switch (CommandList.valueOf(commandName.toUpperCase())) {
                case BEGIN:
                    return new BeginCommand();
                case LOGIN:
                    return new LoginCommand();
                case REGISTER:
                    return new RegisterCommand();
                case NEEDREGISTER:
                    return new NeedRegisterCommand();
                case NEEDLOGIN:
                    return new NeedLoginCommand();
                case PREPARETEST:
                    return new PrepareTestCommand();
                case CHOOSESUBJECT:
                    return new ChooseSubjectCommand();
                case CHOOSETHEME:
                    return new ChooseThemeCommand();
                case MAKETEST:
                    return new MakeTestCommand();
                case CHOOSETEST:
                    return new ChooseTestCommand();
                case PASSTEST:
                    return new PassTestCommand();
                case CHANGETEST:
                    return new ChangeTestCommand();
                case DELETETEST:
                    return new DeleteTestCommand();
                case DELETECHOSENTEST:
                    return new DeleteChosenTestCommand();
                case CHANGECHOSENTEST:
                    return new ChangeChosenTestCommand();
                case LOOKSTAT:
                    return new LookPassStatCommand();
                case LOOKMAKESTAT:
                    return new LookMakeStatCommand();
                case CHANGELOCALE:
                    return new ChangeLocaleCommand();
                default:
                    throw new LogicException("Trouble with CommandList enum");
            }
        } catch (IllegalArgumentException ex) {
            throw new LogicException("Trouble with CommandList enum", ex.getCause());
        }
    }
}
