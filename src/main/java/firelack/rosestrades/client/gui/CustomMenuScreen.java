package firelack.rosestrades.client.gui;

import firelack.rosestrades.RosesTradesClient;
import firelack.rosestrades.client.ModKeyBindings;
import firelack.rosestrades.network.RoseSpendPayload;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import java.awt.Rectangle;
import java.util.List;
import net.minecraft.util.Util;

public class CustomMenuScreen extends Screen {

    private TextFieldWidget amountField; // Field for amount of roses to give
    private Rectangle githubLinkArea; // Area for the GitHub link

    // Pages enum
    public enum Page {
        SEARCH, SHOP, INVENTORY, MORE, IMPORT
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
            y += 12; // Line height
        }

        return y; // Return the new Y position after drawing
    }

    public CustomMenuScreen(int initialCount) {
        super(Text.literal("Menu Roses"));
        // Initial count can be used if needed
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

                // Send the spend request to the server
                ClientPlayNetworking.send(new RoseSpendPayload(amount));
            }
        }).dimensions(this.width - buttonWidth - rightMargin, 3, buttonWidth, buttonHeight).build());

        // Right column buttons
        int columnX = this.width - buttonWidth - rightMargin;
        int startY = topBarHeight + 20; 
        int spacing = 25;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Search"), b -> switchPage(Page.SEARCH))
            .dimensions(columnX, startY, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Shop"), b -> switchPage(Page.SHOP))
            .dimensions(columnX, startY + spacing, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Inventory"), b -> switchPage(Page.INVENTORY))
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
        // Show rose count
        context.drawTextWithShadow(
            this.textRenderer,
            Text.translatable("message.rosestrades.rose_count", RosesTradesClient.clientRoseCount),
            10,
            8,
            0xFFFFFF
        );

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
            case SEARCH -> y = drawTextInBox(context, "Page🌹😭 to delete to make a search box on the button", boxLeft, boxRight, y, 0xFFFFFF);
            case SHOP -> y = drawTextInBox(context, "Futur shop, scroll and cosmetiques need to be added (+page for each article)", boxLeft, boxRight, y, 0xFFFF55);
            case INVENTORY -> y = drawTextInBox(context, "Futur inventory of the player", boxLeft, boxRight, y, 0x55FF55);
            case MORE -> {
                // Text description
                y = drawTextInBox(context, "Welcome to Roses Trades, a Minecraft mod created by Firelack. 🌹", boxLeft, boxRight, y, 0xFFFFFF);
                y = drawTextInBox(context, "This mod focuses on collecting and trading special roses to unlock unique cosmetics.", boxLeft, boxRight, y, 0xAAAAAA);
                y = drawTextInBox(context, "Roses spawn in specific biomes and increase your rose counter instead of dropping as items.", boxLeft, boxRight, y, 0xAAAAAA);
                y = drawTextInBox(context, "The menu will allow you to track your rose counter, spawn physical roses, craft bouquets, and access the shop and cosmetics inventory.", boxLeft, boxRight, y, 0xAAAAAA);
                y = drawTextInBox(context, "Future features include buying cosmetics, equipping them, sharing custom cosmetics in multiplayer, and tracking achievements.", boxLeft, boxRight, y, 0xAAAAAA);

                y += 10; // Space before the link

                // GitHub link
                Text githubLink = Text.literal("→ GitHub Project")
                        .setStyle(Style.EMPTY
                                .withUnderline(true)
                                .withColor(Formatting.AQUA)
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
                                        "https://github.com/Firelack/Roses-Trades")));

                List<OrderedText> wrappedLink = this.textRenderer.wrapLines(githubLink, boxRight - boxLeft - 20);
                int linkX = (boxLeft + boxRight) / 2;
                int linkY = y;

                for (OrderedText line : wrappedLink) {
                    int lineWidth = this.textRenderer.getWidth(line);
                    context.drawTextWithShadow(this.textRenderer, line, linkX - lineWidth / 2, linkY, 0xFFFFFF);
                    linkY += 12;
                }

                // Save the clickable area for the link
                githubLinkArea = new Rectangle(linkX - this.textRenderer.getWidth(githubLink) / 2, y, this.textRenderer.getWidth(githubLink), wrappedLink.size() * 12);
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

        // Verify if the GitHub link was clicked
        if (githubLinkArea != null && githubLinkArea.contains((int) mouseX, (int) mouseY)) {
            Util.getOperatingSystem().open("https://github.com/Firelack");
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
