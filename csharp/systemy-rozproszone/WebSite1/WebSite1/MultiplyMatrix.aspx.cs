using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class MultiplyMatrix : Page
{

    private const string RESULT_FILE_NAME = "ResultMatrix.txt";

    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void UploadButton_Click(object sender, EventArgs e) {

        try {

            ValidateUploadedFile(FileUploadMatrix1, "Macierz 1");
            ValidateUploadedFile(FileUploadMatrix2, "Macierz 2");

            List<String> linesMatrix1 = GetLines(FileUploadMatrix1);
            List<String> linesMatrix2 = GetLines(FileUploadMatrix2);

            if (linesMatrix1.Count != linesMatrix2.Count) {
                throw new ArgumentException("Niepoprawna wielkość macierz 1 lub 2");
            }

            int[,] matrixA = MatrixUtils.ReadMatrixFromContent(linesMatrix1);
            int[,] matrixB = MatrixUtils.ReadMatrixFromContent(linesMatrix2);

            string resultMatrixLabel = String.Empty;

            Stopwatch sw = new Stopwatch();
            sw.Start();
            using (StreamWriter outputFile = new StreamWriter(Path.Combine(Server.MapPath("~/"), RESULT_FILE_NAME))) {
                MatrixUtils.MultiplyMatrix(matrixA, matrixB).ForEach(str => outputFile.WriteLine(str));
            }
            sw.Stop();

            ResultMatrix.Text = "Macierze zostały pomnożone poprawnie. Zajęło: " + sw.Elapsed.TotalMilliseconds + " [ms]";
            MatrixDownload.Enabled = true;
            StatusLabel.Text = "Status przesyłania: Pliki zostały poprawnie odczytane";
        } catch (ArgumentException ex) {
            StatusLabel.Text = ex.Message;
        } catch (Exception ex) {
            StatusLabel.Text = "Status przesyłania:: Plik nie został przesłany. Treść błędu: " + ex.Message;
        }
    }

    protected void MatrixDownload_Click(object sender, EventArgs e) {

        MatrixDownload.Enabled = false;
        string filePath = Server.MapPath("~/") + RESULT_FILE_NAME;
        FileInfo file = new FileInfo(filePath);
        if (file.Exists) {
            Response.Clear();
            Response.AddHeader("Content-Disposition", "attachment; filename=" + file.Name);
            Response.AddHeader("Content-Length", file.Length.ToString());
            Response.ContentType = "text/plain";
            Response.Flush();
            Response.TransmitFile(file.FullName);
            Response.End();
        } else {
            MatrixDownloadInfo.Text = "Dany plik nie jest dostepny do ściągnięcia";
        }
        
    }

    private List<String> GetLines(FileUpload fileUpload) {

        List<String> lineList = new List<string>();
        using (StreamReader inputStreamReader = new StreamReader(fileUpload.PostedFile.InputStream)) {
            string line;
            while (( line = inputStreamReader.ReadLine() ) != null) {
                lineList.Add(line);
            }
        }

        return lineList;
    }


    private void ValidateUploadedFile(FileUpload fileUploadControl, String matrix) {

        if (fileUploadControl.PostedFile.ContentType != "text/plain") {
            throw new ArgumentException("Status przesyłania: Tylko pliki typu text/plain są akceptowane! / " + matrix);
        }

        string filename = Path.GetFileName(fileUploadControl.FileName);
        fileUploadControl.SaveAs(Server.MapPath("~/") + filename);
    }

}