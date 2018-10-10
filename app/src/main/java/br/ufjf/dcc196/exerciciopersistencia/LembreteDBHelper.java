package br.ufjf.dcc196.exerciciopersistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LembreteDBHelper extends SQLiteOpenHelper{

    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "Lembrete.db";

    public LembreteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LembreteContract.Lembrete.CREATE_LEMBRETE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LembreteContract.Lembrete.DROP_LEMBRETE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
