package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

import screen.AssetManager;
import utils.Methods;
import utils.Settings;

public class Robots extends Actor {

    private float runTime;

    private Circle collisionCircle;

    // Paràmetres de l'Spacecraft
    private Vector2 position;
    private int width, height;
    private int direction;
    private Random random;
    protected float velocity;

    protected boolean leftOfScreen;


    public Robots() {
        this.width = Settings.ROBOTS_WIDTH;
        this.height = Settings.ROBOTS_HEIGHT;
        random = new Random();


        // Inicializa la posición en la parte derecha de la pantalla
        position = new Vector2(Settings.GAME_WIDTH, random.nextInt((int) (Settings.GAME_HEIGHT - height)));

        // Establece una velocidad aleatoria para cada robot
        velocity = random.nextInt((int) (Settings.MAX_ROBOT_SPEED - Settings.MIN_ROBOT_SPEED)) + Settings.MIN_ROBOT_SPEED;

        runTime = Methods.randomFloat(0, 1);
        leftOfScreen = false;

        //System.out.println("¡Nuevo robot creado en la posición: (" + position.x + ", " + position.y + ")!");


        // Creem el cercle
        collisionCircle = new Circle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw((TextureRegion) AssetManager.robotsAnim.getKeyFrame(runTime), position.x, position.y, width, height);

    }


    public void act(float delta) {
        super.act(delta);
        // Desplacem l'objecte en l'eix d'X
        position.x -= velocity * delta;

        // Si es troba fora de la pantalla canviem la variable a true
        if (position.x + width < 0) {
            leftOfScreen = true;
        }

    }

    public void reset(float newX) {
        position.x = newX;
        leftOfScreen = false;
    }

    // Retorna true si hi ha col·lisió
    public boolean collides(Gogeta gogeta) {

        if (position.x <= gogeta.getX() + gogeta.getWidth()) {
// Comprovem si han col·lisionat sempre que l'asteroide es trobi a la mateixa alçada que l'spacecraft
            //System.out.println("¡Se ha producido una colisión con Gogeta!");
            return (Intersector.overlaps(collisionCircle, gogeta.getCollisionRect()));
        }
        return false;
    }

    public boolean isLeftOfScreen() {
        return leftOfScreen;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


}
