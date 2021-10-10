package com.manhpd.before;

import com.manhpd.GiftRewardLoyaltyProgram;

public class LoyaltyProgramRepository {
    public GiftRewardLoyaltyProgram getGiftRewardLoyaltyProgram() {
        // Get loyalty program from the database
        return new GiftRewardLoyaltyProgram(1L, 100);
    }

    public GiftRewardLoyaltyProgram save(GiftRewardLoyaltyProgram lp) {
        // Save loyalty program to the database
        return lp;
    }
}
