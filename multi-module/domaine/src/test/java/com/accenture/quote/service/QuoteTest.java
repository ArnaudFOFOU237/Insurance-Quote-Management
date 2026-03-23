package com.accenture.quote.service;


import com.accenture.quote.model.*;
import com.accenture.quote.model.valueobject.CapitalInsure;
import com.accenture.quote.model.valueobject.ClientId;
import com.accenture.quote.model.valueobject.PercentageInsure;

import java.math.BigDecimal;
import java.util.UUID;

public class QuoteTest {

    public static Quote setup () {
        return new Quote(ClientId.of(1234), ProductType.AUTO, new PercentageInsure(new BigDecimal(50)));
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
