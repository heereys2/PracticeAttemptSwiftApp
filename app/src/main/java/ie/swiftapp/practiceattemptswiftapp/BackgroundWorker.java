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
    public String username;
    public String clubNameSpinnerChoice;
    public String[] userTeams;
    public String[] amountOfTeams;
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://swiftproject.000webhostapp.com/login.php";
        String register_url = "http://swiftproject.000webhostapp.com/register.php";
        String createTeam_url = "http://swiftproject.000webhostapp.com/createteam.php";
        String club_url = "http://swiftproject.000webhostapp.com/clubs.php";
        String teams_url = "http://swiftproject.000webhostapp.com/teams.php";
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                username = user_name;
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
        } else if(type.equals("register")) {
            try {
                String email = params[1];
                String username = params[2];
                String password = params[3];
                String userType = params[4];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
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
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("coachTeam")){
            try {
                String clubName = params[1];
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
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        //alertDialog.setMessage(result);
        //alertDialog.show();
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
            context.startActivity(k);
        } else if (result.equals("Team Created successfully")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent j = new Intent(context, CoachTeamChoice.class);
            context.startActivity(j);
        } else if (result.equals("Login Failed")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        } else if (result.equals("teams found")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Intent coachWithTeams = new Intent(context, CoachClubSelection.class);
            coachWithTeams.putExtra("teamsArray", amountOfTeams);
            coachWithTeams.putExtra("clubsArray", userTeams);
            coachWithTeams.putExtra("clubName", clubNameSpinnerChoice);
            context.startActivity(coachWithTeams);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
