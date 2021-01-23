package filipesantoss.rickandmorty.model;

import java.util.Objects;

public abstract class Model {

  private Integer id;

  @Override
  public boolean equals(Object object) {
    if (Objects.isNull(object) || !(object instanceof Model)) {
      return false;
    }

    Model character = (Model) object;
    return Objects.equals(id, character.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
