package com.generalbytes.batm.server.extensions.extra.dexacoin.exchanges;

import com.generalbytes.batm.server.extensions.ICurrencies;
import com.generalbytes.batm.server.extensions.IExchange;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DexCoinExchange implements IExchange{
    private static final String CRYPTO_CURRENCY = ICurrencies.DEX;
    private static final BigDecimal WALLET_BALANCE = new BigDecimal("1000000");
    private static final BigDecimal EXCHANGE_BALANCE = new BigDecimal("2000000");
    private static final String WALLET_ADDRESS = CRYPTO_CURRENCY.substring(1) + "GnubsaWBQf6J2TTvNLF5xLkMydhTjWsQi";

    private String preferredFiatCurrency;
    private BigDecimal rate = BigDecimal.ONE;

    public DexCoinExchange() {
    }

    public DexCoinExchange(String preferredFiatCurrency, BigDecimal rate) {
        this.rate = rate;
        this.preferredFiatCurrency = preferredFiatCurrency;
        if (ICurrencies.EUR.equalsIgnoreCase(preferredFiatCurrency)) {
            this.preferredFiatCurrency = ICurrencies.EUR;
        }
        if (ICurrencies.USD.equalsIgnoreCase(preferredFiatCurrency)) {
            this.preferredFiatCurrency = ICurrencies.USD;
        }
    }

    @Override
    public String purchaseCoins(BigDecimal amount, String cryptoCurrency, String fiatCurrencyToUse, String description) {
        if (cryptoCurrency.equalsIgnoreCase(CRYPTO_CURRENCY) && fiatCurrencyToUse.equalsIgnoreCase(preferredFiatCurrency)) {
            return "true";
        }else{
            return null;
        }
    }


    @Override
    public Set<String> getCryptoCurrencies() {
        Set<String> result = new HashSet<String>();
        result.add(CRYPTO_CURRENCY);
        return result;
    }

    @Override
    public Set<String> getFiatCurrencies() {
        Set<String> result = new HashSet<String>();
        result.add(preferredFiatCurrency);
        return result;
    }

    @Override
    public String getPreferredFiatCurrency() {
        return preferredFiatCurrency;
    }



    @Override
    public BigDecimal getCryptoBalance(String cryptoCurrency) {
        if (CRYPTO_CURRENCY.equalsIgnoreCase(cryptoCurrency)) {
            return WALLET_BALANCE;
        }else{
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal getFiatBalance(String cashCurrency) {
        if (preferredFiatCurrency.equalsIgnoreCase(cashCurrency)) {
            return EXCHANGE_BALANCE;
        }else{
            return BigDecimal.ZERO;
        }
    }

    @Override
    public String sendCoins(String destinationAddress, BigDecimal amount, String cryptoCurrency, String description) {
        return "txt_id";
    }

    @Override
    public String getDepositAddress(String cryptoCurrency) {
        return WALLET_ADDRESS;
    }

    @Override
    public String sellCoins(BigDecimal cryptoAmount, String cryptoCurrency, String fiatCurrencyToUse, String description) {
        return "tx_sell_id";
    }
}
