package stratego.util.observer;

import stratego.entities.impl.GameStatus;

public interface IObservable {

    void addObserver(IObserver s);

    void removeObserver(IObserver s);

    void removeAllObservers();

    void notifyObservers();

    void notifyObservers(GameStatus e);
}
