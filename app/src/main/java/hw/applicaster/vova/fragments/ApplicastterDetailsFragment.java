package hw.applicaster.vova.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import hw.applicaster.vova.R;
import hw.applicaster.vova.databinding.DetailsFragmentBinding;
import hw.applicaster.vova.network.model.Entry;
import hw.applicaster.vova.network.model.general.Type;
import hw.applicaster.vova.viewmodels.ApplicastDetailsViewModel;

public class ApplicastterDetailsFragment extends Fragment {

    private static final String ENTRY = "entry";
    private ApplicastDetailsViewModel mViewModel;
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private DetailsFragmentBinding binding;

    public static ApplicastterDetailsFragment newInstance(@NonNull Entry entry) {
        ApplicastterDetailsFragment fragment = new ApplicastterDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ENTRY, entry);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(ApplicastDetailsViewModel.class);

        if (getArguments() == null || getArguments().isEmpty()) return binding.getRoot();

        Entry entry = getArguments().getParcelable(ENTRY);
        if (entry != null) {
            binding.setEntry(entry);
        }

        if (entry != null) {
            if (entry.getType().getValue().equals(Type.VIDEO)) {
                preperPlayer(entry);
            } else if (entry.getType().getValue().equals(Type.LINK)) {
                setWebView(entry);
                disableScrolling();
            }
        }

        return binding.getRoot();
    }

    private void disableScrolling() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) binding.place.getLayoutParams();
        params.setBehavior(new AppBarLayout.Behavior());
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return false;
            }
        });
    }

    private void setWebView(Entry entry) {
        WebView webView = new WebView(getContext());
        binding.base.addView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        ;
        webView.getSettings().setMixedContentMode(0);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl(entry.getLink().getHref());
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void preperPlayer(@NonNull Entry entry) {
        String src = entry.getContent().getSrc();
        if (TextUtils.isEmpty(src)) return;
        player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
        playerView = new PlayerView(getContext());
        playerView.setPlayer(player);
        Uri uri = Uri.parse(src);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
        player.setPlayWhenReady(true);

        if (binding.base.indexOfChild(playerView) == -1) {
            binding.base.addView(playerView);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new HlsMediaSource.Factory(new DefaultHttpDataSourceFactory("video/hls"))
                .createMediaSource(uri);
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause() {
        releasePlayer();
        super.onPause();
    }


}
