package be.bnair.smsforwarderlegacy;

import android.os.AsyncTask;

import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class RequestTask extends AsyncTask<String, String, String> {

    String sender, message;

    public RequestTask(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    protected String doInBackground(String... uri) {
        String tokenWebhook = "your discord webhook url";
        String jsonBrut = "";
        jsonBrut += "{\"embeds\": [{"
                + "\"title\": \""+ sender +"\","
                + "\"description\": \""+ message.trim() +"\","
                + "\"color\": 15258703"
                + "}]}";
        try {
            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Bnair_");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}