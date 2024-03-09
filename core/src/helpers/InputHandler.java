package helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Disparo;
import com.mygdx.game.Gogeta;

import screen.GameScreen;
import utils.Settings;

public class InputHandler implements InputProcessor {

    private Gogeta gogeta;

    private GameScreen screen;

    // Enter per a la gestió del moviment d'arrossegament
    int previousY = 0;
    int previousX = 0;

    public InputHandler(GameScreen screen) {
        this.screen = screen;

        gogeta = screen.getGogeta();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:

                gogeta.activateShootAnimation();
                // Obtener la posición actual del personaje
                float gogetaX = gogeta.getX();
                float gogetaY = gogeta.getY();

                // Calcular la posición inicial del disparo para que salga desde el centro del personaje
                float disparoX = gogetaX + gogeta.getWidth() - Settings.DISPARO_WIDTH / 2;
                float disparoY = gogetaY + gogeta.getHeight() / 2 - Settings.DISPARO_HEIGHT / 2;

                // Crear un nuevo disparo con la posición calculada
                Disparo disparo = new Disparo(disparoX, disparoY);
                screen.agregarDisparo(disparo);
                //screen.getStage().addActor(disparo);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                gogeta.deactivateShootAnimation(); // Desactivar la animación de disparo
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        previousY = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Quan deixem anar el dit acabem un moviment
        // i posem la nau a l'estat normal
        gogeta.goStraight();
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Calculamos el desplazamiento desde la posición anterior
        int deltaX = screenX - previousX;
        int deltaY = screenY - previousY;

        // Si el desplazamiento en X es mayor que en Y, nos estamos moviendo horizontalmente
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // Si el desplazamiento en X es positivo, vamos hacia la derecha; de lo contrario, vamos hacia la izquierda
            if (deltaX > 0) {
                gogeta.goRight();
            } else {
                gogeta.goLeft();
            }
        } else { // Si el desplazamiento en Y es mayor que en X, nos estamos moviendo verticalmente
            // Si el desplazamiento en Y es positivo, vamos hacia arriba; de lo contrario, vamos hacia abajo
            if (deltaY > 0) {
                gogeta.goUp();
            } else {
                gogeta.goDown();
            }
        }

        // Actualizamos las posiciones anteriores
        previousX = screenX;
        previousY = screenY;

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
