package com.example.studynotesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studynotesapp.R;
import com.example.studynotesapp.adapter.NoteAdapter;
import com.example.studynotesapp.data.AppDatabase;
import com.example.studynotesapp.data.Note;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    RecyclerView recyclerView;
    SearchView searchView;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadNotes();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Note> filtered = db.noteDao().searchNotes(newText);
                adapter = new NoteAdapter(filtered, note -> openEdit(note));
                recyclerView.setAdapter(adapter);
                return true;
            }
        });

        findViewById(R.id.btnAdd).setOnClickListener(v ->
                startActivity(new Intent(this, AddEditNoteActivity.class)));
    }

    private void loadNotes() {
        List<Note> notes = db.noteDao().getAllNotes();
        adapter = new NoteAdapter(notes, note -> openEdit(note));
        recyclerView.setAdapter(adapter);
    }

    private void openEdit(Note note) {
        Intent i = new Intent(this, AddEditNoteActivity.class);
        i.putExtra("id", note.id);
        i.putExtra("title", note.title);
        i.putExtra("content", note.content);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }
}