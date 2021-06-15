package com.example.practicacardview.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practicacardview.R;
import com.example.practicacardview.retrofill.Revista;

import java.util.List;

import retrofit2.Callback;

public class AdaptadorRevista extends RecyclerView.Adapter<AdaptadorRevista.RevistaViewHolder> {
    private Context Ctx;
    List<Revista> listrevista;

    public AdaptadorRevista(Context RevCtx,List<Revista> revista) {
        this.listrevista = revista;
        this.Ctx = RevCtx;
    }

    @NonNull
    @Override
    public RevistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View vista = inflater.inflate(R.layout.item, null);
        return new RevistaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RevistaViewHolder holder, int position) {
        Revista revista = listrevista.get(position);
        holder.textViewIssueID.setText(revista.getIssue_id());
        holder.textViewTitle.setTypeface(null, Typeface.BOLD);
        holder.textViewTitle.setText(revista.getTitle());
        holder.textViewVolume.setText(revista.getVolume());
        holder.textViewYear.setText(revista.getYear());
        holder.textViewDate.setText(revista.getDate_published());
        //Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        holder.textViewDoi.setText(Html.fromHtml("<a href=\""+revista.getDoi()+"\">"+revista.getDoi()+"</a>", Html.FROM_HTML_MODE_LEGACY));
        Glide.with(Ctx)
                .load(revista.getCover())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listrevista.size();
    }

    public class RevistaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIssueID, textViewTitle, textViewVolume,textViewYear,
                textViewDate,textViewDoi;
        ImageView imageView;

        public RevistaViewHolder(View vista) {
            super(vista);
            textViewIssueID=itemView.findViewById(R.id.TvIssue);
            textViewTitle=itemView.findViewById(R.id.TvTitle);
            textViewVolume=itemView.findViewById(R.id.TvVolume);
            textViewYear=itemView.findViewById(R.id.Tvyear);
            textViewDate=itemView.findViewById(R.id.Tvdate);
textViewDoi= itemView.findViewById(R.id.Tvdoi);
            imageView= itemView.findViewById(R.id.imageView);
        }
    }
}
