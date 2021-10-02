package cn.bestcondition.android_learn.model;


import java.util.Collections;
import java.util.List;

import cn.bestcondition.android_learn.view.ViewData;

public class Wait implements Statement {
    private final String expression;
    private final List<Statement> statementList;
    protected final ViewData viewData;

    public Wait(String expression, List<Statement> statementList) {
        this.expression = expression;
        this.statementList = Collections.unmodifiableList(statementList);
        viewData = initViewData();
    }

    protected ViewData initViewData() {
        String bt1Text = "YES";
        String bt2Text = null;
        return new ViewData(expression, bt1Text, null);

    }


    public List<Statement> getStatementList() {
        return statementList;
    }

    @Override
    public ViewData getViewData() {
        return viewData;
    }
}
