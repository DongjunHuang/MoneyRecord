package com.example.dongjunhuang.Activities;

/**
 * Created by dongjunhuang on 10/8/14.
 */

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.example.dongjunhuang.Fragments.DetailFragment;
import com.example.dongjunhuang.Fragments.InputFragment;
import com.example.dongjunhuang.Fragments.ItemsFragment;
import com.example.dongjunhuang.Interfaces.Communicator;
import com.example.dongjunhuang.Libs.Money_Record;
import com.example.dongjunhuang.moneytracker.R;


public class MainActivity extends ActionBarActivity implements Communicator{
    private DrawerLayout drawer_layout;
    private String[] drawer_items;
    private ListView drawer_list;
    private ActionBarDrawerToggle drawer_toggle;
    private EditText edit_source;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_source =  (EditText)findViewById(R.id.edit_source);
        /**set up list view in order to be displayed in actionbar drawer*/
        drawer_items = getResources().getStringArray(R.array.drawer_item);
        drawer_list = (ListView)findViewById(R.id.left_drawer);
        drawer_list.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer, drawer_items));
        /**set up drawerlayout into actionbar drawer toggle*/
        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer_toggle = new ActionBarDrawerToggle(this, drawer_layout, R.drawable.ic_launcher, R.string.drawer_open, R.string.drawer_close);
        drawer_layout.setDrawerListener(drawer_toggle);
        /**set up action bar*/
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_send_money:
                DialogFragment dialog = InputFragment.newInstance();
                dialog.show(getSupportFragmentManager(), "dialog");
                return true;
        }
        if (drawer_toggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    /**Override interface*/
    public void refresh(String s){
        FragmentManager fm = getSupportFragmentManager();
        ItemsFragment frag = (ItemsFragment)fm.findFragmentById(R.id.content_frame);
        frag.Exec();
    }

    public void getData(Money_Record record){
        DetailFragment detail = DetailFragment.newInstance();
        detail.passRecord(record);
        detail.show(getSupportFragmentManager(), "dialog");
    }




}
