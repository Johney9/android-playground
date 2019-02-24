package io.mihalek.nikola.gitretrofittest.base;

import android.arch.lifecycle.LifecycleOwner;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import io.mihalek.nikola.gitretrofittest.R;
import io.mihalek.nikola.gitretrofittest.service.RetrofitFactory;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {

    protected final CompositeDisposable disposables = new CompositeDisposable();
    protected final Retrofit retrofit = RetrofitFactory.createRetrofit();
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        progressBar.setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        progressBar.setVisibility(View.GONE);
        progressBar.setId(351937);
        setViewToCenter(progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressBar();
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposables.clear();
    }

    @LayoutRes
    protected int getContentView() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setViewToCenter(View centeredView) {

        ViewGroup root = (ViewGroup) ((ViewGroup) (findViewById(android.R.id.content))).getChildAt(0);
        int wrap = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(wrap, wrap);
        centeredView.setLayoutParams(params);
        /*
        String className = root.getClass().getSimpleName();
        switch (className) {
            case "ConstraintLayout":
                ConstraintSet set = new ConstraintSet();
                set.centerVertically(centeredView.getId(), ConstraintSet.PARENT_ID);
                set.centerHorizontally(centeredView.getId(), ConstraintSet.PARENT_ID);
                set.applyTo((ConstraintLayout) root);
                break;
            default:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(wrap, wrap);
                layoutParams.gravity = Gravity.CENTER;
                params = layoutParams;
                break;
        }
        centeredView.setLayoutParams(params);
        root.addView(centeredView);
        */
        root.addView(centeredView);
        ConstraintSet set = new ConstraintSet();
        set.centerVertically(centeredView.getId(), ConstraintSet.PARENT_ID);
        set.centerHorizontally(centeredView.getId(), ConstraintSet.PARENT_ID);
        set.constrainHeight(centeredView.getId(), wrap);
        set.constrainWidth(centeredView.getId(), wrap);
        set.applyTo((ConstraintLayout) root);
    }

    protected void showProgressBar() {
        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    protected void hideProgressBar() {
        if(progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void onUnknownError(String errorMessage) {
        showToast(errorMessage);
    }

    public void onTimeout() {
        showToast("Timeoet");
    }

    public void onNetworkError() {
        showToast("Network error");
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
