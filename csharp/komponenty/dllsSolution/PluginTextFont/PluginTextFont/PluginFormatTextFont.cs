using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Windows.Forms;

namespace PluginTextFont
{
    public class PluginFormatTextFont {

        public static void rFormatTextFont(RichTextBox richTextBox) {

            if (richTextBox.SelectionFont != null) {
                FontDialog fontDialog = new FontDialog();
                DialogResult result = fontDialog.ShowDialog();
                Font currentFont = richTextBox.SelectionFont;

                if (result == DialogResult.OK) {
                    richTextBox.SelectionFont = fontDialog.Font;
                }
            }
        }
    }
}
