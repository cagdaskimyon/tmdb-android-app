package com.cagdaskimyon.mobven.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbOpenHelper(context: Context) : SQLiteOpenHelper(context, "mobven", null, 1) {
    companion object {
        private var instance: DbOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DbOpenHelper {
            if (instance == null) {
                instance =
                    DbOpenHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + "favorite" + " (\n" +
                " " + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " " + "movie_id" + " INTEGER\n" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //onUpgrade
    }

}

val Context.database: DbOpenHelper
    get() = DbOpenHelper.getInstance(applicationContext)
