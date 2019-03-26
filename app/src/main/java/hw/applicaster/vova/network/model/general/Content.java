
package hw.applicaster.vova.network.model.general;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content implements Parcelable
{

    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("type")
    @Expose
    private String type;
    public final static Parcelable.Creator<Content> CREATOR = new Creator<Content>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        public Content[] newArray(int size) {
            return (new Content[size]);
        }

    }
    ;

    protected Content(Parcel in) {
        this.src = ((String) in.readValue((String.class.getClassLoader())));
        this.content = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Content() {
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(src);
        dest.writeValue(content);
        dest.writeValue(type);
    }

    public int describeContents() {
        return  0;
    }

}
