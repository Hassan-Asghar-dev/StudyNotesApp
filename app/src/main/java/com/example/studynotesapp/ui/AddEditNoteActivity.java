package com.example.studynotesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studynotesapp.R;
import com.example.studynotesapp.data.AppDatabase;
import com.example.studynotesapp.data.Note;

public class AddEditNoteActivity extends AppCompatActivity {

    EditText title, content;
    AppDatabase db;
    int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        title = findViewById(R.id.edtTitle);
        content = findViewById(R.id.edtContent);
        db = AppDatabase.getInstance(this);

        Intent i = getIntent();
        if (i.hasExtra("id")) {
            noteId = i.getIntExtra("id", -1);
            title.setText(i.getStringExtra("title"));
            content.setText(i.getStringExtra("content"));
        }

        findViewById(R.id.btnSave).setOnClickListener(v -> saveNote());
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteNote());
    }

    private void saveNote() {
        if (noteId == -1) {
            db.noteDao().insert(new Note(title.getText().toString(), content.getText().toString()));
        } else {
            Note n = new Note(title.getText().toString(), content.getText().toString());
            n.id = noteId;
            db.noteDao().update(n);
        }
        finish();
    }

    private void deleteNote() {
        if (noteId != -1) {
            Note n = new Note("", "");
            n.id = noteId;
            db.noteDao().delete(n);
        }
        finish();
    }
}