package com.card_database.mtg.carddatabasemtg.price;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DownloadCard {

    private static final String TAG = "Download";

    public static float downloadCard(String downloadUrl, String card_name) throws IOException {
        Document doc;
        String title = "";
        while (title.isEmpty()) {
            try {
                doc = Jsoup.connect(downloadUrl).get();
                title = doc.title();
                Log.d(TAG, title);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return parseTitle(card_name, title);
    }

    public static float parseTitle(String card_name, String title) {
        float cost = 0;
        String buf_cost = "";
        int i = 0;
        while (title.charAt(i) != '$')
            i++;
        i++;
        while (title.charAt(i) != ')') {
            buf_cost += title.charAt(i++);
        }
        cost = Float.parseFloat(buf_cost);
        Log.d(TAG, cost + "");
        return cost;
    }
}
