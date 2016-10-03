package com.bridgelabz.carouseldemo;

/**
 * Created by bridgeit on 11/8/16.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageDetailsActivity extends AppCompatActivity {

    private static final String DRAWABLE_RESOURCE = "resource";
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_image);

        imageView = (ImageView)findViewById(R.id.img);
        button = (Button)findViewById(R.id.btnClose);

        int drawableResource = getIntent().getIntExtra(DRAWABLE_RESOURCE, 0);
        imageView.setImageResource(drawableResource);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
