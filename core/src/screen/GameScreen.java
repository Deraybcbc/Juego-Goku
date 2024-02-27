package screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import cat.xtec.ioc.objects.ScrollHandler;

public class GameScreen implements Screen {

    private Stage stage;

    private ScrollHandler scrollHandler;


    public GameScreen() {
        // Creem l'stage
        stage = new Stage();

        stage.addActor(scrollHandler);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Dibuixem i actualitzem tots els actors de l'stage
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
