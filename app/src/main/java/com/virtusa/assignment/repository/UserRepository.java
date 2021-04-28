package com.virtusa.assignment.repository;

import android.content.Context;

import com.virtusa.assignment.model.UserResponse;
import com.virtusa.assignment.network.CommonRetrofit;

import io.reactivex.Single;


/*repository*/
public class UserRepository extends CommonRetrofit<ApiInterface> {

    public UserRepository(Context context) {
        super(context);
    }

    @Override
    protected Class getRestClass() {
        return ApiInterface.class;
    }


    public Single<UserResponse> getUserDetails(int userId) {
        return getNetworkService().getUserDetails(userId);
    }
}
