/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2020 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.text;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Set;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextAssertions {
  private static final Set<TextDecoration> DECORATIONS = ImmutableSet.copyOf(TextDecoration.values());

  public static void assertDecorations(final Component component, final Set<TextDecoration> trues, final Set<TextDecoration> falses) {
    assertDecorations(component.style(), trues, falses);
  }

  public static void assertDecorations(final Style style, final Set<TextDecoration> trues, final Set<TextDecoration> falses) {
    assertDecorationsAre(style, trues, TextDecoration.State.TRUE);
    assertDecorationsAre(style, falses, TextDecoration.State.FALSE);
    final Set<TextDecoration> unset = Sets.difference(DECORATIONS, Sets.union(trues, falses));
    assertDecorationsAre(style, unset, TextDecoration.State.NOT_SET);
  }

  private static void assertDecorationsAre(final Style style, final Set<TextDecoration> decorations, final TextDecoration.State state) {
    if(!decorations.isEmpty()) {
      for(final TextDecoration decoration : decorations) {
        assertEquals(state, style.decoration(decoration));
      }
    }
  }
}
