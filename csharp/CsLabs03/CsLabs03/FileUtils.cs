using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.IO.Compression;

namespace CsLabs03
{
    class FileUtils
    {

        public static String ReadFileValues(String fileName)
        {

            StringBuilder data = new StringBuilder();

            try
            {

                using (StreamReader sr = new StreamReader(fileName))
                {

                    data.Append(sr.ReadToEnd());
                }

            }
            catch (Exception e)
            {

                Console.WriteLine("Wystąpił błąd :{0}", e.Message);
            }

            return data.ToString();
        }

        public static void SaveFile(String fileName, String text)
        {

            File.WriteAllText(fileName, text);
        }

        public static void CompressDirectory(string sInDir, string sOutFile)
        {
            string[] sFiles = Directory.GetFiles(sInDir, "*.*", SearchOption.AllDirectories);
            int iDirLen = sInDir[sInDir.Length - 1] == Path.DirectorySeparatorChar ? sInDir.Length : sInDir.Length + 1;

            using (FileStream outFile = new FileStream(sOutFile + ".gz", FileMode.Create, FileAccess.Write, FileShare.None))
            using (GZipStream str = new GZipStream(outFile, CompressionMode.Compress))
                foreach (string sFilePath in sFiles)
                {
                    
                    string sRelativePath = sFilePath.Substring(iDirLen);
                    if ( !sRelativePath.StartsWith(".vs") && !sRelativePath.Contains("\\bin\\") && !sRelativePath.Contains("\\obj\\")) {
                        Console.WriteLine("Compressing: " + sRelativePath);
                        CompressFile(sInDir , sRelativePath , str);
                    }
                }
        }

        static void CompressFile(string sDir, string sRelativePath, GZipStream zipStream)
        {
            
            //Compress file name
            char[] chars = sRelativePath.ToCharArray();
            zipStream.Write(BitConverter.GetBytes(chars.Length), 0, sizeof(int));
            foreach (char c in chars)
                zipStream.Write(BitConverter.GetBytes(c), 0, sizeof(char));

            //Compress file content
            byte[] bytes = File.ReadAllBytes(Path.Combine(sDir, sRelativePath));
            zipStream.Write(BitConverter.GetBytes(bytes.Length), 0, sizeof(int));
            zipStream.Write(bytes, 0, bytes.Length);
        }
    }
}