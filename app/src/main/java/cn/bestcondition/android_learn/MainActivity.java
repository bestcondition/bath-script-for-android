package cn.bestcondition.android_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;

import cn.bestcondition.android_learn.compile.Compile;
import cn.bestcondition.android_learn.control.Control;
import cn.bestcondition.android_learn.model.Script;
import cn.bestcondition.android_learn.view.Callback;
import cn.bestcondition.android_learn.view.CallbackSignal;
import cn.bestcondition.android_learn.view.StatementView;
import cn.bestcondition.android_learn.view.ViewData;

public class MainActivity extends AppCompatActivity {
    private LinearLayout content;
    private LayoutInflater lf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = findViewById(R.id.content);
        lf = LayoutInflater.from(this);

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");


        Compile compile = new Compile(code);
        try {
            Script script = compile.compile();
            executeScript(script);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void executeScript(Script script) {
        Callback callback = new Control(script, this);
        StatementView statementView = new StatementView(content, lf, callback);
    }


    public static String getTextString() {
        return "\n" +
                "主函数：\n" +
                "    查看fast note\n" +
                "    如果 asdf：\n" +
                "        asdf Yes\n" +
                "    当 闹铃响 或者 洗衣机响时：\n" +
                "        拿出收藏袋，关闭洗衣机\n" +
                "    准备\n" +
                "    洗漱\n" +
                "    如果 还没到10点：\n" +
                "        洗衣服\n" +
                "    如果 有快递：\n" +
                "        拆快递\n" +
                "    如果 还没到10点半：\n" +
                "        锻炼\n" +
                "    设定到11点的闹钟\n" +
                "    当 闹钟响 时：\n" +
                "        带上手环\n" +
                "        上床看书\n" +
                "    设定手环11点半闹钟\n" +
                "    当 闹钟响 时：\n" +
                "        关闭闹钟，开始睡觉\n" +
                "\n" +
                "\n" +
                "准备：  \n" +
                "    卸掉手环充上电\n" +
                "    将伞放在爬梯旁边，放妥当\n" +
                "    将厂牌挂在挂钩上\n" +
                "    将包挂在床上\n" +
                "    将口罩挂外面\n" +
                "    将洗澡用拖鞋拿出来\n" +
                "    脱鞋\n" +
                "    脱袜子\n" +
                "    脱上衣\n" +
                "    脱裤子\n" +
                "    穿上洗澡用拖鞋\n" +
                "    将鞋和袜子放在阳台上\n" +
                "    将上衣、裤子放收藏袋里，将收藏袋放阳台上\n" +
                "\n" +
                "\n" +
                "洗漱：\n" +
                "    摘掉眼镜\n" +
                "    如果 需要洗头：\n" +
                "        将另一个拖鞋放在爬梯下面\n" +
                "        挑选一双袜子，放在桌子上\n" +
                "        拿出一个用来擦头的浴巾，挂衣柜上\n" +
                "        拿出吹风机，插上电，放在椅子上\n" +
                "        拿出收纳盒\n" +
                "        将牙刷、牙膏、茶杯、洗面奶、沐浴露、洗头膏、护发素、浴巾依次放入收纳盒，盖上盖，去浴室\n" +
                "        洗头\n" +
                "        抹护发素\n" +
                "        刷牙\n" +
                "        洗脸\n" +
                "        洗澡\n" +
                "        洗掉护发素\n" +
                "        裹上浴巾，将牙刷、牙膏、茶杯、洗面奶、沐浴露、洗头膏、护发素放入收纳盒中，盖上盖，回房间\n" +
                "        头裹浴巾\n" +
                "        将牙刷、牙膏、茶杯、洗面奶、沐浴露、洗头膏、护发素摆放整齐，将收纳箱放阳台\n" +
                "        将内裤放收藏袋里，放阳台\n" +
                "        擦干脚，穿上袜子，将毛巾放行李箱上\n" +
                "        吹干下面，穿上内裤，浴巾挂外面\n" +
                "        擦，吹头，浴巾挂外面\n" +
                "        吹风机收起来\n" +
                "\n" +
                "    否则：\n" +
                "        将头发扎起来\n" +
                "\n" +
                "        拿出牙刷、牙膏、茶杯\n" +
                "        刷牙\n" +
                "        将牙刷、牙膏、茶杯摆放好\n" +
                "\n" +
                "        拿出洗面奶\n" +
                "        洗脸\n" +
                "        将洗面奶摆放好\n" +
                "\n" +
                "        将另一个拖鞋放在爬梯下面\n" +
                "        挑选一双袜子，放在桌子上\n" +
                "        拿出吹风机，插上电，放在椅子上\n" +
                "        拿出浴巾、沐浴露\n" +
                "        洗澡\n" +
                "        裹浴巾，拿着沐浴露、内裤出来\n" +
                "        将沐浴露摆放好\n" +
                "        将内裤放在收藏袋里，放阳台\n" +
                "        擦干脚，穿上袜子，将毛巾放行李箱上\n" +
                "        吹干下面，穿上内裤，浴巾挂外面\n" +
                "        吹风机收起来\n" +
                "\n" +
                "\n" +
                "洗衣服：\n" +
                "    将阳台干衣服拿进来\n" +
                "    将所有收藏袋放进洗衣机\n" +
                "    加洗衣液\n" +
                "    设定合适的模式\n" +
                "    手机设定合适的倒计时\n" +
                "    当 闹铃响 或者 洗衣机响时：\n" +
                "        拿出收藏袋，关闭洗衣机\n" +
                "        将所有衣服套上晾衣架，挂在床上\n" +
                "        将所有衣服一并挂阳台\n" +
                "        将所有收藏袋挂阳台\n" +
                "\n" +
                "\n" +
                "\n" +
                "一组锻炼：\n" +
                "    吊30秒\n" +
                "    跪姿俯卧撑15个\n" +
                "    仰卧起坐14个\n" +
                "\n";
    }

}
