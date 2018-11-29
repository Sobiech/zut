using System;
using System.Drawing;
using System.Windows.Forms;

namespace lab4 {

    public partial class Form1 : Form {

        private string fileName;

        public Form1() {
            InitializeComponent();
        }

        private void btnLoadFile_Click(object sender, EventArgs e) {

            using (OpenFileDialog dlg = new OpenFileDialog()) {
                dlg.Title = "Open Image";
                dlg.Filter = "bmp files (*.bmp)|*.bmp";
                if (dlg.ShowDialog() == DialogResult.OK) {
                    this.fileName = dlg.FileName;
                    pbToAnalyse.Image = new Bitmap(dlg.FileName);
                }
            }
        }

        private void btnAnalyse_Click(object sender, EventArgs e) {

            if (pbToAnalyse.Image is null) {
                MessageBox.Show("There is no image loaded to analyse");
                return;
            }

            JPEGAnalyzer analyzer = new JPEGAnalyzer(fileName);
            analyzer.SaveToJpegStream(100);
            analyzer.SaveFromStreamToBitmap();
            this.pbAnalysed.Image = analyzer.GetBitmapMarked();

        }
    }
}
