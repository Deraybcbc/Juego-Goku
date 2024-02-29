package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import screen.AssetManager;
import utils.Settings;

public class Gogeta extends Actor {

    // Diferents posicions de l'Spacecraft: recta, pujant i baixant
    public static final int GOGETA_STRAIGHT = 0;
    public static final int GOGETA_UP = 1;
    public static final int GOGETA_DOWN = 2;

    public static final int GOGETA_RIGHT = 3;

    public static final int GOGETA_LEFT = 4;

    // Paràmetres de l'Spacecraft
    private Vector2 position;
    private int width, height;
    private int direction;

    private Rectangle collisionRect;

    public Gogeta(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        // Inicialitzem l'Spacecraft a l'estat normal
        direction = GOGETA_STRAIGHT;

        // Creem el rectangle de col·lisions
        collisionRect = new Rectangle();
    }

    public void act(float delta) {

        // Movem l'Spacecraft depenent de la direcció controlant que no surti de la pantalla
        switch (direction) {
            case GOGETA_UP:
                if (this.position.y - Settings.GOGETA_VELOCITY * delta >= 0) {
                    this.position.y -= Settings.GOGETA_VELOCITY * delta;
                }
                break;
            case GOGETA_DOWN:
                if (this.position.y + height + Settings.GOGETA_VELOCITY * delta <= Settings.GAME_HEIGHT) {
                    this.position.y += Settings.GOGETA_VELOCITY * delta;
                }
                break;
            case GOGETA_RIGHT:
                // Lógica de movimiento hacia la derecha
                if (this.position.x + width + Settings.GOGETA_VELOCITY * delta <= Settings.GAME_WIDTH) {
                    this.position.x += Settings.GOGETA_VELOCITY * delta;
                }
                break;
            case GOGETA_LEFT:
                // Lógica de movimiento hacia la izquierda
                if (this.position.x - Settings.GOGETA_VELOCITY * delta >= 0) {
                    this.position.x -= Settings.GOGETA_VELOCITY * delta;
                }
                break;
            case GOGETA_STRAIGHT:
                break;
        }
        collisionRect.set(position.x, position.y + 3, width, 10);
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getSpacecraftTexture(), position.x, position.y, width, height);
    }

    public TextureRegion getSpacecraftTexture() {

        switch (direction) {

            case GOGETA_STRAIGHT:
                return AssetManager.gogeta;
            case GOGETA_UP:
                return AssetManager.gogetaUp;
            case GOGETA_DOWN:
                return AssetManager.gogetaDown;
            case GOGETA_RIGHT:
                return AssetManager.gogetaRight;
            case GOGETA_LEFT:
                return AssetManager.gogetaLeft;
            default:
                return AssetManager.gogeta;
        }
    }

    // Getters dels atributs principals
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

    // Canviem la direcció de l'Spacecraft: Puja
    public void goUp() {
        direction = GOGETA_UP;
    }

    // Canviem la direcció de l'Spacecraft: Baixa
    public void goDown() {
        direction = GOGETA_DOWN;
    }

    // Posem l'Spacecraft al seu estat original
    public void goStraight() {
        direction = GOGETA_STRAIGHT;
    }

    // Cambia la dirección del personaje hacia la derecha
    public void goRight() {
        direction = GOGETA_RIGHT;
    }

    // Cambia la dirección del personaje hacia la izquierda
    public void goLeft() {
        direction = GOGETA_LEFT;
    }
}
