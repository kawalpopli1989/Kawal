package healthengine.android.com.healthengine.fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.activities.AppointmentsActivity_;
import healthengine.android.com.healthengine.activities.PracticeSearchActivity_;
import healthengine.android.com.healthengine.swipemenu.SwipeMenu;
import healthengine.android.com.healthengine.swipemenu.SwipeMenuCreator;
import healthengine.android.com.healthengine.swipemenu.SwipeMenuItem;
import healthengine.android.com.healthengine.swipemenu.SwipeMenuListView;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * Created by jsn on 15/1/16.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment {

    public static final String TAG="HomeFragment";
    boolean buttonsOnTop = true;

    int screenWidth;


    @ViewById
    RelativeLayout general_practitioner_button, general_dentist_layout, general_physiotherapist_layout,
            general_chiropractor_layout, general_psycologist_layout;

    @ViewById
    LinearLayout optometrist_layout, skin_checks_layout, counsellor_layout, podiatrist_layout, audiologist_layout;

    @ViewById
    SwipeMenuListView fav_list, prevous_list;


    FavouritesCustomAdapter mFavouritesCustomAdapter;
    @ViewById
    TextView more_speaciallties_txt;

    @ViewById
    ImageView general_practitioner_image;
    @ViewById
    TextView general_practitioner_text;
    @ViewById
    ImageView general_dentist_image;
    @ViewById
    TextView general_dentist_text;
    @ViewById
    ImageView general_physiotherapist_image;
    @ViewById
    TextView general_physiotherapist_text;
    @ViewById
    ImageView general_chiropractor_image;
    @ViewById
    TextView general_chiropractor_text;
    @ViewById
    ImageView general_psycologist_image;
    @ViewById
    TextView general_psycologist_text;
    @ViewById
    LinearLayout more_specialities;

    public static HomeFragment newInstance() {
        return new HomeFragment_();
    }

    @AfterViews
    void setUIViews() {
//        Model.getInstance().changeToolbarSettings(TAG);

        getScreenWidth();

        mFavouritesCustomAdapter=new FavouritesCustomAdapter(getActivity());

        setUpSwipeOverListView(fav_list);
        fillDataInFavouritesList();
        fillDataInPreviousList();
        setLayouts();
    }



    @Click({R.id.general_practitioner_button, R.id.general_dentist_layout,
            R.id.general_physiotherapist_layout, R.id.general_chiropractor_layout,
            R.id.general_psycologist_layout, R.id.optometrist_layout,
            R.id.skin_checks_layout, R.id.counsellor_layout,
            R.id.podiatrist_layout, R.id.audiologist_layout})
    public void callAppointmentFragment(){

        startActivity(new Intent(getActivity(), AppointmentsActivity_.class));

    }

    private void setLayouts() {
        setMargins(general_practitioner_image, general_practitioner_text, false);
        setMargins(general_dentist_image, general_dentist_text, true);
        setMargins(general_physiotherapist_image, general_physiotherapist_text, true);
        setMargins(general_chiropractor_image, general_chiropractor_text, true);
        setMargins(general_psycologist_image, general_psycologist_text, true);
    }

    public void setMargins(ImageView image, TextView text, boolean halfWidth){

        int usedScreenWidth=screenWidth;
        int extraMargin=20;

        if(halfWidth){
            usedScreenWidth=usedScreenWidth/2;
            extraMargin=40;
        }

        RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        imageParams.leftMargin = (usedScreenWidth-imageParams.width-extraMargin)/2;
        image.setLayoutParams(imageParams);


        RelativeLayout.LayoutParams textParams = (RelativeLayout.LayoutParams) text.getLayoutParams();
        text.measure(0, 0);
        textParams.leftMargin = (usedScreenWidth-text.getMeasuredWidth()-extraMargin)/2;
        textParams.topMargin=Utility.dpToPx(40);
        text.setLayoutParams(textParams);
    }


    View fav_list_footerView;
    public void fillDataInFavouritesList() {

        if(mFavouritesCustomAdapter.getCount()>0) {
            fav_list_footerView = getActivity().getLayoutInflater().inflate(R.layout.layout_favourite_add_more, null);
        }
        else{
            fav_list_footerView = getActivity().getLayoutInflater().inflate(R.layout.layout_child_add_first, null);
        }
        fav_list_footerView.setClickable(true);
        fav_list_footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goForSearchPractice();
            }
        });
        fav_list.addFooterView(fav_list_footerView);
        fav_list.setAdapter(mFavouritesCustomAdapter);
        Utility.setHeightofListView(fav_list);

    }

    public void fillDataInPreviousList() {

        prevous_list.setAdapter(mFavouritesCustomAdapter);
       Utility.setHeightofListView(prevous_list);

    }

    @Click(R.id.more_speaciallties_txt)
    public void moreSpecialityTextViewClick(){
        if (buttonsOnTop) {
            animateBottom(general_practitioner_image, general_practitioner_text);
            animateBottom(general_dentist_image, general_dentist_text);
            animateBottom(general_physiotherapist_image, general_physiotherapist_text);
            animateBottom(general_chiropractor_image, general_chiropractor_text);
            animateBottom(general_psycologist_image, general_psycologist_text);
            more_specialities.setVisibility(View.VISIBLE);
            more_speaciallties_txt.setVisibility(View.GONE);
        } else {
            animateTop(general_practitioner_image, general_practitioner_text);
            animateTop(general_dentist_image, general_dentist_text);
            animateTop(general_physiotherapist_image, general_physiotherapist_text);
            animateTop(general_chiropractor_image, general_chiropractor_text);
            animateTop(general_psycologist_image, general_psycologist_text);
        }
        buttonsOnTop = !buttonsOnTop;
    }



    private void animateBottom(final ImageView image, final TextView text) {

        final RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        final RelativeLayout.LayoutParams textParams = (RelativeLayout.LayoutParams) text.getLayoutParams();


        ValueAnimator animator = ValueAnimator.ofInt(imageParams.leftMargin, imageParams.leftMargin-getTextViewWidth(text)/2-Utility.dpToPx(10));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                imageParams.leftMargin = (Integer) valueAnimator.getAnimatedValue();
                image.requestLayout();
            }
        });

        animator.setDuration(300);
        animator.start();

        ValueAnimator animator1 = ValueAnimator.ofInt(textParams.topMargin, textParams.topMargin-getImageHeight(image)/2-Utility.dpToPx(15));
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                textParams.topMargin = (Integer) valueAnimator.getAnimatedValue();
                text.requestLayout();
            }
        });
        animator1.setDuration(300);
        animator1.start();

        ValueAnimator animator2 = ValueAnimator.ofInt(textParams.leftMargin, textParams.leftMargin+getImageWidth(image)/2);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                textParams.leftMargin = (Integer) valueAnimator.getAnimatedValue();
                text.requestLayout();
            }
        });
        animator2.setDuration(300);
        animator2.start();

    }

    private void animateTop(final ImageView image, final TextView text) {

        final RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        final RelativeLayout.LayoutParams textParams = (RelativeLayout.LayoutParams) text.getLayoutParams();


        ValueAnimator animator = ValueAnimator.ofInt(imageParams.leftMargin, imageParams.leftMargin+getTextViewWidth(text)/2+Utility.dpToPx(10));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                imageParams.leftMargin = (Integer) valueAnimator.getAnimatedValue();
                image.requestLayout();
            }
        });

        animator.setDuration(300);
        animator.start();

        ValueAnimator animator1 = ValueAnimator.ofInt(textParams.topMargin, textParams.topMargin+getImageHeight(image)/2+Utility.dpToPx(15));
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                textParams.topMargin = (Integer) valueAnimator.getAnimatedValue();
                text.requestLayout();
            }
        });
        animator1.setDuration(300);
        animator1.start();

        ValueAnimator animator2 = ValueAnimator.ofInt(textParams.leftMargin, textParams.leftMargin-getImageWidth(image)/2);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                textParams.leftMargin = (Integer) valueAnimator.getAnimatedValue();
                text.requestLayout();
            }
        });
        animator2.setDuration(300);
        animator2.start();

    }

    public void goForSearchPractice(){
        startActivity(new Intent(getActivity(), PracticeSearchActivity_.class));
    }





    public int getImageWidth(ImageView image){
        RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        return imageParams.width;

    }

    public int getImageHeight(ImageView image){
        RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
        return imageParams.height;

    }

    public int getTextViewWidth(TextView text){
        text.measure(0, 0);
        return text.getMeasuredWidth();
    }




    public void getScreenWidth() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
    }



    public class FavouritesCustomAdapter extends BaseAdapter{

        LayoutInflater inflater;
        Context context;
        FavouritesCustomAdapter(Context context){
            this.context=context;
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        ViewHolder holder;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            holder=null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=inflater.inflate(R.layout.layout_child_favourite, null);

                convertView.setTag(holder);

            }
            else{
                holder= (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder{

        }

    }



    private void setUpSwipeOverListView(SwipeMenuListView mListView){

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
                deleteItem.setWidth(Utility.dpToPx(90));

                // set a icon
                deleteItem.setIcon(R.drawable.delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);


        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,
                                           int index) {

                switch (index) {
                    case 0:
                        // open
//                        showDeleteDialog(position);
                        break;

                }
                return false;
            }
        });

    }
}
