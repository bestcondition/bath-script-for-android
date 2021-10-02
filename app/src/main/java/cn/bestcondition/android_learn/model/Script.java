package cn.bestcondition.android_learn.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Script {
    public final String MAIN_FUNCTION_NAME = "主函数";
    private final Map<String, Function> functionMap;
    private final List<Statement> statementList;

    public Script(List<Function> functionList) {
        this.functionMap = Collections.unmodifiableMap(createFunctionMap(functionList));
        this.statementList = Collections.unmodifiableList(this.functionMap.get(MAIN_FUNCTION_NAME).getStatementList());
    }

    private Script(Map<String, Function> functionMap, List<Statement> statementList) {
        this.functionMap = Collections.unmodifiableMap(functionMap);
        this.statementList = Collections.unmodifiableList(statementList);
    }

    public Script fork(Wait wait) {
        Action trigger = new Action(wait.getViewData());
        List<Statement> newStatementList = new LinkedList<>();
        newStatementList.add(trigger);
        newStatementList.addAll(wait.getStatementList());
        return new Script(this.functionMap, newStatementList);
    }


    private Map<String, Function> createFunctionMap(List<Function> functionList) {
        Map<String, Function> functionMap = new HashMap<String, Function>();
        for (Function function : functionList) {
            //在这里假定function不会重名，重名判断在读取里面做，不在这里做
            functionMap.put(function.getFunctionName(), function);
        }
        return functionMap;
    }

    public Map<String, Function> getFunctionMap() {
        return functionMap;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }
}
