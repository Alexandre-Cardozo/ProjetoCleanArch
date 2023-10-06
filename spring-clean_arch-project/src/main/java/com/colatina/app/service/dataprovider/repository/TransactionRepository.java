package com.colatina.app.service.dataprovider.repository;

import com.colatina.app.service.dataprovider.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findAllByAccountOriginIdAndCreatedAtBetween(Integer accountId, LocalDateTime startDate, LocalDateTime endDate);

    List<TransactionEntity> findAllByAccountOriginIdAndValue(Integer accountId, BigDecimal value);

    @Query("SELECT SUM (entity.value) AS amount " +
            "FROM TransactionEntity entity " +
            "WHERE (entity.accountDestination.id = :accountId " +
            "OR entity.accountOrigin.id = :accountId) " +
            "AND entity.createdAt >= TO_DATE (:startDate, 'YYYY-MM-DD') " +
            "AND entity.createdAt <= TO_DATE (:endDate, 'YYYY-MM-DD')")
    BigDecimal getAmountAccountStatementByPeriod(@Param("accountId") Integer accountId,
                                                 @Param("startDate") LocalDateTime startDate,
                                                 @Param("endDate") LocalDateTime endDate);
}
