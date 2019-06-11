package hr.tvz.android.listavladimir;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static hr.tvz.android.listavladimir.R.drawable;
import static hr.tvz.android.listavladimir.R.id;
import static hr.tvz.android.listavladimir.R.layout;
import static hr.tvz.android.listavladimir.R.string;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private View navHeader;

    public static final String ACTION_SHARE = "hr.tvz.android.listavladimir.SHARE";

    List<ModelClass> items;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ModelClass> modelClassList = new ArrayList<>();
    //private ShareActionProvider nShareActionProvider;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        recyclerView = findViewById(id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //doraditi
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener( this);
        navHeader = navigationView.getHeaderView(0);

//        //Loading profile image
//        ImageView profileImage = navHeader.findViewById(R.id.profileImage);
//        Glide.with(this).load(NavigationDrawerConstants.PROFILE_URL)
//                .apply(RequestOptions.circleCropTransform())
//                .thumbnail(0.5f)
//                .into(profileImage);
//        //Loading backgrounf image
//        ImageView navBackground = navHeader.findViewById(R.id.img_header_bg);
//        Glide.with(this).load(NavigationDrawerConstants.BACKGROUND_URL)
//                .thumbnail(0.5f)
//                .into(navBackground);

        createNotificationChannel();
        //Select Home by default
        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new HomeFragment();
        displaySelectedFragment(fragment);


/*
        for (int i = 0; i<=10; i++){
            ModelClass modelClass = new ModelClass(
                    "heading" + (i+1),
                    "dummy text"
            );
            modelClassList.add(modelClass);

        }
*/

        modelClassList.add(new ModelClass("Zagreb", getString(string.zagreb), drawable.zagreb));
        modelClassList.add(new ModelClass("Split", getString(string.split), drawable.split));
        modelClassList.add(new ModelClass("Dubrovnik", getString(string.dubrovnik), drawable.dubrovnik));
        modelClassList.add(new ModelClass("Zadar", getString(string.zadar), drawable.zadar));
        modelClassList.add(new ModelClass("Osijek", getString(string.osijek), drawable.osijek));
        modelClassList.add(new ModelClass("Trogir", getString(string.trogir), drawable.trogir));
        modelClassList.add(new ModelClass("Metković", getString(string.metkovic), drawable.metkovic));


        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_gallery) {
            fragment = new GalleryFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_slideshow) {
            fragment = new SlideshowFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_manage) {
            fragment = new ToolsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_share) {
            System.out.println("SHARE!");
//            //Display Share Via dialogue
//            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
////            sharingIntent.setType(NavigationDrawerConstants.SHARE_TEXT_TYPE);
////            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, NavigationDrawerConstants.SHARE_TITLE);
////            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, NavigationDrawerConstants.SHARE_MESSAGE);
//            startActivity(Intent.createChooser(sharingIntent, NavigationDrawerConstants.TAG_SHARE));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    public void update (View v){
        Intent in = new Intent(this, BatteryActivity.class);
        startActivity(in);

        Button jezik = findViewById(id.jezikTxt);
        jezik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                showChangeLanguageDialog();
            }
        });

    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"de","en","es", "cro"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Odaberite jezik:");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    setLocale("de");
                    recreate();

                }
                else if (i == 1) {
                    setLocale("en");
                    recreate();
                }
                else if (i == 2) {
                    setLocale("es");
                    recreate();
                }
                else if (i == 3) {
                    setLocale("cro");
                    recreate();
                }
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    private void setLocale(String jezik) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(jezik.toLowerCase()));
        }
        res.updateConfiguration(conf, dm);
    }

    public void listavladimir (View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://croatia.hr/hr-HR"));
        startActivity(browserIntent);
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case.R.id.share:
//                Intent i= new Intent(
//                        Intent.ACTION_SEND);
//                1.setType(text/plan);
//                1.putExtra
//                        Intent.EXTRA_TEXT, "https://croatia.hr/hr-HR");
//                startActivity(Intent.createChooser(
//                    i,
//            "Share Via"));
//                break;
//
//            }
//            Toast.makeText(getApplicationContext() {
//        return super.getApplicationContext(), "You click on menu share", Toast.LENGTH_SHORT).show();
//    return super.onOptionsItemSelected(items);

        public void onClick(View view) {
            new android.app.AlertDialog.Builder(this)
                    .setTitle(R.string.share)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(BatteryActivity.ACTION_SHARE);
                            intent.putExtra("url", "https://www.facebook.com");
                            sendBroadcast(intent);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    public static final String CHANNEL_ID = "listavladimir";

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}



