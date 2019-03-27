package hw.applicaster.vova.network;

import hw.applicaster.vova.network.model.LinkResponse;
import hw.applicaster.vova.network.model.VideoResponse;
import hw.applicaster.vova.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApplicasterService {

    @GET(Constants.VIDEO_ITEM)
    Call<VideoResponse> getVideos();

    @GET(Constants.LINK_ITEM)
    Call<LinkResponse> getLinks();

}
