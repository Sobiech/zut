﻿using System.Drawing;

namespace CsLabs04.Draw {

    interface IDrawable {

        void draw(Bitmap bitmap, Pen pen, Point p1, Point p2);

        bool isNormalMode();
    }
}
