using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CsLabs01 {

    class Program {

        static void Main(string[] args) {

            String fileName = "data.txt";
            String saveName = "f1.html";

            Console.WriteLine("Wybrana nazwa pliku:{0}", fileName);

            String data = FileUtils.ReadFileValues(fileName);

            Console.WriteLine(data);

            if (data.Length < 1 ) {

                Console.WriteLine("Brak danych do wyswietlenia ");
            } else  {

                HtmlTable htmlTable = new HtmlTable(data, false);
                String generatedData = htmlTable.GenerateHtmlData();
                Console.WriteLine("\n\n Data1: {0} -> Data:\n {1}" ,  saveName, generatedData );
                FileUtils.SaveFile(saveName , generatedData);

                HtmlTable table2 = new HtmlTable("1;2$3;4", true);
                Console.WriteLine("\n\n Data2: {0}" , table2.GenerateHtmlData());

                HtmlTable table3 = new HtmlTable("gealgkae l jaekgjakel gjaklejgklaej klgaejk; 3kflake aekj kafjeklfjaekl jfael fae; faefklae kfaek falek faef $faelf aeflkae ljjhfjahefjkahekjf;318uf8a uaf8e9uf8 9ua8e9fua8e9fu a98fuae9f uae; f0ea-0faie9faie90if90aeif09aeif90ae" , true);
                Console.WriteLine("\n\n Data3: {0}" , table3.GenerateHtmlData());

            }

            Console.ReadLine();
        }
    }
}
