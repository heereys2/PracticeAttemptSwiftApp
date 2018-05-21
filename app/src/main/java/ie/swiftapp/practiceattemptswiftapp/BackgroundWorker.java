package ie.swiftapp.practiceattemptswiftapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by user on 3/14/2018.
 */


public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    public String username, teamChoice;
    public String[] playerList;
    public String[] resultData;
    public String clubNameSpinnerChoice;
    public String[] userTeams;
    public String[] amountOfTeams;
    public String[] amountOfClubs;
    public String teamNameChosen;
    public String[] playerTeams;
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://swiftproject.000webhostapp.com/login.php";
        String register_url = "http://swiftproject.000webhostapp.com/register.php";
        String createTeam_url = "http://swiftproject.000webhostapp.com/createteam.php";
        String club_url = "http://swiftproject.000webhostapp.com/clubs.php";
        String teams_url = "http://swiftproject.000webhostapp.com/teams.php";
        String savetime_url = "http://swiftproject.000webhostapp.com/savetime.php";
        String saveplayerdata_url = "http://swiftproject.000webhostapp.com/saveplayerdata.php";
        String allclubs_url = "http://swiftproject.000webhostapp.com/allclubs.php";
        String selectplayers_url = "http://swiftproject.000webhostapp.com/selectplayer.php";
        String joinTeam_url = "http://swiftproject.000webhostapp.com/jointeam.php";
        String playerteamlist_url = "http://swiftproject.000webhostapp.com/playerteamlist.php";
        String playerclubjoin_url = "http://swiftproject.000webhostapp.com/playerclubjoin.php";
        String playerteamchoice_url = "http://swiftproject.000webhostapp.com/teamsforplayer.php";
        String addplayertoteam_url = "http://swiftproject.000webhostapp.com/addplayertoteam.php";
        String viewplayerdata_url = "http://swiftproject.000webhostapp.com/viewplayerdata.php";
        if(type.equals("login")) {
            try {
                //Store values passed from login activirt
                String user_name = params[1];
                username = user_name;
                String password = params[2];
                //Connect to the PHP script, address stored in variable above
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //The POST data being sent for the PHP file to user
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                //Store the outputted message from th PHP script (success/fail)
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                URL url2 = new URL(club_url);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection)url2.openConnection();
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "UTF-8"));
                String post_data2 = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter2.write(post_data2);
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream.close();
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2,"iso-8859-1"));
                String line2 = "";
                while((line2 = bufferedReader2.readLine())!= null) {
                    String [] lineArray = line2.split("//");
                    userTeams = new String[lineArray.length];
                    for(int i =0; i < lineArray.length; i++) {
                        userTeams[i] =lineArray[i].replaceAll("//","");
                    }
                }
                URL url3 = new URL(playerteamlist_url);
                HttpURLConnection httpURLConnection3 = (HttpURLConnection)url3.openConnection();
                httpURLConnection3.setRequestMethod("GET");
                httpURLConnection3.setDoOutput(true);
                httpURLConnection3.setDoInput(true);
                OutputStream outputStream3 = httpURLConnection3.getOutputStream();
                BufferedWriter bufferedWriter3 = new BufferedWriter(new OutputStreamWriter(outputStream3, "UTF-8"));
                String post_data3 = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter3.write(post_data3);
                bufferedWriter3.flush();
                bufferedWriter3.close();
                outputStream.close();
                InputStream inputStream3 = httpURLConnection3.getInputStream();
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(inputStream3,"iso-8859-1"));
                String line3 = "";
                while((line3 = bufferedReader3.readLine())!= null) {
                    String [] lineArray = line3.split("//");
                    playerTeams = new String[lineArray.length];
                    for(int i =0; i < lineArray.length; i++) {
                        playerTeams[i] =lineArray[i].replaceAll("//","");
                    }
                }
                bufferedReader3.close();
                inputStream3.close();
                httpURLConnection3.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("playerlist")) {
            try {
                String teamName = params[1];
                teamNameChosen = teamName;
                teamChoice = teamName;
                username = params[2];
                URL url = new URL(selectplayers_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("teamName","UTF-8")+"="+URLEncoder.encode(teamName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while((line = bufferedReader.readLine())!= null) {
                    String [] lineArray = line.split("//");
                    playerList = new String[lineArray.length-1];
                    for(int i =0; i < lineArray.length -1; i++) {
                        playerList[i] =lineArray[i].replaceAll("//","");
                    }
                    result = lineArray[lineArray.length -1];
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("viewplayerresults")) {
            try {
                username = params[1];
                String eventtype = params[2];
                String teamname = params[3];
                URL url = new URL(viewplayerdata_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("eventtype","UTF-8")+"="+URLEncoder.encode(eventtype,"UTF-8")+"&"+
                        URLEncoder.encode("teamname","UTF-8")+"="+URLEncoder.encode(teamname,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while((line = bufferedReader.readLine())!= null) {
                    String [] lineArray = line.split("//");
                    resultData = new String[lineArray.length-1];
                    for(int i =0; i < lineArray.length -1; i++) {
                        resultData[i] =lineArray[i].replaceAll("//","");
                    }
                    result = lineArray[lineArray.length -1];
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("register")) {
            try {
                //Store the values passed from the Register class in strings
                String email = params[1];
                String username = params[2];
                String password = params[3];
                String userType = params[4];
                //Create a url with the value stored in the variable register_url.
                URL url = new URL(register_url);
                //Open a HTTP connection with the URL. This gives it access to the PHP file stored by the web server.
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //POST the stored Strings to the PHP file
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("userType","UTF-8")+"="+URLEncoder.encode(userType,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                //Store the resulting message from the PHP file, alerting if the SQL query was a success or not.
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                URL url2 = new URL(club_url);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection)url2.openConnection();
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "UTF-8"));
                String post_data2 = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter2.write(post_data2);
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream.close();
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2,"iso-8859-1"));
                String line2 = "";
                while((line2 = bufferedReader2.readLine())!= null) {
                    String [] lineArray = line2.split("//");
                    userTeams = new String[lineArray.length];
                    for(int i =0; i < lineArray.length; i++) {
                        userTeams[i] =lineArray[i].replaceAll("//","");
                    }
                }
                bufferedReader2.close();
                inputStream2.close();
                httpURLConnection2.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("createclub")) {
            try {
                String teamName = params[1];
                String clubName = params[2];
                String sport = params[3];
                String username = params[4];
                URL url = new URL(createTeam_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("teamName","UTF-8")+"="+URLEncoder.encode(teamName,"UTF-8")+"&"+
                        URLEncoder.encode("clubName","UTF-8")+"="+URLEncoder.encode(clubName,"UTF-8")+"&"+
                        URLEncoder.encode("sport","UTF-8")+"="+URLEncoder.encode(sport,"UTF-8") + "&" +
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                URL url2 = new URL(club_url);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection)url2.openConnection();
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "UTF-8"));
                String post_data2 = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter2.write(post_data2);
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream.close();
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2,"iso-8859-1"));
                String line2 = "";
                while((line2 = bufferedReader2.readLine())!= null) {
                    String [] lineArray = line2.split("//");
                    userTeams = new String[lineArray.length];
                    for(int i =0; i < lineArray.length; i++) {
                        userTeams[i] =lineArray[i].replaceAll("//","");
                    }
                }
                bufferedReader2.close();
                inputStream2.close();
                httpURLConnection2.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("coachTeam")){
            try {
                String clubName = params[1];
                username = params[2];
                clubNameSpinnerChoice = clubName;
                URL url = new URL(teams_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("clubName","UTF-8")+"="+URLEncoder.encode(clubName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line = "";
                String result = "";
                while((line = bufferedReader.readLine())!= null) {
                    String [] lineArray = line.split("//");
                    amountOfTeams = new String[(lineArray.length - 1)];
                    for(int i =0; i < (lineArray.length - 1); i++) {
                        amountOfTeams[i] =lineArray[i].replaceAll("//","");
                    }
                    result = lineArray[lineArray.length -1];
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("Teams")){
            try {
                URL url = new URL(teams_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("playerentereddata")) {
            try {
                //Store the 4 values passed in the PlayerEnterData class
                String username = params[1];
                String teamname = params[2];
                String eventtype = params[3];
                String value = params[4];
                String date = params[5];
                //Create a new URL with the value of the saveplayerdata_url created earlier
                URL url = new URL(saveplayerdata_url);
                //connect to the file
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //POST the 4 String values to the PHP file
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("teamname","UTF-8")+"="+URLEncoder.encode(teamname,"UTF-8")+"&"+
                        URLEncoder.encode("eventtype","UTF-8")+"="+URLEncoder.encode(eventtype,"UTF-8")+"&"+
                        URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8")+"&"+
                        URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                //Store the result from the PHP file
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                //close connection
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("playerSaveTime")) {
            try {
                String username = params[1];
                String distance = params[2];
                String time = params[3];
                String date = params[4];
                URL url = new URL(savetime_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("distance","UTF-8")+"="+URLEncoder.encode(distance,"UTF-8")+"&"+
                        URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"+
                        URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("allclubs")){
            try{
                String user = params[1];
                username = user;
                URL url = new URL(allclubs_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line = "";
                String result = "";
                while((line = bufferedReader.readLine())!= null) {
                    String [] lineArray = line.split("//");
                    amountOfClubs = new String[(lineArray.length - 1)];
                    for(int i =0; i < (lineArray.length - 1); i++) {
                        amountOfClubs[i] =lineArray[i].replaceAll("//","");
                    }
                    result = lineArray[lineArray.length -1];
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


     

        } else if (type.equals("allclubsforplayers")){
            try{
                String user = params[1];
                username = user;
                URL url = new URL(playerclubjoin_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line = "";
                String result = "";
                while((line = bufferedReader.readLine())!= null) {
                    String [] lineArray = line.split("//");
                    amountOfClubs = new String[(lineArray.length - 1)];
                    for(int i =0; i < (lineArray.length - 1); i++) {
                        amountOfClubs[i] =lineArray[i].replaceAll("//","");
                    }
                    result = lineArray[lineArray.length -1];
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("playerteams")){
            try {
                String clubName = params[1];
                username = params[2];
                clubNameSpinnerChoice = clubName;
                URL url = new URL(playerteamchoice_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("clubName","UTF-8")+"="+URLEncoder.encode(clubName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line = "";
                String result = "";
                while((line = bufferedReader.readLine())!= null) {
                    String [] lineArray = line.split("//");
                    amountOfTeams = new String[(lineArray.length - 1)];
                    for(int i =0; i < (lineArray.length - 1); i++) {
                        amountOfTeams[i] =lineArray[i].replaceAll("//","");
                    }
                    result = lineArray[lineArray.length -1];
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("addplayertoteam")) {
            try {
                String userName = params[1];
                username = userName;
                String clubName = params[2];
                String teamName = params[3];
                URL url = new URL(addplayertoteam_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"+
                        URLEncoder.encode("clubName","UTF-8")+"="+URLEncoder.encode(clubName,"UTF-8")+"&"+
                        URLEncoder.encode("teamName","UTF-8")+"="+URLEncoder.encode(teamName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("joinTeam")){
            try {
                String teamName = params[2];
                String username = params[1];
                URL url = new URL(joinTeam_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("teamName","UTF-8")+"="+URLEncoder.encode(teamName,"UTF-8") + "&" +
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line = "";
                while((line = bufferedReader.readLine())!= null) {
                    result+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                URL url2 = new URL(club_url);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection)url2.openConnection();
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "UTF-8"));
                String post_data2 = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter2.write(post_data2);
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream.close();
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2,"iso-8859-1"));
                String line2 = "";
                while((line2 = bufferedReader2.readLine())!= null) {
                    String [] lineArray = line2.split("//");
                    userTeams = new String[lineArray.length];
                    for(int i =0; i < lineArray.length; i++) {
                        userTeams[i] =lineArray[i].replaceAll("//","");
                    }
                }
                bufferedReader2.close();
                inputStream2.close();
                httpURLConnection2.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    //Execute the following method after connection closes
    protected void onPostExecute(String result) {
        //If successful, redirect user back to login page and display success message. If not, alert the user with fail message.
        if (result.equals("Registration Successful. Please Login")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        } else if (result.equals("Please fill in all fields")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        } else if (result.equals("Coach Login Success")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent j = new Intent(context, CoachTeamChoice.class);
            j.putExtra("clubsArray", userTeams);
            j.putExtra("username",username);
            context.startActivity(j);
        } else if (result.equals("Player Login Success")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent k = new Intent(context, PlayerHome.class);
            k.putExtra("username",username);
            k.putExtra("clubsArray", playerTeams);
            context.startActivity(k);
        } else if (result.equals("Team Created successfully")) {
            Toast.makeText(context, "success!", Toast.LENGTH_LONG).show();
            Intent j = new Intent(context, CoachTeamChoice.class);
            j.putExtra("clubsArray", userTeams);
            j.putExtra("username",username);
            context.startActivity(j);
        } else if (result.equals("Login Failed")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        } else if (result.equals("teams found")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent coachWithTeams = new Intent(context, CoachClubSelection.class);
            coachWithTeams.putExtra("username",username);
            coachWithTeams.putExtra("teamsArray", amountOfTeams);
            coachWithTeams.putExtra("clubsArray", userTeams);
            coachWithTeams.putExtra("clubName", clubNameSpinnerChoice);
            context.startActivity(coachWithTeams);
        }
        else if (result.equals("Time saved successfully")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Data saved successfully")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("clubs found")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent coachTeamChoice = new Intent(context, CoachClubSelection.class);
            coachTeamChoice.putExtra("clubsArray", amountOfClubs);
            coachTeamChoice.putExtra("username", username);
            context.startActivity(coachTeamChoice);
        }
        else if (result.equals("Players found")) {
            Intent j = new Intent(context, CoachHome.class);
            j.putExtra("teamChoice", teamChoice);
            j.putExtra("playerList", playerList);
            j.putExtra("username",username);
            j.putExtra("teamChoice", teamNameChosen);
            context.startActivity(j);
        }
        else if (result.equals("Clubs found to join")){
            Intent y = new Intent(context, PlayerTeamJoin.class);
            y.putExtra("clubsArray", amountOfClubs);
            y.putExtra("username", username);
            context.startActivity(y);
        }
        else if (result.equals("Teams found for player")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent e = new Intent(context, PlayerTeamJoin.class);
            e.putExtra("username",username);
            e.putExtra("teamsArray", amountOfTeams);
            e.putExtra("clubsArray", userTeams);
            e.putExtra("clubName", clubNameSpinnerChoice);
            context.startActivity(e);
        }
        else if (result.equals("Team Joined Successfully")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent e = new Intent(context, PlayerHome.class);
            e.putExtra("username",username);
            e.putExtra("clubsArray", userTeams);
            context.startActivity(e);
        }
        else if (result.equals("Results Found")) {
            Intent w = new Intent(context, PlayerViewResults.class);
            w.putExtra("resultData", resultData);
            w.putExtra("username", username);
            context.startActivity(w);
        }
        else if (result.equals("Results Not Found")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

