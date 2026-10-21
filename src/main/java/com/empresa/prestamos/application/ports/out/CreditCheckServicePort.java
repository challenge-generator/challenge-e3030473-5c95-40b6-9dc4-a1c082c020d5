package com.empresa.prestamos.application.ports.out;

import java.math.BigDecimal;
import reactor.core.publisher.Mono;

public interface CreditCheckServicePort {
    
    Mono<CreditCheckResult> checkCreditHistory(String customerId, BigDecimal requestedAmount);
    
    record CreditCheckResult(
        String customerId,
        boolean approved,
        BigDecimal approvedAmount,
        int creditScore,
        String riskLevel,
        String rejectionReason
    ) {
        public static CreditCheckResult approved(String customerId, BigDecimal amount, int score, String risk) {
            return new CreditCheckResult(customerId, true, amount, score, risk, null);
        }
        
        public static CreditCheckResult rejected(String customerId, String reason) {
            return new CreditCheckResult(customerId, false, BigDecimal.ZERO, 0, "HIGH", reason);
        }
    }
}