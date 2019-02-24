package io.mihalek.nikola.gitretrofittest;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.mihalek.nikola.gitretrofittest.base.BaseActivity;
import io.mihalek.nikola.gitretrofittest.service.GitHubService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private GitHubService gitHub;
    private TextView searchBar;
    private Button searchButton;
    private ListView repositoryListView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);
        repositoryListView = findViewById(R.id.repositoryListView);

        adapter = new ArrayAdapter<>(this, R.layout.item_view_repository);
        repositoryListView.setAdapter(adapter);

        gitHub = retrofit.create(GitHubService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        disposables.add(
                RxView.clicks(searchButton)
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ignored -> {
                            getRepos();
                        })
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideProgressBar();
    }

    private void getRepos() {
        showProgressBar();
        disposables.add(gitHub.listReposRx(searchBar.getText().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext((throwable) -> {
                    hideProgressBar();
                    Log.e(TAG, "getRepos: ", throwable);
                    showToast(throwable.getMessage());
                    return Observable.empty();
                })
                .subscribe(repositories -> {
                    Log.d(TAG, "getRepos: " + repositories);
                    adapter.clear();
                    adapter.addAll(repositories);
                    adapter.notifyDataSetChanged();
                    hideProgressBar();
                })
        );
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
