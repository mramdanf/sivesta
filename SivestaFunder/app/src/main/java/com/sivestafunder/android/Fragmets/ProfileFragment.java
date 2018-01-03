package com.sivestafunder.android.Fragmets;



import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sivestafunder.android.Activity.EditProfileActivity;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.CircleTransform;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Funder mFunder;

    @BindView(R.id.prof_street)
    TextView tvProfStreet;
    @BindView(R.id.prof_contact)
    TextView tvProfContact;
    @BindView(R.id.prof_email)
    TextView tvProfEmail;

    private ProgressDialog progressDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, rootView);

        getActivity().setTitle("My Profile");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.please_wait_tex));
        progressDialog.setCancelable(false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        populateUserData();

    }

    public void populateUserData() {

        mFunder = Utility.getFunderPrefs(getActivity());

        tvProfStreet.setText(mFunder.getAlamat());
        tvProfEmail.setText(mFunder.getEmail());
        tvProfContact.setText(mFunder.getPhone());

        progressDialog.show();
        new Funder(getActivity())
                .checkLoginApi(
                        mFunder.getEmail(),
                        mFunder.getPassword(),
                        new Funder.FunderModelInf() {
                            @Override
                            public void funderModelApiCallback(Bundle args) {
                                progressDialog.dismiss();
                                Funder fullDataFunder = args.getParcelable(AppConst.OBJ_FUNDER);
                                if (fullDataFunder != null) {
                                    tvProfStreet.setText(fullDataFunder.getAlamat());
                                    tvProfEmail.setText(fullDataFunder.getEmail());
                                    tvProfContact.setText(fullDataFunder.getPhone());
                                }
                            }
                        });
    }
}
