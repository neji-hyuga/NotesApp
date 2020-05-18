package com.alura.notesapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.notesapp.R;
import com.alura.notesapp.dao.NoteDAO;
import com.alura.notesapp.model.Notes;
import com.alura.notesapp.ui.reciclerview.adapter.NotesRecyclerAdapter;
import com.alura.notesapp.ui.reciclerview.adapter.listener.onItemClickListener;

import java.util.List;

import static com.alura.notesapp.ui.activity.Constants.NOTE_KEY;
import static com.alura.notesapp.ui.activity.Constants.REQUEST_CODE_INSERT_NOTE;
import static com.alura.notesapp.ui.activity.Constants.RESULT_CODE_CREATED_NOTE;

public class NotesListActivity extends AppCompatActivity {

    private NotesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        List<Notes> allNotes = getAllNotes();
        setUpRecyclerView(allNotes);
        setUpInsertNote();
    }

    private void setUpInsertNote() {
        TextView insertNote = findViewById(R.id.activity_list_text_view_add_note_id);
        insertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreatesNoteActivity();
            }
        });
    }

    private void goToCreatesNoteActivity() {
        Intent intent = new Intent(NotesListActivity.this,
                CreatesNoteActivity.class);
        startActivityForResult(intent, REQUEST_CODE_INSERT_NOTE);
    }

    private List<Notes> getAllNotes() {
        NoteDAO noteDAO = new NoteDAO();
        for (int i = 0; i < 10; i++) {
            noteDAO.insertNote(
                    new Notes("title " + (i + 1),
                            "description " + (i + 1)));
        }
        return noteDAO.allNotes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (isaResultWithNote(requestCode, resultCode, data)) {
            Notes receivedNote = (Notes) data.getSerializableExtra("note");
            addNote(receivedNote);
        }

        if (requestCode == 2 && resultCode == RESULT_CODE_CREATED_NOTE && hasNote(data)) {
            Notes receivedNote = (Notes) data.getSerializableExtra(NOTE_KEY);
            Toast.makeText(this,
                    receivedNote.getTitle(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void addNote(Notes note) {
        new NoteDAO().insertNote(note);
        adapter.addNote(note);
    }

    private boolean isaResultWithNote(int requestCode, int resultCode, @Nullable Intent data) {
        return isaRequestCodeInsertNote(requestCode) &&
                isaResultCodeCreatedNote(resultCode) &&
                hasNote(data);
    }

    private boolean hasNote(@Nullable Intent data) {
        return data.hasExtra(NOTE_KEY);
    }

    private boolean isaResultCodeCreatedNote(int resultCode) {
        return resultCode == RESULT_CODE_CREATED_NOTE;
    }

    private boolean isaRequestCodeInsertNote(int requestCode) {
        return requestCode == REQUEST_CODE_INSERT_NOTE;
    }

    private void setUpRecyclerView(List<Notes> allNotes) {
        RecyclerView listNotes = findViewById(R.id.activity_list_recycler_view_notes_id);
        setUpAdapter(allNotes, listNotes);
    }

    private void setUpAdapter(List<Notes> allNotes, RecyclerView listNotes) {
        adapter = new NotesRecyclerAdapter(this, allNotes);
        listNotes.setAdapter(adapter);
        adapter.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(Notes note) {
                Intent intent = new Intent(NotesListActivity.this,
                        CreatesNoteActivity.class);
                intent.putExtra(NOTE_KEY, note);
                startActivityForResult(intent, 2);
            }
        });
    }

}
