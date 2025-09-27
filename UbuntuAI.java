import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import dev.robocode.tankroyale.botapi.graphics.Color;

// ------------------------------------------------------------------
// UbuntuAI
// ------------------------------------------------------------------
// 
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

        moveToCorner(100);

        double centerX = getArenaWidth()/2.0;
        double centerY = getArenaHeight()/2.0;
        double angleToCenter = bearingTo(centerX, centerY);
        turnGunRight(angleToCenter);

        while (isRunning()) {
            operate(operating_mode);
            turnGunRight(45);
            turnGunLeft(45);

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
                turnRight(directionTo(e.getX(), getY()));
                forward(100);
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
            turnRight(directionTo(0, 0));
            forward(distanceTo(0, 0) - 200);
            turnRight(directionTo(0, getArenaWidth()));
        } else if (operating_mode == "ATK") {
            turnRight(directionTo(0, 0));
            forward(distanceTo(0, 0) - 200);
            turnRight(directionTo(0, getArenaWidth()));
        }
    }

    public void moveToCorner(double margin) {
        double[][] corners = {
            {margin, margin},
            {margin, getArenaHeight()-margin},
            {getArenaWidth()-margin, getArenaHeight()-margin},
            {getArenaWidth()-margin, margin}
        };

        double[] best = corners[0];
        double bestDist = distanceTo(best[0], best[1]);
        for (double[] c : corners) {
            double d = distanceTo(c[0], c[1]);
            if (d < bestDist) {
                bestDist = d;
                best = c;
            }
        }

        double angle = bearingTo(best[0], best[1]);
        turnRight(angle);
        forward(bestDist);
    }
}
