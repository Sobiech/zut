using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing;

namespace PluginTextUnderline
{
    public class PluginFormatTextUnderline {

        public static void rFormatTextToUnderline(RichTextBox richTextBox) {

            if (richTextBox.SelectionFont != null) {
                Font currentFont = richTextBox.SelectionFont;
                FontStyle newFontStyle = FontStyle.Regular;

                bool isItalic = richTextBox.SelectionFont.Italic;
                bool isUnderLine = richTextBox.SelectionFont.Underline;
                bool isBold = richTextBox.SelectionFont.Bold;

                if (isBold)
                    newFontStyle = ( newFontStyle != FontStyle.Regular ) ? newFontStyle | FontStyle.Bold : FontStyle.Bold;

                if (!isUnderLine)
                    newFontStyle = ( newFontStyle != FontStyle.Regular ) ? newFontStyle | FontStyle.Underline : FontStyle.Underline;

                if (isItalic)
                    newFontStyle = ( newFontStyle != FontStyle.Regular ) ? newFontStyle | FontStyle.Italic : FontStyle.Italic;

                richTextBox.SelectionFont = new Font(
                   currentFont.FontFamily,
                   currentFont.Size,
                   newFontStyle
                );
            }
        }

    }
}
