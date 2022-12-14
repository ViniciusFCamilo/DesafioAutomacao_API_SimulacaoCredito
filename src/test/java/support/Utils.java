package support;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
    public static RequestSpecification request;
    public static void pegarRequest() {
        request = RestAssured.given().log().all().accept("application/json").contentType(ContentType.JSON);
    }

    private String CPF = gerarCPF();
    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) { soma += Integer.parseInt(num.substring(i, i + 1)) * peso--; }

        if (soma % 11 == 0 | soma % 11 == 1) { primDig = new Integer(0); }
        else { primDig = new Integer(11 - (soma % 11)); }

        soma = 0;peso = 11;

        for (int i = 0; i < num.length(); i++) { soma += Integer.parseInt(num.substring(i, i + 1)) * peso--; }
        soma += primDig.intValue() * 2;

        if (soma % 11 == 0 | soma % 11 == 1) { segDig = new Integer(0); }
        else { segDig = new Integer(11 - (soma % 11)); }

        return primDig.toString() + segDig.toString();
    }

    public static String gerarCPF() {
        String iniciais = "";
        Integer numero;
        for (int i = 0; i < 9; i++) {
            numero = new Integer((int) (Math.random() * 10));
            iniciais += numero.toString();
        }   return iniciais + calcDigVerif(iniciais);
    }

    public String getCpf() { return CPF; }

}
