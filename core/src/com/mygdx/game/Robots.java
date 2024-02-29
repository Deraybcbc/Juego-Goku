package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
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



    public Robots() {
        random = new Random();
        position = new Vector2(Settings.GAME_WIDTH, random.nextInt(Settings.GAME_HEIGHT));

        runTime = Methods.randomFloat(0,1);

        // Creem el cercle
        collisionCircle = new Circle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = (TextureRegion) AssetManager.robotsAnim.getKeyFrame(runTime, true);
        batch.draw(currentFrame, position.x, position.y, width, height);
    }


    public void act(float delta) {
        super.act(delta);
        runTime += delta;

        // Calcula la nueva posición del robot restando un cierto valor en el eje X
        float newX = position.x - Settings.BG_SPEED * delta;

        // Actualiza la posición del robot
        position.set(newX, position.y);

        // Actualiza el círculo de colisión
        collisionCircle.setPosition(position.x + getWidth() / 2, position.y + getHeight() / 2);

        // Elimina el actor si sale de la pantalla
        if (position.x + getWidth() < 0 || position.y + getHeight() < 0 || position.y > Settings.GAME_HEIGHT) {
            remove();
        }

    }


}
