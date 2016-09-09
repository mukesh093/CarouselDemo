package com.bridgelabz.carouseldemo;

/**
 * Created by bridgeit on 11/8/16.
 */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemFragment extends Fragment {

    private static final String POSITION = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURCE = "resource";

    private int screenWidth;
    private int screenHeight;

    private int[] imageArray = new int[]{R.mipmap.assassins_creed, R.mipmap.avatar_3d,
            R.mipmap.call_of_duty_black_ops_3, R.mipmap.dota_2, R.mipmap.halo_5,
            R.mipmap.left_4_dead_2, R.mipmap.need_for_speed_most_wanted, R.mipmap.starcraft,
            R.mipmap.the_witcher_3, R.mipmap.tomb_raider};

    public static Fragment newInstance(MainActivity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITION, pos);
        b.putFloat(SCALE, scale);

        return Fragment.instantiate(context, ItemFragment.class.getName(), b);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int position = this.getArguments().getInt(POSITION);
        float scale = this.getArguments().getFloat(SCALE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 2, screenHeight / 2);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_image, container, false);

        TextView textView = (TextView) linearLayout.findViewById(R.id.text);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.pagerImg);

        textView.setText("Carousel item: " + position);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(imageArray[position]);

        //handling click event
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImageDetailsActivity.class);
                intent.putExtra(DRAWABLE_RESOURCE, imageArray[position]);
                startActivity(intent);
            }
        });

        root.setScaleBoth(scale);

        return linearLayout;
    }

    /**
     * Get device screen width and height
     */
    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}