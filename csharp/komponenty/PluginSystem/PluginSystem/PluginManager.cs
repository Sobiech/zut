using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Windows.Forms;
using System.Reflection;

namespace PluginSystem {
    class PluginManager {

        private static string PLUGIN_PREFIX_CLASS_NAME = "Plugin";

        private RichTextBox richTextBox;
        private Assembly plugin;

        public String fileName {
            get; private set;
        }
        public Type classAssembly {
            get; private set;
        }
        public List<MethodInfo> methods {
            get; private set;
        }

        public PluginManager(string fileName, RichTextBox richTextBox) {
            this.fileName = fileName;
            this.richTextBox = richTextBox;
            this.methods = new List<MethodInfo>();
        }

        public void LoadPlugin() {
            this.plugin = Assembly.LoadFrom(this.fileName);
            if (plugin is null) {
                throw new ArgumentException("Could not load plugin by name: " + fileName);
            }
            this.classAssembly = getPluginAssemblyClass(plugin);
            LoadMethods(classAssembly);
        }

        public void MenuHandlerToolTipHandler(object sender, EventArgs e) {
            ToolStripMenuItem item = ( ToolStripMenuItem ) sender;
            string NazwaMetody = item.Name;

            MethodInfo method = classAssembly.GetMethod(NazwaMetody);
            string methodPrefix = method.Name.Substring(0, 1);
            this.invokeMethodByPrefix(method, methodPrefix);
        }

        private Type getPluginAssemblyClass(Assembly plugin) {

            Type classAssembly = plugin.GetTypes().Where(type => type.Name.Contains(PLUGIN_PREFIX_CLASS_NAME)).First();
            if (classAssembly is null)
                throw new ArgumentException("Could not find class with prefix like: " + PLUGIN_PREFIX_CLASS_NAME);

            return classAssembly;
        }

        private void LoadMethods(Type assembledClass) {
            this.methods = assembledClass.GetMethods().Where(method => !method.Name.Contains("ToString") &&
                   !method.Name.Contains("Equals") && !method.Name.Contains("HashCode") && !method.Name.Contains("GetType")).ToList();
        }


        private void invokeMethodByPrefix(MethodInfo method, String methodPrefix) {

            if (methodPrefix.Equals("r")) {
                method.Invoke(null, new object[] { richTextBox });
            } else if (methodPrefix.Equals("e")) {
                MessageBox.Show("Got result:" + method.Invoke(null, new object[] { richTextBox.SelectedText }));
            } else if (methodPrefix.Equals("s")) {
                MessageBox.Show("Got result:" + method.Invoke(null, new object[] { richTextBox.Text }));
            } else {
                MessageBox.Show("Got result:" + method.Invoke(null, new object[] { }));
            }
        }

    }
}
