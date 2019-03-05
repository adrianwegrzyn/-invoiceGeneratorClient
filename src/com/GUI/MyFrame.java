package com.GUI;

import com.Error.NoData;
import com.google.gson.Gson;
import com.listPDF.PDF;
import com.model.Buyer;
import com.model.Order;
import com.model.Seller;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.time.ZonedDateTime;


public class MyFrame extends JFrame implements ActionListener {
    private Buyer buyer = new Buyer();
    private Seller seller = new Seller();
    private Order order = new Order();

    private JLabel information;
    private JTextField name, adress, postcode, city, companyT, nameT, adressT, postcodeT, cityT, description, quantity, netPrice, textRate;
    private JLabel bName, bAdress, bPostCode, bCity, sCompany, sName, sAdress, sPostcode, sCity, sellerDetails, bDescription, bQuantity, bNetPrice, bTaxRate, buyersDetails, orderDetails, listPDF;
    private JButton send, show, clean;
    private JScrollPane listFile;
    private JTextArea list;

    public MyFrame() {

        //tworzenie okna

        setSize(800,500);
        setTitle("Generator faktur");
        setLayout(null);

        //kod bledu wyślij

        information = new JLabel("");
        information.setBounds(50,300,300,20);
        add(information);

        //przycisk wyslij

        send = new JButton("Wyślij");
        send.setBounds(50,400,100,20);
        add(send);
        send.addActionListener(this);

        //przycisk wyswietl

        show = new JButton("Wyświetl PDF-y");
        show.setBounds(390,370,150,20);
        add(show);
        show.addActionListener(this);

        //przycisk wyczysc

        clean = new JButton("Wyczyść liste");
        clean.setBounds(560,370,150,20);
        add(clean);
        clean.addActionListener(this);

        //lista plikow

        listPDF = new JLabel("Lista PDF-ów:");
        listPDF.setBounds(490,220,160,20);
        listPDF.setForeground(Color.red);
        listPDF.setFont(new Font("Courier New", Font.ITALIC, 16));
        add(listPDF);

        list = new JTextArea();
        list.setText("");
        listFile = new JScrollPane(list);
        listFile.setBounds(400,260,300,100);
        add(listFile);

        //pola textowe

        // ----------klient-----------
        name = new JTextField("");
        name.setBounds(130,50,160,20);
        add(name);

        adress = new JTextField("");
        adress.setBounds(130,80,160,20);
        add(adress);

        postcode = new JTextField("");
        postcode.setBounds(130,110,160,20);
        add(postcode);

        city = new JTextField("");
        city.setBounds(130,140,160,20);
        add(city);

        //----------sprzedawca----------
        companyT = new JTextField("Apollo");
        companyT.setBounds(530,50,160,20);
        add(companyT);

        nameT = new JTextField("Jan Kowalski");
        nameT.setBounds(530,80,160,20);
        add(nameT);

        adressT = new JTextField("Krakowska 5");
        adressT.setBounds(530,110,160,20);
        add(adressT);

        postcodeT = new JTextField("33-100");
        postcodeT.setBounds(530,140,160,20);
        add(postcodeT);

        cityT = new JTextField("Tarnow");
        cityT.setBounds(530,170,160,20);
        add(cityT);

        //----------zamowienie-----------
        description = new JTextField("");
        description.setBounds(130,250,160,20);
        add(description);

        quantity = new JTextField("");
        quantity.setBounds(130,280,160,20);
        add(quantity);

        netPrice = new JTextField("");
        netPrice.setBounds(130,310,160,20);
        add(netPrice);

        textRate = new JTextField("23");
        textRate.setBounds(130,340,160,20);
        add(textRate);

        //podpisy

        //---------klient----------
        buyersDetails = new JLabel("Dane klienta: ");
        buyersDetails.setBounds(20,20,160,20);
        buyersDetails.setForeground(Color.blue);
        buyersDetails.setFont(new Font("Courier New", Font.ITALIC, 16));
        add(buyersDetails);

        bName = new JLabel("Nazwa klienta");
        bName.setBounds(30,50,160,20);
        add(bName);

        bAdress = new JLabel("Adres");
        bAdress.setBounds(30,80,160,20);
        add(bAdress);

        bPostCode = new JLabel("Kod pocztowy");
        bPostCode.setBounds(30,110,160,20);
        add(bPostCode);

        bCity = new JLabel("Miasto klienta");
        bCity.setBounds(30,140,160,20);
        add(bCity);

        //----------sprzedawca----------
        sellerDetails = new JLabel("Dane sprzedawcy: ");
        sellerDetails.setBounds(400,20,180,20);
        sellerDetails.setForeground(Color.blue);
        sellerDetails.setFont(new Font("Courier New", Font.ITALIC, 16));
        add(sellerDetails);

        sCompany = new JLabel("Nazwa firmy");
        sCompany.setBounds(410,50,160,20);
        add(sCompany);

        sName = new JLabel("Imię i nazwisko");
        sName.setBounds(410,80,160,20);
        add(sName);

        sAdress = new JLabel("Adres ");
        sAdress.setBounds(410,110,160,20);
        add(sAdress);

        sPostcode = new JLabel("Kod pocztowy");
        sPostcode.setBounds(410,140,160,20);
        add(sPostcode);

        sCity = new JLabel("Miasto sprzedawcy");
        sCity.setBounds(410,170,160,20);
        add(sCity);

        //----------zamowienie----------
        orderDetails = new JLabel("Zamówienie:");
        orderDetails.setBounds(20,220,160,20);
        orderDetails.setForeground(Color.blue);
        orderDetails.setFont(new Font("Courier New", Font.ITALIC, 16));
        add(orderDetails);

        bDescription = new JLabel("Opis");
        bDescription.setBounds(30,250,160,20);
        add(bDescription);

        bQuantity = new JLabel("Ilość");
        bQuantity.setBounds(30,280,160,20);
        add(bQuantity);

        bNetPrice = new JLabel("Cena netto");
        bNetPrice.setBounds(30,310,160,20);
        add(bNetPrice);

        bTaxRate = new JLabel("VAT");
        bTaxRate.setBounds(30,340,160,20);
        add(bTaxRate);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        Object z = e.getSource();

        if(z == send) {
            try {
                String imie = name.getText();
                buyer.setBuyerName(imie);

                String adres = adress.getText();
                buyer.setBuyerAdress(adres);

                String kodPocztowy = postcode.getText();
                buyer.setBuyerPostcode(kodPocztowy);

                String miasto = city.getText();
                buyer.setBuyerCity(miasto);

                String opis = description.getText();
                order.setDescription(opis);

                String nazwaFirmy = companyT.getText();
                seller.setSellerCompany(nazwaFirmy);

                String nazwiskoFirma = nameT.getText();
                seller.setSellerName(nazwiskoFirma);

                String adresFirmy = adressT.getText();
                seller.setSellerAdress(adresFirmy);

                String kodFirmy = postcodeT.getText();
                seller.setSellerPostcode(kodFirmy);

                String miastoSprzedawcy = cityT.getText();
                seller.setSellerCity(miastoSprzedawcy);


                String ilosc = quantity.getText();
                order.setQuantity(Integer.parseInt(ilosc));

                String cenaNetto = netPrice.getText();
                order.setNetPrice(Double.parseDouble(cenaNetto));

                String podatek = textRate.getText();
                order.setTaxRate(Double.parseDouble(podatek));

                if(imie.isEmpty() || adres.isEmpty() || kodPocztowy.isEmpty() || miasto.isEmpty() || opis.isEmpty() || nazwaFirmy.isEmpty() || nazwiskoFirma.isEmpty() || adresFirmy.isEmpty() || kodFirmy.isEmpty() || kodFirmy.isEmpty() || miastoSprzedawcy.isEmpty() || ilosc.isEmpty() || cenaNetto.isEmpty() || podatek.isEmpty()) throw new NoData();

                HttpClientForPostMethod();
                HttpClientForGetMethod();

            } catch (NoData s) {
                JOptionPane.showMessageDialog(null, "Wypełnij wszystkie pola ");
            } catch (NumberFormatException ex_litery) {
                JOptionPane.showMessageDialog(null, "W polu ilość, cena netto lub podatek nie została podana liczba lub została podana nieprawidłowo !");
            }

        }else if(z == show){
            HttpClientForGetMethod();
        }
        else if(z == clean){
            list.setText("");
        }
    }

