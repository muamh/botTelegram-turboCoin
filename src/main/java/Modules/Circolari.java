package Modules;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Circolari {

    public static String getContentCircolari() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Document document = Jsoup.connect("https://iislonato.edu.it/circolare/").get();
        Elements articleElements = document.select(".col-lg-7 article");

        for (Element article : articleElements) {
            String data = article.select("div.date").text().trim();
            String titolo = article.select("h2.h3").text().trim();
            String link = article.select("a").attr("href").trim();
            String indirizzamento = article.select("div.p-1").text().trim();
            contentBuilder.append("Circolare:\nindirizzato a: " + indirizzamento  + ".\ndata a: " + data  + ".\ntitolo a: " + titolo + ".\nlink a: " + link).append("\n\n");
        }

        return contentBuilder.toString().trim();
    }

    public static String getContentCircolariAta() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Document document = Jsoup.connect("https://iislonato.edu.it/tipologia-circolare/circolari-per-docenti-e-personale-ata/").get();
        Elements articleElements = document.select(".col-lg-7 article");

        String data = articleElements.get(0).select("div.date").text().trim();
        String titolo = articleElements.get(0).select("h2.h3").text().trim();
        String link = articleElements.get(0).select("a").attr("href").trim();
        String indirizzamento = articleElements.get(0).select("div.p-1").text().trim();
        contentBuilder.append("Circolare:\nindirizzato a: ").append(indirizzamento).append(";\nPubblicata il: ").append(data).append(";\nClausola: ").append(titolo).append(";\nlink: ").append(link).append("\n\n");

        return contentBuilder.toString().trim();
    }

    public static String getContentCircolariFamiglie() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Document document = Jsoup.connect("https://iislonato.edu.it/tipologia-circolare/circolari-per-alunni-e-famiglie/").get();
        Elements articleElements = document.select(".col-lg-7 article");

        String data = articleElements.get(0).select("div.date").text().trim();
        String titolo = articleElements.get(0).select("h2.h3").text().trim();
        String link = articleElements.get(0).select("a").attr("href").trim();
        String indirizzamento = articleElements.get(0).select("div.p-1").text().trim();
        contentBuilder.append("Circolare:\nindirizzato a: ").append(indirizzamento).append(";\nPubblicata il: ").append(data).append(";\nClausola: ").append(titolo).append(";\nlink: ").append(link).append("\n\n");


        return contentBuilder.toString().trim();
    }
}
