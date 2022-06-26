package com.example.strenamedialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.inline.InlineContentView;

import com.example.strenamedialog.databinding.RenameDialogBinding;

import java.io.File;
import java.io.IOException;

public class STRenameDialog extends AlertDialog {
    Context context;
    public RenameDialogBinding binding;

    public enum DialogStyle{
        DEFAULT,
        CUSTOM
    }
    public STRenameDialog(Context context, DialogStyle dialogStyle){
        super(context,R.style.STDialogTheme);
        this.context = context;
        switch (dialogStyle){
            case CUSTOM:
                initialize(context);
                break;
            case DEFAULT:
            default:
                break;
        }
    }
    private void initialize(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.rename_dialog,null);
        binding = RenameDialogBinding.bind(view);

        binding.descriptionLayout.setVisibility(View.GONE);
        binding.clearText.setOnClickListener(view1 -> {
            binding.renameEditText.getText().clear();
        });
        setView(binding.getRoot());
        setCanceledOnTouchOutside(true);
    }

    public void setOnPositiveButtonClick(View.OnClickListener onClickListener){
        binding.renameButton.setOnClickListener(onClickListener);
    }
    public File renameFile(String oldFilePath, String renameFileName){
        File oldFile = new File(oldFilePath); // old file
        if(!oldFile.exists()){
            Toast.makeText(context, "file does not exist", Toast.LENGTH_SHORT).show();
        }

        int i = oldFilePath.lastIndexOf(".");
        int lastSlashIndex = oldFilePath.lastIndexOf("/");
        String newFilePath = oldFilePath.substring(0,lastSlashIndex+1);
        newFilePath += renameFileName;

        String extension = oldFilePath.substring(i);
        newFilePath+=extension;

        File newFile = new File(newFilePath);
        try(ParcelFileDescriptor pfd=ParcelFileDescriptor.open(oldFile,ParcelFileDescriptor.MODE_READ_WRITE)){
            if(oldFile.renameTo(newFile)){
                Toast.makeText(context, "successfully changed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "unable to changed", Toast.LENGTH_SHORT).show();
            }
            try{
                pfd.close();
            }catch (IOException ignored){
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
        }

        return newFile;

    }
    public void setGapBetweenButtonLayer(int gapInDP){
        int dp = (int) convertDpToPixel(gapInDP);
        binding.gapBetweenLayout.getLayoutParams().height = dp;
    }
    public void setOnNegativeButtonClick(View.OnClickListener onClickListener){
        binding.cancelButton.setOnClickListener(onClickListener);
    }
    public void setDialogBackground(int color){
        binding.dialogBg.setBackgroundColor(color);
    }
    public void setDialogCancelable(boolean cancelable){
        setCancelable(cancelable);
    }
    public void setButtonLayoutType(DialogStyle dialogStyle){
        if(dialogStyle == DialogStyle.DEFAULT){
            binding.cancelButton.setBackground(null);
            binding.cancelButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
            binding.renameButton.setBackground(null);
            binding.renameButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }
    public void setButtonLayoutGravity(int gravity){
        binding.buttonLayout.setGravity(gravity);
    }
    public void setTitleGravity(int gravity){
        binding.titleLayout.setGravity(gravity);
    }
    public void setTitleTextSize(int sizeInDp){
        float dp = convertDpToPixel(sizeInDp);
        binding.renameDialogTitle.setTextSize(dp);
    }
    public void setButtonGap(float gapInDp){
        float dp = convertDpToPixel(gapInDp);
        binding.buttonGap.getLayoutParams().width = (int) dp;
    }
    public void setFileExtension(String extension){
        binding.formatTv.setVisibility(View.VISIBLE);
        binding.formatTv.setText(extension);
    }
    public void removeExtension(){
        binding.formatTv.setVisibility(View.GONE);
    }
    public void setCrossButtonPadding(int paddingInDp){
        float dp = convertDpToPixel(paddingInDp);
        binding.buttonGap.getLayoutParams().width = (int) dp;

        binding.clearText.setPadding((int)dp,(int)dp,(int)dp,(int)dp);
    }
    public void setEditTextHint(CharSequence hint){
        binding.renameEditText.setHint(hint);
    }
    public void setTitle(String message){
        binding.titleLayout.setVisibility(View.VISIBLE);
        binding.renameDialogTitle.setText(message);
    }
    public void setTitleVisibility(int visibility){
        if(visibility == View.GONE || visibility == View.INVISIBLE){
            binding.titleLayout.setVisibility(View.GONE);
        }else{
            binding.titleLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setWarning(String message){
        binding.descriptionLayout.setVisibility(View.VISIBLE);
        binding.renameDialogDescription.setText(message);
    }
    public void setWarningVisibility(int visibility){
        if(visibility == View.GONE || visibility == View.INVISIBLE){
            binding.descriptionLayout.setVisibility(View.GONE);
        }else{
            binding.descriptionLayout.setVisibility(View.VISIBLE);
        }
    }
    public void setSelectedEditText(boolean isTextSelected, String textInEditText){
        if(isTextSelected){
            binding.renameEditText.setText(textInEditText);
            binding.renameEditText.requestFocus();
            binding.renameEditText.selectAll();
        }else{
            binding.renameEditText.setText(textInEditText);
        }
    }
    public float convertDpToPixel(float dp){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


}
