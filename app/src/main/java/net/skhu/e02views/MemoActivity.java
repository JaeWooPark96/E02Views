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

        //EditText 의 객체를 찾아주었습니다.
        final EditText editText_title = (EditText) findViewById(R.id.editText_title);
        final EditText editText_content = (EditText) findViewById(R.id.editText_content);

        memo = new Item("","");//맨처음 memo는 비어있도록 했습니다.

        Item item = (Item)getIntent().getSerializableExtra("MEMO");//객체를 부모 Activity로부터 전달 받을 때는 이렇게 전달 받습니다.
        if (item!=null){
            memo = item;
        }

        //Main으로부터 memo에 대한 정보를 받아주었습니다.
        Bundle extras = getIntent().getExtras();//부모 Activity로부터 데이터를 전달 받아 주었습니다.
        if(extras != null) {
            //memo.setTitle(extras.getString("title"));
            //memo.setContent(extras.getString("content"));
        }

        //해당 EditText에 memo의 내용을 채워주었습니다.
        editText_title.setText(memo.getTitle());
        editText_content.setText(memo.getContent());

        //해당 Button 객체를 얻어주었습니다.
        Button button = (Button) findViewById(R.id.btnSave);
        View.OnClickListener listener = new View.OnClickListener() {//View의 리스너를 만들어주었습니다.
            @Override
            public void onClick(View view) {
                //EditText editText_title = (EditText) findViewById(R.id.editText_title);
                String title = editText_title.getText().toString();//EditText에 입력받은 데이터를 저장해주었습니다.
                if (isEmptyOrWhiteSpace(title))//해당 String이 비었는지 확인해주었습니다.
                    editText_title.setError("제목을 입력하세요");//비어있으면, EditText에 에러표시 나오도록 해주었습니다.

                //EditText editText_content = (EditText) findViewById(R.id.editText_content);
                String content = editText_content.getText().toString();//EditText에 입력받은 데이터를 저장해주었습니다.
                if (isEmptyOrWhiteSpace(content))//해당 String이 비었는지 확인해주었습니다.
                    editText_content.setError("내용을 입력하세요");//비어있으면, EditText에 에러표시 나오도록 해주었습니다.

                // 메모 데이터를 서버에 전송하는 코드를 구현해야 함.

                //메모에 해당 데이터를 memo에 저장해주었습니다.
                String msg = "저장 성공: " + title;//Toast message 로 출력할 String
                memo.setTitle(title);
                memo.setContent(content);
                Toast.makeText(MemoActivity.this, msg, Toast.LENGTH_LONG).show();

                //보모 Activity에게 memo의 값을 반환했습니다.
                Intent intent = new Intent();
                intent.putExtra("MEMO", memo);//객체를 반환할 때는, 해당 객체에 Serializable마킹 인터페이스가 되어 있는지 확인해야 합니다.
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        };
        button.setOnClickListener(listener);
    }

    //문자열이 비어있는지 체크하는 함수입니다.
    static boolean isEmptyOrWhiteSpace(String s) {
        if (s == null) return true;
        return s.toString().trim().length() == 0;
    }
}
