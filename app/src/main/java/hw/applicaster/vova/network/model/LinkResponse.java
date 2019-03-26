
package hw.applicaster.vova.network.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkResponse implements Parcelable {


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("entry")
    @Expose
    private List<Entry> linkEntry = null;

    public final static Parcelable.Creator<LinkResponse> CREATOR = new Creator<LinkResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LinkResponse createFromParcel(Parcel in) {
            return new LinkResponse(in);
        }

        public LinkResponse[] newArray(int size) {
            return (new LinkResponse[size]);
        }

    };

    protected LinkResponse(Parcel in) {

        this.title = ((String) in.readValue((String.class.getClassLoader())));

        this.id = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.linkEntry, (Entry.class.getClassLoader()));
    }

    public LinkResponse() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<Entry> getLinkEntry() {
        return linkEntry;
    }

    public void setLinkEntry(List<Entry> linkEntry) {
        this.linkEntry = linkEntry;
    }


    public void writeToParcel(Parcel dest, int flags) {

        dest.writeValue(title);
        dest.writeValue(id);
        dest.writeList(linkEntry);
    }

    public int describeContents() {
        return 0;
    }

}
