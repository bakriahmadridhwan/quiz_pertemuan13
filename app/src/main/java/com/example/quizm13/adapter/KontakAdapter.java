package com.example.quizm13.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizm13.R;
import com.example.quizm13.database.entitas.Kontak;

import java.util.List;

public class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.KontakViewHolder> {

    private List<Kontak> kontakList;
    private Context context;

    private Dialog dialog;


    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public KontakAdapter(List<Kontak> kontakList, Context context) {
        this.kontakList = kontakList;
        this.context = context;
    }

    @NonNull
    @Override
    public KontakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kontak, parent, false);

        return new KontakAdapter.KontakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontakAdapter.KontakViewHolder holder, int position) {
        holder.tvValueNama.setText(kontakList.get(position).nama);
        holder.tvValueHP.setText(kontakList.get(position).HP);
    }

    @Override
    public int getItemCount() {
        return kontakList.size();
    }

    public class KontakViewHolder extends RecyclerView.ViewHolder {
        TextView tvValueNama, tvValueHP;

        public KontakViewHolder(@NonNull View itemView) {
            super(itemView);
            tvValueNama = itemView.findViewById(R.id.tvValueNama);
            tvValueHP = itemView.findViewById(R.id.tvValueHP);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
