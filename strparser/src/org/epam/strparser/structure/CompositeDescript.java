/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.strparser.structure;

import java.util.ArrayList;
import java.util.Collections;
import org.epam.strparser.entity.IndivisibleComponent;

/**
 *
 * @author Sergiusz
 */
public class CompositeDescript implements ComponentBehavior {

    private ArrayList<ComponentBehavior> childLexemes = new ArrayList<>();

    @Override
    public String getContent() {
        StringBuilder content = new StringBuilder();
        for (ComponentBehavior lexeme : childLexemes) {
            content.append(lexeme.getContent());
        }
        return content.toString();
    }

    public void add(ComponentBehavior lexeme) {
        childLexemes.add(lexeme);
    }

    public void remove(ComponentBehavior lexeme) {
        childLexemes.remove(lexeme);
    }

    public void set(int index, ComponentBehavior lexeme) {
        childLexemes.set(index, lexeme);
    }

    public void swap(int index1, int index2) {
        try {
            if (")".equals(childLexemes.get(index2).getContent())) {
                index2--;
            }
            if (!childLexemes.get(index1).getContent().matches("[A-ÿ]+")) {
                index1++;
                if (!childLexemes.get(index1).getContent().matches("[A-ÿ]+")) {
                    index1++;
                    if (!childLexemes.get(index1).getContent().matches("[A-ÿ]+")) {
                        index1++;
                    }
                }
            }
            ((IndivisibleComponent)childLexemes.get(index1)).makeLastInSent();
            ((IndivisibleComponent)childLexemes.get(index2)).makeFirstInSent();
            Collections.swap(childLexemes, index1, index2);
        } catch (IndexOutOfBoundsException ex) {

        }
    }

    public int size() {
        return childLexemes.size();
    }

    public void clear() {
        childLexemes.clear();
    }
}
