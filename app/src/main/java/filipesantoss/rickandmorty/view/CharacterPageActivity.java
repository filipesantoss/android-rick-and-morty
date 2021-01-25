package filipesantoss.rickandmorty.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import filipesantoss.rickandmorty.R;
import filipesantoss.rickandmorty.databinding.ActivityCharacterPageBinding;
import filipesantoss.rickandmorty.view.adapter.CharacterAdapter;
import filipesantoss.rickandmorty.view.util.ScrollListener;
import filipesantoss.rickandmorty.viewmodel.CharacterPageViewModel;
import java.util.Objects;

@AndroidEntryPoint
public class CharacterPageActivity extends AppCompatActivity {

  private ActivityCharacterPageBinding binding;
  private CharacterPageViewModel viewModel;
  private ScrollListener scrollListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_character_page);
    viewModel = new ViewModelProvider(this).get(CharacterPageViewModel.class);

    CharacterAdapter adapter = new CharacterAdapter();
    binding.characterList.setAdapter(adapter);

    scrollListener = new ScrollListener();
    scrollListener.onBottom(viewModel::next);
    binding.characterList.addOnScrollListener(scrollListener);

    viewModel.onLoadStart(() -> binding.characterLoading.setVisibility(View.VISIBLE));
    viewModel.onLoadFinish(() -> binding.characterLoading.setVisibility(View.GONE));
    viewModel.getCharacters().observe(this, adapter::setCharacters);

    if (Objects.isNull(savedInstanceState)) {
      viewModel.list();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    viewModel.getCharacters().removeObservers(this);
    binding.characterList.removeOnScrollListener(scrollListener);
  }

}
