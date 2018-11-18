using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace lab2 {
    class AesHelper {
        
        public static string Decrypt(byte[] cipher, byte[] hash) {

            if (cipher == null || cipher.Length <= 0)
                throw new ArgumentNullException("cipherText");

            if (hash == null || hash.Length < 63)
                throw new ArgumentException("Given hash is invalid / should be 64bytes");

            string plaintext = null;

            byte[] nonce = new byte[16];
            byte[] key = new byte[32];

            Array.Copy(hash, 0, key, 0, 32);
            Array.Copy(hash, 32, nonce, 0, 16);

            using (Aes aesAlg = Aes.Create()) {
                aesAlg.Key = key;
                aesAlg.IV = nonce;

                ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msDecrypt = new MemoryStream(cipher)) {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read)) {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt)) {
                            plaintext = srDecrypt.ReadToEnd();
                        }
                    }
                }
            }

            return plaintext;
        }

        public static byte[] Encrypt(string plainText, byte[] hash) {

            if (hash == null || hash.Length < 63)
                throw new ArgumentException("Given hash is invalid / should be 64bytes");

            if (String.IsNullOrEmpty(plainText))
                throw new ArgumentException("Text argument is null or empty");

            byte[] encrypted;
            byte[] nonce = new byte[16];
            byte[] key = new byte[32];

            Array.Copy(hash, 0, key, 0, 32);
            Array.Copy(hash, 32, nonce, 0, 16);

            using (Aes aesAlg = Aes.Create()) {
                aesAlg.Key = key;
                aesAlg.IV = nonce;
                ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msEncrypt = new MemoryStream()) {
                    using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write)) {
                        using (StreamWriter swEncrypt = new StreamWriter(csEncrypt)) {
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
