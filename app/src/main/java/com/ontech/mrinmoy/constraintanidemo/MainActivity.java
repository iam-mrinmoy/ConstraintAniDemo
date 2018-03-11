package com.ontech.mrinmoy.constraintanidemo;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnticipateOvershootInterpolator;

public class MainActivity extends AppCompatActivity {
    private boolean show= false;
    ConstraintLayout constraint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circuit);
        constraint=findViewById(R.id.constraint);
        findViewById(R.id.backgroundImage).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show)
                    hideComponents(); // if the animation is shown, we hide back the views
                else
                    showComponents(); // if the animation is NOT shown, we animate the views
            }
        });
    }

    private void showComponents() {
        this.show = true;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this,R.layout.circuit_detail);
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1200);
        TransitionManager.beginDelayedTransition(constraint, transition);
        constraintSet.applyTo(constraint);
    }
    private void hideComponents() {
        this.show = false;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this,R.layout.circuit);
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1200);
        TransitionManager.beginDelayedTransition(constraint, transition);
        constraintSet.applyTo(constraint);
    }
}
