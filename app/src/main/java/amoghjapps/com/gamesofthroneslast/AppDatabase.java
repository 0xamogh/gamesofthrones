package amoghjapps.com.gamesofthroneslast;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Data.class},version = 1)
@TypeConverters({TypeConv.class})

public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;
    public abstract DatabaseInterface cDAO();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

}