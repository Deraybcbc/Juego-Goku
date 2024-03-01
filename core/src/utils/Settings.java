package utils;

public class Settings {

    // Mida del joc, s'escalarà segons la necessitat
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 800;

    public static final float BG_SPEED = -200; // Por ejemplo, la velocidad del fondo es 2.0


    // Propietats de la nau
    public static final float GOGETA_VELOCITY = 500;
    public static final int GOGETA_WIDTH = 49;
    public static final int GOGETA_HEIGHT = 89;

    public static final float GOGETA_STARTX = 20;
    public static final float GOGETA_STARTY = GAME_HEIGHT/2 - GOGETA_HEIGHT /2;

    // Rang de valors per canviar la mida de l'asteroide
    public static final float MAX_ROBOTS = 1.5f;
    public static final float MIN_ROBOTS = 0.5f;

    // Configuració scrollable
    public static final int ROBOTS_SPEED = -150;
    public static final int ROBOTS_GAP = 75;

    public static final int ROBOTS_WIDTH = 49;
    public static final int ROBOTS_HEIGHT = 69;

    public static final float MIN_ROBOT_SPEED = 100; // Velocidad mínima del robot
    public static final float MAX_ROBOT_SPEED = 300; // Velocidad máxima del robot

    public static final double ROBOT_SPAWN_CHANCE_PER_FRAME = 0.02;

}
