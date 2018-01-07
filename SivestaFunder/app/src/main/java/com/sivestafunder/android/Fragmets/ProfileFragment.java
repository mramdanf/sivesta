package com.sivestafunder.android.Fragmets;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.tv_user_planted)
    TextView tvUserPlanted;
    @BindView(R.id.tv_user_participated)
    TextView tvUserParticipated;
    private Funder mFunder;

    @BindView(R.id.prof_street)
    TextView tvProfStreet;
    @BindView(R.id.prof_contact)
    TextView tvProfContact;
    @BindView(R.id.prof_email)
    TextView tvProfEmail;

    private ProgressDialog progressDialog;
    private ProfileFragmentInf mCallback;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public interface ProfileFragmentInf {
        void reqUserProfile();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ProfileFragmentInf) context;
        } catch (ClassCastException c) {
            throw new ClassCastException(context + " Activity should implement ProfileFragmentInf");
        }
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
                                    tvUserPlanted.setText(String.valueOf(fullDataFunder.getPlanted()) + " seed(s)\nplanted");
                                    tvUserParticipated.setText(String.valueOf(fullDataFunder.getParticipated()) + " month(s)\nparticipated");
                                }
                            }
                        });
    }
}
