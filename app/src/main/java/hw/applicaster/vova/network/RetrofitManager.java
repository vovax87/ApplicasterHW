package hw.applicaster.vova.network;

import hw.applicaster.vova.network.model.LinkResponse;
import hw.applicaster.vova.network.model.VideoResponse;
import hw.applicaster.vova.util.Constants;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum RetrofitManager {
    INSTANCE;

    private Retrofit retrofit;
    private ApplicasterService applicasterService;

    private Retrofit getRetrofit() {
        if (retrofit == null)
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    private ApplicasterService getApplicasterService() {
        if (applicasterService == null)
            applicasterService = getRetrofit().create(ApplicasterService.class);
        return applicasterService;
    }

    public void getVideos(Callback<VideoResponse> callback) {
        getApplicasterService().getVideos().enqueue(callback);
    }
    public void getLinks(Callback<LinkResponse> callback) {
        getApplicasterService().getLinks().enqueue(callback);
    }

}
