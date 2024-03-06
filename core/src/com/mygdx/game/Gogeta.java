package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    private boolean isDamaged = false;

    private float damageTimeElapsed = 0;
    private static final float DAMAGE_DURATION = 0.05f; // Duración de la animación de daño en segundos

    private int damageCount = 0;
    public static final int MAX_DAMAGE_COUNT = 4; // Número máximo de golpes recibidos antes de desactivar la animación de daño

    private int vidas; // Atributo para las vidas

    private boolean damageProcessed = false; // Variable para controlar si el daño ha sido procesado



    public Gogeta() {
        this.width = Settings.GOGETA_WIDTH;
        this.height = Settings.GOGETA_HEIGHT;
        position = new Vector2(Settings.GOGETA_STARTX, Settings.GOGETA_STARTY);

        // Inicialitzem l'Spacecraft a l'estat normal
        direction = GOGETA_STRAIGHT;

        // Creem el rectangle de col·lisions con las dimensiones del sprite
        collisionRect = new Rectangle(position.x, position.y, width, height);

        vidas = 4;

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
        // Actualizar la posición del rectángulo de colisión
        collisionRect.setPosition(position.x, position.y);

        if (isDamaged) {
            damageTimeElapsed += delta;
            if (!damageProcessed) {
                damageProcessed = true; // Marcar el daño como procesado
            }
            if (damageTimeElapsed >= DAMAGE_DURATION) {
                if (damageCount < MAX_DAMAGE_COUNT) {
                    damageCount++; // Aumentar el contador de golpes recibidos solo si no ha alcanzado el máximo
                }
                if (damageCount >= MAX_DAMAGE_COUNT) {
                    isDamaged = false; // Desactivar la animación de daño si se han recibido suficientes golpes
                    damageCount = 0; // Reiniciar el contador de golpes recibidos
                }
                damageTimeElapsed = 0; // Reiniciar el temporizador
                damageProcessed = false; // Restablecer el estado de procesamiento de daño
            }
        }
    }
    public void loseLife() {
        vidas--;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (isDamaged) {
            batch.draw(AssetManager.gogetaDaño, position.x, position.y, width, height);
        } else {
            batch.draw(getGogetaTexture(), position.x, position.y, width, height);
        }

        // Dibujar la hitbox del Gogeta con líneas blancas
        batch.end(); // Finalizar el batch para comenzar a usar líneas primitivas
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1); // Establecer color blanco
        shapeRenderer.rect(collisionRect.x, collisionRect.y, collisionRect.width, collisionRect.height);
        shapeRenderer.end();
        batch.begin(); // Volver a comenzar el batch para dibujar texturas
    }

    public TextureRegion getGogetaTexture() {

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

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
        if (!damaged) {
            // Si el daño está desactivado, reiniciar el contador de golpes recibidos
            damageCount = 0;
        }
    }

    public int getDamageCount() {
        return damageCount;
    }

    public void incrementDamageCount() {
        damageCount++;
    }

    public boolean isMaxDamageReached() {
        return damageCount >= MAX_DAMAGE_COUNT;
    }

    public void setDamageCount(int damageCount) {
        this.damageCount = damageCount;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
