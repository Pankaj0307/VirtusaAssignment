package com.virtusa.assignment.repository;


import com.virtusa.assignment.model.UserResponse;
import com.virtusa.assignment.util.Constants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*interface to make api calls*/
public interface ApiInterface {

    @GET(Constants.get_user_detail)
    Single<UserResponse> getUserDetails(@Path("userId") int userId);
}
