package br.ufjf.dcc196.exerciciopersistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnRemove;
    private RecyclerView rvLista;
    private EditText txtLembrete;
    private EditText txtTemp;
    private EditText txtEp;
    private LembreteDBHelper dbHelper;
    private LembreteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new LembreteDBHelper(getApplicationContext());
        rvLista = (RecyclerView) findViewById(R.id.rv_lista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LembreteAdapter(getLembretes());
        rvLista.setAdapter(adapter);

        btnAdd = (Button) findViewById(R.id.btn_Add);
        btnRemove = (Button) findViewById(R.id.btn_Remover);
        txtLembrete = (EditText) findViewById(R.id.txtSerie);
        txtTemp = (EditText) findViewById(R.id.txt_Temp);
        txtEp = (EditText) findViewById(R.id.txt_Ep);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(LembreteContract.Lembrete.COLUMN_NAME_SERIE, String.valueOf(txtLembrete.getText()));
                valores.put(LembreteContract.Lembrete.COLUMN_NAME_TEMPORADA, "Temp:  " + txtTemp.getText());
                valores.put(LembreteContract.Lembrete.COLUMN_NAME_EPISODIO, "Ep: " + txtEp.getText());
                long id = db.insert(LembreteContract.Lembrete.TABLE_NAME, null, valores);
                Log.i("DBINFO", "registro criado com id: " + id );
            }
        });

        adapter.setOnClickListener(new LembreteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                //Problema na exclusão
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String select = LembreteContract.Lembrete._ID+" = ?";
                String [] selectArgs = {String.valueOf(position)};
                db.delete(LembreteContract.Lembrete.TABLE_NAME, select, selectArgs);
                adapter.setCursor(getLembretes());
                adapter.notifyItemRemoved(position);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Cursor getLembretes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String []visao = {
                LembreteContract.Lembrete.COLUMN_NAME_SERIE,
                LembreteContract.Lembrete.COLUMN_NAME_TEMPORADA,
                LembreteContract.Lembrete.COLUMN_NAME_EPISODIO,
        };
        String sort = LembreteContract.Lembrete.COLUMN_NAME_SERIE+ " ASC";
        return db.query(LembreteContract.Lembrete.TABLE_NAME, visao,null,null,null,null, sort);
    }
}
