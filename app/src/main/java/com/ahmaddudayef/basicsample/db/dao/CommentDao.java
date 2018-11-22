package com.ahmaddudayef.basicsample.db.dao;

import com.ahmaddudayef.basicsample.db.entity.CommentEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */
@Dao
public interface CommentDao {
    @Query("SELECT * FROM comments where productId = :productId")
    LiveData<List<CommentEntity>> loadComments(int productId);

    @Query("SELECT * FROM comments where productId = :productId")
    List<CommentEntity> loadCommentsSync(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CommentEntity> comments);
}
