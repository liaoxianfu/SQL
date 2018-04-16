package cn.democpp.www.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MySqlLite sqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlLite = new MySqlLite(this, "Person.db", null, 2);

        Button database = (Button) findViewById(R.id.btn1);
        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlLite.getWritableDatabase();
            }
        });
        Button button = (Button) findViewById(R.id.btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertValue(sqlLite);
            }
        });

        Button button1 = (Button) findViewById(R.id.btn3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateValue(sqlLite);
            }
        });

        Button button2 = (Button) findViewById(R.id.btn4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteValue(sqlLite);
            }
        });

        Button button3 = (Button) findViewById(R.id.btn5);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchValue(sqlLite);
            }
        });
    }

    //数据插入
    private void insertValue(MySqlLite sqlLite) {
        SQLiteDatabase db = sqlLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "zhangsan");
        values.put("number", "100");
        db.insert("Person", null, values);
        values.clear();

        values.put("name", "lisi");
        values.put("number", "100");
        db.insert("Person", null, values);
        values.clear();

        values.put("category_name", "perpetual");
        values.put("category_code", 2);
        db.insert("category", null, values);
        values.clear();
        Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();

    }

    // 数据更新
    private void updateValue(MySqlLite mySqlLite) {
        SQLiteDatabase db = mySqlLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "李四");
        db.update("Person", values, "name=?", new String[]{"zhangsan"});
        Toast.makeText(this, "更新数据成功", Toast.LENGTH_SHORT).show();
    }

    //数据删除
    private void deleteValue(MySqlLite mySqlLite) {
        SQLiteDatabase db = mySqlLite.getWritableDatabase();
        db.delete("Person", "name=?", new String[]{"zhangsan"});
        Toast.makeText(this, "删除数据成功", Toast.LENGTH_SHORT).show();

    }
    // 数据查询

    private void searchValue(MySqlLite mySqlLite) {
        SQLiteDatabase db = mySqlLite.getWritableDatabase();
        Cursor cursor = db.query("Person", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                Log.d("Info", name + "  " + number);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


}
