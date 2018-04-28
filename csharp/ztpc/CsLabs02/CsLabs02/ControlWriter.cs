using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CsLabs02 {
    public class ControlWriter : TextWriter {

        private Control textbox;

        public ControlWriter(Control textbox) {
            this.textbox = textbox;
        }

        public override void Write(char value) {
            if (textbox.InvokeRequired) {

                try {

                    textbox.Invoke(new MethodInvoker(() => textbox.Text += value));

                } catch (ObjectDisposedException e) {

                }

            } else {
                textbox.Text += value;

            }
        }

        public override void Write(string value) {

            if (textbox.InvokeRequired) {

                try {

                    textbox.Invoke(new MethodInvoker(() => textbox.Text += value));

                } catch (ObjectDisposedException e) {

                }

            } else {
                textbox.Text += value;
            }
        }

        public override Encoding Encoding {
            get {
                return Encoding.ASCII;
            }
        }

    }
}
