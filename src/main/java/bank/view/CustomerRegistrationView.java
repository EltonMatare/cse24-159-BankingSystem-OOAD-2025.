package bank.view;

import bank.controller.LoginController;
import bank.model.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomerRegistrationView {
    private Scene scene;
    private LoginController loginController;
    private Runnable onRegistrationSuccess;
    private Runnable onShowLogin;

    private TextField firstNameField;
    private TextField surnameField;
    private TextArea addressField;
    private TextField usernameField;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private ComboBox<String> employmentCombo;
    private ComboBox<String> branchCombo; 
    private TextField companyNameField;
    private TextArea companyAddressField;
    private Label errorLabel;

    public CustomerRegistrationView(LoginController loginController,
                                    Runnable onRegistrationSuccess, Runnable onShowLogin) {
        this.loginController = loginController;
        this.onRegistrationSuccess = onRegistrationSuccess;
        this.onShowLogin = onShowLogin;
        createView();
    }

    private void createView() {
        
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #f7fafc;");

        
        VBox leftSection = createLeftSection();
        mainLayout.setLeft(leftSection);

        
        VBox rightSection = createRightSection();
        mainLayout.setCenter(rightSection);

        scene = new Scene(mainLayout, 1200, 700);
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

        Label featuresTitle = new Label("Create Your Account");
        featuresTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        featuresTitle.setTextFill(Color.web("#2D3748"));

        VBox featureList = new VBox(10);

        Label feature1 = createFeatureItem("👤", "Personal & Business Accounts");
        Label feature2 = createFeatureItem("🏢", "Multiple Branch Locations");
        Label feature3 = createFeatureItem("💳", "Instant Account Number");
        Label feature4 = createFeatureItem("🔒", "Secure Registration Process");

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
        VBox rightSection = new VBox(20);
        rightSection.setAlignment(Pos.CENTER);
        rightSection.setPadding(new Insets(40));
        rightSection.setMaxWidth(600);

        
        Label titleLabel = new Label("Register New Account");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#2D3748"));

       
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);

        VBox formContainer = new VBox(15);
        formContainer.setPadding(new Insets(20));
        formContainer.setMaxWidth(550);

       
        Label personalSection = new Label("Personal Information");
        personalSection.setFont(Font.font("System", FontWeight.BOLD, 18));

        
        HBox nameBox = new HBox(15);
        VBox firstNameBox = new VBox(5);
        Label firstNameLabel = new Label("First Name *");
        firstNameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        firstNameField = new TextField();
        firstNameField.setPromptText("Enter your first name");
        firstNameField.setStyle("-fx-pref-height: 40px; -fx-padding: 0 12px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");
        firstNameBox.getChildren().addAll(firstNameLabel, firstNameField);

        VBox surnameBox = new VBox(5);
        Label surnameLabel = new Label("Surname *");
        surnameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        surnameField = new TextField();
        surnameField.setPromptText("Enter your surname");
        surnameField.setStyle("-fx-pref-height: 40px; -fx-padding: 0 12px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");
        surnameBox.getChildren().addAll(surnameLabel, surnameField);

        nameBox.getChildren().addAll(firstNameBox, surnameBox);

        // Address
        VBox addressBox = new VBox(5);
        Label addressLabel = new Label("Address *");
        addressLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        addressField = new TextArea();
        addressField.setPromptText("Enter your full address");
        addressField.setPrefRowCount(3);
        addressField.setStyle("-fx-background-color: white; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-padding: 8px 12px;");
        addressBox.getChildren().addAll(addressLabel, addressField);

        // Branch Selection 
        VBox branchBox = new VBox(5);
        Label branchLabel = new Label("Preferred Branch *");
        branchLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        branchCombo = new ComboBox<>();
        branchCombo.getItems().addAll("Main Branch - Gaborone", "Francistown Branch", "Maun Branch", "Palapye Branch", "Serowe Branch");
        branchCombo.setValue("Main Branch - Gaborone");
        branchCombo.setStyle("-fx-pref-height: 40px; -fx-background-color: white; -fx-border-color: #e2e8f0; -fx-border-radius: 8px;");
        branchBox.getChildren().addAll(branchLabel, branchCombo);

        // Login Information Section
        Label loginSection = new Label("Login Information");
        loginSection.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox usernameBox = new VBox(5);
        Label usernameLabel = new Label("Username *");
        usernameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        usernameField = new TextField();
        usernameField.setPromptText("Choose a username");
        usernameField.setStyle("-fx-pref-height: 40px; -fx-padding: 0 12px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");
        usernameBox.getChildren().addAll(usernameLabel, usernameField);

        VBox emailBox = new VBox(5);
        Label emailLabel = new Label("Email Address *");
        emailLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setStyle("-fx-pref-height: 40px; -fx-padding: 0 12px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");
        emailBox.getChildren().addAll(emailLabel, emailField);

        HBox passwordBox = new HBox(15);
        VBox passwordFieldBox = new VBox(5);
        Label passwordLabel = new Label("Password *");
        passwordLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setStyle("-fx-pref-height: 40px; -fx-padding: 0 12px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");
        passwordFieldBox.getChildren().addAll(passwordLabel, passwordField);

        VBox confirmPasswordBox = new VBox(5);
        Label confirmPasswordLabel = new Label("Confirm Password *");
        confirmPasswordLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your password");
        confirmPasswordField.setStyle("-fx-pref-height: 40px; -fx-padding: 0 12px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");
        confirmPasswordBox.getChildren().addAll(confirmPasswordLabel, confirmPasswordField);

        passwordBox.getChildren().addAll(passwordFieldBox, confirmPasswordBox);

        // Employment Section
        Label employmentSection = new Label("Employment Information");
        employmentSection.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox employmentBox = new VBox(5);
        Label employmentLabel = new Label("Employment Status");
        employmentLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        employmentCombo = new ComboBox<>();
        employmentCombo.getItems().addAll("Select Employment Status", "Employed", "Self-Employed", "Student", "Other");
        employmentCombo.setValue("Select Employment Status");
        employmentCombo.valueProperty().addListener((obs, oldVal, newVal) -> toggleEmploymentFields());
        employmentCombo.setStyle("-fx-pref-height: 40px; -fx-background-color: white; -fx-border-color: #e2e8f0; -fx-border-radius: 8px;");
        employmentBox.getChildren().addAll(employmentLabel, employmentCombo);

        // Employment details 
        VBox companyNameBox = new VBox(5);
        Label companyNameLabel = new Label("Company Name");
        companyNameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        companyNameField = new TextField();
        companyNameField.setPromptText("Enter company name");
        companyNameField.setStyle("-fx-pref-height: 40px; -fx-padding: 0 12px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-font-size: 14px;");
        companyNameBox.getChildren().addAll(companyNameLabel, companyNameField);
        companyNameBox.setVisible(false);

        VBox companyAddressBox = new VBox(5);
        Label companyAddressLabel = new Label("Company Address");
        companyAddressLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        companyAddressField = new TextArea();
        companyAddressField.setPromptText("Enter company address");
        companyAddressField.setPrefRowCount(2);
        companyAddressField.setStyle("-fx-background-color: white; -fx-border-color: #e2e8f0; -fx-border-radius: 8px; -fx-padding: 8px 12px;");
        companyAddressBox.getChildren().addAll(companyAddressLabel, companyAddressField);
        companyAddressBox.setVisible(false);

        // Error label
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);
        errorLabel.setWrapText(true);

        // Buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button registerButton = new Button("Register Account");
        registerButton.setStyle("-fx-pref-height: 50px; -fx-min-width: 200px; -fx-background-color: #667eea; -fx-background-radius: 8px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-cursor: hand;");
        registerButton.setOnAction(e -> handleRegistration());

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-pref-height: 45px; -fx-min-width: 120px; -fx-background-color: white; -fx-background-radius: 8px; -fx-border-color: #667eea; -fx-border-radius: 8px; -fx-text-fill: #667eea; -fx-font-weight: bold; -fx-font-size: 14px; -fx-cursor: hand;");
        cancelButton.setOnAction(e -> onShowLogin.run());

        buttonBox.getChildren().addAll(registerButton, cancelButton);

        // Login link
        HBox loginBox = new HBox(5);
        loginBox.setAlignment(Pos.CENTER);
        Label loginText = new Label("Already have an account?");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setStyle("-fx-text-fill: #667eea; -fx-border-color: transparent; -fx-underline: false;");
        loginLink.setOnMouseEntered(e -> loginLink.setStyle("-fx-text-fill: #5a6fd8; -fx-underline: true;"));
        loginLink.setOnMouseExited(e -> loginLink.setStyle("-fx-text-fill: #667eea; -fx-underline: false;"));
        loginLink.setOnAction(e -> onShowLogin.run());
        loginBox.getChildren().addAll(loginText, loginLink);

        formContainer.getChildren().addAll(
                personalSection, nameBox, addressBox, branchBox,
                new Separator(),
                loginSection, usernameBox, emailBox, passwordBox,
                new Separator(),
                employmentSection, employmentBox, companyNameBox, companyAddressBox,
                errorLabel, buttonBox, loginBox
        );

        scrollPane.setContent(formContainer);
        rightSection.getChildren().addAll(titleLabel, scrollPane);

        return rightSection;
    }

    private void toggleEmploymentFields() {
        String employmentStatus = employmentCombo.getValue();
        boolean showEmploymentFields = "Employed".equals(employmentStatus) || "Self-Employed".equals(employmentStatus);

        companyNameField.setVisible(showEmploymentFields);
        companyAddressField.setVisible(showEmploymentFields);


        for (var node : ((VBox) ((ScrollPane) ((VBox) scene.getRoot().getChildrenUnmodifiable().get(1)).getChildren().get(1)).getContent()).getChildren()) {
            if (node instanceof VBox) {
                VBox vbox = (VBox) node;
                if (!vbox.getChildren().isEmpty() && vbox.getChildren().get(0) instanceof Label) {
                    Label label = (Label) vbox.getChildren().get(0);
                    if ("Company Name".equals(label.getText())) {
                        vbox.setVisible(showEmploymentFields);
                    } else if ("Company Address".equals(label.getText())) {
                        vbox.setVisible(showEmploymentFields);
                    }
                }
            }
        }
    }

    private void handleRegistration() {
        // Validation
        if (firstNameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
                addressField.getText().isEmpty() || usernameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                branchCombo.getValue() == null) {
            showError("Please fill in all required fields");
            return;
        }

        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            showError("Passwords do not match");
            return;
        }

        if (usernameField.getText().length() < 3) {
            showError("Username must be at least 3 characters long");
            return;
        }

        try {
            String employmentStatus = employmentCombo.getValue();
            String companyName = null;
            String companyAddress = null;

            if ("Employed".equals(employmentStatus) || "Self-Employed".equals(employmentStatus)) {
                companyName = companyNameField.getText();
                companyAddress = companyAddressField.getText();

                if (companyName.isEmpty() || companyAddress.isEmpty()) {
                    showError("Please fill in company details for employed/self-employed status");
                    return;
                }
            }


            String branch = branchCombo.getValue().split(" - ")[0];

            System.out.println("Attempting to register customer: " + usernameField.getText() + " at branch: " + branch);

            Customer customer = loginController.register(
                    firstNameField.getText(),
                    surnameField.getText(),
                    addressField.getText(),
                    usernameField.getText(),
                    passwordField.getText(),
                    emailField.getText(),
                    employmentStatus,
                    companyName,
                    companyAddress
            );

            if (customer != null) {
                System.out.println("Registration successful! Navigating to dashboard...");
                onRegistrationSuccess.run();
            } else {
                showError("Registration failed - please try again");
            }

        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        } catch (Exception e) {
            showError("Registration failed: " + e.getMessage());
            e.printStackTrace();
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
        firstNameField.clear();
        surnameField.clear();
        addressField.clear();
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        employmentCombo.setValue("Select Employment Status");
        branchCombo.setValue("Main Branch - Gaborone");
        companyNameField.clear();
        companyAddressField.clear();
        errorLabel.setVisible(false);
        toggleEmploymentFields();
    }
}