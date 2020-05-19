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
import com.alura.notesapp.ui.reciclerview.adapter.listener.onItemClickListener;

import java.util.Collections;
import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.NotesViewHolder> {

    final private List<Notes> notesList;
    final private Context context;
    private onItemClickListener onItemClickListener;

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

    public void setOnItemClickListener(com.alura.notesapp.ui.reciclerview.adapter.listener.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void changeNote(int i, Notes note) {
        notesList.set(i, note);
        notifyItemChanged(i);
    }

    public void delete(int i) {
        notesList.remove(i);
        notifyItemRemoved(i); // smooth effect on delete
    }

    public void changePosition(int startPosition, int finalPosition) {
        Collections.swap(notesList, startPosition, finalPosition);
        notifyItemMoved(startPosition, finalPosition); // smooth effect and allow a item change position with any another item in the list
    }

    class NotesViewHolder extends RecyclerView.ViewHolder { // inner class to creates view holder

        private final TextView titleTextView;
        private final TextView descriptionTextView;
        private Notes note;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.item_note_title_id);
            descriptionTextView = itemView.findViewById(R.id.item_note_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(note, getAdapterPosition());
                }
            });
        }

        public void attachesNote(Notes note) {
            this.note = note;
            titleTextView.setText(note.getTitle());
            descriptionTextView.setText(note.getDescription());
        }
    }

    public void addNote(Notes note) {
        notesList.add(note);
        notifyDataSetChanged();
    }
}
