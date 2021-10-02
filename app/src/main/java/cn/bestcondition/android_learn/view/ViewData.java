package cn.bestcondition.android_learn.view;

public class ViewData {
    public static final String DEFAULT_TEXT = "null";
    private String text, bt1Text, bt2Text;

    public ViewData() {
        text = DEFAULT_TEXT;
        bt1Text = DEFAULT_TEXT;
        bt2Text = DEFAULT_TEXT;
    }

    public ViewData(String text, String bt1Text, String bt2Text) {
        this.text = text;
        this.bt1Text = bt1Text;
        this.bt2Text = bt2Text;
    }

    public void update(ViewData viewData) {
        text = viewData.text;
        bt1Text = viewData.bt1Text;
        bt2Text = viewData.bt2Text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBt1Text() {
        return bt1Text;
    }

    public void setBt1Text(String bt1Text) {
        this.bt1Text = bt1Text;
    }

    public String getBt2Text() {
        return bt2Text;
    }

    public void setBt2Text(String bt2Text) {
        this.bt2Text = bt2Text;
    }
}
