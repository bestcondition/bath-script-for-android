package cn.bestcondition.android_learn.control;

import android.view.View;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.bestcondition.android_learn.MainActivity;
import cn.bestcondition.android_learn.R;
import cn.bestcondition.android_learn.model.Action;
import cn.bestcondition.android_learn.model.Function;
import cn.bestcondition.android_learn.model.If;
import cn.bestcondition.android_learn.model.Statement;
import cn.bestcondition.android_learn.model.Script;
import cn.bestcondition.android_learn.model.Wait;
import cn.bestcondition.android_learn.view.CallbackSignal;
import cn.bestcondition.android_learn.view.Callback;
import cn.bestcondition.android_learn.view.ViewData;


import static cn.bestcondition.android_learn.view.CallbackSignal.END;
import static cn.bestcondition.android_learn.view.CallbackSignal.NOT_END;


public class Control implements Callback {
    //语句栈
    private Deque<Statement> stack;
    //脚本对象
    private final Script script;
    //可调用方法
    private final Map<String, Function> functionMap;
    //前端数据
    private final ViewData viewData;
    //上一条语句
    private Statement lastStatement;
    //活动
    private final MainActivity mainActivity;


    public Control(Script script, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        stack = new LinkedList<>();
        stack.addAll(script.getStatementList());
        this.script = script;
        functionMap = script.getFunctionMap();
        viewData = new ViewData();
    }

    @Override
    public CallbackSignal callback(View view) {
        //如果上一条语句是if语句
        if (lastStatement instanceof If) {
            If if_ = (If) lastStatement;
            int id = view.getId();
            List<Statement> statementList = id == R.id.yes_bt ? if_.getStatementList() : if_.getElseStatementList();
            pushAll(statementList);
        }

        Statement now = stack.peek();
        lastStatement = now;
        if (now == null) {
            return END;
        }

        if (now instanceof Action) {
            Action action = (Action) now;
            if (functionMap.containsKey(action.getContent())) {//说明这是个函数调用
                Function function = functionMap.get(action.getContent());
                stack.pop();
                assert function != null;
                pushAll(function.getStatementList());
                //再回调一下，刚刚算作是替换，并没执行语句
                return callback(view);
            } else {
                updateViewData(action);
            }
        } else if (now instanceof If) {
            If if_ = (If) now;
            updateViewData(if_);
        } else if (now instanceof Wait) {
            Wait wait = (Wait) now;
            Script newScript = this.script.fork(wait);
            mainActivity.executeScript(newScript);
            //在这把这个wait pop走，不然死循环了
            stack.pop();
            //fork一个script
            return callback(view);
        }
        stack.pop();
        return NOT_END;
    }

    /*
    批量入栈，实际上是以新列表创建新栈，把老栈放到新栈后面，
    不然的话要让列表逆序，一个一个入栈，
    我想一个一个入栈开销可能大一些，但是没测试啊，就这样吧！
     */
    private void pushAll(List<Statement> statementList) {
        Deque<Statement> newStack = new LinkedList<>(statementList);
        newStack.addAll(this.stack);
        this.stack = newStack;

    }

    private void updateViewData(Statement statement) {
        this.viewData.update(statement.getViewData());
    }


    @Override
    public ViewData getViewData() {
        return viewData;
    }

}
