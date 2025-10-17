package bank.view;

import bank.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView {
    private Scene scene;
    private LoginController loginController;
    private Runnable onLoginSuccess;
    private Runnable onShowRegister;

    private TextField usernameField;
    private PasswordField passwordField;
    private Label errorLabel;

    public LoginView(LoginController loginController, Runnable onLoginSuccess, Runnable onShowRegister) {
        this.loginController = loginController;
        this.onLoginSuccess = onLoginSuccess;
        this.onShowRegister = onShowRegister;
        createView();
    }

    private void createView() {
        
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #f7fafc;");

        
        VBox leftSection = createLeftSection();
        mainLayout.setLeft(leftSection);

        
        VBox rightSection = createRightSection();
        mainLayout.setCenter(rightSection);

        scene = new Scene(mainLayout, 1000, 600);
    }

    private VBox createLeftSection() {
        VBox leftSection = new VBox(30);
        leftSection.setAlignment(Pos.CENTER);
        leftSection.setPadding(new Insets(40));
        leftSection.setPrefWidth(400);
        leftSection.setStyle("-fx-background-color: white; -fx-border-color: #e2e8f0; -fx-border-width: 0 1px 0 0;");

        
        VBox logoContainer = new VBox(10);
        logoContainer.setAlignment(Pos.CENTER);

        Label bankLogo = new Label("🏦");
        bankLogo.setFont(Font.font(48));

        Label bankName = new Label("Bank");
        bankName.setFont(Font.font("System", FontWeight.BOLD, 32));
        bankName.setTextFill(Color.web("#2D3748"));

        Label bankTagline = new Label("Secure Banking Solutions");
        bankTagline.setFont(Font.font("System", 16));
        bankTagline.setTextFill(Color.web("#718096"));

        logoContainer.getChildren().addAll(bankLogo, bankName, bankTagline);

        
        VBox featuresContainer = new VBox(15);
        featuresContainer.setAlignment(Pos.CENTER_LEFT);

        Label featuresTitle = new Label("Why Bank With Us?");
        featuresTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        featuresTitle.setTextFill(Color.web("#2D3748"));

        VBox featureList = new VBox(10);

        Label feature1 = createFeatureItem("💰", "Savings Accounts with 0.05% interest");
        Label feature2 = createFeatureItem("📈", "Investment Accounts with 5% returns");
        Label feature3 = createFeatureItem("💳", "Cheque Accounts for salary deposits");
        Label feature4 = createFeatureItem("🔒", "Secure & Protected Banking");

        featureList.getChildren().addAll(feature1, feature2, feature3, feature4);
        featuresContainer.getChildren().addAll(featuresTitle, featureList);

        leftSection.getChildren().addAll(logoContainer, new Separator(), featuresContainer);
        return leftSection;
    }

    private Label createFeatureItem(String emoji, String text) {
        HBox featureBox = new HBox(10);
        featureBox.setAlignment(Pos.CENTER_LEFT);

        Label emojiLabel = new Label(emoji);
        emojiLabel.setFont(Font.font(16));

        Label textLabel = new Label(text);
        textLabel.setFont(Font.font("System", 14));
        textLabel.setTextFill(Color.web("#4A5568"));
        textLabel.setWrapText(true);

        featureBox.getChildren().addAll(emojiLabel, textLabel);

        
        Label container = new Label();
        container.setGraphic(featureBox);
        container.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        return container;
    }

    private VBox createRightSection() {
        VBox rightSection = new VBox(30);
        rightSection.setAlignment(Pos.CENTER);
        rightSection.setPadding(new Insets(40));
        rightSection.setMaxWidth(400);

        // Welcome section
        VBox welcomeContainer = new VBox(10);
        welcomeContainer.setAlignment(Pos.CENTER);

        Label welcomeTitle = new Label("Welcome Back");
        welcomeTitle.setFont(Font.font("System", FontWeight.BOLD, 28));
        welcomeTitle.setTextFill(Color.web("#2D3748"));

        Label welcomeSubtitle = new Label("Sign in to your account to continue");
        welcomeSubtitle.setFont(Font.font("System", 16));
        welcomeSubtitle.setTextFill(Color.web("#718096"));

        welcomeContainer.getChildren().addAll(welcomeTitle, welcomeSubtitle);

        // Login form
        VBox formContainer = new VBox(20);
        formContainer.setAlignment(Pos.CENTER);

        // Username field
        VBox usernameBox = new VBox(5);
        usernameBox.setAlignment(Pos.CENTER_LEFT);

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        usernameLabel.setTextFill(Color.web("#4A5568"));

        usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle("-fx-pref-height: 45px; -fx-padding: 0 15px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");

        usernameBox.getChildren().addAll(usernameLabel, usernameField);

        // Password field
        VBox passwordBox = new VBox(5);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        passwordLabel.setTextFill(Color.web("#4A5568"));

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-pref-height: 45px; -fx-padding: 0 15px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");

        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        // Error label
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
        errorLabel.setWrapText(true);
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setMaxWidth(Double.MAX_VALUE);

        // Login button
        Button loginButton = new Button("Sign In");
        loginButton.setStyle("-fx-pref-height: 50px; -fx-background-color: #667eea; -fx-background-radius: 8px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-cursor: hand;");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnAction(e -> handleLogin());

        // Register link 
        HBox registerBox = new HBox(5);
        registerBox.setAlignment(Pos.CENTER);

        Label registerText = new Label("Don't have an account?");
        registerText.setFont(Font.font("System", 14));
        registerText.setTextFill(Color.web("#718096"));

        Hyperlink registerLink = new Hyperlink("Register Account"); // CHANGED TEXT
        registerLink.setStyle("-fx-text-fill: #667eea; -fx-border-color: transparent; -fx-underline: false; -fx-font-size: 14px; -fx-font-weight: bold;");
        registerLink.setOnMouseEntered(e -> registerLink.setStyle("-fx-text-fill: #5a6fd8; -fx-underline: true; -fx-font-size: 14px; -fx-font-weight: bold;"));
        registerLink.setOnMouseExited(e -> registerLink.setStyle("-fx-text-fill: #667eea; -fx-underline: false; -fx-font-size: 14px; -fx-font-weight: bold;"));
        registerLink.setOnAction(e -> onShowRegister.run());

        registerBox.getChildren().addAll(registerText, registerLink);

        formContainer.getChildren().addAll(
                usernameBox, passwordBox, errorLabel, loginButton, registerBox
        );

        rightSection.getChildren().addAll(welcomeContainer, formContainer);
        return rightSection;
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }

        try {
            if (loginController.login(username, password) != null) {
                System.out.println("Login successful for: " + username);
                onLoginSuccess.run();
            } else {
                showError("Invalid username or password");
            }
        } catch (Exception e) {
            showError("Login failed: " + e.getMessage());
        }
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public Scene getScene() {
        return scene;
    }

    public void clearForm() {
        usernameField.clear();
        passwordField.clear();
        errorLabel.setVisible(false);
    }
}

