package com.rizieq.androidcrudvolley.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizieq.androidcrudvolley.InsertData;
import com.rizieq.androidcrudvolley.R;
import com.rizieq.androidcrudvolley.model.ModelData;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterData(List<ModelData> items, Context context) {
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_row,viewGroup,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int i) {
        ModelData md = mItems.get(i);
        holderData.tvNama.setText(md.getNama());
        holderData.tvNpm.setText(md.getNpm());
        holderData.tvProdi.setText(md.getProdi());
        holderData.tvFakultas.setText(md.getFakultas());


        holderData.md = md;

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvNama,tvNpm, tvProdi, tvFakultas;
        ModelData md;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.nama);
            tvNpm = itemView.findViewById(R.id.npm);
            tvProdi = itemView.findViewById(R.id.prodi);
            tvFakultas = itemView.findViewById(R.id.fakultas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update = new Intent(context, InsertData.class);
                    update.putExtra("update",1);
                    update.putExtra("npm",md.getNpm());
                    update.putExtra("nama",md.getNama());
                    update.putExtra("fakultas",md.getFakultas());
                    update.putExtra("prodi",md.getProdi());

                    context.startActivity(update);
                }
            });

        }
    }
}
