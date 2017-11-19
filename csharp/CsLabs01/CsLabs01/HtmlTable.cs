using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CsLabs01 {
    public class HtmlTable {

        public const char VALUE_DELIMITER = ';';

        public const char ROW_DELIMITER = '$';

        private IDictionary<int, List<String>> rowsMap;

        private Boolean withoutHeaders;

        public HtmlTable() {
       
            this.rowsMap = new Dictionary<int , List<String>>();
        }

        /**
         * 2 wiersze / 3 i 4 kolumny
         * @example inputData = "5135;1211;512515$1111;4124;5531;63161"
         * 
         **/
        public HtmlTable(String data) : this() {

            foreach ( String row in data.Split(ROW_DELIMITER) ) {

                AddRow(row);
            }
        }

        public HtmlTable(String data, Boolean withoutHeaders) : this(data) {

            this.withoutHeaders = withoutHeaders;
        }

        public void AddRow(String values) {

            List<String> rowValues = new List<String>();
            foreach (String value in values.Split(VALUE_DELIMITER)) {
                rowValues.Add(value);
            }

            this.rowsMap[this.rowsMap.Count + 1] = rowValues;

        }

        public void AddColumn(int row , String val) {
            this.rowsMap [ row ].Add(val);
        }

        public String GenerateHtmlData() {

            StringBuilder htmlData = new StringBuilder()

                .Append(OpenTag("html" , 0))

                    .Append(OpenTag("head" , 1))
                        .Append(BuildTag("title" , "DEFAULT TITLE" , 2))
                    .Append(CloseTag("head" , 1))

                    .Append(OpenTag("body" , 1))
                        .Append(OpenTag("table" , 2));

                        if ( !withoutHeaders )
                            htmlData.Append(OpenTag("thead" , 3));
                            foreach (KeyValuePair<int , List<String>> entry in rowsMap) {
                                htmlData = withoutHeaders ? AppendNormalRow(htmlData , entry) : AppendHeaderAndFooter(htmlData , entry);
                            }

                        if (!withoutHeaders)
                            htmlData
                                .Append(CloseTag("tbody" , 3));

            return htmlData
                        .Append(CloseTag("table", 2))
                    .Append(CloseTag("body", 1))
                .Append(CloseTag("html", 0))

                .ToString();
        }

        private StringBuilder AppendHeaderAndFooter(StringBuilder sb, KeyValuePair<int , List<String>> row) {

            String colTag = row.Key.Equals(1) ? "th" : "td";
            if (row.Key.Equals(2)) {
                sb
                    .Append(CloseTag("thead" , 3))
                    .Append(OpenTag("tfoot" , 3));
            }

            sb.Append(OpenTag("tr" , 4));
            foreach (String value in row.Value) {

                sb.Append(BuildTag(colTag , value , 5));
            }

            sb.Append(CloseTag("tr" , 4));
            if (row.Key.Equals(2)) {

                sb
                    .Append(CloseTag("tfoot" , 3))
                    .Append(OpenTag("tbody" , 3));
            }

            return sb;
        }

        private StringBuilder AppendNormalRow(StringBuilder sb , KeyValuePair<int , List<String>> row) {

            String colTag = row.Key.Equals(1) ? "th" : "td";
            sb.Append(OpenTag("tr" , 3));

            foreach (String value in row.Value) {
                sb.Append(BuildTag("td" , value , 4));
            }

            sb.Append(CloseTag("tr" , 3));

            return sb;
        }

        public List<String> GetRow(int i) {
            return this.rowsMap [ i ];
        }

        public String GetValue(int row , int col) {
            return this.rowsMap [ row ] [ col ];
        }

        public String Head {
            get; set;
        }

        public String Foot {
            get; private set;
        }

        private String AddTabWhiteSpace(int j) {

            StringBuilder tabs = new StringBuilder();
            for (int i = 0; i < j; i++) {

                tabs.Append("    ");
            }

            return tabs.ToString();
        }

        private String OpenTag(String tag, int place) {

            return new StringBuilder(AddTabWhiteSpace(place))
                .Append("<")
                .Append(tag)
                .Append(">")
                .Append(Environment.NewLine)
                .ToString();
        }

        private String CloseTag(String tag, int place) {

            return new StringBuilder(AddTabWhiteSpace(place))
                .Append("</")
                .Append(tag)
                .Append(">")
                .Append(Environment.NewLine)
                .ToString();
        }

        private String BuildTag(String tag, String value , int place) {

            return
                new StringBuilder()
                        .Append(OpenTag(tag, place))
                            .Append(AddTabWhiteSpace(place + 1))
                            .Append(value)
                            .Append(Environment.NewLine)
                        .Append(CloseTag(tag, place))
                    .ToString();
        }

    }
}
