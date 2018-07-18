package amoghjapps.com.gamesofthroneslast;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import amoghjapps.com.gamesofthroneslast.Data;

public class MainActivity extends AppCompatActivity {

    Button button;
    AutoCompleteTextView textView;
    ArrayList<String> stroredChars = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.name);
        int kk = checkDB();
        if(kk!=0)
            stroredChars = getData();

        textView.setThreshold(0);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,stroredChars);
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String arrayList= (String)adapterView.getItemAtPosition(i);
                Toast.makeText(MainActivity.this, arrayList, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DetailActivity.class );
                intent.putExtra("name",arrayList);
                intent.putExtra("isHistory",1);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                String arrayList= textView.getText().toString();
                Intent intent = new Intent(MainActivity.this,DetailActivity.class );
                intent.putExtra("name",arrayList);
                intent.putExtra("isHistory",0);
                startActivityForResult(intent,1);
            }
        });


    }
    public void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1)
        {
            if(resultCode == 1)
            {
                stroredChars.add(data.getStringExtra("newC"));
                stroredChars = clearReplicas(stroredChars);
                //Toast.makeText(this, data.getStringExtra("newC"), Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,stroredChars);
                textView.setAdapter(adapter);

            }
            else if(resultCode == 0)
            {
                Toast.makeText(this, "There was an error", Toast.LENGTH_SHORT).show();
            }



        }
    }

    public void storeDB(ArrayList<String> arrayList) {
        SQLiteDatabase sqliteDatabase = openOrCreateDatabase("data.sqliteDatabase", MODE_PRIVATE, null);
        sqliteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data (name VARCHAR(200))");
        int i;
        sqliteDatabase.execSQL("delete from data");
        for (i = 0; i < arrayList.size(); ++i) {
            ContentValues row1 = new ContentValues();
            row1.put("name", arrayList.get(i));
            sqliteDatabase.insert("data", null, row1);
        }
        sqliteDatabase.close();
    }

    public ArrayList<String> getData() {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase sqliteDatabase = openOrCreateDatabase("data.sqliteDatabase"
                , MODE_PRIVATE, null);
        Cursor myCursor = sqliteDatabase.rawQuery("select * from data", null);
        int i = 0;
        String temp = null;
        while (myCursor.moveToNext()) {
            temp = new String();
            temp = myCursor.getString(0);
            arrayList.add(temp);
            i++;
        }

        myCursor.close();
        sqliteDatabase.close();
        return arrayList;
    }

    public int checkDB() {
        SQLiteDatabase sqliteDatabase = openOrCreateDatabase("data.sqliteDatabase", MODE_PRIVATE, null);
        sqliteDatabase.execSQL("CREATE TABLE IF NOT EXISTS data (name VARCHAR(200))");

        Cursor myCursor = sqliteDatabase.rawQuery("select * from data", null);
        int counter = 0;

        while (myCursor.moveToNext()) {
            counter++;
        }

        myCursor.close();
        sqliteDatabase.close();
        return counter;
    }
    public ArrayList<String> clearReplicas(ArrayList<String> list)
    {

        List<String> arrayList = new ArrayList<>(list);
        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(arrayList);
        arrayList.clear();
        arrayList.addAll(hashSet);
        list = new ArrayList<>(arrayList);

        storeDB(list);

        return list;

    }
}

