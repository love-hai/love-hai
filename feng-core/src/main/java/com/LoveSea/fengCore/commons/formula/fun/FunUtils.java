package com.LoveSea.fengCore.commons.formula.fun;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiahaifeng
 */

public class FunUtils {
    public static String removeOuterMatchedBrackets(String expr) {
        if (expr == null) return "";

        expr = expr.trim(); // 去除前后空格

        if (expr.length() < 2) return expr;

        char first = expr.charAt(0);
        char last = expr.charAt(expr.length() - 1);

        if (!isMatchingPair(first, last)) return expr;

        int depth = 0;
        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (ch == first) {
                depth++;
            } else if (ch == last) {
                depth--;
            }

            // 如果中途就闭合，说明最外层不是完整包裹
            if (depth == 0 && i < expr.length() - 1) {
                return expr;
            }
        }

        // 最外层括号成对且完整包裹整个表达式
        return expr.substring(1, expr.length() - 1).trim();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
    }

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
        boolean isSymbols = false;
        for (int i = 0; i < formula.length(); i++) {
            if (0 == balanced && evalFirstIndex + 1 != i) {
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

    public static char findNextNoBlankChar(String formula, int start) {
        char[] chars = formula.toCharArray();
        for (int i = start; i < chars.length; i++) {
            char c = chars[i];
            if (c != ' ') {
                return c;
            }
        }
        return ' ';
    }

    private static String cellRg = "^([A-Z]+)([0-9]+)$";
    private static Pattern cellPattern = Pattern.compile(cellRg);

    public static boolean isCell(String formula) {
        if (formula == null) return false;
        formula = removeOuterMatchedBrackets(formula).trim();
        return cellPattern.matcher(formula).matches();
    }

    public static String[] splitCell(String formula) {
        if (formula == null) return null;
        formula = removeOuterMatchedBrackets(formula).trim();
        Matcher matcher = cellPattern.matcher(formula);
        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            return null;
        }
    }
}