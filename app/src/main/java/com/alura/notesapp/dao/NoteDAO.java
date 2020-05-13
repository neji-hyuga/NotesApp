package com.alura.notesapp.dao;

import com.alura.notesapp.model.Notes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NoteDAO {
    private final static ArrayList<Notes> notes = new ArrayList<>();

    public List<Notes> allNotes() {
        return (List<Notes>) notes.clone();
    }

    public void insertNote(Notes... notes) {
        NoteDAO.notes.addAll(Arrays.asList(notes));
    }

    public void modifyNote(int i, Notes note){
        notes.set(i, note);
    }

    public void deleteNote(int i){
        notes.remove(i);
    }

    public void changeNotePosition(int startPosition, int finalPosition){
        Collections.swap(notes, startPosition, finalPosition);
    }

    public void clearAll(){
        notes.clear();
    }

}
