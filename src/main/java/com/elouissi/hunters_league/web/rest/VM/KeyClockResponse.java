package com.elouissi.hunters_league.web.rest.VM;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyClockResponse {
    private String accessToken;
    private String refreshToken;
}
