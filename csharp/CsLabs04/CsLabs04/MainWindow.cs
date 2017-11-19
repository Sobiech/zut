using System;
using System.Drawing;
using System.Windows.Forms;

namespace CsLabs04
{
    public partial class MainWindow : Form
    {

        private Boolean draw;

        private Bitmap drawing;

        private float pX;
        private float pY;

        private Pen pen;

        private int penSize;
        private Color selectedColor;

        public MainWindow()
        {

            InitializeComponent();
            Console.SetOut(new ConsoleWriter(logger));
            Console.WriteLine("Inicjalizacja aplikacji");
            penSize = 4;
            selectedColor = Color.Black;

            initializePen();

            initializeGraphics();

        }

        private void initializeGraphics()
        {

            drawing = new Bitmap(panel1.Width - 5, panel1.Height - 5, panel1.CreateGraphics());
            Graphics.FromImage(drawing).Clear(Color.White);
        }

        private void panel1_MouseMove(object sender, MouseEventArgs e)
        {
            if (draw)
            {
                Graphics panel = Graphics.FromImage(drawing);

                panel.DrawLine(pen, pX, pY, e.X, e.Y);

                panel1.CreateGraphics().DrawImageUnscaled(drawing, new Point(0, 0));
            }

            pX = e.X;
            pY = e.Y;
        }

        private void panel1_MouseDown(object sender, MouseEventArgs e)
        {
            draw = true;

            pX = e.X;
            pY = e.Y;
        }

        private void panel1_MouseUp(object sender, MouseEventArgs e)
        {
            draw = false;
        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawImageUnscaled(drawing, new Point(0, 0));
        }

        private void colorBtn_Click(object sender, EventArgs e)
        {
            colorDialog1.ShowDialog();

            selectedColor = colorDialog1.Color;
            colorBtn.BackColor = selectedColor;
            initializePen();
        }

        private void fontSizeIndex_Changed(object sender, EventArgs e)
        {
            this.penSize = this.fontSizeCb.SelectedIndex + 1;
            initializePen();
        }

        private void clearPanelBtn_Click(object sender, EventArgs e)
        {
            Console.WriteLine("Czyszcze panel !");
            panel1.Invalidate();
            initializeGraphics();

        }



        private void initializePen() 
        {
            Console.WriteLine("Zmiana ustawien pędzla: kolor: {0} ,  wielkosc: {1}", selectedColor.Name, penSize.ToString());
            pen = new Pen(selectedColor, penSize);

        }
    }

}
