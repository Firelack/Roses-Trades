# 🌹 Roses Trades

**Minecraft Version:** 1.21.1  
**Mod Loader:** Fabric  

## 📖 Description

**Roses Trades** is a Minecraft mod focused on collecting and trading roses to unlock unique cosmetics.  
The system is inspired by the trading mechanics of the game Wolvesville.

> **Note:** At the moment, this repository only contains the base Fabric template.  
> Features will be added in future commits.

## 🚀 Features

- Features are in progress

## 📦 Requirements

- [Java 21](https://adoptium.net/) (required for Minecraft 1.21+)  
- [Fabric Loader](https://fabricmc.net/use/)  
- [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) on CurseForge or [Fabric API](https://modrinth.com/mod/fabric-api) on Modrinth (place it in your `mods` folder)  

## ✨ Planned Features

- 🌱 **Special Roses**:  
  - Naturally spawn in specific biomes.  
  - Can be harvested, but don't drop as items.  
  - Instead, a **rose counter** increases (only visible in the mod menu and when harvesting a rose).  

- 🎁 **Mod Menu**:  
  - Displays the rose counter.  
  - Option to **spawn physical roses** (cannot be re-added to the counter).  
  - Craft **bouquets** (decorative items for pots/frames).  
  - Access to a **shop** and a **cosmetics inventory**.  

- 🛒 **Cosmetics Shop**:  
  - Use roses as currency.  
  - Various cosmetics: hats, capes, skins rendered above armor (with an option to hide armor).  

- 🌍 **Multiplayer & Singleplayer**:  
  - In multiplayer: roses must be sent (and received) in order to be traded.  
  - In singleplayer: roses can be used for cosmetics without needing to send them.  

- ☁️ **Cosmetics Sharing**:  
  - Upload system for sharing custom cosmetics.  
  - Works in both singleplayer and on servers (cosmetics available to all players).  

- ✨ **Special Cosmetics** (ideas that may not necessarily be implemented):  
  - A unique set of very expensive cosmetics, synchronized across your worlds.  
  - Ability to add your own cosmetics to your games and servers.  
  - Achievements and rewards tied to rose collection.  

## 🚀 Installation

This mod is still in development and is not yet available for installation.  

## 🛣️ Detailed Roadmap

### 🌱 Rose Generation & Harvesting

- [ ] Create a new "Special Rose" item (non-stackable or limited stack).  
- [ ] Add natural rose generation in certain biomes.  
- [ ] Prevent roses from dropping as items → increment counter instead.  
- [ ] Implement a global rose counter tied to each player (persistent data).  

### 📊 Rose Counter

- [ ] Add a HUD/overlay to show the counter only when harvesting.  
- [ ] Integrate the counter into the mod menu (always accessible).  
- [ ] Save/load the counter between game sessions.  

### 🎁 Mod Main Menu

- [ ] Create a custom GUI screen accessible via a keybind or button.  
- [ ] Display the rose counter.  
- [ ] Button to **spawn a physical rose** (added to the inventory).  
- [ ] Crafting recipe for **bouquets** (decorative item).  
- [ ] Tabs: Shop / Cosmetics Inventory.  

### 🛒 Cosmetics Shop

- [ ] Define a list of available cosmetics (placeholders at first).  
- [ ] Allow purchases with roses.  
- [ ] Decrease the rose counter when buying.  
- [ ] Store purchased cosmetics in a dedicated inventory.  

### 👕 Cosmetics

- [ ] Implement a system to equip/unequip cosmetics.  
- [ ] Add basic cosmetics (e.g. hat, cape).  
- [ ] Option: hide armor so cosmetics are visible.  
- [ ] Render cosmetics on top of the player's skin.  

### 🌍 Multiplayer & Trading

- [ ] In multiplayer, ensure roses must be sent before being used.  
- [ ] Add a "gift" or "rose sending" system between players.  
- [ ] Prevent exploits (duplication/rollback issues).  

### ☁️ Cosmetics Sharing & Customization

- [ ] Create a simple file format for cosmetics (JSON + textures).  
- [ ] Add a command to import cosmetics in singleplayer.  
- [ ] Sync custom cosmetics with servers (available for all players).  
- [ ] Ensure compatibility (prevent crashes if a player is missing files).  

### ✨ Bonus & Future Improvements

- [ ] Rare/seasonal cosmetics (e.g. Christmas, Halloween).  
- [ ] Achievements and rewards tied to rose collection.  
- [ ] Event system (special drops, limited-time cosmetics).  
- [ ] Compatibility with other mods (Optifine, UI mods, etc).  
