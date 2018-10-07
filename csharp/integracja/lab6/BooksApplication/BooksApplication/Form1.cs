using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace BooksClient
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            BookManagerClient bm = new BookManagerClient();
            List<book> books = new List<book>();
            switch (comboBox1.SelectedIndex)
            {
                case 0:
                    string author = textBox1.Text;
                    if (!string.IsNullOrEmpty(author))
                    {
                        books.AddRange(bm.searchByAuthor(author));
                    }
                    break;
                case 1:
                    string title = textBox1.Text;
                    if (!string.IsNullOrEmpty(title))
                    {
                        books.AddRange(bm.searchByTitle(title));
                    }
                    break;
                case 2:
                    string isbn = textBox1.Text;
                    if (!string.IsNullOrEmpty(isbn))
                    {
                        books.Add(bm.searchByISBN(isbn));
                    }
                    break;
                default:
                    MessageBox.Show("Wybierz typ argumentu z listy");
                    break;
            }
            dataGridView1.DataSource = books;
        }

        private void button4_Click(object sender, EventArgs e)
        {
            BookManagerClient x = new BookManagerClient();
            int year;
            int pages;

            if (!Int32.TryParse(this.textBoxRok.Text, out year) || !Int32.TryParse(this.textBoxStrony.Text, out pages))
            {
                MessageBox.Show("W polach Strony oraz Rok muszą byc liczby!");
                return;
            }

            if (this.textBoxID.Text.Length == 0 || this.textBoxTytul.Text.Length == 0 || this.textBoxAutor.Text.Length == 0 || this.textBoxISBN.Text.Length == 0 || this.textBoxWydawca.Text.Length == 0)
            {
                MessageBox.Show("Żadne pole nie może pozostać puste!");
                return;
            }
            book newBook = new book();
            newBook.id = this.textBoxID.Text;
            newBook.title = this.textBoxTytul.Text;
            newBook.author = this.textBoxAutor.Text;
            newBook.isbn = this.textBoxISBN.Text;
            newBook.year = year;
            newBook.publisher = this.textBoxWydawca.Text;
            newBook.pages = pages;
            x.AddBook(newBook);
        }

        private void button3_Click_1(object sender, EventArgs e)
        {
            this.textBoxID.Text = string.Empty;
            this.textBoxTytul.Text = string.Empty;
            this.textBoxAutor.Text = string.Empty;
            this.textBoxISBN.Text = string.Empty;
            this.textBoxRok.Text = string.Empty;
            this.textBoxWydawca.Text = string.Empty;
            this.textBoxStrony.Text = string.Empty;
        }
    }
}
