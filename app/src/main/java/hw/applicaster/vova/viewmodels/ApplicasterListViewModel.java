package hw.applicaster.vova.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hw.applicaster.vova.network.model.Entry;
import hw.applicaster.vova.repo.Repository;


public class ApplicasterListViewModel extends ViewModel {

    private final MutableLiveData<List<Entry>> entriesMutableLiveData = new MutableLiveData<>();
    private final Observer<List<Entry>> videoObserver;
    private final Observer<List<Entry>> linkObserver;
    private List<Entry> entryList = new ArrayList<>();

    public LiveData<List<Entry>> getEntriesMutableLiveData() {
        return entriesMutableLiveData;
    }

    public ApplicasterListViewModel() {
        videoObserver = entries -> {
            entryList.addAll(entries);
            entriesMutableLiveData.postValue(entryList);
            Log.e("Vova","videoObserver");

        };
        linkObserver = entries -> {
            entryList.addAll(entries);
            entriesMutableLiveData.postValue(entryList);
            Log.e("Vova","linkObserver");

        };
        getFeed();
    }

    @Override
    protected void onCleared() {
        Repository.INSTANCE.getVideoResponseLiveData().removeObserver(videoObserver);
        Repository.INSTANCE.getLinkResponseLiveData().removeObserver(linkObserver);
        super.onCleared();
    }

    public void getFeed() {
        Repository.INSTANCE.getVideoResponseLiveData().observeForever(videoObserver);
        Repository.INSTANCE.getLinkResponseLiveData().observeForever(linkObserver);
        getVideos();
        getLinks();


    }

    public void getVideos() {
        Repository.INSTANCE.getVideos();
    }

    public void getLinks() {
        Repository.INSTANCE.getLinks();
    }

    @NonNull
    @Override
    public String toString() {
        return "ApplicasterListViewModel{" +
                hashCode() +
                '}';
    }


}
