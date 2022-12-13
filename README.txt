# CS611-legends

This is a magical game full of spells, monsters, and heroes.

---------------------------------------------------------------------------
I-Ju Lin
liniju@bu.edu
U05295432

Piyush Kathuria
pkath@bu.edu
U01034912
---------------------------------------------------------------------------

## Compile instructions

```shell
mkdir bin
javac -d bin src/*.java src/java/*/*.java src/java/*/*/*.java
java -cp bin src.Main
```

## Interesting designs

### Generics for display

Based on experience from previous homeworks, terminal games use selection a lot. Though lists are composed with various classes, they all want to be numbered and displayed. Thus, I create a `SelectionController.java` to cover the class-wide usage. Also, many strings constant to display in several stages of the game. Thus, I create `Messages.java` to help display management.

### HashMap for KeyInputs and InventoryType

HashMap helps with the enum and items' categorization. For example, `Inputs` provides the key inputs list that can further use in any input interactions like map walking direction, confirmation query, and global quit or info display.

### MVC design

I've separated most objects into MVC and handler design. That is, classes in modules folder is purely attributes and getter/setter for the data manipulation. Classes end their name with controller takes all the job of wrapping data getting and setting with the scenarios in need. For any of those messages showing to users, such as selection list or action confirmation, viewers are the ones taking the role. Whenever these operations are including various objects, I use handler to make sure all the input checking and game logistics.

For each object:
+---------+    +------------+       +------+
| Module |-----| Controller | ----- | View | -----> Display to users.
+---------+    +------------+       +------+
                     |
               +------------+
               |   Handler  | ---------------------> Interact with other objects.
               +------------+

### Builder to preload data in provided `Legends_Monsters_and_Heroes.zip` and Factory to create new objects.

According to the .txt data provided, I created `ResourceParser.java` as an util to help data parsing. After that, I have `MonsterFactory.java`, `HeroBuilder.java`, and `WeaponFactory.java`, etc. to generate the according objects with prepared attribute. And `MonsterFactory.java` is responsible to create a new monster according to hero party's level in each battle according to the preparsed data.

For each predefined objects in Module, such as monsters, weapons, spells, potions:
+------------+     +---------+       +---------+
| <DATA>.txt |-----| Builder | ----- | Factory | -----> new objects for controllers.
+------------+     +---------+       +---------+

### Customized Exceptions

I've extends RuntimeException to several legend game specific exceptions, such as `InvalidEquipments.java` and `InvalidMoveExceptions.java`. As a result, the error handling can be specific to which scenario meets these either NullPointerException or index out of the bound. Additionally, I can throw the error in any class or function without handling them in each place errors happened. Furthermore, I create `Request.java` to implement global control, e.g. QuitRequest, MapDisPlayRequest, and HelpInstructionRequest.

### Logger to improve game experience

Since players may be overwhelmed by the status display, I take the advantage of logger to set which severity to see. That is, if players do not want to see any double check with their input. They can set the leve to `warning` or `error`, so no `info` or `debug` messages would display in the game.

### Setting `config` without recompile

In case any game settings not influencing game logistics flow update in the running time, I distill them into the config file. As a result, the game manager do not need to recompile the game. Those without programming experience are welcome to change this game as they want like the map size, rendered ratios for various map components, monster appearing ration, etc. After the first compile, this game is ready to deliver to customers with multiple legend settings.

### World Grid Creation Logic ###

For creating the world grid, we filled out each lane by calculating how many empty cells we have based on board configuration, creating plain and special(Bush,Koulou,Cave) cells in the 40:60 ratio and distributing them randomly in the lanes.

## Input/Output Example

### Welcome scene

🪄 Welcome to the magic legend world ⚔️
`ARRRRRRRRRRR` A monster is born at [00][0]. Be prepared for a fight!
`ARRRRRRRRRRR` A monster is born at [00][3]. Be prepared for a fight!
`ARRRRRRRRRRR` A monster is born at [00][6]. Be prepared for a fight!

### Map movement

🤠️: hero positions.   👾: monster positions.
🏠: Nexus.   🌲: inaccessible forests.
🌳: bush.   ⛰️: cave.   ✨: Koulou.
  +------+------+------+------+------+------+------+------+
