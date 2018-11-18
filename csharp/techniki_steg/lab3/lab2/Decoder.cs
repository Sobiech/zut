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

            if (hiddenMessage.Length <= 0) {
                throw new BadImageFormatException("Given image doesnt contain any encrypted message");
            }

            Console.WriteLine("hidden size: {0} message: {1}", hiddenMessage.Length, Encoding.UTF8.GetString(hiddenMessage));
            // kod nadmiarowy
            hiddenMessage = RedundantDecoding(hiddenMessage);
            List<int> unvisitedPixels = new List<int>();

            return AesHelper.Decrypt(hiddenMessage, passHashKey);
        }

        private byte[] Decode(Bitmap bitmap) {

            int maxIndex = bitmap.Width * bitmap.Height;
            int messageLength = getMessageLength(bitmap);
            int counter = 1, mByteCounter = 0, pixIndex, pixelCounter = 0;

            byte[] message = new byte[messageLength];

            Color pixel = new Color();
            Random rnd = new Random(BitConverter.ToInt32(this.stegHashKey, 0));
            List<int> unvisitedPixels = new List<int>();

            //lista indexów wszystkich pikseli bitmapy
            for (int i = 0; i < maxIndex; i++) {
                unvisitedPixels.Add(i);
            }

            int x1, x2;

            while (pixelCounter < messageLength * 4) {

                // permutation of given stegHashKey
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

        private byte[] RedundantDecoding(byte[] encodedText) {
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

        private int getMessageLength(Bitmap bitmap) {
            int maxIndex = bitmap.Width * bitmap.Height,
                pixelCounter = 0,
                pixIndex;

            byte messageLength = 0;
            byte[] temp = new byte[5];

            Color pixel = new Color();

            List<int> unvisitedPixels = new List<int>();

            Random rnd = new Random(BitConverter.ToInt32(this.stegHashKey, 0));

            //lista indexów wszystkich pikseli bitmapy
            for (int i = 0; i < maxIndex; i++) {
                unvisitedPixels.Add(i);
            }

            byte x1, x2;

            while (pixelCounter < 4) {

                // permutation of given stegHashKey
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
            return ( int ) messageLength;
        }

    }
}