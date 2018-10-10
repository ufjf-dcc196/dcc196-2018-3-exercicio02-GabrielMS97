package br.ufjf.dcc196.exerciciopersistencia;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.ufjf.dcc196.exerciciopersistencia.R;

class LembreteAdapter extends RecyclerView.Adapter<LembreteAdapter.ViewHolder>{

    private Cursor cursor;

    public LembreteAdapter(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lembreteView = inflater.inflate(R.layout.lembrete_layout, parent, false);
        ViewHolder holder = new ViewHolder(lembreteView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int idxSerie = cursor.getColumnIndexOrThrow(LembreteContract.Lembrete.COLUMN_NAME_SERIE);
        int idxTemporada = cursor.getColumnIndexOrThrow(LembreteContract.Lembrete.COLUMN_NAME_TEMPORADA);
        int idxEpisodio = cursor.getColumnIndexOrThrow(LembreteContract.Lembrete.COLUMN_NAME_EPISODIO);
        String titulo = cursor.getString(idxSerie);
        String autor = cursor.getString(idxTemporada);
        Integer ano = cursor.getInt(idxEpisodio);
        cursor.moveToPosition(position);
        holder.txtTempLembrete.setText(cursor.getString(idxTemporada));
        holder.txtSerieLembrete.setText(cursor.getString(idxSerie));
        holder.txtEpLembrete.setText(cursor.getString(idxEpisodio));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtSerieLembrete;
        public TextView txtTempLembrete;
        public TextView txtEpLembrete;

        public ViewHolder(View itemView) {
            super(itemView);
            txtEpLembrete = itemView.findViewById(R.id.txt_lembreteEp);
            txtSerieLembrete = itemView.findViewById(R.id.txt_lembreteSerie);
            txtTempLembrete = itemView.findViewById(R.id.txt_lembreteTemp);
        }
    }
}
