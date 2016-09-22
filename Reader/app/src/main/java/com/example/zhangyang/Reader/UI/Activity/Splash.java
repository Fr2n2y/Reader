package com.example.zhangyang.Reader.UI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.zhangyang.Reader.R;

/**
 * Created by zhangyang on 16/8/19.
 */
public class Splash extends Activity {
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        img= (ImageView) findViewById(R.id.start_iv);
        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        img.setAnimation(scaleAnim);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(Splash.this,NewsActivity.class));
                finish();
                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(Splash.this,NewsActivity.class));
//                finish();
//                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
//            }
//        }, 2000);
    }
}
