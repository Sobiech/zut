<%@ Page Title="MultiplyMatrix" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="MultiplyMatrix.aspx.cs" Inherits="MultiplyMatrix" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <h2><%: Title %>.</h2>

    <label for="FileUploadMatrix1">Macierz 1:</label>
    <asp:FileUpload runat="server" ID="FileUploadMatrix1" />
    <br />
    
    <label for="FileUploadMatrix2">Macierz 2:</label>
    <asp:FileUpload runat="server" ID="FileUploadMatrix2" />

    <br />
    <asp:Button runat="server" id="UploadButton" text="Prześlij" onclick="UploadButton_Click" />
    <br /><br />
    <asp:Label runat="server" id="StatusLabel" text="Status przesyłania: " />

    <br /><br />
    <asp:Label runat="server" ID="MatrixLabel1" />

    <br />
    <asp:Label runat="server" ID="MatrixLabel2" />

    <br />
    <asp:Label runat="server" ID="ResultMatrix" />
    <br /><br />
    <asp:Button ID="MatrixDownload" runat="server" OnClick="MatrixDownload_Click" Text="Pobierz plik" Enabled="false" />  
    <br />
    <asp:Label ID="MatrixDownloadInfo" runat="server"></asp:Label>  

</asp:Content>
