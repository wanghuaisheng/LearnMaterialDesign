package com.witways.mylearn.logics.iservices;

import com.witways.mylearn.logics.models.Repo;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IInfraService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
