package filipesantoss.rickandmorty.repository;

import io.reactivex.rxjava3.core.Observable;

public interface Repository<P> {

  Observable<P> list(int pageNumber);

}
