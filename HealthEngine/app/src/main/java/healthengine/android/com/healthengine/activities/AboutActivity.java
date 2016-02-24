package healthengine.android.com.healthengine.activities;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.utils.Utility;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AppointmentPractionersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by jsn.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener{

    private Dialog dialogShare;

    TextView about_text,about_back_button,termsOfUse;
    ImageButton about_share_imagebutton;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);


        setContentView(R.layout.activity_about);
        initUI();


    }

    private void initUI() {
        about_text=(TextView)findViewById(R.id.about_text);
        about_back_button=(TextView)findViewById(R.id.about_back_button);
        about_back_button.setOnClickListener(this);
        about_share_imagebutton=(ImageButton)findViewById(R.id.about_share_imagebutton);
        about_share_imagebutton.setOnClickListener(this);
        termsOfUse = (TextView) findViewById(R.id.termsOfUse);
        termsOfUse.setOnClickListener(this);
    }


    public void showShareDialog(){

        dialogShare = new Dialog(this);
        dialogShare.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogShare.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogShare.setContentView(R.layout.layout_share);
        dialogShare.setCanceledOnTouchOutside(false);
        dialogShare.setCancelable(false);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.btnDialogShareCancel:
                        dialogShare.dismiss();
                        break;
                    case R.id.btnDialogShareFacebook:
                        sharetoFb();
                        break;
                    case R.id.btnDialogShareTwitter:
                        shareTwiter();
                        break;
                    case R.id.btnDialogShareMail:
                        shareWithMail();
                        break;
                }
            }
        };
        ( dialogShare.findViewById(R.id.btnDialogShareCancel)).setOnClickListener(onClickListener);
        (dialogShare.findViewById(R.id.btnDialogShareTwitter)).setOnClickListener(onClickListener);
        ( dialogShare.findViewById(R.id.btnDialogShareFacebook)).setOnClickListener(onClickListener);
        ( dialogShare.findViewById(R.id.btnDialogShareMail)).setOnClickListener(onClickListener);


        Window window = dialogShare.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        dialogShare.show();

    }

    private void sharetoFb() {

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);


        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("HealthEngine")
                    .setContentDescription(
                            getResources().getString(R.string.about_text))
                    .setContentUrl(Uri.parse("http://www.google.com"))
                    .setImageUrl(Uri.parse("https://pbs.twimg.com/profile_images/611013963086598145/oL-STDoh.png"))
                    .build();

            shareDialog.show(linkContent);
        }
        hideShareDialog();
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void shareTwiter() {
        Intent intent = getTwitterIntent(this, about_text.getText().toString(), "com.twitter.android");
        if(intent!=null){
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please install Twitter Application", Toast.LENGTH_SHORT).show();
        }
        hideShareDialog();
    }

    public Intent getTwitterIntent(Context ctx, String shareText, String application){

        Intent shareIntent = ctx.getPackageManager().getLaunchIntentForPackage(application);
        if (shareIntent != null) {
            shareIntent = new Intent(Intent.ACTION_SEND);
//            shareIntent.setClassName("com.twitter.android",
//                    "com.twitter.android.PostActivity");
            shareIntent.setPackage(application);
            shareIntent.setType("text/*");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
            return shareIntent;
        }
       return null;
    }

    public void hideShareDialog(){
        if((null != dialogShare)&&(dialogShare.isShowing())) {
            dialogShare.dismiss();
        }
    }



    private void shareWithMail(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("mailto:");
        buffer.append("");
        buffer.append("?subject=");
        buffer.append("Helath Engine");
        buffer.append("&body=");
        buffer.append(about_text.getText().toString());

        String uriString = buffer.toString().replace(" ", "%20").replace("#","%23");

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uriString));

        startActivity(intent);
        hideShareDialog();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.about_back_button:
                finish();
                break;

            case R.id.about_share_imagebutton:
                showShareDialog();
                break;

            case R.id.termsOfUse:
                Utility.goToNextActivity(this, TermsOfUseActivity_.class, null, false);
                break;

        }
    }
}
