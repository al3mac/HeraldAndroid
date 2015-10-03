package com.szwagry.heroldandroid.mainActivityFragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.szwagry.heroldandroid.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EFragment(R.layout.fragment_add_item)
public class AddItemFragment extends Fragment {

    ProgressDialog progressDialog;
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
        }
    }

    @Background
    void processRegistration(String name, String type, String formatedData) {

        createAndSaveImageFromText("trolololo");

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

    public void createAndSaveImageFromText(String itemCode){
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.src); // the original file yourimage.jpg i added in resources
        Bitmap dest;
        dest = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas cs = new Canvas(dest);
        Paint tPaint = new Paint();
        tPaint.setTextSize(120);
        tPaint.setColor(Color.BLACK);
        tPaint.setStyle(Paint.Style.FILL);
        cs.drawBitmap(src, 0f, 0f, null);
        float height = tPaint.measureText("yY");
        float width = tPaint.measureText(itemCode);
        float x_coord = (src.getWidth() - width)/2;
        cs.drawText(itemCode, x_coord, height+600f, tPaint); // 15f is to put space between top edge and the text, if you want to change it, you can
        try {
            dest.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File("/sdcard/ImageAfterAddingText.jpg")));
            // dest is Bitmap, if you want to preview the final image, you can display it on screen also before saving
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