00|  🏠👾|  🏠  |🌲🌲🌲|  🏠👾|  🏠  |🌲🌲🌲|  🏠👾|  🏠  |
  +------+------+------+------+------+------+------+------+
01|  🌳  |  ✨  |🌲🌲🌲|      |  ⛰️  |🌲🌲🌲|  ⛰️  |  🌳  |
  +------+------+------+------+------+------+------+------+
02|  🌳  |      |🌲🌲🌲|      |  ✨  |🌲🌲🌲|      |  🌳  |
  +------+------+------+------+------+------+------+------+
03|  ⛰️  |  ⛰️  |🌲🌲🌲|  ✨  |  🌳  |🌲🌲🌲|      |  ✨  |
  +------+------+------+------+------+------+------+------+
04|  🌳  |  ⛰️  |🌲🌲🌲|  ⛰️  |      |🌲🌲🌲|      |  🌳  |
  +------+------+------+------+------+------+------+------+
05|      |      |🌲🌲🌲|      |  ⛰️  |🌲🌲🌲|  ⛰️  |  ⛰️  |
  +------+------+------+------+------+------+------+------+
06|      |  ✨  |🌲🌲🌲|  🌳  |  🌳  |🌲🌲🌲|  ✨  |      |
  +------+------+------+------+------+------+------+------+
07|🤠️🏠  |  🏠  |🌲🌲🌲|🤠️🏠  |  🏠  |🌲🌲🌲|🤠️🏠  |  🏠  |
  +------+------+------+------+------+------+------+------+
      0      1      2      3      4      5      6      7

Current position: [06][6], Spawn position: [07][6]
Reverie_Ashels, which action do you want to take?
(Q) Quit   (W) Up⬆️       (R) Recall           (T) Teleport       (I) Information      (P) Show map
  (A) Left⬅️ (S) Down⬇️     (D) Right➡️          (H) Help
    (Z) Attack (X) Cast Spell (C) Change Equipment (V) Drink Potion (M) Enter Market

h
Take advantage of the terrain, coordinate actions between heroes, and use items to outwit and outfight the invading waves of monsters.
Can you destroy the monsters’ Nexus and stop the monster invasion? Or will the monsters overrun your own fortress?
Feel free to type:
`(Q) Quit` whenever you need an emergency exit. `(P) Map` to see the legend world.
`(I) Information` to know more about your team. `(H) Help` to know the things to do.
Current position: [06][6], Spawn position: [07][6]
Type <Enter> to continue.

### Battle

#### Monster's attack

💥 Sehanine_Moonbow gets hit by 950 damage.
💫 Sehanine_Moonbow is dead and respawn where the hero was born.

#### Hero's attack

💥 Taltecuhtli gets hit by 825 damage.
🪙 Every hero get 500 gold and 2 experience points.
Sehanine_Monnbow's level up (1)
😵 Taltecuhtli is dead and respawn where it was born.

### Market

😎 Nice to meet you. I'm sure we've never met before!
================Vendor's Table======================
 0) 🗡️[Weapon]         $500   Sword                | Level required: 1 | Damage: 800
 1) 🗡️[Weapon]         $300   Bow                  | Level required: 2 | Damage: 500
 2) 🗡️[Weapon]         $1000  Scythe               | Level required: 6 | Damage: 1100
 3) 🗡️[Weapon]         $550   Axe                  | Level required: 5 | Damage: 850
 4) 🗡️[Weapon]         $1400  TSwords              | Level required: 8 | Damage: 1600
 5) 🗡️[Weapon]         $200   Dagger               | Level required: 1 | Damage: 250
=================== Parzival's Bag========================
Your bag is empty.
========================================================================
Type in the number in a listed item or (Q) Exist this market (P) Show the map (I) Show hero's information (H) Get help instruction.
😎 Which item do you want to buy or sell? Selling is in half price.

### Hero selection

