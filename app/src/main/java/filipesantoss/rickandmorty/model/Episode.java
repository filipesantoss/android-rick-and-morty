package filipesantoss.rickandmorty.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Episode extends Model {

  @SerializedName("name")
  private String title;
  @SerializedName("air_date")
  private String number;
  @SerializedName("episode")
  private String name;
  private List<String> characters;

  public String getTitle() {
    return title;
  }

  public String getName() {
    return name;
  }

  public String getNumber() {
    return number;
  }
}
