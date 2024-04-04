package com.domain.stub;

import com.domain.ddd.Stub;
import com.domain.entity.BankAccount;
import com.domain.entity.Transaction;
import com.domain.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * a fake in memory DataBase to perform domain's unit tests and infrastructure's integration tests.
 * <p>
 * Use the @Stub annotation to create a Bean for the framework used in the infrastructure layer for integration test.
 *
 * @see Stub
 */
@Stub
public final class DataBaseStub {
    
    private final List<User> users = new ArrayList<>(
            List.of(new User(UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f"), "jaboyd@email.com", "Password",
                            "John", "Boyd", "USER", UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"), new ArrayList<>(
                            List.of(UUID.fromString("866aad44-6b52-43be-95f3-9f26ccdea918"),
                                    UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002")))),
                    new User(UUID.fromString("866aad44-6b52-43be-95f3-9f26ccdea918"), "tenz@email.com", "Password",
                            "Tessa", "Carman", "USER", UUID.fromString("5a518f97-9808-4196-b71a-daf5d8081295"),
                            new ArrayList<>(List.of(UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f")))),
                    new User(UUID.fromString("28c3aa49-6b74-4dd6-aadb-6df300cfc002"), "bstel@email.com", "Password",
                            "Brian", "Stelzer", "USER", UUID.fromString("85a5c541-1486-46c9-bd5b-8701778457d6"),
                            new ArrayList<>(List.of(UUID.fromString("866aad44-6b52-43be-95f3-9f26ccdea918"),
                                    UUID.fromString("37c4d6f5-46e6-4265-a5ae-450425306d0f")))),
                    new User(UUID.fromString("677218cd-a42c-4885-95a9-bb9d5006fc17"), "gramps@email.com", "Password",
                            "Eric", "Cadigan", "USER", UUID.fromString("f41c1529-c180-4b59-9fc1-1aec8e032658"),
                            new ArrayList<>())));
    
    private final List<BankAccount> bankAccounts = new ArrayList<>(
            List.of(new BankAccount(UUID.fromString("eaf27617-2fd1-421e-a083-991b9fa03bf7"), new BigDecimal("10.00"),
                            UUID.fromString("204593f2-1aef-4760-9c6f-f38d348a0f17"),
                            UUID.fromString("09df757f-071b-42d1-9fac-d321ab39c684"), new ArrayList<>()),
                    new BankAccount(UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"), new BigDecimal("1500.00"),
                            UUID.fromString("c4755a5e-04c8-448d-a227-5658967d3458"),
                            UUID.fromString("8c602a63-721f-46b2-a640-985fb9498931"), new ArrayList<>(
                            List.of(UUID.fromString("9a00d550-47d0-40c4-a63d-c00971b69f73"),
                                    UUID.fromString("8ec246af-0e0f-4b30-968b-2ea36fb87a33")))),
                    new BankAccount(UUID.fromString("5a518f97-9808-4196-b71a-daf5d8081295"), new BigDecimal("500.00"),
                            UUID.fromString("bb5ff8a1-a484-4599-8ee4-47c13458b594"),
                            UUID.fromString("d28ac7cf-4aea-49c9-9070-dd39a23c1366"),
                            new ArrayList<>(List.of(UUID.fromString("e3703f77-182e-4b91-8388-ed0861df1607")))),
                    new BankAccount(UUID.fromString("85a5c541-1486-46c9-bd5b-8701778457d6"), new BigDecimal("1000.00"),
                            UUID.fromString("9555d309-9460-4caa-a21c-4ec2855dbcb9"),
                            UUID.fromString("76e12291-9dc5-43a8-a85c-51bc39b71b24"),
                            new ArrayList<>(List.of(UUID.fromString("47bc0b9b-3f5d-42f6-9b29-5b71e8033d17")))),
                    new BankAccount(UUID.fromString("f41c1529-c180-4b59-9fc1-1aec8e032658"), new BigDecimal("2000.00"),
                            UUID.fromString("94801973-6c50-4a33-a60b-e864df635809"),
                            UUID.fromString("ade7c7ac-84b0-44ce-82e0-3fae478b315b"), new ArrayList<>())));
    
    private final List<Transaction> transactions = new ArrayList<>(
            List.of(new Transaction(UUID.fromString("9a00d550-47d0-40c4-a63d-c00971b69f73"),
                            UUID.fromString("5a518f97-9808-4196-b71a-daf5d8081295"), new BigDecimal("125.00"),
                            "Premier virement.", LocalDateTime.now(), UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de")),
                    new Transaction(UUID.fromString("8ec246af-0e0f-4b30-968b-2ea36fb87a33"),
                            UUID.fromString("85a5c541-1486-46c9-bd5b-8701778457d6"), new BigDecimal("1550.00"),
                            "Second virement.", LocalDateTime.now(),
                            UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de")),
                    new Transaction(UUID.fromString("e3703f77-182e-4b91-8388-ed0861df1607"),
                            UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"), new BigDecimal("520.00"),
                            "Troisième virement.", LocalDateTime.now(),
                            UUID.fromString("5a518f97-9808-4196-b71a-daf5d8081295")),
                    new Transaction(UUID.fromString("47bc0b9b-3f5d-42f6-9b29-5b71e8033d17"),
                            UUID.fromString("2c070842-1506-45b8-a21b-bb6444c522de"), new BigDecimal("100.00"),
                            "Quatrième virement.", LocalDateTime.now(),
                            UUID.fromString("85a5c541-1486-46c9-bd5b-8701778457d6"))));
    
    public List<User> users() {
        return users;
    }
    
    public List<BankAccount> bankAccounts() {
        return bankAccounts;
    }
    
    public List<Transaction> transactions() {
        return transactions;
    }
}
