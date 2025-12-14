package com.example.studynotesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studynotesapp.R;
import com.example.studynotesapp.data.Note;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;
    private OnNoteClick listener;

    public interface OnNoteClick {
        void onClick(Note note);
    }

    public NoteAdapter(List<Note> notes, OnNoteClick listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.title);
        holder.content.setText(note.content);

        holder.itemView.setOnClickListener(v -> listener.onClick(note));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        public NoteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            content = itemView.findViewById(R.id.txtContent);
        }
    }
}