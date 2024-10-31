package com.elouissi.hunters_league;

import lombok.Getter;

@Getter
public enum SpeciesType {

        SEA(9), BIG_GAME(3), BIRD(5);

        private final int value;

        SpeciesType(int value) {
            this.value = value;
        }
    }
