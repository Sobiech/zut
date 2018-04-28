using System;
using System.Drawing;

namespace CsLabs05.Draw {

    public abstract class AbstractDrawingMode : IDrawable {

        public AbstractDrawingMode(String mode) {
            Console.WriteLine("Aktywowano tryb {0} " , mode);
        }

        protected Graphics GetGraphics(Bitmap bitmap) {
            return Graphics.FromImage(bitmap);
        }

        public abstract void draw(Bitmap bitmap , Pen pen , Point p1 , Point p2);

        public virtual Boolean isNormalMode() {
            return false;
        }
    }

    class RectangleMode : AbstractDrawingMode {

        public RectangleMode() : base("Prostokat") { }

        public override void draw(Bitmap bitmap , Pen pen , Point p1 , Point p2) {

            GetGraphics(bitmap).DrawRectangle(pen , new Rectangle(p1.X , p1.Y , p2.X - p1.X , p2.Y - p1.Y));
        }

    }

    class LineMode : AbstractDrawingMode {

        public LineMode() : base("Linia") { }

        public override void draw(Bitmap bitmap , Pen pen , Point p1 , Point p2) {
            GetGraphics(bitmap).DrawLine(pen , p1.X , p1.Y , p2.X , p2.Y);
        }

    }

    class ElipseMode : AbstractDrawingMode {

        public ElipseMode() : base("Elipsa") { }

        public override void draw(Bitmap bitmap , Pen pen , Point p1 , Point p2) {
            GetGraphics(bitmap).DrawEllipse(pen , p1.X , p1.Y , p2.X , p2.Y);
        }

    }

    class NormalMode : AbstractDrawingMode {

        public NormalMode() : base("Normalny") { }

        public override void draw(Bitmap bitmap , Pen pen , Point p1 , Point p2) {
            GetGraphics(bitmap).DrawLine(pen , p1.X , p1.Y , p2.X , p2.Y);
        }

        public override Boolean isNormalMode() {
            return true;
        }
    }

}
