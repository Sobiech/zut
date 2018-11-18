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

namespace kompLab1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            LoadPlugins();
        }

        private void zapiszToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.DefaultExt = "*.rtf";
            saveFileDialog.Filter = "RTF Files|*.rtf";

            if (saveFileDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK && saveFileDialog.FileName.Length > 0)
            {
                editorRichTextBox.SaveFile(saveFileDialog.FileName);
            }
        }

        private void wczytajToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.DefaultExt = "*.rtf";
            openFileDialog.Filter = "RTF Files|*.rtf";
            if (openFileDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK && openFileDialog.FileName.Length > 0)
            {
                editorRichTextBox.LoadFile(openFileDialog.FileName);
            }
        }

        void LoadPlugins()
        {
            String path = @"E:\Projekty\github\zut\csharp\komponenty\dlls";
            foreach (string fileName in Directory.GetFiles(path, "*.dll", SearchOption.AllDirectories))
            {
                Assembly plugin = Assembly.LoadFrom(fileName);
                foreach (Type item in plugin.GetTypes()) { 
                    //lista klas
                    foreach (MethodInfo method in item.GetMethods())
                    {   
                        wtyczkiToolStripMenuItem.DropDownItems.Add(
                            new ToolStripMenuItem(method.Name, null, MenuHandler, fileName + "|" + item.Name + "|" + method.Name)
                        );
                    }
                }
            }
        }

        private void addOptionBtn_Click(object sender, EventArgs e)
        {
            String fieldName = optionNameField.Text;
            if (!String.IsNullOrEmpty(fieldName) && !wtyczkiToolStripMenuItem.DropDownItems.ContainsKey(fieldName))
            {
                wtyczkiToolStripMenuItem.DropDownItems.Add(new ToolStripMenuItem("Opis", null, MenuHandler, fieldName));
            }
        }

        private void MenuHandler(object sender, EventArgs e)
        {
            ToolStripMenuItem item = (ToolStripMenuItem)sender;

            string[] names = item.Name.Split(new char[] { '|' });
            string NazwaAssembly = names[0];
            string NazwaKlasy = names[1];
            string NazwaMetody = names[2];

            Assembly plugin = Assembly.LoadFrom(NazwaAssembly);
            Type classAssembly = null;
            foreach (Type type in plugin.GetTypes())
            {
                if ( type.Name.Equals(NazwaKlasy))
                {
                    classAssembly = type;
                    break;
                }
            }

            if ( classAssembly == null )
            {
                return;
            }

            MethodInfo method = classAssembly.GetMethod(NazwaMetody);
            string methodPrefix = method.Name.Substring(0, 1);

            if (methodPrefix.Equals("r"))
            {
                method.Invoke(null, new object[] { editorRichTextBox });
            } else if ( methodPrefix.Equals("e"))
            {
                MessageBox.Show("Got result:" + method.Invoke(null, new object[] { editorRichTextBox.SelectedText }));
            } else
            {
                MessageBox.Show("Got result:" + method.Invoke(null, new object[] { editorRichTextBox.Text }));
            }

        }
    }
}
