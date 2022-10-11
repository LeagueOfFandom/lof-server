package com.lof.lofserver.domain.game.sub;

import lombok.Getter;

@Getter
public class Damage {
    private Long dealt;
    private Long dealt_to_champions;
    private Long taken;
}
