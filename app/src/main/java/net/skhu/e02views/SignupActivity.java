package net.skhu.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    //Activity class가 만들어질 때 자동으로 생성됨.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);//해당 layout형태로 Activity만듦

        Button button = (Button)findViewById(R.id.button);//버튼객체를 findViewById로 찾아냄
        View.OnClickListener listener = new View.OnClickListener() {//버튼이 클릭될 떄의 리스너
            @Override
            public void onClick(View view) {
                //ID에 대한 EditText 입니다.
                EditText editText_loginId = (EditText)findViewById(R.id.editText_loginId);//해당 EditText 객체를 찾아주었습니다.
                String loginId = editText_loginId.getText().toString();//EditText의 문자열을 저장했습니다. 여기서 중요한 건 toString입니다.
                if (isEmptyOrWhiteSpace(loginId))//loginId 문자열이 비어있는지 체크해주었습니다.
                    editText_loginId.setError("로그인 아이디를 입력하세요");//해당 EditText에 에러 표시 나오게 해주었습니다.

                //password 대한 EditText 입니다.
                EditText editText_password = (EditText)findViewById(R.id.editText_password);//해당 EditText 객체를 찾아주었습니다.
                String password = editText_password.getText().toString();//EditText의 문자열을 저장했습니다. 여기서 중요한 건 toString입니다.
                if (isEmptyOrWhiteSpace(password))//password 문자열이 비어있는지 체크해주었습니다.
                    editText_password.setError("비밀번호를 입력하세요");//해당 EditText에 에러 표시 나오게 해주었습니다.

                //password 확인 대한 EditText 입니다.
                EditText editText_password2 = (EditText)findViewById(R.id.editText_password2);//해당 EditText 객체를 찾아주었습니다.
                String password2 = editText_password2.getText().toString();//EditText의 문자열을 저장했습니다. 여기서 중요한 건 toString입니다.
                if (password.equals(password2) == false)//password2 문자열이 비어있는지 체크해주었습니다.
                    editText_password2.setError("비밀번호가 일치하지 않습니다");//해당 EditText에 에러 표시 나오게 해주었습니다.

                //email 확인 대한 EditText 입니다.//이메일은 필수 사항이 아니라서, 문자열이 비었는지 여부를 확인해 주지 않았습니다.
                EditText editText_email = (EditText)findViewById(R.id.editText_email);//해당 EditText 객체를 찾아주었습니다.
                String email = editText_email.getText().toString();//EditText의 문자열을 저장했습니다. 여기서 중요한 건 toString입니다.

                // 회원 가입 데이터를 서버에 전송하는 코드를 구현해야 함.

                //회원가입 성공에 대한 Toast message 를 화면에 출력해주었습니다.
                String msg = "회원가입 성공: " + loginId + " " + email;
                Toast.makeText(SignupActivity.this, msg, Toast.LENGTH_LONG).show();

                //Intent intent = new Intent();
                //setResult(Activity.RESULT_OK, intent);

                //회원가입이 끝나면, 바로 MemoActivity로 넘어가도록 해주었습니다.
                //여기서 중요한 것은 outer 클래스의 this와 inner class의 this 차이를 구분하기 위해서, SignupActivity.this를 사용했다는 것입니다.
                Intent intent = new Intent(SignupActivity.this, MemoActivity.class);
                startActivity(intent);

                finish();//해당 Activity를 종료해주었습니다.
            }
        };
        button.setOnClickListener(listener);
    }

    //문자열이 비어있는지 체크하는 함수입니다.
    static boolean isEmptyOrWhiteSpace(String s) {
        if (s == null) return true;
        return s.trim().length() == 0;
    }
}

