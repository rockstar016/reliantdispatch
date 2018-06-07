package com.rock.reliantdispatch.Authority.Register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rock.reliantdispatch.Base.BaseActivity;
import com.rock.reliantdispatch.R;
import com.rock.reliantdispatch.Utils.ScrollDisableViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ScrollDisableViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        InitToolbar(toolbar, "Register");
        RegisterAdapter adapter = new RegisterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    class RegisterAdapter extends FragmentPagerAdapter
    {
        public RegisterAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0)
            {
                return FirstRegisterFragment.newInstance();
            }
            else if(position == 1)
            {
                return SecondRegisterFragment.newInstance();
            }
            else if(position == 2){
                return ThirdRegisterFragment.newInstance();
            }
            else if(position == 3){
                return FourthRegisterFragment.newInstance();
            }
            else if(position == 4){
                return FifthRegisterFragment.newInstance();
            }
            else{
                return SixthRegisterFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    }

}
