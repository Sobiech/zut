using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Security.Cryptography;
using System.Text;

namespace lab2 {
    class Encoder {
        byte[] passHashKey;
        byte[] stegHashKey;
        byte[] encrypted;

        public Encoder(byte[] passHashKey, byte[] stegHashKey) {
            this.passHashKey = passHashKey;
            this.stegHashKey = stegHashKey;
        }

        public Bitmap EncodeMessage(Bitmap bitmap, String text) {

            encrypted = AesHelper.Encrypt(text, passHashKey);
            Console.WriteLine("Original encrypted: {0} \t length: {1}", System.Text.Encoding.UTF8.GetString(encrypted), encrypted.Length);

            // zakodowana wiadomosc w bajtach
            byte[] messageToEncode = new byte[encrypted.Length + 1];

            // dopisanie zaszyfrowanej wiadomosci
            Array.Copy(encrypted, 0, messageToEncode, 1, encrypted.Length);
            int messageLength = messageToEncode.Length;

            Console.WriteLine("Encoding message length: {0}", messageLength);

            // obrazek w bitmapie
            Bitmap encodedBitmap = new Bitmap(bitmap);

            // kod nadmiarowy
            encrypted = RedundantEncoding(encrypted);
            return Encode(encodedBitmap, encrypted);
        }


        private Bitmap Encode(Bitmap bitmap, byte[] messageToEncode) {

            Console.WriteLine("Message to encode: " + Encoding.UTF8.GetString(messageToEncode));
            byte input, x1, x2;

            byte[] newMessage = new byte[messageToEncode.Length + 1];

            Color pixel, newPixel;

            int imgW = bitmap.Width,
                imgH = bitmap.Height,
                maxIndex = imgH * imgW,
                encodedBit = 0,
                byteCounter = 0,
                pixIndex, rndW = 1, rndH = 1;

            List<int> unvisitedPixels = new List<int>();
            newMessage[0] = ( byte ) messageToEncode.Length;

            Array.Copy(messageToEncode, 0, newMessage, 1, messageToEncode.Length);
            input = newMessage[0];

            //lista indexów  pikseli bitmapy
            for (int i = 0; i < maxIndex; i++) {
                unvisitedPixels.Add(i);
            }

            Random rnd = new Random(BitConverter.ToInt32(stegHashKey, 0));

            while (true) {
                if (byteCounter == newMessage.Length && encodedBit == 8)
                    return bitmap;

                if (encodedBit == 8) {
                    input = newMessage[byteCounter++];
                    encodedBit = 0;
                }

                while (true) {
                    //losowanie wspolrzednych pixsela
                    pixIndex = rnd.Next(0, maxIndex);
                    if (unvisitedPixels.IndexOf(pixIndex) > -1) {
                        rndH = pixIndex / imgW;
                        rndW = pixIndex % imgW;
                        unvisitedPixels.Remove(pixIndex);
                        break;
                    }
                }

                byte a1, a2, a3;

                x1 = ( ( input & ( 1 << encodedBit ) ) > 0 ) ? ( byte ) 1 : ( byte ) 0;
                encodedBit++;

                x2 = ( ( input & ( 1 << encodedBit ) ) > 0 ) ? ( byte ) 1 : ( byte ) 0;

                pixel = bitmap.GetPixel(rndW, rndH);

                a1 = pixel.R;
                a2 = pixel.G;
                a3 = pixel.B;

                if (( ( x1 & 1 ) == ( ( a1 ^ a3 ) & 1 ) ) && ( ( x2 & 1 ) == ( ( a2 ^ a3 ) & 1 ) )) {
                } else if (( ( x1 & 1 ) != ( ( a1 ^ a3 ) & 1 ) ) && ( ( x2 & 1 ) == ( ( a2 ^ a3 ) & 1 ) )) {
                    a1 = ( byte ) ( a1 ^ 1 );
                } else if (( ( x1 & 1 ) == ( ( a1 ^ a3 ) & 1 ) ) && ( ( x2 & 1 ) != ( ( a2 ^ a3 ) & 1 ) )) {
                    a2 = ( byte ) ( a2 ^ 1 );
                } else {
                    a3 = ( byte ) ( a3 ^ 1 );
                }

                newPixel = Color.FromArgb(a1, a2, a3);
                encodedBit++;
                bitmap.SetPixel(rndW, rndH, newPixel);
            }
        }

        private byte[] RedundantEncoding(byte[] encryptedText) {
            int newMessageLength = 5 * encryptedText.Length,
                NMByteCounter = 0;

            byte[] newMessage = new byte[newMessageLength];

            foreach (byte b in encryptedText) {
                //1bit
                newMessage[NMByteCounter] = ( byte ) ( getCode(( b & ( 1 << 7 ) ) > 0) << 3 );
                //2bit
                newMessage[NMByteCounter] += ( byte ) ( getCode(( b & ( 1 << 6 ) ) > 0) >> 2 );
                newMessage[NMByteCounter + 1] = ( byte ) ( getCode(( b & ( 1 << 6 ) ) > 0) << 6 );
                //3bit
                newMessage[NMByteCounter + 1] += ( byte ) ( getCode(( b & ( 1 << 5 ) ) > 0) << 1 );
                //4bit
                newMessage[NMByteCounter + 1] += ( byte ) ( getCode(( b & ( 1 << 4 ) ) > 0) >> 4 );
                newMessage[NMByteCounter + 2] = ( byte ) ( getCode(( b & ( 1 << 4 ) ) > 0) << 4 );
                //5bit
                newMessage[NMByteCounter + 2] += ( byte ) ( getCode(( b & ( 1 << 3 ) ) > 0) >> 1 );
                newMessage[NMByteCounter + 3] = ( byte ) ( getCode(( b & ( 1 << 3 ) ) > 0) << 7 );
                //6bit
                newMessage[NMByteCounter + 3] += ( byte ) ( getCode(( b & ( 1 << 2 ) ) > 0) << 2 );
                //7bit
                newMessage[NMByteCounter + 3] += ( byte ) ( getCode(( b & ( 1 << 1 ) ) > 0) >> 3 );
                newMessage[NMByteCounter + 4] = ( byte ) ( getCode(( b & ( 1 << 1 ) ) > 0) << 5 );
                //8bit
                newMessage[NMByteCounter + 4] += ( byte ) ( getCode(( b & 1 ) > 0) );
                NMByteCounter += 5;
            }
            return newMessage;
        }

        private byte getCode(bool bitValue) {
            byte redundantKey = 28; //11100

            if (bitValue == true)
                return redundantKey; //return 11100
            else
                return ( byte ) ( redundantKey ^ 31 );     //return 00011
        }

    }
}
