package com.hackerrank.tradingplatform.service;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;

import java.util.List;

public interface TraderService {

    boolean existsByEmail(String email);

    Trader registerTrader(Trader trader);

    Trader getTraderByEmail(String email);

    List<Trader> getAllTraders();

    Trader updateTrader(UpdateTraderDTO traderDTO);

    Trader addMoney(AddMoneyTraderDTO moneyDTO);
}
