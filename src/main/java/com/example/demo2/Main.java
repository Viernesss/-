package com.example.demo2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Customer, String> coldog;

    @FXML
    private TableColumn<Customer, String> colplan;

    @FXML
    private TableColumn<Customer, String> colzak;

    @FXML
    private TableColumn<Customer1, String> colact;

    @FXML
    private TableColumn<Customer1, String> coldata;

    @FXML
    private TableColumn<Customer1, String> colz;

    @FXML
    private RadioButton gr1;

    @FXML
    private RadioButton gr2;

    @FXML
    private RadioButton g1;

    @FXML
    private RadioButton g2;

    @FXML
    private TextField adres;

    @FXML
    private ComboBox<String> cmbb;

    @FXML
    private DatePicker data;

    @FXML
    private DatePicker data_sr;

    @FXML
    private Button sohract;

    @FXML
    private Button dobk;

    @FXML
    private TextField fio;

    @FXML
    private ImageView imgv;

    @FXML
    private TextArea imuch;

    @FXML
    private TextArea komm;

    @FXML
    private TextField naim_k;

    @FXML
    private Tab tbp;

    @FXML
    private TableColumn<Customeract, String> ca;

    @FXML
    private TableColumn<Customeract, String> co;

    @FXML
    private TableColumn<Customeract, String> cz;

    @FXML
    private Tab one;

    @FXML
    private TextField otv_l;

    @FXML
    private TextField poisk;

    @FXML
    private ToggleGroup rbt;

    @FXML
    private ToggleGroup rbt1;

    @FXML
    private ToggleGroup rbt2;

    @FXML
    private Button sohr;

    @FXML
    private TabPane tabpane;

    @FXML
    private TableView<Customer> tbvw;

    @FXML
    private TableView<Customer1> tbvw1;

    @FXML
    private TableView<Customeract> tbvw2;

    @FXML
    private Tab three;

    @FXML
    private TextField time;

    @FXML
    private Tab two;

    @FXML
    private TextField txtim;

    @FXML
    private Button ud;

    @FXML
    private Button vih;

    @FXML
    private Button vih1;

    @FXML
    private Button vih2;

    @FXML
    private TextField yd_z;

    @FXML
    private TextField zakaz;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DataBaseHU dbHandler = new DataBaseHU();
        ArrayList<String> filesToMonitor = new ArrayList<>();
        cmbb.getItems().addAll("Юр. лицо", "Физ. лицо");
        cmbb.getSelectionModel().select("Юр. лицо");
        cmbb.setOnAction(actionEvent -> {
            int ind = cmbb.getSelectionModel().getSelectedIndex();
            if (ind == 1) {
                naim_k.setDisable(true);
            } else naim_k.setDisable(false);
        }); // настройка комбобкса

        dobk.setOnAction(actionEvent -> {
            String st = txtim.getText();

            File file = new File(st);
            Image image = new Image((file.toURI().toString()));
            imgv.setImage(image);
        }); // добавление картинки

        zap_tabl_cust();
        zap_tabl_act();

        rbt.selectToggle(rbt.getToggles().get(1));
        gr1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                try {
                    zap_tabl_cust();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        gr2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tbvw.getItems().sort(Comparator.naturalOrder());
            }
        });

        rbt1.selectToggle(rbt1.getToggles().get(0));
        g1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                try {
                    zap_tabl_act();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        g2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tbvw1.getItems().sort(Comparator.naturalOrder());
            }
        });

        poisk.textProperty().addListener((observable, oldValue, newValue) -> {
            ResultSet allc = null;
            try {
                allc = dbHandler.getCustomer1(newValue);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ObservableList<Customer> people = FXCollections.observableArrayList();
            ArrayList<Customer> cust = new ArrayList<>();
            while (true) {
                try {
                    if (!allc.next()) break; else {
                        cust.add(new Customer(allc.getString(2), allc.getString(8), allc.getString(7)));
                    };
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            people.addAll(cust);

            colzak.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
            coldog.setCellValueFactory(new PropertyValueFactory<Customer, String>("imageUrl1"));
            coldog.setCellFactory(tv -> new TableCell<Customer, String>() {
                private final ImageView imageView = new ImageView();
                @Override
                protected void updateItem(String imageUrl, boolean empty) {
                    super.updateItem(imageUrl, empty);
                    if (empty || imageUrl == null) {
                        setGraphic(null);
                    } else {
                        File file = new File( "C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/docum.png");
                        Image image = new Image(file.toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(40);
                        imageView.setFitHeight(40);
                        setGraphic(imageView);
                        setOnMouseClicked(event -> {
                            if (event.getClickCount() == 2) { // Двойной клик
                                try {
                                    Desktop.getDesktop().open(new File(imageUrl));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                ;}
                        });
                    }}});
            colplan.setCellValueFactory(new PropertyValueFactory<Customer, String>("imageUrl2"));
            colplan.setCellFactory(tv -> new TableCell<Customer, String>() {
                private final ImageView imageView = new ImageView();
                @Override
                protected void updateItem(String imageUrl, boolean empty) {
                    super.updateItem(imageUrl, empty);
                    if (empty || imageUrl == null) {
                        setGraphic(null);
                    } else {
                        File file = new File( "C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/plan.png");
                        Image image = new Image(file.toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(35);
                        imageView.setFitHeight(35);
                        setGraphic(imageView);
                        setOnMouseClicked(event -> {
                            if (event.getClickCount() == 2) { // Двойной клик
                                try {
                                    Desktop.getDesktop().open(new File(imageUrl));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                ;}
                        });
                    }}});
            tbvw.setItems(people);
        });

        sohr.setOnAction(actionEvent -> {
            if (!fio.getText().isEmpty()) {
                String dog = "C:/Users/veron/IdeaProjects/Prilozhenie/src/" +
                        "main/resources/com/example/demo2/" + fio.getText();
                new File(dog).mkdir();
                try {
                    BufferedImage image = ImageIO.read(new File("C:/Users/veron/IdeaProjects/Prilozhenie" +
                            "/src/main/resources/com/example/demo2/Договор/Д1.jpg"));
                    Graphics2D graphics = image.createGraphics();

                    Font font = new Font("Times New Roman", Font.BOLD, 30);
                    graphics.setFont(font);
                    graphics.setColor(Color.BLACK);

                    String[] dat = data.getValue().toString().split("-");

                    graphics.drawString((dat[2] + "." + dat[1] + "." + dat[0]), 1240, 210);
                    graphics.drawString(adres.getText(), 690, 802);

                    ImageIO.write(image, "jpg", new File(dog + "/Д1.jpg"));
                    graphics.dispose();

                    Files.copy(Path.of("C:/Users/veron/IdeaProjects/Prilozhenie/src/main/" +
                                    "resources/com/example/demo2/Договор/Д2.jpg"),
                            Path.of(dog + "/Д2.jpg"));

                    Files.copy(Path.of("C:/Users/veron/IdeaProjects/Prilozhenie/" +
                                    "src/main/resources/com/example/demo2/Договор/Д4.jpg"),
                            Path.of(dog + "/Д4.jpg"));

                    BufferedImage image1 = ImageIO.read(new File("C:/Users/veron/IdeaProjects/Prilozhenie" +
                            "/src/main/resources/com/example/demo2/Договор/Д3.jpg"));
                    Graphics2D graphics1 = image1.createGraphics();

                    graphics1.setFont(font);
                    graphics1.setColor(Color.BLACK);

                    String[] sp = imuch.getText().split("\n");
                    String it_st = "";
                    for (int i = 0; i < sp.length; i++) {
                        if (i != (sp.length - 1)) {
                            it_st += sp[i] + ", ";
                        } else {
                            it_st += sp[i];
                        }
                    }

                    graphics1.drawString(it_st, 200, 770);

                    ImageIO.write(image1, "jpg", new File(dog + "/Д3.jpg"));
                    graphics1.dispose();

                    BufferedImage image2 = ImageIO.read(new File("C:/Users/veron/IdeaProjects/Prilozhenie" +
                            "/src/main/resources/com/example/demo2/Договор/Д5.jpg"));
                    Graphics2D graphics2 = image2.createGraphics();

                    graphics2.setFont(font);
                    graphics2.setColor(Color.BLACK);

                    String n_k = "";
                    String otv = "";
                    int ind = cmbb.getSelectionModel().getSelectedIndex();
                    if (ind == 1) {
                        n_k = otv_l.getText();
                        otv = "";
                    } else {
                        n_k = naim_k.getText();
                        otv = otv_l.getText();
                    }

                    graphics2.drawString(n_k, 185, 975);
                    graphics2.drawString(otv, 185, 1012);
                    graphics2.drawString(adres.getText(), 280, 1053);

                    ImageIO.write(image2, "jpg", new File(dog + "/Д5.jpg"));
                    graphics1.dispose();

                    String st = txtim.getText();
                    Files.copy(Path.of(st), Path.of(dog + "/План.jpg"));

                    Desktop.getDesktop().open(new File(dog));


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Path filePath = Paths.get("C:/Users/veron/IdeaProjects/Prilozhenie/src/main/" +
                        "resources/com/example/demo2/Сигнализация", (fio.getText() + ".txt"));

                // Создание текстового файла с указанным содержимым
                try {
                    Files.write(filePath, "".getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // блок заполнения БД
                try {
                    String n_k = "";
                    String zak = "";
                    int ind = cmbb.getSelectionModel().getSelectedIndex();
                    if (ind == 1) {
                        n_k = fio.getText();
                    } else {
                        n_k = naim_k.getText();
                    }
                    zak = fio.getText();
                    dbHandler.signUpCustomer(n_k, zak, adres.getText(), otv_l.getText(),
                            imuch.getText(), dog + "/План.jpg", dog);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                filesToMonitor.add("C:/Users/veron/IdeaProjects/Prilozhenie/src/main/" +
                        "resources/com/example/demo2/Сигнализация/" + (fio.getText() + ".txt"));
                naim_k.clear();
                fio.clear();
                adres.clear();
                otv_l.clear();
                imuch.clear();
                txtim.clear();
                data.getEditor().clear();
                imgv.setImage(null);
                try {
                    zap_tabl_cust();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }); // сохранение договора

        sohract.setOnAction(actionEvent -> {
            if (!zakaz.getText().isEmpty()) {
                int nact = 1;
                try {
                    ResultSet result = dbHandler.getCustomer(zakaz.getText());
                    String nazvanie = "";
                    String surname = "";
                    String raspol = "";
                    String otvetstv = "";
                    String im_otvetstv = "";
                    while (true) {
                        if (!result.next()) break;
                        else {
                            nazvanie = result.getString(2);
                            surname = result.getString(3);
                            raspol = result.getString(4);
                            otvetstv = result.getString(5);
                            im_otvetstv = result.getString(6);
                        }
                    }


                    BufferedImage image = ImageIO.read(new File("C:/Users/veron/IdeaProjects/Prilozhenie" +
                            "/src/main/resources/com/example/demo2/Акт.jpg"));
                    Graphics2D graphics = image.createGraphics();

                    Font font = new Font("Times New Roman", Font.BOLD, 30);
                    graphics.setFont(font);
                    graphics.setColor(Color.BLACK);


                    graphics.drawString(otvetstv, 270, 315);
                    graphics.drawString(HelloController.user, 930, 315);


                    String[] sp = im_otvetstv.split("\n");
                    String it_st1 = "";
                    String it_st2 = "";
                    for (int i = 0; i < sp.length; i++) {
                        if ((it_st1 + sp[i]).length() < 42) {
                            if (i != (sp.length - 1)) {
                                it_st1 += sp[i] + ", ";
                            } else {
                                it_st1 += sp[i];
                            }
                        } else {
                            if (i != (sp.length - 1)) {
                                it_st2 += sp[i] + ", ";
                            } else {
                                it_st2 += sp[i];
                            }
                        }
                    }
                    graphics.drawString(it_st1, 650, 360);
                    graphics.drawString(it_st2, 250, 440);


                    String[] dat = data_sr.getValue().toString().split("-");
                    String[] mes = {"января", "февраля", "марта", "апреля", "мая", "июня",
                            "июля", "августа", "сентября", "октября", "ноября", "декабря"};
                    graphics.drawString(dat[2], 740, 482);
                    graphics.drawString(mes[Integer.parseInt(dat[1]) - 1], 875, 482);
                    graphics.drawString(dat[0].substring(2), 1175, 482);
                    String[] vrem = time.getText().split(":");
                    graphics.drawString(vrem[0], 1275, 482);
                    graphics.drawString(vrem[1], 1420, 482);


                    if (nazvanie.equals(surname)) {
                        graphics.drawString("на объекте частной территории", 870, 520);
                    } else {
                        graphics.drawString("на объекте компании " + nazvanie + " ", 870, 520);
                    }


                    graphics.drawString(raspol, 650, 560);


                    RadioButton selection = (RadioButton) rbt2.getSelectedToggle();
                    if (selection.getText().equals("Проникновение")) {
                        graphics.drawString("совершен взлом с проникновением", 745, 681);
                    } else if (selection.getText().equals("Ложное срабатывание")) {
                        graphics.drawString("произошло ложное срабатывание сигнализации", 745, 681);
                    } else {
                        graphics.drawString("", 745, 681);
                    }


                    String it1 = "";
                    String it2 = "";
                    String it3 = "";
                    String[] sp1 = komm.getText().split(" ");
                    for (String i : sp1) {
                        if ((it1 + i).length() < 74) {
                            it1 += (i + " ");
                        } else if ((it2 + i).length() < 74) {
                            it2 += (i + " ");
                        } else if ((it3 + i).length() < 74) {
                            it3 += (i + " ");
                        }
                    }
                    graphics.drawString(it1, 245, 843);
                    graphics.drawString(it2, 245, 884);
                    graphics.drawString(it3, 245, 923);

                    ResultSet result1;
                    try {
                        result1 = dbHandler.getNAct();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    while (true) {
                        try {
                            if (!result1.next()) break; else nact = result1.getInt(1);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    ImageIO.write(image, "jpg", new File("C:/Users/veron/IdeaProjects/Prilozhenie/" +
                            "src/main/resources/com/example/demo2/Акты/" + zakaz.getText() + " №" + nact + ".jpg"));
                    graphics.dispose();

                    Desktop.getDesktop().open(new File("C:/Users/veron/IdeaProjects/Prilozhenie/" +
                            "src/main/resources/com/example/demo2/Акты/" + zakaz.getText() + " №" + nact + ".jpg"));
                } catch (IOException | SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


                String pact = "C:/Users/veron/IdeaProjects/Prilozhenie/" +
                        "src/main/resources/com/example/demo2/Акты/" + zakaz.getText() + " №" + nact + ".jpg";
                try {
                    dbHandler.signUpAct(data_sr.getValue(), zakaz.getText(), pact);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                zakaz.clear();
                time.clear();
                data_sr.getEditor().clear();
                komm.clear();
                rbt2.selectToggle(null);
                try {
                    zap_tabl_act();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }


        }); // сохранение акта

        ud.setOnAction(actionEvent -> {
            if (!yd_z.getText().isEmpty()) {
                try {
                    ResultSet res = dbHandler.getCustomer(yd_z.getText());
                    String put = "";
                    while (true) {
                        if (!res.next()) break; else {put = res.getString(8);};
                    }
                    Files.walk(Paths.get(put))
                            .map(Path::toAbsolutePath)
                            .sorted((o1, o2) -> o2.compareTo(o1))
                            .forEach(path -> {
                                try {
                                    Files.deleteIfExists(path);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                    dbHandler.DeleteCustomer(yd_z.getText());
                    zap_tabl_cust();
                    yd_z.clear();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        vih.setOnAction(actionEvent -> {
            vih.getScene().getWindow().hide();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("welcome.fxml"));


            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setNode(root);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);

            // Запускаем анимацию перед отображением окна
            fadeTransition.play();
        });

        vih1.setOnAction(actionEvent -> {
            vih1.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("welcome.fxml"));


            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setNode(root);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);

            // Запускаем анимацию перед отображением окна
            fadeTransition.play();
        });

        vih2.setOnAction(actionEvent -> {
            vih2.getScene().getWindow().hide();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("welcome.fxml"));


            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setNode(root);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);

            // Запускаем анимацию перед отображением окна
            fadeTransition.play();
        });


        ResultSet nf = dbHandler.getAllCustomers();

        while (true) {
            try {
                if (!nf.next()) break; else {
                    filesToMonitor.add("C:/Users/veron/IdeaProjects/Prilozhenie/src/main/" +
                            "resources/com/example/demo2/Сигнализация/" + nf.getString(3) + ".txt");
                };
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        ScheduledExecutorService executor;
        executor = Executors.newScheduledThreadPool(1);
        try {
            startMonitoringFiles(executor, filesToMonitor); // сюда что-то написать
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void zap_tabl_act() throws SQLException, ClassNotFoundException {
        DataBaseHU dbHandler = new DataBaseHU();
        ResultSet alla = dbHandler.getNAct();
        ObservableList<Customer1> people1 = FXCollections.observableArrayList();
        ArrayList<Customer1> cust1 = new ArrayList<>();
        while (true) {
            try {
                if (!alla.next()) break; else {
                    cust1.add(new Customer1(alla.getDate(2).toString(), alla.getString(3), alla.getString(4)));
                };
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        people1.addAll(cust1);
        colz.setCellValueFactory(new PropertyValueFactory<Customer1, String>("name1"));
        coldata.setCellValueFactory(new PropertyValueFactory<Customer1, String>("data1"));
        colact.setCellValueFactory(new PropertyValueFactory<Customer1, String>("imageUrl"));
        colact.setCellFactory(tv -> new TableCell<Customer1, String>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);
                if (empty || imageUrl == null) {
                    setGraphic(null);
                } else {
                    File file = new File( "C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/act.png");
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(35);
                    imageView.setFitHeight(35);
                    setGraphic(imageView);
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) { // Двойной клик
                            try {
                                Desktop.getDesktop().open(new File(imageUrl));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            ;}
                    });
                }}});
        tbvw1.setItems(people1);
    }

    public void zap_tabl_cust() throws SQLException, ClassNotFoundException {
        DataBaseHU dbHandler = new DataBaseHU();
        ResultSet allc = dbHandler.getAllCustomers();
        ObservableList<Customer> people = FXCollections.observableArrayList();
        ArrayList<Customer> cust = new ArrayList<>();
        while (true) {
            try {
                if (!allc.next()) break; else {
                    cust.add(new Customer(allc.getString(2), allc.getString(8), allc.getString(7)));
                };
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        people.addAll(cust);

        colzak.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        coldog.setCellValueFactory(new PropertyValueFactory<Customer, String>("imageUrl1"));
        coldog.setCellFactory(tv -> new TableCell<Customer, String>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);
                if (empty || imageUrl == null) {
                    setGraphic(null);
                } else {
                    File file = new File( "C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/docum.png");
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    setGraphic(imageView);
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) { // Двойной клик
                            try {
                                Desktop.getDesktop().open(new File(imageUrl));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            ;}
                    });
                }}});
        colplan.setCellValueFactory(new PropertyValueFactory<Customer, String>("imageUrl2"));
        colplan.setCellFactory(tv -> new TableCell<Customer, String>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String imageUrl, boolean empty) {
                super.updateItem(imageUrl, empty);
                if (empty || imageUrl == null) {
                    setGraphic(null);
                } else {
                    File file = new File( "C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/plan.png");
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(35);
                    imageView.setFitHeight(35);
                    setGraphic(imageView);
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) { // Двойной клик
                            try {
                                Desktop.getDesktop().open(new File(imageUrl));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            ;}
                    });
                }}});
        tbvw.setItems(people);
    }

    private void startMonitoringFiles(ScheduledExecutorService executor, ArrayList<String> filesToMonitor) throws IOException, SQLException, ClassNotFoundException {
        DataBaseHU dbHandler = new DataBaseHU();
        ObservableList<Customeract> people = FXCollections.observableArrayList();
        ArrayList<Customeract> custact = new ArrayList<>();
        Runnable task = () -> {
            for (String filePath : filesToMonitor) {
                try {
                    String content = new String(Files.readAllBytes(Paths.get(filePath)));
                    if (!content.equals("")) {
                        tbvw2.getItems().clear();

                        String[] st = filePath.split("/");
                        String name = st[st.length - 1].split(".txt")[0];
                        ResultSet allc = dbHandler.getCustomer2(name);
                        while (true) {
                            try {
                                if (!allc.next()) break; else {
                                    custact.add(new Customeract(allc.getString(2), allc.getString(4), "Сигнализация!!!"));;
                                };
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        people.addAll(custact);
                        ca.setCellValueFactory(new PropertyValueFactory<Customeract, String>("adres"));
                        co.setCellValueFactory(new PropertyValueFactory<Customeract, String>("soob"));
                        cz.setCellValueFactory(new PropertyValueFactory<Customeract, String>("name2"));
                        tbvw2.setItems(people);

                        PrintWriter writer = new PrintWriter(filePath);
                        writer.print("");
                        writer.flush();
                        writer.close();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Запуск задачи с периодичностью в 5 секунд
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    private void stopMonitoringFiles(ScheduledExecutorService executor) {
        if (executor!= null) {
            executor.shutdownNow();
        }
    }


}