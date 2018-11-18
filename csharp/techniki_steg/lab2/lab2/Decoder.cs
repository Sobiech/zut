using System;
using System.Drawing;
using System.IO;
using System.Security.Cryptography;

namespace lab2
{
    class Decoder
    {
        byte[] hashKey;

        public Decoder(byte[] pHashKey)
        {
            hashKey = pHashKey;
        }

        public String DecodeMessage(Bitmap bitmap)
        {
            byte messageLength = getMessageLength(bitmap);
            Console.WriteLine("messageLength is: {0}", messageLength);

            byte[] hiddenMessage = getMessage(bitmap, messageLength);
            byte[] message = new byte[hiddenMessage.Length - 2];

            Array.Copy(hiddenMessage, 1, message, 0, hiddenMessage.Length - 2);
            Console.WriteLine("hidden size: {0} message: {1}", message.Length, System.Text.Encoding.UTF8.GetString(message));

            if (message.Length <= 0)
                throw new BadImageFormatException("Given image doesnt contain any encrypted message");

            return DecryptStringFromBytes_Aes(message, hashKey);
        }

        public byte getMessageLength(Bitmap bitmap)
        {
            Bitmap encodedBitmap = bitmap;
            Color color;

            byte length = 0;
            byte x1, x2;

            // 4 piksele
            for (int p = 0; p < 4; p++)
            {
                color = encodedBitmap.GetPixel(0, p);

                byte a1 = color.R, a2 = color.G, a3 = color.B;
            
                x1 = (byte)(((a1 & 1) ^ (a3 & 1)) & 1);
                x2 = (byte)(((a2 & 1) ^ (a3 & 1)) & 1);

                length |= (byte)(x1 << (2 * p));
                length |= (byte)(x2 << (2 * p + 1));
            }
            return length;
        }

        public byte[] getMessage(Bitmap bitmap, byte messageLength)
        {
            Bitmap encodedBitmap = bitmap;           
            byte[] message = new byte[messageLength + 2];
            return DecryptMessageFromBitmap(bitmap, message, messageLength);
        }

        private byte[] DecryptMessageFromBitmap(Bitmap bitmap, byte[] message, byte messageLength)
        {

            int byteCounter = 0;
            int bitPosition = 0;

            byte x1, x2;

            Color color;

            for (int x = 0; x < bitmap.Width; x++)
            {
                for (int y = 0; y < bitmap.Height; y++)
                {
                    if (byteCounter > messageLength && bitPosition == 4)
                    {
                        return message;
                    }
                    if (bitPosition == 4)
                    {
                        byteCounter++;
                        bitPosition = 0;
                    }
                    color = bitmap.GetPixel(x, y);

                    byte a1 = color.R, a2 = color.G, a3 = color.B;

                    x1 = (byte)(((a1 & 1) + (a3 & 1)) & 1);
                    x2 = (byte)(((a2 & 1) + (a3 & 1)) & 1);

                    message[byteCounter] |= (byte)(x1 << (2 * bitPosition));
                    message[byteCounter] |= (byte)(x2 << (2 * bitPosition + 1));
                    bitPosition++;
                }
            }

            return message;
        }

        private string DecryptStringFromBytes_Aes(byte[] cipherText, byte[] hashKey)
        {
           
            if (cipherText == null || cipherText.Length <= 0)
                throw new ArgumentNullException("cipherText");

            string plaintext = null;

            byte[] nonce = new byte[16];
            byte[] key = new byte[32];

            Array.Copy(hashKey, 0, key, 0, 32);
            Array.Copy(hashKey, 32, nonce, 0, 16);

            using (Aes aesAlg = Aes.Create())
            {
                aesAlg.Key = key;
                aesAlg.IV = nonce;

                ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msDecrypt = new MemoryStream(cipherText))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {
                            plaintext = srDecrypt.ReadToEnd();
                        }
                    }
                }
            }

            return plaintext;

        }

    }
}
