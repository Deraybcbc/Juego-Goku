package cat.xtec.ioc.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import screen.AssetManager;
import utils.Settings;

public class Background extends Actor {

    private float velocity;
    private float width;
    private float height;

    public Background(float x, float y, float width, float height, float velocity) {
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        setPosition(x, y);
        setSize(width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Mueve el fondo hacia la derecha
        moveBy(velocity * delta, 0);

        // Si el fondo ha salido completamente de la pantalla hacia la izquierda
        if (getX() + getWidth() <= 0) {
            // Reinicia su posición al lado derecho de la pantalla
            setX(getX() + getWidth() * 2);
        }
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        /*
        // Ajusta las coordenadas Y para que coincidan con el sistema de coordenadas invertido
        float y = Settings.GAME_HEIGHT - position.y - height;
        */

        batch.draw(AssetManager.background, getX(), getY(), getWidth(), getHeight());
    }
}
