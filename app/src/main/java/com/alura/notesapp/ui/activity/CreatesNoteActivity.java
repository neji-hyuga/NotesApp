package com.alura.notesapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.notesapp.R;
import com.alura.notesapp.model.Notes;

import static com.alura.notesapp.ui.activity.Constants.NOTE_KEY;
import static com.alura.notesapp.ui.activity.Constants.RESULT_CODE_CREATED_NOTE;

public class CreatesNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creates_note);
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
        setResult(RESULT_CODE_CREATED_NOTE, intent);
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
