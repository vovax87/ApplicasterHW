
package hw.applicaster.vova.network.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResponse implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("entry")
    @Expose
    private List<Entry> videoEntries = null;

    public final static Parcelable.Creator<VideoResponse> CREATOR = new Creator<VideoResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VideoResponse createFromParcel(Parcel in) {
            return new VideoResponse(in);
        }

        public VideoResponse[] newArray(int size) {
            return (new VideoResponse[size]);
        }

    }
    ;

    protected VideoResponse(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.videoEntries, (Entry.class.getClassLoader()));
    }

    public VideoResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Entry> getVideoEntries() {
        return videoEntries;
    }

    public void setVideoEntries(List<Entry> videoEntries) {
        this.videoEntries = videoEntries;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeList(videoEntries);
    }

    public int describeContents() {
        return  0;
    }

}
