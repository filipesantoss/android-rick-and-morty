package filipesantoss.rickandmorty.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ActivityEpisodeListBinding;
import filipesantoss.rickandmorty.view.adapter.EpisodeAdapter;
import filipesantoss.rickandmorty.view.util.ScrollListener;
import filipesantoss.rickandmorty.viewmodel.EpisodeListViewModel;
import java.util.Objects;

@AndroidEntryPoint
public class EpisodeListActivity extends AppCompatActivity {

  private ActivityEpisodeListBinding binding;
  private EpisodeListViewModel viewModel;
  private ScrollListener scrollListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_episode_list);
    viewModel = new ViewModelProvider(this).get(EpisodeListViewModel.class);

    EpisodeAdapter adapter = new EpisodeAdapter();
    binding.episodeList.setAdapter(adapter);

    scrollListener = new ScrollListener();
    scrollListener.onBottom(viewModel::next);
    binding.episodeList.addOnScrollListener(scrollListener);

    viewModel.onLoadStart(() -> binding.episodeLoading.setVisibility(View.VISIBLE));
    viewModel.onLoadFinish(() -> binding.episodeLoading.setVisibility(View.GONE));
    viewModel.getEpisodes().observe(this, adapter::setEpisodes);

    if (Objects.isNull(savedInstanceState)) {
      viewModel.list();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    viewModel.getEpisodes().removeObservers(this);
    binding.episodeList.removeOnScrollListener(scrollListener);
  }

}
