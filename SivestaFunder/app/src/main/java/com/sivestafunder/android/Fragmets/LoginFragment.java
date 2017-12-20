package com.sivestafunder.android.Fragmets;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sivestafunder.android.Activity.MainActivity;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private LoginFragmentInf mCallback;
    private ProgressDialog mProgressDialog;
    private Context mContext;

    @BindView(R.id.in_email_login)
    EditText etEmail;
    @BindView(R.id.in_password)
    EditText etPassword;


    public LoginFragment() {
        // Required empty public constructor
    }

    public interface LoginFragmentInf {
        void loginFragmentClickListerner(Bundle args);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);

        getActivity().setTitle("Login");
        mContext = getActivity();

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (LoginFragmentInf) context;
        } catch (ClassCastException c) {
            throw new ClassCastException(context + " Activity should implement LoginFragmentInf");
        }
    }

    @OnClick(R.id.btn_submit_login) public void submitLoginClickListener(View v) {
        mProgressDialog.show();
        Bundle args = new Bundle();

        args.putInt(AppConst.VIEW_ID, v.getId());
        args.putString(AppConst.PRF_TAG_EMAIL, etEmail.getText().toString());
        args.putString(AppConst.PRF_TAG_PASS, etPassword.getText().toString());

        mCallback.loginFragmentClickListerner(args);
    }

    @OnClick(R.id.btn_create_account) public void createAccountClickListener(View v) {
        Bundle args = new Bundle();
        args.putInt(AppConst.VIEW_ID, v.getId());

        mCallback.loginFragmentClickListerner(args);
    }

    public void displayLoginResult(Funder f) {
        mProgressDialog.dismiss();
        Intent i = new Intent(mContext, MainActivity.class);
        i.putExtra(AppConst.OBJ_FUNDER, f);
        startActivity(i);
        getActivity().finish();
    }
}
