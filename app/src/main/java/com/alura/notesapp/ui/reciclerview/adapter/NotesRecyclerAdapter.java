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

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.NotesViewHolder> {

    final private List<Notes> notesList;
    final private Context context;

    public NotesRecyclerAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.notesList = notes;
    }

    @NonNull
    @Override
    public NotesRecyclerAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(context)
                .inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerAdapter.NotesViewHolder holder, int i) { // creates views
        Notes note = notesList.get(i);
        holder.attachesNote(note);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder { // inner class to creates view holder

        private final TextView titleTextView;
        private final TextView descriptionTextView;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_note_title_id);
            descriptionTextView = itemView.findViewById(R.id.item_note_description);
        }

        public void attachesNote(Notes note) {
            titleTextView.setText(note.getTitle());
            descriptionTextView.setText(note.getDescription());
        }
    }

    public void addNote(Notes note) {
        notesList.add(note);
        notifyDataSetChanged();
    }
}
