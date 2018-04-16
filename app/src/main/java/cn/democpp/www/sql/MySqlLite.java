package cn.democpp.www.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySqlLite extends SQLiteOpenHelper {

    private Context mycontext;
    public MySqlLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mycontext = context;
    }

    //数据库第一次调用时所用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Person(" +
                "id integer primary key autoincrement," +
                "name varchar(20)," +
                "number varchar(20))"
                );
        sqLiteDatabase.execSQL("create table category(\n" +
                "    id integer primary key autoincrement,\n" +
                "    category_name text,\n" +
                "    category_code integer\n" +
                ")");
        Toast.makeText(mycontext,"创建成功",Toast.LENGTH_SHORT).show();
    }

    //数据库更新用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists Person");
            sqLiteDatabase.execSQL("drop table if exists category");
            onCreate(sqLiteDatabase);
    }
}
