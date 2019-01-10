<%@ Page Title="Mendelbrot" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Mendelbrot.aspx.cs" Inherits="Mendelbrot" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <h2><%: Title %>.</h2>
    <p>Utworzenie fraktalu mendelbrota</p>


    <br /><br />
    <label for="xCritical">Wprowadź punkt krytyczny zX (-1,1)</label>
    <asp:TextBox runat="server" Text="0,0" ID="xCritical"/>

    <br /><br />
    <label for="yCritical">Wprowadź punkt krytyczny zy (-1,1)</label>
    <asp:TextBox runat="server" Text="0,0" ID="yCritical"/>

    <br /><br />
    <label for="zRadius">Wprowadź promień (2, 100)</label>
    <asp:TextBox runat="server" Text="2,0" ID="radius"/>

    <br /><br />
    <label for="zRadius">Wprowadź ilość iteracji (100, 1000)</label>
    <asp:TextBox runat="server" Text="100" ID="iterationCount"/>

    <br />
    <asp:Button Text="Genertuj fraktal" runat="server" OnClick="GenerateMendelbrot" />

    <br /><br />
    <asp:Label runat="server" Text="Status:" ID="StatusLabel"/>



    <br /><br />
    <img runat="server" id="imgCtrl" />
    
</asp:Content>
