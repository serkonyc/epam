/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.factory;

import org.epam.testing.command.common.*;
import org.epam.testing.command.statistics.*;
import org.epam.testing.command.testmaking.*;
import org.epam.testing.command.testmanaging.*;
import org.epam.testing.command.testpassing.*;
import org.epam.testing.command.userdeal.*;
import org.epam.testing.exception.LogicException;

/**
 * Класс получения команды для выполнения по её имени.
 * 
 * @author Sergiusz
 */
public class CommandFactory {

    /**
     * Получение команды для выполнения по имени.
     * 
     * @param commandName Название команды
     * @return инициализированный потомок AbstractCommand
     * @throws LogicException при проблеме со списком допустимых команд.
     */
    public static AbstractCommand getCommandByName(String commandName) throws LogicException {
        try {
            switch (CommandType.valueOf(commandName.toUpperCase())) {
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
                case USERDEAL:
                    return new LookUserListCommand();
                case UPGRADEUSER:
                    return new UpgradeUserCommand();
                case DOWNGRADEUSER:
                    return new DownGradeUserCommand();
                case DELETEUSER:
                    return new DeleteUserCommand();
                default:
                    throw new LogicException("Trouble with CommandList enum");
            }
        } catch (IllegalArgumentException ex) {
            throw new LogicException("Trouble with CommandList enum", ex.getCause());
        }
    }
}
