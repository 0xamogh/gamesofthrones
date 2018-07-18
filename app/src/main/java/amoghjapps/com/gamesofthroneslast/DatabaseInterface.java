package amoghjapps.com.gamesofthroneslast;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DatabaseInterface {
    @Insert(onConflict = REPLACE)
    void addNew(Data c);

    @Query("SELECT * FROM char")
    List<Data> getAll();

    @Query ("SELECT * FROM char WHERE name = :n")
    Data getspecific(String n);

    @Query ("SELECT * FROM char WHERE name = :n")
    List<Data> checkC(String n);

    //include any methods to fetch specific user
    // @Query("SELECT * FROM User")
}

