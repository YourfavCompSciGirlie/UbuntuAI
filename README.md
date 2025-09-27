# UbuntuAI - Robocode Tank Royale Bot

Welcome to the UbuntuAI bot repository! This bot is created for the Robocode Tank Royale hackathon by Khomotso Kumalo, Siphamandla Mazibuko, and Malaika Kamangu. UbuntuAI is designed with strategic movement, adaptive tactics, and efficient energy management. It uses a combination of randomness and precise calculations to dominate in the arena!

## Table of Contents

- [Project Description](#project-description)
- [Bot Strategy](#bot-strategy)
- [Installation](#installation)
- [Running the Bot](#running-the-bot)
- [Customizing the Bot](#customizing-the-bot)
- [Submission Instructions](#submission-instructions)
- [Acknowledgments](#acknowledgments)

## Project Description

UbuntuAI is a strategic bot that employs a variety of tactics for movement and engagement. It operates with a straightforward strategy for beginners, yet has the potential for upgrades to enhance its performance.

- **Movement**: The bot moves in a seesaw motion, making it harder for opponents to target.
- **Targeting**: It fires when it detects another bot in its scan range.
- **Radar and Energy**: The bot is set to utilize radar and energy efficiently during combat.

### Bot Colors

- **Body Color**: #34A853
- **Turret Color**: #4285F4
- **Gun Color**: #FF9800
- **Radar Color**: #F44336
- **Bullet Color**: #9C27B0
- **Scan Color**: #3F51B5

## Bot Strategy

The UbuntuAI bot's strategy focuses on these key points:

### 1. Movement
- The bot uses a seesaw motion while moving forward, making it unpredictable and harder for opponents to track.
- The bot has a max speed limit of 3, which is balanced for quick movement while minimizing vulnerability.

### 2. Radar
- The radar continuously spins and keeps scanning for nearby enemies. This allows UbuntuAI to stay aware of its surroundings and react quickly.

### 3. Shooting
- The bot fires with a firepower of 1 when it detects a target in its scan range.
- The onScannedBot event triggers the firing mechanism.

### 4. Defense
- When the bot is hit by a bullet, it turns perpendicular to the bullet's direction to minimize further damage.

### 5. Adaptive Behavior (To be upgraded)
UbuntuAI is built to adapt to the environment and the behaviours of other bots in the arena. It can be modified to implement more advanced strategies, such as:
- **Energy management**: Use more energy-efficient movements.
- **Targeting behavior**: Customize how it targets different types of opponents based on their behavior.

## Installation

### Prerequisites

- Java 21 or later is required to run this bot. Ensure you have JDK 21+ installed:
```bash
  java -version
```

### Robocode Tank Royale setup:
- Download the Robocode Tank Royale GUI from the official releases page.
- Follow the setup instructions in the Robocode Tank Royale Docs.

### Clone the repository to your local machine:

  ```bash
git clone https://github.com/YourfavCompSciGirlie/UbuntuAI.git
  ```

## Running the Bot
1. From the Command Line (Linux/macOS)
  - Ensure your terminal is in the UbuntuAI directory.
  - Run the following command to start the bot:

```bash
./UbuntuAI.sh
```

2. From the Command Line (Windows)
  - Ensure your terminal is in the UbuntuAI directory.
  - Run the following command to start the bot:

```bash
UbuntuAI.cmd
```

3. Start a Battle in the Robocode GUI
  - Open the Robocode GUI using:

```bash
java -jar robocode-tankroyale-gui-x.y.z.jar
```

- Load your bot into the battle arena.

## Customizing the Bot
You can modify various elements of the bot to enhance its strategy or make it more competitive:

- Movement Strategy: Modify the run() method to change how the bot moves and dodges.
- Radar Strategy: Adjust how the radar scans and how often it rotates.
- Firing Strategy: Adjust when the bot fires, such as implementing different firing modes or calculating optimal firepower.
- Energy Management: Modify how the bot uses its energy for different actions to optimize performance.