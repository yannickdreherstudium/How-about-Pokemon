package draftpanels;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import client.MainMenu;
import client.Manage;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DraftGui {

	private static DraftGui window;
	private JFrame frmPokemonDraft;
	private PanelMenu panelMenu;
	private PanelStartDraft panelStartDraft;
	private PanelLoadDraft panelLoadDraft;
	private PanelTierlist panelTierlist;
	private PanelDraft panelDraft;
	private PanelPlayer panelPlayer;
	private PanelSettings panelSettings;
	private PanelOrder panelOrder;
	private JPanel panelloading;
	private PanelAfterDraft panelAfterDraft;
	private boolean finishlayout = false;

	public static void startPokemonDrafting() {
		EventQueue.invokeLater(() -> {
			try {
				Manage.msgboxErfolg("Pre-release" + "\n" + "You can test all valid functions in the program" + "\n"
						+ "Please contact me and send me all files of the program if an error occurs.", null);

				window = new DraftGui();
				window.frmPokemonDraft.setVisible(true);
			} catch (Exception e) {
				try {
					e.printStackTrace(
							new PrintWriter(new BufferedWriter(new FileWriter("Error" + ".txt", true)), true));
					Manage.msgboxError("Ein Error ist aufgetreten und gespeichert", null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static DraftGui getwindow() {
		return MainMenu.getPokemonDraft();
	}

	public DraftGui() {
		frmPokemonDraft = new JFrame();
		frmPokemonDraft.setTitle("Pokemon Draft        Alpha by Tronic44");
		frmPokemonDraft.setResizable(false);
		frmPokemonDraft.setBounds(100, 100, 409, 640);
		frmPokemonDraft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPokemonDraft.getContentPane().setLayout(new CardLayout(0, 0));

		initmenubar();
		initpanel();
	}

	private void initpanel() {
		panelMenu = new PanelMenu();
		frmPokemonDraft.getContentPane().add(panelMenu, "name_526992955635103");
		panelMenu.setLayout(null);
		panelMenu.setVisible(true);

		panelStartDraft = new PanelStartDraft();
		frmPokemonDraft.getContentPane().add(panelStartDraft, "name_527021172921335");
		panelStartDraft.setVisible(false);
		panelStartDraft.setLayout(null);

		panelLoadDraft = new PanelLoadDraft();
		frmPokemonDraft.getContentPane().add(panelLoadDraft, "name_527666975961040");
		panelLoadDraft.setLayout(null);
		panelLoadDraft.setVisible(false);

		panelTierlist = new PanelTierlist();
		frmPokemonDraft.getContentPane().add(panelTierlist, "name_2032838076395");
		panelTierlist.setLayout(null);
		panelTierlist.setVisible(false);

		panelPlayer = new PanelPlayer();
		frmPokemonDraft.getContentPane().add(panelPlayer, "name_601620298180688");
		panelPlayer.setVisible(false);
		panelPlayer.setLayout(null);

		panelSettings = new PanelSettings();
		frmPokemonDraft.getContentPane().add(panelSettings, "name_601636768594680");
		panelSettings.setVisible(false);
		panelSettings.setLayout(null);

		panelOrder = new PanelOrder();
		frmPokemonDraft.getContentPane().add(panelOrder, "name_601652242106220");
		panelOrder.setVisible(false);
		panelOrder.setLayout(null);

		panelDraft = new PanelDraft();
		frmPokemonDraft.getContentPane().add(panelDraft, "name_2679324427935");
		panelDraft.setLayout(null);
		panelDraft.setVisible(false);

		panelloading = new JPanel();
		frmPokemonDraft.getContentPane().add(panelloading, "name_910613970759788");
		initloading();

		panelAfterDraft = new PanelAfterDraft();
		frmPokemonDraft.getContentPane().add(panelAfterDraft, "name_1868052027276522");
		panelAfterDraft.setLayout(null);
		panelAfterDraft.setVisible(false);
	}

	private void initloading() {
		JLabel lblLabel = new JLabel("Loading");
		lblLabel.setBounds(148, 40, 107, 33);
		lblLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		JLabel lblNewLabel = new JLabel("Wenn du das lesen kannst, hast du ein Problem. Versusche es bitte erneut");
		lblNewLabel.setBounds(28, 79, 500, 14);
		panelloading.setLayout(null);
		panelloading.add(lblLabel);
		panelloading.add(lblNewLabel);
		panelloading.setVisible(false);
	}

	private void initmenubar() {

		JMenuBar menuBar = new JMenuBar();
		frmPokemonDraft.setJMenuBar(menuBar);

		JButton btnMainmenu = new JButton("MainMen\u00FC");
		btnMainmenu.addActionListener(e -> {
			if (panelMenu.isVisible()) {
				client.MainMenu.getwindow().visMainMenu();
			} else {
				visMenu();
			}
		});
		menuBar.add(btnMainmenu);

		JButton btnmenuback = new JButton("zur\u00FCck");
		btnmenuback.addActionListener(e -> {
			if (panelMenu.isVisible()) {
				MainMenu.getwindow().visMainMenu();
			}
			if (panelStartDraft.isVisible() || panelLoadDraft.isVisible()) {
				visMenu();
			}
			if (panelTierlist.isVisible() || panelSettings.isVisible() || panelOrder.isVisible()
					|| panelDraft.isVisible()) {
				visStartDraft();
			}
			if (panelAfterDraft.isVisible()) {
				visMenu();
			}
			if (panelPlayer.isVisible()) {
				if (!getPanelPlayer().isSafed())
					Manage.msgboxErfolg("Beachte: Du hast ungespeicherte Änderung",
							DraftGui.getwindow().frmPokemonDraft);
				visStartDraft();
			}
			frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), 409, 640);
		});
		menuBar.add(btnmenuback);

		JButton btnDebug = new JButton("Debug");
		btnDebug.addActionListener(e -> {
			try {
				frmPokemonDraft.list(new PrintWriter(new BufferedWriter(new FileWriter("debug" + ".txt", true)), true));
				Manage.msgboxErfolg("Aktion Erfoglreich Gespeichert", frmPokemonDraft);
			} catch (IOException e1) {
				Manage.msgboxError("konnte nicht gespeichert werden", frmPokemonDraft);
			}
		});
		menuBar.add(btnDebug);
	}

	public JFrame getFrmPokemonDraft() {
		return frmPokemonDraft;
	}

	public PanelMenu getPanelMainMenu() {
		return panelMenu;
	}

	public PanelStartDraft getPanelStartDraft() {
		return panelStartDraft;
	}

	public PanelLoadDraft getPanelLoadDraft() {
		return panelLoadDraft;
	}

	public PanelTierlist getPanelTierlist() {
		return panelTierlist;
	}

	public PanelDraft getPanelDraft() {
		return panelDraft;
	}

	public PanelPlayer getPanelPlayer() {
		return panelPlayer;
	}

	public PanelSettings getPanelSettings() {
		return panelSettings;
	}

	public PanelOrder getPanelOrder() {
		return panelOrder;
	}

	public PanelAfterDraft getPanelAfterDraft() {
		return panelAfterDraft;
	}

	public boolean isFinishlayout() {
		return finishlayout;
	}

	public void setFinishlayout(boolean b) {
		finishlayout = b;
	}

	protected void visStartDraft() {
		visLoading();
		if (isFinishlayout()) {
			panelStartDraft.deaktivatebtnReihenfolge();
		}
		if (panelDraft.finishdrafting) {
			visAfterDraft();
			return;
		}
		frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), 409, 640);
		panelStartDraft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visLoadDraft() {
		visLoading();
		panelLoadDraft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visMenu() {
		visLoading();
		frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), 409, 640);
		if (isFinishlayout()) {
			panelMenu.renamebtn("Open Draft");
		} else {
			panelMenu.renamebtn("Start Draft");
			panelMenu.setVisible(true);
		}
		panelloading.setVisible(false);
	}

	protected void visPlayer() {
		visLoading();
		DraftGui.getwindow().getPanelPlayer().reloadTeamlist();
		panelPlayer.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visTierlist() {
		visLoading();
		getPanelTierlist().openTierlist();
		panelTierlist.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visSettings() {
		visLoading();
		if (finishlayout) {
			panelSettings.disablecrtiticalchanges();
		}
		panelSettings.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visOrder() {
		visLoading();
		panelOrder.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visDraft() {
		visLoading();
		DraftGui.getwindow().getPanelDraft().opendraft();
		panelDraft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visAfterDraft(String[][] draftergebniss) {
		visLoading();
		frmPokemonDraft.getContentPane().remove(panelAfterDraft);
		panelAfterDraft = new PanelAfterDraft(draftergebniss);
		frmPokemonDraft.getContentPane().add(panelAfterDraft, "name_601634568594680");
		visAfterDraft();
	}

	protected void visAfterDraft() {
		visLoading();
		frmPokemonDraft.setBounds(frmPokemonDraft.getX(), frmPokemonDraft.getY(), getPanelAfterDraft().getwidth(), 400);
		panelAfterDraft.setVisible(true);
		panelloading.setVisible(false);
	}

	protected void visLoading() {
		panelTierlist.setVisible(false);
		panelLoadDraft.setVisible(false);
		panelStartDraft.setVisible(false);
		panelPlayer.setVisible(false);
		panelSettings.setVisible(false);
		panelOrder.setVisible(false);
		panelDraft.setVisible(false);
		panelMenu.setVisible(false);
		panelAfterDraft.setVisible(false);
		panelloading.setVisible(true);
	}
}