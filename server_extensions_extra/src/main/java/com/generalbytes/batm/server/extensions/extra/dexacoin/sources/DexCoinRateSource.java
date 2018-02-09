package com.generalbytes.batm.server.extensions.extra.dexacoin.sources;

import com.generalbytes.batm.server.extensions.ICurrencies;
import com.generalbytes.batm.server.extensions.IRateSource;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DexCoinRateSource implements IRateSource {
    private static final String CRYPTO_CURRENCY = ICurrencies.DEX;

    private String preferredFiatCurrency;
    private BigDecimal rate = BigDecimal.ONE;

    public DexCoinRateSource(String preferedFiatCurrency, BigDecimal rate) {
        this.rate = rate;
        this.preferredFiatCurrency = preferedFiatCurrency;
        if (ICurrencies.EUR.equalsIgnoreCase(preferredFiatCurrency)) {
            this.preferredFiatCurrency = ICurrencies.EUR;
        }
        if (ICurrencies.USD.equalsIgnoreCase(preferredFiatCurrency)) {
            this.preferredFiatCurrency = ICurrencies.USD;
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
    public BigDecimal getExchangeRateLast(String cryptoCurrency, String fiatCurrency) {
        if (CRYPTO_CURRENCY.equalsIgnoreCase(cryptoCurrency)) {
            return rate;
        }
        return null;
    }

    @Override
    public String getPreferredFiatCurrency() {
        return preferredFiatCurrency;
    }
}
