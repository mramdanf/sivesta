package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sivestafunder.android.ApiEndPoint.KomoditasEndPoint;
import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.ApiRespWrapper.ListNewSeeds;
import com.sivestafunder.android.Fragmets.ArticleFragment;
import com.sivestafunder.android.Fragmets.CatalogFragment;
import com.sivestafunder.android.Fragmets.HomeFragment;
import com.sivestafunder.android.Fragmets.MySeedsFragment;
import com.sivestafunder.android.Fragmets.ProfileFragment;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.CircleTransform;
import com.sivestafunder.android.Models.Artikel;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.HomeFragmentInf,
        BottomNavigationView.OnNavigationItemSelectedListener,
        ArticleFragment.ArticleFragmentInf,
        CatalogFragment.CataglogFragmentInf,
        MySeedsFragment.MySeedsFragmentInf,
        ProfileFragment.ProfileFragmentInf {

    private ProgressDialog mProgressDialog;
    private KomoditasEndPoint mKomoditasService;
    private Funder mLoggedInFunder;
    private String mCollapsingTitle;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mainToolBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.header_nameof_user)
    TextView tvUserFullName;
    @BindView(R.id.profile_pic)
    ImageView imgviewProfilePic;
    @BindView(R.id.wrapper_profile_btn)
    LinearLayout wrapperProfileBtn;
    @BindView(R.id.tv_planted_info)
    TextView tvPlantedInfo;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;


    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCollapsingTitle = "Home";
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(mCollapsingTitle);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        setFullScreen();

        Intent i = getIntent();
        mLoggedInFunder = i.getParcelableExtra(AppConst.OBJ_FUNDER);
        populateHeaderData();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.please_wait_tex));
        mProgressDialog.setCancelable(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setUpFragment(new HomeFragment(), false);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle args = new Bundle();
        switch (item.getItemId()) {
            case R.id.act_home:
                setUpFragment(new HomeFragment(), false);
                break;
            case R.id.act_article:
                setUpFragment(new ArticleFragment(), false);
                break;
            case R.id.act_catalog:
                setUpFragment(new CatalogFragment(), false);
                break;
            case R.id.act_myseed:
                setUpFragment(new MySeedsFragment(), false);
                break;
            case R.id.act_profile:
                ProfileFragment pf = new ProfileFragment();
                setUpFragment(pf, false);
                break;
        }
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void reqListKomoditas() {
        populateHeaderData();
        Komoditas km = new Komoditas();
        km.getNewKomoditas(
                new Komoditas.KomoditasModelInf() {
                    @Override
                    public void komoditasModelApiCallback(Bundle args) {
                        HomeFragment hf = (HomeFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);
                        ListKomoditasResp respListKom = args.getParcelable(AppConst.LIST_OBJ_KOMODITAS);
                        hf.showKomoditas(respListKom);
                    }
                }
        );
    }

    @Override
    public void reqListArtikel() {
        populateHeaderData();
        Artikel a = new Artikel();
        a.getArtikelFromApi(
                new Artikel.ArtikelModelInterface() {
                    @Override
                    public void getArtikelCallback(Bundle args) {
                        ListArtikelResp respListArtikel = args.getParcelable(AppConst.LIST_OBJ_ARTIKEL);
                        HomeFragment hf = (HomeFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);
                        hf.showArtikel(respListArtikel);

                    }
                }
        );
    }

    @Override
    public void reqFullListArticle() {
        populateHeaderData();
        Artikel fullArticle = new Artikel();
        fullArticle.getArtikelFromApi(
                new Artikel.ArtikelModelInterface() {
                    @Override
                    public void getArtikelCallback(Bundle args) {
                        ListArtikelResp respListArtikel = args.getParcelable(AppConst.LIST_OBJ_ARTIKEL);
                        ArticleFragment af = (ArticleFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);
                        Log.d(LOG_TAG, "INVOKED");
                        af.showFullArtikel(respListArtikel);
                    }
                }
        );
    }

    @Override
    public void reqListMySeeds(String filter) {
        populateHeaderData();
        new Kontrak().getKontrakMySeeds(
                mLoggedInFunder.getIdFunder(),
                mLoggedInFunder.getEmail(),
                mLoggedInFunder.getPassword(),
                filter,
                new Kontrak.KontrakModelInf() {
                    @Override
                    public void kontrakModelInfCallback(Bundle args) {
                        ListNewSeeds myseeds = args.getParcelable(AppConst.LIST_OBJ_KONTRAK);
                        MySeedsFragment mf = (MySeedsFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);
                        mf.showMySeedsList(myseeds);
                    }
                }
        );
    }

    @Override
    public void mySeedsFragmentClickListener(Bundle args) {
        int clickedItemId = args.getInt(AppConst.VIEW_ID);

        switch (clickedItemId) {
            case R.id.tv_view_seeds:
                bottomNavigationView.setSelectedItemId(R.id.act_catalog);
                setUpFragment(new CatalogFragment(), false);
                break;
        }
    }

    @Override
    public void homeFragmentClickListener(Bundle args) {
        int viewId = args.getInt(AppConst.VIEW_ID);
        switch (viewId) {
            case R.id.more_btn_artikel:
                bottomNavigationView.setSelectedItemId(R.id.act_article);
                setUpFragment(new ArticleFragment(), false);
                break;
            case R.id.more_btn_komoditas:
                bottomNavigationView.setSelectedItemId(R.id.act_catalog);
                setUpFragment(new CatalogFragment(), false);
        }
    }

    @Override
    public void reqFullListKomoditas() {
        populateHeaderData();
        Komoditas fullKomoditas = new Komoditas();
        fullKomoditas.getAllKomoditasApi(
                new Komoditas.KomoditasModelInf() {
                    @Override
                    public void komoditasModelApiCallback(Bundle args) {
                        CatalogFragment cf = (CatalogFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);
                        ListKomoditasResp respListKom = args.getParcelable(AppConst.LIST_OBJ_KOMODITAS);
                        cf.showAllKomoditas(respListKom);
                    }
                }
        );
    }

    @Override
    public void reqUserProfile() {
        new Funder(this)
                .checkLoginApi(
                        mLoggedInFunder.getEmail(),
                        mLoggedInFunder.getPassword(),
                        new Funder.FunderModelInf() {
                            @Override
                            public void funderModelApiCallback(Bundle args) {
                                String msg = args.getString(AppConst.TAG_MSG);
                                if (msg.equals(AppConst.TAG_SUCCESS)) {
                                    Funder retFunder = args.getParcelable(AppConst.OBJ_FUNDER);
                                    ProfileFragment pf = (ProfileFragment) getSupportFragmentManager()
                                            .findFragmentById(R.id.fragment_container);
                                    pf.populateUserData(retFunder);

                                }
                            }
                        }
                );
    }

    @OnClick(R.id.btn_logout)
    public void doLogout() {
        new Funder(this).logout();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 2) {

            new Funder(this)
                    .checkLoginApi(
                            mLoggedInFunder.getEmail(),
                            mLoggedInFunder.getPassword(),
                            new Funder.FunderModelInf() {
                                @Override
                                public void funderModelApiCallback(Bundle args) {
                                    String msg = args.getString(AppConst.TAG_MSG);
                                    if (msg.equals(AppConst.TAG_SUCCESS)) {
                                        Funder retFunder = args.getParcelable(AppConst.OBJ_FUNDER);
                                        ProfileFragment pf = (ProfileFragment) getSupportFragmentManager()
                                                .findFragmentById(R.id.fragment_container);
                                        pf.populateUserData(retFunder);

                                    }
                                }
                            }
                    );
        }
    }

    private void setUpFragment(Fragment f, boolean addToBack) {
        toggleBtnProfile(2);
        if (f instanceof HomeFragment) {
            mCollapsingTitle = "Home";
        } else if (f instanceof ArticleFragment) {
            mCollapsingTitle = "Articles";
        } else if (f instanceof CatalogFragment) {
            mCollapsingTitle = "Catalog";
        } else if (f instanceof MySeedsFragment) {
            mCollapsingTitle = "My Seed";
        } else if (f instanceof ProfileFragment) {
            toggleBtnProfile(1);
            mCollapsingTitle = "My Profile";
        }
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_container, f);
        String tag = fragmentTransaction.toString();

        if (addToBack)
            fragmentTransaction.addToBackStack(tag);

        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setFullScreen() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void populateHeaderData() {
        new Funder(this)
                .checkLoginApi(
                        mLoggedInFunder.getEmail(),
                        mLoggedInFunder.getPassword(),
                        new Funder.FunderModelInf() {
                            @Override
                            public void funderModelApiCallback(Bundle args) {
                                Funder funder = args.getParcelable(AppConst.OBJ_FUNDER);
                                if (funder != null) {
                                    tvPlantedInfo.setText(
                                            "You have " + funder.getPlanted()
                                                    + " seeds planted, "
                                                    + funder.getHarvestSoon()
                                                    + " harvest soon"
                                    );
                                    tvUserFullName.setText(funder.getName());
                                    Picasso
                                            .with(MainActivity.this)
                                            .load(funder.getProfilePic())
                                            .error(R.drawable.user_default)
                                            .transform(new CircleTransform())
                                            .into(imgviewProfilePic);
                                }
                            }
                        }
                );
    }

    private void toggleBtnProfile(int show) {
        if (show == 1) {
            wrapperProfileBtn.setVisibility(View.VISIBLE);
            tvPlantedInfo.setVisibility(View.GONE);
            tvWelcome.setText("My Profile");
        } else if (show == 2) {
            wrapperProfileBtn.setVisibility(View.GONE);
            tvPlantedInfo.setVisibility(View.VISIBLE);
            tvWelcome.setText("Welcome to Sivesta");
        }

    }

    @OnClick(R.id.btn_edit_profile)
    public void onViewClicked() {
        startActivityForResult(new Intent(this, EditProfileActivity.class), 2);
    }
}
