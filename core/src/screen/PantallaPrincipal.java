package screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.JuegoGoku;

import cat.xtec.ioc.objects.Background;
import cat.xtec.ioc.objects.ScrollHandler;
import utils.Settings;

public class PantallaPrincipal implements Screen {

    private Texture gifTexture;

    Background background, bg_back;

    private Stage stage;
    private SpriteBatch spriteBatch;

    // Per obtenir el batch de l'stage
    private Batch batch;
    private BitmapFont font, titleFont;
    private ScrollHandler scrollHandler;

    private Table buttonTable;

    private JuegoGoku game;
    private OrthographicCamera camera;

    private Label titleLabel;

    private Skin skin, skin_vida;


    public PantallaPrincipal(JuegoGoku game) {

        this.game = game;


        // Creem la càmera de les dimensions del joc
        camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        // Posant el paràmetre a true configurem la càmera perquè
        // faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(false);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));


        background = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED); // Ajusta la velocidad según sea necesario
        bg_back = new Background(background.getX() + background.getWidth(), 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);


        // Obtén el estilo de la etiqueta "title" del Skin
        Label.LabelStyle titleLabelStyle = skin.get("default", Label.LabelStyle.class);

        // Crea una instancia de Label con el texto "R6 PIXEL" y el nuevo estilo
        Label titleLabel = new Label("Dragon Ball: Poderes Desatados", titleLabelStyle);

        // Escala el tamaño del título
        titleLabel.setFontScale(3); // Ajusta el valor según lo desees para aumentar o disminuir el tamaño del título

        // Calcula la posición X centrada en la pantalla
        float posX = (Settings.GAME_WIDTH - titleLabel.getWidth() - 500);

        // Establece la posición del título
        titleLabel.setPosition(posX, Settings.GAME_HEIGHT - titleLabel.getHeight() - 100); // Alinea el título en la parte superior y centrado, con un espacio de 20 píxeles desde el borde superior


        //BOTONES
        TextButton.TextButtonStyle textButtonStyle = skin.get("round", TextButton.TextButtonStyle.class);

        // Crear instancia del TextButton con el estilo obtenido del Skin
        TextButton btn_play = new TextButton("PLAY", textButtonStyle);

        // Agregar oyentes de eventos a los botones si es necesario
        btn_play.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Acción cuando se hace clic en el botón de empezar juego
                System.out.println("Empezar juego");
                // Obtener la instancia del Game que contiene las pantallas

                // Cambiar a la pantalla de juego
                game.setScreen(new GameScreen(game));
                return true;
            }
        });

        TextButton btn_settings = new TextButton("SETTINGS", textButtonStyle);

        btn_settings.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Acción cuando se hace clic en el botón de empezar juego
                System.out.println("SETTINGS");
                // Obtener la instancia del Game que contiene las pantallas

                // Cambiar a la pantalla de juego
                game.setScreen(new SettingsScreen(game));
                return true;
            }
        });

        // Crea una tabla para organizar los elementos en el escenario
        Table table = new Table();
        table.setFillParent(true); // Hace que la tabla ocupe todo el tamaño del escenario

        // Agrega el botón a la tabla
        table.add(btn_play).size(200, 70).center().padBottom(20).row(); // Alinea el botón al centro de la tabla y agrega un espacio inferior de 20 píxeles
        table.add(btn_settings).size(200, 70).center().padBottom(20); // Alinea el botón al centro de la tabla y agrega un espacio inferior de 20 píxeles


        //AÑADIMOS EL FONDO AL STAGE
        stage.addActor(background);
        stage.addActor(bg_back);

        // Añade el título al stage
        stage.addActor(titleLabel);

        // Añade la tabla al escenario
        stage.addActor(table);

        // Establece el procesador de entrada para que sea el Stage de los botones
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {


        camera.update();

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
        //Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
