using System;
using System.Xml;
using System.IO;

namespace CsLabs03
{
    class XmlUtils
    {

        public String FilePath { get; set; }

        private String csProjectFile;


        public XmlUtils()
        {
        }

        public XmlUtils(String path)
        {

            this.FilePath = path;
    
        }

        public void readXml()
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

            csProjectFile = FilePath + "\\" + splitedValue;

            readXmlByPath(csProjectFile);
        }


        public void readXmlByPath(String path)
        {

            if (path == null || (path != null && path.Length < 1))
            {
                throw new ArgumentException("Podana ścieżka jest niepoprawna !");
            }

          
            XmlTextReader reader = new XmlTextReader(path);
            while (reader.Read())
            {
                switch (reader.NodeType)
                {
                    case XmlNodeType.Element: // The node is an element.
                        Console.Write("<" + reader.Name + ">");
                        break;
                    case XmlNodeType.Text: //Display the text in each element.
                        Console.Write(reader.Value);
                        break;
                    case XmlNodeType.EndElement: //Display the end of the element.
                        Console.Write("</" + reader.Name + ">");
                        break;
                }
            }
           

            Console.ReadLine();

            FileUtils.CompressDirectory(path, "CsLabs03.zip");

        }
    }

}
