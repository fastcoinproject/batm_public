package com.generalbytes.batm.server.extensions.extra.dexacoin.wallets;

import com.generalbytes.batm.server.extensions.ICurrencies;
import com.generalbytes.batm.server.extensions.IWallet;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DexCoinWallet implements IWallet {

    private static final String CRYPTO_CURRENCY = ICurrencies.DEX;
    private static final String WALLET_ADDRESS = CRYPTO_CURRENCY.substring(1) + "GnubsaWBQf6J2TTvNLF5xLkMydhTjWsQi";

    private static final BigDecimal WALLET_BALANCE = new BigDecimal("1000000");

    @Override
    public String sendCoins(String destinationAddress, BigDecimal amount, String cryptoCurrency, String description) {
        return null;
    }

    @Override
    public String getCryptoAddress( String cryptoCurrency) {
        if (CRYPTO_CURRENCY.equalsIgnoreCase(cryptoCurrency)) {
            return WALLET_ADDRESS;
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
    public String getPreferredCryptoCurrency() {
        return CRYPTO_CURRENCY;
    }


    @Override
    public BigDecimal getCryptoBalance(String cryptoCurrency) {
        if (CRYPTO_CURRENCY.equalsIgnoreCase(cryptoCurrency)) {
            return WALLET_BALANCE;
        }else{
            return BigDecimal.ZERO;
        }
    }
}
