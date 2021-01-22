package filipesantoss.rickandmorty.viewmodel;

public interface Loadable {

  void onLoadStart(Runnable action);

  void onLoadFinish(Runnable action);

}
