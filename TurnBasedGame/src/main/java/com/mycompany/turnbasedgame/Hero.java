package com.mycompany.turnbasedgame;

import static com.mycompany.turnbasedgame.TurnBasedGame.s;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Hero extends Character {
    
    Queue<Integer> attackStack = new LinkedList<>();
    
    public Hero(int playerHP, String playerName, int playerMaxDMG, int playerMinDMG, int playerSpeed) {
        super(playerHP, playerName, playerMaxDMG, playerMinDMG, playerSpeed);
    }
    
    
    // ----------------------------------- Parry by Mark Vincent Palencia ----------------------------------------
    // ===========================================================================================================
    public void parryTechnique (){
      int chance = random.nextInt(16);
      if(chance != 1) {
          return;
      }
      
      int chance2 = random.nextInt(5);
      double array [] = {0.05,0.1,0.15,0.20,0.25};
      double boost = array[chance2];
     
      System.out.println("*Parry Technique* Player Has Parried the Attack " + (boost * 100) + "% to HP and DMG");
      
      this.playerHP = (int)(this.playerHP + (this.playerHP * boost));
      this.playerMinDMG = (int)(this.playerMinDMG + (this.playerMinDMG * boost));
      this.playerMaxDMG = (int)(this.playerMaxDMG + (this.playerMaxDMG * boost));         
    }
    // ===========================================================================================================
    
    
    // --------------------- Passive Abilities of Hero ----------------------    
    private void jinguMasteryTechnique (Character character, Character enemy) {
        
        attackStack.add(character.playerDMG);
        
        if(attackStack.size() % 4 == 0) {
            
            int abilityChoice = new Random().nextInt(2);
            
            if(abilityChoice == 0) {
                System.out.println("*Turn Technique Passive/Jingu Mastery* has been activiated -> double damage");
                character.playerDMG = character.playerDMG * 2;
            }
            else {
                System.out.println("*Turn Technique Passive/Jingu Mastery* has been activiated -> enemy has been burned for 3 turns");
                enemy.burned = 3;
            }
        }
    }
    // -----------------------------------------------------------------------

    @Override
    void actionInputSelection(Character enemy) {
        
                System.out.println("""
                               
                               %s HP : %d HP
                               %s HP : %d HP
                               
                               Actions :
                               `>> type `attack`
                               `>> type `stun`
                               `>> type `skip`
                               """.formatted(this.playerName, this.playerHP, enemy.playerName, enemy.playerHP));
                
                System.out.print("Enter Action : ");
                String actionStringInput = s.nextLine().trim().toLowerCase();
                
                System.out.println("\n----------- Player at Play! ------------");
                if(actionStringInput.equals("attack")) jinguMasteryTechnique(this, enemy);
                encounterInputAction(this, enemy, actionStringInput);
                System.out.println("------------------------------------------");
    }
}
