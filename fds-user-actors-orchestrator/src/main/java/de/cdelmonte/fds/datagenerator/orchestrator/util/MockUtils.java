package de.cdelmonte.fds.datagenerator.orchestrator.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Address;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Balance;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BankAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BitcoinAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.PaypalAccount;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.IBANType;


public class MockUtils {
  private static Random rand = new Random();

  public static List<Balance> generateBalances(int howMany, MockNeat mock) {
    List<Balance> balances =
        mock.reflect(Balance.class).field("pending", mock.longs().range(10, 9999999))
            .field("paid", mock.longs().range(1, 9999999))
            .field("denied", mock.longs().range(10, 9999999))
            .field("received", mock.longs().range(10, 9999999)).list(howMany).val();

    return balances;
  }


  public static MockUnit<BankAccount> generateBankAccount(MockNeat mock) {
    Supplier<BankAccount> bankAccountSupplier = () -> {
      String name = mock.names().full().val();

      BankAccount bankAccount = new BankAccount();
      bankAccount.setIban(mock.ibans().type(IBANType.GERMANY).val());
      bankAccount.setBic("DEUTDEFF500");
      bankAccount.setAccountHolder(name);

      return bankAccount;
    };

    return () -> bankAccountSupplier;
  }

  public static MockUnit<PaypalAccount> generatePaypalAccount(MockNeat mock) {
    Supplier<PaypalAccount> paypalAccountSupplier = () -> {
      String name = mock.names().full().val();

      PaypalAccount paypalAccount = new PaypalAccount();
      paypalAccount.setAccountHolder(name);
      paypalAccount.setAddress(mock.emails().val());

      return paypalAccount;
    };

    return () -> paypalAccountSupplier;
  }

  public static MockUnit<BitcoinAccount> generateBitcoinAccount(MockNeat mock) {
    Supplier<BitcoinAccount> bitcoinAccountSupplier = () -> {

      BitcoinAccount bitcoinAccount = new BitcoinAccount();
      bitcoinAccount.setAddress(mock.strings().size(24).val());

      return bitcoinAccount;
    };

    return () -> bitcoinAccountSupplier;
  }

  public static MockUnitString generateBrowserLanguage(MockNeat mock) {
    Supplier<String> browserLanguagesSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append("de-DE,de").append(";q=0.9,").append("en-US").append(";q=0.8,")
          .append(mock.countries().iso2().val()).append(";q=0.7,")
          .append(mock.countries().iso2().val()).append(";q=0.6,")
          .append(mock.countries().iso2().val()).append(";q=");

      return buff.toString();
    };

    return () -> browserLanguagesSupplier;
  }

  public static Address generateAddress(MockNeat mock) {
    Supplier<String> zipSupplier = () -> {
      StringBuilder buff = new StringBuilder();
      buff.append(mock.chars().letters().val()).append(mock.chars().digits().val())
          .append(mock.chars().digits().val()).append(mock.chars().digits().val())
          .append(mock.chars().digits().val()).append(mock.chars().digits().val());

      return buff.toString();
    };
    MockUnitString zipMock = () -> zipSupplier;

    Supplier<String> streetNumberSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append(mock.chars().digits().val()).append(mock.chars().digits().val());

      return buff.toString();
    };
    MockUnitString streetNumberMock = () -> streetNumberSupplier;

    Supplier<String> germanCitiesSupplier = () -> {
      List<String> cities = Arrays.asList(new String[] {"Arzberg", "Aschaffenburg", "Aschersleben",
          "Asperg", "Aßlar", "Attendorn", "Aub", "Aue", "Auerbach in der Oberpfalz",
          "Auerbach/Vogtl.", "Augsburg", "Augustusburg", "Aulendorf", "Auma-Weidatal", "Aurich",
          "Babenhausen", "Bacharach", "Backnang", "Bad Aibling", "Bad Arolsen", "Bad Belzig",
          "Bad Bentheim", "Bad Bergzabern", "Bad Berka", "Bad Berleburg",
          "Bad Berneck im Fichtelgebirge", "Bad Bevensen", "Bad Bibra", "Bad Blankenburg",
          "Bad Bramstedt", "Bad Breisig", "Bad Brückenau", "Bad Buchau", "Bad Camberg",
          "Bad Colberg-Heldburg", "Bad Doberan", "Bad Driburg", "Bad Düben", "Bad Dürkheim",
          "Bad Dürrenberg", "Bad Dürrheim", "Bad Elster", "Bad Ems", "Baden-Baden",
          "Bad Fallingbostel", "Bad Frankenhausen/Kyffhäuser", "Bad Freienwalde (Oder)"});

      return cities.get(new Random().nextInt(cities.size()));
    };
    MockUnitString germanCitiesMock = () -> germanCitiesSupplier;

    Address address = mock.reflect(Address.class).field("country", "Germany")
        .field("city", germanCitiesMock).field("zipCode", zipMock)
        .field("streetAddress", mock.names()).field("streetNumber", streetNumberMock).val();

    return address;
  }

  public static MockUnit<String> generateUserAgent(MockNeat mock) {
    Supplier<String> supplier = () -> {
      String[] userAgents = {
          "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
          "Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:27.0) Gecko/20121011 Firefox/27.0",
          "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A",
          "Mozilla/4.7 [fr] (WinNT; I)"};

      return userAgents[rand.nextInt(4)];
    };

    return () -> supplier;
  }
}
