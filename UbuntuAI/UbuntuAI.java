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

    // The main method starts our bot
    public static void main(String[] args) {
        new UbuntuAI().start();
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        setBodyColor(Color.BLACK);
        setTurretColor(Color.BLACK);
        setRadarColor(Color.BLUE);
        setScanColor(Color.RED);

        // Repeat while the bot is running
        while (isRunning()) {
            // Tell the game that when we take move, we'll also want to turn right... a lot
            setTurnRight(10_000);
            // Limit our speed to 5
            setMaxSpeed(3);
            // Start moving (and turning)
            forward(5000);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(1);
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        var bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnRight(90 - bearing);
    }
}
