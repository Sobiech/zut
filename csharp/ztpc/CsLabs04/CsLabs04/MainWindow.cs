using CsLabs04.Draw;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace CsLabs04 {

    public partial class MainWindow : Form {

        private Bitmap drawableBitmap;

        private Boolean draw;

        private Pen pen;
        private Color selectedColor;
        private int penSize;

        private IDrawable drawableMode;

        private int clickedTimes;

        Point p1;

        Point p2;

        public MainWindow() {

            InitializeComponent();

            Console.SetOut(new ConsoleWriter(logger));
            Console.WriteLine("Inicjalizacja aplikacji");

            selectedColor = Color.Black;
            penSize       = 4;

            drawableMode = new NormalMode();
            initializePen();
            initializeGraphics();
        }

        private void initializeGraphics() {

            drawableBitmap = new Bitmap(drawablePanel.Width - 5, drawablePanel.Height - 5, drawablePanel.CreateGraphics());
            Graphics.FromImage(drawableBitmap).Clear(Color.White);
        }

        private void EventMouseMove(object sender, MouseEventArgs e) {

            if (draw) {

                if (drawableMode.isNormalMode()) {
                    p2 = e.Location;
                }

                drawFigure();

                p1 = e.Location;
            }

        }

        private void EventMouseDown(object sender, MouseEventArgs e) {

            p1 = e.Location;

            if (drawableMode.isNormalMode()) {
                draw = true;
            }

        }

        private void EventMouseUp(object sender, MouseEventArgs e) {

            draw = false;

            if (!drawableMode.isNormalMode()) {
                p2 = e.Location;
                drawFigure();
            }
        }

        private void panelPaintEvent(object sender, PaintEventArgs e) {

            e.Graphics.DrawImageUnscaled(drawableBitmap, new Point(0, 0));
        }

        private void changePenColor(object sender, EventArgs e) {

            colorDialog1.ShowDialog();
            selectedColor = colorDialog1.Color;
            colorBtn.BackColor = selectedColor;
            initializePen();
        }

        private void changeFontSize(object sender, EventArgs e) {

            this.penSize = this.fontSizeCb.SelectedIndex + 1;
            initializePen();
        }

        private void clearPanelEvent(object sender, EventArgs e) {

            Console.WriteLine("Czyszcze panel !");
            drawablePanel.Invalidate();
            initializeGraphics();
        }

        private void initializePen() {

            Console.WriteLine("Zmiana ustawien pędzla: kolor: {0} ,  wielkosc: {1}", selectedColor.Name, penSize.ToString());
            pen = new Pen(selectedColor, penSize);
        }

        private void activateNormalMode(object sender , EventArgs e) {
            this.drawableMode = new NormalMode();
        }

        private void activateLineMode(object sender , EventArgs e) {
            this.drawableMode = new LineMode();
        }

        private void activateRectangleMode(object sender , EventArgs e) {
            this.drawableMode = new RectangleMode();
        }

        private void activateElipseMode(object sender , EventArgs e) {
            this.drawableMode = new ElipseMode();
        }

        private void drawFigure() {

            drawableMode.draw(drawableBitmap , pen , p1 , p2);
            drawablePanel.CreateGraphics().DrawImageUnscaled(drawableBitmap , new Point(0 , 0));
        }

    }

}
