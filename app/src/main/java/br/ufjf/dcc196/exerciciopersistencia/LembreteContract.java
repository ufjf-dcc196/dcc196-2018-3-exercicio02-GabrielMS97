package br.ufjf.dcc196.exerciciopersistencia;

import android.provider.BaseColumns;

public final class LembreteContract {
    public final class Lembrete implements BaseColumns {
        public final static String TABLE_NAME = "Lembrete";
        public final static String COLUMN_NAME_SERIE = "serie";
        public final static String COLUMN_NAME_TEMPORADA = "temporada";
        public final static String COLUMN_NAME_EPISODIO = "episodio";
        public final static String CREATE_LEMBRETE = "CREATE TABLE " + Lembrete.TABLE_NAME + " ("
                + Lembrete._ID + " INTEGER, " + Lembrete.COLUMN_NAME_SERIE + " TEXT, " + Lembrete.COLUMN_NAME_TEMPORADA
                + " INTEGER, " + Lembrete.COLUMN_NAME_EPISODIO + " INTEGER " + ")";
        public final static String DROP_Lembrete = "DROP TABLE IF EXISTS" + Lembrete.TABLE_NAME;
    }
}
