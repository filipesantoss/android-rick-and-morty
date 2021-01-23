package filipesantoss.rickandmorty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import filipesantoss.rickandmorty.databinding.ItemCharacterBinding;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter.CharacterViewHolder;
import filipesantoss.rickandmorty.view.custom.LoadedImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterAdapter extends Adapter<CharacterViewHolder> implements Scrollable {

  private List<Character> characters = new ArrayList<>();
  private Runnable bottomScrollAction;

  public void setCharacters(List<Character> characters) {
    this.characters = characters;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    ItemCharacterBinding binding = ItemCharacterBinding
        .inflate(LayoutInflater.from(context), parent, false);

    return new CharacterViewHolder(binding, context);
  }

  @Override
  public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
    if (!Objects.isNull(bottomScrollAction) && isLastItem(position)) {
      bottomScrollAction.run();
    }

    Character character = characters.get(position);
    holder.name.setText(character.getName());
    holder.image.setUrl(character.getImage());
  }

  @Override
  public int getItemCount() {
    return characters.size();
  }

  @Override
  public void onBottomScroll(Runnable action) {
    bottomScrollAction = action;
  }

  private boolean isLastItem(int position) {
    return position + 1 == getItemCount();
  }

  public static class CharacterViewHolder extends ViewHolder {

    private final LoadedImageView image;
    private final TextView name;

    public CharacterViewHolder(ItemCharacterBinding binding, Context context) {
      super(binding.getRoot());
      image = binding.characterImage;
      image.setContext(context);
      name = binding.characterName;
    }

  }

}
