package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static final String DATABASE_NAME = "poll_db";
    private PollDatabase pollDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);


        pollDatabase = Room.databaseBuilder(getApplicationContext(), PollDatabase.class, DATABASE_NAME).build();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId()) {

                            case R.id.nav_camera:
                                Form form = new Form();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, form).commit();
                                mDrawerLayout.closeDrawers();
                                return true;

                            case R.id.nav_gallery:
                                frag2 form2 = new frag2();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, form2).commit();
                                mDrawerLayout.closeDrawers();
                                return true;

                            case R.id.nav_slideshow:
                                Form form3 = new Form();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, form3).commit();
                                mDrawerLayout.closeDrawers();
                                return true;
                        }

                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }

                }
        );

    }

    public void savePress(View view){
        EditText name = (EditText) view.getRootView().findViewById(R.id.new_name);
        EditText date = (EditText) view.getRootView().findViewById(R.id.new_date);
        Spinner category = (Spinner) view.getRootView().findViewById(R.id.new_cat);
        EditText comment = (EditText) view.getRootView().findViewById(R.id.new_com);

        final String nameFin = name.getText().toString();
        final String dateFin = date.getText().toString();
        final String catFin = category.getSelectedItem().toString();
        final String comFin = comment.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Poll poll =new Poll();
                poll.setUserName(nameFin);
                poll.setDate(dateFin);
                poll.setCategory(catFin);
                poll.setComment(comFin);
                pollDatabase.daoAccess().insertOnlySinglePoll (poll);
            }
        }) .start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}