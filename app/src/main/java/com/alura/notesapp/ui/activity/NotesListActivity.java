package com.alura.notesapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.alura.notesapp.R;
import com.alura.notesapp.adapter.NotesListAdapter;
import com.alura.notesapp.dao.NoteDAO;
import com.alura.notesapp.model.Notes;

import java.util.List;

public class NotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        ListView listNotes = findViewById(R.id.activity_list_list_view_notes_id);

        NoteDAO noteDAO = new NoteDAO();
        noteDAO.insertNote(new Notes("first",
                "first eh?!"));
        List<Notes> allNotes = noteDAO.allNotes();

        listNotes.setAdapter(new NotesListAdapter(this, allNotes));
    }
}
