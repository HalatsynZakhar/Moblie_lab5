package ua.kpi.comsys.io8207;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Lab4_BookFragment extends Fragment {

    private FloatingActionButton addButton;
    View v;
    protected static Lab4_RecyclerViewAdapter recyclerAdapter;
    private RecyclerView myRecycleView;
    protected static List<Lab4_Book> books = new ArrayList<>();
    private Lab4_Book deletedBook = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.lab4_fragment_book, container, false);
        myRecycleView = v.findViewById(R.id.item_list_books);

        setUpData();

        addButton = v.findViewById(R.id.addingButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(getContext(), Lab4_Book_activity_add.class);
                startActivity(addIntent);
            }
        });

        recyclerAdapter = new Lab4_RecyclerViewAdapter(getContext(), books);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recyclerAdapter);

        SearchView searchView = v.findViewById(R.id.Find_line);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<Lab4_Book> filteredBook = new ArrayList<Lab4_Book>();
                for (Lab4_Book book : books) {
                    if (book.getBookName().toLowerCase().contains(s.toLowerCase())) {
                        filteredBook.add(book);
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Lab4_Book> filteredBooks = new ArrayList<Lab4_Book>();
                for (Lab4_Book book : books) {
                    if (book.getBookName().toLowerCase().contains(s.toLowerCase())) {
                        filteredBooks.add(book);
                    }
                }
                recyclerAdapter.filterList(filteredBooks);

                return false;
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(myRecycleView);

        return v;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedBook = books.get(position);
                    books.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(getContext(), c, myRecycleView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.design_default_color_error))
                    .addSwipeLeftActionIcon(R.drawable.ic_del)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void setUpData() {
        books = new ArrayList<>();

        books.add(new Lab4_Book("iOS Components and Frameworks", "Understanding the Advanced Features of the iOS SDK", "Kyle Richter, Joe Keeley", "Addison-Wesley", "9780321856715", "576","2013", "3", "iOS Components and Frameworks will help you leverage iOS's powerful components and frameworks to build apps with outstanding performance, reliability, interactivity, media support, " +
                "and customization.Pioneering iOS developers Kyle Richter and Joe Keeley illuminate the sophisticated intermediate-to-ad...", "$23.30", R.drawable.image_01));
        books.add(new Lab4_Book("Learning iOS Development","A Hands-on Guide to the Fundamentals of iOS Programming","Maurice Sharp, Erica Sadun, Rod Strougo",
                "Addison-Wesley","9780321862969","592","2013","4",
                "Learning iOS Developmentis the perfect first book for every new iOS 7 developer. " +
                        "It delivers  a complete foundation for iOS development, including an introduction " +
                        "to the Objective-C language, Xcode development tools, best-practice user interface development, " +
                        "and best practices for all  aspects of ap...","$3.99",R.drawable.image_02));
        books.add(new Lab4_Book("Beginning iOS Programming","Building and Deploying iOS Applications","Nick Harris","Wrox","9781118841471","336","2014","3","iOS 7 is a major shift in the look and feel of apps - the first major sea change since the iPhone was first introduced. For apps to blend in with the new UI, each needs a complete redesign. Beginning iOS Programming: Building and Deploying iOS Applications starts at the beginning - including an intr...","$6.35", R.drawable.image_03));
        books.add(new Lab4_Book("Beginning iOS 5 Development","Exploring the iOS SDK","David Mark, Jack Nutting, Jeff LaMarche","Apress","9781430236054","752","2011","4","The team that brought you the bestselling Beginning iPhone 4 Development is back again for Beginning iOS 5 Development, bringing this definitive guide up-to-date with Apple's latest and greatest iOS SDK, as well as with the latest version of Xcode. There's coverage of brand new technologies, with ch...","$3.65", R.drawable.no_image));
        books.add(new Lab4_Book("Beginning iOS 5 Games Development","Using the iOS SDK for iPad, iPhone and iPod touch","Lucas Jordan","Apress","9781430237105","344","2011","4","Game apps on iPhone and now iPad remain one of the most popular type of apps in the Apple iTunes App Store.  Does Angry Birds ring a bell?  What you were once able to do just for the iPhone (and iPod touch) is now possible for the popular iPad, using the new iOS 5 SDK.Beginning iOS 5 Games Developme...","$36.31",R.drawable.image_05));
        books.add(new Lab4_Book("More iOS 6 Development", "Further Explorations of the iOS SDK", "David Mark, Alex Horovitz, Kevin Kim, Jeff LaMarche", "Apress", "9781430238072", "552","2013","4",
                "Interested in iPhone and iPad apps development? Want to learn more? " +
                        "Whether you're a self-taught iPhone and iPad apps development genius or " +
                        "have just made your way through the pages of Beginning iOS 6 Development, " +
                        "we have the perfect book for you.More iOS 6 Development: Further Explorations of the i...","$4.95", R.drawable.image_06));
        books.add(new Lab4_Book("Beginning iOS 6 Development","Exploring the iOS SDK","David Mark, Jack Nutting, Jeff LaMarche, Fredrik Olsson","Apress","9781430245124","764","2013","4","There's coverage of brand new technologies, with chapters on storyboards and iCloud, for example, as well as significant updates to existing chapters to bring them in line with all the changes that came with the iOS 6 SDK. You'll have everything you need to create your very own apps for the latest i...","$5.34",R.drawable.image_07));
        books.add(new Lab4_Book("Beginning iOS 7 Development","Exploring the iOS SDK","Jack Nutting, Fredrik Olsson, David Mark, Jeff LaMarche","Apress","9781430260226","720","2014","3","The team that brought you the bestselling Beginning iPhone Development is back again for Beginning iOS 7 Development, bringing this definitive guide up-to-date with Apple's latest and greatest iOS 7 SDK, as well as with the latest version of Xcode.There's coverage of brand-new technologies, includin...","$3.65",R.drawable.image_08));
        books.add(new Lab4_Book("Developing iOS Applications with Flex 4.5","Building iOS Applications with ActionScript","Rich Tretola","O'Reilly Media","9781449308360","134","2011","4","Ready to put your ActionScript 3 skills to work on iPhone and iPad apps? This hands-on book walks you through the process of creating an Adobe AIR application for iOS devices from start to finish, using the Flex 4.5 framework. Move quickly from a basic Hello World application to complex interactions...","$12.99", R.drawable.no_image));
        books.add(new Lab4_Book("iOS 6 Programming Cookbook","Solutions for iOS Developers","Vandad Nahavandipoor","O'Reilly Media","9781449342753","976","2012","4","Overcome the vexing issues you'll inevitably confront when creating apps for the iPhone, iPad, or iPod touch. By making use of new and revised recipes in this updated cookbook, you'll quickly learn the steps necessary to write complete iOS apps - including ways to store and protect data, enhance and...","$4.45",R.drawable.image_10));
    }


}
