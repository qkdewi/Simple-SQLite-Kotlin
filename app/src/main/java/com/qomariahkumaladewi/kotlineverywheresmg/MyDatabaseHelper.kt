package com.qomariahkumaladewi.kotlineverywheresmg

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseHelper private constructor(context:Context) : ManagedSQLiteOpenHelper(context, "student_database.db",null,3){
    companion object {
        private var instance: MyDatabaseHelper? = null

        fun getInstance(context: Context): MyDatabaseHelper{
            if(instance==null){
                instance = MyDatabaseHelper(context)
            }

            return instance as MyDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            StudentContract.TABLE_STUDENT,
            true,
            StudentContract.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            StudentContract.NAME to TEXT,
            StudentContract.AGE to INTEGER,
            StudentContract.ADDRESS to TEXT,
            StudentContract.PHOTO to TEXT,
            StudentContract.MAJORITY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(StudentContract.TABLE_STUDENT, true)
    }

}

val Context.database: MyDatabaseHelper
get() = MyDatabaseHelper.getInstance(applicationContext)