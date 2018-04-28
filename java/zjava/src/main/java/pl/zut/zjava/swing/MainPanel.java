package pl.zut.zjava.swing;

import pl.zut.zjava.entity.Dyrektor;
import pl.zut.zjava.entity.Handlowiec;
import pl.zut.zjava.entity.Pracownik;
import pl.zut.zjava.entity.service.DyrektorServiceImpl;
import pl.zut.zjava.entity.service.HandlowiecServiceImpl;
import pl.zut.zjava.entity.service.PracownikServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainPanel {

	private JPanel contentPane;
	private JPanel workerPane;
	private JPanel traderPane;
	private JPanel directorPane;


	private JTabbedPane tabbedPanel;


	private JTextField workerName;
	private JTextField workerLastName;
	private JTextField workerId;
	private JTextField workerSalary;
	private JTextField workerPlace;
	private JTextField workerMsisdn;

	private JTextField traderLastName;
	private JTextField traderFirstName;
	private JTextField traderId;
	private JTextField traderSalary;
	private JTextField traderPlace;
	private JTextField traderMsisdn;
	private JTextField traderLimit;
	private JTextField traderProvision;

	private JTextField directorLastName;
	private JTextField directorFirstName;
	private JTextField directorId;
	private JTextField directorSalary;
	private JTextField directorPlace;
	private JTextField directorMsisdn;
	private JTextField directorDodatek;
	private JTextField directorCard;
	private JTextField directorLimit;


	private JButton saveWorkerButton;
	private JButton deleteWorkerButton;

	private JButton deleteTraderButton;
	private JButton saveTraderButton;

	private JButton deleteDirectorButton;
	private JButton saveDirectorButton;


	private JComboBox<Pracownik> workerComboBox;
	private JComboBox<Handlowiec> traderComboBox;
	private JComboBox<Dyrektor> directorComboBox;
	private JButton serializeWorkerButton;
	private JButton serializeTraderButton;
	private JButton serializeDirectorButton;


	private PracownikServiceImpl pracownikService;
	private DyrektorServiceImpl dyrektorService;
	private HandlowiecServiceImpl handlowiecService;

	private JFileChooser fileChooser;

	MainPanel() {

		try {
			fileChooser = new JFileChooser();
			pracownikService = new PracownikServiceImpl();
			dyrektorService = new DyrektorServiceImpl();
			handlowiecService = new HandlowiecServiceImpl();

			this.initializeComboBoxes();

			initializeSaveButtonListeners();
			initializeDeleteButtonListeners();
			initializeComboBoxListeners();
			initializeSerializeButtonListener();
		} catch ( Exception e ){
			showError(e);
		}
	}


	private void initializeComboBoxes() throws SQLException {
		initializeWorkerList();
		initializeTraderList();
		initializeDirectorList();
	}

	/** inicjalizacja list dla itemow w comboboxliscie **/

	private void initializeTraderList() throws SQLException {
		List<Handlowiec> list = new ArrayList<>();
		list.add(new Handlowiec());
		list.addAll(handlowiecService.getList());
		list.forEach(traderComboBox::addItem);
	}

	private void initializeDirectorList() throws SQLException {
		List<Dyrektor> list = new ArrayList<>();
		list.add(new Dyrektor());
		list.addAll(dyrektorService.getList());
		list.forEach(directorComboBox::addItem);
	}

	private void initializeWorkerList() throws SQLException {
		List<Pracownik> list = new ArrayList<>();
		list.add(new Pracownik());
		list.addAll(pracownikService.getList());
		list.forEach(workerComboBox::addItem);
	}


	/** inicjalizacja listenerow dla przycisku SAVE / UPDATE **/

	private void initializeSaveButtonListeners() {

		saveWorkerButton.addActionListener(e -> {

			saveWorkerButton.setEnabled(false);

			Integer selectedIndex = workerComboBox.getSelectedIndex();
			try {
				Pracownik pracownik = (Pracownik) workerComboBox.getSelectedItem();
				if (isModified(pracownik)) {
					pracownik = getData(Objects.requireNonNull(pracownik));
					if (selectedIndex.equals(0)) {
						pracownikService.save(pracownik);
						workerComboBox.removeAllItems();
						initializeWorkerList();
					} else {
						pracownikService.update(pracownik);
					}
				}
			} catch ( Exception e1 ){
				showError(e1);
			}

			saveWorkerButton.setEnabled(true);
		});

		saveDirectorButton.addActionListener(e -> {

			saveDirectorButton.setEnabled(false);
			Integer selectedIndex = directorComboBox.getSelectedIndex();
			try {
				Dyrektor dyrektor = (Dyrektor) directorComboBox.getSelectedItem();
				if (isModified(dyrektor)) {

					dyrektor = getData(Objects.requireNonNull(dyrektor));
					if (selectedIndex.equals(0)) {
						dyrektorService.save(dyrektor);
						directorComboBox.removeAllItems();
						initializeDirectorList();
					} else {
						dyrektorService.update(dyrektor);
					}
				}
			} catch ( Exception e1 ){
				showError(e1);
			}

			saveDirectorButton.setEnabled(true);
		});

		saveTraderButton.addActionListener(e -> {

			saveTraderButton.setEnabled(false);
			Integer selectedIndex = traderComboBox.getSelectedIndex();
			try {
				Handlowiec handlowiec = (Handlowiec) traderComboBox.getSelectedItem();
				if (isModified(handlowiec)) {

					handlowiec = getData(Objects.requireNonNull(handlowiec));
					if (selectedIndex.equals(0)) {
						handlowiecService.save(handlowiec);
						traderComboBox.removeAllItems();
						initializeTraderList();
					} else {
						handlowiecService.update(handlowiec);
					}
				}
			} catch ( Exception e1 ){
				showError(e1);
			}

			saveTraderButton.setEnabled(true);
		});

	}

	private void initializeSerializeButtonListener() {

		serializeWorkerButton.addActionListener(e -> {
			serializeWorkerButton.setEnabled(false);
			Integer selectedIndex = workerComboBox.getSelectedIndex();
			if (!selectedIndex.equals(0)) {
				saveSelectedObject((Pracownik) workerComboBox.getSelectedItem());
			}
			serializeWorkerButton.setEnabled(true);
		});

		serializeTraderButton.addActionListener(e -> {
			serializeTraderButton.setEnabled(false);
			Integer selectedIndex = traderComboBox.getSelectedIndex();
			if (!selectedIndex.equals(0)) {
				saveSelectedObject((Handlowiec) traderComboBox.getSelectedItem());
			}
			serializeTraderButton.setEnabled(true);
		});

		serializeDirectorButton.addActionListener(e -> {
			serializeDirectorButton.setEnabled(false);
			Integer selectedIndex = directorComboBox.getSelectedIndex();
			if (!selectedIndex.equals(0)) {
				saveSelectedObject((Dyrektor) directorComboBox.getSelectedItem());
			}
			serializeDirectorButton.setEnabled(true);
		});
	}


	private void saveSelectedObject(Serializable serializable) {

		if (fileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			String message = String.format("Saving data: %s to file:%s", serializable.toString(), file.getAbsolutePath());
			System.out.println(message);

			ObjectOutputStream out = null;

			try {
				out = new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(serializable);
				out.close();
				showInfo("Plik zostal zapisany. Sciezka do pliku:  " + file.getAbsolutePath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	/** inicjalziacja listenerow dla przycisku DELETE **/

	private void initializeDeleteButtonListeners() {

		deleteDirectorButton.addActionListener(e -> {

			try {
				Dyrektor dyrektor = (Dyrektor) directorComboBox.getSelectedItem();
				dyrektorService.delete(dyrektor);
				directorComboBox.removeItem(dyrektor);
			} catch (Exception e1 ) {
				showError(e1);
			}

		});

		deleteWorkerButton.addActionListener(e -> {

			try {
				Pracownik pracownik = (Pracownik) workerComboBox.getSelectedItem();
				pracownikService.delete(pracownik);
				workerComboBox.removeItem(pracownik);
			} catch (Exception e1 ) {
				showError(e1);
			}
		});

		deleteTraderButton.addActionListener(e -> {

			try {
				Handlowiec handlowiec= (Handlowiec) traderComboBox.getSelectedItem();
				handlowiecService.delete(handlowiec);
				traderComboBox.removeItem(handlowiec);
			} catch (Exception e1 ) {
				showError(e1);
			}
		});
	}


	/** inicjalizacja listenerow dla drop down listy **/

	private void initializeComboBoxListeners() {

		workerComboBox.addActionListener(e -> {
			Boolean isIndexEqualZero = workerComboBox.getSelectedIndex() == 0;
			this.workerId.setEditable(isIndexEqualZero);
			this.serializeWorkerButton.setEnabled(!isIndexEqualZero);
			this.deleteWorkerButton.setEnabled(!isIndexEqualZero);
			Pracownik pracownik = (Pracownik)((JComboBox) e.getSource()).getSelectedItem();
			if ( !Objects.isNull(pracownik)) {
				setData(pracownik);
			}
		} );


		directorComboBox.addActionListener(e -> {
			Boolean isIndexEqualZero = directorComboBox.getSelectedIndex() == 0;
			this.directorId.setEditable(isIndexEqualZero);
			this.serializeDirectorButton.setEnabled(!isIndexEqualZero);
			this.deleteDirectorButton.setEnabled(!isIndexEqualZero);
			Dyrektor dyrektor = (Dyrektor) ((JComboBox) e.getSource()).getSelectedItem();
			if ( !Objects.isNull(dyrektor)) {
				setData(dyrektor);
			}
		});


		traderComboBox.addActionListener(e -> {
			Boolean isIndexEqualZero = traderComboBox.getSelectedIndex() == 0;
			this.traderId.setEditable(isIndexEqualZero);
			this.serializeTraderButton.setEnabled(!isIndexEqualZero);
			this.deleteTraderButton.setEnabled(!isIndexEqualZero);
			Handlowiec handlowiec = (Handlowiec)((JComboBox) e.getSource()).getSelectedItem();
			if ( !Objects.isNull(handlowiec)) {
				setData(handlowiec);
			}
		});
	}

	JPanel getContentPane() {
		return this.contentPane;
	}



	/** getery i settery dla obiektow typu textfield **/

	private void setData(Pracownik data) {
		workerLastName.setText(data.getNazwisko());
		workerName.setText(data.getImie());
		workerId.setText(data.getPesel());
		workerSalary.setText(data.getSalary());
		workerPlace.setText(data.getStanowisko());
		workerMsisdn.setText(data.getTelefon());
	}

	private Pracownik getData(Pracownik data) {
		data.setNazwisko(workerLastName.getText());
		data.setImie(workerName.getText());
		data.setPesel(workerId.getText());
		data.setSalary(workerSalary.getText());
		data.setStanowisko(workerPlace.getText());
		data.setTelefon(workerMsisdn.getText());

		return data;
	}

	private boolean isModified(Pracownik data) {
		if (workerLastName.getText() != null ? !workerLastName.getText().equals(data.getNazwisko()) : data.getNazwisko() != null)
			return true;
		if (workerName.getText() != null ? !workerName.getText().equals(data.getImie()) : data.getImie() != null)
			return true;
		if (workerId.getText() != null ? !workerId.getText().equals(data.getPesel()) : data.getPesel() != null)
			return true;
		if (workerSalary.getText() != null ? !workerSalary.getText().equals(data.getSalary()) : data.getSalary() != null)
			return true;
		if (workerPlace.getText() != null ? !workerPlace.getText().equals(data.getStanowisko()) : data.getStanowisko() != null)
			return true;
		return workerMsisdn.getText() != null ? !workerMsisdn.getText().equals(data.getTelefon()) : data.getTelefon() != null;
	}


	private void setData(Dyrektor data) {
		directorLastName.setText(data.getNazwisko());
		directorFirstName.setText(data.getImie());
		directorId.setText(data.getPesel());
		directorSalary.setText(data.getSalary());
		directorPlace.setText(data.getStanowisko());
		directorMsisdn.setText(data.getTelefon());
		directorDodatek.setText(data.getDodatekSluzbowyStr());
		directorCard.setText(data.getKartaSluzbowaStr());
		directorLimit.setText(data.getLimitKosztowStr());
	}

	private Dyrektor getData(Dyrektor data) {
		data.setNazwisko(directorLastName.getText());
		data.setImie(directorFirstName.getText());
		data.setPesel(directorId.getText());
		data.setSalary(directorSalary.getText());
		data.setStanowisko(directorPlace.getText());
		data.setTelefon(directorMsisdn.getText());
		data.setDodatekSluzbowyStr(directorDodatek.getText());
		data.setKartaSluzbowaStr(directorCard.getText());
		data.setLimitKosztowStr(directorLimit.getText());
		return data;
	}

	private boolean isModified(Dyrektor data) {
		if (directorLastName.getText() != null ? !directorLastName.getText().equals(data.getNazwisko()) : data.getNazwisko() != null)
			return true;
		if (directorFirstName.getText() != null ? !directorFirstName.getText().equals(data.getImie()) : data.getImie() != null)
			return true;
		if (directorId.getText() != null ? !directorId.getText().equals(data.getPesel()) : data.getPesel() != null)
			return true;
		if (directorSalary.getText() != null ? !directorSalary.getText().equals(data.getSalary()) : data.getSalary() != null)
			return true;
		if (directorPlace.getText() != null ? !directorPlace.getText().equals(data.getStanowisko()) : data.getStanowisko() != null)
			return true;
		if (directorMsisdn.getText() != null ? !directorMsisdn.getText().equals(data.getTelefon()) : data.getTelefon() != null)
			return true;
		if (directorDodatek.getText() != null ? !directorDodatek.getText().equals(data.getDodatekSluzbowyStr()) : data.getDodatekSluzbowyStr() != null)
			return true;
		if (directorCard.getText() != null ? !directorCard.getText().equals(data.getKartaSluzbowaStr()) : data.getKartaSluzbowaStr() != null)
			return true;
		return directorLimit.getText() != null ? !directorLimit.getText().equals(data.getLimitKosztowStr()) : data.getLimitKosztowStr() != null;
	}

	private void setData(Handlowiec data) {
		traderLastName.setText(data.getNazwisko());
		traderFirstName.setText(data.getImie());
		traderId.setText(data.getPesel());
		traderSalary.setText(data.getSalary());
		traderPlace.setText(data.getStanowisko());
		traderMsisdn.setText(data.getTelefon());
		traderLimit.setText(data.getLimitProwizjiStr());
		traderProvision.setText(data.getStawkaProwizjiStr());
	}

	private Handlowiec getData(Handlowiec data) {
		data.setNazwisko(traderLastName.getText());
		data.setImie(traderFirstName.getText());
		data.setPesel(traderId.getText());
		data.setSalary(traderSalary.getText());
		data.setStanowisko(traderPlace.getText());
		data.setTelefon(traderMsisdn.getText());
		data.setLimitProwizjiStr(traderLimit.getText());
		data.setStawkaProwizjiStr(traderProvision.getText());
		return data;
	}

	private boolean isModified(Handlowiec data) {
		if (traderLastName.getText() != null ? !traderLastName.getText().equals(data.getNazwisko()) : data.getNazwisko() != null)
			return true;
		if (traderFirstName.getText() != null ? !traderFirstName.getText().equals(data.getImie()) : data.getImie() != null)
			return true;
		if (traderId.getText() != null ? !traderId.getText().equals(data.getPesel()) : data.getPesel() != null)
			return true;
		if (traderSalary.getText() != null ? !traderSalary.getText().equals(data.getSalary()) : data.getSalary() != null)
			return true;
		if (traderPlace.getText() != null ? !traderPlace.getText().equals(data.getStanowisko()) : data.getStanowisko() != null)
			return true;
		if (traderMsisdn.getText() != null ? !traderMsisdn.getText().equals(data.getTelefon()) : data.getTelefon() != null)
			return true;
		if (traderLimit.getText() != null ? !traderLimit.getText().equals(data.getLimitProwizjiStr()) : data.getLimitProwizjiStr() != null)
			return true;
		return traderProvision.getText() != null ? !traderProvision.getText().equals(data.getStawkaProwizjiStr()) : data.getStawkaProwizjiStr() != null;
	}


	private void showError(Exception e) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String message = sw.toString();

		if ( e instanceof NumberFormatException ) {
			message = "Wprowadzono niepoprawne dane!";
		}

		JOptionPane.showMessageDialog(new JFrame(),
				message,
				"ERROR",
				JOptionPane.ERROR_MESSAGE);
	}


	private void showInfo(String message) {
		JOptionPane.showMessageDialog(new JFrame(),
				message,
				"Info",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
