package com.mycompany.turnbasedgame;

import java.util.Random;
import java.util.Stack;

public class Monster extends Character {
    
    public Monster(int playerHP, String playerName, int playerMaxDMG, int playerMinDMG, int playerSpeed) {
        super(playerHP, playerName, playerMaxDMG, playerMinDMG, playerSpeed);
    }

    private void healingPassive(Character character, Character enemy) {

        if(character.playerHPStack.size() <= 1 || new Random().nextInt(4) + 1 != 4) return;
        character.playerHPStack.pop();
        character.playerHP = character.playerHPStack.peek();
        System.out.printf("%s's *Passive Healing Ability* has healed itself back to %s%n", character.playerName, character.playerHP);
    }
        
    
     private void unoReverseTechnique (Character character, Character enemy) {

        Stack<Integer> damageInflictedStack = enemy.damageInflicted;

        if(damageInflictedStack.isEmpty()) damageInflictedStack.add(character.playerDMG);

        if (new Random().nextInt(10) == 0) {
            if (!damageInflictedStack.isEmpty()) {
                System.out.printf("%s has used *Uno Reversed Technique*. %s healed, %s damage returned %n", 
                        character.playerName,
                        damageInflictedStack.peek(),
                        damageInflictedStack.peek());
                        character.playerHPStack.push(character.playerHP += damageInflictedStack.peek());
                        enemy.playerDMG -= damageInflictedStack.pop();
            } 
            else System.out.println("UNO reverse card failed.");
        }
    }
    
    @Override
    void actionInputSelection(Character enemy) {
        
        Hero hero = (Hero) enemy;
        
//        System.out.printf("%n%s is Analyzing their Next Brilliant Move!!%n".formatted(this.playerName)); Thread.sleep(7000L);
                
                String randomBotChoice = switch (random.nextInt(3) + 1) {case 1 -> "attack"; case 2 -> "stun"; case 3 -> "skip"; default -> "ran";}; 
                System.out.printf("%n----- %s at Play! (Random Choice) -----%n".formatted(this.playerName));
                healingPassive(this, enemy);
                unoReverseTechnique(this, enemy);
                encounterInputAction(this, enemy, randomBotChoice);
                if(randomBotChoice.equals(randomBotChoice)) hero.parryTechnique();
                System.out.println("------------------------------------------"); //Thread.sleep(5000L);
    }
}
