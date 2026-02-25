package com.dev.quote.service;

import com.dev.quote.domaine.model.*;

import java.math.BigDecimal;
import java.util.UUID;

public class QuoteTest {

    public static Quote setup () {
        return new Quote(new ClientId(2534), ProductType.AUTO, new PercentageInsure(new BigDecimal(50)));
    }

    public static Quote setupWithID () {
        return new Quote(UUID.fromString("d4b3f7be-bb00-402a-a938-3420ddd17740"),
                new ClientId(2534),
                QuoteStatus.PROVISOIRE,
                ProductType.AUTO,
                new PercentageInsure(new BigDecimal(50)),
                new CapitalInsure(new BigDecimal(750000)),
                24);
    }
}
