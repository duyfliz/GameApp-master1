package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    Game g;
    TextView textView;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppBarLayout appBarLayout;
    FragmentOne one = new FragmentOne();
    FragmentTwo two = new FragmentTwo();
    FragmentThree three = new FragmentThree();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        g = (Game) intent.getSerializableExtra("game");





        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setTitle(g.getName());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewP);
        setUpViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);


        Bundle bundle = new Bundle();
        bundle.putString("1", g.getName());
        bundle.putString("2", g.getDescription());
        bundle.putString("3", g.getImage());
        bundle.putFloat("4", g.getRating());
        bundle.putString("5", g.getDate());
        bundle.putString("6", g.getPlatform());
        bundle.putString("7", g.getDev());
        bundle.putString("8", g.getTrailer());
        bundle.putString("9", g.getImgrv());
        bundle.putString("10", g.getRv());
        one.setArguments(bundle);
        two.setArguments(bundle);
        three.setArguments(bundle);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(one, "Info");
        viewPagerAdapter.addFragment(two, "Media");
        viewPagerAdapter.addFragment(three, "Review");
        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments = new ArrayList<>();
        List<String> strings = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            strings.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strings.get(position);
        }
    }

}
