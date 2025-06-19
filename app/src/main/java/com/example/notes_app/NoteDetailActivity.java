package com.example.notes_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class NoteDetailActivity extends AppCompatActivity {

    TextView textTitle, textDescription;

    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);


        textTitle = findViewById(R.id.text_note_title);
        textDescription = findViewById(R.id.text_note_description);


        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");


        textTitle.setText(title);
        textDescription.setText(description);

        imageView4 = findViewById(R.id.imageView4);


        imageView4.setOnClickListener(v -> {
            finish();
        });
    }
}