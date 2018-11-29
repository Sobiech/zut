namespace lab4 {
    partial class Form1 {
        /// <summary>
        /// Wymagana zmienna projektanta.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Wyczyść wszystkie używane zasoby.
        /// </summary>
        /// <param name="disposing">prawda, jeżeli zarządzane zasoby powinny zostać zlikwidowane; Fałsz w przeciwnym wypadku.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && ( components != null )) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Kod generowany przez Projektanta formularzy systemu Windows

        /// <summary>
        /// Metoda wymagana do obsługi projektanta — nie należy modyfikować
        /// jej zawartości w edytorze kodu.
        /// </summary>
        private void InitializeComponent() {
            this.pbToAnalyse = new System.Windows.Forms.PictureBox();
            this.pbAnalysed = new System.Windows.Forms.PictureBox();
            this.btnLoadFile = new System.Windows.Forms.Button();
            this.btnAnalyse = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.pbToAnalyse)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbAnalysed)).BeginInit();
            this.SuspendLayout();
            // 
            // pbToAnalyse
            // 
            this.pbToAnalyse.BackColor = System.Drawing.SystemColors.ButtonShadow;
            this.pbToAnalyse.Location = new System.Drawing.Point(31, 48);
            this.pbToAnalyse.Name = "pbToAnalyse";
            this.pbToAnalyse.Size = new System.Drawing.Size(529, 470);
            this.pbToAnalyse.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pbToAnalyse.TabIndex = 0;
            this.pbToAnalyse.TabStop = false;
            // 
            // pbAnalysed
            // 
            this.pbAnalysed.BackColor = System.Drawing.SystemColors.ButtonShadow;
            this.pbAnalysed.Location = new System.Drawing.Point(612, 48);
            this.pbAnalysed.Name = "pbAnalysed";
            this.pbAnalysed.Size = new System.Drawing.Size(529, 470);
            this.pbAnalysed.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pbAnalysed.TabIndex = 1;
            this.pbAnalysed.TabStop = false;
            // 
            // btnLoadFile
            // 
            this.btnLoadFile.Location = new System.Drawing.Point(31, 12);
            this.btnLoadFile.Name = "btnLoadFile";
            this.btnLoadFile.Size = new System.Drawing.Size(75, 23);
            this.btnLoadFile.TabIndex = 2;
            this.btnLoadFile.Text = "Load file";
            this.btnLoadFile.UseVisualStyleBackColor = true;
            this.btnLoadFile.Click += new System.EventHandler(this.btnLoadFile_Click);
            // 
            // btnAnalyse
            // 
            this.btnAnalyse.Location = new System.Drawing.Point(112, 12);
            this.btnAnalyse.Name = "btnAnalyse";
            this.btnAnalyse.Size = new System.Drawing.Size(75, 23);
            this.btnAnalyse.TabIndex = 3;
            this.btnAnalyse.Text = "Analyse";
            this.btnAnalyse.UseVisualStyleBackColor = true;
            this.btnAnalyse.Click += new System.EventHandler(this.btnAnalyse_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1177, 530);
            this.Controls.Add(this.btnAnalyse);
            this.Controls.Add(this.btnLoadFile);
            this.Controls.Add(this.pbAnalysed);
            this.Controls.Add(this.pbToAnalyse);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.pbToAnalyse)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbAnalysed)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox pbToAnalyse;
        private System.Windows.Forms.PictureBox pbAnalysed;
        private System.Windows.Forms.Button btnLoadFile;
        private System.Windows.Forms.Button btnAnalyse;
    }
}

