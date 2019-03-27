
package hw.applicaster.vova.network.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hw.applicaster.vova.network.model.general.Author;
import hw.applicaster.vova.network.model.general.Content;
import hw.applicaster.vova.network.model.general.Link;
import hw.applicaster.vova.network.model.general.MediaGroup;
import hw.applicaster.vova.network.model.general.Type;

public class Entry implements Parcelable {

    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("link")
    @Expose
    private Link link;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("content")
    @Expose
    private Content content;
    @SerializedName("ui_tag")
    @Expose
    private Object uiTag;
    @SerializedName("screen_type")
    @Expose
    private Object screenType;
    @SerializedName("media_group")
    @Expose
    private List<MediaGroup> mediaGroup = null;
    public final static Parcelable.Creator<Entry> CREATOR = new Creator<Entry>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        public Entry[] newArray(int size) {
            return (new Entry[size]);
        }

    };

    protected Entry(Parcel in) {
        this.type = ((Type) in.readValue((Type.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.published = ((String) in.readValue((String.class.getClassLoader())));
        this.updated = ((String) in.readValue((String.class.getClassLoader())));
        this.link = ((Link) in.readValue((Link.class.getClassLoader())));
        this.author = ((Author) in.readValue((Author.class.getClassLoader())));
        this.content = ((Content) in.readValue((Content.class.getClassLoader())));
        this.uiTag = ((Object) in.readValue((Object.class.getClassLoader())));
        this.screenType = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.mediaGroup, (MediaGroup.class.getClassLoader()));
    }

    public Entry() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Object getUiTag() {
        return uiTag;
    }

    public void setUiTag(Object uiTag) {
        this.uiTag = uiTag;
    }

    public Object getScreenType() {
        return screenType;
    }

    public void setScreenType(Object screenType) {
        this.screenType = screenType;
    }

    public List<MediaGroup> getMediaGroup() {
        return mediaGroup;
    }

    public void setMediaGroup(List<MediaGroup> mediaGroup) {
        this.mediaGroup = mediaGroup;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(summary);
        dest.writeValue(published);
        dest.writeValue(updated);
        dest.writeValue(link);
        dest.writeValue(author);
        dest.writeValue(content);
        dest.writeValue(uiTag);
        dest.writeValue(screenType);
        dest.writeList(mediaGroup);
    }

    public int describeContents() {
        return 0;
    }


    @Override
    public String toString() {
        return title;
    }
}
