/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.strparser.entity;

import org.epam.strparser.exception.LogicException;
import org.epam.strparser.structure.ComponentBehavior;

/**
 *
 * @author Sergiusz
 */
public class IndivisibleComponent implements ComponentBehavior {

    private String content;
    private final String TYPE;

    public IndivisibleComponent(String defType) throws LogicException {
        if (!defType.isEmpty()) {
            TYPE = defType;
        } else {
            throw new LogicException("Empty type in lexeme constructor!");
        }
    }

    public IndivisibleComponent(String defType, String lexContent) throws LogicException {
        if (!defType.isEmpty() && !lexContent.isEmpty()) {
            TYPE = defType;
            content = lexContent;
        } else {
            throw new LogicException("Empty type or content in lexeme constructor!");
        }
    }

    public void setContent(String lexContent) throws LogicException {
        if (!lexContent.isEmpty()) {
            content = lexContent;
        } else {
            throw new LogicException("Empty lexeme? How?");
        }
    }

    @Override
    public String getContent() {
        return content;
    }

    public String getType() {
        return TYPE;
    }

    public void makeFirstInSent() {
        content = content.substring(0, 1).toUpperCase().concat(content.substring(1));
    }

    public void makeLastInSent() {
        if (!content.matches("[А-яA-Z]{2,}")) {
            content = content.substring(0, 1).toLowerCase().concat(content.substring(1));
        }
    }

}
