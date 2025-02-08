/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {

    private JTabbedPane tabbedPane;
    private JToolBar toolBar;

    public MainForm() {
        setTitle("Houseware Store Inventory");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the toolbar (initially hidden)
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setVisible(false); // Hide the toolbar initially

        // Add buttons to the toolbar
        JButton openCustomerFormButton = new JButton("Open Customer Form");
        openCustomerFormButton.addActionListener(e -> openForm("Customer", new CustomerForm()));
        toolBar.add(openCustomerFormButton);

        JButton openSalesFormButton = new JButton("Open Sales Form");
        openSalesFormButton.addActionListener(e -> openForm("Sales", new SalesForm()));
        toolBar.add(openSalesFormButton);

        JButton UserFormButton = new JButton("Open User Form");
        UserFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openForm("User", new UserForm());
            }
        });
        toolBar.add(UserFormButton);

        // Add the toolbar to the main form
        add(toolBar, BorderLayout.NORTH);

        // Create the tabbed pane
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        // Add a menu bar with a Logout menu item
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(e -> logout());
        fileMenu.add(logoutMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Show the LoginForm on startup (without a title)
        showLoginForm();
    }

    private void showLoginForm() {
        // Create and add the LoginForm to the tabbed pane
        LoginForm loginForm = new LoginForm(this);
        tabbedPane.addTab("", loginForm); // No title for the login tab

        // Disable tab closing for the LoginForm
        int loginIndex = tabbedPane.indexOfComponent(loginForm);
        tabbedPane.setEnabledAt(loginIndex, false); // Disable the tab
    }

    public void onLoginSuccess() {
        // Remove the LoginForm
        tabbedPane.removeAll();

        // Show the DashboardForm
        addDashboardTab();

        // Show the toolbar
        toolBar.setVisible(true);
    }

    private void addDashboardTab() {
        // Create the Dashboard Form
        DashboardForm dashboardForm = new DashboardForm();

        // Add the Dashboard Form to the tabbed pane
        tabbedPane.addTab("Dashboard", dashboardForm);

        // Disable closing for the Dashboard tab
        int dashboardIndex = tabbedPane.indexOfTab("Dashboard");
        tabbedPane.setEnabledAt(dashboardIndex, true); // Ensure the tab is enabled
    }

    private void openForm(String title, JPanel form) {
        // Check if the form is already open
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            if (tabbedPane.getTitleAt(i).equals(title)) {
                // Switch to the existing tab
                tabbedPane.setSelectedIndex(i);
                return;
            }
        }

        // If the form is not open, create a new tab
        tabbedPane.addTab(title, form);

        // Add a close button to the tab (except for the Dashboard tab)
        int tabIndex = tabbedPane.getTabCount() - 1;
        if (!title.equals("Dashboard")) {
            JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            tabPanel.setOpaque(false);

            JLabel tabLabel = new JLabel(title);
            JButton tabCloseButton = new JButton("x");
            tabCloseButton.setMargin(new Insets(0, 0, 0, 0));
            tabCloseButton.setBorderPainted(false);
            tabCloseButton.setContentAreaFilled(false);
            tabCloseButton.setFocusPainted(false);
            tabCloseButton.addActionListener(e -> {
                int indexToClose = tabbedPane.indexOfTabComponent(tabPanel);
                if (indexToClose != -1) {
                    tabbedPane.removeTabAt(indexToClose);
                }
            });

            tabPanel.add(tabLabel);
            tabPanel.add(tabCloseButton);
            tabbedPane.setTabComponentAt(tabIndex, tabPanel);
        }

        // Select the new tab
        tabbedPane.setSelectedIndex(tabIndex);
    }

    private void logout() {
        // Remove all tabs from the tabbed pane
        tabbedPane.removeAll();

        // Hide the toolbar
        toolBar.setVisible(false);

        // Show the LoginForm
        showLoginForm();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm().setVisible(true));
    }
}
