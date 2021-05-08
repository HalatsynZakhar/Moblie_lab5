package ua.kpi.comsys.io8207;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Lab4_Book_activity_add extends AppCompatActivity {

    TextInputEditText book_titleAdd;
    TextInputEditText book_subtitleAdd;
    TextInputEditText book_authorsAdd;
    Button ButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab4_activity_book_adds);

        book_titleAdd = findViewById(R.id.title_text_add);
        book_subtitleAdd = findViewById(R.id.addSubtitle);
        book_authorsAdd = findViewById(R.id.addAuthor);

        ButtonAdd = findViewById(R.id.addButton);
        ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = book_titleAdd.getText().toString();
                String subtitle = book_subtitleAdd.getText().toString();
                String authors = book_authorsAdd.getText().toString();

                if (title.equals("") && subtitle.equals("") && authors.equals("")) {
                    Toast.makeText(Lab4_Book_activity_add.this, "Empty input", Toast.LENGTH_SHORT).show();
                }
                else if (title.equals("")) {
                    Toast.makeText(Lab4_Book_activity_add.this, "Empty input", Toast.LENGTH_SHORT).show();
                }
                else {
                    Lab4_BookFragment.books.add(new Lab4_Book(title, subtitle, authors, "", "",
                            "", "", "","","", R.drawable.no_image));

                    Toast.makeText(Lab4_Book_activity_add.this, "New book added", Toast.LENGTH_SHORT).show();

                    Lab4_BookFragment.recyclerAdapter.notifyDataSetChanged();

                    finish();
                }
            }
        });
    }
}