namespace CsLabs04
{
    partial class MainWindow
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
            this.logger = new System.Windows.Forms.TextBox();
            this.normalDrawBtn = new System.Windows.Forms.Button();
            this.clearPanelBtn = new System.Windows.Forms.Button();
            this.drawRectBtn = new System.Windows.Forms.Button();
            this.drawPointBtn = new System.Windows.Forms.Button();
            this.drawLineBtn = new System.Windows.Forms.Button();
            this.colorDialog1 = new System.Windows.Forms.ColorDialog();
            this.colorBtn = new System.Windows.Forms.Button();
            this.fontSizeCb = new System.Windows.Forms.ComboBox();
            this.colorDialog2 = new System.Windows.Forms.ColorDialog();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.tabControl1.SuspendLayout();
            this.SuspendLayout();
            // 
            // logger
            // 
            this.logger.Location = new System.Drawing.Point(12, 413);
            this.logger.Multiline = true;
            this.logger.Name = "logger";
            this.logger.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.logger.Size = new System.Drawing.Size(980, 208);
            this.logger.TabIndex = 1;
            // 
            // normalDrawBtn
            // 
            this.normalDrawBtn.Location = new System.Drawing.Point(12, 12);
            this.normalDrawBtn.Name = "normalDrawBtn";
            this.normalDrawBtn.Size = new System.Drawing.Size(165, 42);
            this.normalDrawBtn.TabIndex = 3;
            this.normalDrawBtn.Text = "Normal";
            this.normalDrawBtn.UseVisualStyleBackColor = true;
            // 
            // clearPanelBtn
            // 
            this.clearPanelBtn.Location = new System.Drawing.Point(12, 365);
            this.clearPanelBtn.Name = "clearPanelBtn";
            this.clearPanelBtn.Size = new System.Drawing.Size(165, 42);
            this.clearPanelBtn.TabIndex = 4;
            this.clearPanelBtn.Text = "Clear";
            this.clearPanelBtn.UseVisualStyleBackColor = true;
            this.clearPanelBtn.Click += new System.EventHandler(this.clearPanelBtn_Click);
            // 
            // drawRectBtn
            // 
            this.drawRectBtn.Location = new System.Drawing.Point(12, 156);
            this.drawRectBtn.Name = "drawRectBtn";
            this.drawRectBtn.Size = new System.Drawing.Size(165, 42);
            this.drawRectBtn.TabIndex = 5;
            this.drawRectBtn.Text = "Draw Rectangles";
            this.drawRectBtn.UseVisualStyleBackColor = true;
            // 
            // drawPointBtn
            // 
            this.drawPointBtn.Location = new System.Drawing.Point(12, 108);
            this.drawPointBtn.Name = "drawPointBtn";
            this.drawPointBtn.Size = new System.Drawing.Size(165, 42);
            this.drawPointBtn.TabIndex = 6;
            this.drawPointBtn.Text = "Draw Point";
            this.drawPointBtn.UseVisualStyleBackColor = true;
            // 
            // drawLineBtn
            // 
            this.drawLineBtn.Location = new System.Drawing.Point(12, 60);
            this.drawLineBtn.Name = "drawLineBtn";
            this.drawLineBtn.Size = new System.Drawing.Size(165, 42);
            this.drawLineBtn.TabIndex = 7;
            this.drawLineBtn.Text = "Draw Line";
            this.drawLineBtn.UseVisualStyleBackColor = true;
            // 
            // colorBtn
            // 
            this.colorBtn.BackColor = System.Drawing.SystemColors.Control;
            this.colorBtn.Location = new System.Drawing.Point(12, 214);
            this.colorBtn.Name = "colorBtn";
            this.colorBtn.Size = new System.Drawing.Size(165, 23);
            this.colorBtn.TabIndex = 8;
            this.colorBtn.UseVisualStyleBackColor = false;
            this.colorBtn.Click += new System.EventHandler(this.colorBtn_Click);
            // 
            // fontSizeCb
            // 
            this.fontSizeCb.BackColor = System.Drawing.SystemColors.ScrollBar;
            this.fontSizeCb.FormattingEnabled = true;
            this.fontSizeCb.Items.AddRange(new object[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14"});
            this.fontSizeCb.Location = new System.Drawing.Point(13, 244);
            this.fontSizeCb.Name = "fontSizeCb";
            this.fontSizeCb.Size = new System.Drawing.Size(164, 21);
            this.fontSizeCb.TabIndex = 9;
            this.fontSizeCb.SelectedIndexChanged += new System.EventHandler(this.fontSizeIndex_Changed);
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Location = new System.Drawing.Point(183, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(809, 395);
            this.tabControl1.TabIndex = 0;
            this.tabControl1.SelectedIndexChanged += new System.EventHandler(this.changedTabPage);
            // 
            // tabPage1
            // 
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(801, 369);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "tabPage1";
            this.tabPage1.UseVisualStyleBackColor = true;
            this.tabPage1.Paint += new System.Windows.Forms.PaintEventHandler(this.EventPaint);
            this.tabPage1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.EventMouseDown);
            this.tabPage1.MouseMove += new System.Windows.Forms.MouseEventHandler(this.EventMouseMove);
            this.tabPage1.MouseUp += new System.Windows.Forms.MouseEventHandler(this.EventMouseUp);
            // 
            // tabPage2
            // 
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(801, 369);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "tabPage2";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1004, 633);
            this.Controls.Add(this.tabControl1);
            this.Controls.Add(this.fontSizeCb);
            this.Controls.Add(this.colorBtn);
            this.Controls.Add(this.drawLineBtn);
            this.Controls.Add(this.drawPointBtn);
            this.Controls.Add(this.drawRectBtn);
            this.Controls.Add(this.clearPanelBtn);
            this.Controls.Add(this.normalDrawBtn);
            this.Controls.Add(this.logger);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "MainWindow";
            this.Text = "CsLabs04";
            this.tabControl1.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox logger;
        private System.Windows.Forms.Button normalDrawBtn;
        private System.Windows.Forms.Button clearPanelBtn;
        private System.Windows.Forms.Button drawRectBtn;
        private System.Windows.Forms.Button drawPointBtn;
        private System.Windows.Forms.Button drawLineBtn;
        private System.Windows.Forms.ColorDialog colorDialog1;
        private System.Windows.Forms.Button colorBtn;
        private System.Windows.Forms.ComboBox fontSizeCb;
        private System.Windows.Forms.ColorDialog colorDialog2;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
    }
}