Are you over 18 years old? (yes/no) y
Which hero do you want to add?
 0) [Paladin]    Parzival             Lv.1  | STR 750 | DEF 0 | AGI 650 | DEX 700 | EXP  7 | HP 100 | MP  300 | 💰 2500
 1) [Paladin]    Sehanine_Moonbow     Lv.1  | STR 750 | DEF 0 | AGI 700 | DEX 700 | EXP  7 | HP 100 | MP  300 | 💰 2500
 2) [Paladin]    Skoraeus_Stonebones  Lv.1  | STR 650 | DEF 0 | AGI 600 | DEX 350 | EXP  4 | HP 100 | MP  250 | 💰 2500
 3) [Paladin]    Garl_Glittergold     Lv.1  | STR 600 | DEF 0 | AGI 500 | DEX 400 | EXP  5 | HP 100 | MP  100 | 💰 2500
 4) [Paladin]    Amaryllis_Astra      Lv.1  | STR 500 | DEF 0 | AGI 500 | DEX 500 | EXP  5 | HP 100 | MP  500 | 💰 2500
 5) [Paladin]    Caliber_Heist        Lv.1  | STR 400 | DEF 0 | AGI 400 | DEX 400 | EXP  8 | HP 100 | MP  400 | 💰 2500
 6) [Sorcerer]   Rillifane_Rallathil  Lv.1  | STR 750 | DEF 0 | AGI 450 | DEX 500 | EXP  9 | HP 100 | MP 1300 | 💰 2500
 7) [Sorcerer]   Segojan_Earthcaller  Lv.1  | STR 800 | DEF 0 | AGI 500 | DEX 650 | EXP  5 | HP 100 | MP  900 | 💰 2500
 8) [Sorcerer]   Reign_Havoc          Lv.1  | STR 800 | DEF 0 | AGI 800 | DEX 800 | EXP  8 | HP 100 | MP  800 | 💰 2500
 9) [Sorcerer]   Reverie_Ashels       Lv.1  | STR 800 | DEF 0 | AGI 700 | DEX 400 | EXP  7 | HP 100 | MP  900 | 💰 2500
10) [Sorcerer]   Kalabar              Lv.1  | STR 850 | DEF 0 | AGI 400 | DEX 600 | EXP  6 | HP 100 | MP  800 | 💰 2500
11) [Sorcerer]   Skye_Soar            Lv.1  | STR 700 | DEF 0 | AGI 400 | DEX 500 | EXP  5 | HP 100 | MP 1000 | 💰 2500
12) [Warrior]    Gaerdal_Ironhand     Lv.1  | STR 700 | DEF 0 | AGI 500 | DEX 600 | EXP  7 | HP 100 | MP  100 | 💰 1354
13) [Warrior]    Sehanine_Monnbow     Lv.1  | STR 700 | DEF 0 | AGI 800 | DEX 500 | EXP  8 | HP 100 | MP  600 | 💰 2500
14) [Warrior]    Muamman_Duathall     Lv.1  | STR 900 | DEF 0 | AGI 500 | DEX 750 | EXP  6 | HP 100 | MP  300 | 💰 2546
15) [Warrior]    Flandal_Steelskin    Lv.1  | STR 750 | DEF 0 | AGI 650 | DEX 700 | EXP  7 | HP 100 | MP  200 | 💰 2500
16) [Warrior]    Undefeated_Yoj       Lv.1  | STR 800 | DEF 0 | AGI 400 | DEX 700 | EXP  7 | HP 100 | MP  400 | 💰 2500
17) [Warrior]    Eunoia_Cyn           Lv.1  | STR 700 | DEF 0 | AGI 800 | DEX 600 | EXP  6 | HP 100 | MP  400 | 💰 2500
9
Which lane do you want to spawn? (0 to 2)
0
====================== You added this hero ============================
[Sorcerer]   Reverie_Ashels       Lv.1  | STR 800 | DEF 0 | AGI 700 | DEX 400 | EXP  7 | HP 100 | MP  900 | 💰 2500
Current position: [07][0], Spawn position: [07][0]
========================================================================
Type <Enter> to continue.

## Possible improvements

- [ ] Save game progress and allow resume in the beginning.
- [ ] Observer Pattern
- [x] Wrap shell script before submission.
- [ ] Check level requirement before the usage of each item.
- [x] Clear unused imports.

## Script before submission

```shell
zip -d pa4.zip __MACOSX/\* \*\*/.DS_Store
```
