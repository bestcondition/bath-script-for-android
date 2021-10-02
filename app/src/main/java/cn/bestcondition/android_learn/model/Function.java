package cn.bestcondition.android_learn.model;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

//函数，函数不能在函数中定义
public class Function {
    private final String functionName;
    private final List<Statement> statementList;

    public Function(String functionName, List<Statement> statementList) {
        this.functionName = functionName;
        this.statementList = Collections.unmodifiableList(statementList);
    }

    public String getFunctionName() {
        return functionName;
    }

    @NotNull
    public List<Statement> getStatementList() {
        return statementList;
    }
}
