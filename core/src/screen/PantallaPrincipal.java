package screen;

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

import cat.xtec.ioc.objects.Background;
import cat.xtec.ioc.objects.ScrollHandler;
import utils.Settings;

public class PantallaPrincipal implements Screen {

    private Texture gifTexture;

    Background background, bg_back;

    private Stage stage, buttonStage;
    private SpriteBatch spriteBatch;

    // Per obtenir el batch de l'stage
    private Batch batch;
    private BitmapFont font, titleFont;
    private ScrollHandler scrollHandler;

    private Skin skin;



    public PantallaPrincipal() {

        AssetManager.load();

        // Creem la càmera de les dimensions del joc
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        // Posant el paràmetre a true configurem la càmera perquè
        // faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(false);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);
        StretchViewport button = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);


        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);
        buttonStage = new Stage(button);


        // Crear la fuente del título
        titleFont = new BitmapFont(); // Puedes ajustar los parámetros según lo desees

        // Crear el estilo del título
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = titleFont;
        titleLabelStyle.fontColor = Color.CORAL; // Color del texto del título

        // Crear el texto del título
        Label titleLabel = new Label("Dragon Ball: Poderes Desatados", titleLabelStyle);
        titleLabel.setFontScale(2); // Escalar el tamaño del título si es necesario

        // Configurar la posición del título en el centro de la pantalla
        titleLabel.setPosition((Settings.GAME_WIDTH - titleLabel.getWidth()) / 3, (Settings.GAME_HEIGHT - titleLabel.getHeight()) / 2);

        // Agregar el título al stage
        buttonStage.addActor(titleLabel);


        font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;

        // Crear el estilo de los botones
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font; // Utilizamos la misma fuente que para las etiquetas
        buttonStyle.fontColor = Color.BLACK; // Color del texto del botón

        TextButton.TextButtonStyle empezarJuegoButtonStyle = new TextButton.TextButtonStyle();
        empezarJuegoButtonStyle.font = font;
        empezarJuegoButtonStyle.fontColor = Color.BLACK;

// Establecer el fondo del botón (puedes personalizarlo según tus necesidades)
        TextureRegionDrawable buttonSettings = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("settings.png"))));
        TextureRegionDrawable buttonStarts = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("starts.png"))));

        buttonStyle.up = buttonSettings;
        empezarJuegoButtonStyle.up = buttonStarts;

// Crear los botones
        TextButton empezarJuegoButton = new TextButton("Empezar Juego", empezarJuegoButtonStyle);
        TextButton menuButton = new TextButton("Menu", buttonStyle);

        // Crear un nuevo Table para organizar los botones
        Table buttonTable = new Table();
        buttonTable.setFillParent(true); // El Table se ajustará al tamaño del stage

// Agregar los botones al Table
        buttonTable.add(empezarJuegoButton).padBottom(20).row(); // Agrega el botón "Empezar Juego" con un espacio inferior de 20 píxeles
        buttonTable.add(menuButton).padBottom(20).row(); // Agrega el botón "Menú" con un espacio inferior de 20 píxeles

// Agregar el Table al stage
        buttonStage.addActor(buttonTable);

// Agregar oyentes de eventos a los botones si es necesario
        empezarJuegoButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Acción cuando se hace clic en el botón de empezar juego
                System.out.println("Empezar juego");
                return true;
            }
        });

        menuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Acción cuando se hace clic en el botón de menú
                System.out.println("Menú");
                return true;
            }
        });

// Agregar los botones al stage
        buttonStage.addActor(empezarJuegoButton);
        buttonStage.addActor(menuButton);




        batch = stage.getBatch();

        scrollHandler = new ScrollHandler();

        // Afegim els actors a l'stage
        stage.addActor(scrollHandler);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(buttonStage); // Establecer el stage como el procesador de entrada
    }

    @Override
    public void render(float delta) {

        // Limpiar la pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibuixem i actualitzem tots els actors de l'stage
        stage.draw();
        stage.act(delta);

        // Dibujar los botones
        buttonStage.draw();

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
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
