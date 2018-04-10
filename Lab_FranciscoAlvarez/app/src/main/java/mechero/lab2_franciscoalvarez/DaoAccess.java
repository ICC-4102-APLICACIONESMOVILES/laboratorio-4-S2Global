package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertOnlySinglePoll (Poll polls);
    @Insert
    void insertMultiplePolls (List<Poll> pollList);
    @Query("SELECT * FROM Poll WHERE pollId = :pollId")
    Poll fetchOnePollbyPollId (int pollId);
    @Update
    void updatePoll (Poll movies);
    @Delete
    void deletePoll (Poll movies);
}





