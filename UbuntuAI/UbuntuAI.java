// import java.awt.geom.*;
import java.util.*;

import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import dev.robocode.tankroyale.botapi.graphics.Color;

// ------------------------------------------------------------------
// UbuntuAI
// ------------------------------------------------------------------
// A sample bot original made for Robocode by Mathew Nelson.
//
// Probably the first bot you will learn about.
// Moves in a seesaw motion and spins the gun around at each end.
// ------------------------------------------------------------------
public class UbuntuAI extends Bot {

    public static void main(String[] args) {
        new UbuntuAI().start();
    }

    @Override
    public void run() {
        String operating_mode = "DEF";

        setBodyColor(Color.BLACK);
        setTurretColor(Color.BLACK);
        setRadarColor(Color.GREEN);
        setScanColor(Color.GREEN);

        while (isRunning()) {
            operate(operating_mode);
            setTurnRight(10_000);
            setMaxSpeed(3);
            forward(5000);

            int enemies = getEnemyCount();
            System.out.println(enemies);
            if (enemies < 5) {
                setRadarColor(Color.RED);
                setScanColor(Color.RED);
                operating_mode = "ATK";
            } else {
                setRadarColor(Color.GREEN);
                setScanColor(Color.GREEN);
                operating_mode = "DEF";
            }
        }
    }

    @Override
    public void onScannedBot(ScannedBotEvent e) {
        double distanceToEnemy = distanceTo(e.getX(), e.getY());

        if (getEnergy() > 70) {
            if (distanceToEnemy < 200) {
                // if bot is close and we have high energy, shoot at 3
                fire(3);
            } else {
                // if bot is far and we have high energy, shoot at 1
                fire(1);
            }
        } else {
            // if we have low energy, shoot at 0.5
            fire(0.5);
        }
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        var bearing = calcBearing(e.getBullet().getDirection());
        turnRight(90 - bearing);
    }

    public void operate(String operating_mode) {
        if (operating_mode == "DEF") {
            // defend
        } else if (operating_mode == "ATK") {
            // attack
        }
    }

    public void findCorner() {
        Map<String, Double> distances = new HashMap<>();
        distances.put("TL", distanceTo(0, 0));
        distances.put("BL", distanceTo(0, getArenaHeight()));
        distances.put("BR", distanceTo(getArenaWidth(), getArenaHeight()));
        distances.put("TR", distanceTo(getArenaWidth(), 0));

        

    }
}
