package io.mihalek.nikola.gitretrofittest.service;

import java.util.List;

import io.mihalek.nikola.gitretrofittest.model.Repository;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repository>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<Repository>> listReposRx(@Path("user") String user);
}