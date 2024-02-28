package cat.xtec.ioc.objects;

import com.badlogic.gdx.graphics.g2d.Batch;

import screen.AssetManager;
import utils.Settings;

public class Background extends  Scrollable{
    public Background(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.disableBlending();
        /*
        // Ajusta las coordenadas Y para que coincidan con el sistema de coordenadas invertido
        float y = Settings.GAME_HEIGHT - position.y - height;
        */

        batch.draw(AssetManager.background, position.x, position.y, width, height);
        batch.enableBlending();
    }
}
