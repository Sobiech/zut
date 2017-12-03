using System;
using System.Drawing;
using System.Windows.Forms;
using System.Collections.Generic;

namespace CsLabs04
{
    public partial class MainWindow : Form
    {

        private Boolean draw;

        private Dictionary<Int32, Bitmap> bitmapDict;

        private float pX;
        private float pY;

        private Pen pen;

        private int penSize;
        private Color selectedColor;

        public MainWindow()
        {

            bitmapDict = new Dictionary<Int32, Bitmap>();

            InitializeComponent();
            Console.SetOut(new ConsoleWriter(logger));
            Console.WriteLine("Inicjalizacja aplikacji");
            penSize = 4;
            selectedColor = Color.Black;

            initializePen();
            initializeTabEvents();
            initializeGraphics();
        }

        private void initializeTabEvents() {

            foreach(TabPage tabPage in tabControl1.TabPages ) {

               
            }

        }

        private void initializeTabEvent()
        {
            SelectedPanel.MouseDown += new MouseEventHandler(EventMouseDown);
            SelectedPanel.MouseUp   += new MouseEventHandler(EventMouseUp);
            SelectedPanel.MouseMove += new MouseEventHandler(EventMouseMove);

            SelectedPanel.Paint     += new PaintEventHandler(EventPaint);
        }

        private void initializeGraphics()
        {

            Bitmap bitmap = new Bitmap(SelectedPanel.Width - 5, SelectedPanel.Height - 5, SelectedPanel.CreateGraphics());
            Graphics.FromImage(bitmap).Clear(Color.White);
            bitmapDict.Add(tabControl1.SelectedIndex, bitmap);
        }

        private void EventMouseMove(object sender, MouseEventArgs e)
        {

            if (draw)
            {
                Graphics panel = Graphics.FromImage(SelectedBitmap);

                panel.DrawLine(pen, pX, pY, e.X, e.Y);

                SelectedPanel.CreateGraphics().DrawImageUnscaled(SelectedBitmap, new Point(0, 0));
            }

            pX = e.X;
            pY = e.Y;
        }

        private void EventMouseDown(object sender, MouseEventArgs e)
        {
            draw = true;
            pX = e.X;
            pY = e.Y;
          
        }

        private void EventMouseUp(object sender, MouseEventArgs e)
        {
            draw = false;
        }

        protected void EventPaint(object sender, PaintEventArgs e)
        {
           e.Graphics.DrawImageUnscaled(SelectedBitmap, new Point(0, 0));
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

            SelectedPanel.Invalidate();
            initializeGraphics();
        }

        private void initializePen() 
        {
            Console.WriteLine("Zmiana ustawien pędzla: kolor: {0} ,  wielkosc: {1}", selectedColor.Name, penSize.ToString());
            pen = new Pen(selectedColor, penSize);

        }


        public TabPage SelectedPanel {
            get {
                return this.tabControl1.SelectedTab;         
            }
            private set { }
        }

        public Bitmap SelectedBitmap
        {
            get
            {
                int pageIndex = this.tabControl1.SelectedIndex;

                if ( !this.bitmapDict.ContainsKey(pageIndex) ) 
                {
                    initializeGraphics();
                }

                return this.bitmapDict[pageIndex];
            }
        }

        private void changedTabPage(object sender, EventArgs e)
        {
            initializeTabEvent();
            Console.WriteLine("Tab has been changed on : {0}", SelectedPanel.Name);
        }
    }

}
