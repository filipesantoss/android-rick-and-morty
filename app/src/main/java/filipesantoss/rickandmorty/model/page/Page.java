package filipesantoss.rickandmorty.model.page;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Page<T> {

  @SerializedName("results")
  private List<T> items;
  @SerializedName("info")
  private Data data;

  public Data getData() {
    return data;
  }

  public List<T> getItems() {
    return items;
  }

  public static class Data {

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
