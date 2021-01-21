package filipesantoss.rickandmorty.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CharacterPage {

  @SerializedName("results")
  private List<Character> characters;

  public List<Character> getCharacters() {
    return characters;
  }
}
