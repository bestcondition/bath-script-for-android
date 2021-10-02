package cn.bestcondition.android_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class TextInput extends AppCompatActivity {
    private static final String TAG = "fuck";
    private EditText inputText;
    private Button excButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        inputText = findViewById(R.id.input_text);
        excButton = findViewById(R.id.exc_bt);

        excButton.setOnClickListener(v -> {
            String code = inputText.getText().toString();
            Log.d(TAG, "onCreate: " + code);
            Intent intent = new Intent(TextInput.this, MainActivity.class);
            intent.putExtra("code", code);
            startActivity(intent);
        });

    }
}