package AdventureGame;

import java.util.InputMismatchException;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private Award award;

    public BattleLoc(int id, Player player, String locName, Obstacle obstacle, Award award) {
        super(id, player, locName);
        this.obstacle = obstacle;
        this.award = award;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    @Override
    public boolean onLocation() {
        int obstacleCount = getRandomObstacleCount(obstacle.getMaxCount());
        System.out.println("\nYou are in " + super.getLocName() + " now.");
        System.out.println("Caution! There live " + obstacleCount + " " + obstacle.getName() + "'s, be careful...");
        System.out.println("Want to fight? (Y/N)");
        System.out.print("Sir " + super.getPlayer().getPlayerName() + ", press a valid key to select a case: ");
        String selection = "";

        try {
            selection = PlayGround.SCANNER.next();
            if (!selection.equalsIgnoreCase("y") && !selection.equalsIgnoreCase("n"))
                throw new InputMismatchException();
        } catch (InputMismatchException e) {
            System.out.println("Invalid value entered");
            System.exit(0);
        }

        if (selection.equalsIgnoreCase("y")) {
            if (combat(obstacleCount)) {
                System.out.println("\nCongrats! You've defeated all the " + obstacle.getName() + "'s in the " + this.getLocName() + ".");
            } else {
                if (super.getPlayer().getHealth() <= 0) {
                    System.out.println("\nYou've been defeated by the " + obstacle.getName() + "'s in the " + this.getLocName() + ".");
                    return false;
                }
            }
        } else {
            System.out.println("\nSee you!");
        }

        return true;
    }

    public int getRandomObstacleCount(int maxCount) {
        Random random = new Random();
        return random.nextInt(maxCount) + 1;
    }

    public boolean combat(int obstacleCount) {
        String selection = "";
        boolean isAlive = true;
        while (obstacleCount > 0 && isAlive) {
            if (obstacle.getHealth() <= 0) {
                obstacle.setHealth(obstacle.getDefaultHealth());
            }
            super.getPlayer().printInfo();
            obstacle.printInfo();
            System.out.println("\nAwards: " + award.getAmount() + " " + award.getName() + " + " + obstacle.getMoney() + " cash");

            while (super.getPlayer().getHealth() > 0 && obstacle.getHealth() > 0) {
                try {
                    System.out.print("\nHit or Run (H/R): ");
                    selection = PlayGround.SCANNER.next();
                    if (!selection.equalsIgnoreCase("h") && !selection.equalsIgnoreCase("r"))
                        throw new InputMismatchException();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid value entered");
                    System.exit(0);
                }

                if (selection.equalsIgnoreCase("h")) {
                    System.out.println("\nYou hit!");
                    obstacle.setHealth(obstacle.getHealth() - super.getPlayer().getDamage());
                    hitMessage();

                    if (obstacle.getHealth() > 0) {
                        System.out.println("\n" + obstacle.getName() + " hit!");
                        int totalDamage = super.getPlayer().getDefense() - obstacle.getDamage();
                        if (totalDamage < 0) {
                            totalDamage = Math.abs(totalDamage);
                            super.getPlayer().setHealth(super.getPlayer().getHealth() - totalDamage);
                        } else {
                            System.out.println("Hit blocked!");
                        }
                        hitMessage();
                    }
                } else {
                    System.out.println("\nEscaped!");
                    return false;
                }
            }
            if (super.getPlayer().getHealth() <= 0) {
                isAlive = false;
            } else {
                earnAwards(award, obstacle);
                obstacleCount--;
            }
        }
        return isAlive;
    }

    private void earnAwards(Award award, Obstacle obstacle) {
        super.getPlayer().setMoney(super.getPlayer().getMoney() + obstacle.getMoney());
        System.out.print("\nYou've earned " + obstacle.getMoney() + " cash + ");

        switch (award.getId()) {
            case 1:
                super.getPlayer().getInventory().getWater().setAmount(super.getPlayer().getInventory().getWater().getAmount() + award.getAmount());
                break;
            case 2:
                super.getPlayer().getInventory().getFood().setAmount(super.getPlayer().getInventory().getFood().getAmount() + award.getAmount());
                break;
            case 3:
                super.getPlayer().getInventory().getFirewood().setAmount(super.getPlayer().getInventory().getFirewood().getAmount() + award.getAmount());
                break;
        }
        System.out.println(award.getAmount() + " " + award.getName() + "'s");
    }

    private void hitMessage() {
        System.out.println("Your Health: " + Math.max(super.getPlayer().getHealth(), 0) + "\n" + obstacle.getName() + " Health: " + Math.max(obstacle.getHealth(), 0));
    }
}
