package filipesantoss.rickandmorty.view.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import java.util.Objects;

public class ScrollListener extends OnScrollListener {

  public static final int DOWN = 1;
  private Runnable bottomAction;

  @Override
  public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    if (!Objects.isNull(bottomAction) && scrolledToBottom(recyclerView, dy)) {
      bottomAction.run();
    }
  }

  public void onBottom(Runnable action) {
    bottomAction = action;
  }

  private boolean scrolledToBottom(RecyclerView view, int scrolledPixels) {
    return !view.canScrollVertically(DOWN) && scrolledPixels > 0;
  }

}
