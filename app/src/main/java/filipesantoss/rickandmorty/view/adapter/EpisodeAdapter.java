package filipesantoss.rickandmorty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ItemEpisodeBinding;
import filipesantoss.rickandmorty.model.Episode;
import filipesantoss.rickandmorty.view.adapter.EpisodeAdapter.EpisodeViewHolder;
import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends Adapter<EpisodeViewHolder> {

  private List<Episode> episodes = new ArrayList<>();

  public void setEpisodes(List<Episode> episodes) {
    this.episodes = episodes;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    ItemEpisodeBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.item_episode, parent, false);

    return new EpisodeViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
    holder.binding.setEpisode(episodes.get(position));
  }

  @Override
  public int getItemCount() {
    return episodes.size();
  }

  public static class EpisodeViewHolder extends ViewHolder {

    private final ItemEpisodeBinding binding;

    public EpisodeViewHolder(ItemEpisodeBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

  }

}
