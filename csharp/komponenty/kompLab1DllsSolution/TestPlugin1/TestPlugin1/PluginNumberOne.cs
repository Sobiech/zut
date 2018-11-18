using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Windows.Forms;
using System.Reflection;

namespace TestPlugin1
{
    public class PluginNumberOne
    {
        public static string getPluginName()
        {
            return "PluginNumberOne: PrecyzyjnaNazwaDrugiejFunkcji";
        }

        public static string sPrecyzyjnaNazwaDrugiejFunkcji(string value)
        {
            return "PluginNumberOne: wartość podana do precyzyjnej funkcji to: " + value;
        }

        public static void rPrecyzyjnaNazwaDrugiejFunkcji(RichTextBox richBox)
        {
            richBox.Text = "PluginNumberOne: wartość podana do precyzyjnej funkcji to: " + richBox.Text;
        }

        public static string ePrecyzyjnaNazwaDrugiejFunkcji(string selectedText)
        {
            return "PluginNumberOne: wartość zaznaczona / selected text to : " + selectedText;
        }
    }
}
