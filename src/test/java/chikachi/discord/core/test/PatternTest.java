/*
 * Copyright (C) 2017 Chikachi
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 */
package chikachi.discord.core.test;

import chikachi.discord.core.CoreUtils;
import chikachi.discord.core.Patterns;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PatternTest {
    @Before
    public void init() {
        CoreUtils.addPatterns();
    }

    @Test
    public void discordToMinecraft() {
        Assert.assertEquals("Bold", "\u00a7lBold\u00a7r", Patterns.discordToMinecraft("**Bold**"));
        Assert.assertEquals("BoldItalic", "\u00a7lBold \u00a7oItalic\u00a7r", Patterns.discordToMinecraft("**Bold *Italic***"));
        Assert.assertEquals("BoldItalicUnderline", "\u00a7lBold \u00a7oItalic \u00a7nUnderline\u00a7r", Patterns.discordToMinecraft("**Bold *Italic __Underline__***"));
        Assert.assertEquals("BoldItalicUnderline2", "\u00a7lBold \u00a7oItalic\u00a7r\u00a7l \u00a7nUnderline\u00a7r", Patterns.discordToMinecraft("**Bold *Italic* __Underline__**"));
        Assert.assertEquals("Strikethrough", "\u00a7mStrikethrough\u00a7r", Patterns.discordToMinecraft("~~Strikethrough~~"));
        Assert.assertEquals("Underline", "\u00a7nUnderline\u00a7r", Patterns.discordToMinecraft("__Underline__"));
        Assert.assertEquals("Italic", "\u00a7oItalic\u00a7r", Patterns.discordToMinecraft("*Italic*"));
        Assert.assertEquals("Italic /me", "\u00a7oItalic\u00a7r", Patterns.discordToMinecraft("_Italic_"));
        Assert.assertEquals("Reset", "\u00a7lBold\u00a7rNormal", Patterns.discordToMinecraft("**Bold**Normal"));
        Assert.assertEquals("\u00a7oIta _lic\u00a7r", Patterns.discordToMinecraft("*Ita _lic*"));
    }

    @Test
    public void minecraftToDiscord() {
        Assert.assertEquals("Color", "Color", Patterns.minecraftToDiscord("\u00a70\u00a71\u00a72\u00a73\u00a74\u00a75\u00a76\u00a77\u00a78\u00a79\u00a7a\u00a7b\u00a7c\u00a7d\u00a7e\u00a7fColor"));
        Assert.assertEquals("Obfuscated", "Obfuscated", Patterns.minecraftToDiscord("\u00a7kObfuscated"));
        Assert.assertEquals("Bold", "**Bold**", Patterns.minecraftToDiscord("\u00a7lBold"));
        Assert.assertEquals("BoldItalic", "**Bold *Italic***", Patterns.minecraftToDiscord("\u00a7lBold \u00a7oItalic"));
        Assert.assertEquals("BoldItalicUnderline", "**Bold *Italic __Underline__***", Patterns.minecraftToDiscord("\u00a7lBold \u00a7oItalic \u00a7nUnderline"));
        Assert.assertEquals("BoldItalicUnderline2", "**Bold *Italic* __Underline__**", Patterns.minecraftToDiscord("\u00a7lBold \u00a7oItalic\u00a7r\u00a7l \u00a7nUnderline"));
        Assert.assertEquals("Strikethrough", "~~Strikethrough~~", Patterns.minecraftToDiscord("\u00a7mStrikethrough"));
        Assert.assertEquals("Underline", "__Underline__", Patterns.minecraftToDiscord("\u00a7nUnderline"));
        Assert.assertEquals("Italic", "*Italic*", Patterns.minecraftToDiscord("\u00a7oItalic"));
        Assert.assertEquals("Reset", "**Bold**Normal", Patterns.minecraftToDiscord("\u00a7lBold\u00a7rNormal"));
    }

    @Test
    public void minecraftFormattingConvert() {
        Assert.assertEquals("Color", "\u00a70\u00a71\u00a72\u00a73\u00a74\u00a75\u00a76\u00a77\u00a78\u00a79\u00a7a\u00a7b\u00a7c\u00a7d\u00a7e\u00a7fColor", Patterns.unifyMinecraftFormatting("&0&1&2&3&4&5&6&7&8&9&a&b&c&d&e&fColor"));
        Assert.assertEquals("Obfuscated", "\u00a7kObfuscated", Patterns.unifyMinecraftFormatting("&kObfuscated"));
        Assert.assertEquals("Bold", "\u00a7lBold", Patterns.unifyMinecraftFormatting("&lBold"));
        Assert.assertEquals("BoldItalic", "\u00a7lBold \u00a7oItalic", Patterns.unifyMinecraftFormatting("&lBold &oItalic"));
        Assert.assertEquals("BoldItalicUnderline", "\u00a7lBold \u00a7oItalic \u00a7nUnderline", Patterns.unifyMinecraftFormatting("&lBold &oItalic &nUnderline"));
        Assert.assertEquals("BoldItalicUnderline2", "\u00a7lBold \u00a7oItalic\u00a7r\u00a7l \u00a7nUnderline", Patterns.unifyMinecraftFormatting("&lBold &oItalic&r&l &nUnderline"));
        Assert.assertEquals("Strikethrough", "\u00a7mStrikethrough", Patterns.unifyMinecraftFormatting("&mStrikethrough"));
        Assert.assertEquals("Underline", "\u00a7nUnderline", Patterns.unifyMinecraftFormatting("&nUnderline"));
        Assert.assertEquals("Italic", "\u00a7oItalic", Patterns.unifyMinecraftFormatting("&oItalic"));
        Assert.assertEquals("Reset", "\u00a7lBold\u00a7rNormal", Patterns.unifyMinecraftFormatting("&lBold&rNormal"));
    }

    @Test
    public void checkNoConvert() {
        Assert.assertEquals("Bold", "Test**Bold", Patterns.discordToMinecraft("Test**Bold"));
        Assert.assertEquals("Strikethrough", "Test~~Strikethrough", Patterns.discordToMinecraft("Test~~Strikethrough"));
        Assert.assertEquals("Underline", "Test__Underline", Patterns.discordToMinecraft("Test__Underline"));
        Assert.assertEquals("Italic", "Test_Italic", Patterns.discordToMinecraft("Test_Italic"));
        Assert.assertEquals("Italic2", "Test*Italic", Patterns.discordToMinecraft("Test*Italic"));

        Assert.assertEquals("User mention", "<@1234567890>", Patterns.minecraftToDiscord("<@1234567890>"));
        Assert.assertEquals("Role mention", "<@&1234567890>", Patterns.minecraftToDiscord("<@&1234567890>"));
    }
}
