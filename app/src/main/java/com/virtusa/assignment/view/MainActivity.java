package com.virtusa.assignment.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.virtusa.assignment.R;
import com.virtusa.assignment.model.UserResponse;
import com.virtusa.assignment.repository.UserRepository;
import com.virtusa.assignment.viewmodel.UserViewModel;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.constraint_main)
    ConstraintLayout constraint_main;
    @BindView(R.id.cv_userOne)
    CardView cv_userOne;
    @BindView(R.id.cv_userTwo)
    CardView cv_userTwo;
    @BindView(R.id.cv_userThree)
    CardView cv_userThree;
    @BindView(R.id.tv_userOne)
    TextView tv_userOne;
    @BindView(R.id.tv_userTwo)
    TextView tv_userTwo;
    @BindView(R.id.tv_userThree)
    TextView tv_userThree;

    private UserViewModel userViewModel;
    private UserRepository userRepository;
    private int userId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        userRepository = new UserRepository(this);
        userViewModel = new UserViewModel(userRepository);
        getUserDetails(userId, true);

        userViewModel.progressLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showDialog();
                } else {
                    dismissDialog();
                }
            }
        });

        userViewModel.userResponseMutableLiveData.observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if (userResponse != null) {
                    setUserDetails(userResponse);
                }
            }
        });

        userViewModel.errorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null && s.length() > 0) {
                    showError(s);
                }
            }
        });
    }

    //if getting api error then user can retry
    private void showError(String s) {
        Snackbar.make(constraint_main, s, Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getUserDetails(userId, true);
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_dark))
                .show();
    }

    private void setUserDetails(UserResponse userResponse) {
        String userEmail = userResponse.getData().getEmail();
        switch (userResponse.getData().getId()) {
            case 1:
                cv_userOne.setVisibility(View.VISIBLE);
                tv_userOne.setText(userEmail);
                getUserDetails(3, false);
                break;
            case 3:
                cv_userTwo.setVisibility(View.VISIBLE);
                tv_userTwo.setText(userEmail);
                getUserDetails(10, false);
                break;
            case 10:
                cv_userThree.setVisibility(View.VISIBLE);
                tv_userThree.setText(userEmail);
                break;
        }
    }

    private void getUserDetails(int userId, boolean showProgress) {
        userViewModel.getUserDetails(userId, showProgress);
    }
}