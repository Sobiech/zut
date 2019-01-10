<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="row">
        <div class="col-md-12">
            <h2>Mnożenie macierzy</h2>
            <p>Rozwiązanie pozwala na mnożenie macierzy z wykorzystaniem zwykłej przeglądarki. Stworzona jest strona aspx powalająca na przesłanie dwóch macierzy zapisanych w plikach na serwer. Po stronie serwera pliki zostają odczytane i wykonane mnożenie macierzy. Jako odpowiedź ma zostać zwrócona strona internetowa zawierająca plik z wynikiem mnożenia oraz czasem wykonania operacji. Mnożenie macierzy odbywa się z wykorzystaniem biblioteki Task Parallel Library (TPL).</p>        
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h2>Generowanie fraktalu Mendelbrota</h2>
            <p>Komunikacja analogicznie jak dla podbunktu a – róznica polega na przesłaniu odpowiednich parametrów do serwera webowego i zwróceniu wyników.</p>
        </div>
    </div>
</asp:Content>
