package com.alura.notesapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.notesapp.R;
import com.alura.notesapp.adapter.NotesListAdapter;
import com.alura.notesapp.dao.NoteDAO;
import com.alura.notesapp.model.Notes;
import com.alura.notesapp.ui.reciclerview.adapter.NotesRecyclerAdapter;

import java.util.List;

public class NotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        RecyclerView listNotes = findViewById(R.id.activity_list_recycler_view_notes_id);

        NoteDAO noteDAO = new NoteDAO();
        noteDAO.insertNote(new Notes("first",
                "first eh?!"));
        List<Notes> allNotes = noteDAO.allNotes();

        listNotes.setAdapter(new NotesRecyclerAdapter(this, allNotes));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listNotes.setLayoutManager(layoutManager);

    }
}
