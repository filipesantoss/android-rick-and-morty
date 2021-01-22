package filipesantoss.rickandmorty.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CharacterPage {

  @SerializedName("results")
  private List<Character> characters;
  @SerializedName("info")
  private Pagination pagination;

  public Pagination getPagination() {
    return pagination;
  }

  public List<Character> getCharacters() {
    return characters;
  }

  public static class Pagination {

    private String next;

    public Optional<Integer> getNextPageNumber() {
      return Objects.isNull(next)
          ? Optional.empty()
          : Optional.of(getPageNumber(next));
    }

    private int getPageNumber(String pageUrl) {
      return Integer.parseInt(pageUrl.substring(pageUrl.lastIndexOf("=") + 1));
    }

  }

}
