package screen;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Disparo;
import com.mygdx.game.Gogeta;
import com.mygdx.game.JuegoGoku;
import com.mygdx.game.Robots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import cat.xtec.ioc.objects.ScrollHandler;
import helpers.InputHandler;
import utils.Settings;

public class GameScreen implements Screen {

    private Stage stage;

    private ScrollHandler scrollHandler;

    private Gogeta gogeta;

    private Robots robots;

    // Asteroides
    int numAsteroids;
    ArrayList<Robots> robotsArrayList;

    ArrayList<Disparo> disparos;


    // Objecte random
    Random r;

    Boolean gameover = false;

    JuegoGoku game;


    public GameScreen(JuegoGoku game) {

        this.game =game;

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

        // Inicializar la lista de robots
        robotsArrayList = new ArrayList<>();

        disparos = new ArrayList<>();

        stage.addActor(scrollHandler);

        // Creem la nau i la resta d'objectes
        gogeta = new Gogeta();

        robots = new Robots();

        stage.addActor(gogeta);


        gogeta.setName("Gogeta");


        // Assignem com a gestor d'entrada la classe InputHandler
        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    @Override
    public void show() {

    }

    // Método para generar un nuevo robot y añadirlo al stage
    private void generateRobot() {
        Robots robot = new Robots();
        robotsArrayList.add(robot);
        stage.addActor(robot);
    }

    // Método para agregar un nuevo disparo a la lista de disparos
    public void agregarDisparo(Disparo disparo) {
        disparos.add(disparo);
        stage.addActor(disparo); // Agregar el disparo al Stage
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibuixem i actualitzem tots els actors de l'stage
        stage.act(delta);
        stage.draw();


        // Verificar si Gogeta ya está dañado
        if (!gogeta.isDamaged()) {
            // Verificar colisiones entre Gogeta y los robots
            for (int i = robotsArrayList.size() - 1; i >= 0; i--) {
                Robots robot = robotsArrayList.get(i);
                if (robot.collides(gogeta)) {
                    System.out.println("CHOQUE");
                    gogeta.loseLife();
                    gogeta.setDamaged(true);
                    System.out.println("GOLPES: " + gogeta.getDamageCount());
                    System.out.println("VIDAS: " + gogeta.getVidas());
                    if (gogeta.getVidas() <= 0) {
                        gameover = true;
                        stage.getRoot().findActor("Gogeta").remove();
                        game.setScreen(new GameOver(game));
                    }
                    // Eliminar el robot que colisionó con el Gogeta
                    robotsArrayList.remove(robot);
                    robot.remove();
                    break; // Salir del bucle una vez que se ha eliminado el robot
                }
            }
        }

        for (int i = disparos.size() - 1; i >= 0; i--) {
            Disparo disparo = disparos.get(i);
            for (int j = robotsArrayList.size() - 1; j >= 0; j--) {
                Robots robot = robotsArrayList.get(j);
                if (disparo.collidesWithRobot(robot)) {
                    System.out.println("DISPARO DADO");
                    disparo.startExplosion(); // Iniciar la animación de explosión
                    disparo.remove();
                    robot.remove();
                    disparos.remove(disparo);
                    robotsArrayList.remove(robot);
                    break; // Sal del bucle interno
                }
            }
        }

        // Generar un nuevo robot aleatorio a intervalos regulares
        if (random.nextFloat() < Settings.ROBOT_SPAWN_CHANCE_PER_FRAME) {
            generateRobot();
        }

        // Mover y eliminar los robots que estén fuera de la pantalla
        for (int i = robotsArrayList.size() - 1; i >= 0; i--) {
            Robots robot = robotsArrayList.get(i);
            robot.act(delta);
            if (robot.isLeftOfScreen()) {
                robotsArrayList.remove(robot);
                robot.remove();
            }
        }


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
