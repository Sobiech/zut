using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;

namespace lab4 {

    class JPEGAnalyzer {

        private Bitmap mOriginalBitmap;
        private Bitmap mFinalBitmap;
        private MemoryStream mMemoryStream;

        private List<Point> ChangedPixels {
            get; set;
        }

        public JPEGAnalyzer(String pFileName) {
            mOriginalBitmap = new Bitmap(pFileName);
        }

        public void SaveToJpegStream(int pQuality) {

            var encoderParameters = new EncoderParameters(1);
            encoderParameters.Param[0] = new EncoderParameter(Encoder.Quality, pQuality);

            MemoryStream ms = new MemoryStream();
            mOriginalBitmap.Save(ms, GetEncoderInfo("image/jpeg"), encoderParameters);
            mMemoryStream = ms;
        }

        public void SaveFromStreamToBitmap() {
            mFinalBitmap = new Bitmap(mMemoryStream);
            CheckChangedPixels();
        }

        public static ImageCodecInfo GetEncoderInfo(String mimeType) {
            int j;
            ImageCodecInfo[] encoders;
            encoders = ImageCodecInfo.GetImageEncoders();
            for (j = 0; j < encoders.Length; ++j) {
                if (encoders[j].MimeType == mimeType)
                    return encoders[j];
            }
            return null;
        }

        private void CheckChangedPixels() {
            ChangedPixels = new List<Point>();

            for (int x = 0; x < mOriginalBitmap.Width; x++) {
                for (int y = 0; y < mOriginalBitmap.Height; y++) {
                    Color originalColor = mOriginalBitmap.GetPixel(x, y);
                    Color finalColor = mFinalBitmap.GetPixel(x, y);

                    if (!originalColor.Equals(finalColor)) {
                        ChangedPixels.Add(new Point(x, y));
                    }
                }
            }
        }

        public Bitmap GetBitmapMarked() {
            Bitmap bmp = mFinalBitmap;

            for (int i = 0; i < ChangedPixels.Count - 1; i++) {
                bmp.SetPixel(ChangedPixels[i].X, ChangedPixels[i].Y, Color.Red);
            }

            return bmp;
        }

        public int getChangedPixelsNum() {
            return ChangedPixels.Count;
        }

    }
}