package br.ufjf.dcc196.exerciciopersistencia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnRemove;
    private RecyclerView rvLista;
    private TextView txtSerie;
    private TextView txtTemp;
    private TextView txtEp;
    private LembreteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btn_Add);
        btnRemove = (Button) findViewById(R.id.btn_Remover);
        rvLista = (RecyclerView) findViewById(R.id.rv_lista);
        txtSerie = (TextView) findViewById(R.id.txtSerie);
        txtTemp = (TextView) findViewById(R.id.txt_Temp);
        txtEp = (TextView) findViewById(R.id.txt_Ep);
        dbHelper = new LembreteDBHelper(getApplicationContext());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(LembreteContract.Lembrete.COLUMN_NAME_SERIE, String.valueOf(txtSerie.getText()));
                valores.put(LembreteContract.Lembrete.COLUMN_NAME_TEMPORADA, "Temp:  " + txtTemp.getText());
                valores.put(LembreteContract.Lembrete.COLUMN_NAME_EPISODIO, "Ep: " + txtEp.getText());
                long id = db.insert(LembreteContract.Lembrete.TABLE_NAME, null, valores);
                Log.i("DBINFO", "registro criado com id: " + id );
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
