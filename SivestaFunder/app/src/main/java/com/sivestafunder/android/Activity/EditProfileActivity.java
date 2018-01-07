package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.CircleTransform;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.et_profile_name)
    EditText etProfileName;
    @BindView(R.id.et_profile_email)
    EditText etProfileEmail;
    @BindView(R.id.et_profile_phone)
    EditText etProfilePhone;
    @BindView(R.id.et_profile_address)
    EditText etProfileAddress;
    @BindView(R.id.profile_img)
    ImageView profileImg;

    private int PICK_IMAGE_REQUEST = 1;
    private Funder mLoggedInFunder;
    private Uri mProfilePicUri;
    private final String LOG_TAG = this.getClass().getSimpleName();
    private ProgressDialog mProgresDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        setTitle("Edit Profile");

        mProgresDialog = new ProgressDialog(this);
        mProgresDialog.setMessage(getString(R.string.please_wait_tex));
        mProgresDialog.setCancelable(false);

        mLoggedInFunder = Utility.getFunderPrefs(this);
        populateCurrentUserData();
    }

    @OnClick(R.id.btn_save_profile)
    public void onBtnSaveProfileClicked() {
        final Funder newFunder = new Funder(this);
        newFunder.setName(etProfileName.getText().toString());
        newFunder.setEmail(etProfileEmail.getText().toString());
        newFunder.setPhone(etProfilePhone.getText().toString());
        newFunder.setAlamat(etProfileAddress.getText().toString());
        newFunder.setIdFunder(mLoggedInFunder.getIdFunder());

        mProgresDialog.show();
        new Funder(this)
                .updateProfileApi(
                        newFunder,
                        mLoggedInFunder.getEmail(),
                        mLoggedInFunder.getPassword(),
                        new Funder.FunderModelInf() {
                            @Override
                            public void funderModelApiCallback(Bundle args) {
                                mProgresDialog.dismiss();
                                String msg = args.getString(AppConst.TAG_MSG);
                                if (msg.equals(AppConst.TAG_SUCCESS)) {
                                    newFunder.setPassword(mLoggedInFunder.getPassword());
                                    newFunder.setIdFunder(mLoggedInFunder.getIdFunder());
                                    Utility.setFundersPrefs(EditProfileActivity.this, newFunder);
                                    Toast.makeText(EditProfileActivity.this, "Profile berhasil diupdate.", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(EditProfileActivity.this, "Terjadi error. Profile gagal diupdate.", Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                );
    }

    @OnClick(R.id.btn_selesai_editprofile)
    public void onBtnChangePhotoClicked() {
        /*Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);*/
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", 200);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            mProfilePicUri = data.getData();

            Picasso
                    .with(this)
                    .load(mProfilePicUri)
                    .error(R.drawable.user_default)
                    .transform(new CircleTransform())
                    .into(profileImg);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void populateCurrentUserData() {
        mProgresDialog.show();
        new Funder(this)
                .checkLoginApi(
                        mLoggedInFunder.getEmail(),
                        mLoggedInFunder.getPassword(),
                        new Funder.FunderModelInf() {
                            @Override
                            public void funderModelApiCallback(Bundle args) {
                                mProgresDialog.dismiss();
                                Funder fullDataFunder = args.getParcelable(AppConst.OBJ_FUNDER);
                                if (fullDataFunder != null) {
                                    etProfileName.setText(fullDataFunder.getName());
                                    etProfileAddress.setText(fullDataFunder.getAlamat());
                                    etProfileEmail.setText(fullDataFunder.getEmail());
                                    etProfilePhone.setText(fullDataFunder.getPhone());
                                    Picasso
                                            .with(EditProfileActivity.this)
                                            .load(fullDataFunder.getProfilePic())
                                            .error(R.drawable.user_default)
                                            .transform(new CircleTransform())
                                            .into(profileImg);
                                }
                            }
                        });
    }

}
