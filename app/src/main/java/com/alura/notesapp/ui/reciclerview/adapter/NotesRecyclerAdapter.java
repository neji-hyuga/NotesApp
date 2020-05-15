package com.alura.notesapp.ui.reciclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.notesapp.R;
import com.alura.notesapp.model.Notes;

import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter {

    private List<Notes> notes;
    private Context context;

    public NotesRecyclerAdapter(Context context,List<Notes> notes){
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) { // creates views
        Notes note = notes.get(i);
        TextView titleTextView = holder.itemView.findViewById(R.id.item_note_title_id);
        titleTextView.setText(note.getTitle());
        TextView descriptionTextView = holder.itemView.findViewById(R.id.item_note_description);
        descriptionTextView.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{ // inner class to creates view holder

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
