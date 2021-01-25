package filipesantoss.rickandmorty.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Character extends Model implements Parcelable {

  private String name;
  private String image;
  private String species;
  private Origin origin;

  public static final Creator<Character> CREATOR = new Creator<Character>() {

    @Override
    public Character createFromParcel(Parcel in) {
      return new Character(in);
    }

    @Override
    public Character[] newArray(int size) {
      return new Character[size];
    }

  };

  private Character(Parcel in) {
    name = in.readString();
    image = in.readString();
    species = in.readString();
    origin = in.readParcelable(Origin.class.getClassLoader());
  }

  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }

  public String getSpecies() {
    return species;
  }

  public String getOrigin() {
    return origin.name;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(image);
    dest.writeString(species);
    dest.writeParcelable(origin, 0);
  }

  private static class Origin implements Parcelable {

    private String name;

    public static final Creator<Origin> CREATOR = new Creator<Origin>() {

      @Override
      public Origin createFromParcel(Parcel in) {
        return new Origin(in);
      }

      @Override
      public Origin[] newArray(int size) {
        return new Origin[size];
      }

    };

    private Origin(Parcel in) {
      name = in.readString();
    }

    @Override
    public int describeContents() {
      return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(name);
    }

  }

}
