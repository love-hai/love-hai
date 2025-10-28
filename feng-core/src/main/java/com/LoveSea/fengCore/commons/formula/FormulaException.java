package com.LoveSea.fengCore.commons.formula;

/**
 * @author xiahaifeng
 */

public class FormulaException extends IllegalArgumentException {

    private FormulaExceptionType formulaExceptionType = FormulaExceptionType.OTHER;

    public FormulaException(String message) {
        super(message);
    }

    public FormulaException(String message, FormulaExceptionType formulaExceptionType) {
        this(message);
        this.formulaExceptionType = formulaExceptionType;
    }

    public FormulaExceptionType getFormulaExceptionType() {
        return formulaExceptionType;
    }

    public enum FormulaExceptionType {
        DIV_ZERO("除数不能为0", "#DIV/0!"),
        DATA_ERROR("数据错误", "#VALUE!"),
        DATA_NULL("数据为空", "#N/A"),
        ILLEGAL_FUN("非法函数", "#NAME!"),
        OTHER("其他错误", "#ERROR!"),
        ;
        public final String message;
        public final String errorCode;

        FormulaExceptionType(String message, String errorCode) {
            this.message = message;
            this.errorCode = errorCode;
        }
    }


}