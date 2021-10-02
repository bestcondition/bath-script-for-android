package cn.bestcondition.android_learn.model;

import android.view.View;

import java.util.Collections;
import java.util.List;

import cn.bestcondition.android_learn.view.ViewData;

public class If extends Wait {
    private final List<Statement> elseStatementList;


    public If(String expression, List<Statement> statementList, List<Statement> elseStatementList) {
        super(expression, statementList);
        this.elseStatementList = Collections.unmodifiableList(elseStatementList);
    }

    @Override
    protected ViewData initViewData() {
        ViewData viewData = super.initViewData();
        viewData.setBt2Text("NO");
        return viewData;
    }

    public List<Statement> getElseStatementList() {
        return elseStatementList;
    }
}
