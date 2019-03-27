package hw.applicaster.vova.repo;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import hw.applicaster.vova.network.RetrofitManager;
import hw.applicaster.vova.network.model.Entry;
import hw.applicaster.vova.network.model.LinkResponse;
import hw.applicaster.vova.network.model.VideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public enum Repository {
    INSTANCE;

    private MutableLiveData<List<Entry>> videoResponseLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Entry>> linkResponseLiveData = new MutableLiveData<>();
    //todo add network states

    public MutableLiveData<List<Entry>> getVideoResponseLiveData() {
        return videoResponseLiveData;
    }

    public MutableLiveData<List<Entry>> getLinkResponseLiveData() {
        return linkResponseLiveData;
    }

    public void getVideos() {
        Log.e("Vova","getVideos");

        RetrofitManager.INSTANCE.getVideos( new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (!response.isSuccessful()) {
                    onFailure(call, null);
                    return;
                }
                Log.e("Vova","getVideos onResponse");

                List<Entry> videoEntries = response.body().getVideoEntries();
                // remove empty
                videoEntries.removeIf(entry -> entry == null ||TextUtils.isEmpty(entry.getTitle()));
                videoResponseLiveData.postValue(videoEntries);

            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Log.e("getVideos  error", "Throwable: " + t);
            }
        });

    }

    public void getLinks() {

        RetrofitManager.INSTANCE.getLinks( new Callback<LinkResponse>() {
            @Override
            public void onResponse(Call<LinkResponse> call, Response<LinkResponse> response) {
                if (!response.isSuccessful()) {
                    onFailure(call, null);
                    return;
                }

                List<Entry> linkEntry = response.body().getLinkEntry();
                linkEntry.removeIf(entry ->  entry == null ||TextUtils.isEmpty(entry.getTitle()));
                linkResponseLiveData.postValue(linkEntry);


            }

            @Override
            public void onFailure(Call<LinkResponse> call, Throwable t) {
                Log.e("getLinks  error", "Throwable: " + t);
            }
        });
    }
}
