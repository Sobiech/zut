using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Reflection;

namespace PluginSystem {
    public partial class PluginSystem : Form {

        private List<PluginManager> pluginManagers;

        public PluginSystem() {
            InitializeComponent();
            LoadPlugins();
        }

        private void LoadPlugins() {
            pluginManagers = new List<PluginManager>();
            String path = @"E:\Projekty\github\zut\csharp\komponenty\dlls";
            foreach (string fileName in Directory.GetFiles(path, "*.dll", SearchOption.AllDirectories)) {
                PluginManager pluginManager = new PluginManager(fileName, this.editorRichTextBox);
                pluginManager.LoadPlugin();
                this.pluginManagers.Add(pluginManager);
                AddPluginToolStrip(pluginManager);
            }
        }

        private void AddPluginToolStrip(PluginManager pluginManager) {
            foreach (MethodInfo method in pluginManager.methods) {
                string optionName = pluginManager.classAssembly.Name + "." + method.Name;
                wtyczkiToolStripMenuItem.DropDownItems.Add(
                    new ToolStripMenuItem(optionName, null, pluginManager.MenuHandlerToolTipHandler, method.Name)
                );
            }
        }

        private void zapiszToolStripMenuItem_Click(object sender, EventArgs e) {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.DefaultExt = "*.rtf";
            saveFileDialog.Filter = "RTF Files|*.rtf";

            if (saveFileDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK && saveFileDialog.FileName.Length > 0) {
                editorRichTextBox.SaveFile(saveFileDialog.FileName);
            }
        }

        private void wczytajToolStripMenuItem_Click(object sender, EventArgs e) {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.DefaultExt = "*.rtf";
            openFileDialog.Filter = "RTF Files|*.rtf";
            if (openFileDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK && openFileDialog.FileName.Length > 0) {
                editorRichTextBox.LoadFile(openFileDialog.FileName);
            }
        }

    }
}
