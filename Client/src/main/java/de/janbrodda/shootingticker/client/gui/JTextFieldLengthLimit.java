package de.janbrodda.shootingticker.client.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLengthLimit extends PlainDocument {
  private final int limit;

  JTextFieldLengthLimit(int limit) {
   super();
   this.limit = limit;
   }

  @Override
  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    if (str == null) return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
    }
  }
}