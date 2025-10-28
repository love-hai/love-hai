package com.LoveSea.fengCore.commons.formula;

import static com.LoveSea.fengCore.commons.formula.FormulaException.FormulaExceptionType.DATA_ERROR;
import static com.LoveSea.fengCore.commons.formula.FormulaException.FormulaExceptionType.DIV_ZERO;

/**
 * @author xiahaifeng
 */

public class OperatorsSymbols {
    private static String[] operatorsStrs = {">", "<", ">=", "<=", "!=", "=", "+", "-", "*", "/"};

    public static boolean isOperator(String symbol) {
        for (String operator : operatorsStrs) {
            if (operator.equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public static int getGrade(String symbol) {
        for (int i = 0; i < operatorsStrs.length; i++) {
            if (operatorsStrs[i].equals(symbol)) {
                return i;
            }
        }
        return -1;
    }

    public static Object add(Object first, Object second) {
        Object objInstance = first;
        Object objSecond = second;
        if (null == objInstance) {
            objInstance = second;
            objSecond = null;
        }
        if (null == objInstance) {
            return null;
        }
        if (objInstance instanceof String) {
            if (null == objSecond) {
                return objInstance;
            } else {
                if (!(objSecond instanceof String)) {
                    throw new FormulaException("", DATA_ERROR);
                }
                return objInstance + objSecond.toString();
            }
        } else if (objInstance instanceof Integer) {
            int a = (Integer) objInstance;
            if (null == objSecond) {
                return a;
            } else if (objSecond instanceof Integer) {
                int b = (Integer) objSecond;
                return a + b;
            } else if (objSecond instanceof Number) {
                double b = ((Number) objSecond).doubleValue();
                return a + b;
            } else {
                throw new FormulaException("", DATA_ERROR);
            }
        } else if (objInstance instanceof Number) {
            double a = ((Number) objInstance).doubleValue();
            if (null == objSecond) {
                return a;
            }
            if (objSecond instanceof Number) {
                double b = ((Number) objSecond).doubleValue();
                return a + b;
            } else {
                throw new FormulaException("", DATA_ERROR);
            }
        }
        String objStr = objInstance.getClass().toString();
        String objSecondStr = "";
        if (null == objSecond) {
            objSecondStr = objInstance.getClass().toString();
        }
        throw new FormulaException("add 不支持的类型 " + objStr + " " + objSecondStr);
    }

    public static Object sub(Object first, Object second) {
        if (null == first) {
            first = 0;
        }
        if (null == second) {
            second = 0;
        }
        if (first instanceof Integer && second instanceof Integer) {
            return (Integer) first - (Integer) second;
        }
        if (first instanceof Number && second instanceof Number) {
            return ((Number) first).doubleValue() - ((Number) second).doubleValue();
        }
        throw new FormulaException("减法运算符左右 operand 必须是数字");
    }

    public static Object mul(Object first, Object second) {
        Object objInstance = first;
        Object objSecond = second;
        if (null == objInstance) {
            objInstance = second;
            objSecond = 0;
        }
        if (null == objInstance) {
            return 0;
        }
        if (objInstance instanceof Integer && objSecond instanceof Integer) {
            return ((Integer) objInstance) * ((Integer) objSecond);
        }
        if (objInstance instanceof Number && objSecond instanceof Number) {
            return ((Number) objInstance).doubleValue() * ((Number) objSecond).doubleValue();
        }
        throw new FormulaException("乘法运算符左右 operand 必须是数字");
    }

    public static Object divide(Object first, Object second) {
        if (null == second) {
            throw new FormulaException("", DIV_ZERO);
        }
        if (null == first) {
            first = 0;
        }
        if (!(first instanceof Number) || !(second instanceof Number)) {
            throw new FormulaException("", DATA_ERROR);
        }
        double b = ((Number) second).doubleValue();
        if (b == 0) {
            throw new FormulaException("", DIV_ZERO);
        }
        double a = ((Number) first).doubleValue();
        return a / b;
    }

    public static Boolean compare(Object first, Object second, String symbol) {
        if (first instanceof Number && second instanceof Number) {
            double firstNum = ((Number) first).doubleValue();
            double secondNum = ((Number) second).doubleValue();
            switch (symbol) {
                case ">":
                    return firstNum > secondNum;
                case "<":
                    return firstNum < secondNum;
                case "=":
                    return firstNum == secondNum;
                case ">=":
                    return firstNum >= secondNum;
                case "<=":
                    return firstNum <= secondNum;
                case "!=":
                    return firstNum != secondNum;
                default:
                    throw new FormulaException("不支持的运算符");
            }
        }
        throw new FormulaException("不支持的运算符");
    }

}