
package hw.applicaster.vova.network.model.general;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaGroup implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("media_item")
    @Expose
    private List<MediaItem> mediaItem = null;
    public final static Parcelable.Creator<MediaGroup> CREATOR = new Creator<MediaGroup>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MediaGroup createFromParcel(Parcel in) {
            return new MediaGroup(in);
        }

        public MediaGroup[] newArray(int size) {
            return (new MediaGroup[size]);
        }

    }
    ;

    protected MediaGroup(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.mediaItem, (MediaItem.class.getClassLoader()));
    }

    public MediaGroup() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MediaItem> getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(List<MediaItem> mediaItem) {
        this.mediaItem = mediaItem;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeList(mediaItem);
    }

    public int describeContents() {
        return  0;
    }

}
