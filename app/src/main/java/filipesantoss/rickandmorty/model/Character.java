package filipesantoss.rickandmorty.model;

import java.util.Objects;

public class Character {

  private Integer id;
  private String name;
  private String image;

  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }

  @Override
  public boolean equals(Object object) {
    if (Objects.isNull(object) || !Objects.equals(getClass(), object.getClass())) {
      return false;
    }

    Character character = (Character) object;
    return Objects.equals(id, character.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
