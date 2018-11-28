package leo.awatin.com.e_seebudgetv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, new BalanceFragment() ).commit();
            navigationView.setCheckedItem( R.id.nav_balance );
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_balance:
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, new BalanceFragment() ).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.nav_help:
                helpAndFeedback();
                break;
        }

        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen( GravityCompat.START )){
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    public void  helpAndFeedback(){
        Intent h = null, chooser = null;
        h = new Intent(Intent.ACTION_SEND);
        h.setData( Uri.parse("mailto:"));
        String[] to = {"2row4way@gmail.com"};
        h.putExtra(Intent.EXTRA_EMAIL,to);
        h.putExtra(Intent.EXTRA_SUBJECT, "Help and Feedback");
        h.putExtra(Intent.EXTRA_TEXT, "Put your question or feedback here!");
        h.setType("message/rfc822");
        chooser = Intent.createChooser(h, "Select your email app:");
        startActivity(chooser);
    }
}
