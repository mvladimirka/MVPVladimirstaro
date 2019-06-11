package hr.tvz.android.listavladimir;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelClass implements Parcelable {
    public Long id;
    public int image;
    private int imageResource;
    private String title;
    private String body;

    public ModelClass( String title, String body, int imageResource) {
        this.id = null;
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
    }

    protected ModelClass(Parcel in) {
        id = in.readLong();
        imageResource = in.readInt();
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<ModelClass> CREATOR = new Creator<ModelClass>() {
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }



    @Override
    public int describeContents() {
       // return 0;
        return this.id.hashCode()
                //^ this.imageResource.hashCode()
                ^ this.title.hashCode()
                ^ this.body.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeInt(imageResource);
        parcel.writeString(title);
        parcel.writeString(body);
    }
}