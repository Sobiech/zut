using System;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Security.Cryptography;
using System.Drawing.Imaging;

namespace lab2
{
    public partial class Form1 : Form
    {

        Bitmap originalBitmap;
        Bitmap encodedBitmap;

        Bitmap bitmapToEncode;

        public Form1()
        {
            InitializeComponent();
        }


        private void loadImage_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog dlg = new OpenFileDialog())
            {
                dlg.Title = "Open Image";
                dlg.Filter = "bmp files (*.bmp)|*.bmp";

                if (dlg.ShowDialog() == DialogResult.OK)
                {
                    originalBitmap = new Bitmap(dlg.FileName);
                    pictureBox1.Image = originalBitmap;
                    bitmapToEncode = originalBitmap;
                    pictureBox2.Image = null;
                    encodedBitmap = null;
                }
            }
        }

        private void encodeMessage_Click(object sender, EventArgs e)
        {
            try
            {

                string password = getPasswordAndValidateInputs(true);
                string text = messageToEncode.Text;
                SHA512 shaM = new SHA512Managed();
                Console.WriteLine("Original text: {0} \t length:{1}", text, text.Length);
                byte[] hash = shaM.ComputeHash(Encoding.UTF8.GetBytes(password));
                Encoder encoder = new Encoder(hash);
                encodedBitmap = encoder.EncodeMessage(originalBitmap, messageToEncode.Text);
                pictureBox2.Image = encodedBitmap;
            } catch (Exception exc)
            {
                Console.WriteLine(exc.ToString());
                MessageBox.Show(exc.Message);
            }
        }

        private void decodeMessage_Click(object sender, EventArgs e)
        {
            try
            {
                string password = getPasswordAndValidateInputs();
                SHA512 shaM = new SHA512Managed();
                byte[] hash = shaM.ComputeHash(Encoding.UTF8.GetBytes(password));
                Decoder decoder = new Decoder(hash);
                String decodedMessage = decoder.DecodeMessage(bitmapToEncode);
                Console.WriteLine("Decoded message: {0}", decodedMessage);
                MessageBox.Show("Odczytana wiadomość to: " + decodedMessage);
            }
            catch(BadImageFormatException exc)
            {
                MessageBox.Show(exc.Message);
            }
            catch(CryptographicException exc)
            {
                MessageBox.Show("Given passphrase is invalid");
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private string getPasswordAndValidateInputs(Boolean encode = false)
        {
            if (bitmapToEncode is null)
            {
                String msg = (encode) ? "encoded" : "decoded";
                throw new ArgumentException("No image choosen to be " + msg);
            }
            if (String.IsNullOrEmpty(messageToEncode.Text) && encode)
                throw new ArgumentException("Message to encode is not set");

            String password = tbPassword.Text;
            if (String.IsNullOrEmpty(password))
                throw new ArgumentException("Pleas input passphrase");

            return password;
        }

        private void saveEncodedImage_Click(object sender, EventArgs e)
        {
            SaveFileDialog dialog = new SaveFileDialog();
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                encodedBitmap.Save(dialog.FileName, ImageFormat.Bmp);
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            bitmapToEncode = originalBitmap; 
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            bitmapToEncode = encodedBitmap;
        }
    }
}
