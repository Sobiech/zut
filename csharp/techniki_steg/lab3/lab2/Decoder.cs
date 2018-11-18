using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Security.Cryptography;
using System.Text;

namespace lab2 {
    class Decoder {
        byte[] passHashKey;
        byte[] stegHashKey;

        public Decoder(byte[] passHashKey, byte[] stegHashKey) {
            this.passHashKey = passHashKey;
            this.stegHashKey = stegHashKey;
        }

        public String DecodeMessage(Bitmap bitmap) {

            byte[] hiddenMessage = Decode(bitmap);

            if (hiddenMessage.Length <= 0)
                throw new BadImageFormatException("Given image doesnt contain any encrypted message");

            Console.WriteLine("hidden size: {0} message: {1}", hiddenMessage.Length, Encoding.UTF8.GetString(hiddenMessage));

            hiddenMessage = redundantDecoding(hiddenMessage);
            return DecryptStringFromBytes_Aes(hiddenMessage);
        }

        private byte[] Decode(Bitmap bitmap) {
            int messageLength = getMessageLength(bitmap, passHashKey),
                maxIndex = bitmap.Width * bitmap.Height,
                pixelCounter = 0,
                pixIndex,
                counter = 1,
                mByteCounter = 0;

            byte[] message = new byte[messageLength];
            Color pixel = new Color();
            List<int> unvisitedPixels = new List<int>();

            //ineksy 4 pierwszych pikseli - z dlugoscia wiad.
            Random rnd = new Random(BitConverter.ToInt32(this.stegHashKey, 0));

            //lista indexów wszystkich pikseli bitmapy
            for (int i = 0; i < maxIndex; i++) {
                unvisitedPixels.Add(i);
            }

            int x1, x2;

            while (pixelCounter < messageLength * 4) {
                pixIndex = rnd.Next(0, maxIndex);
                if (unvisitedPixels.IndexOf(pixIndex) > -1) {
                    unvisitedPixels.Remove(pixIndex);

                    if (counter < 9) {
                        counter++;
                        continue;
                    }

                    pixel = bitmap.GetPixel(pixIndex % bitmap.Width, pixIndex / bitmap.Width);

                    byte a1 = pixel.R, a2 = pixel.G, a3 = pixel.B;

                    x1 = ( byte ) ( ( a1 ^ a3 ) & 1 );
                    x2 = ( byte ) ( ( a2 ^ a3 ) & 1 );

                    message[mByteCounter] |= ( byte ) ( x1 << ( 2 * ( pixelCounter % 4 ) ) );
                    message[mByteCounter] |= ( byte ) ( x2 << ( 2 * ( pixelCounter % 4 ) + 1 ) );

                    pixelCounter++;

                    if (pixelCounter % 4 == 0)
                        mByteCounter++;

                }
            }

            Console.WriteLine("Message to decode: " + Encoding.UTF8.GetString(message));
            return message;
        }

        public byte[] getMessage(Bitmap bitmap, byte messageLength) {
            Bitmap encodedBitmap = bitmap;
            byte[] message = new byte[messageLength + 2];
            return DecryptMessageFromBitmap(bitmap, message, messageLength);
        }

        private byte[] DecryptMessageFromBitmap(Bitmap bitmap, byte[] message, byte messageLength) {

            int byteCounter = 0;
            int bitPosition = 0;

            byte x1, x2;

            Color color;

            for (int x = 0; x < bitmap.Width; x++) {
                for (int y = 0; y < bitmap.Height; y++) {
                    if (byteCounter > messageLength && bitPosition == 4) {
                        return message;
                    }
                    if (bitPosition == 4) {
                        byteCounter++;
                        bitPosition = 0;
                    }
                    color = bitmap.GetPixel(x, y);

                    byte a1 = color.R, a2 = color.G, a3 = color.B;

                    x1 = ( byte ) ( ( ( a1 & 1 ) + ( a3 & 1 ) ) & 1 );
                    x2 = ( byte ) ( ( ( a2 & 1 ) + ( a3 & 1 ) ) & 1 );

                    message[byteCounter] |= ( byte ) ( x1 << ( 2 * bitPosition ) );
                    message[byteCounter] |= ( byte ) ( x2 << ( 2 * bitPosition + 1 ) );
                    bitPosition++;
                }
            }

            return message;
        }

