package com.example.gameapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Game> arrayList;
    ArrayList<Game> results;
    DBHelper dbHelper;
    GridView gridView;
    GameAdapter gameAdapter;
    Utils utils = new Utils(this);
    int columnWidth;
    NavigationView navigationView;

    TextView txt, user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //setStatusBar();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txt = (TextView) findViewById(R.id.txt1);
        gridView = (GridView) findViewById(R.id.gridV);
        InitilizeGridLayout();
        dbHelper = new DBHelper(WelcomeActivity.this);
        arrayList = dbHelper.getAllGame();
        results = dbHelper.getAllGame();



        View header = navigationView.getHeaderView(0);
        user_name = header.findViewById(R.id.user_text);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String value = extras.getString("user");
            user_name.setText(value);

        } else user_name.setText("Empty User");


        //Check if there are games available
        if(arrayList.size() > 0) {
            txt.setVisibility(View.GONE);
        }


        gameAdapter = new GameAdapter(WelcomeActivity.this, R.layout.game_layout, arrayList);
        gridView.setAdapter(gameAdapter);
        registerForContextMenu(gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WelcomeActivity.this,DetailActivity.class);
                intent.putExtra("game",arrayList.get(position));
                startActivity(intent);
            }
        });

    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.welcome, menu);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    ArrayList<Game> temp = new ArrayList<Game>();
                    for(Game g : results) {
                        if(g.getName().toLowerCase().contains(newText.toLowerCase())) {
                            temp.add(g);
                        }
                    }

                    if(temp.size() > 0) {
                        gameAdapter.clear();
                        gameAdapter.addAll(temp);
                        gameAdapter.notifyDataSetChanged();
                    }  else {
                        txt.setVisibility(View.VISIBLE);
                    }
                    if(newText.isEmpty()) {
                        txt.setVisibility(View.GONE);
                        gameAdapter.clear();
                        gameAdapter.addAll(results);
                        gameAdapter.notifyDataSetChanged();
                    }
                    return false;
                }
            });

        return super.onCreateOptionsMenu(menu);
    }




        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());

        return true;
    }

    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                8, r.getDisplayMetrics());

        columnWidth = (int) ((utils.getScreenWidth() - ((2 + 1) * padding)) / 2);

        gridView.setNumColumns(2);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }

    public void displaySelectedScreen(int itemID) {
        Fragment fragment = null;
        switch (itemID) {
            case R.id.nav_home:
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
            case R.id.nav_pc:
                fragment = new FirstFragment();
                break;
            case R.id.nav_ps4:
                fragment = new SecondFragment();
                break;
            case R.id.nav_3ds:
                fragment = new ThirdFragment();
                break;
            case R.id.nav_xbox1:
                fragment = new FourthFragment();
                break;
            case R.id.nav_classic:
                fragment = new FifthFragment();
                break;
        }


        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_welcome, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
