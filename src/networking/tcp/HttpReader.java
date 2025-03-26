package networking.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class HttpReader {
    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpReader httpReader = new HttpReader();
        httpReader.readAddress();
        httpReader.readAddressOpenConnection();
    }

    private void readAddressOpenConnection() throws URISyntaxException, IOException {
        URL url = new URI("https://www.pudim.com.br/").toURL();
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        while((inputLine = br.readLine()) != null) {
            System.out.println(inputLine);
        }
        inputStream.close();
    }

    public void readAddress() throws IOException, URISyntaxException {
        URL url = new URI("https://www.pudim.com.br/").toURL();
        InputStream inputStream = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        while((inputLine = br.readLine()) != null) {
            System.out.println(inputLine);
        }
        inputStream.close();
    }

}
