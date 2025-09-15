package firelack.rosestrades.client.gui;

import firelack.rosestrades.client.ModKeyBindings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;

public class CustomMenuScreen extends Screen {

    private int rosesBroken; 
    private TextFieldWidget amountField; // Field for amount of roses to give

    // Pages enum
    public enum Page {
        RECHERCHE, SHOP, INVENTAIRE, MORE, IMPORT
    }

    private Page currentPage = Page.SHOP; // Default page

    // Method to draw text in a box with word wrapping and centering
    private int drawTextInBox(DrawContext context, String texte, int boxLeft, int boxRight, int startY, int color) {
        int textXCenter = (boxLeft + boxRight) / 2;
        int boxWidth = boxRight - boxLeft - 20;
        int y = startY;

        for (OrderedText line : this.textRenderer.wrapLines(Text.literal(texte), boxWidth)) {
            int lineWidth = this.textRenderer.getWidth(line);
            int x = textXCenter - (lineWidth / 2);
            context.drawTextWithShadow(this.textRenderer, line, x, y, color);
            y += 12; // espace entre les lignes
        }

        return y; // retourne la nouvelle position Y pour continuer à dessiner
    }

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

        // Field for amount of roses
        this.amountField = new TextFieldWidget(
            this.textRenderer,
            this.width - buttonWidth - rightMargin,
            30, // Y position higher
            buttonWidth,
            20,
            Text.literal("Amount")
        );
        this.amountField.setText("1");
        this.addSelectableChild(this.amountField);

        // Bouton "Give Roses"
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Give Roses"), b -> {
            if (client != null && client.player != null) {
                String text = this.amountField.getText();
                int amount = 1;
                try {
                    amount = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    amount = 1; // Default
                }

                client.player.networkHandler.sendChatMessage(
                    "/give " + client.player.getGameProfile().getName() + " minecraft:rose " + amount
                );
            }
        }).dimensions(this.width - buttonWidth - rightMargin, 3, buttonWidth, buttonHeight).build());

        // Right column buttons
        int columnX = this.width - buttonWidth - rightMargin;
        int startY = topBarHeight + 20; 
        int spacing = 25;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Recherche"), b -> switchPage(Page.RECHERCHE))
            .dimensions(columnX, startY, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Boutique"), b -> switchPage(Page.SHOP))
            .dimensions(columnX, startY + spacing, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Inventaire"), b -> switchPage(Page.INVENTAIRE))
            .dimensions(columnX, startY + spacing * 2, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("More"), b -> switchPage(Page.MORE))
            .dimensions(columnX, startY + spacing * 3, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Import"), b -> switchPage(Page.IMPORT))
            .dimensions(columnX, startY + spacing * 4, buttonWidth, buttonHeight).build());
    }

    private void switchPage(Page page) {
        this.currentPage = page;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Background
        this.renderBackground(context, mouseX, mouseY, delta);

        // Draw widgets
        super.render(context, mouseX, mouseY, delta);

        // Top bar
        context.fill(0, 0, this.width, 25, 0x88000000);
        context.drawTextWithShadow(this.textRenderer, "Roses cassées : " + rosesBroken, 10, 8, 0xFFFFFF);

        // Middle box
        int leftMargin = 20;
        int rightColumnWidth = 120;
        int topBarHeight = 25;
        int boxLeft = leftMargin;
        int boxTop = topBarHeight + 10;
        int boxRight = this.width - rightColumnWidth - 20;
        int boxBottom = this.height - 20;

        context.fill(boxLeft, boxTop, boxRight, boxBottom, 0x22000000);

        // Content based on current page
        int y = boxTop + 10;

        switch (currentPage) {
            case RECHERCHE -> y = drawTextInBox(context, "Page to delete to make a search box on the button", boxLeft, boxRight, y, 0xFFFFFF);
            case SHOP -> y = drawTextInBox(context, "Futur shop, scroll and cosmetiques need to be added (+page for each article)", boxLeft, boxRight, y, 0xFFFF55);
            case INVENTAIRE -> y = drawTextInBox(context, "Futur inventory of the player", boxLeft, boxRight, y, 0x55FF55);
            case MORE -> {
                y = drawTextInBox(context, "More info : (page in construction)", boxLeft, boxRight, y, 0xFFFFFF);
                y = drawTextInBox(context, "Link :", boxLeft, boxRight, y, 0xAAAAAA);

                Text lien1 = Text.literal("→ Link don't work yet")
                        .setStyle(Style.EMPTY.withUnderline(true).withColor(Formatting.AQUA)
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Firelack")));

                for (OrderedText line : this.textRenderer.wrapLines(lien1, boxRight - boxLeft - 20)) {
                    int x = (boxLeft + boxRight) / 2 - this.textRenderer.getWidth(line) / 2;
                    context.drawTextWithShadow(this.textRenderer, line, x, y, 0xFFFFFF);
                    y += 12;
                }
            }
            case IMPORT -> y = drawTextInBox(context, "Future import function", boxLeft, boxRight, y, 0xAAAAFF);
        }

        // Signature
        String signature = "by Firelack";
        int sigWidth = this.textRenderer.getWidth(signature);
        context.drawTextWithShadow(this.textRenderer, signature, this.width - sigWidth - 5, this.height - 12, 0x808080);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Text field input
        if (this.amountField.keyPressed(keyCode, scanCode, modifiers) || this.amountField.isActive()) {
            return true;
        }

        // Close on custom key
        if (ModKeyBindings.OPEN_MENU.matchesKey(keyCode, scanCode)) {
            this.close();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        // Basic input validation: only digits
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
