package com.generalbytes.batm.server.extensions.extra.dexacoin;

import com.generalbytes.batm.server.extensions.*;
import com.generalbytes.batm.server.extensions.extra.dexacoin.exchanges.DexCoinExchange;
import com.generalbytes.batm.server.extensions.extra.dexacoin.sources.DexCoinRateSource;
import com.generalbytes.batm.server.extensions.extra.dexacoin.wallets.DexCoinWallet;
import com.generalbytes.batm.server.extensions.watchlist.IWatchList;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DexCoinExtension implements IExtension {
    private static final String CRYPTO_CURRENCY = ICurrencies.DEX;

    @Override
    public String getName() {
        return "BATM " + CRYPTO_CURRENCY + " extension";
    }

    @Override
    public Set<String> getSupportedCryptoCurrencies() {
        Set<String> result = new HashSet<>();
        result.add(CRYPTO_CURRENCY);
        return result;
    }

    @Override
    public IExchange createExchange(String exchangeLogin) {
        if (exchangeLogin !=null && !exchangeLogin.trim().isEmpty()) {
            StringTokenizer st = new StringTokenizer(exchangeLogin,":");
            String exchangeType = st.nextToken();

            if ((CRYPTO_CURRENCY.toLowerCase()+ "_exchange").equalsIgnoreCase(exchangeType)) {
                BigDecimal rate = BigDecimal.ZERO;
                if (st.hasMoreTokens()) {
                    try {
                        rate = new BigDecimal(st.nextToken());
                    } catch (Throwable e) {
                    }
                }
                String preferredFiatCurrency = ICurrencies.USD;
                if (st.hasMoreTokens()) {
                    preferredFiatCurrency = st.nextToken().toUpperCase();
                }
                return new DexCoinExchange(preferredFiatCurrency,rate);
            }
        }
        return null;
    }

    @Override
    public IPaymentProcessor createPaymentProcessor(String paymentProcessorLogin) {
        return null; //no payment processors available
    }

    @Override
    public IRateSource createRateSource(String sourceLogin) {
        if (sourceLogin != null && !sourceLogin.trim().isEmpty()) {
            StringTokenizer st = new StringTokenizer(sourceLogin,":");
            String exchangeType = st.nextToken();

            if ((CRYPTO_CURRENCY.toLowerCase() + "_fix").equalsIgnoreCase(exchangeType)) {
                BigDecimal rate = BigDecimal.ZERO;
                if (st.hasMoreTokens()) {
                    try {
                        rate = new BigDecimal(st.nextToken());
                    } catch (Throwable e) {
                    }
                }
                String preferedFiatCurrency = ICurrencies.USD;
                if (st.hasMoreTokens()) {
                    preferedFiatCurrency = st.nextToken().toUpperCase();
                }
                return new DexCoinRateSource(preferedFiatCurrency,rate);
            }
        }
        return null;
    }

    @Override
    public IWallet createWallet(String walletLogin) {
        if (walletLogin !=null && !walletLogin.trim().isEmpty()) {
            StringTokenizer st = new StringTokenizer(walletLogin,":");
            String walletType = st.nextToken();

            if ((CRYPTO_CURRENCY.toLowerCase()+ "_wallet").equalsIgnoreCase(walletType)) {
                return new DexCoinWallet();
            }
        }
        return null;
    }

    @Override
    public ICryptoAddressValidator createAddressValidator(String cryptoCurrency) {
        if (CRYPTO_CURRENCY.equalsIgnoreCase(cryptoCurrency)) {
            return new DexCoinAddressValidator();
        }
        return null;
    }

    @Override
    public IPaperWalletGenerator createPaperWalletGenerator(String cryptoCurrency) {
        return null;
    }

    @Override
    public Set<String> getSupportedWatchListsNames() {
        return null;
    }

    @Override
    public IWatchList getWatchList(String name) {
        return null;
    }
}
