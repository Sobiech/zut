using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestPlugin2
{
    public class PluginNumberTwo
    {
        public static string getPluginName()
        {
            return "PluginNumberTwo: PrecyzyjnaNazwaDrugiejFunkcji";
        }
        public static string PrecyzyjnaNazwaDrugiejFunkcji(string value)
        {
            return "PluginNumberTwo: wartość podana do precyzyjnej funkcji to: " + value;
        }
    }
}
