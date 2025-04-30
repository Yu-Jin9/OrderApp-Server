package com.backend.server.domain.order.repository;

import com.backend.server.domain.order.data.OrderEntity;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    List<OrderEntity> findAllByOrderByOrderTimeDesc();
    Optional<OrderEntity> findFirstByOrderByOrderTimeDesc();    //최신 주문 1건 조회

}
