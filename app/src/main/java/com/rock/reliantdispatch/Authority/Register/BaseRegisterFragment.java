package com.rock.reliantdispatch.Authority.Register;


import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by michalejackson on 4/6/18.
 */

public class BaseRegisterFragment extends Fragment {
    RegisterActivity Parent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof RegisterActivity){
            Parent = (RegisterActivity)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Parent = null;
    }
}
