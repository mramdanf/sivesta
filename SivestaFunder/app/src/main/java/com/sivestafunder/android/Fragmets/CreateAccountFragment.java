package com.sivestafunder.android.Fragmets;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
public class CreateAccountFragment extends Fragment {

    private CreateAccountFragmentInf mCallback;
    private ProgressDialog mProgressDialog;

    @BindView(R.id.in_full_name)
    EditText etFullName;
    @BindView(R.id.in_email)
    EditText etEmail;
    @BindView(R.id.in_password_create)
    EditText etPassword;
    @BindView(R.id.in_conf_password)
    EditText etConfPass;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    public interface CreateAccountFragmentInf {
        void createAccountClickListener(Bundle args);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
        ButterKnife.bind(this, rootView);

        getActivity().setTitle("Create Account");

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (CreateAccountFragmentInf) context;
        } catch (ClassCastException c) {
            throw new ClassCastException(context +  " Activity should implement Creteacctounfragmentinf");
        }
    }

    @OnClick(R.id.btn_back_login) public void btnBackLoginClickHandle(View v) {
        Bundle args = new Bundle();
        args.putInt(AppConst.VIEW_ID, v.getId());

        mCallback.createAccountClickListener(args);
    }

    @OnClick(R.id.btn_submit_create_acc) public void btnSubmitCreateHandle(View v) {

        String password = etPassword.getText().toString();
        String confPass = etConfPass.getText().toString();

        if (!password.toLowerCase().equals(confPass.toLowerCase())) {
            Toast.makeText(getActivity(), "Password and confirm password not match.", Toast.LENGTH_LONG).show();
            return;
        }

        mProgressDialog.show();
        Funder f = new Funder(getActivity());
        f.setName(etFullName.getText().toString());
        f.setEmail(etEmail.getText().toString());
        f.setPassword(etPassword.getText().toString());

        Bundle args = new Bundle();
        args.putInt(AppConst.VIEW_ID, v.getId());
        args.putParcelable(AppConst.OBJ_FUNDER, f);
        mCallback.createAccountClickListener(args);

    }

    public void showCreateAccountResult(Funder f) {
        mProgressDialog.dismiss();
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.putExtra(AppConst.OBJ_FUNDER, f);
        startActivity(i);
        getActivity().finish();
    }
}
