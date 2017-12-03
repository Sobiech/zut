using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CsLabs03
{
    public partial class MainWindow : Form
    {

        private String choosenDirectoryPath;

        public MainWindow()
        {
            InitializeComponent();
            Console.SetOut(new ConsoleWriter(logger));
            Console.WriteLine("Inicjalizacja aplikacji.");

        }

        private void onChooseFileClick(object sender, EventArgs e) {

            this.folderBrowserDialog1.ShowDialog();
            choosenDirectoryPath = this.folderBrowserDialog1.SelectedPath;
            this.textBox1.Text = choosenDirectoryPath;

            Console.WriteLine("Wybrano sciezke:{0}", choosenDirectoryPath);
        }

        private void button1_Click(object sender, EventArgs e)
        {

            PathProcessor xmlUtils = new PathProcessor(choosenDirectoryPath);

            try
            {
                xmlUtils.read();

            } catch ( Exception e1 )
            {

                Console.WriteLine("Wystapil blad w trakcie proby parsowania pliku!");
                Console.WriteLine("Szczegóły:{0} -> {1}", Environment.NewLine, e1.Message);
            }

        }
    }
}
