package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Poll.class}, version = 1, exportSchema = false)
public abstract class PollDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}