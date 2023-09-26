package com.fareco.simul;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "file:simul.properties" })
public class SimulStarter extends JFrame {

//	private SimulWebSocket simulWs;
	public static void main(String[] args) {
		SpringApplication.run(SimulStarter.class, args);
	}

	public SimulStarter(String[] args) {

//		initUI();
	}

	private void initUI() {

		JButton quitButton = new JButton("Quit");

		quitButton.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		JComponent[] args = { quitButton };
		createLayout(args);

		setTitle("Quit button");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent[] arg) {

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));

		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));
	}
}
