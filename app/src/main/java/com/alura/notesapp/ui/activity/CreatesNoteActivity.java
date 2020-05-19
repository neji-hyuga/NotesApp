package com.alura.notesapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.notesapp.R;
import com.alura.notesapp.model.Notes;

import static com.alura.notesapp.ui.activity.Constants.INVALID_POSITION;
import static com.alura.notesapp.ui.activity.Constants.NOTE_KEY;
import static com.alura.notesapp.ui.activity.Constants.POSITION;

public class CreatesNoteActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "add a new note : D";
    public static final String APPBAR_TITLE_EDIT_NOTE = "edit note ; )";
    private int receivedPosition = INVALID_POSITION;
    private TextView titleTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creates_note);
        initializesFields();

        setTitle(APPBAR_TITLE);


        Intent receivedData = getIntent();
        if (receivedData.hasExtra(NOTE_KEY)) {
            setTitle(APPBAR_TITLE_EDIT_NOTE);
            Notes receivedNote = (Notes) receivedData
                    .getSerializableExtra(NOTE_KEY);
            receivedPosition = receivedData.getIntExtra(POSITION, INVALID_POSITION);
            fillFields(receivedNote);
        }
    }

    private void fillFields(Notes receivedNote) {
        titleTextView.setText(receivedNote.getTitle());
        descriptionTextView.setText(receivedNote.getDescription());
    }

    private void initializesFields() {
        titleTextView = findViewById(R.id.creates_activity_edit_text_note_title_id);
        descriptionTextView = findViewById(R.id.creates_activity_edit_text_note_description_id);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (isaMenuSaveNote(item)) {
            Notes createdNote = createsNote();
            returnNote(createdNote);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnNote(Notes note) {
        Intent intent = new Intent();
        intent.putExtra(NOTE_KEY, note);
        intent.putExtra(POSITION, receivedPosition);
        setResult(Activity.RESULT_OK, intent);
    }

    private Notes createsNote() {
        EditText titleEditText = findViewById(R.id.creates_activity_edit_text_note_title_id);
        EditText descriptionEditText = findViewById(R.id.creates_activity_edit_text_note_description_id);
        return new Notes(titleEditText.getText().toString(),
                descriptionEditText.getText().toString());
    }

    private boolean isaMenuSaveNote(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_saved_note;
    }
}
