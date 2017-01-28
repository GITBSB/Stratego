package stratego.util.observer;

import stratego.entities.impl.GameStatus;

public interface IObserver {
    void update(GameStatus e);
}
