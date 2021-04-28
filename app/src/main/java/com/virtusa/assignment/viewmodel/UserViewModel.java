package com.virtusa.assignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.virtusa.assignment.model.UserResponse;
import com.virtusa.assignment.network.NoNetworkException;
import com.virtusa.assignment.repository.UserRepository;
import com.virtusa.assignment.util.Constants;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*ViewModel with MutableLiveData which are observed in view models*/
public class UserViewModel extends ViewModel {
    public MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public MutableLiveData<UserResponse> userResponseMutableLiveData = new MutableLiveData<>();

    UserRepository userRepository;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void getUserDetails(int userId, boolean showProgress) {
        if (showProgress)
            progressLiveData.postValue(true);
        userRepository.getUserDetails(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull UserResponse userResponse) {
                        progressLiveData.postValue(false);
                        userResponseMutableLiveData.postValue(userResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        handleErrorResponse(e);
                    }
                });
    }

    private void handleErrorResponse(Throwable e) {
        try {
            if (e instanceof NoNetworkException) {
                errorLiveData.postValue(Constants.check_network);
                progressLiveData.postValue(false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            errorLiveData.postValue(Constants.please_try_again);
        }
        progressLiveData.postValue(false);
    }

}