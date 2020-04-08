package net.skhu.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {
    Item memo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        EditText editText_title = (EditText) findViewById(R.id.editText_title);
        EditText editText_content = (EditText) findViewById(R.id.editText_content);

        memo = new Item("","");

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            memo.setTitle(extras.getString("title"));
            memo.setContent(extras.getString("content"));
        }

        editText_title.setText(memo.getTitle());
        editText_content.setText(memo.getContent());

        Button button = (Button) findViewById(R.id.btnSave);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_title = (EditText) findViewById(R.id.editText_title);
                String title = editText_title.getText().toString();
                if (isEmptyOrWhiteSpace(title))
                    editText_title.setError("제목을 입력하세요");

                EditText editText_content = (EditText) findViewById(R.id.editText_content);
                String content = editText_content.getText().toString();
                if (isEmptyOrWhiteSpace(content))
                    editText_content.setError("내용을 입력하세요");

                // 메모 데이터를 서버에 전송하는 코드를 구현해야 함.

                String msg = "저장 성공: " + title;
                memo.setTitle(title);
                memo.setContent(content);
                Toast.makeText(MemoActivity.this, msg, Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.putExtra("title", memo.getTitle());
                intent.putExtra("content", memo.getContent());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        };
        button.setOnClickListener(listener);
    }

    static boolean isEmptyOrWhiteSpace(String s) {
        if (s == null) return true;
        return s.toString().trim().length() == 0;
    }
}
