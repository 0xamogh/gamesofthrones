package amoghjapps.com.gamesofthroneslast;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private RetroFitInterf RetroFitInstance;
    Data character;
    ListView list;
    TextView n;
    ImageView imageView;
    String m;
    public int isOK;
    ArrayList<Data> history;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    private static final String DATABASE_NAME = "char_db";
    private AppDatabase CharDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activty);
        list = findViewById(R.id.det);
        imageView = findViewById(R.id.image);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle("Name");
        isOK =0;


        CharDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME)
                .build();

        Intent intent = getIntent();
        final String message = intent.getStringExtra("name");
        m=message;
        int i = intent.getIntExtra("isHistory", 0);

        if (i == 0)
        {

            RetroFitInstance = GotApiHandler.getGOTService();
            Call<ResponseModel> call = GotApiHandler.getGOTService().getCharacter(message);
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response)
                {
                    if(response.body().getMessage().equals("Success")) {
                        character = response.body().getData();


                        int kk = checkDB(character.getName());
                        if(kk==0)
                        {
                            isOK = 1;
                            onLoad(0);
                        }
                        else
                        {
                            isOK = 2;
                            m=character.getName();
                            imgLoad n = new imgLoad();
                            n.execute();
                        }
                    }
                    else
                    {
                        isOK =0;
                        Intent intent = new Intent();
                        setResult(0, intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {

                    isOK = 0;
                    Intent intent = new Intent();
                    setResult(0, intent);
                    finish();
                }

            });
        }
        else if(i==1)
        {
            //Toast.makeText(this, "Accessing from Database...", Toast.LENGTH_SHORT).show();
            imgLoad n = new imgLoad();
            n.execute();
        }
    }

    private class imgLoad extends AsyncTask<String, Integer, Long>
    {
        protected Long doInBackground(String... t)
        {
            Long i = 0L;
            character = CharDatabase.cDAO().getspecific(m);

            return i;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            onLoad(1);


        }
    }
    public void onLoad(int i)
    {
        if(i==0)
            Toast.makeText(this, "Accessing from API...", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Accessing from Database...", Toast.LENGTH_SHORT).show();
        ArrayList<StringData> attr = new ArrayList<>();
        String link = "https://api.got.show/";



        link += character.getImageLink();
        Picasso.with(DetailActivity.this).load(link).into(imageView);
        if(i==0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Data newCharacter = new Data();
                    newCharacter = character;
                    CharDatabase.cDAO().addNew(newCharacter);
                }
            }).start();
        }
        if(character.getHouse()!=null)
        {
            StringData temp=new StringData();
            temp.head="House";
            temp.sub=character.getHouse();
            attr.add(temp);
        }
        if(character.getMale()!=true)
        {
            StringData temp=new StringData();
            temp.head="Gender";
            temp.sub="Female";
            attr.add(temp);
        }
        else
        {
            StringData temp=new StringData();
            temp.head="Gender";
            temp.sub="Male";
            attr.add(temp);
        }
        if(character.getName()!=null)
        {
            mCollapsingToolbarLayout.setTitle(character.getName());
        }
        if(character.getDateOfBirth()!=null)
        {
            StringData temp=new StringData();
            temp.head="Date of Birth";
            temp.sub=String.valueOf(character.getDateOfBirth());
            attr.add(temp);
        }
        if(character.getSpouse()!=null)
        {
            StringData temp=new StringData();
            temp.head="Spouse";
            temp.sub=String.valueOf(character.getSpouse());
            attr.add(temp);
        }
        if(character.getBooks()!=null)
        {
            StringData temp=new StringData();
            temp.head="Appears in";
            List<String> a = character.getBooks();
            String tt = "";
            for(int kk = 0;kk<a.size();++kk)
                if(kk!=a.size()-1)
                    tt+=a.get(kk) + ", ";
                else
                    tt+=a.get(kk);


            temp.sub=String.valueOf(tt);
            attr.add(temp);
        }
        Adapter lis = new Adapter(this,attr);
        list.setAdapter(lis);
    }



    @Override
    public void onBackPressed()
    {
        if(isOK==1) {
            String data = character.getName();
            Intent intent = new Intent();
            intent.putExtra("newC", data);
            setResult(1, intent);
            finish();
        }
        else if(isOK == 0)
        {

            Intent intent = new Intent();
            setResult(0, intent);
            finish();
        }
        else if(isOK == 2)
        {
            Intent intent = new Intent();
            setResult(2, intent);
            finish();

        }
    }
    public int checkDB(String tt) {
        SQLiteDatabase DB = openOrCreateDatabase("data.db", MODE_PRIVATE, null);
        DB.execSQL("CREATE TABLE IF NOT EXISTS data (name VARCHAR(200))");
        String s="select * from data ";

        Cursor myCursor = DB.rawQuery(s ,null);
        int i = 0;

        while (myCursor.moveToNext()) {
            String temp = new String();
            temp = myCursor.getString(0);
            if(tt.equals(temp))
                i++;
        }

        myCursor.close();
        DB.close();
        return i;
    }

}