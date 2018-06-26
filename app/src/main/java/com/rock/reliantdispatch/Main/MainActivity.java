package com.rock.reliantdispatch.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.Base.BaseFragment;
import com.rock.reliantdispatch.Main.Billlading.BillLadingFragment;
import com.rock.reliantdispatch.Main.Carrier.SearchFragment;
import com.rock.reliantdispatch.Main.Profile.ProfileFragment;
import com.rock.reliantdispatch.Main.Resource.ResourceFragment;
import com.rock.reliantdispatch.Main.Shipper.ShipperFragment;
import com.rock.reliantdispatch.Main.Shipper.VehicleList.VehicleListFragment;
import com.rock.reliantdispatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private FRAGMENTS currentFragment;


    public enum FRAGMENTS {
        PostFragment, SearchFragment, BillladingFragment, ResourceFragment, ProfileFragment
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View headerLayout =
                navigation.getHeaderView(0);
        navigation.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null){
            navigation.setCheckedItem(R.id.post);
            addFragment(FRAGMENTS.PostFragment);
        }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        MoveToSelectedFragment(item);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void MoveToSelectedFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post:
                addFragment(FRAGMENTS.PostFragment);
                break;
            case R.id.search:
                addFragment(FRAGMENTS.SearchFragment);
                break;
            case R.id.billlading:
                addFragment(FRAGMENTS.BillladingFragment);
                break;
            case R.id.resource:
                addFragment(FRAGMENTS.ResourceFragment);
                break;
            case R.id.profile:
                addFragment(FRAGMENTS.ProfileFragment);
                break;
        }
    }

    public void setCurrentFragment(FRAGMENTS currentFragment) {
        this.currentFragment = currentFragment;
    }

    public void addFragment(FRAGMENTS donationfragment) {
        if (currentFragment != donationfragment) {
            setCurrentFragment(donationfragment);
            BaseFragment f;
            switch (donationfragment) {
                case PostFragment:
                    f = ShipperFragment.newInstance();
                    break;
                case SearchFragment:
                    f = SearchFragment.newInstance();
                    break;
                case BillladingFragment:
                    f = BillLadingFragment.newInstance();
                    break;
                case ResourceFragment:
                    f = ResourceFragment.newInstance();
                    break;
                case ProfileFragment:
                    f = ProfileFragment.newInstance();
                    break;
                default:
                    f = VehicleListFragment.newInstance();
                    break;
            }
            attachFragment(f);
        }
    }

    private void attachFragment(BaseFragment f) {
        if (f != null) {
            while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, f).addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
