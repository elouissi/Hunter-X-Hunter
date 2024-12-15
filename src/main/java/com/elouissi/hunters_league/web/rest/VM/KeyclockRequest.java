package com.elouissi.hunters_league.web.rest.VM;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyclockRequest {
    private String username;
    private String password;
}
