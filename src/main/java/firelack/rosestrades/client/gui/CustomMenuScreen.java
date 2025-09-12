package firelack.rosestrades.client.gui;

import firelack.rosestrades.client.ModKeyBindings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class CustomMenuScreen extends Screen {

    private int rosesBroken; 
    private TextFieldWidget amountField; // champ texte pour la quantité

    public CustomMenuScreen(int rosesBroken) {
        super(Text.literal("Menu Roses"));
        this.rosesBroken = rosesBroken;
    }

    @Override
    protected void init() {
        int topBarHeight = 25;
        int buttonWidth = 100;
        int buttonHeight = 20;
        int rightMargin = 10;

        // Champ texte pour le nombre de roses
        this.amountField = new TextFieldWidget(
            this.textRenderer,
            this.width - buttonWidth - rightMargin,
            30, // position Y sous le bouton
            buttonWidth,
            20,
            Text.literal("Amount")
        );
        this.amountField.setText("1");
        this.addSelectableChild(this.amountField);

        // Bouton give roses
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Give Roses"), b -> {
            if (client != null && client.player != null) {
                String text = this.amountField.getText();
                int amount = 1;
                try {
                    amount = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    amount = 1; // défaut
                }

                client.player.networkHandler.sendChatMessage(
                    "/give " + client.player.getGameProfile().getName() + " minecraft:rose " + amount
                );
            }
        }).dimensions(this.width - buttonWidth - rightMargin, 3, buttonWidth, buttonHeight).build());

        // Colonne de droite avec boutons
        int columnX = this.width - buttonWidth - rightMargin;
        int startY = topBarHeight + 60;
        int spacing = 25;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Recherche"), b -> {
            // action
        }).dimensions(columnX, startY, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Boutique"), b -> {
            // action
        }).dimensions(columnX, startY + spacing, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Inventaire"), b -> {
            // action
        }).dimensions(columnX, startY + spacing * 2, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("More"), b -> {
            // action
        }).dimensions(columnX, startY + spacing * 3, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Import"), b -> {
            // action
        }).dimensions(columnX, startY + spacing * 4, buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Fond flou vanilla
        this.renderBackground(context, mouseX, mouseY, delta);

        // Dessiner widgets
        super.render(context, mouseX, mouseY, delta);

        // Overlay custom
        // Bande supérieure
        context.fill(0, 0, this.width, 25, 0x88000000);
        context.drawTextWithShadow(this.textRenderer, "Roses cassées : " + rosesBroken, 10, 8, 0xFFFFFF);

        // Zone centrale
        int leftMargin = 20;
        int rightColumnWidth = 120;
        int topBarHeight = 25;
        context.fill(leftMargin, topBarHeight + 10, this.width - rightColumnWidth - 20, this.height - 20, 0x22000000);
        context.drawCenteredTextWithShadow(this.textRenderer, "Cosmétiques (scroll à venir)", this.width / 2, topBarHeight + 20, 0xAAAAAA);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Gestion du TextField
        if (this.amountField.keyPressed(keyCode, scanCode, modifiers) || this.amountField.isActive()) {
            return true;
        }

        // Fermer le menu avec la touche définie
        if (ModKeyBindings.OPEN_MENU.matchesKey(keyCode, scanCode)) {
            this.close();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        // Bloquer tout sauf les chiffres
        if (Character.isDigit(chr)) {
            return this.amountField.charTyped(chr, modifiers);
        }
        return super.charTyped(chr, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.amountField.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(null);
        }
    }
}
