package net.skhu.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Item memo;
    final static int MEMO = 0;
    final static int SIGN_UP = 1;

    //Activity class가 만들어질 때 자동으로 생성됨.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memo = new Item("", "");
    }

    //메뉴 만들 때의 리스너, 메뉴 다시 그릴 때에도 이 함수 호출된다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//layout inflation 해당 .xml 대로 메뉴 그려주는 것
        return true;
    }

    //메뉴의 아이템 클릭되었을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_signUp) {//signUp 메뉴 클릭되면, 해당 Activity로 넘어가도록 했습니다.
            Intent intent = new Intent(this, SignupActivity.class);
            //startActivity(intent);
            startActivityForResult(intent, SIGN_UP);

            return true;
        } else if (id == R.id.action_memo) {//memo 메뉴 클릭되면, 해당 Activity로 넘어가도록 했습니다.
            Intent intent = new Intent(this, MemoActivity.class);
            //intent.putExtra("title", memo.getTitle());
            //intent.putExtra("content", memo.getContent());
            intent.putExtra("MEMO", memo);//객체를 MemoActivity에 전달해주었습니다.
            startActivityForResult(intent, MEMO);
            return true;
        }
        else if (id == R.id.action_buttons) {//buttons 메뉴 클릭되면, 해당 Activity로 넘어가도록 했습니다.
            Intent intent = new Intent(this, ButtonsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_checkboxes) {//checkboxes 메뉴 클릭되면, 해당 Activity로 넘어가도록 했습니다.
            Intent intent = new Intent(this, CheckboxesActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_spinners) {//spinners 메뉴 클릭되면, 해당 Activity로 넘어가도록 했습니다.
            Intent intent = new Intent(this, SpinnersActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_alerts) {//alerts 메뉴 클릭되면, 해당 Activity로 넘어가도록 했습니다.
            Intent intent = new Intent(this, AlertsActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //Activity로부터 리턴 받는 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == MEMO && resultCode == Activity.RESULT_OK)//MEMO에서 RESULT_OK로 리턴될 때
        {
            Bundle extras = intent.getExtras();//해당 메모를 MainActivity에 저장해주었습니다.
            Item item = (Item)intent.getSerializableExtra("MEMO");//객체를 반환 받아주었습니다. 해당 객체에 Serializable마킹 인터페이스가 되어 있는지 확인해야 합니다.
            if(extras != null)
            {
                memo.setTitle(item.getTitle());
                memo.setContent(item.getContent());
            }
        }
        else if(requestCode == SIGN_UP && resultCode == Activity.RESULT_OK){//SIGN_UP에서 RESULT_OK로 리턴될 때
            /*Intent intent2 = new Intent(this, MemoActivity.class);
            intent2.putExtra("title", memo.getTitle());
            intent2.putExtra("content", memo.getContent());
            startActivityForResult(intent2, MEMO);*/
        }
    }
}
