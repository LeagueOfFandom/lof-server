package com.lof.lofserver.domain.game.sub;

import lombok.Getter;

@Getter
public class KillCounter {
    private Long inhibitors;
    private Long turrets;
    private Long wards;
    private Long players;
}
