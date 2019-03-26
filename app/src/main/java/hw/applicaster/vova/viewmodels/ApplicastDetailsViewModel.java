package hw.applicaster.vova.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import hw.applicaster.vova.network.model.Entry;


public class ApplicastDetailsViewModel extends ViewModel {
    final private MutableLiveData entry;

    public ApplicastDetailsViewModel() {
        entry = new MutableLiveData<Entry>();

    }

    public MutableLiveData<Entry> getEntry() {
        return entry;
    }

}
