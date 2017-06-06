package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    private TextField vodNumberTF;
    @FXML
    private Button okButton;
    @FXML
    private TableView tableView;
    @FXML
    private TableView tableViewMax;
    @FXML
    private TableColumn<CountsRow, String> timeStampCol;
    @FXML
    private TableColumn<CountsRow, String> maxDishCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxWOWCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxNOCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxKappaCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxSubCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxCalcCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxDWGCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxAllinCountCol;
    @FXML
    private TableColumn<CountsRow, String> maxRubCountCol;
    @FXML
    private ProgressIndicator progressBar;

    private boolean isCurrentlyRunning = false;
    private String vodNumber;
    private String url = "";
    private boolean isErrorURL;
    private String message = "";
    private String startTimeStamp = "";
    private String stopTimeStamp = "";
    private int maxDishCount;
    private int maxWOWCount;
    private int maxNOCount;
    private int maxKappaCount;
    private int maxSubCount;
    private int maxCalcCount;
    private int maxDWGCount;
    private int maxAllinCount;
    private int maxRubCount;
    private long videolength;
    private int currentTimemark = -30;

    public void initTables() {
        timeStampCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("timeStamp"));
        maxDishCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxDishCount"));
        maxWOWCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxWOWCount"));
        maxNOCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxNOCount"));
        maxKappaCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxKappaCount"));
        maxSubCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxSubCount"));
        maxCalcCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxCalcCount"));
        maxDWGCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxDWGCount"));
        maxAllinCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxAllinCount"));
        maxRubCountCol.setCellValueFactory(
                new PropertyValueFactory<CountsRow, String>("maxRubCount"));
    }

    public void getDishStampsFromVodID(String vodID) {
        okButton.setDisable(true);
        initTables();

        //System.out.println("getting emotetimestamps of vod: " + vodID);
        url = "https://rechat.twitch.tv/rechat-messages?start=123&video_id=v" + vodID;
        try {
            readURL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }


        new Thread() {
            //filter timestamp out of ErrorURL
            public void run() {
                if (isErrorURL)

                {
                    //System.out.println("getting start and stoptime");
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(message);
                    boolean first = true;
                    while (m.find()) {
                        if (m.group().length() > 8) {
                            if (first) {
                                //System.out.println("wut");
                                startTimeStamp = m.group();
                                first = false;
                            } else {
                                stopTimeStamp = m.group();
                            }

                        }
                    }

                    try {
                        videolength = Long.parseLong(stopTimeStamp) - Long.parseLong(startTimeStamp);

                    } catch (Exception e) {
                        showError("Vod-ID must be valid. The ID is at the end of a Twitch URL. ex.: https://www.twitch.tv/videos/148457111 ; 148457111 would be the ID.");
                    }


                    String timeMarkString = "";
                    ObservableList<CountsRow> maxObsList = FXCollections.observableArrayList();
                    for (long i = Long.parseLong(startTimeStamp); i <= Long.parseLong(stopTimeStamp); i = i + 30) {

                        currentTimemark += 30;
                        //System.out.println("Checking next 30 seconds from videotime: " + currentTimemark);
                        url = "https://rechat.twitch.tv/rechat-messages?start=" + i + "&video_id=v" + vodID;
                        try {
                            readURL(url);
                        } catch (Exception e) {
                            showError("Cant Load VOD-Transcript Data.");
                        }

                        maxDishCount = StringUtils.countMatches(message, "maxDish");
                        maxWOWCount = StringUtils.countMatches(message, "maxWOW");
                        maxNOCount = StringUtils.countMatches(message, "maxNO");
                        maxKappaCount = StringUtils.countMatches(message, "maxKappa");
                        maxSubCount = StringUtils.countMatches(message, "maxSub");
                        maxCalcCount = StringUtils.countMatches(message, "maxCalc");
                        maxDWGCount = StringUtils.countMatches(message, "maxDWG");
                        maxAllinCount = StringUtils.countMatches(message, "maxAllin");
                        maxRubCount = StringUtils.countMatches(message, "maxRub");


                        int hours = currentTimemark / 3600;
                        int minutes =  currentTimemark % 3600 / 60;
                        int seconds = currentTimemark % 60;


                        timeMarkString = String.format("%02d:%02d:%02d", hours, minutes, seconds);



                        if (maxDishCount > 0 || maxWOWCount > 0 || maxNOCount > 0 || maxKappaCount > 0 || maxSubCount > 0 || maxCalcCount > 0 || maxDWGCount > 0 || maxAllinCount > 0 || maxRubCount > 0) {

                            maxObsList.add(new CountsRow(timeMarkString, String.valueOf(maxDishCount), String.valueOf(maxWOWCount), String.valueOf(maxNOCount), String.valueOf(maxKappaCount), String.valueOf(maxSubCount), String.valueOf(maxCalcCount), String.valueOf(maxDWGCount), String.valueOf(maxAllinCount), String.valueOf(maxRubCount)));

                        }

                        double progress = ((double)currentTimemark / (double)videolength);
                        progressBar.setProgress(progress);
                        System.out.println(progress);
                    }

                    tableView.setItems(maxObsList);

                    progressBar.setProgress(1);


                }
            }
        }.start();
        okButton.setDisable(false);
    }

    public static void showError(String error) {
        if (Platform.isFxApplicationThread()) {
            Stage s = new Stage();
            VBox v = new VBox();
            v.setPrefWidth(800);
            v.setPrefHeight(100);
            v.setAlignment(Pos.CENTER);
            v.getChildren().add(new Label("Error : " + error));
            Scene sc = new Scene(v);
            s.setTitle("Fail");
            s.setScene(sc);
            s.show();
        } else {
            Platform.runLater(() -> {
                Stage s = new Stage();
                VBox v = new VBox();
                v.setPrefWidth(800);
                v.setPrefHeight(100);
                v.setAlignment(Pos.CENTER);
                v.getChildren().add(new Label("Error: " + error));
                Scene sc = new Scene(v);
                s.setTitle("Fail");
                s.setScene(sc);
                s.show();
            });
        }
    }


    public void onButtonPress(ActionEvent actionEvent) {
        if (vodNumberTF.getText().matches("[0-9]+") && vodNumberTF.getText().length() > 2) {
            vodNumber = vodNumberTF.getText();
            getDishStampsFromVodID(vodNumber);
        } else {
            showError("Vod-ID must be valid. The ID is at the end of a Twitch URL. ex.: https://www.twitch.tv/videos/148457111 ; 148457111 would be the ID.");
        }

    }

    public void readURL(String url) throws Exception {
        message = "";
        URL vodURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) vodURL.openConnection();
        InputStream _is;

        if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
            _is = conn.getInputStream();
            isErrorURL = false;
        } else {
     /* error from server */
            _is = conn.getErrorStream();
            isErrorURL = true;

        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(_is));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            message += inputLine;
        in.close();
        //System.out.println(message);
    }
}
