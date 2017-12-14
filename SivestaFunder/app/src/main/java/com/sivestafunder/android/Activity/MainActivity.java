package com.sivestafunder.android.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sivestafunder.android.ApiEndPoint.ArtikelEndPoint;
import com.sivestafunder.android.ApiEndPoint.KomoditasEndPoint;
import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.Fragmets.ArticleFragment;
import com.sivestafunder.android.Fragmets.CatalogFragment;
import com.sivestafunder.android.Fragmets.HomeFragment;
import com.sivestafunder.android.Fragmets.MySeedsFragment;
import com.sivestafunder.android.Fragmets.ProfileFragment;
import com.sivestafunder.android.Helpers.AppConst;
import com.sivestafunder.android.Helpers.RetrofitHelper;
import com.sivestafunder.android.Helpers.Utility;
import com.sivestafunder.android.Models.Artikel;
import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.Komoditas;
import com.sivestafunder.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.HomeFragmentInf,
        BottomNavigationView.OnNavigationItemSelectedListener,
        ArticleFragment.ArticleFragmentInf,
        CatalogFragment.CataglogFragmentInf {

    private ProgressDialog mProgressDialog;
    private KomoditasEndPoint mKomoditasService;
    private Funder mLoggedInFunder;

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


    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Home");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        setFullScreen();

        Intent i = getIntent();
        mLoggedInFunder = i.getParcelableExtra(AppConst.OBJ_FUNDER);
        tvUserFullName.setText(mLoggedInFunder.getName());

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
    public boolean onNavigationItemSelected(@android.support.annotation.NonNull MenuItem item) {
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
                args.putParcelable(AppConst.OBJ_FUNDER, mLoggedInFunder);
                pf.setArguments(args);
                setUpFragment(pf, false);
                break;
        }
        return true;
    }

    @Override
    public void reqListKomoditas() {
        Komoditas km = new Komoditas();
        km.getPopularKomoditasApi(
                new Komoditas.KomoditasModelInf() {
                    @Override
                    public void getPopularKomoditasCallback(Bundle args) {
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
        Artikel fullArticle = new Artikel();
        fullArticle.getArtikelFromApi(
                new Artikel.ArtikelModelInterface() {
                    @Override
                    public void getArtikelCallback(Bundle args) {
                        ListArtikelResp respListArtikel = args.getParcelable(AppConst.LIST_OBJ_ARTIKEL);
                        ArticleFragment af = (ArticleFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);
                        af.showFullArtikel(respListArtikel);
                    }
                }
        );
    }

    @Override
    public void reqFullListKomoditas() {
        Komoditas fullKomoditas = new Komoditas();
        fullKomoditas.getAllKomoditasApi(
                new Komoditas.KomoditasModelInf() {
                    @Override
                    public void getPopularKomoditasCallback(Bundle args) {
                        CatalogFragment cf = (CatalogFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragment_container);
                        ListKomoditasResp respListKom = args.getParcelable(AppConst.LIST_OBJ_KOMODITAS);
                        cf.showAllKomoditas(respListKom);
                    }
                }
        );
    }

    private void setUpFragment(Fragment f, boolean addToBack) {
        android.support.v4.app.FragmentTransaction fragmentTransaction =
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

}
