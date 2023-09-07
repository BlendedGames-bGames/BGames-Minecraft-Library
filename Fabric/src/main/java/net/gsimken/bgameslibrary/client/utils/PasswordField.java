package net.gsimken.bgameslibrary.client.utils;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class PasswordField extends TextFieldWidget {
    private String realText = "";
    public PasswordField(TextRenderer textRenderer, int x, int y, int width, int height, Text text) {
        super(textRenderer, x, y, width, height, text);
    }

    public PasswordField(TextRenderer textRenderer, int x, int y, int width, int height, @Nullable TextFieldWidget copyFrom, Text text) {
        super(textRenderer, x, y, width, height, copyFrom, text);
    }

    public String getRealText() {
        return realText;
    }
    public void setRealPassword(String text) {
        this.realText = text;
        super.setText("*".repeat(text.length()));
    }
    @Override
    public void write(String text) {
        realText += text;
        super.write("*".repeat(text.length()));

    }

}
