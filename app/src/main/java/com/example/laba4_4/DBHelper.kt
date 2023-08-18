package com.example.laba4_4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    context: Context) :
    SQLiteOpenHelper(context, name, null, version) {
    companion object{
        private const val name = "dblab4"
        private const val version = 2
    }

    private var TABLE_CONTACTS = "myDB"

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("myLogs", "---On create database---")
        db?.execSQL("create table user ("
                + "id integer primary key autoincrement,"
                + "login text,"
                + "password text,"
                + "plus integer,"
                + "minus integer,"
                + "multiplication integer,"
                + "division integer" + ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists $TABLE_CONTACTS")
        onCreate(db)
    }
}