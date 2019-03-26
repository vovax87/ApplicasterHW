
package hw.applicaster.vova.network.model.general;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaItem implements Parcelable
{

    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("scale")
    @Expose
    private String scale;
    @SerializedName("key")
    @Expose
    private String key;
    public final static Parcelable.Creator<MediaItem> CREATOR = new Creator<MediaItem>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MediaItem createFromParcel(Parcel in) {
            return new MediaItem(in);
        }

        public MediaItem[] newArray(int size) {
            return (new MediaItem[size]);
        }

    }
    ;

    protected MediaItem(Parcel in) {
        this.src = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.scale = ((String) in.readValue((String.class.getClassLoader())));
        this.key = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MediaItem() {
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(src);
        dest.writeValue(type);
        dest.writeValue(scale);
        dest.writeValue(key);
    }

    public int describeContents() {
        return  0;
    }

}
