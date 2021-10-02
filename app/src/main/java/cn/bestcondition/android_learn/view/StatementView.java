package cn.bestcondition.android_learn.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.bestcondition.android_learn.R;

public class StatementView {
    private static final String TAG = "StatementView";
    public LinearLayout item;
    public TextView text;
    public Button bt1;
    public Button bt2;
    public LinearLayout content;
    public Callback callback;

    private final ViewData viewData;

    public StatementView(LinearLayout content, LayoutInflater lf, Callback callback) {
        this.content = content;
        item = (LinearLayout) lf.inflate(R.layout.song, content, false);
        this.callback = callback;
        //拿到这个view data就好了，控制函数回直接改变这个对象里面的值，等回调函数好了之后，直接覆盖ui
        viewData = callback.getViewData();

        text = item.findViewById(R.id.tv);
        bt1 = item.findViewById(R.id.yes_bt);
        bt2 = item.findViewById(R.id.no_bt);

        bt1.setOnClickListener(this::bind);
        bt2.setOnClickListener(this::bind);

        //先点一次，把默认数据换掉
        bt1.callOnClick();
        add();
    }

    public void bind(View v) {
        CallbackSignal s = callback.callback(v);
        if (s == CallbackSignal.NOT_END) {
            update();
        } else if (s == CallbackSignal.END) {
            remove();
        }
    }

    public void add() {
        content.addView(item);
    }

    public void remove() {
        content.removeView(item);
    }

    private void update() {
        update(viewData.getText(), viewData.getBt1Text(), viewData.getBt2Text());
    }

    public void update(String text, String bt1_text, String bt2_text) {
        this.text.setText(text);
        this.bt1.setText(bt1_text);
        if (bt2_text != null) {
            this.bt2.setVisibility(View.VISIBLE);
            this.bt2.setText(bt2_text);
        } else {
            this.bt2.setVisibility(View.INVISIBLE);
        }

    }
}