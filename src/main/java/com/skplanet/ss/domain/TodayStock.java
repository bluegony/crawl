package com.skplanet.ss.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 2017. 2. 16..
 */
@Getter
@Setter
@ToString
public class TodayStock {
    String name;
    String priceIndex;
    String fluctuation; // 등락
    String fluctuationPercent;

    String individual;
    String foreigner;
    String institution;

    public void checkMinusAndSetFluctuation(String f) {
        if (fluctuationPercent.charAt(0) == '-')
            f = '-'+f;
        fluctuation = f;
    }
}
