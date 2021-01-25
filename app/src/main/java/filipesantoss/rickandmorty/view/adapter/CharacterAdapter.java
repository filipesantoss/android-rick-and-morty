package filipesantoss.rickandmorty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ItemCharacterBinding;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter.CharacterViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterAdapter extends Adapter<CharacterViewHolder> {

  private List<Character> characters = new ArrayList<>();

  public void setCharacters(List<Character> characters) {
    this.characters = characters;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    ItemCharacterBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.item_character, parent, false);

    return new CharacterViewHolder(binding, context);
  }

  @Override
  public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
    holder.binding.setCharacter(characters.get(position));
  }

  @Override
  public int getItemCount() {
    return characters.size();
  }

  public static class CharacterViewHolder extends ViewHolder {

    private final ItemCharacterBinding binding;

    public CharacterViewHolder(ItemCharacterBinding binding, Context context) {
      super(binding.getRoot());
      this.binding = binding;
      binding.characterImage.setContext(context);
    }

  }

}
