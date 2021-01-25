package filipesantoss.rickandmorty.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<Integer> getCharacterIds() {
    return characters.stream().map(this::getCharacterId).collect(Collectors.toList());
  }

  private int getCharacterId(String characterUrl) {
    return Integer.parseInt(characterUrl.substring(characterUrl.lastIndexOf("/") + 1));
  }

}
