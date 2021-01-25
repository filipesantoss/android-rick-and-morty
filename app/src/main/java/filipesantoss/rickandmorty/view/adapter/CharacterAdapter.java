package filipesantoss.rickandmorty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ItemCharacterBinding;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter.CharacterViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CharacterAdapter extends Adapter<CharacterViewHolder> {

  private final Function<Character, OnClickListener> onCharacterClick;
  private List<Character> characters = new ArrayList<>();

  public CharacterAdapter(Function<Character, OnClickListener> onCharacterClick) {
    this.onCharacterClick = onCharacterClick;
  }

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
    Character character = characters.get(position);
    holder.binding.getRoot().setOnClickListener(onCharacterClick.apply(character));
    holder.binding.setCharacter(character);
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
