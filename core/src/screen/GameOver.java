package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Gogeta;
import com.mygdx.game.GogetaDead;
import com.mygdx.game.JuegoGoku;

import utils.Settings;

public class GameOver implements Screen {

    private Stage stage;

    private OrthographicCamera camera;

    private BitmapFont titleFont;

    private Label titleLabel;

    JuegoGoku game;

    GogetaDead gogeta;

    int puntosfinal;

    private Label puntosLabel;



    public GameOver(JuegoGoku game, int puntos) {

        this.game = game;

        this.puntosfinal = puntos;

        AssetManager.load();

        // Creem la càmera de les dimensions del joc
        camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        // Posant el paràmetre a true configurem la càmera perquè
        // faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(false);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);


        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        // Crear la fuente del título
        titleFont = new BitmapFont(); // Puedes ajustar los parámetros según lo desees

        // Crear el estilo del título
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = titleFont;
        titleLabelStyle.fontColor = Color.WHITE; // Color del texto del título

        // Crear el texto del título
        titleLabel = new Label("Game Over", titleLabelStyle);
        titleLabel.setFontScale(2); // Escalar el tamaño del título si es necesario

        // Configurar la posición del título en el centro de la pantalla
        titleLabel.setPosition((Settings.GAME_WIDTH - titleLabel.getWidth()) / 3, (Settings.GAME_HEIGHT - titleLabel.getHeight()) /2 );

        // Creem la nau i la resta d'objectes
        // Crear el actor para la animación del Gogeta muerto
        gogeta = new GogetaDead();
        //gogeta.setPosition((Settings.GAME_WIDTH - gogeta.getWidth()) / 2, (Settings.GAME_HEIGHT - titleLabel.getHeight()) / 3);
        stage.addActor(gogeta);

// Crear la fuente para los puntos
        BitmapFont puntosFont = new BitmapFont(); // Puedes ajustar los parámetros según lo desees

        // Crear el estilo para los puntos
        Label.LabelStyle puntosLabelStyle = new Label.LabelStyle();
        puntosLabelStyle.font = puntosFont;
        puntosLabelStyle.fontColor = Color.WHITE; // Color del texto de los puntos

        // Crear el texto para los puntos
        puntosLabel = new Label("Puntos: " + puntosfinal, puntosLabelStyle);
        puntosLabel.setFontScale(2); // Escalar el tamaño del texto si es necesario

        // Configurar la posición de los puntos encima de la animación de Gogeta
        puntosLabel.setPosition(Settings.GOGETA_STARTX_DEAD, Settings.GOGETA_STARTY_DEAD + gogeta.getHeight() +250); // Ajusta la posición según tu preferencia
        stage.addActor(puntosLabel);
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

        camera.update();

        game.getSpriteBatch().begin();

        titleLabel.draw(game.getSpriteBatch(), 1);
        // Dibujar y actualizar la etiqueta de los puntos
        puntosLabel.draw(game.getSpriteBatch(), 1);
        game.getSpriteBatch().end();

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
