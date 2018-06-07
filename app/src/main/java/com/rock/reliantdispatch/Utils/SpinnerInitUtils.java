package com.rock.reliantdispatch.Utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidbuts.multispinnerfilter.MultiSpinner;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.rock.reliantdispatch.R;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class SpinnerInitUtils {
    public static void InitSpinner(Context context, Spinner spinner, int providerResource)
    {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(context), providerResource, R.layout.item_spinner_item);
        adapter.setDropDownViewResource(R.layout.item_spinner_item);
        spinner.setAdapter(adapter);
    }

    public static void InitMultipleSpinner(Context context, MultiSpinner spinner, int arrayListResourceId, MultiSpinnerListener listener)
    {
        final List<String> list = Arrays.asList(context.getResources().getStringArray(arrayListResourceId));
        LinkedHashMap<String, Boolean> items = new LinkedHashMap<>();
        for (String item : list) {
            items.put(item, false);
        }
        spinner.setItems(items, listener);
    }
}
