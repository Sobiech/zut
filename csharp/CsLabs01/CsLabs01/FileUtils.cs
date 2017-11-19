using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace CsLabs01
{
    class FileUtils
    {

        public static String ReadFileValues(String fileName) {

            StringBuilder data = new StringBuilder();

            try {

                using (StreamReader sr = new StreamReader(fileName)) {

                    data.Append(sr.ReadToEnd());
                }

            } catch (Exception e) {

                Console.WriteLine("ERROR:", e);
            }

            return data.ToString();
        }

        public static void SaveFile(String fileName , String text) {

            File.WriteAllText(fileName , text);
        }

    }
}
