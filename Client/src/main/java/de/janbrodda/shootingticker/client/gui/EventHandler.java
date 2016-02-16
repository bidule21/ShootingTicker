package de.janbrodda.shootingticker.client.gui;

import de.janbrodda.shootingticker.client.app.App;
import de.janbrodda.shootingticker.client.data.Competition;
import de.janbrodda.shootingticker.client.settings.Settings;
import de.janbrodda.shootingticker.client.settings.SettingsValidator;
import de.janbrodda.shootingticker.client.settings.ValidationResult;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.StringUtils;

public class EventHandler {

	private final GUI_dev gui;
	private final App app = App.get();

	public EventHandler(GUI_dev gui) {
		this.gui = gui;
	}

	public void init() {
		setCompetitionUploadTime(200);
	}

	private void invokeLater(final Runnable runnable) {
		new Thread() {
			@Override
			public void run() {
				runnable.run();
			}
		}.start();

		//SwingUtilities.invokeLater(runnable);
	}

	public void saveSettingsButtonPressed() {
		Settings s = Settings.get();
		s.competitionBasePath = gui.competitionBasePath.getText();
		s.apiUrl = gui.apiUrl.getText();
		s.apiKey = gui.apiKey.getText();
		s.proxyHost = gui.proxyHost.getText();
		if (StringUtils.isNumeric(gui.proxyPort.getText())) {
			s.proxyPort = Integer.parseInt(gui.proxyPort.getText());
		}
		s.proxyPass = new String(gui.proxyPass.getPassword());
		s.proxyUser = gui.proxyUser.getText();
		s.useProxy = gui.useProxy.isSelected();

		ValidationResult validationResult = SettingsValidator.validate(s);
		if (!validationResult.isValid) {
			JOptionPane.showMessageDialog(gui, validationResult.message);
		} else {
			s.save();
			JOptionPane.showMessageDialog(gui, "Settings saved");
		}
	}

	public void createCompetitionButtonPressed() {
		int year = Integer.parseInt(gui.newCompetitionDateYearField.getText());
		int month = Integer.parseInt(gui.newCompetitionDateMonthField.getText());
		int day = Integer.parseInt(gui.newCompetitionDateDayField.getText());
		int hour = Integer.parseInt(gui.newCompetitionDateHourField.getText());
		int minute = Integer.parseInt(gui.newCompetitionDateMinuteField.getText());
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hour, minute, 0);
		Date date = calendar.getTime();

		final Competition competition = new Competition();
		competition.name = gui.newCompetitionNameField.getText();
		competition.date = date;
		competition.numShots = 0;

		invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					app.saveCompetition(competition);
					selectedTabChanged(gui.managePanel);
				} catch (IOException ex) {
					Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
					//showPopup("Can't send Competition to Server");
				}
			}
		});
	}

	public void selectedTabChanged(final JPanel activePanel) {
		invokeLater(new Runnable() {
			@Override
			public void run() {

				if (activePanel == gui.managePanel) {
					blockForm();
					refreshManageTab();
					unblockForm();
				} else if (activePanel == gui.settingsPanel) {
					refreshSettingsTab();
				} else if (activePanel == gui.uploadPanel) {
					blockForm();
					refreshUploadTab();
					unblockForm();
				}

			}
		});
	}

	public void useProxyStateChanged(boolean checked) {
		gui.proxyHost.setEnabled(checked);
		gui.proxyPort.setEnabled(checked);
		gui.proxyUser.setEnabled(checked);
		gui.proxyPass.setEnabled(checked);
	}

	private int getCompetitionUploadTime() {
		int minutes = Integer.parseInt(gui.competitionTimeMinutes.getText());
		int seconds = Integer.parseInt(gui.competitionTimeSeconds.getText());

		return minutes * 60 + seconds;
	}

	private void setCompetitionUploadTime(int time) {
		int minutes = (int) Math.floor(time / 60);
		int seconds = time % 60;

		gui.competitionTimeMinutes.setText(minutes + "");
		gui.competitionTimeSeconds.setText(seconds + "");
	}

	private void refreshUploadTab() {

	}

	private void refreshManageTab() {
		gui.managePanelCompetitionListPanel.removeAll();

		try {
			final String[] columnNames = {"#", "Name", "Date", ""};

			final List<Competition> competitions = app.getRemoteCompetitions();
			List<Object[]> tableData = new ArrayList<>();

			for (Competition competition : competitions) {
				Object[] rowData = new Object[columnNames.length];
				rowData[0] = competition.id;
				rowData[1] = competition.name;
				//TODO add date to data model
				rowData[2] = competition.timestamp;

				rowData[columnNames.length - 1] = "Delete";
				tableData.add(rowData);
			}

			Object[][] data = new Object[tableData.size()][];
			for (int i = 0; i < tableData.size(); i++) {
				data[i] = tableData.get(i);
			}

			// anonymous class overwrite is necessary for cell editing
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			JTable table = new JTable(model) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// returns true for last column, this should be the button
					return column == columnNames.length - 1;
				}

			};
			table.setModel(model);

			// action for delete button press
			Action delete = new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int modelRow = Integer.valueOf(e.getActionCommand());
					Competition competition = competitions.get(modelRow);

					boolean deleteConfirmed
							= JOptionPane.showConfirmDialog(gui,
									"Wettkampf <" + competition.name + "> löschen?",
									"",
									JOptionPane.YES_NO_OPTION)
							== JOptionPane.YES_OPTION;

					if (deleteConfirmed) {
						try {
							app.deleteCompetition(competition);
							selectedTabChanged(gui.managePanel);
						} catch (IOException ex) {
							Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
							//showPopup("Can't delete Competition from Server");
							//TODO
						}
					}
				}
			};

			ButtonColumn buttonColumn = new ButtonColumn(table, delete, columnNames.length - 1);
			buttonColumn.setMnemonic(KeyEvent.VK_D);

			JScrollPane container = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			gui.managePanelCompetitionListPanel.add(container);
		} catch (IOException ex) {
			//showPopup("Can't load Competitions from Server");
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void refreshSettingsTab() {
		Settings s = Settings.get();
		gui.apiUrl.setText(s.apiUrl);
		gui.apiKey.setText(s.apiKey);
		gui.proxyHost.setText(s.proxyHost);
		gui.proxyUser.setText(s.proxyUser);
		gui.proxyPass.setText(s.proxyPass);
		gui.proxyPort.setText(s.proxyPort + "");
		gui.competitionBasePath.setText(s.competitionBasePath);
		gui.competitionBasePath.setToolTipText(s.competitionBasePath);
		gui.useProxy.setSelected(s.useProxy);
		useProxyStateChanged(s.useProxy);
	}

	private void blockForm() {
		changeContainerVisibility(gui, false);
	}

	private void unblockForm() {
		changeContainerVisibility(gui, true);
	}

	private void changeContainerVisibility(Container rootComponent, boolean visible) {
		for (Component component : rootComponent.getComponents()) {
			if (component instanceof Container) {
				changeContainerVisibility((Container) component, visible);
			}

			component.setEnabled(visible);
		}
	}
}
