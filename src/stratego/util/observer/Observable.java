package stratego.util.observer;

import java.util.ArrayList;
import java.util.List;

import stratego.entities.impl.GameStatus;

public class Observable implements IObservable {

    private List<IObserver> subscribers = new ArrayList<IObserver>(2);

    public void addObserver(IObserver s) {
        subscribers.add(s);
    }

    public void removeObserver(IObserver s) {
        subscribers.remove(s);
    }

    public void removeAllObservers() {
        subscribers.clear();
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(GameStatus e) {
        for (IObserver current: subscribers) {
            current.update(e);
        }
	}
}
