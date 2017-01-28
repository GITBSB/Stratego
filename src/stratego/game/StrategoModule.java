package stratego.game;

import stratego.controller.IController;

import com.google.inject.AbstractModule;

public class StrategoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IController.class).to(
                stratego.controller.impl.Controller.class);
    }
}
