package com.github.dbee.utils;


import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {

    public static final String PK = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAufkSfMRr+e1oDfk1Nc3ce3UvFDbe7vibs8esZPzkW0GUxBb7IBhVo1JPSdOeHnSHUm8TQLAshd8zMb5ohMfygEq50seNSQ+1HE08k47sicnquGRPIIuuicouF5899pIuqvBj/jCImuaw/v2dgacCcmCgV4IsOJ2nyImIdXXbKwUAkQ+QpBU4AJeRN4jPMDV5Vfxn9IXjIXHOik3XIPo9HTfm4rRJOXU468UdeeUdaxqicaIGyCXTU0IXGPX0BqTXWuQ8/f0i/h8sM2Sn/Jq1YZNAmiECTQFeY+y7FkTTQJXLSua2SjyewN1IpO76moQpZ5+lCUi89OHZsOPJ3D9U3wIDAQAB";
    public static final String SK = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5+RJ8xGv57WgN+TU1zdx7dS8UNt7u+Juzx6xk/ORbQZTEFvsgGFWjUk9J054edIdSbxNAsCyF3zMxvmiEx/KASrnSx41JD7UcTTyTjuyJyeq4ZE8gi66Jyi4Xnz32ki6q8GP+MIia5rD+/Z2BpwJyYKBXgiw4nafIiYh1ddsrBQCRD5CkFTgAl5E3iM8wNXlV/Gf0heMhcc6KTdcg+j0dN+bitEk5dTjrxR155R1rGqJxogbIJdNTQhcY9fQGpNda5Dz9/SL+HywzZKf8mrVhk0CaIQJNAV5j7LsWRNNAlctK5rZKPJ7A3Uik7vqahClnn6UJSLz04dmw48ncP1TfAgMBAAECggEARpCGfkb9fXiiaeLsWL0C8ulU8sObNWupkL6r4CnA8AtjSkA6C0SQggcCRM4CCiZHDnVNBdeTHTzCrRZFjFRd5GX2Ok8zgcxu+Q0pdgfeIq413L9a8sttb6UCdj4bAcsX4A6Usmdx/eyYrjhHImHfYMzfKjUfWGAhwMrGt2L/xUNaJPcqarHDPXdV/Z5NHF+zAZ0hjeYTPfcQO9ERBPQsZbCr5vURPdtZuCuWhXPkoRDC0WKKPtZoNWmuJABD08BQdCvp7p+xvdST/3k2mqHdJpfSve0CdwvcE0kZYYcdRtJAjnlju47RXQBEL5uVr2KNrg3Mtgd8ScOscTDw5/2KcQKBgQD962hO/rdf6bEaIYzy040DY6T80haT8EEehstGdxTFdMOEFWoqEMezPCER1hlnWeLZt64jDCvcJ02f29Zg8o8plJKgD8Iv1KplZvZPbDuzYq8OfsF52ED3/xyjqPMZwLSXPPX30dWtWyci/oQ1EPt2Rr8SegzJzP4fCzzgpaEOOQKBgQC7fyXQ/eOyhYBWHLlRdT5w+JbH86tjXpZDlITfcGNGspA6KXgd33HuHwRIvSXCOc5gjVd+ykor7IevgMF7a8DjlJdeiewBfKJ0yjcqSmHpxHLBFU/my/cquUzbdBvSvjLjcF6GekUlY0PiLfCUe6TVq+HvD0yP8+6o9kl24V971wKBgQC1QxdzTOIa1fhXbxvvPJWtlbRxxofXk+ron9D8vIVXb8MixybCYLguev4gfXYlEUWfpj/pRQcKYgRH2edvzbflNup02MsG2gnJ2XZ7vAfiRtAwyMvU70txvevz6Oehuq8wR5RPFLw0xJ1rncVDHJxmEprT1czLQksmXh9XPuV6AQKBgAokWY5RfXSVphiCWz/dwK/psnZEZvvsDOLA8OMJOEWFuSl8PsQW5xnDUb8BhD5aVCCC5L22AYcYt0o2A8FCVd+5lOqHOV3nOP5qRcui1GMJvk9VcMoCNG96MH8aa7rdkPEeje5mRME9+lkMtRvDCGqKbqE4yaJrxngbQmQM0tZnAoGAOEQiWcoQGssIpKlkCspLENO44fOYNgd4lrfKigYwQlj2Gob6iJhCRlI6LdAxZYD7jQ2lsimEtn60jdKA9lfJXpoQ3OVXcH6LTD4PDK37U425NrdfoqBPbQC1VSPsjKLndsx5wE5uRBaLnuvR3C8akvWsKPV/XaTCo9yzAhRcQg0=";

    public static PublicKey getPublicKey() throws Exception {
        byte[] keyBytes;

        keyBytes = Base64.getDecoder().decode(PK);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKey() throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.getDecoder().decode(SK);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

}
