package com.mycompany.turnbasedgame;

import static com.mycompany.turnbasedgame.TurnBasedGame.s;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {
    
    
    static Random random = new Random();

    // ----------------- Random Names LinkedList ----------------------------------
    public static LinkedList<String> firstNames = new LinkedList<>(List.of(
        "Michael", "James", "Cabasan","Tigbason","Luther", "Anthony","Edwards","Steph","Curry","Lebron",
        "James", "Russel", "Westbrook","Kobe","Bryant", "Butler", "Vincent", "Uchiha","Hyuga","Sasuke",
        "Naruto", "Uzumaki", "Killua","Zero","Michele", "Sarada", "Kyle", "Izuki","Natalio","Solis",
        "Justine", "Bronny", "Ligma","Ballu","Amper", "Maroon", "Brody", "Perry","Platapus","Monster",
        "Kage", "Izuka", "Joshua","Iron","Silver", "Bronze", "Golden", "Superior","Gon","Kurapika"
    )); 
        
    public static LinkedList<String> lastNames = new LinkedList<>(List.of(
        "Reponoya", "Ampi", "Palencia","Gonzaga","Capybara", "Dela Cruz","Garcia","Undangan","Olpenado","Cruz",
        "Padilla", "Cartel", "Mc Daniel","Magdallona","Pogi", "Coffin", "Grave", "Nightmare","Ghost","Oppenheimer",
        "Hitler", "Nazi", "Kanye","Lamar","Kendric", "Drake","Doechi","2pac","Zombie","Creeper",
        "Creep", "T-rex", "Crow","Oleander","Belladonna", "Craven","Raimi","Ash","Bruce","Campbell",
        "Cairns", "Hyuga", "Bovary","Marlowe","Poe", "Frost","Fire","Flame","Toxic","Poison"
    )); 
    // -----------------------------------------------------------------------------
    
    Character player;
    
    public void startGame(Character player1) {
        
        this.player = player1;
        
        
        boolean heart = true;
        
        while(heart) {
            
            // --------------------------- Open Menu -------------------------------
            System.out.println("""
                           -------------- Hello Gamer: "%s"! -------------
                           
                           What Would you like to Do?
                           1. -> Travel
                           2. -> Check Map
                           3. -> Fight Random
                           4. -> Exit
                           """.formatted(player1.playerName));
        
            System.out.print("Enter Input: ");
            // ---------------------------------------------------------------------
            
            try {
                
                int actionChoice = s.nextInt(); s.nextLine();
                
                if(actionChoice >= 1 && actionChoice <= 3) {
                    switch(actionChoice) {
                        case 1 -> travel();
                        case 2 -> System.out.println("""
                                    -------------- %s's Travel Map! --------------

                                    Area Coordinates :

                                    [0, 0] -> Spawn / Foosha Village
                                    [3, 12] -> Sky Island
                                    [-3, -12] -> Fish Island
                                    [15, 15] -> The Land of Wano
                                    [50, 50] -> The Grand Line
                                    Back to Menu
                                   """);
                        case 3 -> encounter(100, " (BOT)", 10, 1, 50);
                        case 4 -> heart = false;
                    }
                }
                else System.out.println("Invalid Input");
                
            } 
            catch (InterruptedException e) {
                System.out.println("Thread Interrupted"); s.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("That is an Invalid Input"); s.nextLine();
            }
        }
    }
    
    private void travel () throws InterruptedException {
        
        // -------------------- Location Area Coordinates ----------------------
        HashMap<String, String> locationAreaHashMap = new HashMap<>();
        
        locationAreaHashMap.put("[0,0]", "Spawn/Foosha Village");
        locationAreaHashMap.put("[3, 12]", "Sky Island");
        locationAreaHashMap.put("[-3, -12]", "Fish Island");
        locationAreaHashMap.put("[15, 15]", "The Land of Wano");
        locationAreaHashMap.put("[30, 30]", "GrandLine");
        // ---------------------------------------------------------------------
        
        while(true) {
            
            String location = "wilderness";
            
            if(locationAreaHashMap.get(Arrays.toString(player.playerPosition)) != null) {
                location = locationAreaHashMap.get(Arrays.toString(player.playerPosition));
            }
            else if ("[0,0]".equals(Arrays.toString(player.playerPosition))){
                location = "Spawn";
            }
            
            System.out.println("""
                               %n--------------- Player Movement Control -------------
                               
                               Current Location : %s
                               
                               %s's Position : x - %d  y - %d
                               
                               "w" -> North
                               "a" -> West
                               "s" -> South
                               "d" -> East
                               "x" -> Back to Menu
                               -----------------------------------------------------%n
                               """.formatted(location, player.playerName, player.playerPosition[0], player.playerPosition[1]));
            
            
  
                
            if(locationAreaHashMap.get(Arrays.toString(player.playerPosition)) != null){
                
                if(location.equals("Spawn")) {System.out.println("Welcome to Foosha Village/Spawn\n"); break;}

                String message = "It Appears That....,You Have Ventured in a New Location....,Prepare To DIE!!!!\n";

                for(var sentence : message.split(",")) {
                    System.out.println(sentence);
                    Thread.sleep(3500L);
                }

                System.out.println("____________________________________________________");
                System.out.println("----------------!! BOSS BATTLE !!-------------------");
                switch(location) {
                    case "Sky Island" -> encounter(150, " (Sky Island Boss)", 15, 5, 50);
                    case "Fish Island" -> encounter(50, " (Fish Island Boss)", 30, 15, 100);
                    case "The Land of Wano" -> encounter(250, " (Wano Boss)", 25, 10, 150); 
                    case "GrandLine" -> encounter(300, " (Grand Line Boss)", 30, 15, 250); 
                }

                break;
            }
            

            System.out.print("Enter Movement: ");
            
            String inputAction = s.nextLine().trim().toLowerCase();
            if(inputAction.isEmpty()) System.out.println("Bruh Moment");
            else {
                
                
                
                if(inputAction.charAt(0) == 'x') break;

                switch(inputAction.charAt(0)) {
                    case 'w' -> player.playerPosition[1]++;
                    case 'a' -> player.playerPosition[0]--;
                    case 's' -> player.playerPosition[1]--;
                    case 'd' -> player.playerPosition[0]++;
                }
            }
        }
    }
    
    
    private void encounter(int botHP, String additionalInfo, int maxDMG, int minDMG, int playerSpeed) throws InterruptedException {
        
        // ---------------------- Player HP Reset ------------------------------
        player.playerHP = 100;
        // ---------------------------------------------------------------------
        
        // ------------------ Random Bot Name Generator ------------------------
        String botFirstName = firstNames.get(random.nextInt(15));
        String botLastName = lastNames.get(random.nextInt(15));
        
        String botName = "%s %s%s".formatted(botFirstName, botLastName, additionalInfo);
        
        firstNames.remove(botFirstName);
        lastNames.remove(botLastName);
        // ---------------------------------------------------------------------
        
        Monster bot = new Monster(botHP, botName, maxDMG, minDMG, playerSpeed);
        
        int gameTime = 1;
        
        while(true) {
            
            if(player.isDefeated()) {
                System.out.println("You Have Lost!"); break;
            }
            if(player.isDefeated()) {
                System.out.println("You Have Won"); break;
            }
            
            
            if(player.playerSpeed > bot.playerSpeed) {
                System.out.println("You are Faster!");
                player.actionInputSelection(bot);
                bot.actionInputSelection(player);
            }
            else {
                System.out.println("The Bot is Faster!");
                bot.actionInputSelection(player);
                player.actionInputSelection(bot);
            }
            gameTime++;
        }
    }
}
