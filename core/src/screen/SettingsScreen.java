package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.JuegoGoku;

import cat.xtec.ioc.objects.Background;
import utils.Settings;

public class SettingsScreen implements Screen {

    private JuegoGoku game;

    Skin skin;
    Background background, bg_back;
    private OrthographicCamera camera;
    private Stage stage;
    Slider.SliderStyle horizontalSliderStyle;
    Preferences preferences;
    Slider slider_musica, slider_volumen;

    private float valorSliderVolumen, valorSliderMusic;


    public SettingsScreen(JuegoGoku game) {
        this.game = game;

        preferences = Gdx.app.getPreferences("Settings");


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

        //LABELS
        // Obtener el estilo del Label del Skin
        Label.LabelStyle labelStyle = skin.get("subtitle", Label.LabelStyle.class);

        // Crear una instancia de Label con el texto "Username" y el estilo definido
        Label sonido = new Label("SONIDO", labelStyle);

        Label musica = new Label("MUSICA", labelStyle);

        //VOLUMEN
        // Obtener el estilo del Slider del Skin
        Slider.SliderStyle sliderStyle = skin.get("default-horizontal", Slider.SliderStyle.class);

        // Crear un nuevo estilo para el Slider en dirección horizontal
        horizontalSliderStyle = new Slider.SliderStyle(sliderStyle);

        // Asignar el nuevo background y knob antes al estilo horizontal del Slider
        horizontalSliderStyle.background = skin.getDrawable("slider-bar");
        horizontalSliderStyle.knob = skin.getDrawable("slider-bar-knob");
        horizontalSliderStyle.knobBefore = skin.getDrawable("slider-bar-fill");



        //BOTON DE MUTE
        Button.ButtonStyle buttonStyle = skin.get("sound", Button.ButtonStyle.class);

        Button.ButtonStyle buttonStylemusic = skin.get("music", Button.ButtonStyle.class);


        Button btn_volumen = new Button(buttonStyle);

        //ENCENDER BOTONES
        Boolean btn_boolean_volumen = preferences.getBoolean("estado_volumen", true); // TRUE es el valor predeterminado si no se encuentra ningún valor guardado
        Boolean btn_boolean_musica = preferences.getBoolean("estado_musica", true); // TRUE es el valor predeterminado si no se encuentra ningún valor guardado

        btn_volumen.setChecked(btn_boolean_volumen);

        btn_volumen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (btn_volumen.isChecked()) {
                    System.out.println("TRUE");
                    AssetManager.volumenTotal = valorSliderVolumen;
                    AssetManager.setVolumenTotal(AssetManager.volumenTotal);
                    slider_volumen.setValue(AssetManager.volumenTotal);
                } else {
                    System.out.println("FALSE");
                    // Guardar el valor actual del Slider
                    valorSliderVolumen = slider_volumen.getValue();

                    // Establecer el valor del Slider a 0
                    AssetManager.volumenTotal = 0f;
                    slider_volumen.setValue(0f);
                }
            }
        });

        Button btn_musica = new Button(buttonStylemusic);

        btn_musica.setChecked(btn_boolean_musica);

        btn_musica.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (btn_musica.isChecked()) {
                    System.out.println("TRUE");
                    AssetManager.volumen = valorSliderMusic;
                    AssetManager.music.setVolume(AssetManager.volumen);
                    slider_musica.setValue(AssetManager.volumen);
                } else {
                    System.out.println("FALSE");
                    // Guardar el valor actual del Slider
                    valorSliderMusic = slider_musica.getValue();
                    // Establecer el valor del Slider a 0
                    //slider_musica.setValue(0);
                    AssetManager.volumen = 0f;
                    slider_musica.setValue(0f);
                }
            }
        });


        // Crear una instancia del Slider con el estilo horizontal
        slider_volumen = new Slider(0f, 1f, 0.1f, false, horizontalSliderStyle);


        slider_volumen.setValue(AssetManager.volumenTotal);


        slider_volumen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AssetManager.volumenTotal = slider_volumen.getValue();
                AssetManager.setVolumenTotal(AssetManager.volumenTotal);
            }
        });

        slider_musica = new Slider(0f, 1f, 0.1f, false, horizontalSliderStyle);


        slider_musica.setValue(AssetManager.volumen);

        slider_musica.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Guardar el valor actual del Slider en las preferencias
                System.out.println("MUSICA Slider: "+slider_musica.getValue());
                AssetManager.volumen = slider_musica.getValue();
                //System.out.println("MUSICA Slider: "+va);
                AssetManager.music.setVolume(AssetManager.volumen);
            }
        });


        // Verifica si el valor del slider es diferente de cero para encender el botón correspondiente
        if (AssetManager.volumenTotal != 0) {
            btn_volumen.setChecked(true);
        }

        if (AssetManager.volumen != 0f) {
            btn_musica.setChecked(true);
        }

        if (AssetManager.volumen == 0f) {
            btn_musica.setChecked(false);
        }
        if (AssetManager.volumenTotal == 0) {
            btn_volumen.setChecked(false);
        }


        //VENTANA
        Window.WindowStyle windowStyle = skin.get(Window.WindowStyle.class);

        // Crea una instancia de Window con el estilo obtenido
        Window window = new Window("SETTINGS", windowStyle);
        window.getTitleLabel().setAlignment(Align.center);

        // Calcula las coordenadas X e Y para colocar la ventana en el centro de la pantalla
        float windowX = (Gdx.graphics.getWidth() - window.getWidth()) / 2;
        float windowY = (Gdx.graphics.getHeight() - window.getHeight()) / 2;

        // Establece la posición de la ventana en el centro de la pantalla
        window.setPosition(windowX, windowY);

        window.setSize(400, 400); // Establece el tamaño como desees

        window.add(sonido).row();

        window.add(btn_volumen);

        window.add(slider_volumen).prefSize(200, 80).row();

        window.add(musica).row();

        window.add(btn_musica);

        window.add(slider_musica).prefSize(200, 80).row();



        TextButton.TextButtonStyle textButtonStyle = skin.get("round", TextButton.TextButtonStyle.class);

        // Crear instancia del TextButton con el estilo obtenido del Skin
        TextButton btn_volver = new TextButton("Volver", textButtonStyle);

        btn_volver.setSize(200, 70);

        btn_volver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PantallaPrincipal(game));

                // Guardar el estado de los botones en las preferencias
                preferences.putBoolean("estado_volumen", btn_volumen.isChecked());
                preferences.putBoolean("estado_musica", btn_musica.isChecked());

                preferences.putFloat("volumen", slider_volumen.getValue());
                preferences.putFloat("musica", slider_musica.getValue());
                preferences.flush(); // Esto es importante para guardar los cambios inmediatamente
            }
        });

        btn_volver.setPosition(windowX + 100, windowY - 100);



        //AÑADIMOS EL FONDO AL STAGE
        stage.addActor(background);
        stage.addActor(bg_back);

        stage.addActor(window);
        // Añade el título al stage
        stage.addActor(titleLabel);
        stage.addActor(btn_volver);



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

    }

    @Override
    public void dispose() {

    }
}
