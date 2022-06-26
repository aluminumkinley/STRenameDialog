package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.strenamedialog.STRenameDialog;
import com.example.testproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
//        mainBinding.clickMe.setOnClickListener(view -> {
            showSTRenameDialog();
//        });
//        showDefaultDialog();
    }
    void showSTRenameDialog(){
       final STRenameDialog dialog = new STRenameDialog(this, STRenameDialog.DialogStyle.CUSTOM);
       dialog.create();
       dialog.show();
//       dialog.setDialogBackground(Color.WHITE);
       dialog.setDialogCancelable(true);
       dialog.setButtonLayoutType(STRenameDialog.DialogStyle.DEFAULT);
       dialog.setButtonLayoutGravity(Gravity.RIGHT);
       dialog.setButtonGap(0);
       dialog.setEditTextHint("Rename photo");
       dialog.setTitle("Rename Photo");
       dialog.setCrossButtonPadding(12);
       dialog.setFileExtension(".3gp");
       dialog.setWarning("Warning: Empty text can not be applied.");
       dialog.setSelectedEditText(true,"Hello Sakhawat !!!");
       dialog.setTitleGravity(Gravity.CENTER);
       dialog.setTitleTextSize(6);
       dialog.setGapBetweenButtonLayer(0);
       dialog.setOnPositiveButtonClick(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(dialog.binding.renameEditText.getText().length()>0) {

               }else{
                   Toast.makeText(MainActivity.this,"File name can't be empty",Toast.LENGTH_SHORT).show();
               }
           }
       });
       dialog.setOnNegativeButtonClick(view -> dialog.dismiss());
    }

}