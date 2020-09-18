package com.example.jetpackstudy.repository.dao;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.jetpackstudy.repository.bean.Word;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
//                WordDao dao = INSTANCE.wordDao();
//                dao.deleteAll();
//                Word word = new Word("Hello");
//                dao.insert(word);
//                word = new Word("World");
//                dao.insert(word);
            });
        }
    };

    public static WordRoomDatabase getDatabase(final Context context) {
        initPath();
        Log.e("===",storagePath);
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, /*storagePath+*/"word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static String storagePath = "";

    private static String initPath() {
        if (storagePath.equals("")) {
            storagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "study" + File.separator + "jetpack";
            File f = new File(storagePath);
            if (!f.exists()) {
                f.mkdirs();
            }
        }
        return storagePath;
    }
}
