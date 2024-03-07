package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import screen.AssetManager;
import utils.Settings;

public class GogetaDead extends Actor {

    private float runTime;

    private Vector2 position;
    private int width, height;

    public GogetaDead() {
        this.height = Settings.GOGETA_HEIGHT_DEAD;
        this.width = Settings.GOGETA_WIDTH_DEAD;
        position = new Vector2(Settings.GOGETA_STARTX, Settings.GOGETA_STARTY);

        runTime=0;
    }

    public void act(float delta) {
        super.act(delta); // Asegúrate de llamar al método de la clase base
        runTime += delta;
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw((TextureRegion) AssetManager.muertegogeta.getKeyFrame(runTime), position.x, position.y, width, height);
    }
}
