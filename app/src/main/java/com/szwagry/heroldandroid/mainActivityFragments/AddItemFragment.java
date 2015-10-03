package com.szwagry.heroldandroid.mainActivityFragments;

import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.szwagry.heroldandroid.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EFragment(R.layout.fragment_add_item)
public class AddItemFragment extends Fragment {

    @ViewById
    Spinner spinnerItemTypes;

    @ViewById
    EditText registerItemName;

    @ViewById
    Button registerOwnedItemButton;

    @AfterViews
    public void setupViews(){
        List<String> itemTypes = new ArrayList<String>();
        itemTypes.add("Bicycle");
        itemTypes.add("Car");
        itemTypes.add("Mobile");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (AddItemFragment.this.getActivity(), android.R.layout.simple_spinner_item,itemTypes);
        spinnerItemTypes.setAdapter(adapter);
    }

    @Click({R.id.registerOwnedItemButton})
    public void registerOwnedItem(){
        String type = spinnerItemTypes.getSelectedItem().toString();
        String name = registerItemName.getText().toString();

        Date addedDate = new Date();
        String formatedData = new SimpleDateFormat("yyyy-MM-dd").format(addedDate);
        if(isBlank(name)){
            showToast("Name is empty!");
        } else{
            processRegistration(name, type, formatedData);
            showToast("Created:" + name + " " + type + " " + formatedData);
        }
    }

    private void processRegistration(String name, String type, String formatedData) {

    }

    boolean validateInput(EditText text) {
        return isBlank(text.getText().toString().trim());
    }

    @UiThread
    void showToast(String text) {
        Toast.makeText(AddItemFragment.this.getActivity(), text, Toast.LENGTH_LONG).show();
    }

    boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        if (str == "") {
            return true;
        }
        return false;
    }
}
