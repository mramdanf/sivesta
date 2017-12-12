package com.sivestafunder.android.Fragmets;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Funder mFunder;

    @BindView(R.id.prof_street)
    TextView tvProfStreet;
    @BindView(R.id.prof_location)
    TextView tvProfLocation;
    @BindView(R.id.prof_contact)
    TextView tvProfContact;
    @BindView(R.id.prof_email)
    TextView tvProfEmail;


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

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mFunder = args.getParcelable(AppConst.OBJ_FUNDER);
            populateUserData();
        }
    }

    private void populateUserData() {
        tvProfStreet.setText(mFunder.getAlamat());
        tvProfEmail.setText(mFunder.getEmail());
        tvProfContact.setText(mFunder.getPhone());
    }
}
