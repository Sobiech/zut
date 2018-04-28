using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;

namespace CsLabs02 {
    public partial class MainWindow : Form {

        private Boolean isServerStarted = false;

        private Thread serverThread;

        private HttpServer httpServer;

        public MainWindow() {

            InitializeComponent();
            Console.SetOut(new ControlWriter(textBox4));

            button1.Enabled = !isServerStarted;
            button2.Enabled = isServerStarted;
        }

        private void textBox3_Clicked(object sender , EventArgs e) {
            folderBrowserDialog1.ShowDialog();
            textBox3.Text = folderBrowserDialog1.SelectedPath;
        }

        private void startStopServer_Click(object sender , EventArgs e) {

            if (!isServerStarted) {

                String addr = textBox1.Text;
                int port = Int32.Parse(textBox2.Text);

                httpServer = new HttpServer(addr , port);

                Console.WriteLine("Startuje serwer na: {0}" , new StringBuilder(addr).Append(":").Append(port.ToString()).ToString());

                serverThread = new Thread(httpServer.StartAsyncServer);
                serverThread.Start();

            } else {

                if (httpServer != null) {

                    Console.WriteLine("Zatrzymuje serwer !");

                    //serverThread = new Thread(httpServer.StopAsyncServer);
                    //serverThread.Start();

                    httpServer.StopAsyncServer();
                    serverThread.Abort();

                }
            }

            isServerStarted = !isServerStarted;


            button1.Enabled = !isServerStarted;
            button2.Enabled = isServerStarted;
        }

    }
}
