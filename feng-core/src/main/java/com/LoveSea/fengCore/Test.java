/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {

    private static final char[] OperationSymbols = {'+', '-', '*', '/', '<', '>', '='};
    private static final char[] SeparationSymbols = {','};

    private static boolean isSymbol(char c, char[] symbols) {
        for (char symbol : symbols) {
            if (c == symbol) {
                return true;
            }
        }
        return false;
    }

    public static List<String> findEvalsBySeparation(String formula) {
        return findEvalsBySymbols(formula, SeparationSymbols);
    }

    public static List<String> findEvalsByOperation(String formula) {
        return findEvalsBySymbols(formula, OperationSymbols);
    }


    public static List<String> findEvalsBySymbols(String formula, char[] symbols) {
        List<String> evals = new ArrayList<>();
        int balanced = 0;
        int evalFirstIndex = -1;
        boolean isSymbols = true;
        for (int i = 0; i < formula.length(); i++) {
            if (0 == balanced) {
                if (isSymbols && !isSymbol(formula.charAt(i), symbols)) {
                    evals.add(formula.substring(evalFirstIndex + 1, i).trim());
                    evalFirstIndex = i - 1;
                    isSymbols = false;
                } else if (!isSymbols && isSymbol(formula.charAt(i), symbols)) {
                    evals.add(formula.substring(evalFirstIndex + 1, i).trim());
                    evalFirstIndex = i - 1;
                    isSymbols = true;
                }
            }
            if ('(' == formula.charAt(i)) {
                balanced += 1;
            }
            if (')' == formula.charAt(i)) {
                balanced -= 1;
            }
        }
        if (balanced != 0) {
            throw new RuntimeException(" Round not found balanced");
        }
        evals.add(formula.substring(evalFirstIndex + 1).trim());
        return evals;
    }

    public static void main(String[] args) {
        String formula = "IF((X2+Y2)/R2>AA2,0,(AA2-(X2+Y2)/R2)*R2),0";
        List<String> as = findEvalsBySeparation( formula);
        for (String a : as) {
            System.out.println(a);
        }
    }
}
