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
    final int MEMO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memo = new Item("", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_signUp) {
            Intent intent = new Intent(this, SignupActivity.class);
            //startActivity(intent);
            startActivityForResult(intent, 1);

            return true;
        } else if (id == R.id.action_memo) {
            Intent intent = new Intent(this, MemoActivity.class);
            intent.putExtra("title", memo.getTitle());
            intent.putExtra("content", memo.getContent());
            startActivityForResult(intent, MEMO);
            return true;
        }
        else if (id == R.id.action_buttons) {
            Intent intent = new Intent(this, ButtonsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_checkboxes) {
            Intent intent = new Intent(this, CheckboxesActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_spinners) {
            Intent intent = new Intent(this, SpinnersActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_alerts) {
            Intent intent = new Intent(this, AlertsActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == MEMO && resultCode == Activity.RESULT_OK)
        {
            Bundle extras = intent.getExtras();
            if(extras != null)
            {
                memo.setTitle(extras.getString("title"));
                memo.setContent(extras.getString("content"));
            }
        }
        else if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Intent intent2 = new Intent(this, MemoActivity.class);
            intent2.putExtra("title", memo.getTitle());
            intent2.putExtra("content", memo.getContent());
            startActivityForResult(intent2, MEMO);
        }
    }
}
