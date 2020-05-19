package com.alura.notesapp.ui.reciclerview.helper.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alura.notesapp.dao.NoteDAO;
import com.alura.notesapp.ui.reciclerview.adapter.NotesRecyclerAdapter;

public class NoteItemTouchCallBack extends ItemTouchHelper.Callback { // like listener, wait to do an action

    private final NotesRecyclerAdapter adapter;

    public NoteItemTouchCallBack(NotesRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, // allow movement in a view
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        int movementFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int dragFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT
                | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, movementFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, // when an component be drag, needs to be implemented
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {

        int startPosition = viewHolder.getAdapterPosition();
        int finalPosition = target.getAdapterPosition();
        changeNotes(startPosition, finalPosition);
        return true;
    }

    private void changeNotes(int startPosition, int finalPosition) {
        new NoteDAO().changeNotePosition(startPosition, finalPosition);
        adapter.changePosition(startPosition, finalPosition);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, // when a component is swipe, needs to be implemented
                         int direction) {
        int selectedNotePosition = viewHolder.getAdapterPosition();
        removeNote(selectedNotePosition);
    }

    private void removeNote(int i) {
        new NoteDAO().deleteNote(i);
        adapter.delete(i);
    }
}
