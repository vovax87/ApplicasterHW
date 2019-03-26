
package hw.applicaster.vova.network.model.general;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type implements Parcelable
{

    public static String VIDEO = "video";
    public static String LINK = "link";

    @SerializedName("value")
    @Expose
    private String value;
    public final static Parcelable.Creator<Type> CREATOR = new Creator<Type>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Type createFromParcel(Parcel in) {
            return new Type(in);
        }

        public Type[] newArray(int size) {
            return (new Type[size]);
        }

    }
    ;

    protected Type(Parcel in) {
        this.value = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Type() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
