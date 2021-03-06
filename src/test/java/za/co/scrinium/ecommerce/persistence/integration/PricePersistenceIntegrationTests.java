/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Project Bibliotheca (Leo Ackerman)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package za.co.scrinium.ecommerce.persistence.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.scrinium.ecommerce.persistence.domain.Price;
import za.co.scrinium.ecommerce.persistence.repository.PriceDAO;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PricePersistenceIntegrationTests extends PersistenceIntegrationTests {

  @Autowired
  private PriceDAO priceDAO;

  @Test
  public void testPricePersistenceAndRetrieval() throws Exception {
    Price price = new Price();
    price.setCostPrice(new BigDecimal("10.99"));
    price.setSellingPrice(new BigDecimal("12.99"));
    priceDAO.create(price);

    List<Price> prices = priceDAO.getAll(Price.class);
    assertNotNull(prices);
    assertEquals(1, prices.size());

    Price retrieved = prices.get(0);

    assertEquals(price.getId(), retrieved.getId());
    assertEquals(price.getCostPrice(), retrieved.getCostPrice());
    assertEquals(price.getSellingPrice(), retrieved.getSellingPrice());

    priceDAO.delete(retrieved);

    prices = priceDAO.getAll(Price.class);
    assertNotNull(prices);
    assertEquals(0, prices.size());
  }

}