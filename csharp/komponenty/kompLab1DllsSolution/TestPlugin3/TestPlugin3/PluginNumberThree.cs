using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestPlugin3
{
    public class PluginNumberThree
    {
        public static string getPluginName()
        {
            return "PluginNumberThree: PrecyzyjnaNazwaDrugiejFunkcji";
        }
        public static string PrecyzyjnaNazwaDrugiejFunkcji(string value)
        {
            return "PluginNumberThree: wartość podana do precyzyjnej funkcji to: " + value;
        }
    }
}
