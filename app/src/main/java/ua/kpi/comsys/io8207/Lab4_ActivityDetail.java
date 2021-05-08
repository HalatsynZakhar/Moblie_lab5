package ua.kpi.comsys.io8207;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Lab4_ActivityDetail extends AppCompatActivity {

    private int img;
    private String titl;
    private String subtitl;
    private String authors;
    private String publisher;
    private String isbn13;
    private String pages;
    private String year_book;
    private String rating;
    private String desc;
    private String price;


    ImageView poster;
    TextView b_title, b_subtitle, b_authors, b_publisher, b_isbn13, b_pages;
    TextView p_year, b_rating, b_dect, b_price;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab4_detail_book);

        poster = findViewById(R.id.image_book);
        b_title = findViewById(R.id.title_book);
        b_subtitle = findViewById(R.id.subtitile_book);
        b_authors = findViewById(R.id.authors_book);
        b_publisher = findViewById(R.id.publisher_books);
        b_isbn13 = findViewById(R.id.isbn13_book);
        b_pages = findViewById(R.id.pages_book);
        p_year = findViewById(R.id.year_book);
        b_rating = findViewById(R.id.rating_book);
        b_dect = findViewById(R.id.desc_book);
        b_price = findViewById(R.id.price_book);

        getDataOfSelectedBook();
        setValues();
    }

    private void getDataOfSelectedBook() {
        img = getIntent().getIntExtra("bookimage", 0);
        titl = getIntent().getStringExtra("booktitle");
        subtitl = getIntent().getStringExtra("booksubtitle");
        authors = getIntent().getStringExtra("bookauthors");
        isbn13 = getIntent().getStringExtra("bookpublisher");
        pages = getIntent().getStringExtra("bookisbn13");
        year_book = getIntent().getStringExtra("bookpages");
        rating = getIntent().getStringExtra("bookyear");
        desc = getIntent().getStringExtra("bookrating");
        price = getIntent().getStringExtra("bookdesc");
        publisher = getIntent().getStringExtra("bookprice");
    }

    private void setValues() {
        poster.setImageResource(img);
        b_title.setText(titl);
        b_subtitle.setText(subtitl);
        b_authors.setText(authors);
        b_publisher.setText(isbn13);
        b_isbn13.setText(pages);
        b_pages.setText(year_book);
        p_year.setText(rating);
        b_rating.setText(desc);
        b_dect.setText(price);
        b_price.setText(publisher);
    }
}
