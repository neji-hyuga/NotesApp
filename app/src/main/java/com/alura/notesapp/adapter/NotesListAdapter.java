package com.alura.notesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alura.notesapp.R;
import com.alura.notesapp.model.Notes;

import java.util.List;

public class NotesListAdapter  extends BaseAdapter {

    private final Context context;
    private final List<Notes> notes;

    public NotesListAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }


    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View createdView = LayoutInflater.from(context)
                .inflate(R.layout.item_note, viewGroup, false);
        Notes note = notes.get(i);

        TextView titleTextView = createdView.findViewById(R.id.item_note_title_id);
        titleTextView.setText(note.getTitle());

        TextView descriptionTextView = createdView.findViewById(R.id.item_note_description);
        descriptionTextView.setText(note.getDescription());

        return createdView;
    }
}
