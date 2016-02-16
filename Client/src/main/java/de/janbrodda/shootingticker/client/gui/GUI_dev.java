/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janbrodda.shootingticker.client.gui;

import javax.swing.JPanel;

/**
 *
 * @author broddaja
 */
public class GUI_dev extends javax.swing.JFrame {
	
	private final EventHandler event = new EventHandler(this);
	
	public GUI_dev() {
		initComponents();
		event.init();
		this.setLocationRelativeTo(null);

		// limit textfield lengths
		newCompetitionDateDayField.setDocument(new JTextFieldLimit((2)));
		newCompetitionDateMonthField.setDocument(new JTextFieldLimit((2)));
		newCompetitionDateYearField.setDocument(new JTextFieldLimit((4)));
		newCompetitionDateHourField.setDocument(new JTextFieldLimit((2)));
		newCompetitionDateMinuteField.setDocument(new JTextFieldLimit((2)));
		
		competitionTimeMinutes.setEditable(false);
		competitionNumShots.setEditable(false);
		competitionTimeSeconds.setEditable(false);
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainContentTab = new javax.swing.JTabbedPane();
        uploadPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        remoteCompetitionDropdown = new javax.swing.JComboBox();
        localCompetitionDropdown = new javax.swing.JComboBox();
        competitionTimeMinutes = new javax.swing.JTextField();
        competitionNumShots = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnTimePlusTen = new javax.swing.JButton();
        btnTimeMinusTen = new javax.swing.JButton();
        btnTimePlusOne = new javax.swing.JButton();
        btnTimeMinusOne = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        competitionTimeSeconds = new javax.swing.JTextField();
        startCompetitionUploadButton = new javax.swing.JButton();
        stopCompetitionUploadButton = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        managePanel = new javax.swing.JPanel();
        managePanelCompetitionListPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        newCompetitionDateDayField = new javax.swing.JTextField();
        newCompetitionNameField = new javax.swing.JTextField();
        newCompetitionDateMonthField = new javax.swing.JTextField();
        newCompetitionDateYearField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        createCompetitionButton = new javax.swing.JButton();
        newCompetitionDateHourField = new javax.swing.JTextField();
        newCompetitionDateMinuteField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        apiUrl = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        proxyUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        competitionBasePath = new javax.swing.JTextField();
        apiKey = new javax.swing.JTextField();
        useProxy = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        proxyPass = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        proxyHost = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnSaveSettings = new javax.swing.JButton();
        proxyPort = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainContentTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mainContentTabStateChanged(evt);
            }
        });

        java.awt.GridBagLayout uploadPanelLayout = new java.awt.GridBagLayout();
        uploadPanelLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        uploadPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        uploadPanelLayout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        uploadPanel.setLayout(uploadPanelLayout);

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("Server Wettkampf");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        uploadPanel.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setText("Lokaler Wettkampf");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        uploadPanel.add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel14.setText("Wettkampf Info");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        uploadPanel.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 25;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        uploadPanel.add(remoteCompetitionDropdown, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 25;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        uploadPanel.add(localCompetitionDropdown, gridBagConstraints);

        competitionTimeMinutes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        competitionTimeMinutes.setText("0");
        competitionTimeMinutes.setPreferredSize(new java.awt.Dimension(30, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        uploadPanel.add(competitionTimeMinutes, gridBagConstraints);

        competitionNumShots.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        competitionNumShots.setText("0");
        competitionNumShots.setPreferredSize(new java.awt.Dimension(30, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        uploadPanel.add(competitionNumShots, gridBagConstraints);

        jLabel1.setText("Wettkampf-Zeit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        uploadPanel.add(jLabel1, gridBagConstraints);

        jLabel6.setText("Anzahl Schüsse:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        uploadPanel.add(jLabel6, gridBagConstraints);

        btnTimePlusTen.setText("+10");
        btnTimePlusTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimePlusTenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 8;
        uploadPanel.add(btnTimePlusTen, gridBagConstraints);

        btnTimeMinusTen.setText("-10");
        btnTimeMinusTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeMinusTenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 8;
        uploadPanel.add(btnTimeMinusTen, gridBagConstraints);

        btnTimePlusOne.setText("+1");
        btnTimePlusOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimePlusOneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 8;
        uploadPanel.add(btnTimePlusOne, gridBagConstraints);

        btnTimeMinusOne.setText("-1");
        btnTimeMinusOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeMinusOneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 8;
        uploadPanel.add(btnTimeMinusOne, gridBagConstraints);

        jLabel15.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        uploadPanel.add(jLabel15, gridBagConstraints);

        competitionTimeSeconds.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        competitionTimeSeconds.setText("0");
        competitionTimeSeconds.setPreferredSize(new java.awt.Dimension(30, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        uploadPanel.add(competitionTimeSeconds, gridBagConstraints);

        startCompetitionUploadButton.setText("Wettkampf starten");
        startCompetitionUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startCompetitionUploadButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        uploadPanel.add(startCompetitionUploadButton, gridBagConstraints);

        stopCompetitionUploadButton.setText("Wettkampf stoppen");
        stopCompetitionUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopCompetitionUploadButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        uploadPanel.add(stopCompetitionUploadButton, gridBagConstraints);

        jLabel22.setText("    ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        uploadPanel.add(jLabel22, gridBagConstraints);

        mainContentTab.addTab("Wettkampf hochladen", uploadPanel);

        managePanelCompetitionListPanel.setLayout(new java.awt.BorderLayout());

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel16.setText("Neuen Wettkampf anlegen");

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel17.setText("Datum:");

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel18.setText("Name:");

        newCompetitionDateDayField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        newCompetitionDateMonthField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        newCompetitionDateYearField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel19.setText(":");

        createCompetitionButton.setText("<html><center>Wettkampf erstellen</center></html>");
        createCompetitionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createCompetitionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCompetitionButtonActionPerformed(evt);
            }
        });

        newCompetitionDateHourField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        newCompetitionDateMinuteField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setText(".");

        jLabel21.setText(".");

        javax.swing.GroupLayout managePanelLayout = new javax.swing.GroupLayout(managePanel);
        managePanel.setLayout(managePanelLayout);
        managePanelLayout.setHorizontalGroup(
            managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(managePanelLayout.createSequentialGroup()
                        .addGroup(managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addGap(20, 20, 20)
                        .addGroup(managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(managePanelLayout.createSequentialGroup()
                                .addComponent(newCompetitionDateDayField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newCompetitionDateMonthField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newCompetitionDateYearField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(newCompetitionDateHourField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newCompetitionDateMinuteField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(newCompetitionNameField))
                        .addGap(18, 18, 18)
                        .addComponent(createCompetitionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(managePanelCompetitionListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        managePanelLayout.setVerticalGroup(
            managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(managePanelCompetitionListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addGap(6, 6, 6)
                .addGroup(managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(managePanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createCompetitionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(managePanelLayout.createSequentialGroup()
                                .addComponent(newCompetitionNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(managePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(newCompetitionDateDayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)
                                    .addComponent(newCompetitionDateMonthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21)
                                    .addComponent(newCompetitionDateYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newCompetitionDateHourField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(newCompetitionDateMinuteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))))
                .addContainerGap())
        );

        mainContentTab.addTab("Wettkämpfe verwalten", managePanel);

        java.awt.GridBagLayout settingsPanelLayout = new java.awt.GridBagLayout();
        settingsPanelLayout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        settingsPanel.setLayout(settingsPanelLayout);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Lokale Daten");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(apiUrl, gridBagConstraints);

        jLabel2.setText("Hostname:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(proxyUser, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Proxy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        settingsPanel.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(competitionBasePath, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(apiKey, gridBagConstraints);

        useProxy.setText("Proxy benutzen?");
        useProxy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useProxyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        settingsPanel.add(useProxy, gridBagConstraints);

        jLabel5.setText("Benutzer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel5, gridBagConstraints);

        jLabel7.setText("Passwort:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Daten-Verzeichnis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(proxyPass, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Server");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel9, gridBagConstraints);

        jLabel10.setText("Adresse:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel10, gridBagConstraints);

        jLabel11.setText("Passwort:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        settingsPanel.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(proxyHost, gridBagConstraints);

        jButton1.setText("Ändern");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        settingsPanel.add(jButton1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(jSeparator1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(jSeparator2, gridBagConstraints);

        btnSaveSettings.setText("Speichern");
        btnSaveSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSettingsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(btnSaveSettings, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        settingsPanel.add(proxyPort, gridBagConstraints);

        mainContentTab.addTab("Einstellungen", settingsPanel);

        getContentPane().add(mainContentTab, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createCompetitionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCompetitionButtonActionPerformed
		event.createCompetitionButtonPressed();
    }//GEN-LAST:event_createCompetitionButtonActionPerformed

    private void mainContentTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mainContentTabStateChanged
		JPanel activePanel = (JPanel) mainContentTab.getSelectedComponent();
		event.selectedTabChanged(activePanel);
    }//GEN-LAST:event_mainContentTabStateChanged

    private void useProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useProxyActionPerformed
		boolean checked = useProxy.isSelected();
		event.useProxyStateChanged(checked);
    }//GEN-LAST:event_useProxyActionPerformed

    private void btnSaveSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSettingsActionPerformed
		event.saveSettingsButtonPressed();
    }//GEN-LAST:event_btnSaveSettingsActionPerformed

    private void startCompetitionUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startCompetitionUploadButtonActionPerformed
		event.startCompetitionUploadButtonPressed();
    }//GEN-LAST:event_startCompetitionUploadButtonActionPerformed

    private void stopCompetitionUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopCompetitionUploadButtonActionPerformed
		event.stopCompetitionUploadButtonPressed();
    }//GEN-LAST:event_stopCompetitionUploadButtonActionPerformed

    private void btnTimePlusTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimePlusTenActionPerformed
		event.changeCompetitionUploadTime(+10);
    }//GEN-LAST:event_btnTimePlusTenActionPerformed

    private void btnTimeMinusTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeMinusTenActionPerformed
		event.changeCompetitionUploadTime(-10);
    }//GEN-LAST:event_btnTimeMinusTenActionPerformed

    private void btnTimePlusOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimePlusOneActionPerformed
		event.changeCompetitionUploadTime(+1);
    }//GEN-LAST:event_btnTimePlusOneActionPerformed

    private void btnTimeMinusOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeMinusOneActionPerformed
		event.changeCompetitionUploadTime(-1);
    }//GEN-LAST:event_btnTimeMinusOneActionPerformed
	
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI_dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI_dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI_dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI_dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI_dev().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField apiKey;
    public javax.swing.JTextField apiUrl;
    public javax.swing.JButton btnSaveSettings;
    public javax.swing.JButton btnTimeMinusOne;
    public javax.swing.JButton btnTimeMinusTen;
    public javax.swing.JButton btnTimePlusOne;
    public javax.swing.JButton btnTimePlusTen;
    public javax.swing.JTextField competitionBasePath;
    public javax.swing.JTextField competitionNumShots;
    public javax.swing.JTextField competitionTimeMinutes;
    public javax.swing.JTextField competitionTimeSeconds;
    public javax.swing.JButton createCompetitionButton;
    public javax.swing.JButton jButton1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JComboBox localCompetitionDropdown;
    public javax.swing.JTabbedPane mainContentTab;
    public javax.swing.JPanel managePanel;
    public javax.swing.JPanel managePanelCompetitionListPanel;
    public javax.swing.JTextField newCompetitionDateDayField;
    public javax.swing.JTextField newCompetitionDateHourField;
    public javax.swing.JTextField newCompetitionDateMinuteField;
    public javax.swing.JTextField newCompetitionDateMonthField;
    public javax.swing.JTextField newCompetitionDateYearField;
    public javax.swing.JTextField newCompetitionNameField;
    public javax.swing.JTextField proxyHost;
    public javax.swing.JPasswordField proxyPass;
    public javax.swing.JTextField proxyPort;
    public javax.swing.JTextField proxyUser;
    public javax.swing.JComboBox remoteCompetitionDropdown;
    public javax.swing.JPanel settingsPanel;
    public javax.swing.JButton startCompetitionUploadButton;
    public javax.swing.JButton stopCompetitionUploadButton;
    public javax.swing.JPanel uploadPanel;
    public javax.swing.JCheckBox useProxy;
    // End of variables declaration//GEN-END:variables
}
