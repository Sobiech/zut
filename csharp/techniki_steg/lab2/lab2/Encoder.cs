using System;
using System.Drawing;
using System.IO;
using System.Security.Cryptography;

namespace lab2
{
    class Encoder
    {
        byte[] hashKey;
        byte[] encrypted;
        
        public Encoder(byte[] pHashKey)
        {
            hashKey = pHashKey;
        }

        public Bitmap EncodeMessage(Bitmap bitmap, String text)
        {
            
            encrypted = EncryptStringToBytes_Aes(text, hashKey);
            Console.WriteLine("Original encrypted: {0} \t length: {1}", System.Text.Encoding.UTF8.GetString(encrypted), encrypted.Length);

            // zakodowana wiadomosc w bajtach
            byte[] messageToEncode = new byte[encrypted.Length + 1];

            // pierwszy bajt to rozmiar wiadomosci
            messageToEncode[0] = (byte)encrypted.Length;

            // dopisanie zaszyfrowanej wiadomosci
            Array.Copy(encrypted, 0, messageToEncode, 1, encrypted.Length);
            int messageLength = messageToEncode.Length;

            Console.WriteLine("Encoding message length: {0}", messageLength);
           
            return encryptMessageOnBitmap(bitmap, messageLength, messageToEncode);
        }

        /**
         *  Metoda zapisuje wiadomosc w bitmapie:
         *  uzycie LSB / Least Significant Byte dla zdjęć BMP
         */
        private Bitmap encryptMessageOnBitmap(Bitmap bitmap, int messageLength, byte[] messageToEncode)
        {

            int byteCounter = 0;
            int bitPosition = 0;
            byte input = messageToEncode[byteCounter++];

            byte x1, x2;

            Color color, newColor;
            byte a1, a2, a3;

            // obrazek w bitmapie
            Bitmap encodedBitmap = new Bitmap(bitmap);

            for (int x = 0; x < bitmap.Width; x++)
            {
                for (int y = 0; y < bitmap.Height; y++)
                {
                    if (byteCounter == messageLength && bitPosition == 8)
                    {
                        return encodedBitmap;
                    }

                    if (bitPosition == 8)
                    {
                        bitPosition = 0;
                        input = messageToEncode[byteCounter++];
                    }

                    x1 = ((input & (1 << bitPosition)) > 0) ? (byte)1 : (byte)0;
                    bitPosition++;
                    x2 = ((input & (1 << bitPosition)) > 0) ? (byte)1 : (byte)0;
                    bitPosition++;

                    color = encodedBitmap.GetPixel(x, y);

                    a1 = color.R;
                    a2 = color.G;
                    a3 = color.B;

                    if (((x1 & 1) == ((a1 ^ a3) & 1)) && ((x2 & 1) == ((a2 ^ a3) & 1)))
                    {
                    }
                    else if (((x1 & 1) != ((a1 ^ a3) & 1)) && ((x2 & 1) == ((a2 ^ a3) & 1)))
                    {
                        a1 = (byte)(a1 ^ 1);
                    }
                    else if (((x1 & 1) == ((a1 ^ a3) & 1)) && ((x2 & 1) != ((a2 ^ a3) & 1)))
                    {
                        a2 = (byte)(a2 ^ 1);
                    }
                    else
                    {
                        a3 = (byte)(a3 ^ 1);
                    }

                    newColor = Color.FromArgb(a1, a2, a3);
                    encodedBitmap.SetPixel(x, y, newColor);
                }
            }

            return encodedBitmap;
        }


        private byte[] EncryptStringToBytes_Aes(string plainText, byte[] hashKey)
        {
            
            byte[] encrypted;
            byte[] nonce = new byte[16];
            byte[] key = new byte[32];

            Array.Copy(hashKey, 0, key, 0, 32);
            Array.Copy(hashKey, 32, nonce, 0, 16);

            using (Aes aesAlg = Aes.Create())
            {
                aesAlg.Key = key;
                aesAlg.IV = nonce;

                ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msEncrypt = new MemoryStream())
                {
                    using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                    {
                        using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                        {
                            swEncrypt.Write(plainText);
                        }
                        encrypted = msEncrypt.ToArray();
                    }
                }
            }
            return encrypted;
        }
    }
}
