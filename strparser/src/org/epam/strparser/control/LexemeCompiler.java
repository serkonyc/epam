/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.strparser.control;

import java.util.ArrayList;
import org.epam.strparser.entity.IndivisibleComponent;
import org.epam.strparser.exception.LogicException;
import org.epam.strparser.structure.CompositeDescript;

/**
 *
 * @author Sergiusz
 */
public class LexemeCompiler {

    private ArrayList<IndivisibleComponent> procLexArr;

    public LexemeCompiler(ArrayList<String> dataLexArr) throws LogicException {
        procLexArr = new ArrayList<>();
        for (String dataLex : dataLexArr) {
            if (dataLex.matches("\\p{Punct}|-")) {
                procLexArr.add(new IndivisibleComponent("Punct", dataLex));
            } else if (dataLex.matches("\\p{Space}")) {
                procLexArr.add(new IndivisibleComponent("Space", dataLex));
            } else if (dataLex.contains("Код-пример")) {
                procLexArr.add(new IndivisibleComponent("Code", dataLex));
            } else {
                procLexArr.add(new IndivisibleComponent("Lexeme", dataLex));
            }
        }
    }

    public CompositeDescript compileResult() throws LogicException {
        if (procLexArr != null) {
            CompositeDescript parag, sentence, compiledText = new CompositeDescript();
            for (int i = 0; i < procLexArr.size(); i++) {
                parag = new CompositeDescript();
                do {
                    sentence = new CompositeDescript();
                    do {
                        sentence.add(procLexArr.get(i));
                        i++;
                    } while (!procLexArr.get(i).getContent().matches("[\\.!?]"));
                    
                    sentence.swap(0, sentence.size()-1);
                    
                    sentence.add(procLexArr.get(i));
                    parag.add(sentence);
                    if (i == procLexArr.size() - 1) {
                        break;
                    }
                    i++;
                } while (!procLexArr.get(i).getContent().matches("\t") && i != procLexArr.size() - 1);
                compiledText.add(parag);
            }
            return compiledText;
        } else {
            throw new LogicException("Nothing to compile! Null Pointer!");
        }
    }
}
