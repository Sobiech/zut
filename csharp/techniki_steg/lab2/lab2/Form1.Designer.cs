namespace lab2
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.encodeMessageBtn = new System.Windows.Forms.Button();
            this.decodeMessageBtn = new System.Windows.Forms.Button();
            this.saveEncodedImageBtn = new System.Windows.Forms.Button();
            this.loadImageBtn = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.messageToEncode = new System.Windows.Forms.TextBox();
            this.tbPassword = new System.Windows.Forms.TextBox();
            this.textBox3 = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.pictureBox1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox1.Location = new System.Drawing.Point(21, 107);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(513, 338);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.Click += new System.EventHandler(this.pictureBox1_Click);
            // 
            // pictureBox2
            // 
            this.pictureBox2.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.pictureBox2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox2.Location = new System.Drawing.Point(588, 107);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(513, 338);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox2.TabIndex = 1;
            this.pictureBox2.TabStop = false;
            this.pictureBox2.Click += new System.EventHandler(this.pictureBox2_Click);
            // 
            // encodeMessageBtn
            // 
            this.encodeMessageBtn.Location = new System.Drawing.Point(403, 21);
            this.encodeMessageBtn.Name = "encodeMessageBtn";
            this.encodeMessageBtn.Size = new System.Drawing.Size(131, 23);
            this.encodeMessageBtn.TabIndex = 2;
            this.encodeMessageBtn.Text = "Encode Message";
            this.encodeMessageBtn.UseVisualStyleBackColor = true;
            this.encodeMessageBtn.Click += new System.EventHandler(this.encodeMessage_Click);
            // 
            // decodeMessageBtn
            // 
            this.decodeMessageBtn.Location = new System.Drawing.Point(403, 51);
            this.decodeMessageBtn.Name = "decodeMessageBtn";
            this.decodeMessageBtn.Size = new System.Drawing.Size(131, 23);
            this.decodeMessageBtn.TabIndex = 3;
            this.decodeMessageBtn.Text = "Decode Message";
            this.decodeMessageBtn.UseVisualStyleBackColor = true;
            this.decodeMessageBtn.Click += new System.EventHandler(this.decodeMessage_Click);
            // 
            // saveEncodedImageBtn
            // 
            this.saveEncodedImageBtn.Location = new System.Drawing.Point(102, 21);
            this.saveEncodedImageBtn.Name = "saveEncodedImageBtn";
            this.saveEncodedImageBtn.Size = new System.Drawing.Size(75, 23);
            this.saveEncodedImageBtn.TabIndex = 4;
            this.saveEncodedImageBtn.Text = "Save Image";
            this.saveEncodedImageBtn.UseVisualStyleBackColor = true;
            this.saveEncodedImageBtn.Click += new System.EventHandler(this.saveEncodedImage_Click);
            // 
            // loadImageBtn
            // 
            this.loadImageBtn.Location = new System.Drawing.Point(21, 21);
            this.loadImageBtn.Name = "loadImageBtn";
            this.loadImageBtn.Size = new System.Drawing.Size(75, 23);
            this.loadImageBtn.TabIndex = 5;
            this.loadImageBtn.Text = "Load Image";
            this.loadImageBtn.UseVisualStyleBackColor = true;
            this.loadImageBtn.Click += new System.EventHandler(this.loadImage_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 37);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(104, 13);
            this.label1.TabIndex = 6;
            this.label1.Text = "Message to encode:";
            // 
            // messageToEncode
            // 
            this.messageToEncode.Location = new System.Drawing.Point(115, 34);
            this.messageToEncode.Name = "messageToEncode";
            this.messageToEncode.Size = new System.Drawing.Size(100, 20);
            this.messageToEncode.TabIndex = 7;
            // 
            // tbPassword
            // 
            this.tbPassword.Location = new System.Drawing.Point(115, 12);
            this.tbPassword.Name = "tbPassword";
            this.tbPassword.Size = new System.Drawing.Size(100, 20);
            this.tbPassword.TabIndex = 8;
            // 
            // textBox3
            // 
            this.textBox3.Location = new System.Drawing.Point(351, 12);
            this.textBox3.Name = "textBox3";
            this.textBox3.Size = new System.Drawing.Size(100, 20);
            this.textBox3.TabIndex = 9;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(42, 15);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(68, 13);
            this.label2.TabIndex = 10;
            this.label2.Text = "Passphrase :";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(240, 15);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(105, 13);
            this.label3.TabIndex = 11;
            this.label3.Text = "Steganographic key:";
            // 
            // panel1
            // 
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.messageToEncode);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.tbPassword);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Controls.Add(this.textBox3);
            this.panel1.Location = new System.Drawing.Point(588, 21);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(513, 71);
            this.panel1.TabIndex = 12;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1125, 465);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.loadImageBtn);
            this.Controls.Add(this.saveEncodedImageBtn);
            this.Controls.Add(this.decodeMessageBtn);
            this.Controls.Add(this.encodeMessageBtn);
            this.Controls.Add(this.pictureBox2);
            this.Controls.Add(this.pictureBox1);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.Button encodeMessageBtn;
        private System.Windows.Forms.Button decodeMessageBtn;
        private System.Windows.Forms.Button saveEncodedImageBtn;
        private System.Windows.Forms.Button loadImageBtn;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox messageToEncode;
        private System.Windows.Forms.TextBox tbPassword;
        private System.Windows.Forms.TextBox textBox3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Panel panel1;
    }
}

