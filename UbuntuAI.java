import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import dev.robocode.tankroyale.botapi.graphics.Color;

// ------------------------------------------------------------------
// UbuntuAI
// ------------------------------------------------------------------
// Strategic bot with adaptive movement, ramming capability, and smart targeting
// ------------------------------------------------------------------
public class UbuntuAI extends Bot {

    private String operatingMode = "DEF";
    private int turnDirection = 1; // clockwise (-1) or counterclockwise (1)

    public static void main(String[] args) {
        new UbuntuAI().start();
    }

    @Override
    public void run() {
        // Set Ubuntu-inspired colors
        setBodyColor(Color.GREEN); // Green
        setTurretColor(Color.BLUE); // Blue
        setGunColor(Color.ORANGE); // Orange
        setRadarColor(Color.RED); // Red
        setBulletColor(Color.PURPLE); // Purple
        setScanColor(Color.INDIGO); // Indigo

        // Seesaw movement strategy with adaptive behavior
        while (isRunning()) {
            operate(operatingMode);
            
            // Continuous radar scanning
            turnRadarRight(360);
            
            // Adaptive mode switching based on enemy count and energy
            int enemies = getEnemyCount();
            double energy = getEnergy();
            
            if (enemies < 3 || energy > 70) {
                operatingMode = "ATK";
                setRadarColor(Color.RED); // Red for attack mode
                setScanColor(Color.RED);
            } else if (enemies >= 3 || energy < 30) {
                operatingMode = "DEF";
                setRadarColor(Color.GREEN); // Green for defense mode
                setScanColor(Color.GREEN);
            }
        }
    }

    @Override
    public void onScannedBot(ScannedBotEvent e) {
        double distanceToEnemy = distanceTo(e.getX(), e.getY());
        double enemyBearing = bearingTo(e.getX(), e.getY());
        
        // Smart targeting based on distance and energy
        if (operatingMode.equals("ATK")) {
            // Attack mode: aggressive approach
            if (distanceToEnemy < 200) {
                // Close range: ram and powerful shot
                turnToFaceTarget(e.getX(), e.getY());
                forward(distanceToEnemy + 10);
                fire(3.0);
            } else if (distanceToEnemy < 500) {
                // Medium range: approach and medium shot
                turnToFaceTarget(e.getX(), e.getY());
                forward(distanceToEnemy / 2);
                fire(2.0);
            } else {
                // Long range: precise shot
                turnGunRight(enemyBearing);
                fire(1.0);
            }
        } else {
            // Defense mode: conservative approach
            if (distanceToEnemy < 150) {
                // Too close: retreat and weak shot
                turnToFaceTarget(e.getX(), e.getY());
                back(100);
                fire(0.5);
            } else {
                // Safe distance: precise weak shot
                turnGunRight(enemyBearing);
                fire(0.5);
            }
        }
        
        rescan(); // Continue scanning for better positioning
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Defensive maneuver: turn perpendicular to bullet direction
        var bearing = calcBearing(e.getBullet().getDirection());
        turnRight(90 - bearing);
        forward(50); // Move away from danger
    }

    @Override
    public void onHitBot(HitBotEvent e) {
        // Ramming strategy with immediate firing
        var direction = directionTo(e.getX(), e.getY());
        var bearing = calcBearing(direction);
        
        if (bearing > -15 && bearing < 15) {
            // Direct hit: powerful shot
            fire(3.0);
        }
        
        if (e.isRammed()) {
            // We rammed the enemy: strategic repositioning
            turnRight(45);
            forward(50);
            
            // Quick follow-up shot
            if (getEnergy() > 20) {
                fire(2.0);
            }
        }
    }

    public void operate(String operatingMode) {
        // Strategic movement based on mode
        if (operatingMode.equals("DEF")) {
            // Defense mode: circular movement near center
            setTurnRight(5000);
            setMaxSpeed(3);
            forward(200);
            
            // Occasionally change direction for unpredictability
            if (getTimeLeft() % 100 == 0) {
                turnDirection *= -1;
                setTurnRight(5000 * turnDirection);
            }
        } else {
            // Attack mode: aggressive forward movement with scanning
            setTurnRight(3000);
            setMaxSpeed(5);
            forward(300);
            
            // More frequent direction changes for attack pattern
            if (getTimeLeft() % 50 == 0) {
                turnDirection *= -1;
                setTurnRight(3000 * turnDirection);
            }
        }
    }

    private void turnToFaceTarget(double x, double y) {
        var bearing = bearingTo(x, y);
        if (bearing >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnLeft(bearing);
    }
}
