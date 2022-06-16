package com.example.testproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Visibility;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.strenamedialog.STRenameDialog;
import com.example.testproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        mainBinding.clickMe.setOnClickListener(view -> {
            showSTRenameDialog();
        });
    }
    void showSTRenameDialog(){
       final STRenameDialog dialog = new STRenameDialog(this, STRenameDialog.DialogStyle.DEFAULT);
       dialog.create();
       dialog.show();
       dialog.setDialogBackground(Color.WHITE);
       dialog.setDialogCancelable(true);
       dialog.setButtonLayoutVisibility(View.VISIBLE);
       dialog.setButtonLayoutGravity(Gravity.CENTER);
       dialog.setButtonGap(20);
       dialog.setEditTextHint("Rename photo");
       dialog.setTitle("Rename Photo");
       dialog.setCrossButtonPadding(12);
       dialog.setFileExtension(".3gp");
       dialog.setWarning("Warning: Empty text can not be applied.");
       dialog.setSelectedEditText(true,"Hello Sakhawat !!!");
       dialog.setTitleGravity(Gravity.CENTER);
       dialog.setTitleTextSize(6);
    }

}