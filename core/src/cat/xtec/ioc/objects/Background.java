package cat.xtec.ioc.objects;

import com.badlogic.gdx.graphics.g2d.Batch;

import screen.AssetManager;

public class Background extends  Scrollable{
    public Background(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Actualizar la posición del fondo basado en la velocidad y el tiempo transcurrido
        position.x += velocity * delta;

        // Si el fondo se mueve completamente fuera de la pantalla, lo reposicionamos a la derecha de la pantalla
        if (position.x + width <= 0) {
            position.x += width * 2;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.disableBlending();
        batch.draw(AssetManager.background, position.x, position.y, width, height);
        batch.enableBlending();
    }
}
