package com.rock.reliantdispatch.Utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.rock.reliantdispatch.R;

import java.util.Locale;


/**
 * Created by rock on 2/8/2017.
 */

public class StringCheckUtil {
    public static boolean isEmpty(Context context, TextInputLayout txtEdit){
        if(txtEdit.getEditText().getText().toString().trim().isEmpty()){
            txtEdit.setError(context.getString(R.string.requried_field));
            return true;
        }
        return false;
    }

    public static boolean isEqual(EditText edit1, EditText edit2){
        return (edit1.getText().toString().equals(edit2.getText().toString()));
    }

    public static boolean isLength(EditText edit, int length){
        if(edit.getText().toString().length() < length){
            edit.setError(String.format(Locale.US, "At least %d letters", length));
            return false;
        }
        return true;
    }

    public static boolean validEmail(EditText edit){
        boolean result = android.util.Patterns.EMAIL_ADDRESS.matcher(edit.getText().toString()).matches();
        if(!result)
            edit.setError("Invalid Email address");
        return result;
    }
}
