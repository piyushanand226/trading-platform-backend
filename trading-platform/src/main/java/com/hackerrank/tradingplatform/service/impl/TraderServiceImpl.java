package com.hackerrank.tradingplatform.service.impl;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.exception.TraderAlreadyExistsException;
import com.hackerrank.tradingplatform.exception.TraderNotFoundException;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.repository.TraderRepository;
import com.hackerrank.tradingplatform.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TraderServiceImpl implements TraderService {

    @Autowired
    private TraderRepository traderRepository;

    @Override
    public boolean existsByEmail(String email) {
        return traderRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Trader registerTrader(Trader trader) {
        if (existsByEmail(trader.getEmail())) {
            throw new TraderAlreadyExistsException("Trader with email " + trader.getEmail() + " already exists.");
        }
        return traderRepository.save(trader);
    }

    @Override
    public Trader getTraderByEmail(String email) {
        return traderRepository.findByEmail(email)
                .orElseThrow(() -> new TraderNotFoundException("Trader not found with email: " + email));
    }

    @Override
    public List<Trader> getAllTraders() {
        return traderRepository.findAll();
    }

    @Override
    @Transactional
    public Trader updateTrader(UpdateTraderDTO dto) {
        Trader trader = traderRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new TraderNotFoundException("Trader not found with email: " + dto.getEmail()));

        // Update allowed fields only
        trader.setName(dto.getName());
        trader.setPhone(dto.getPhone());
        trader.setCountry(dto.getCountry());
        trader.setAccountType(dto.getAccountType());

        return traderRepository.save(trader);
    }

    @Override
    @Transactional
    public Trader addMoney(AddMoneyTraderDTO dto) {
        Trader trader = traderRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new TraderNotFoundException("Trader not found with email: " + dto.getEmail()));

        double newBalance = trader.getBalance() + dto.getAmount();
        trader.setBalance(newBalance);

        return traderRepository.save(trader);
    }
}
