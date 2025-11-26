<p align="center">
  <img src="./image.png" alt="Gentlemon Starters" width="650">
</p>

<h1 align="center">CS205 | Java | Final</h1>
<p align="center">A Java-based Pokemon battle simulator</p>

---

## 📘 Overview

**Pokemon Battle Simulator** is a terminal-based Pokemon battle simulator written entirely in Java.  
Choose a Pokemon, select your moves, and fight through a fully programmable turn based combat system inspired by classic Pokemon mechanics.

This project was created for my CS205 Final Project to demonstrate:

- Object-oriented programming  
- Multiple Java classes interacting  
- Constructors and method overloading  
- Encapsulation  
- Enums  
- Static variables & methods  
- ArrayLists  
- User input handling  

The result is a simple but surprisingly deep turn based battle engine.

---

## 🧱 Project Structure
PokemonBattleProject/
│
├── Type.java
├── Move.java
├── Pokemon.java
├── Battle.java
├── Game.java
└── README.md

---

## 🎮 Features

- Four playable Pokemon:
  - Pikachu ⚡  
  - Charizard 🔥  
  - Blastoise 🌊  
  - Venusaur 🌿  

- Move system with:
  - Accuracy  
  - Power  
  - Elemental types  
  - STAB (Same Type Attack Bonus)  
  - Type effectiveness (super effective / not effective)  

- Turn based battle engine  
- Opponent that selects random moves  
- HP tracking & fainting logic  
- Reusable battle logic via the `Battle` class  
- Records how many battles were created using static variables  

---

## 🚀 How to Compile & Run

### **📌 Running Locally (VS Code or Terminal)**

1. Install **Java JDK 17 or 21**
2. Clone or download this repository
3. Open the folder in VS Code or navigate into it via terminal
4. Compile all Java files:
```bash
javac *.java
```
5. Run the game:
```bash
java Game
```
