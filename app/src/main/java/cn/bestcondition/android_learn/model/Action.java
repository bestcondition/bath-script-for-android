package cn.bestcondition.android_learn.model;

import cn.bestcondition.android_learn.view.ViewData;

//一条可执行的普通语句
public class Action implements Statement {
    //语句的内容
    private final String content;
    //前端显示的内容
    private final ViewData viewData;

    public Action(String content) {
        this.content = content;
        viewData = initViewData();
    }

    public Action(ViewData viewData) {
        content = viewData.getText();
        this.viewData = viewData;
    }

    protected ViewData initViewData() {
        String bt1Text = "完成";
        String bt2Text = null;
        return new ViewData(content, bt1Text, bt2Text);
    }

    public String getContent() {
        return content;
    }

    @Override
    public ViewData getViewData() {
        return viewData;
    }

}
