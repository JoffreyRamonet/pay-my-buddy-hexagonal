package com.domain.api;

import com.domain.dto.BuddyDto;
import com.domain.dto.PasswordDto;

/**
 * Interface to check if the inputs are correct.
 */
public interface InputChecker {
    Boolean sameInputCheck(String firstInput, String secondInput);
    
    Boolean sameEmailCheck(BuddyDto buddyDto);
    
    Boolean GoodPatternEmailCheck(String email);
    
    Boolean passwordModifyCheck(PasswordDto passwordDto);
    
    Boolean buddyEmailAlreadyExistCheck(BuddyDto buddyDto);
    
    Boolean buddyRelationAlreadyExistCheck(BuddyDto buddyDto);
}
