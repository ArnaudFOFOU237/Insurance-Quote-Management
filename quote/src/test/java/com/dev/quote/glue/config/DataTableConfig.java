package com.dev.quote.glue.config;

import com.dev.quote.domaine.model.ClientId;
import com.dev.quote.domaine.model.PercentageInsure;
import com.dev.quote.domaine.model.ProductType;
import com.dev.quote.domaine.model.Quote;
import io.cucumber.java.DataTableType;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;

@Configuration
public class DataTableConfig {

    @DataTableType
    public Quote assetEntry(Map<String, String> entry) {
        return new Quote(new ClientId(Integer.parseInt(entry.get("CLIENT-ID"))),
                ProductType.valueOf(entry.get("PRODUCT-TYPE")),
                new PercentageInsure(new BigDecimal(entry.get("PERCENTAGE-INSURE"))));
    }
}
