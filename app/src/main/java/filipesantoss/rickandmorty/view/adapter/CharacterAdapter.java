package filipesantoss.rickandmorty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ItemCharacterBinding;
import filipesantoss.rickandmorty.model.Character;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter.CharacterViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

  private List<Character> characters = new ArrayList<>();
  private final Context context;
  private Runnable onBottom;

  public CharacterAdapter(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new CharacterViewHolder(ItemCharacterBinding.inflate(inflater, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
    if (!Objects.isNull(onBottom) && isLastItem(position)) {
      onBottom.run();
    }

    Character character = characters.get(position);
    holder.name.setText(character.getName());
    Glide.with(context).load(character.getImage())
        .placeholder(R.drawable.placeholder_item_character)
        .error(R.drawable.error_item_character)
        .into(holder.image);
  }

  @Override
  public int getItemCount() {
    return characters.size();
  }

  public void setCharacters(List<Character> characters) {
    this.characters = characters;
    notifyDataSetChanged();
  }

  public void onBottomScroll(Runnable onBottom) {
    this.onBottom = onBottom;
  }

  private boolean isLastItem(int position) {
    return position + 1 == getItemCount();
  }

  public static class CharacterViewHolder extends RecyclerView.ViewHolder {

    private final ImageView image;
    private final TextView name;

    public CharacterViewHolder(ItemCharacterBinding binding) {
      super(binding.getRoot());
      image = binding.characterImage;
      name = binding.characterName;
    }

  }

}
