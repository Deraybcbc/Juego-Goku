package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

import screen.AssetManager;
import utils.Settings;

public class Disparo extends Actor {

    private float runTime;

    private Rectangle hitbox; // Hitbox rectangular del disparo

    // Paràmetres de l'Spacecraft
    private Vector2 position;
    private int width, height;
    private int direction;
    private Random random;
    protected float velocity;

    private boolean remove = false; // Bandera para indicar si el disparo debe eliminarse


    public Disparo(float x, float y) {
        position = new Vector2(x, y);

        velocity = Settings.DISPARO_VELOCITY_X;

        runTime = 0;

        // Creem el rectangle de col·lisions con las dimensiones del sprite
        hitbox = new Rectangle(position.x, position.y, Settings.DISPARO_WIDTH, Settings.DISPARO_HEIGHT);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Desplazamos el objeto en el eje X
        position.x += velocity * delta;  // Modificamos += para mover el disparo hacia la derecha

        runTime += delta;

        // Marcar el disparo para eliminación si sale de la pantalla
        if (position.x < 0 || position.x > Settings.GAME_WIDTH || position.y < 0 || position.y > Settings.GAME_HEIGHT) {
            remove = true;
        }

        // Actualizar la posición del rectángulo de colisión
        hitbox.setPosition(position.x, position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        // Dibujar el disparo en la posición actual
        batch.draw(AssetManager.disparo, position.x, position.y, Settings.DISPARO_WIDTH, Settings.DISPARO_HEIGHT);

        // Dibujar la hitbox del Gogeta con líneas blancas
        batch.end(); // Finalizar el batch para comenzar a usar líneas primitivas
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1); // Establecer color blanco
        shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        shapeRenderer.end();
        batch.begin(); // Volver a comenzar el batch para dibujar texturas
    }

    public boolean shouldRemove() {
        return remove;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean collidesWithRobot(Robots robot) {

        hitbox.set((position.x + width / 2 - robot.getX()), (position.y + height / 2 - robot.getY()), Settings.DISPARO_WIDTH, Settings.DISPARO_HEIGHT);

        if (position.x <= robot.getX() + robot.getWidth()) {
            // Comprobamos si han colisionado siempre que el robot esté a la misma altura que Gogeta
            if (Intersector.overlaps(robot.getCollisionCircle(), hitbox)) {
                return true;
            }
        }
        return false;
    }
}
