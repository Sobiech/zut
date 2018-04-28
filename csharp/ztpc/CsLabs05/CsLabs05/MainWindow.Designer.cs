namespace CsLabs05
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
            this.drawNormalBtn = new System.Windows.Forms.Button();
            this.clearPanelBtn = new System.Windows.Forms.Button();
            this.drawRectBtn = new System.Windows.Forms.Button();
            this.drawLineBtn = new System.Windows.Forms.Button();
            this.colorDialog1 = new System.Windows.Forms.ColorDialog();
            this.colorBtn = new System.Windows.Forms.Button();
            this.fontSizeCb = new System.Windows.Forms.ComboBox();
            this.colorDialog2 = new System.Windows.Forms.ColorDialog();
            this.drawElipseBtn = new System.Windows.Forms.Button();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.tabControl1.SuspendLayout();
            this.SuspendLayout();
            // 
            // logger
            // 
            this.logger.Location = new System.Drawing.Point(16, 508);
            this.logger.Margin = new System.Windows.Forms.Padding(4);
            this.logger.Multiline = true;
            this.logger.Name = "logger";
            this.logger.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.logger.Size = new System.Drawing.Size(1305, 255);
            this.logger.TabIndex = 1;
            // 
            // drawNormalBtn
            // 
            this.drawNormalBtn.Location = new System.Drawing.Point(16, 15);
            this.drawNormalBtn.Margin = new System.Windows.Forms.Padding(4);
            this.drawNormalBtn.Name = "drawNormalBtn";
            this.drawNormalBtn.Size = new System.Drawing.Size(220, 27);
            this.drawNormalBtn.TabIndex = 3;
            this.drawNormalBtn.Text = "Normal Mode";
            this.drawNormalBtn.UseVisualStyleBackColor = true;
            // 
            // clearPanelBtn
            // 
            this.clearPanelBtn.Location = new System.Drawing.Point(13, 445);
            this.clearPanelBtn.Margin = new System.Windows.Forms.Padding(4);
            this.clearPanelBtn.Name = "clearPanelBtn";
            this.clearPanelBtn.Size = new System.Drawing.Size(220, 52);
            this.clearPanelBtn.TabIndex = 4;
            this.clearPanelBtn.Text = "Clear";
            this.clearPanelBtn.UseVisualStyleBackColor = true;
            // 
            // drawRectBtn
            // 
            this.drawRectBtn.Location = new System.Drawing.Point(16, 85);
            this.drawRectBtn.Margin = new System.Windows.Forms.Padding(4);
            this.drawRectBtn.Name = "drawRectBtn";
            this.drawRectBtn.Size = new System.Drawing.Size(220, 27);
            this.drawRectBtn.TabIndex = 5;
            this.drawRectBtn.Text = "Rectangle Mode";
            this.drawRectBtn.UseVisualStyleBackColor = true;
            // 
            // drawLineBtn
            // 
            this.drawLineBtn.Location = new System.Drawing.Point(16, 50);
            this.drawLineBtn.Margin = new System.Windows.Forms.Padding(4);
            this.drawLineBtn.Name = "drawLineBtn";
            this.drawLineBtn.Size = new System.Drawing.Size(220, 27);
            this.drawLineBtn.TabIndex = 7;
            this.drawLineBtn.Text = "Line Mode";
            this.drawLineBtn.UseVisualStyleBackColor = true;
            // 
            // colorBtn
            // 
            this.colorBtn.BackColor = System.Drawing.SystemColors.Control;
            this.colorBtn.Location = new System.Drawing.Point(16, 155);
            this.colorBtn.Margin = new System.Windows.Forms.Padding(4);
            this.colorBtn.Name = "colorBtn";
            this.colorBtn.Size = new System.Drawing.Size(220, 27);
            this.colorBtn.TabIndex = 8;
            this.colorBtn.UseVisualStyleBackColor = false;
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
            this.fontSizeCb.Location = new System.Drawing.Point(16, 191);
            this.fontSizeCb.Margin = new System.Windows.Forms.Padding(4);
            this.fontSizeCb.Name = "fontSizeCb";
            this.fontSizeCb.Size = new System.Drawing.Size(217, 24);
            this.fontSizeCb.TabIndex = 9;
            // 
            // drawElipseBtn
            // 
            this.drawElipseBtn.Location = new System.Drawing.Point(16, 120);
            this.drawElipseBtn.Margin = new System.Windows.Forms.Padding(4);
            this.drawElipseBtn.Name = "drawElipseBtn";
            this.drawElipseBtn.Size = new System.Drawing.Size(217, 27);
            this.drawElipseBtn.TabIndex = 10;
            this.drawElipseBtn.Text = "Elipse Mode";
            this.drawElipseBtn.UseMnemonic = false;
            this.drawElipseBtn.UseVisualStyleBackColor = true;
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Location = new System.Drawing.Point(243, 15);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(1078, 486);
            this.tabControl1.TabIndex = 11;
            // 
            // tabPage1
            // 
            this.tabPage1.Location = new System.Drawing.Point(4, 25);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(1070, 457);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "tabPage1";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // tabPage2
            // 
            this.tabPage2.Location = new System.Drawing.Point(4, 25);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(1070, 457);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "tabPage2";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1339, 779);
            this.Controls.Add(this.tabControl1);
            this.Controls.Add(this.drawElipseBtn);
            this.Controls.Add(this.fontSizeCb);
            this.Controls.Add(this.colorBtn);
            this.Controls.Add(this.drawLineBtn);
            this.Controls.Add(this.drawRectBtn);
            this.Controls.Add(this.clearPanelBtn);
            this.Controls.Add(this.drawNormalBtn);
            this.Controls.Add(this.logger);
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "MainWindow";
            this.Text = "CsLabs05";
            this.tabControl1.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox logger;
        private System.Windows.Forms.Button drawNormalBtn;
        private System.Windows.Forms.Button clearPanelBtn;
        private System.Windows.Forms.Button drawRectBtn;
        private System.Windows.Forms.Button drawLineBtn;
        private System.Windows.Forms.ColorDialog colorDialog1;
        private System.Windows.Forms.Button colorBtn;
        private System.Windows.Forms.ComboBox fontSizeCb;
        private System.Windows.Forms.ColorDialog colorDialog2;
        private System.Windows.Forms.Button drawElipseBtn;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
    }
}

