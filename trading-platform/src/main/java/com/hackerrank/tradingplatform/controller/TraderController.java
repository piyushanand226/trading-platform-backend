package com.hackerrank.tradingplatform.controller;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.TraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trading/traders")
public class TraderController {

    @Autowired
    private TraderService traderService;

   @PostMapping("/register")
public ResponseEntity<TraderDTO> registerTrader(@RequestBody @Valid Trader trader) {
    if (traderService.existsByEmail(trader.getEmail())) {
        throw new TraderAlreadyExistsException("Trader with email " + trader.getEmail() + " already exists.");
    }
    Trader savedTrader = traderService.registerTrader(trader);
    return ResponseEntity.status(HttpStatus.CREATED).body(new TraderDTO(savedTrader));
}

@GetMapping
public ResponseEntity<TraderDTO> getTraderByEmail(@RequestParam("email") String email) {
    Trader trader = traderService.getTraderByEmail(email);
    if (trader == null) {
        throw new TraderNotFoundException("No trader found with email: " + email);
    }
    return ResponseEntity.ok(new TraderDTO(trader));
}

    // ✅ Get all traders
    @GetMapping("/all")
    public ResponseEntity<List<TraderDTO>> getAllTraders() {
        List<TraderDTO> traders = traderService.getAllTraders()
                .stream()
                .map(TraderDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(traders);
    }

    // ✅ Update trader
    @PutMapping
    public ResponseEntity<TraderDTO> updateTrader(@RequestBody @Valid UpdateTraderDTO traderDTO) {
        Trader updated = traderService.updateTrader(traderDTO);
        return ResponseEntity.ok(new TraderDTO(updated));
    }

    // ✅ Add money to trader
    @PutMapping("/add")
    public ResponseEntity<TraderDTO> addMoney(@RequestBody @Valid AddMoneyTraderDTO moneyDTO) {
        Trader updated = traderService.addMoney(moneyDTO);
        return ResponseEntity.ok(new TraderDTO(updated));
    }
}
