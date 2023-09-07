package net.gsimken.bgameslibrary.client.utils;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class PasswordField extends EditBox {
    private String realText = "";

    public PasswordField(Font p_94114_, int p_94115_, int p_94116_, int p_94117_, int p_94118_, Component p_94119_) {
        super(p_94114_, p_94115_, p_94116_, p_94117_, p_94118_, p_94119_);
    }

    public PasswordField(Font p_94106_, int p_94107_, int p_94108_, int p_94109_, int p_94110_, @Nullable EditBox p_94111_, Component p_94112_) {
        super(p_94106_, p_94107_, p_94108_, p_94109_, p_94110_, p_94111_, p_94112_);
    }
    public String getRealText() {
        return realText;
    }
    public void setRealPassword(String text) {
        this.realText = text;
        super.setValue("*".repeat(text.length()));
    }
    @Override
    public void insertText(String text) {
        realText += text;
        super.insertText("*".repeat(text.length()));

    }
}