        private string DecryptStringFromBytes_Aes(byte[] cipherText) {

            if (cipherText == null || cipherText.Length <= 0)
                throw new ArgumentNullException("cipherText");

            string plaintext = null;

            byte[] nonce = new byte[16];
            byte[] key = new byte[32];

            Array.Copy(this.passHashKey, 0, key, 0, 32);
            Array.Copy(this.passHashKey, 32, nonce, 0, 16);

            using (Aes aesAlg = Aes.Create()) {
                aesAlg.Key = key;
                aesAlg.IV = nonce;

                ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msDecrypt = new MemoryStream(cipherText)) {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read)) {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt)) {
                            plaintext = srDecrypt.ReadToEnd();
                        }
                    }
                }
            }

            return plaintext;

        }

        private byte[] redundantDecoding(byte[] encodedText) {
            int messageLength = encodedText.Length / 5;
            byte[] message = new byte[messageLength];

            int mByte = 0;

            Console.WriteLine("New message length is: " + encodedText.Length);

            for (int i = 0; i < messageLength * 5; i++) {
                //1 bit
                message[mByte] = ( byte ) ( ( getBitFromCode(( byte ) ( encodedText[i] >> 3 )) ) << 7 );

                //2 bit
                message[mByte] |= ( byte ) ( ( getBitFromCode(( byte ) ( ( ( encodedText[i] & 7 ) << 2 ) | ( ( ( encodedText[i + 1] ) >> 6 ) ) )) ) << 6 );

                //3 bit
                message[mByte] |= ( byte ) ( ( getBitFromCode(( byte ) ( ( ( encodedText[i + 1] & 62 ) >> 1 ) )) ) << 5 );

                //4 bit
                message[mByte] |= ( byte ) ( ( getBitFromCode(( byte ) ( ( ( encodedText[i + 1] & 1 ) << 4 ) | ( ( encodedText[i + 2] >> 4 ) ) )) ) << 4 );

                //5 bit
                message[mByte] |= ( byte ) ( ( getBitFromCode(( byte ) ( ( ( encodedText[i + 2] & 15 ) << 1 ) | ( ( encodedText[i + 3] >> 7 ) ) )) ) << 3 );

                //6 bit
                message[mByte] |= ( byte ) ( ( getBitFromCode(( byte ) ( ( ( encodedText[i + 3] & 124 ) >> 2 ) )) ) << 2 );

                //7 bit
                message[mByte] |= ( byte ) ( ( getBitFromCode(( byte ) ( ( ( encodedText[i + 3] & 3 ) << 3 ) | ( ( encodedText[i + 4] >> 5 ) ) )) ) << 1 );

                //8 bit
                message[mByte] |= ( byte ) ( getBitFromCode(( byte ) ( ( ( encodedText[i + 4] & 31 ) ) )) );

                i += 4;
                mByte++;

            }

            return message;
        }

        private byte getBitFromCode(byte myByte) {
            byte redundantKey = 28; //11100
            if (myByte == redundantKey) {
                return 1;
            }
            return 0;
        }

        private int getMessageLength(Bitmap bitmap, byte[] encryptedSKey) {
            int maxIndex = bitmap.Width * bitmap.Height,
                pixelCounter = 0,
                pixIndex;

            byte messageLength = 0;
            byte[] temp = new byte[5];

            Color pixel = new Color();

            List<int> unvisitedPixels = new List<int>();

            //ineksy 4 pierwszych pikseli - z dlugoscia wiad.
            Random rnd = new Random(BitConverter.ToInt32(encryptedSKey, 0));

            //lista indexów wszystkich pikseli bitmapy
            for (int i = 0; i < maxIndex; i++) {
                unvisitedPixels.Add(i);
            }

            byte x1, x2;

            while (pixelCounter < 4) {
                pixIndex = rnd.Next(0, maxIndex);

                if (unvisitedPixels.IndexOf(pixIndex) > -1) {
                    unvisitedPixels.Remove(pixIndex);
                    pixel = bitmap.GetPixel(pixIndex % bitmap.Width, pixIndex / bitmap.Width);

                    byte a1 = pixel.R, a2 = pixel.G, a3 = pixel.B;

                    x1 = ( byte ) ( ( ( a1 & 1 ) ^ ( a3 & 1 ) ) & 1 );
                    x2 = ( byte ) ( ( ( a2 & 1 ) ^ ( a3 & 1 ) ) & 1 );

                    messageLength |= ( byte ) ( x1 << ( 2 * pixelCounter ) );
                    messageLength |= ( byte ) ( x2 << ( 2 * pixelCounter + 1 ) );

                    pixelCounter++;
                }
            }

            //Console.WriteLine("Msg len is: " + (int)messageLength);

            return ( int ) messageLength;
        }

    }
}
