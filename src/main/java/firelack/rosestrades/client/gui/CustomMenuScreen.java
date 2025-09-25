package firelack.rosestrades.client.gui;

import firelack.rosestrades.RosesTradesClient;
import firelack.rosestrades.client.ModKeyBindings;
import firelack.rosestrades.cosmetics.Cosmetic;
import firelack.rosestrades.cosmetics.CosmeticRegistry;
import firelack.rosestrades.cosmetics.CosmeticShop;
import firelack.rosestrades.network.BuyCosmeticPayload;
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
import java.util.Set;
import net.minecraft.util.Util;

public class CustomMenuScreen extends Screen {

    private TextFieldWidget amountField; // Field for amount of roses to give
    private TextFieldWidget searchField; // Field for searching cosmetics
    private Rectangle githubLinkArea; // Area for the GitHub link

    // Shop instance
    private final CosmeticShop shop = new CosmeticShop();

    // Pages enum
    public enum Page {
        SHOP, INVENTORY, MORE, IMPORT
    }

    private Page currentPage = Page.SHOP; // Default page

    // Helper method to draw centered text in a box with wrapping
    private int drawTextInBox(DrawContext context, Text texte, int boxLeft, int boxRight, int startY, int color) {
        int textXCenter = (boxLeft + boxRight) / 2;
        int boxWidth = boxRight - boxLeft - 20;
        int y = startY;

        for (OrderedText line : this.textRenderer.wrapLines(texte, boxWidth)) {
            int lineWidth = this.textRenderer.getWidth(line);
            int x = textXCenter - (lineWidth / 2);
            context.drawTextWithShadow(this.textRenderer, line, x, y, color);
            y += 12; // Line height
        }

        return y; // Return the new Y position after drawing
    }

    public CustomMenuScreen(int initialCount) {
        super(Text.translatable("rosestrades.menu.roses_menu"));
    }

