using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CsLabs04
{
    public class ConsoleWriter : TextWriter
    {

        private Control textbox;

        public ConsoleWriter(Control textbox)
        {
            this.textbox = textbox;

        }

        public override void Write(string value)
        {

            textbox.Text += value;
            ((TextBox)textbox).SelectionStart = textbox.Text.Length;
            ((TextBox)textbox).ScrollToCaret();
        }

        public override void WriteLine(string value)
        {

            textbox.Text += value + Environment.NewLine;
            ((TextBox)textbox).SelectionStart = textbox.Text.Length;
            ((TextBox)textbox).ScrollToCaret();
        }

        public override Encoding Encoding
        {
            get
            {
                return Encoding.ASCII;
            }
        }

    }
}

