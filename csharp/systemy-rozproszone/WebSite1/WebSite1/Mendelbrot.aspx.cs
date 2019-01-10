using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Mendelbrot : Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void GenerateMendelbrot(object sender, EventArgs e) {

        try {

            MendelbroteGenerator mendelbrotUtils = new MendelbroteGenerator();

            double zxCritical = Double.Parse(xCritical.Text);
            double zyCritical = Double.Parse(yCritical.Text);

            double fractalRadius = Double.Parse(radius.Text);

            int algorithmIterationsCount = Int32.Parse(iterationCount.Text);

            Stopwatch sw = new Stopwatch();
            sw.Start();
            Bitmap bitmap = mendelbrotUtils.GenerateBitmapMendelbrot(zxCritical, zyCritical, fractalRadius, algorithmIterationsCount);
            sw.Stop();
            StatusLabel.Text = "Fraktal został wygenerowany poprawnie. Zajęło: " + sw.Elapsed.TotalMilliseconds + " [ms]";

            MemoryStream ms = new MemoryStream();
            bitmap.Save(ms, ImageFormat.Bmp);
            var base64Data = Convert.ToBase64String(ms.ToArray());
            imgCtrl.Src = "data:image/bmp;base64," + base64Data;
        } catch (Exception ex) {

            System.Diagnostics.Debug.WriteLine(ex.StackTrace.ToString());
            StatusLabel.Text = "Status: " + ex.Message;
        }

    }
}