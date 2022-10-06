package com.manhpd.pension.setup;

import com.manhpd.pension.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;

class AccountOpeningServiceTest {

    private AccountOpeningService accountOpeningService;
    private BackgroundCheckService backgroundCheckService = mock(BackgroundCheckService.class);
    private ReferenceIdsManager referenceIdsManager = mock(ReferenceIdsManager.class);
    private AccountRepository accountRepository = mock(AccountRepository.class);

    @BeforeEach
    void setUp() {
        this.accountOpeningService =
                new AccountOpeningService(this.backgroundCheckService, this.referenceIdsManager, this.accountRepository);
    }

    @Test
    public void shouldOpenAccount() throws IOException {
        final AccountOpeningStatus accountOpeningStatus =
                this.accountOpeningService.openAccount("John", "Smith", "123XYZ9",
                                                        LocalDate.of(1990, 1, 1));
        Assertions.assertEquals(AccountOpeningStatus.OPENED, accountOpeningStatus);
    }
}