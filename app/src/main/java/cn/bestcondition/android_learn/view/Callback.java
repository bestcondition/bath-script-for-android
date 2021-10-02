package cn.bestcondition.android_learn.view;


import android.view.View;

import cn.bestcondition.android_learn.model.Statement;

public interface Callback extends Statement {
    public CallbackSignal callback(View view);
}