    @Override
    protected void init() {
        super.init();
        this.clearChildren(); // Reload buttons when new pages load

        int topBarHeight = 25;
        int buttonWidth = 100;
        int buttonHeight = 20;
        int rightMargin = 10;

        // Create the text field (small, for number only)
        this.amountField = new TextFieldWidget(
            this.textRenderer,
            this.width - buttonWidth - rightMargin - 45, // position left of the button
            3, // y
            40, // width
            20, // height
            Text.translatable("rosestrades.menu.amount")
        );
        this.amountField.setText("1");
        this.amountField.setPlaceholder(Text.literal("1"));
        this.amountField.setEditable(true);
        this.amountField.setDrawsBackground(true);
        this.addDrawableChild(this.amountField);

        // Bouton "Give Roses"
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("rosestrades.menu.give_roses"), b -> {
            if (client != null && client.player != null) {
                String text = this.amountField.getText();
                int amount = 1;
                try {
                    amount = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    amount = 1; // Default if invalid input
                }
                // Send the spend request to the server
                ClientPlayNetworking.send(new RoseSpendPayload(amount));
            }
        }).dimensions(this.width - buttonWidth - rightMargin, 3, buttonWidth, buttonHeight).build());

        // Right column buttons
        int columnX = this.width - buttonWidth - rightMargin;
        int startY = topBarHeight + 20; 
        int spacing = 25;

        this.searchField = new TextFieldWidget(
            this.textRenderer, columnX, startY, buttonWidth, buttonHeight, Text.translatable("rosestrades.menu.search"));
        this.searchField.setPlaceholder(Text.translatable("rosestrades.menu.search"));
        this.searchField.setEditable(true);
        this.searchField.setDrawsBackground(true);
        this.addDrawableChild(this.searchField);

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("rosestrades.menu.page.shop"), b -> switchPage(Page.SHOP))
            .dimensions(columnX, startY + spacing, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("rosestrades.menu.page.inventory"), b -> switchPage(Page.INVENTORY))
            .dimensions(columnX, startY + spacing * 2, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("rosestrades.menu.page.more"), b -> switchPage(Page.MORE))
            .dimensions(columnX, startY + spacing * 3, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("rosestrades.menu.page.import"), b -> switchPage(Page.IMPORT))
            .dimensions(columnX, startY + spacing * 4, buttonWidth, buttonHeight).build());

        // Middle box content based on current page
        int leftMargin = 20;
        int rightColumnWidth = 120;
        int boxLeft = leftMargin;
        int boxTop = topBarHeight + 10;
        int boxRight = this.width - rightColumnWidth - 20;

        int y = boxTop + 40; // Start below the title

        Set<String> ownedCosmetics = RosesTradesClient.clientCosmetics;

        switch (currentPage) {
            case SHOP -> {
                for (Cosmetic c : shop.getAllCosmetics()) {
                    if (ownedCosmetics.contains(c.getId())) continue;
                    boolean owned = ownedCosmetics.contains(c.getId());

                    ButtonWidget button = ButtonWidget.builder(
                        owned
                            ? Text.translatable("rosestrades.menu.purchase.already", c.getName() )
                            : Text.translatable("rosestrades.menu.purchase.price", c.getName(), c.getPrice() ),
                        b -> {
                            if (!owned) {
                                // Send purchase request to server
                                ClientPlayNetworking.send(new BuyCosmeticPayload(c.getId()));
                                b.setMessage(Text.translatable("rosestrades.menu.purchase.already", c.getName() ));
                                b.active = false; // Desactivate after purchase
                            }
                        }
                    ).dimensions(boxLeft + 10, y, (boxRight - boxLeft) - 20, 20).build();

                    if (owned) button.active = false; // Desactivate if already owned
                    this.addDrawableChild(button);

                    y += 25;
                }
            }
            case INVENTORY -> {
                for (String id : ownedCosmetics) {
                    Cosmetic c = CosmeticRegistry.get(id);
                    if (c != null) {
                        this.addDrawableChild(ButtonWidget.builder(
                            Text.translatable("rosestrades.menu.inventory.nequipped", c.getName() ),
                            button -> System.out.println(Text.translatable("rosestrades.menu.inventory.equipped", c.getName() ))
                        ).dimensions(boxLeft + 10, y, (boxRight - boxLeft) - 20, 20).build());
                        y += 25;
                    }
                }
            }
            default -> {
                // Nothing special for other pages yet
            }
        }
    }

    private void switchPage(Page page) {
        this.currentPage = page;
        this.init(this.client, this.width, this.height); // Re-initialize to refresh buttons
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Background
        this.renderBackground(context, mouseX, mouseY, delta);

        // Draw widgets
        super.render(context, mouseX, mouseY, delta);

        // Ensure the text field is visible
        this.amountField.render(context, mouseX, mouseY, delta);

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
            case SHOP -> y = drawTextInBox(context, Text.translatable("rosestrades.menu.shop"), boxLeft, boxRight, y, 0xFFFF55);
            case INVENTORY -> y = drawTextInBox(context, Text.translatable("rosestrades.menu.inventory"), boxLeft, boxRight, y, 0x55FF55);
            case MORE -> {
                y = drawTextInBox(context, Text.translatable("rosestrades.menu.more.welcome"), boxLeft, boxRight, y, 0xFFFFFF);
                y = drawTextInBox(context, Text.translatable("rosestrades.menu.more.focus"), boxLeft, boxRight, y, 0xAAAAAA);
                y = drawTextInBox(context, Text.translatable("rosestrades.menu.more.spawn"), boxLeft, boxRight, y, 0xAAAAAA);
                y = drawTextInBox(context, Text.translatable("rosestrades.menu.more.menu_features"), boxLeft, boxRight, y, 0xAAAAAA);
                y = drawTextInBox(context, Text.translatable("rosestrades.menu.more.future"), boxLeft, boxRight, y, 0xAAAAAA);

                y += 10; // Space before the link

                // GitHub link
                Text githubLink = Text.translatable("rosestrades.menu.more.github")
                        .setStyle(Style.EMPTY.withUnderline(true).withColor(Formatting.AQUA)
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Firelack/Roses-Trades")));
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
            case IMPORT -> y = drawTextInBox(context, Text.translatable("rosestrades.menu.import.info"), boxLeft, boxRight, y, 0xAAAAFF);
        }

        // Signature
        Text signature = Text.translatable("rosestrades.menu.signature");
        int sigWidth = this.textRenderer.getWidth(signature);
        context.drawTextWithShadow(this.textRenderer, signature, this.width - sigWidth - 5, this.height - 12, 0x808080);
    }

    // Validate and correct the amount field input
    private void validateAmountField() {
        String text = this.amountField.getText();

        // vide → remettre 1
        if (text.isEmpty()) {
            this.amountField.setText("1");
            return;
        }

        try {
            int value = Integer.parseInt(text);
            int stock = RosesTradesClient.clientRoseCount;
            int max = Math.min(999, stock); // maximum 999 or stock

            if (value < 1) {
                this.amountField.setText("1");
            } else if (value > max) {
                this.amountField.setText(String.valueOf(max));
            }
        } catch (NumberFormatException e) {
            this.amountField.setText("1");
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.amountField.keyPressed(keyCode, scanCode, modifiers)) {
            validateAmountField();
            return true;
        }
        if (this.searchField.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        if (keyCode == 256) { // ESC
            if (this.amountField.isFocused()) {
                if (this.amountField.getText().isEmpty()) this.amountField.setText("1");
                this.amountField.setFocused(false);
                return true;
            }
            this.close();
            return true;
        }

        // Close on custom key
        if (ModKeyBindings.OPEN_MENU.matchesKey(keyCode, scanCode) && !this.amountField.isFocused() && !this.searchField.isFocused()) {
            this.close();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        // amountField : only digits
        if (this.amountField.isFocused() && Character.isDigit(chr)) {
            boolean result = this.amountField.charTyped(chr, modifiers);
            validateAmountField();
            return result;
        }
        // searchField : any character
        if (this.searchField.isFocused()) {
            return this.searchField.charTyped(chr, modifiers);
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Click on amountField
        if (this.amountField.mouseClicked(mouseX, mouseY, button)) {
            this.amountField.setFocused(true);
            return true;
        }

        // Click on searchField
        if (this.searchField.mouseClicked(mouseX, mouseY, button)) {
            this.searchField.setFocused(true);
            return true;
        }

        // If clicking outside the fields, unfocus them
        if (this.amountField.isFocused()) {
            if (this.amountField.getText().isEmpty()) this.amountField.setText("1");
            this.amountField.setFocused(false);
        }
        if (this.searchField.isFocused()) {
            this.searchField.setFocused(false);
        }

        // Verify if the click is on the GitHub link
        if (currentPage == Page.MORE && githubLinkArea != null && githubLinkArea.contains((int) mouseX, (int) mouseY)) {
            Util.getOperatingSystem().open("https://github.com/Firelack/Roses-Trades");
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        if (this.client != null) this.client.setScreen(null);
    }
}