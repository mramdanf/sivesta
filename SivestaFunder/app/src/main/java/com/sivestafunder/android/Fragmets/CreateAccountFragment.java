package com.sivestafunder.android.Fragmets;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {

    private CreateAccountFragmentInf mCallback;

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
}
