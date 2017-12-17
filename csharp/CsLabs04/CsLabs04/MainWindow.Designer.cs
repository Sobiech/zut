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
            this.drawablePanel = new System.Windows.Forms.Panel();
            this.normalDrawBtn = new System.Windows.Forms.Button();
            this.clearPanelBtn = new System.Windows.Forms.Button();
            this.drawRectBtn = new System.Windows.Forms.Button();
            this.drawLineBtn = new System.Windows.Forms.Button();
            this.colorDialog1 = new System.Windows.Forms.ColorDialog();
            this.colorBtn = new System.Windows.Forms.Button();
            this.fontSizeCb = new System.Windows.Forms.ComboBox();
            this.colorDialog2 = new System.Windows.Forms.ColorDialog();
            this.button1 = new System.Windows.Forms.Button();
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
            // drawablePanel
            // 
            this.drawablePanel.Location = new System.Drawing.Point(244, 15);
            this.drawablePanel.Margin = new System.Windows.Forms.Padding(4);
            this.drawablePanel.Name = "drawablePanel";
            this.drawablePanel.Size = new System.Drawing.Size(1079, 486);
            this.drawablePanel.TabIndex = 2;
            this.drawablePanel.Paint += new System.Windows.Forms.PaintEventHandler(this.panelPaintEvent);
            this.drawablePanel.MouseDown += new System.Windows.Forms.MouseEventHandler(this.EventMouseDown);
            this.drawablePanel.MouseMove += new System.Windows.Forms.MouseEventHandler(this.EventMouseMove);
            this.drawablePanel.MouseUp += new System.Windows.Forms.MouseEventHandler(this.EventMouseUp);
            // 
            // normalDrawBtn
            // 
            this.normalDrawBtn.Location = new System.Drawing.Point(16, 15);
            this.normalDrawBtn.Margin = new System.Windows.Forms.Padding(4);
            this.normalDrawBtn.Name = "normalDrawBtn";
            this.normalDrawBtn.Size = new System.Drawing.Size(220, 52);
            this.normalDrawBtn.TabIndex = 3;
            this.normalDrawBtn.Text = "Normal Mode";
            this.normalDrawBtn.UseVisualStyleBackColor = true;
            this.normalDrawBtn.Click += new System.EventHandler(this.activateNormalMode);
            // 
            // clearPanelBtn
            // 
            this.clearPanelBtn.Location = new System.Drawing.Point(16, 449);
            this.clearPanelBtn.Margin = new System.Windows.Forms.Padding(4);
            this.clearPanelBtn.Name = "clearPanelBtn";
            this.clearPanelBtn.Size = new System.Drawing.Size(220, 52);
            this.clearPanelBtn.TabIndex = 4;
            this.clearPanelBtn.Text = "Clear";
            this.clearPanelBtn.UseVisualStyleBackColor = true;
            this.clearPanelBtn.Click += new System.EventHandler(this.clearPanelEvent);
            // 
            // drawRectBtn
            // 
            this.drawRectBtn.Location = new System.Drawing.Point(16, 134);
            this.drawRectBtn.Margin = new System.Windows.Forms.Padding(4);
            this.drawRectBtn.Name = "drawRectBtn";
            this.drawRectBtn.Size = new System.Drawing.Size(220, 52);
            this.drawRectBtn.TabIndex = 5;
            this.drawRectBtn.Text = "Rectangle Mode";
            this.drawRectBtn.UseVisualStyleBackColor = true;
            this.drawRectBtn.Click += new System.EventHandler(this.activateRectangleMode);
            // 
            // drawLineBtn
            // 
            this.drawLineBtn.Location = new System.Drawing.Point(16, 74);
            this.drawLineBtn.Margin = new System.Windows.Forms.Padding(4);
            this.drawLineBtn.Name = "drawLineBtn";
            this.drawLineBtn.Size = new System.Drawing.Size(220, 52);
            this.drawLineBtn.TabIndex = 7;
            this.drawLineBtn.Text = "Line Mode";
            this.drawLineBtn.UseVisualStyleBackColor = true;
            this.drawLineBtn.Click += new System.EventHandler(this.activateLineMode);
            // 
            // colorBtn
            // 
            this.colorBtn.BackColor = System.Drawing.SystemColors.Control;
            this.colorBtn.Location = new System.Drawing.Point(16, 272);
            this.colorBtn.Margin = new System.Windows.Forms.Padding(4);
            this.colorBtn.Name = "colorBtn";
            this.colorBtn.Size = new System.Drawing.Size(220, 28);
            this.colorBtn.TabIndex = 8;
            this.colorBtn.UseVisualStyleBackColor = false;
            this.colorBtn.Click += new System.EventHandler(this.changePenColor);
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
            this.fontSizeCb.Location = new System.Drawing.Point(19, 308);
            this.fontSizeCb.Margin = new System.Windows.Forms.Padding(4);
            this.fontSizeCb.Name = "fontSizeCb";
            this.fontSizeCb.Size = new System.Drawing.Size(217, 24);
            this.fontSizeCb.TabIndex = 9;
            this.fontSizeCb.SelectedIndexChanged += new System.EventHandler(this.changeFontSize);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(16, 194);
            this.button1.Margin = new System.Windows.Forms.Padding(4);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(220, 52);
            this.button1.TabIndex = 10;
            this.button1.Text = "Elipse Mode";
            this.button1.UseMnemonic = false;
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.activateElipseMode);
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1339, 779);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.fontSizeCb);
            this.Controls.Add(this.colorBtn);
            this.Controls.Add(this.drawLineBtn);
            this.Controls.Add(this.drawRectBtn);
            this.Controls.Add(this.clearPanelBtn);
            this.Controls.Add(this.normalDrawBtn);
            this.Controls.Add(this.drawablePanel);
            this.Controls.Add(this.logger);
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "MainWindow";
            this.Text = "CsLabs04";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox logger;
        private System.Windows.Forms.Panel drawablePanel;
        private System.Windows.Forms.Button normalDrawBtn;
        private System.Windows.Forms.Button clearPanelBtn;
        private System.Windows.Forms.Button drawRectBtn;
        private System.Windows.Forms.Button drawLineBtn;
        private System.Windows.Forms.ColorDialog colorDialog1;
        private System.Windows.Forms.Button colorBtn;
        private System.Windows.Forms.ComboBox fontSizeCb;
        private System.Windows.Forms.ColorDialog colorDialog2;
        private System.Windows.Forms.Button button1;
    }
}

