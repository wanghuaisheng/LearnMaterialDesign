package com.witways.mylearn.material.activity.menu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.witways.utils.ui.Tools;
import com.mikhaellopez.circularimageview.CircularImageView;

public class MenuDrawerMail extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private Menu menu_navigation;
    private DrawerLayout drawer;
    private View navigation_header;
    private boolean is_account_mode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer_mail);

        initToolbar();
        initNavigationMenu();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.pink_600));
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("新闻列表");
        Tools.setSystemBarColor(this, R.color.pink_700);
    }

    private void initNavigationMenu() {
        final NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                updateCounter(navView);
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                onItemNavigationClicked(item);
                return true;
            }
        });

        // open drawer at start
        drawer.openDrawer(GravityCompat.START);
        updateCounter(navView);
        menu_navigation = navView.getMenu();

        // navigation header
        navigation_header = navView.getHeaderView(0);
        (navigation_header.findViewById(R.id.bt_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_hide = Tools.toggleArrow(view);
                is_account_mode = is_hide;
                menu_navigation.clear();
                if (is_hide) {
                    menu_navigation.add(1, 1000, 100, "hiwhs@qq.com").setIcon(R.drawable.ic_account_circle);
                    menu_navigation.add(1, 2000, 100, "whs83@163.com").setIcon(R.drawable.ic_account_circle);
                    menu_navigation.add(1, 1, 100, "添加账户").setIcon(R.drawable.ic_add);
                    menu_navigation.add(1, 2, 100, "管理账户").setIcon(R.drawable.ic_settings);
                } else {
                    navView.inflateMenu(R.menu.menu_navigation_drawer_mail);
                    updateCounter(navView);
                }
            }
        });
    }

    private void onItemNavigationClicked(MenuItem item) {
        if (!is_account_mode) {
            Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
            actionBar.setTitle(item.getTitle());
            drawer.closeDrawers();
        } else {
            TextView name = (TextView) navigation_header.findViewById(R.id.name);
            TextView email = (TextView) navigation_header.findViewById(R.id.email);
            CircularImageView avatar = (CircularImageView) navigation_header.findViewById(R.id.avatar);
            switch (item.getItemId()) {
                case 1000:
                    name.setText("刘娟秀");
                    email.setText(item.getTitle().toString());
                    avatar.setImageResource(R.drawable.photo_male_5);
                    break;
                case 2000:
                    name.setText("王怀生");
                    email.setText(item.getTitle().toString());
                    avatar.setImageResource(R.drawable.photo_male_2);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void updateCounter(NavigationView nav) {
        if (is_account_mode) return;
        Menu m = nav.getMenu();
        ((TextView) m.findItem(R.id.nav_all_inbox).getActionView().findViewById(R.id.text)).setText("75");
        ((TextView) m.findItem(R.id.nav_inbox).getActionView().findViewById(R.id.text)).setText("68");

        TextView badgePrioInbx = (TextView) m.findItem(R.id.nav_priority_inbox).getActionView().findViewById(R.id.text);
        badgePrioInbx.setText("3");
        badgePrioInbx.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));

        TextView badgeSocial = (TextView) m.findItem(R.id.nav_social).getActionView().findViewById(R.id.text);
        badgeSocial.setText("51");
        badgeSocial.setBackgroundColor(getResources().getColor(R.color.green_500));

        ((TextView) m.findItem(R.id.nav_spam).getActionView().findViewById(R.id.text)).setText("13");
    }
}