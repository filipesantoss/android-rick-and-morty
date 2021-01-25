package filipesantoss.rickandmorty.viewmodel;

import androidx.lifecycle.ViewModel;
import java.util.Objects;

public abstract class LoadableViewModel extends ViewModel implements Loadable {

  private Runnable loadStartAction;
  private Runnable loadFinishAction;

  @Override
  public void onLoadStart(Runnable action) {
    loadStartAction = action;
  }

  @Override
  public void onLoadFinish(Runnable action) {
    loadFinishAction = action;
  }

  protected void startLoading() {
    if (!Objects.isNull(loadStartAction)) {
      loadStartAction.run();
    }
  }

  protected void stopLoading() {
    if (!Objects.isNull(loadFinishAction)) {
      loadFinishAction.run();
    }
  }

}
