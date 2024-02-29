package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Gogeta;

import cat.xtec.ioc.objects.ScrollHandler;
import helpers.InputHandler;
import utils.Settings;

public class GameScreen implements Screen {

    private Stage stage;

    private ScrollHandler scrollHandler;

    private Gogeta gogeta;



    public GameScreen() {


        AssetManager.load();

        // Creem la càmera de les dimensions del joc
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        // Posant el paràmetre a true configurem la càmera perquè
        // faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(false);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        scrollHandler = new ScrollHandler();

        stage.addActor(scrollHandler);

        // Creem la nau i la resta d'objectes
        gogeta = new Gogeta(Settings.GOGETA_STARTX, Settings.GOGETA_STARTY, Settings.GOGETA_WIDTH, Settings.GOGETA_HEIGHT);

        stage.addActor(gogeta);

        gogeta.setName("Gogeta");

        // Assignem com a gestor d'entrada la classe InputHandler
        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    public Stage getStage() {
        return stage;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public Gogeta getGogeta() {
        return gogeta;
    }
}
