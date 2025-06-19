package org.mps.board;


import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdvertisementBoardTest {

    // CASO DE PRUEBA 1
    @Test
    public void Hay1AnuncioInicialmente(){
        AdvertisementBoard advertisementBoard = new AdvertisementBoard();

        int expected = 1;
        int actual = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(actual, expected);

    }

    // CASO DE PRUEBA 2
    @Test
    public void InsertarAnuncioDeTHECompany(){
        AdvertisementBoard advertisementBoard = new AdvertisementBoard();
        Advertisement ad = new Advertisement("Titulo", "Text", "THE Company");

        int expected = 1 + advertisementBoard.numberOfPublishedAdvertisements();

        AdvertiserDatabase databaseMock = mock(AdvertiserDatabase.class);
        PaymentGateway paymentMock = mock(PaymentGateway.class);


        advertisementBoard.publish(ad, databaseMock, paymentMock);

        int actual  = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(expected, actual);


    }

    // CASO DE PRUEBA 3
    @Test
    public void InsertarAnuncioSinSaldo(){
        AdvertisementBoard advertisementBoard = new AdvertisementBoard();
        Advertisement ad = new Advertisement("Titulo", "Text", "Pepe Gotera y Otilio");

        int expected = advertisementBoard.numberOfPublishedAdvertisements();

        AdvertiserDatabase databaseMock = mock(AdvertiserDatabase.class);
        PaymentGateway paymentMock = mock(PaymentGateway.class);


        when(databaseMock.advertiserIsRegistered(ad.advertiser)).thenReturn(true);
        when(paymentMock.advertiserHasFunds(ad.advertiser)).thenReturn(false);


        advertisementBoard.publish(ad, databaseMock, paymentMock);

        int actual  = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(expected, actual);


    }

    // CASO DE PRUEBA 4
    @Test
    public void InsertarAnuncioConSaldoYEnBBDD(){
        AdvertisementBoard advertisementBoard = new AdvertisementBoard();
        Advertisement ad = new Advertisement("Titulo", "Text", "Robin Robot");

        int expected = 1 + advertisementBoard.numberOfPublishedAdvertisements();

        AdvertiserDatabase databaseMock = mock(AdvertiserDatabase.class);
        PaymentGateway paymentMock = mock(PaymentGateway.class);

        when(databaseMock.advertiserIsRegistered(ad.advertiser)).thenReturn(true);
        when(paymentMock.advertiserHasFunds(ad.advertiser)).thenReturn(true);


        advertisementBoard.publish(ad, databaseMock, paymentMock);

        int actual  = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(expected, actual);
        verify(paymentMock, times(1)).chargeAdvertiser(ad.advertiser);

    }

    // CASO DE PRUEBA 5
    @Test
    public void InsertarDosAnunciosYBorrarUno(){
        String title1 = "Titulo1";
        String advertiser1 = "THE Company";
        AdvertisementBoard advertisementBoard = new AdvertisementBoard();
        Advertisement ad1 = new Advertisement(title1, "Text1", advertiser1);
        Advertisement ad2 = new Advertisement("Titulo2", "Text2", "THE Company");


        int expectedAds = 1 + advertisementBoard.numberOfPublishedAdvertisements();

        AdvertiserDatabase databaseMock = mock(AdvertiserDatabase.class);
        PaymentGateway paymentMock = mock(PaymentGateway.class);

        when(databaseMock.advertiserIsRegistered(ad1.advertiser)).thenReturn(true);
        when(paymentMock.advertiserHasFunds(ad1.advertiser)).thenReturn(true);

        when(databaseMock.advertiserIsRegistered(ad2.advertiser)).thenReturn(true);
        when(paymentMock.advertiserHasFunds(ad2.advertiser)).thenReturn(true);

        advertisementBoard.publish(ad1, databaseMock, paymentMock);
        advertisementBoard.publish(ad2, databaseMock, paymentMock);

        advertisementBoard.deleteAdvertisement(title1, advertiser1);


        int actualAds  = advertisementBoard.numberOfPublishedAdvertisements();
        //Comprobamos que la cantidad que es 2 => El inicial mas el segundo anuncio
        assertEquals(expectedAds, actualAds);

        Optional<Advertisement> actual = advertisementBoard.findByTitle(title1);
        assertFalse(actual.isPresent());
    }


    // CASO DE PRUEBA 6
    @Test
    public void InsertarAnuncioQueYaExiste(){
        String title1 = "Titulo1";
        String advertiser1 = "Anunciante1";
        AdvertisementBoard advertisementBoard = new AdvertisementBoard();
        Advertisement ad1 = new Advertisement(title1, "Text1", advertiser1);
        Advertisement ad2 = new Advertisement(title1, "Text2", advertiser1);


        int expectedAds = 1 + advertisementBoard.numberOfPublishedAdvertisements();

        AdvertiserDatabase databaseMock = mock(AdvertiserDatabase.class);
        PaymentGateway paymentMock = mock(PaymentGateway.class);

        when(databaseMock.advertiserIsRegistered(ad1.advertiser)).thenReturn(true);
        when(paymentMock.advertiserHasFunds(ad1.advertiser)).thenReturn(true);

        advertisementBoard.publish(ad1, databaseMock, paymentMock);
        advertisementBoard.publish(ad2, databaseMock, paymentMock);




        int actualAds  = advertisementBoard.numberOfPublishedAdvertisements();
        //Comprobamos que la cantidad que es 2 (no se ha incluido el segundo con mismo titulo y anunciante
        assertEquals(expectedAds, actualAds);

        //Verificamos que solo se realiza 1 pago por el primer anuncio
        verify(paymentMock, times(1)).chargeAdvertiser(advertiser1);
    }

    // CASO DE PRUEBA 7
    /*
    No tendría sentido en este caso usar un Test Double tipo spy ya que
    en este caso el test lo que esta verificando es que no se puedan publicar mas
    anuncios de los permitidos. No se esta pidiendo ver la interaccion con la bbdd
    o con la pasarela de pago. Por ello con los mocks es mas adecuado hacerlo.
     */
    @Test
    public void InsertarMasAnunciosQueElMaximo(){
        String title = "Titulo";
        String advertiser = "Tim O'Theo";
        int j = 20;
        AdvertisementBoard advertisementBoard = new AdvertisementBoard();

        AdvertiserDatabase databaseMock = mock(AdvertiserDatabase.class);
        PaymentGateway paymentMock = mock(PaymentGateway.class);

        when(databaseMock.advertiserIsRegistered(advertiser)).thenReturn(true);
        when(paymentMock.advertiserHasFunds(advertiser)).thenReturn(true);

        for(int i = 1; i <= 19; i++){
            Advertisement ad = new Advertisement(title + i, "Text", advertiser);

            advertisementBoard.publish(ad, databaseMock, paymentMock);

        }


        Advertisement ad19 = new Advertisement(title + j, "Text", advertiser);

        assertThrows(AdvertisementBoardException.class, () -> advertisementBoard.publish(ad19, databaseMock, paymentMock));


    }


}
