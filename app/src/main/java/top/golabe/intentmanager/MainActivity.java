package top.golabe.intentmanager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import top.golabe.intent.IntentGo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gotoNormal(View view) {
        IntentGo
                .with(this)
                .target(Main2Activity.class)
                .go();
    }

    public void gotoParams(View view) {
        IntentGo
                .with(this)
                .target(Main3Activity.class)
                .params("username", "allen")
                .params("age", 12)
                .params("userInfo", new User("allen", 12))
                .params("id", 2000000L)
                .params("float", 0.5F)
                .params("bool", false)
                .go();
    }

    public void gotoResult(View view) {
        IntentGo
                .with(this)
                .targetForResult(Main3Activity.class, 2000)
                .params("username", "allen")
                .params("age", 12)
                .params("userInfo", new User("allen", 12))
                .params("id", 2000000L)
                .params("float", 0.5F)
                .params("bool", false)
                .go();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 2000) {
                Bundle bundle = data.getExtras();
                String username = bundle.getString("username");
                Toast.makeText(MainActivity.this, username, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void gotoTransition(View view) {
        IntentGo
                .with(this)
                .targetForResult(Main3Activity.class, 2000)
                .params("username", "allen")
                .params("age", 12)
                .params("userInfo", new User("allen", 12))
                .params("id", 2000000L)
                .params("float", 0.5F)
                .params("bool", false)
                .makeSceneTransitionAnimation(new Pair<>(view, "BUTTON"))
                .go();
    }
}
