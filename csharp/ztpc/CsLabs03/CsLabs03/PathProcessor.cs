using System;
using System.IO;

namespace CsLabs03
{
    class PathProcessor
    {

        public String FilePath { get; set; }
        
        public PathProcessor()
        {
        }

        public PathProcessor(String path)
        {

            this.FilePath = path;
    
        }

        public void read()
        {
            String[] fileNames = Directory.GetFiles(FilePath, "*.sln");

            String fileName = null;

            foreach( String f in fileNames )
            {
                Console.WriteLine("Found file with name like: {0} ", f);
                fileName = f;
                break;
            }

            if (fileName == null)
            {
                throw new FileNotFoundException("Nieodnaleziono pliku o formacie *.sln w podany folderze !");
            }


            String gotContent = FileUtils.ReadFileValues(fileName);

            Console.WriteLine("Content of {0} is : ", fileName);
            Console.WriteLine(gotContent);


            string[] lines = gotContent.Split(
                new[] { "\r\n", "\r", "\n" },
                StringSplitOptions.None
            );

            String foundedLine = null;
            foreach ( String line in lines )
            {
                if ( line.Contains("Project"))
                {
                    foundedLine = line;
                    break;
                }
            }

            Console.WriteLine("Found line: {0}", foundedLine);
            String splitedValue = null;
            if (foundedLine != null) {

                splitedValue = foundedLine.Split('=')[1].Split(',')[1].Replace("\"","").Replace(" ", "");

            } else
            {
                throw new ArgumentNullException("Nieodnaleziono odpowiedniego parametru we wskazanej ścieżce!");
            }

            Console.WriteLine("Got splited value: {0}", splitedValue);

            String targetPath = FilePath + "\\" + splitedValue;

            if (targetPath == null || ( targetPath != null && targetPath.Length < 1 )) {
                throw new ArgumentException("Podana ścieżka jest niepoprawna !");
            }

            FileUtils.CompressDirectory(FilePath , targetPath.Replace(".csproj", "") );

        }

    }

}
