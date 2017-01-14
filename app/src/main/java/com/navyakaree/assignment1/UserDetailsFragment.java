package com.navyakaree.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by NavyaKaree on 1/13/17.
 */

public class UserDetailsFragment extends Fragment {

    private static EditText username;
    private static EditText password;
    private static Button login;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_details_fragment,container,false);
        username = (EditText) view.findViewById(R.id.userNameText);
        password = (EditText) view.findViewById(R.id.passwordText);
        login = (Button) view.findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginClicked(view);
            }
        });
        return view;
    }

    LoginListener activitycommander;
    public interface LoginListener {
        public void createMeme(String username, String password);
    }

/// onAttach is called when the activity is attached to the fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activitycommander = (LoginListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    private void loginClicked(View view) {
        activitycommander.createMeme(username.getText().toString(), password.getText().toString());
    }
}
