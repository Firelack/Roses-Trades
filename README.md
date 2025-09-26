# 🌹 Roses Trades

**Minecraft Version:** 1.21.1  
**Mod Loader:** Fabric  

## 📚 Table of Contents

- [🌹 Roses Trades](#-roses-trades)
  - [📚 Table of Contents](#-table-of-contents)
  - [📖 Description](#-description)
  - [🚀 Features](#-features)
  - [☑️ Installation](#️-installation)
  - [📦 Requirements](#-requirements)
  - [✨ Planned Features](#-planned-features)
  - [🛣️ Detailed Roadmap](#️-detailed-roadmap)
    - [🎁 Mod Main Menu](#-mod-main-menu)
    - [🛒 Cosmetics Shop](#-cosmetics-shop)
    - [👕 Cosmetics](#-cosmetics)
    - [🌍 Multiplayer \& Trading](#-multiplayer--trading)
    - [☁️ Cosmetics Sharing \& Customization](#️-cosmetics-sharing--customization)
    - [✨ Bonus \& Future Improvements](#-bonus--future-improvements)
    - [🌹 Roses boss](#-roses-boss)

## 📖 Description

**Roses Trades** is a Minecraft mod focused on collecting and trading roses to unlock unique cosmetics.  
The system is inspired by the trading mechanics of the game [Wolvesville](https://www.wolvesville.com/).

> **Note:** At the moment, this repository contains a little part of the mod.  
> Features will be added in future commits.

## 🚀 Features

- 🌱 **Special Roses**:  
  - Naturally spawn in specific biomes.  
  - Can be harvested, but don't drop as items.  
  - Instead, a **rose counter** increases.  
  - Commands to see position and leaderboard.
- 🎁 **Mod Menu**:  
  - Displays the rose counter.  
  - Option to **spawn physical roses** (cannot be re-added to the counter).  
  - Craft **bouquets** (decorative items for pots/frames).
  - Access to a **shop** and a **cosmetics inventory**.  
- 🛒 **Cosmetics Shop**:  
  - Use roses as currency.
- **Features are in progress**

## ☑️ Installation

This mod is still in development and is not yet available for installation.  

## 📦 Requirements

- [Java 21](https://adoptium.net/) (required for Minecraft 1.21+)  
- [Fabric Loader](https://fabricmc.net/use/)  
- [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) on CurseForge or [Fabric API](https://modrinth.com/mod/fabric-api) on Modrinth (place it in your `mods` folder)  

## ✨ Planned Features

- 🛒 **Cosmetics Shop**:  
  - Various cosmetics: hats, capes, skins rendered above armor (with an option to hide armor).  

- 🌍 **Multiplayer & Singleplayer**:  
  - In multiplayer: roses can be sent to other player.  
  - In singleplayer: roses can be used for cosmetics without needing to send them.  

- ☁️ **Cosmetics Sharing**:  
  - Upload system for sharing custom cosmetics.  
  - Works in both singleplayer and on servers (cosmetics available to all players).  

- ✨ **Special Cosmetics** (ideas that may not necessarily be implemented):  
  - A unique set of very expensive cosmetics, synchronized across your worlds.  
  - Ability to add your own cosmetics to your games and servers.  
  - Achievements and rewards tied to rose collection.  

- 🌹 **Rose Boss**
  - Add a Carnivorous Rose.
  - Spawn in specific structure.
  - Give roses to player who kill her (divide by two each time)

## 🛣️ Detailed Roadmap

### 🎁 Mod Main Menu

- [X] Create a custom GUI screen accessible via a keybind.  
- [x] Display the rose counter.  
- [x] Button to **spawn a physical rose**.
- [x] Option to choose how many roses you want.  
- [x] Add item and crafting recipe for **bouquets** (decorative item).
- [x] Add block (and potted_bouquet) for decorative bouquets.
- [x] Tabs: Shop / Cosmetics Inventory.
- [ ] Add confirmation on clickable link.
- [ ] Add a real Search zone.
- [x] Language compatibility.

### 🛒 Cosmetics Shop

- [x] Define a list of available cosmetics (placeholders at first).  
- [x] Allow purchases with roses.  
- [x] Decrease the rose counter when buying.  
- [x] Store purchased cosmetics in a dedicated inventory.  

### 👕 Cosmetics

- [ ] Implement a system to equip/unequip cosmetics.  
- [ ] Add basic cosmetics (e.g. hat, cape).  
- [ ] Option: hide armor so cosmetics are visible.  
- [ ] Render cosmetics on top of the player's skin.  

### 🌍 Multiplayer & Trading

- [ ] Add a "gift" or "rose sending" system between players.
- [ ] These gift could not be send back or to an other player.

### ☁️ Cosmetics Sharing & Customization

- [ ] Create a simple file format for cosmetics (JSON + textures).  
- [ ] Add a page to import cosmetics in singleplayer.  
- [ ] Sync custom cosmetics with servers (available for all players).  
- [ ] Ensure compatibility (prevent crashes if a player is missing files).  

### ✨ Bonus & Future Improvements

- [ ] Rare/seasonal cosmetics (e.g. Christmas, Halloween).  
- [ ] Achievements and rewards tied to rose collection.  
- [ ] Event system (special drops, limited-time cosmetics).  
- [ ] Compatibility with other mods (ModMenu, Optifine, UI mods, etc).  
- [ ] Implement a global rose counter tied to each player (persistent data) and cosmetics sync.  

### 🌹 Roses boss

- [ ] Add Roses boss mob.
- [ ] Create a structure for spawn Roses boss.
- [ ] Add system to invoke the boss.
- [ ] Incremente player who kill the boss counter (divide by 2 each time).
