using CsLabs05.Draw;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace CsLabs05 {

    public partial class MainWindow : Form {

        private Dictionary<int , Bitmap> drawableBitmaps;

        private Boolean drawOnSurface;

        private Pen pen;

        private Color selectedColor;

        private int penSize;

        private Point p1Location;

        private Point p2Location;



        public MainWindow() {

            InitializeComponent();
            AssingControlEvents();
            AssingDrawingEvents();
            InitializeEnvir();
        }

        private void InitializeEnvir() {

            Console.SetOut(new ConsoleWriter(logger));

            Console.WriteLine("Inicjalizacja aplikacji");
            drawableBitmaps = new Dictionary<int , Bitmap>();

            selectedColor = Color.Black;
            penSize = 4;

            DrawableMode = new NormalMode();
            
            InitializePen();
            InitializeGraphics();
        }

        private void InitializePen() {

            Console.WriteLine("Zmiana ustawien pędzla: kolor: {0} ,  wielkosc: {1}" , selectedColor.Name , penSize.ToString());
            pen = new Pen(selectedColor , penSize);
        }

 
        private void InitializeGraphics() {

            DrawableBitmap = new Bitmap(DrawablePanel.Width - 5, DrawablePanel.Height - 5, DrawablePanel.CreateGraphics());
            Graphics.FromImage(DrawableBitmap).Clear(Color.White);
        }


        private void DrawFigure() {

            DrawableMode.draw(DrawableBitmap , pen , p1Location , p2Location);
            DrawablePanel.CreateGraphics().DrawImageUnscaled(DrawableBitmap , new Point(0 , 0));
        }


        private void AssingDrawingEvents() {

            DrawablePanel.MouseDown += new MouseEventHandler((ob , e) => {

                p1Location = e.Location;

                if (DrawableMode.isNormalMode()) {
                    drawOnSurface = true;
                }
            });

            DrawablePanel.MouseUp += new MouseEventHandler((ob , e) => {

                drawOnSurface = false;

                if (!DrawableMode.isNormalMode()) {
                    p2Location = e.Location;
                    DrawFigure();
                }
            });

            DrawablePanel.MouseMove += new MouseEventHandler((ob , e) => {

                if (drawOnSurface) {

                    if (DrawableMode.isNormalMode()) {
                        p2Location = e.Location;
                    }

                    DrawFigure();

                    p1Location = e.Location;
                }
            });

            DrawablePanel.Paint += new PaintEventHandler((ob , e) => {
                e.Graphics.DrawImageUnscaled(DrawableBitmap , new Point(0 , 0));
            });
        }

        private void AssingControlEvents() {

            // zmiana 'DrawingMode'
            drawNormalBtn.Click += new EventHandler((ob, e) => this.DrawableMode = new NormalMode());
            drawLineBtn.Click   += new EventHandler((ob, e) => this.DrawableMode = new LineMode());
            drawRectBtn.Click   += new EventHandler((ob, e) => this.DrawableMode = new RectangleMode());
            drawElipseBtn.Click += new EventHandler((ob, e) => this.DrawableMode = new ElipseMode());

            // czyszczenie panelu
            clearPanelBtn.Click += new EventHandler((ob, e) => {

                Console.WriteLine("Czyszcze panel !");
                DrawablePanel.Invalidate();
                InitializeGraphics();
            });

            // zmiana wielkosci czcionki
            fontSizeCb.SelectedIndexChanged += new EventHandler((ob, e) => {

                this.penSize = this.fontSizeCb.SelectedIndex + 1;
                InitializePen();
            });

            // zmiana koloru pedzla
            colorBtn.Click += new EventHandler((ob , e) => {

                colorDialog1.ShowDialog();
                selectedColor = colorDialog1.Color;
                colorBtn.BackColor = selectedColor;
                InitializePen();
            });

            // zmiana indeksu tabControl
            tabControl1.SelectedIndexChanged += new EventHandler((ob , e) => {

                if (!drawableBitmaps.ContainsKey(tabControl1.SelectedIndex)) {
                    AssingDrawingEvents();
                    InitializeGraphics();
                }
            });

        }


        private IDrawable drawableMode;
        internal IDrawable DrawableMode {
            get => drawableMode;
            set => drawableMode = value;
        }


        private Bitmap DrawableBitmap {
            get => drawableBitmaps [ SelectedTabIndex ];
            set => drawableBitmaps [ SelectedTabIndex ] = value;
        }


        private TabPage DrawablePanel {
            get => tabControl1.SelectedTab;
        }


        private int SelectedTabIndex {
            get => tabControl1.SelectedIndex;
        }
    }

}
