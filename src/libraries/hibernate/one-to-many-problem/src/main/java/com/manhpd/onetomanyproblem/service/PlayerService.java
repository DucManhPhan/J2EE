package com.manhpd.onetomanyproblem.service;

import com.manhpd.onetomanyproblem.persistence.entity.Player;
import com.manhpd.onetomanyproblem.persistence.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getPlayers() {
        return this.playerRepository.findAll();
    }

}
