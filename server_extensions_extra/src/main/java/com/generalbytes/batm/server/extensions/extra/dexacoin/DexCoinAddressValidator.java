package com.generalbytes.batm.server.extensions.extra.dexacoin;

import com.generalbytes.batm.server.extensions.*;

public class DexCoinAddressValidator implements ICryptoAddressValidator {

    private static final String CRYPTO_CURRENCY = ICurrencies.DEX;

    @Override
    public boolean isAddressValid(String address) {
        boolean result = isCryptoAddressValid(address);
        if (!result) {
            result = isPaperWalletSupported() && ExtensionsUtil.isValidEmailAddress(address);
        }
        return result;
    }
    @Override
    public boolean isPaperWalletSupported() {
        return false;
    }

    @Override
    public boolean mustBeBase58Address() {
        return false;
    }

    private boolean isCryptoAddressValid(String address) {
        if (address.startsWith(CRYPTO_CURRENCY.substring(1))) {
            return true;
        }else{
            return false;
        }
    }
}