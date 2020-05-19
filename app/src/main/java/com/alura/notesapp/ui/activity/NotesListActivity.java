package com.alura.notesapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.notesapp.R;
import com.alura.notesapp.dao.NoteDAO;
import com.alura.notesapp.model.Notes;
import com.alura.notesapp.ui.reciclerview.adapter.NotesRecyclerAdapter;
import com.alura.notesapp.ui.reciclerview.adapter.listener.onItemClickListener;
import com.alura.notesapp.ui.reciclerview.helper.callback.NoteItemTouchCallBack;

import java.util.List;

import static com.alura.notesapp.ui.activity.Constants.INVALID_POSITION;
import static com.alura.notesapp.ui.activity.Constants.NOTE_KEY;
import static com.alura.notesapp.ui.activity.Constants.POSITION;
import static com.alura.notesapp.ui.activity.Constants.REQUEST_CODE_INSERT_NOTE;
import static com.alura.notesapp.ui.activity.Constants.REQUEST_CODE_MODIFY_NOTE;

public class NotesListActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "notes : )";
    private NotesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        setTitle(TITLE_APPBAR);

        List<Notes> allNotes = getAllNotes();
        setUpRecyclerView(allNotes);
        setUpInsertNote();
    }

    private void setUpInsertNote() {
        TextView insertNote = findViewById(R.id.activity_list_text_view_add_note_id);
        insertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToCreatesNoteActivity();
            }
        });
    }

    private void sendToCreatesNoteActivity() {
        Intent intent = new Intent(NotesListActivity.this,
                CreatesNoteActivity.class);
        startActivityForResult(intent, REQUEST_CODE_INSERT_NOTE);
    }

    private List<Notes> getAllNotes() {
        NoteDAO noteDAO = new NoteDAO();
//        for (int i = 0; i < 10; i++) {
//            noteDAO.insertNote(
//                    new Notes("title " + (i + 1),
//                            "description " + (i + 1)));
//        }
        return noteDAO.allNotes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (isaResultInsertNote(requestCode, data)) {
            if (resultOk(resultCode)) {
                Notes receivedNote = (Notes) data.getSerializableExtra(NOTE_KEY);
                addNote(receivedNote);
            }
        }

        if (isaResultToModifyNote(requestCode, data)) {
            if (resultOk(resultCode)) {
                Notes receivedNote = (Notes) data.getSerializableExtra(NOTE_KEY);
                int receivedPosition = data.getIntExtra(POSITION, INVALID_POSITION);
                if (isaValidPosition(receivedPosition)) {
                    modifyNote(receivedNote, receivedPosition);
                }
            }

        }

    }

    private void modifyNote(Notes note, int i) {
        new NoteDAO().modifyNote(i, note);
        adapter.changeNote(i, note);
    }

    private boolean isaValidPosition(int receivedPosition) {
        return receivedPosition > INVALID_POSITION;
    }

    private boolean isaResultToModifyNote(int requestCode, Intent data) {
        return isaRequestedCodeModifyNote(requestCode) &&
                hasNote(data);
    }

    private boolean isaRequestedCodeModifyNote(int requestCode) {
        return requestCode == REQUEST_CODE_MODIFY_NOTE;
    }

    private void addNote(Notes note) {
        new NoteDAO().insertNote(note);
        adapter.addNote(note);
    }

    private boolean isaResultInsertNote(int requestCode, @Nullable Intent data) {
        return isaRequestCodeInsertNote(requestCode) &&
                hasNote(data);
    }

    private boolean hasNote(@Nullable Intent data) {
        return data != null && data.hasExtra(NOTE_KEY);
    }

    private boolean resultOk(int resultCode) {
        return resultCode == Activity.RESULT_OK;
    }

    private boolean isaRequestCodeInsertNote(int requestCode) {
        return requestCode == REQUEST_CODE_INSERT_NOTE;
    }

    private void setUpRecyclerView(List<Notes> allNotes) {
        RecyclerView listNotes = findViewById(R.id.activity_list_recycler_view_notes_id);
        setUpAdapter(allNotes, listNotes);
        setUpTouchHelper(listNotes);
    }

    private void setUpTouchHelper(RecyclerView listNotes) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NoteItemTouchCallBack(adapter));
        itemTouchHelper.attachToRecyclerView(listNotes);
    }

    private void setUpAdapter(List<Notes> allNotes, RecyclerView listNotes) {
        adapter = new NotesRecyclerAdapter(this, allNotes);
        listNotes.setAdapter(adapter);
        adapter.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(Notes note, int i) {
                goesToModifyNote(note, i);
            }
        });
    }

    private void goesToModifyNote(Notes note, int i) {
        Intent intent = new Intent(NotesListActivity.this,
                CreatesNoteActivity.class);
        intent.putExtra(NOTE_KEY, note);
        intent.putExtra(POSITION, i);
        startActivityForResult(intent, REQUEST_CODE_MODIFY_NOTE);
    }

}