    private void HttpClientForPostMethod() {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/invoice");

        Gson gson = new Gson();

        final Order userData = new Order(order.getDescription(), order.getQuantity(), order.getNetPrice(), order.getTaxRate(), seller, buyer);
        final String json = gson.toJson(userData);

        try {
            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            final CloseableHttpResponse response = client.execute(httpPost);

            System.out.println("Kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if (response.getStatusLine().getStatusCode() == 404) {
                JOptionPane.showMessageDialog(null, "Prosze poprawic w kontrolerze sciezke do pliku - sciezka jest nieprawidlowa!");
            } else if (response.getStatusLine().getStatusCode() == 201) {
                long time = ZonedDateTime.now().toEpochSecond();
                JOptionPane.showMessageDialog(null, "Utworzono fakture  invoice_" + time + " !");
            }
            client.close();
        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "Nie moge polaczyc sie z serwerem");
        } catch (UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(null, "Problem z nieobsługiwanym kodowaniem POST");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            JOptionPane.showMessageDialog(null, "Problem z protokołem klienta POST");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problem z wyjściem POST");
            e.printStackTrace();
        }
    }

    private void HttpClientForGetMethod() {

        final HttpClient client = HttpClientBuilder.create().build();

        final HttpGet request = new HttpGet("http://127.0.0.1:8080/api/invoice");

        final Gson gson = new Gson();

        try {
            final HttpResponse response = client.execute(request);
            final HttpEntity entity = response.getEntity();

            final String json = EntityUtils.toString(entity);

            final PDF personJson = gson.fromJson(json, PDF.class);

            System.out.println("Kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if(response.getStatusLine().getStatusCode() == 404) {

                JOptionPane.showMessageDialog(null, "Brak danych do wyswietlenia!");
            } else if(response.getStatusLine().getStatusCode() == 200) {
                String lista = "";
                for( String  x : personJson.getList()){
                    lista= lista + x+"\n";
                }
                list.setText(lista);
                JOptionPane.showMessageDialog(null, "Wyświetlono liste faktur !");
            }

        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "Nie moge polaczyc sie z serwerem");
            System.out.println();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problem z GET");
            e.printStackTrace();
        }
    }
}