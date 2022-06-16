package com.example.strenamedialog;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.inline.InlineContentView;

public class STRenameDialog extends AlertDialog {
    Context context;
    View view,buttonGap;
    Button renameButton,cancelButton;
    EditText renameEditText;
    ImageView clearTextButton;
    TextView formatTextView,title,description;
    LinearLayout dialogBackground,buttonLayout,titleLayout,descriptionLayout;
    public enum DialogStyle{
        DEFAULT,
        NO_BUTTON
    }
    public STRenameDialog(Context context, DialogStyle dialogStyle){
        super(context,R.style.STDialogTheme);
        this.context = context;
        switch (dialogStyle){
            case DEFAULT:
                initialize(context);
                break;
            case NO_BUTTON:

            default:
                break;
        }
    }
    private void initialize(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.rename_dialog,null);
        renameEditText = view.findViewById(R.id.rename_edit_text);
        clearTextButton = view.findViewById(R.id.clear_text);
        formatTextView = view.findViewById(R.id.format_tv);
        renameButton = view.findViewById(R.id.rename_button);
        cancelButton = view.findViewById(R.id.cancel_button);
        dialogBackground = view.findViewById(R.id.dialog_bg);
        buttonLayout = view.findViewById(R.id.button_layout);
        buttonGap = view.findViewById(R.id.button_gap);
        titleLayout = view.findViewById(R.id.title_layout);
        title = view.findViewById(R.id.rename_dialog_title);
        descriptionLayout = view.findViewById(R.id.description_layout);
        description = view.findViewById(R.id.rename_dialog_description);

        descriptionLayout.setVisibility(View.GONE);


        setView(view);
        setCanceledOnTouchOutside(true);
    }
    public void setDialogBackground(int color){
        dialogBackground.setBackgroundColor(color);
    }
    public void setDialogCancelable(boolean cancelable){
        setCancelable(cancelable);
    }
    public void setButtonLayoutVisibility(int visibility){
        buttonLayout.setVisibility(visibility);
    }
    public void setButtonLayoutGravity(int gravity){
        buttonLayout.setGravity(gravity);
    }
    public void setTitleGravity(int gravity){
        titleLayout.setGravity(gravity);
    }
    public void setTitleTextSize(int sizeInDp){
        float dp = convertDpToPixel(sizeInDp);
        title.setTextSize(dp);
    }
    public void setButtonGap(float gapInDp){
        float dp = convertDpToPixel(gapInDp);
        buttonGap.getLayoutParams().width = (int) dp;
    }
    public void setFileExtension(String extension){
        formatTextView.setVisibility(View.VISIBLE);
        formatTextView.setText(extension);
    }
    public void removeExtension(){
        formatTextView.setVisibility(View.GONE);
    }
    public void setCrossButtonPadding(int paddingInDp){
        float dp = convertDpToPixel(paddingInDp);
        buttonGap.getLayoutParams().width = (int) dp;

        clearTextButton.setPadding((int)dp,(int)dp,(int)dp,(int)dp);
    }
    public void setEditTextHint(CharSequence hint){
        renameEditText.setHint(hint);
    }
    public void setTitle(String message){
        titleLayout.setVisibility(View.VISIBLE);
        title.setText(message);
    }
    public void setTitleVisibility(int visibility){
        if(visibility == View.GONE || visibility == View.INVISIBLE){
            titleLayout.setVisibility(View.GONE);
        }else{
            titleLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setWarning(String message){
        descriptionLayout.setVisibility(View.VISIBLE);
        description.setText(message);
    }
    public void setWarningVisibility(int visibility){
        if(visibility == View.GONE || visibility == View.INVISIBLE){
            descriptionLayout.setVisibility(View.GONE);
        }else{
            descriptionLayout.setVisibility(View.VISIBLE);
        }
    }
    public void setSelectedEditText(boolean isTextSelected, String textInEditText){
        if(isTextSelected){
            renameEditText.setText(textInEditText);
            renameEditText.requestFocus();
            renameEditText.selectAll();
        }else{
            renameEditText.setText(textInEditText);
        }
    }
    public float convertDpToPixel(float dp){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


}
