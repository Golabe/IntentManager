package top.golabe.intentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import top.golabe.intent.IntentResult;

public class Main3Activity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.tv_result);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String name = bundle.getString("username");
        int age = bundle.getInt("age");
        User userInfo = (User) bundle.getSerializable("userInfo");
        long id = bundle.getLong("id");
        float aFloat = bundle.getFloat("float");

        boolean bool = bundle.getBoolean("bool");


        textView.append(name + '\n');
        textView.append(age + "" + '\n');
        textView.append(userInfo.getUsername() + "----" + userInfo.getAge() + '\n');
        textView.append(id + "" + '\n');
        textView.append(aFloat + "" + '\n');
        textView.append(bool + "");


    }

    @Override
    public void onBackPressed() {
        IntentResult
                .with(this)
                .params("username","allen")
                .result(RESULT_OK);
        super.onBackPressed();

    }
}
